/**
 * This class keeps track of one rater and all their ratings.
 *
 * @author Miguel Hernandez
 * @version 0
 */

import java.util.*;

public interface Rater {
    public void addRating(String item, String rating);

    public boolean hasRating(String item);

    public String getID();

    public double getRating(String item);

    public int numRatings();

    public ArrayList<String> getItemsRated();
}
