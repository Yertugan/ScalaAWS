package week6.actor

import akka.actor.{Actor, ActorLogging, Props}
import week6.model.{ErrorResponse, Movie, SuccessfulResponse}

// props
// messages
object MovieManager {

  // Create
  case class CreateMovie(movie: Movie)

  // Read
  case class ReadMovie(id: String)

  // Update
  case class UpdateMovie(movie: Movie)

  // Delete
  case class DeleteMovie(id: String)

  def props() = Props(new MovieManager)
}

// know about existing movies
// can create a movie
// can manage movie
class MovieManager extends Actor with ActorLogging {

  // import companion OBJECT
  import MovieManager._

  var movies: Map[String, Movie] = Map()

  override def receive: Receive = {

    case CreateMovie(movie) =>
      movies.get(movie.id) match {
        case Some(existingMovie) =>
          log.warning(s"Could not create a movie with ID: ${movie.id} because it already exists.")
          sender() ! ErrorResponse(409, s"Movie with ID: ${movie.id} already exists.")

        case None =>
          movies = movies + (movie.id -> movie)
          log.info("Movie with ID: {} created.", movie.id)
          sender() ! SuccessfulResponse(201, s"Movie with ID: ${movie.id} created.")
      }

    case msg: ReadMovie =>
      movies.get(msg.id) match {
        case Some(movie) =>
          log.info(s"Movie with ID: {} exists.", movie.id)
          sender() ! movie

        case None =>
          log.warning(s"Movie with ID: ${msg.id} does not exists.")
          sender() ! ErrorResponse(404, s"Movie with ID: ${msg.id} not found.")
      }

    case UpdateMovie(movie) =>
      movies.get(movie.id) match {
        case Some(movie) =>
          movies = movies + (movie.id -> movie)
          log.info(s"Movie with ID: {} is updated.", movie.id)
          sender() ! movie

        case None =>
          log.warning(s"Movie with ID: {} cannot be updated because it does not exists.", movie.id)
          sender() ! ErrorResponse(404, s"Movie with ID: ${movie.id} not found.")
      }

    case msg: DeleteMovie =>
      movies.get(msg.id) match {
        case Some(movie) =>
          log.info(s"Movie with ID: {} is deleted.", msg.id)
          movies = movies - msg.id
          sender() ! movie

        case None =>
          log.warning(s"Movie with ID: {} cannot be deleted because it does not exists.", msg.id)
          sender() ! ErrorResponse(404, s"Movie with ID: ${msg.id} not found.")
      }
  }

  def randomInt() =
    // FIXME: use random
    4
}
