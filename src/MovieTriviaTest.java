import static org.junit.jupiter.api.Assertions.*;

import movies.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import file.MovieDB;

import java.util.ArrayList;

class MovieTriviaTest {

	// instance of movie trivia object to test
	MovieTrivia mt;
	// instance of movieDB object
	MovieDB movieDB;

	@BeforeEach
	void setUp() throws Exception {
		// initialize movie trivia object
		mt = new MovieTrivia();

		// set up movie trivia object
		mt.setUp("C:/Users/zes/Desktop/SDA Project/Movie-Trivia/src/file/moviedata.txt", "C:/Users/zes/Desktop/SDA Project/Movie-Trivia/src/file/movieratings.csv");

		// get instance of movieDB object from movie trivia object
		movieDB = mt.movieDB;
	}

	@Test
	void testSetUp() {
		assertEquals(6, movieDB.getActorsInfo().size(),
				"actorsInfo should contain 6 actors after reading moviedata.txt.");
		assertEquals(7, movieDB.getMoviesInfo().size(),
				"moviesInfo should contain 7 movies after reading movieratings.csv.");

		assertEquals("meryl streep", movieDB.getActorsInfo().get(0).getName(),
				"\"meryl streep\" should be the name of the first actor in actorsInfo.");
		assertEquals(3, movieDB.getActorsInfo().get(0).getMoviesCast().size(),
				"The first actor listed in actorsInfo should have 3 movies in their moviesCasted list.");
		assertEquals("doubt", movieDB.getActorsInfo().get(0).getMoviesCast().get(0),
				"\"doubt\" should be the name of the first movie in the moviesCasted list of the first actor listed in actorsInfo.");

		assertEquals("doubt", movieDB.getMoviesInfo().get(0).getName(),
				"\"doubt\" should be the name of the first movie in moviesInfo.");
		assertEquals(79, movieDB.getMoviesInfo().get(0).getCriticRating(),
				"The critics rating for the first movie in moviesInfo is incorrect.");
		assertEquals(78, movieDB.getMoviesInfo().get(0).getAudienceRating(),
				"The audience rating for the first movie in moviesInfo is incorrect.");
	}

	@Test
	void testInsertActor() {

		// try to insert new actor with new movies
		mt.insertActor("test1", new String[] { "testmovie1", "testmovie2" }, movieDB.getActorsInfo());
		assertEquals(7, movieDB.getActorsInfo().size(),
				"After inserting an actor, the size of actorsInfo should have increased by 1.");
		assertEquals("test1", movieDB.getActorsInfo().get(movieDB.getActorsInfo().size() - 1).getName(),
				"After inserting actor \"test1\", the name of the last actor in actorsInfo should be \"test1\".");
		assertEquals(2, movieDB.getActorsInfo().get(movieDB.getActorsInfo().size() - 1).getMoviesCast().size(),
				"Actor \"test1\" should have 2 movies in their moviesCasted list.");
		assertEquals("testmovie1",
				movieDB.getActorsInfo().get(movieDB.getActorsInfo().size() - 1).getMoviesCast().get(0),
				"\"testmovie1\" should be the first movie in test1's moviesCasted list.");

		// try to insert existing actor with new movies
		mt.insertActor("   Meryl STReep      ", new String[] { "   DOUBT      ", "     Something New     " },
				movieDB.getActorsInfo());
		assertEquals(7, movieDB.getActorsInfo().size(),
				"Since \"meryl streep\" is already in actorsInfo, inserting \"   Meryl STReep      \" again should not increase the size of actorsInfo.");
		assertEquals(4, mt.selectWhereActorIs("meryl streep", movieDB.getActorsInfo()).size(),
				"After inserting Meryl Streep again with 2 movies, only one of which is not on the list yet, the number of movies \"meryl streep\" appeared in should be 4.");
		assertTrue(mt.selectWhereActorIs("meryl streep", movieDB.getActorsInfo()).contains("something new"),
				"After inserting Meryl Streep again with a new Movie \"     Something New     \", \"somenthing new\" should appear as one of the movies she has appeared in.");

		// TODO add additional test case scenarios

		mt.insertActor("test2", new String[] {}, movieDB.getActorsInfo());
		assertEquals(8, movieDB.getActorsInfo().size(), "After inserting an actor with no movies, the size of actorsInfo should have increased by 1.");
		assertEquals("test2", movieDB.getActorsInfo().get(movieDB.getActorsInfo().size() - 1).getName(), "After inserting actor \"test2\", the name of the last actor in actorsInfo should be \"test2\".");
		assertEquals(0, movieDB.getActorsInfo().get(movieDB.getActorsInfo().size() - 1).getMoviesCast().size(), "Actor \"test2\" should have 0 movies in their moviesCasted list.");

		mt.insertActor("test3", new String[] {"The Post"}, movieDB.getActorsInfo());
		assertEquals(9, movieDB.getActorsInfo().size(), "After inserting an actor with an existing movie, the size of actorsInfo should have increased by 1.");
		assertEquals("test3", movieDB.getActorsInfo().get(movieDB.getActorsInfo().size() - 1).getName(), "After inserting actor \"test3\", the name of the last actor in actorsInfo should be \"test3\".");
		assertEquals(1, movieDB.getActorsInfo().get(movieDB.getActorsInfo().size() - 1).getMoviesCast().size(), "Actor \"test3\" should have 1 movie in their moviesCasted list.");
		assertTrue(movieDB.getActorsInfo().get(movieDB.getActorsInfo().size() - 1).getMoviesCast().contains("The Post"), "Actor \"test3\" should have \"The Post\" in their moviesCasted list.");

		mt.insertActor("test4", new String[] {"The Godfather", "The Shawshank Redemption"}, movieDB.getActorsInfo());
		assertEquals(10, movieDB.getActorsInfo().size(), "After inserting an actor with multiple new movies, the size of actorsInfo should have increased by 1.");
		assertEquals("test4", movieDB.getActorsInfo().get(movieDB.getActorsInfo().size() - 1).getName(), "After inserting actor \"test4\", the name of the last actor in actorsInfo should be \"test4\".");
		assertEquals(2, movieDB.getActorsInfo().get(movieDB.getActorsInfo().size() - 1).getMoviesCast().size(), "Actor \"test4\" should have 2 movies in their moviesCasted list.");
		assertTrue(movieDB.getActorsInfo().get(movieDB.getActorsInfo().size() - 1).getMoviesCast().contains("The Godfather"), "Actor \"test4\" should have \"The Godfather\" in their moviesCasted list.");
		assertTrue(movieDB.getActorsInfo().get(movieDB.getActorsInfo().size() - 1).getMoviesCast().contains("The Shawshank Redemption"), "Actor \"test4\" should have \"The Shawshank Redemption\" in their moviesCasted list.");

		mt.insertActor("Tom Hanks", new String[] {"The Post", "Cast Away"}, movieDB.getActorsInfo());
		assertEquals(10, movieDB.getActorsInfo().size(), "Since \"Tom Hanks\" is already in actorsInfo, inserting him again should not increase the size of actorsInfo.");
		assertEquals(3, mt.selectWhereActorIs("Tom Hanks", movieDB.getActorsInfo()).size(), "After trying to insert Tom Hanks with three movies he already appears in, the number of movies \"Tom Hanks\" appeared in should still be 3.");



	}

	@Test
	void testInsertRating() {

		// try to insert new ratings for new movie
		mt.insertRating("testmovie", new int[] { 79, 80 }, movieDB.getMoviesInfo());
		assertEquals(8, movieDB.getMoviesInfo().size(),
				"After inserting ratings for a movie that is not in moviesInfo yet, the size of moviesInfo should increase by 1.");
		assertEquals("testmovie", movieDB.getMoviesInfo().get(movieDB.getMoviesInfo().size() - 1).getName(),
				"After inserting a rating for \"testmovie\", the name of the last movie in moviessInfo should be \"testmovie\".");
		assertEquals(79, movieDB.getMoviesInfo().get(movieDB.getMoviesInfo().size() - 1).getCriticRating(),
				"The critics rating for \"testmovie\" is incorrect.");
		assertEquals(80, movieDB.getMoviesInfo().get(movieDB.getMoviesInfo().size() - 1).getAudienceRating(),
				"The audience rating for \"testmovie\" is incorrect.");

		// try to insert new ratings for existing movie
		mt.insertRating("doubt", new int[] { 100, 100 }, movieDB.getMoviesInfo());
		assertEquals(8, movieDB.getMoviesInfo().size(),
				"Since \"doubt\" is already in moviesInfo, inserting ratings for it should not increase the size of moviesInfo.");

		assertEquals(1, mt.selectWhereRatingIs('>', 99, true, movieDB.getMoviesInfo()).size(),
				"After inserting a critic rating of 100 for \"doubt\", there should be 1 movie in moviesInfo with a critic rating greater than 99.");
		assertTrue(mt.selectWhereRatingIs('>', 99, true, movieDB.getMoviesInfo()).contains("doubt"),
				"After inserting the rating for \"doubt\", \"doubt\" should appear as a movie with critic rating greater than 99.");

		// TODO add additional test case scenarios

		mt.insertRating("The Post", new int[] { 80, 90 }, movieDB.getMoviesInfo());
		assertEquals(9, movieDB.getMoviesInfo().size(),
				"Since \"The Post\" is already in moviesInfo, inserting ratings for it should not increase the size of moviesInfo.");
		assertEquals(29, movieDB.getMoviesInfo().get(4).getCriticRating(),
				"The critics rating for \"The Post\" is incorrect.");
		assertEquals(29, movieDB.getMoviesInfo().get(4).getAudienceRating(),
				"The audience rating for \"The Post\" is incorrect.");

		mt.insertRating("testmovie", new int[] { 50, 60 }, movieDB.getMoviesInfo());
		assertEquals(9, movieDB.getMoviesInfo().size(),
				"Since \"testmovie\" is already in moviesInfo, inserting ratings for it should not increase the size of moviesInfo.");
		assertEquals(50, movieDB.getMoviesInfo().get(7).getCriticRating(),
				"The critics rating for \"testmovie\" is incorrect.");
		assertEquals(60, movieDB.getMoviesInfo().get(7).getAudienceRating(),
				"The audience rating for \"testmovie\" is incorrect.");

		mt.insertRating("testmovie2", new int[] { 50, 60 }, movieDB.getMoviesInfo());
		assertEquals(10, movieDB.getMoviesInfo().size(),
				"After inserting ratings for a movie that is not in moviesInfo yet, the size of moviesInfo should increase by 1.");
		assertEquals("testmovie2", movieDB.getMoviesInfo().get(movieDB.getMoviesInfo().size() - 1).getName(),
				"After inserting a rating for \"testmovie2\", the name of the last movie in moviessInfo should be \"testmovie2\".");

	}

	@Test
	void testSelectWhereActorIs() {
		assertEquals(3, mt.selectWhereActorIs("meryl streep", movieDB.getActorsInfo()).size(),
				"The number of movies \"meryl streep\" has appeared in should be 3.");
		assertEquals(3, mt.selectWhereActorIs("meryl streep", movieDB.getActorsInfo()).size(),
				"The number of movies \"meryl streep\" has appeared in should be 3.");
		assertEquals(0, mt.selectWhereActorIs("John Doe", movieDB.getActorsInfo()).size(),
				"The number of movies \"John Doe\" has appeared in should be 0.");
		assertEquals("doubt", mt.selectWhereActorIs("Meryl Streep", movieDB.getActorsInfo()).iterator().next(),
				"\"doubt\" should show up as first in the list of movies \"Meryl Streep\" has appeared in.");

		// TODO add additional test case scenarios

		assertEquals(0, mt.selectWhereActorIs("john doe", movieDB.getActorsInfo()).size(),
				"The number of movies \"Meryl Streep\" has appeared in should be 0.");
		assertEquals(4, mt.selectWhereActorIs("AMY ADAMS", movieDB.getActorsInfo()).size(),
				"The number of movies \"amy adams\" has appeared in should be 4.");
		assertEquals(3, mt.selectWhereActorIs("Tom Hanks", movieDB.getActorsInfo()).size(),
				"The number of movies \"Tom Hanks\" has appeared in should be 3.");
		assertEquals(4, mt.selectWhereActorIs("amy adams", movieDB.getActorsInfo()).size(),
				"The number of movies \"amy adams\" has appeared in should be 4.");
		assertEquals(3, mt.selectWhereActorIs("Tom Hanks", movieDB.getActorsInfo()).size(),
				"The number of movies \"Tom Hanks\" has appeared in should be 3.");




	}

	@Test
	void testSelectWhereMovieIs() {
		assertEquals(2, mt.selectWhereMovieIs("doubt", movieDB.getActorsInfo()).size(),
				"There should be 2 actors in \"doubt\".");
		assertEquals(true, mt.selectWhereMovieIs("doubt", movieDB.getActorsInfo()).contains("meryl streep"),
				"\"meryl streep\" should be an actor who appeared in \"doubt\".");
		assertEquals(true, mt.selectWhereMovieIs("doubt", movieDB.getActorsInfo()).contains("amy adams"),
				"\"amy adams\" should be an actor who appeared in \"doubt\".");

		// TODO add additional test case scenarios
		assertEquals(0, mt.selectWhereMovieIs("the devil wears prada", movieDB.getActorsInfo()).size(),
				"There should be 0 actors in \"the devil wears prada\".");
		assertFalse(mt.selectWhereMovieIs("the devil wears prada", movieDB.getActorsInfo()).contains("meryl streep"),
				"\"meryl streep\" should be an actor who appeared in \"the devil wears prada\".");
		assertEquals(2, mt.selectWhereMovieIs("Doubt", movieDB.getActorsInfo()).size(),
				"There should be 2 actors in \"Doubt\".");
		assertEquals(1, mt.selectWhereMovieIs("Catch Me If You Can", movieDB.getActorsInfo()).size(),
				"There should be 1 actor in \"Catch Me If You Can\".");





	}

	@Test
	void testSelectWhereRatingIs() {
		assertEquals(6, mt.selectWhereRatingIs('>', 0, true, movieDB.getMoviesInfo()).size(),
				"There should be 6 movies where critics rating is greater than 0.");
		assertEquals(0, mt.selectWhereRatingIs('=', 65, false, movieDB.getMoviesInfo()).size(),
				"There should be no movie where audience rating is equal to 65.");
		assertEquals(2, mt.selectWhereRatingIs('<', 30, true, movieDB.getMoviesInfo()).size(),
				"There should be 2 movies where critics rating is less than 30.");

		// TODO add additional test case scenarios
		assertEquals(0, mt.selectWhereRatingIs('>', 99, true, movieDB.getMoviesInfo()).size(),
				"There should be 1 movie where critics rating is greater than 99.");
		assertEquals(0, mt.selectWhereRatingIs('>', 99, false, movieDB.getMoviesInfo()).size(),
				"There should be 1 movie where audience rating is greater than 99.");
		assertEquals(0, mt.selectWhereRatingIs('=', 100, true, movieDB.getMoviesInfo()).size(),
				"There should be 1 movie where critics rating is equal to 100.");
		assertEquals(3, mt.selectWhereRatingIs('>', 90, true, movieDB.getMoviesInfo()).size(),
				"There should be 1 movie where critics rating is greater than 90.");

	}


@Test
void testGetCoActors() {
	assertEquals(2, mt.getCoActors("meryl streep", movieDB.getActorsInfo()).size(),
			"\"meryl streep\" should have 2 co-actors.");
	assertTrue(mt.getCoActors("meryl streep", movieDB.getActorsInfo()).contains("tom hanks"),
			"\"tom hanks\" was a co-actor of \"meryl streep\".");
	assertTrue(mt.getCoActors("meryl streep", movieDB.getActorsInfo()).contains("amy adams"),
			"\"amy adams\" was a co-actor of \"meryl streep\".");

	// TODO add additional test case scenarios

	assertEquals(1, mt.getCoActors("tom hanks", movieDB.getActorsInfo()).size(),
			"\"tom hanks\" should have 1 co-actor.");
	assertTrue(mt.getCoActors("tom hanks", movieDB.getActorsInfo()).contains("meryl streep"),
			"\"meryl streep\" was a co-actor of \"tom hanks\".");
	assertEquals(0, mt.getCoActors("brad pitt", movieDB.getActorsInfo()).size(),
			"\"brad pitt\" should have 2 co-actors.");
	assertFalse(mt.getCoActors("brad pitt", movieDB.getActorsInfo()).contains("morgan freeman"),
			"\"morgan freeman\" was not a co-actor of \"brad pitt\".");
	assertFalse(mt.getCoActors("brad pitt", movieDB.getActorsInfo()).contains("edward norton"),
			"\"edward norton\" was not a co-actor of \"brad pitt\".");

}

	@Test
	void testGetCommonMovie() {
		assertEquals(1, mt.getCommonMovie("meryl streep", "tom hanks", movieDB.getActorsInfo()).size(),
				"\"tom hanks\" and \"meryl streep\" should have 1 movie in common.");
		assertTrue(mt.getCommonMovie("meryl streep", "tom hanks", movieDB.getActorsInfo()).contains("the post"),
				"\"the post\" should be a common movie between \"tom hanks\" and \"meryl streep\".");

		// TODO add additional test case scenarios

		assertEquals(0, mt.getCommonMovie("meryl streep", "john doe", movieDB.getActorsInfo()).size(),
				"\"john doe\" and \"meryl streep\" should have 0 movies in common.");
		assertEquals(0, mt.getCommonMovie("robin williams", "brad pitt", movieDB.getActorsInfo()).size(),
				"\"robin williams\" and \"brad pitt\" should have 0 movies in common.");
		assertEquals(0, mt.getCommonMovie("brandon krakowsky", "meryl streep", movieDB.getActorsInfo()).size(),
				"\"brandon krakowsky\" has not acted in any movies, so there should be 0 movies in common with \"meryl streep\".");
		assertFalse(mt.getCommonMovie("meryl streep", "amy adams", movieDB.getActorsInfo()).contains("man of steel"),
				"\"man of steel\" should not be a common movie between \"meryl streep\" and \"amy adams\".");


	}

	@Test
	void testGoodMovies() {
		assertEquals(3, mt.goodMovies(movieDB.getMoviesInfo()).size(),
				"There should be 3 movies that are considered good movies, movies with both critics and audience rating that are greater than or equal to 85.");
		assertTrue(mt.goodMovies(movieDB.getMoviesInfo()).contains("jaws"),
				"\"jaws\" should be considered a good movie, since it's critics and audience ratings are both greater than or equal to 85.");

		// TODO add additional test case scenarios
		assertEquals(3, mt.goodMovies(movieDB.getMoviesInfo()).size(),
				"There should be 3 movies that are considered good movies, movies with both critics and audience rating that are greater than or equal to 85.");
		assertTrue(mt.goodMovies(movieDB.getMoviesInfo()).contains("jaws"),
				"\"jaws\" should be considered a good movie, since it's critics and audience ratings are both greater than or equal to 85.");
		assertFalse(mt.goodMovies(movieDB.getMoviesInfo()).contains("seven"),
				"\"seven\" should not be considered a good movie, since it's critics and audience ratings are both less than 85.");
		assertFalse(mt.goodMovies(movieDB.getMoviesInfo()).contains("popeye"),
				"\"popeye\" should not be considered a good movie, since it's critics and audience ratings are both equal to 0.");


	}

	@Test
	void testGetCommonActors() {
		assertEquals(1, mt.getCommonActors("doubt", "the post", movieDB.getActorsInfo()).size(),
				"There should be one actor that appeared in both \"doubt\" and \"the post\".");
		assertTrue(mt.getCommonActors("doubt", "the post", movieDB.getActorsInfo()).contains("meryl streep"),
				"The actor that appeared in both \"doubt\" and \"the post\" should be \"meryl streep\".");

		// TODO add additional test case scenarios
		assertEquals(1, mt.getCommonActors("seven", "fight club", movieDB.getActorsInfo()).size(),
				"There should be one actors that appeared in both \"seven\" and \"fight club\".");
		assertEquals(1, mt.getCommonActors("doubt", "arrival", movieDB.getActorsInfo()).size(),
				"There should be one actors that appeared in both \"doubt\" and \"arrival\".");
		assertFalse(mt.getCommonActors("doubt", "arrival", movieDB.getActorsInfo()).contains("meryl streep"),
				"The actor that appeared in both \"doubt\" and \"arrival\" should be \"meryl streep\".");
		assertTrue(mt.getCommonActors("doubt", "arrival", movieDB.getActorsInfo()).contains("amy adams"),
				"The actor that appeared in both \"doubt\" and \"arrival\" should be \"amy adams\".");

	}





	@Test
	void testGetMean() {

		// Test with a list of movies with equal critic and audience ratings
		ArrayList<Movie> equalRatings = new ArrayList<>();
		equalRatings.add(new Movie("Movie1", 5, 5));
		equalRatings.add(new Movie("Movie2", 5, 5));
		equalRatings.add(new Movie("Movie3", 5, 5));
		double[] equalMean = mt.getMean(equalRatings);
		assertEquals(5.0, equalMean[0]);
		assertEquals(5.0, equalMean[1]);

		// Test with a list of movies with different critic and audience ratings
		ArrayList<Movie> differentRatings = new ArrayList<>();
		differentRatings.add(new Movie("Movie1", 3, 5));
		differentRatings.add(new Movie("Movie2", 4, 2));
		differentRatings.add(new Movie("Movie3", 2, 3));
		double[] differentMean = mt.getMean(differentRatings);
		assertEquals(3.0, differentMean[0]);
		assertEquals(3.33, differentMean[1], 0.01); // Using delta to account for rounding errors

		ArrayList<Movie> moviesInfo = movieDB.getMoviesInfo();
		double[] expected = {67.85714285714286, 65.71428571428571}; // expected mean values for critic and audience ratings
		double[] actual = mt.getMean(moviesInfo);
		assertEquals(expected[0], actual[0], 0.001, "Mean critic rating is incorrect.");
		assertEquals(expected[1], actual[1], 0.001, "Mean audience rating is incorrect.");



	}


//	@Test
//	void testGetMean() {
////		fail(); // This automatically causes the test to fail. Remove this line when you are ready to write your own tests.
//
//
//	}
}
