
/**
 * Write a description of class MinMaxFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MatchAllFilter implements Filter
{
    private ArrayList<Filter> ftrs;
    
    public MatchAllFilter() { 
        ftrs = new ArrayList<Filter>();
    }

    public void addFilter(Filter f) {
	ftrs.add(f);
    }

    public boolean satisfies(QuakeEntry qe) {
	for (Filter f : ftrs) {
		if (!f.satisfies(qe)) {
			return false;
		}
	}
        return true; 
    }


    public String getName() {
	StringBuilder sb = new StringBuilder();
	for (Filter f : ftrs) {
		sb.append(f.getName());
	}
        return "These filters were used: "+sb.toString();
    }

}
