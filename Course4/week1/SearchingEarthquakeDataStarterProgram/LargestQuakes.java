
/**
 * Find N-closest quakes
 * 
 * @author Aakash Yadav
 * @version 5.0, May 2020 (during COVID 19 lockdown in India :'( 1:11AM )
 */

import java.util.*;

public class LargestQuakes {
	
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany) {
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
	ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        // TO DO
	for (int j=0; j<howMany; j++){
		int min = indexOfLargest(copy);
		ret.add(copy.get(min));
		copy.remove(min);
	}
        return ret;
    }

    public int indexOfLargest(ArrayList<QuakeEntry> copy) {
	int min = 0;
	double mag=0;
	for (int k=0; k<copy.size(); k++){
		QuakeEntry quake = copy.get(k);
		if (quake.getMagnitude()>copy.get(min).getMagnitude()){
			min = k;
			mag=quake.getMagnitude();
		}
	}
	return min;
    }

    public void findLargestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());

        Location jakarta  = new Location(-6.211,106.845);

        ArrayList<QuakeEntry> close = getLargest(list,50);
        
	for(int k=0; k < close.size(); k++){
            QuakeEntry entry = close.get(k);
            double distanceInMeters = jakarta.distanceTo(entry.getLocation());
            System.out.printf("%4.2f\t %s\n", distanceInMeters/1000,entry);
        }
        System.out.println("number found: "+close.size());
	
	//System.out.println(indexOfLargest(list));
    }

    public static void main (String[] args) {
        LargestQuakes pr = new LargestQuakes();
        pr.findLargestQuakes();
    }
    
}
