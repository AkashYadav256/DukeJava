
/**
 * Write a description of SecondRatings here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;

public class MovieRunnerWithFilters {

    public void printAverageRatings() {
      ThirdRatings sr = new ThirdRatings("data/ratings.csv");
      //System.out.println("Number of movies: "+sr.getMovieSize());
      System.out.println("Number of raters: "+sr.getRaterSize());
      MovieDatabase.initialize("ratedmoviesfull.csv");
      System.out.println("Number of movies: "+MovieDatabase.size());

      sr.getAverageRatings(35);
      //for (Rating r : bv) {
        //System.out.println(sr.getAverageRatings(3));
      //}
      //sr.getAverageRatingOneMovie();
    }

    public void printAverageRatingsByYear() {
      YearAfterFilter yaf = new YearAfterFilter(2000);
      ThirdRatings sr = new ThirdRatings("data/ratings.csv");
      //System.out.println("Number of movies: "+sr.getMovieSize());
      System.out.println("Number of raters: "+sr.getRaterSize());
      MovieDatabase.initialize("ratedmoviesfull.csv");
      System.out.println("Number of movies: "+MovieDatabase.size());

      sr.getAverageRatingsByFilter(20, yaf);
      //for (Rating r : bv) {
        //System.out.println(sr.getAverageRatings(3));
      //}
      //sr.getAverageRatingOneMovie();
    }

    public void printAverageRatingsByGenre() {
      GenreFilter yaf = new GenreFilter("Comedy");
      ThirdRatings sr = new ThirdRatings("data/ratings.csv");
      //System.out.println("Number of movies: "+sr.getMovieSize());
      System.out.println("Number of raters: "+sr.getRaterSize());
      MovieDatabase.initialize("ratedmoviesfull.csv");
      System.out.println("Number of movies: "+MovieDatabase.size());

      sr.getAverageRatingsByFilter(20, yaf);
      //for (Rating r : bv) {
        //System.out.println(sr.getAverageRatings(3));
      //}
      //sr.getAverageRatingOneMovie();
    }

    public void printAverageRatingsByMinutes() {
      MinutesFilter yaf = new MinutesFilter(105, 135);
      ThirdRatings sr = new ThirdRatings("data/ratings.csv");
      //System.out.println("Number of movies: "+sr.getMovieSize());
      System.out.println("Number of raters: "+sr.getRaterSize());
      MovieDatabase.initialize("ratedmoviesfull.csv");
      System.out.println("Number of movies: "+MovieDatabase.size());

      sr.getAverageRatingsByFilter(5, yaf);
      //for (Rating r : bv) {
        //System.out.println(sr.getAverageRatings(3));
      //}
      //sr.getAverageRatingOneMovie();
    }

    public void printAverageRatingsByDirectors() {
      DirectorsFilter yaf = new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack");
      ThirdRatings sr = new ThirdRatings("data/ratings.csv");
      //System.out.println("Number of movies: "+sr.getMovieSize());
      System.out.println("Number of raters: "+sr.getRaterSize());
      MovieDatabase.initialize("ratedmoviesfull.csv");
      System.out.println("Number of movies: "+MovieDatabase.size());

      sr.getAverageRatingsByFilter(4, yaf);
      //for (Rating r : bv) {
        //System.out.println(sr.getAverageRatings(3));
      //}
      //sr.getAverageRatingOneMovie();
    }

    public void printAverageRatingsByYearAfterAndGenre() {
      AllFilters af = new AllFilters();
      YearAfterFilter yaf = new YearAfterFilter(1990);
      GenreFilter gf = new GenreFilter("Drama");
      af.addFilter(yaf);
      af.addFilter(gf);
      ThirdRatings sr = new ThirdRatings("data/ratings.csv");
      //System.out.println("Number of movies: "+sr.getMovieSize());
      System.out.println("Number of raters: "+sr.getRaterSize());
      MovieDatabase.initialize("ratedmoviesfull.csv");
      System.out.println("Number of movies: "+MovieDatabase.size());

      sr.getAverageRatingsByFilter(8, af);
      //for (Rating r : bv) {
        //System.out.println(sr.getAverageRatings(3));
      //}
      //sr.getAverageRatingOneMovie();
    }

    public void printAverageRatingsByDirectorsAndMinutes() {
      AllFilters af = new AllFilters();
      MinutesFilter mf = new MinutesFilter(90, 180);
      DirectorsFilter df = new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack");
      af.addFilter(mf);
      af.addFilter(df);
      ThirdRatings sr = new ThirdRatings("data/ratings.csv");
      //System.out.println("Number of movies: "+sr.getMovieSize());
      System.out.println("Number of raters: "+sr.getRaterSize());
      MovieDatabase.initialize("ratedmoviesfull.csv");
      System.out.println("Number of movies: "+MovieDatabase.size());

      sr.getAverageRatingsByFilter(3, af);
      //for (Rating r : bv) {
        //System.out.println(sr.getAverageRatings(3));
      //}
      //sr.getAverageRatingOneMovie();
    }

    public static void main (String[] args) {
        	MovieRunnerWithFilters pr = new MovieRunnerWithFilters();
        	pr.printAverageRatingsByDirectorsAndMinutes();
    	}

}
