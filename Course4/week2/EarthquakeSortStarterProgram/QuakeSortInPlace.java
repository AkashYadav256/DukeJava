
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }

    public int getLargestDepth(ArrayList<QuakeEntry> quakes, int from) {
        int maxIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getDepth() > quakes.get(maxIdx).getDepth()) {
                maxIdx = i;
            }
        }
        return maxIdx;
    }

    public void onePassBubbleSort(ArrayList<QuakeEntry> in, int nSorted) {
	for (int i=0; i<in.size()-nSorted-1; i++) {
        	QuakeEntry qi = in.get(i);
        	QuakeEntry qmin = in.get(i+1);
		if (qi.getMagnitude()>qmin.getMagnitude()) {
			in.set(i,qmin);
			in.set(i+1,qi);
		}
        }
    }

    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in) {
	for (int i=0; i< in.size()-1; i++) {
		onePassBubbleSort(in, i);
	}
    }

    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in) {
	int i;
	for (i=0; i< in.size()-1; i++) {
		if (!checkInSortedOrder(in)) {
			onePassBubbleSort(in, i);
		}
		else {
			System.out.println("Done in "+i+" passes.");
			break;
		}
	}
	System.out.println("Done in "+i+" passes.");
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }

    public void sortByLargestDepth(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int maxIdx = getLargestDepth(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(maxIdx);
            in.set(i,qmin);
            in.set(maxIdx,qi);
        }
        
    }

    public boolean checkInSortedOrder(ArrayList<QuakeEntry> in) {
	for (int i=0; i<in.size()-1; i++) {
        	QuakeEntry qi = in.get(i);
        	QuakeEntry qmin = in.get(i+1);
		if (qi.getMagnitude()>qmin.getMagnitude()) {
			return false;
		}
        }
	return true;
    }

    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in) {
       int i;
       for (i=0; i< in.size(); i++) {
	    if (!checkInSortedOrder(in)) {
		    int minIdx = getSmallestMagnitude(in,i);
		    QuakeEntry qi = in.get(i);
		    QuakeEntry qmin = in.get(minIdx);
		    in.set(i,qmin);
		    in.set(minIdx,qi);
	    }
	    else {
		    System.out.println("Done in "+i+" passes.");
		    break;
	    }
        }
        System.out.println("Done in "+i+" passes.");
    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/earthQuakeDataWeekDec6sample2.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");    
        sortByMagnitudeWithBubbleSortWithCheck(list);
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        } 
        
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
		System.out.println("Latitude,Longitude,Magnitude,Info");
		for(QuakeEntry qe : list){
			System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
			                  qe.getLocation().getLatitude(),
			                  qe.getLocation().getLongitude(),
			                  qe.getMagnitude(),
			                  qe.getInfo());
	    }
		
	}

    public static void main (String[] args) {
        QuakeSortInPlace pr = new QuakeSortInPlace();
        pr.testSort();
    }
}
