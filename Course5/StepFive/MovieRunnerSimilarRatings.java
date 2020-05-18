
/**
 * Write a description of SecondRatings here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;

public class MovieRunnerSimilarRatings {

    public void printAverageRatings() {
      FourthRatings sr = new FourthRatings();
      //System.out.println("Number of movies: "+sr.getMovieSize());
      RaterDatabase.initialize("ratings_short.csv");
      System.out.println("Number of raters: "+RaterDatabase.size());
      MovieDatabase.initialize("ratedmovies_short.csv");
      System.out.println("Number of movies: "+MovieDatabase.size());

      sr.getAverageRatings(1);
      //for (Rating r : bv) {
        //System.out.println(sr.getAverageRatings(3));
      //}
      //sr.getAverageRatingOneMovie();
    }

    public void printAverageRatingsByYearAfterAndGenre() {
      AllFilters af = new AllFilters();
      YearAfterFilter yaf = new YearAfterFilter(2000);
      GenreFilter gf = new GenreFilter("Romance");
      af.addFilter(yaf);
      af.addFilter(gf);
      FourthRatings sr = new FourthRatings();
      //System.out.println("Number of movies: "+sr.getMovieSize());
      RaterDatabase.initialize("ratings.csv");
      //System.out.println("Number of raters: "+RaterDatabase.size());
      MovieDatabase.initialize("ratedmoviesfull.csv");
      //System.out.println("Number of movies: "+MovieDatabase.size());

      sr.getAverageRatingsByFilter(10, af);
      //for (Rating r : bv) {
        //System.out.println(sr.getAverageRatings(3));
      //}
      //sr.getAverageRatingOneMovie();
    }

    public void printSimilarRatings() {
      //AllFilters af = new AllFilters();
      //YearAfterFilter yaf = new YearAfterFilter(1980);
      //GenreFilter gf = new GenreFilter("Romance");
      //af.addFilter(yaf);
      //af.addFilter(gf);
      FourthRatings sr = new FourthRatings();
      //System.out.println("Number of movies: "+sr.getMovieSize());
      RaterDatabase.initialize("ratings.csv");
      System.out.println("Number of raters: "+RaterDatabase.size());
      MovieDatabase.initialize("ratedmoviesfull.csv");
      System.out.println("Number of movies: "+MovieDatabase.size());

      ArrayList<Rating> ans = sr.getSimilarRatings("71", 20, 5);
      System.out.println(MovieDatabase.getTitle(ans.get(0).getItem()));
      //for (Rating r : bv) {
        //System.out.println(sr.getAverageRatings(3));
      //}
      //sr.getAverageRatingOneMovie();
    }

    public void printSimilarRatingsByGenre() {
      //AllFilters af = new AllFilters();
      //YearAfterFilter yaf = new YearAfterFilter(1980);
      GenreFilter gf = new GenreFilter("Mystery");
      //af.addFilter(yaf);
      //af.addFilter(gf);
      FourthRatings sr = new FourthRatings();
      //System.out.println("Number of movies: "+sr.getMovieSize());
      RaterDatabase.initialize("ratings.csv");
      System.out.println("Number of raters: "+RaterDatabase.size());
      MovieDatabase.initialize("ratedmoviesfull.csv");
      System.out.println("Number of movies: "+MovieDatabase.size());

      ArrayList<Rating> ans = sr.getSimilarRatingsByFilter("964", 20, 5, gf);
      System.out.println(MovieDatabase.getTitle(ans.get(0).getItem()));
      //for (Rating r : bv) {
        //System.out.println(sr.getAverageRatings(3));
      //}
      //sr.getAverageRatingOneMovie();
    }

    public void printSimilarRatingsByDirector() {
      //AllFilters af = new AllFilters();
      //YearAfterFilter yaf = new YearAfterFilter(1980);
      DirectorsFilter gf = new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh");
      //af.addFilter(yaf);
      //af.addFilter(gf);
      FourthRatings sr = new FourthRatings();
      //System.out.println("Number of movies: "+sr.getMovieSize());
      RaterDatabase.initialize("ratings.csv");
      System.out.println("Number of raters: "+RaterDatabase.size());
      MovieDatabase.initialize("ratedmoviesfull.csv");
      System.out.println("Number of movies: "+MovieDatabase.size());

      ArrayList<Rating> ans = sr.getSimilarRatingsByFilter("120", 10, 2, gf);
      System.out.println(MovieDatabase.getTitle(ans.get(0).getItem()));
      //for (Rating r : bv) {
        //System.out.println(sr.getAverageRatings(3));
      //}
      //sr.getAverageRatingOneMovie();
    }

    public void printSimilarRatingsByGenreAndMinutes() {
      AllFilters af = new AllFilters();
      GenreFilter gf = new GenreFilter("Drama");
      MinutesFilter mf = new MinutesFilter(80, 160);
      //GenreFilter gf = new GenreFilter("Action");
      af.addFilter(gf);
      af.addFilter(mf);
      FourthRatings sr = new FourthRatings();
      //System.out.println("Number of movies: "+sr.getMovieSize());
      RaterDatabase.initialize("ratings.csv");
      System.out.println("Number of raters: "+RaterDatabase.size());
      MovieDatabase.initialize("ratedmoviesfull.csv");
      System.out.println("Number of movies: "+MovieDatabase.size());

      ArrayList<Rating> ans = sr.getSimilarRatingsByFilter("168", 10, 3, af);
      System.out.println(MovieDatabase.getTitle(ans.get(0).getItem()));
      //for (Rating r : bv) {
        //System.out.println(sr.getAverageRatings(3));
      //}
      //sr.getAverageRatingOneMovie();
    }

    public void printSimilarRatingsByYearAfterAndMinutes() {
      AllFilters af = new AllFilters();
      YearAfterFilter yaf = new YearAfterFilter(1975);
      MinutesFilter mf = new MinutesFilter(70, 200);
      //GenreFilter gf = new GenreFilter("Action");
      af.addFilter(yaf);
      af.addFilter(mf);
      FourthRatings sr = new FourthRatings();
      //System.out.println("Number of movies: "+sr.getMovieSize());
      RaterDatabase.initialize("ratings.csv");
      System.out.println("Number of raters: "+RaterDatabase.size());
      MovieDatabase.initialize("ratedmoviesfull.csv");
      System.out.println("Number of movies: "+MovieDatabase.size());

      ArrayList<Rating> ans = sr.getSimilarRatingsByFilter("314", 10, 5, af);
      System.out.println(MovieDatabase.getTitle(ans.get(0).getItem()));
      //for (Rating r : bv) {
        //System.out.println(sr.getAverageRatings(3));
      //}
      //sr.getAverageRatingOneMovie();
    }

    public static void main (String[] args) {
        	MovieRunnerSimilarRatings pr = new MovieRunnerSimilarRatings();
        	pr.printAverageRatingsByYearAfterAndGenre();
    	}

}
