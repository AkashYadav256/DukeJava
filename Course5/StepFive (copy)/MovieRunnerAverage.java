
/**
 * Write a description of SecondRatings here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;

public class MovieRunnerAverage {

    public void printAverageRatings() {
      SecondRatings sr = new SecondRatings("data/ratedmovies_short.csv" ,"data/ratings_short.csv");
      System.out.println("Number of movies: "+sr.getMovieSize());
      System.out.println("Number of raters: "+sr.getRaterSize());
      sr.getAverageRatings(3);
      //for (Rating r : bv) {
        //System.out.println(sr.getAverageRatings(3));
      //}
      sr.getAverageRatingOneMovie();
    }

    public static void main (String[] args) {
        	MovieRunnerAverage pr = new MovieRunnerAverage();
        	pr.printAverageRatings();
    	}

}
