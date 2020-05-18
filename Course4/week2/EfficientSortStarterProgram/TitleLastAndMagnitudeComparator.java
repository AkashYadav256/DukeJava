import java.util.*;
import java.lang.*;
import edu.duke.*;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry>{
	
	public int compare (QuakeEntry q1, QuakeEntry q2) {
		String t1 = q1.getInfo();
		String t2 = q2.getInfo();
		if (t1.substring(t1.lastIndexOf(" ")+1).compareTo(t2.substring(t2.lastIndexOf(" ")+1)) < 0) {
			return -1;
		}
		else if (t1.substring(t1.lastIndexOf(" ")+1).compareTo(t2.substring(t2.lastIndexOf(" ")+1)) > 0) {
			return 1;
		}
		else {
			if (q1.getMagnitude() < q2.getMagnitude()) {
				return -1;
			}
			else if (q1.getMagnitude() > q2.getMagnitude()) {
				return 1;
			}
			else {return 0;}
		}
	//return 0;
	}
	
}
