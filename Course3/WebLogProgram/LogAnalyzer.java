
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
	FileResource fr = new FileResource(filename);
	for (String line : fr.lines()) {
		LogEntry x = WebLogParser.parseEntry(line);
		records.add(x);
	}
     }

     public int countUniqueIPs() {
	ArrayList<String> uip = new ArrayList<String>();
	for (LogEntry le : records) {
		if (!uip.contains(le.getIpAddress())) {uip.add(le.getIpAddress());}
	}
	return uip.size();
    }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }

     public void printAllHigherThanNum(int n) {
	for (LogEntry le : records) {
		if (le.getStatusCode()>n) {System.out.println(le);}
	}
     }

     public ArrayList<String> uniqueIPVisitsOnDay(String sr) {
	ArrayList<String> uip = new ArrayList<String>();
	for (LogEntry le : records) {
		if (!uip.contains(le.getIpAddress()) && le.getAccessTime().toString().substring(4,10).equals(sr)) {uip.add(le.getIpAddress());}
		//System.out.println(le.getAccessTime().toString().substring(4,10));
	}
	return uip;
     }

     public ArrayList<String> IPVisitsOnDay(String sr) {
	ArrayList<String> uip = new ArrayList<String>();
	for (LogEntry le : records) {
		if (le.getAccessTime().toString().substring(4,10).equals(sr)) {uip.add(le.getIpAddress());}
		//System.out.println(le.getAccessTime().toString().substring(4,10));
	}
	return uip;
     }

     public int countUniqueIPsInRange(int n1, int n2) {
	ArrayList<String> uip = new ArrayList<String>();
	for (LogEntry le : records) {
		if (le.getStatusCode()>=n1 && le.getStatusCode()<=n2 && !uip.contains(le.getIpAddress())) {uip.add(le.getIpAddress());}
	}
	return uip.size();
     }

     public HashMap<String, Integer> countVisitsPerIP() {
	HashMap<String, Integer> c = new HashMap<String, Integer>();
	for (LogEntry le : records) {
		if (!c.containsKey(le.getIpAddress())) {
			c.put(le.getIpAddress(), 1);
		}
		else {
			c.put(le.getIpAddress(), c.get(le.getIpAddress())+1);
		}
	}
	return c;
     }

     public int mostNumberVisitsByIP(HashMap<String, Integer> c) {
	int x = 0;
	for (String s : c.keySet()) {
		if (x<c.get(s)) {x=c.get(s);}
	}
	return x;
     }

     public ArrayList<String> iPsMostVisits(HashMap<String, Integer> c) {
	ArrayList<String> ans = new ArrayList<String>();
	int x = mostNumberVisitsByIP(c);
	for (String s : c.keySet()) {
		if (x==c.get(s)) {ans.add(s);}
	}
	return ans;
     }

     public HashMap<String, ArrayList<String>> iPsForDays() {
	HashMap<String, ArrayList<String>> ans = new HashMap<String, ArrayList<String>>();
	for (LogEntry le : records) {
		ans.put(le.getAccessTime().toString().substring(4,10), IPVisitsOnDay(le.getAccessTime().toString().substring(4,10)));
	}
	return ans;
     }

     public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> c) {
	int x = 0;
	String ans = "";
	for (String s : c.keySet()) {
		if (x<c.get(s).size()) {
			x=c.get(s).size();
			ans = s;
		}
	}
	return ans;
     }

     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> c, String sr) {
	ArrayList<String> ans = new ArrayList<String>();
	ArrayList<String> itr = c.get(sr);
	HashMap<String, Integer> c1 = new HashMap<String, Integer>();
	for (String s : itr) {
		if (!c1.containsKey(s)) {
			c1.put(s, 1);
		}
		else {
			c1.put(s, c1.get(s)+1);
		}
	}
	ans = iPsMostVisits(c1);
	return ans;
     }

}
