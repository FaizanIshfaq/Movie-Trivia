# Movie-Trivia
Summary:

The project is a Movie Trivia application implemented in Java. It manages a movie database by loading and parsing data from two files, namely moviedata.txt and movieratings.csv. The core classes include MovieDB, Actor, and Movie.
The MovieDB class is responsible for loading and parsing data from two files (moviedata.txt and movieratings.csv). It uses two ArrayLists (actorsInfo and moviesInfo) to store information about actors and movies, respectively. The class provides methods to retrieve lists of actors and movies.

The Actor class represents an actor with a name and a list of movies they have acted in. It includes constructors for creating actors with or without an initial list of movies, and it overrides the toString method to provide a string representation.

The Movie class represents a movie with a name, critic rating, and audience rating. It includes a constructor for initializing movies and provides getters and setters for accessing and modifying movie details. The class also overrides the toString method for a string representation.

The project includes a testing class (MovieDBTest) using JUnit, which ensures that the MovieDB class initializes and populates actor and movie information correctly.


1. Movie Database (MovieDB Class):
Responsibility: The MovieDB class serves as a manager for loading and parsing data related to movies and ratings from two different files.
Data Representation: It maintains two ArrayLists (actorsInfo and moviesInfo) to store information about actors and movies, respectively.
File Loading and Parsing:
The setUp method takes two file paths as parameters (movieData and movieRatings).
It reads and parses the movie data file (moviedata.txt), creating Actor objects and populating the actorsInfo ArrayList.
It reads and parses the movie ratings file (movieratings.csv), creating Movie objects and populating the moviesInfo ArrayList.
Data Access:
Provides methods (getActorsInfo and getMoviesInfo) to retrieve the lists of actors and movies.
2. Actor Class:
Responsibility: Represents an actor with a name and a list of movies the actor has acted in.
Fields:
name: Stores the name of the actor.
moviesCasted: ArrayList storing the names of movies the actor has acted in.
Constructors:
Two constructors allow creating an actor with just the name or with both the name and an initial list of movies.
Getters:
Provides getter methods (getName and getMoviesCast) to access the actor's name and the list of movies.
String Representation:
Overrides the toString method to provide a string representation of the actor, including the name and the list of movies.
3. Movie Class:
Responsibility: Represents a movie with a name, critic rating, and audience rating.
Fields:
name: Stores the name of the movie.
criticRating: Stores the critic rating of the movie.
audienceRating: Stores the audience rating of the movie.
Constructor:
Initializes a movie with a name, critic rating, and audience rating.
Getters and Setters:
Provides getter methods (getName, getCriticRating, and getAudienceRating) to access the movie's name, critic rating, and audience rating.
Provides setter methods (setCriticRating and setAudienceRating) to modify the critic and audience ratings.
String Representation:
Overrides the toString method to provide a string representation of the movie, including the name, critic rating, and audience rating.
4. MovieDBTest Class:
Responsibility: Contains JUnit tests to verify the functionality of the MovieDB class.
Test Initialization:
Creates an instance of MovieDB and sets it up with specific file paths.
Test Methods:
The testSetUp method checks if the setUp method of MovieDB correctly initializes and populates the actor and movie information.
Sample Data Files:
moviedata.txt: Contains data about actors and the movies they have acted in.
movieratings.csv: Contains data about movies, including critic and audience ratings.
