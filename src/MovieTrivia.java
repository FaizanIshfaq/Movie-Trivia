import java.util.*;

import file.MovieDB;
import movies.Actor;
import movies.Movie;

/**
 * Movie trivia class providing different methods for querying and updating a movie database.
 */
public class MovieTrivia {
	
	/**
	 * Create instance of movie database
	 */
	MovieDB movieDB = new MovieDB();
	
	
	public static void main(String[] args) {
		
		//create instance of movie trivia class
		MovieTrivia mt = new MovieTrivia();
		
		//setup movie trivia class
		mt.setUp("C:/Users/zes/Desktop/SDA Project/Movie-Trivia/src/file/moviedata.txt", "C:/Users/zes/Desktop/SDA Project/Movie-Trivia/src/file/movieratings.csv");
	}
	
	/**
	 * Sets up the Movie Trivia class
	 * @param movieData .txt file
	 * @param movieRatings .csv file
	 */
	public void setUp(String movieData, String movieRatings) {
		//load movie database files
		movieDB.setUp(movieData, movieRatings);
		
		//print all actors and movies
		this.printAllActors();
		this.printAllMovies();		
	}
	
	/**
	 * Prints a list of all actors and the movies they acted in.
	 */
	public void printAllActors () {
		System.out.println(movieDB.getActorsInfo());
	}
	
	/**
	 * Prints a list of all movies and their ratings.
	 */
	public void printAllMovies () {
		System.out.println(movieDB.getMoviesInfo());
	}

//	public Collection<Object> getCommonActors(String doubt, String the_post, ArrayList<Actor> actorsInfo) {
//		Collection<Actor> commonActors = new HashSet<>();
//
//		// find the movies with the given titles
//		Movie movie1 = movieDB.findMovieByTitle(title1);
//		Movie movie2 = movieDB.findMovieByTitle(title2);
//
//		// if either movie is not found, return an empty collection
//		if (movie1 == null || movie2 == null) {
//			return commonActors;
//		}
//
//		// find the actors that appear in both movies
//		Set<Actor> actors1 = movie1.getActors();
//		Set<Actor> actors2 = movie2.getActors();
//		for (Actor actor : actors1) {
//			if (actors2.contains(actor)) {
//				commonActors.add(actor);
//			}
//		}
//
//		return commonActors;
//		return null;
//	}


	public ArrayList<String> getCommonActors(String movie1, String movie2, ArrayList<Actor> actors) {
		ArrayList<String> commonActors = new ArrayList<String>();
		for (Actor actor : actors) {
			if (actor.getMoviesCast().contains(movie1.toLowerCase()) && actor.getMoviesCast().contains(movie2.toLowerCase())) {
				commonActors.add(actor.getName());
			}
		}
		return commonActors;
	}



	//	public Collection<Object> goodMovies(ArrayList<Movie> moviesInfo) {
//		return null;
//	}
	public ArrayList<String> goodMovies(ArrayList<Movie> movies) {
	ArrayList<String> goodMovies = new ArrayList<String>();
	for (Movie movie : movies) {
		if (movie.getCriticRating() >= 85 && movie.getAudienceRating() >= 85) {
			goodMovies.add(movie.getName());
		}
	}
	return goodMovies;
}



	public ArrayList<String> getCommonMovie(String actor1, String actor2, ArrayList<Actor> actors) {
		ArrayList<String> commonMovies = new ArrayList<String>();
		ArrayList<String> movies1 = new ArrayList<String>();
		ArrayList<String> movies2 = new ArrayList<String>();

		// find movies for actor1
		for (Actor actor : actors) {
			if (actor.getName().equals(actor1.toLowerCase())) {
				movies1.addAll(actor.getMoviesCast());
			}
		}

		// find movies for actor2
		for (Actor actor : actors) {
			if (actor.getName().equals(actor2.toLowerCase())) {
				movies2.addAll(actor.getMoviesCast());
			}
		}

		// find common movies
		for (String movie : movies1) {
			if (movies2.contains(movie)) {
				commonMovies.add(movie);
			}
		}

		return commonMovies;
	}




//	public Collection<Object> getCommonMovie(String meryl_streep, String tom_hanks, ArrayList<Actor> actorsInfo) {
//		return null;
//	}

//	public Collection<Object> getCoActors(String meryl_streep, ArrayList<Actor> actorsInfo) {
//		return null;
//	}
//public Collection<Object> getCoActors(String actorName, ArrayList<Actor> actorsInfo) {
//	Set<String> coActors = new HashSet<>();
//	for (Actor actor : actorsInfo) {
//		if (actor.getName().equalsIgnoreCase(actorName)) {
//			// found the actor, now iterate through their movies
//			for (Movie movie : actor.getMoviesCast()) {
//				for (String coActor : movie.getCast()) {
//					if (!coActor.equalsIgnoreCase(actorName)) {
//						coActors.add(coActor);
//					}
//				}
//			}
//			break;
//		}
//	}
//	return coActors;
//}
//public Collection<String> getCoActors(String actorName, ArrayList<Actor> actorsInfo) {
//	Set<String> coActors = new HashSet<>();
//	for (Actor actor : actorsInfo) {
//		if (actor.getName().equalsIgnoreCase(actorName)) {
//			for (String movieTitle : actor.getMoviesCast()) {
//				for (Movie movie : movieDB.getMoviesInfo()) {
//					if (movie.getName().equalsIgnoreCase(movieTitle)) {
//						for (String castMember : movie) {
//							if (!castMember.equalsIgnoreCase(actorName)) {
//								coActors.add(castMember);
//							}
//						}
//						break;
//					}
//				}
//			}
//			break;
//		}
//	}
//	return coActors;
//}


//	public void insertActor(String s, String[] strings, ArrayList<Actor> actorsInfo) {
////		this.insertActor(s, strings, actorsInfo);
////		actorsInfo.add(new Actor(s));
//
//
//	}


	public void insertActor(String name, String[] movies, ArrayList<Actor> actorsInfo) {
		// clean up actor name
		name = name.trim().toLowerCase();
		// check if actor already exists
		Actor actor = findActorByName(name, actorsInfo);
		if (actor != null) {
			// actor already exists, add new movies to their moviesCasted list
			for (String movie : movies) {
				movie = movie.trim().toLowerCase();
				if (!actor.getMoviesCast().contains(movie)) {
					actor.getMoviesCast().add(movie);
				}
			}
		} else {
			// create new actor with moviesCasted list
			actor = new Actor(name, new ArrayList<>(List.of(movies)));
			actorsInfo.add(actor);
		}
	}


	private Actor findActorByName(String name, ArrayList<Actor> actorsInfo) {
		for (Actor actor : actorsInfo) {
			if (actor.getName().equals(name)) {
				return actor;
			}
		}
		return null;
	}





	//	public Collection<Object> selectWhereActorIs(String meryl_streep, ArrayList<Actor> actorsInfo) {
//		return null;
//	}
public Collection<Object> selectWhereActorIs(String actorName, ArrayList<Actor> actorsInfo) {
	ArrayList<Object> movies = new ArrayList<Object>();
	for (Actor actor : actorsInfo) {
		if (actor.getName().equalsIgnoreCase(actorName)) {
			for (String movieName : actor.getMoviesCast()) {
				movies.add(movieName.toLowerCase());
			}
		}
	}
	return movies;
}
//	public void insertRating(String testmovie, int[] ints, ArrayList<Movie> moviesInfo) {
//
////		this.insertRating(testmovie, ints, moviesInfo);
////		moviesInfo.add(new Movie(testmovie, ints[0], ints[1], ints[2], ints[3], ints[4]	));
//	}
public void insertRating(String movieName, int[] ratings, ArrayList<Movie> movies) {
	// Check if the movie already exists in the database
	for (Movie movie : movies) {
		if (movie.getName().equalsIgnoreCase(movieName)) {
			// Movie found, update its ratings
			movie.setCriticRating(ratings[0]);
			movie.setAudienceRating(ratings[1]);
			return;
		}
	}
	// Movie not found, add it to the database
	Movie newMovie = new Movie(movieName, ratings[0], ratings[1]);
	movies.add(newMovie);
}
//	public Collection<Object> selectWhereRatingIs(char c, int i, boolean b, ArrayList<Movie> moviesInfo) {
//		return null;
//	}
	public Collection<Object> selectWhereRatingIs(char c, int i, boolean b, ArrayList<Movie> moviesInfo) {
	ArrayList<Object> selectedMovies = new ArrayList<>();

	for (Movie movie : moviesInfo) {
		if (c == '>' && b) {
			if (movie.getCriticRating() > i) {
				selectedMovies.add(movie.getName());
			}
		} else if (c == '>' && !b) {
			if (movie.getAudienceRating() > i) {
				selectedMovies.add(movie.getName());
			}
		} else if (c == '<' && b) {
			if (movie.getCriticRating() < i) {
				selectedMovies.add(movie.getName());
			}
		} else if (c == '<' && !b) {
			if (movie.getAudienceRating() < i) {
				selectedMovies.add(movie.getName());
			}
		} else if (c == '=' && b) {
			if (movie.getCriticRating() == i) {
				selectedMovies.add(movie.getName());
			}
		} else if (c == '=' && !b) {
			if (movie.getAudienceRating() == i) {
				selectedMovies.add(movie.getName());
			}
		}
	}

	return selectedMovies;
}
//	public Collection<Object> selectWhereMovieIs(String doubt, ArrayList<Actor> actorsInfo)
//	{
//		return null;
//	}
public Collection<Object> selectWhereMovieIs(String movieTitle, ArrayList<Actor> actorsInfo) {
	ArrayList<Object> actorsInMovie = new ArrayList<Object>();

	// iterate through all actors in the database
	for (Actor actor : actorsInfo) {
		// check if the actor appeared in the given movie
		if (actor.getMoviesCast().contains(movieTitle.toLowerCase())) {
			// if so, add the actor's name to the list of actors in the movie
			actorsInMovie.add(actor.getName());
		}
	}

	return actorsInMovie;
}
	public String selectWhereActorIs(String meryl_streep, int size, String s) {
		return meryl_streep;
	}

//	public Collection<Object> getCoActors(String meryl_streep, ArrayList<Actor> actorsInfo) {
//		return null;
//	}
public ArrayList<String> getCoActors(String actorName, ArrayList<Actor> actorsInfo) {
	ArrayList<String> coActors = new ArrayList<String>();
	for (Actor actor : actorsInfo) {
		if (actor.getName().equals(actorName)) {
			for (String movie : actor.getMoviesCast()) {
				for (Actor otherActor : actorsInfo) {
					if (!otherActor.getName().equals(actorName) && otherActor.getMoviesCast().contains(movie)) {
						coActors.add(otherActor.getName());
					}
				}
			}
		}
	}
	return coActors;
}

	private int getActorIndex(String actorName, ArrayList<Actor> actorsInfo) {
		for (int i = 0; i < actorsInfo.size(); i++) {
			if (actorsInfo.get(i).getName().equals(actorName)) {
				return i;
			}
		}
		return -1;
	}

	public static double [] getMean (ArrayList <Movie> moviesInfo)
	{
		double [] mean = new double [2];
		double sumCritic = 0;
		double sumAudience = 0;
		for (Movie movie : moviesInfo)
		{
			sumCritic += movie.getCriticRating();
			sumAudience += movie.getAudienceRating();
		}
		mean[0] = sumCritic / moviesInfo.size();
		mean[1] = sumAudience / moviesInfo.size();
		return mean;
	}
	
}
