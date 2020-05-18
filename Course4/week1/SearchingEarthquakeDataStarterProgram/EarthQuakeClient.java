import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
	for (QuakeEntry qe : quakeData) {
		if (qe.getMagnitude()>magMin) {
			answer.add(qe);
		}
	}

        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
	for (QuakeEntry qe : quakeData) {
		if (qe.getLocation().distanceTo(from)<distMax) {
			answer.add(qe);
		}
	}

        return answer;
    }

    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData,
    double minDepth,
    double maxDepth) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
	for (QuakeEntry qe : quakeData) {
		if (qe.getDepth()<maxDepth && qe.getDepth()>minDepth) {
			answer.add(qe);
		}
	}

        return answer;
    }

    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData,
    String where,
    String phrase) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
	for (QuakeEntry qe : quakeData) {
		if (qe.getInfo().indexOf(phrase)==0 && where.equals("start")) {
			answer.add(qe);
		}
		else if (qe.getInfo().indexOf(phrase)+phrase.length()==qe.getInfo().length() && where.equals("end")) {
			answer.add(qe);
		}
		else if (qe.getInfo().contains(phrase) && where.equals("any")) {
			answer.add(qe);
		}
	}

        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
	ArrayList<QuakeEntry> biggy = filterByMagnitude(list, 5.0);
	for (QuakeEntry qe : biggy) {
		System.out.println(qe);
	}
	System.out.println("Found Earthquakes: "+biggy.size());
    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        //Location city = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
         Location city =  new Location(38.17, -118.82);

        // TODO

	ArrayList<QuakeEntry> close = filterByDistanceFrom(list, 1000000, city);
	for (QuakeEntry qe : close) {
		System.out.println("Distance: "+qe.getLocation().distanceTo(city));
		System.out.println("Information about city: "+qe.getInfo());
	}
	System.out.println("Found Earthquakes: "+close.size());
    }

    public void quakesOfDepth() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
	ArrayList<QuakeEntry> biggy = filterByDepth(list, -4000, -2000);
	for (QuakeEntry qe : biggy) {
		System.out.println(qe);
	}
	System.out.println("Found Earthquakes: "+biggy.size());
    }

    public void quakesByPhrase() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
	ArrayList<QuakeEntry> biggy = filterByPhrase(list, "any", "Can");
	for (QuakeEntry qe : biggy) {
		System.out.println(qe);
	}
	System.out.println("Found Earthquakes: "+biggy.size());
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }

    public static void main (String[] args) {
        EarthQuakeClient pr = new EarthQuakeClient();
        pr.quakesByPhrase();
    }
    
}
