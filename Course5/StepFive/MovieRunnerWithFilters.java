
/**
 * Write a description of SecondRatings here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;

public class MovieRunnerWithFilters {

    public void printAverageRatings() {
      ThirdRatings sr = new ThirdRatings("data/ratings_short.csv");
      //System.out.println("Number of movies: "+sr.getMovieSize());
      System.out.println("Number of raters: "+sr.getRaterSize());
      MovieDatabase.initialize("ratedmovies_short.csv");
      System.out.println("Number of movies: "+MovieDatabase.size());

      sr.getAverageRatings(1);
      //for (Rating r : bv) {
        //System.out.println(sr.getAverageRatings(3));
      //}
      //sr.getAverageRatingOneMovie();
    }

    public void printAverageRatingsByYear() {
      YearAfterFilter yaf = new YearAfterFilter(2000);
      ThirdRatings sr = new ThirdRatings("data/ratings_short.csv");
      //System.out.println("Number of movies: "+sr.getMovieSize());
      System.out.println("Number of raters: "+sr.getRaterSize());
      MovieDatabase.initialize("ratedmovies_short.csv");
      System.out.println("Number of movies: "+MovieDatabase.size());

      sr.getAverageRatingsByFilter(1, yaf);
      //for (Rating r : bv) {
        //System.out.println(sr.getAverageRatings(3));
      //}
      //sr.getAverageRatingOneMovie();
    }

    public void printAverageRatingsByGenre() {
      GenreFilter yaf = new GenreFilter("Crime");
      ThirdRatings sr = new ThirdRatings("data/ratings_short.csv");
      //System.out.println("Number of movies: "+sr.getMovieSize());
      System.out.println("Number of raters: "+sr.getRaterSize());
      MovieDatabase.initialize("ratedmovies_short.csv");
      System.out.println("Number of movies: "+MovieDatabase.size());

      sr.getAverageRatingsByFilter(1, yaf);
      //for (Rating r : bv) {
        //System.out.println(sr.getAverageRatings(3));
      //}
      //sr.getAverageRatingOneMovie();
    }

    public void printAverageRatingsByMinutes() {
      MinutesFilter yaf = new MinutesFilter(110, 170);
      ThirdRatings sr = new ThirdRatings("data/ratings_short.csv");
      //System.out.println("Number of movies: "+sr.getMovieSize());
      System.out.println("Number of raters: "+sr.getRaterSize());
      MovieDatabase.initialize("ratedmovies_short.csv");
      System.out.println("Number of movies: "+MovieDatabase.size());

      sr.getAverageRatingsByFilter(1, yaf);
      //for (Rating r : bv) {
        //System.out.println(sr.getAverageRatings(3));
      //}
      //sr.getAverageRatingOneMovie();
    }

    public void printAverageRatingsByDirectors() {
      DirectorsFilter yaf = new DirectorsFilter("Charles Chaplin,Michael Mann,Spike Jonze");
      ThirdRatings sr = new ThirdRatings("data/ratings_short.csv");
      //System.out.println("Number of movies: "+sr.getMovieSize());
      System.out.println("Number of raters: "+sr.getRaterSize());
      MovieDatabase.initialize("ratedmovies_short.csv");
      System.out.println("Number of movies: "+MovieDatabase.size());

      sr.getAverageRatingsByFilter(1, yaf);
      //for (Rating r : bv) {
        //System.out.println(sr.getAverageRatings(3));
      //}
      //sr.getAverageRatingOneMovie();
    }

    public void printAverageRatingsByYearAfterAndGenre() {
      AllFilters af = new AllFilters();
      YearAfterFilter yaf = new YearAfterFilter(1980);
      GenreFilter gf = new GenreFilter("Romance");
      af.addFilter(yaf);
      af.addFilter(gf);
      ThirdRatings sr = new ThirdRatings("data/ratings_short.csv");
      //System.out.println("Number of movies: "+sr.getMovieSize());
      System.out.println("Number of raters: "+sr.getRaterSize());
      MovieDatabase.initialize("ratedmovies_short.csv");
      System.out.println("Number of movies: "+MovieDatabase.size());

      sr.getAverageRatingsByFilter(1, af);
      //for (Rating r : bv) {
        //System.out.println(sr.getAverageRatings(3));
      //}
      //sr.getAverageRatingOneMovie();
    }

    public void printAverageRatingsByDirectorsAndMinutes() {
      AllFilters af = new AllFilters();
      MinutesFilter mf = new MinutesFilter(30, 170);
      DirectorsFilter df = new DirectorsFilter("Spike Jonze,Michael Mann,Charles Chaplin,Francis Ford Coppola");
      af.addFilter(mf);
      af.addFilter(df);
      ThirdRatings sr = new ThirdRatings("data/ratings_short.csv");
      //System.out.println("Number of movies: "+sr.getMovieSize());
      System.out.println("Number of raters: "+sr.getRaterSize());
      MovieDatabase.initialize("ratedmovies_short.csv");
      System.out.println("Number of movies: "+MovieDatabase.size());

      sr.getAverageRatingsByFilter(1, af);
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
