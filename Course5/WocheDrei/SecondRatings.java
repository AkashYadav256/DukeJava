
/**
 * Write a description of SecondRatings here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings{
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;

    private double getAverageByID(String id, int minimalRaters) {
      int sum = 0;
  		double average = 0.0d;
  		ArrayList<Rater> ratings = new ArrayList<Rater>();
  		for(Rater rater : myRaters) {
  			if(rater.hasRating(id)) {
  				ratings.add(rater);
  			}
  		}
  		for(Rater rater : ratings) {
  			if(ratings.size() >= minimalRaters) {
  				sum += rater.getRating(id);
  				average = sum / (double) ratings.size();
  			}
  		}
  		return average;
	  }

    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
      ArrayList<Rating> ans = new ArrayList<Rating>();
      for (Movie m : myMovies) {
        Rating r = new Rating(m.getID(), Double.toString(getAverageByID(m.getID(), minimalRaters)));
        ans.add(r);
      }
      ArrayList<Rating> sort = new ArrayList<Rating>();
      for (Rating r : ans) {
        if (r.getValue() != 0.0){
          sort.add(r);
          //System.out.println(getTitle(r.getItem()));
          //System.out.println(getTitle(r.getItem()) + " " + r.getValue());
          //sort.add(r.getValue());
        }
      }
      Collections.sort(sort);
      for (Rating r : sort) {
        System.out.println(getTitle(r.getItem()) + " " + r.getValue());
      }
      return sort;
    }

    public String getID(String title) {
      for (Movie m : myMovies) {
        if (m.getTitle().equals(title)) {return m.getID();}
      }
      return "NO SUCH TITLE";
    }

    public void getAverageRatingOneMovie() {
      SecondRatings sr = new SecondRatings("data/ratedmovies_short.csv", "data/ratings_short.csv");
      String title = "The Godfather";
      String id = sr.getID(title);
      double avg = sr.getAverageByID(id, 3);
      System.out.println(avg);
    }

    public String getTitle(String id) {
      for (Movie m : myMovies) {
        if (m.getID().equals(id)){
          return m.getTitle();
        }
      }
      return "Film with this ID not found";
    }

    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }

    public SecondRatings(String moviefile, String ratingsfile) {
        // my constructor
        FirstRatings fr = new FirstRatings();
        myMovies = fr.loadMovies(moviefile);
        myRaters = fr.loadRaters(ratingsfile);
    }

    public int getMovieSize() {
      return myMovies.size();
    }

    public int getRaterSize() {
      return myRaters.size();
    }

}
