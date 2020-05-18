
/**
 * Write a description of class MinMaxFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DistanceFilter implements Filter
{
    private Location loc;
    private double disMax; 
    
    public DistanceFilter(Location locn, double max) { 
        loc = locn;
	disMax = max;
    } 

    public boolean satisfies(QuakeEntry qe) { 
        return (qe.getLocation().distanceTo(loc) <= disMax); 
    }

    public String getName() {
	return "DistanceFilter ";
    }

}
