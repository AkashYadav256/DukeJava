
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer x = new LogAnalyzer();
	x.readFile("weblog2_log");
	int c = x.countUniqueIPs();
	//x.printAll();
	//System.out.println("Unique IPs: "+c);
	//x.printAllHigherThanNum(400);
	//System.out.println(x.uniqueIPVisitsOnDay("Sep 27"));
	//System.out.println(x.countUniqueIPsInRange(200,299));
	//HashMap<String, Integer> ans = x.countVisitsPerIP();
	//System.out.println(ans);
	//System.out.println(x.mostNumberVisitsByIP(ans));
	//System.out.println(x.iPsMostVisits(ans));
	HashMap<String, ArrayList<String>> ans1 = x.iPsForDays();
	//System.out.println(x.dayWithMostIPVisits(ans1));
	System.out.println(x.iPsWithMostVisitsOnDay(ans1, "Sep 30"));
    }

    public static void main (String[] args) {
        Tester pr = new Tester();
        pr.testLogAnalyzer();
    }

}
