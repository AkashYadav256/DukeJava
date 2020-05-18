
/**
 * Write a description of SecondRatings here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;

public class ThirdRatings{
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
      ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
      ArrayList<Rating> ans = new ArrayList<Rating>();
      for (String m : movies) {
        Rating r = new Rating(m, Double.toString(getAverageByID(m, minimalRaters)));
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
        System.out.println(MovieDatabase.getTitle(r.getItem()) + " " + r.getValue());
      }
      System.out.println("Found "+sort.size()+" movies");
      return sort;
    }

    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriterion) {
      ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
      ArrayList<Rating> ans = new ArrayList<Rating>();
      for (String m : movies) {
        if (filterCriterion.satisfies(m)) {
          Rating r = new Rating(m, Double.toString(getAverageByID(m, minimalRaters)));
          ans.add(r);
        }
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
        System.out.println(MovieDatabase.getTitle(r.getItem()) + " " + r.getValue() + " " + MovieDatabase.getYear(r.getItem()));
        //System.out.println(MovieDatabase.getGenres(r.getItem()));
        System.out.println(MovieDatabase.getMinutes(r.getItem()));
        System.out.println(MovieDatabase.getDirector(r.getItem()));
      }
      System.out.println("Found "+sort.size()+" movies");
      return sort;
    }

    public void getAverageRatingOneMovie() {
      ThirdRatings sr = new ThirdRatings("data/ratings_short.csv");
      String title = "The Godfather";
      //String id = sr.getID(title);
      //double avg = sr.getAverageByID(id, 3);
      //System.out.println(avg);
    }

    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }

    public ThirdRatings(String ratingsfile) {
        // my constructor
        FirstRatings fr = new FirstRatings();
        myRaters = fr.loadRaters(ratingsfile);
    }

    public int getRaterSize() {
      return myRaters.size();
    }

}
