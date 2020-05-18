/**
 * This class keeps track of one rater and all their ratings.
 *
 * @author Aakash Yadav
 * @version 0
 */

import java.util.*;

public class EfficientRater implements Rater {
    private String myID; // a unique String ID for this rater
    private HashMap<String, Rating> myRatings; // an ArrayList of Ratings

    public EfficientRater(String id) {
        myID = id;
        myRatings = new HashMap<String, Rating>();
    }

    public void addRating(String item, double rating) {
        myRatings.put(item, new Rating(item, rating));
    }

    public boolean hasRating(String item) {
      if (myRatings.containsKey(item)){
                return true;
      }
      return false;
    }

    public String getID() {
        return myID;
    }

    public double getRating(String item) {
        if (myRatings.containsKey(item)){
            return myRatings.get(item).getValue();
        }

        return -1;
    }

    public int numRatings() {
        return myRatings.size();
    }

    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<String>();
        for(String s : myRatings.keySet()){
            list.add(myRatings.get(s).getItem());
        }

        return list;
    }
}
