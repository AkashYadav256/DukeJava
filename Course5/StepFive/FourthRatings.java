
/**
 * Write a description of SecondRatings here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;

public class FourthRatings{

    private double getAverageByID(String id, int minimalRaters) {
      int sum = 0;
  		double average = 0.0d;
  		ArrayList<Rater> ratings = new ArrayList<Rater>();
      ArrayList<Rater> raters = RaterDatabase.getRaters();
  		for(Rater rater : raters) {
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
        Rating r = new Rating(m, getAverageByID(m, minimalRaters));
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
          Rating r = new Rating(m, getAverageByID(m, minimalRaters));
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
      // for (Rating r : sort) {
      //   System.out.println(MovieDatabase.getTitle(r.getItem()) + " " + r.getValue() + " " + MovieDatabase.getYear(r.getItem()));
      //   System.out.println(MovieDatabase.getGenres(r.getItem()));
      //   System.out.println(MovieDatabase.getMinutes(r.getItem()));
      //   System.out.println(MovieDatabase.getDirector(r.getItem()));
      // }
      // System.out.println("Found "+sort.size()+" movies");
      return sort;
    }

    private Rating dotProduct(Rater me, Rater r) {
      ArrayList<String> myRatings = me.getItemsRated();
      ArrayList<String> hisRatings = r.getItemsRated();
      ArrayList<String> common = new ArrayList<String>();
      for (String s : myRatings) {
        if (hisRatings.contains(s)) {
          common.add(s);
        }
      }
      double x = 0.0;
      for (String s : common) {
        x += (me.getRating(s)-5)*(r.getRating(s)-5);
      }
      Rating ans = new Rating(r.getID(), x);
      return ans;
    }

    private ArrayList<Rating> getSimilarities(String id) {
      ArrayList<Rating> list = new ArrayList<Rating>();
      Rater me = RaterDatabase.getRater(id);
      for (Rater r : RaterDatabase.getRaters()) {
        Rating x = dotProduct(me, r);
        if (!r.equals(me) && x.getValue()>0) {
          list.add(x);
        }
      }
      Collections.sort(list, Collections.reverseOrder());
      return list;
    }

    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters) {
		ArrayList<Rating> list = new ArrayList<Rating>();
		ArrayList<Rating> similarRatings = getSimilarities(id);
		ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
		for(String movieID : movies) {
			double weightedAverage = 0.0;
			double sum = 0.0;
			int ratersCount = 0;
			for(int i = 0; i < numSimilarRaters; i++) {
				Rating rating = similarRatings.get(i);
				String raterID  = rating.getItem();
				double value = rating.getValue();
				Rater myRater = RaterDatabase.getRater(raterID);
				if(myRater.hasRating(movieID)) {
					ratersCount++;
					sum += value * myRater.getRating(movieID);
				}
			}
			if(ratersCount >= minimalRaters) {
				weightedAverage = sum / (double) ratersCount;
				list.add(new Rating(movieID, weightedAverage));
			}
		}
		Collections.sort(list, Collections.reverseOrder());
		return list;
//		return getSimilarRatingsByFilter(id, numSimilarRaters, minimalRaters, new TrueFilter());
	}

    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter filterCriterion) {
      ArrayList<Rating> list = new ArrayList<Rating>();
  		ArrayList<Rating> similarRatings = getSimilarities(id);
  		ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
  		for(String movieID : movies) {
        if (filterCriterion.satisfies(movieID)) {
  			double weightedAverage = 0.0;
  			double sum = 0.0;
  			int ratersCount = 0;
  			for(int i = 0; i < numSimilarRaters; i++) {
  				Rating rating = similarRatings.get(i);
  				String raterID  = rating.getItem();
  				double value = rating.getValue();
  				Rater myRater = RaterDatabase.getRater(raterID);
  				if(myRater.hasRating(movieID)) {
  					ratersCount++;
  					sum += value * myRater.getRating(movieID);
  				}
  			}
  			if(ratersCount >= minimalRaters) {
  				weightedAverage = sum / (double) ratersCount;
  				list.add(new Rating(movieID, weightedAverage));
  			}
  		}}
  		Collections.sort(list, Collections.reverseOrder());
  		return list;
    }


    /*public FourthRatings() {
        // default constructor
        this("ratings.csv");
    }

    public FourthRatings(String ratingsfile) {
        // my constructor
        FirstRatings fr = new FirstRatings();
        myRaters = fr.loadRaters(ratingsfile);
    }

    public int getRaterSize() {
      return myRaters.size();
    }*/

}
