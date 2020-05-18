import edu.duke.*;
import java.io.File;
import java.util.*;

public class GladLibMap {

    private HashMap<String, ArrayList<String>> myMap = new HashMap<String, ArrayList<String>>();
    private ArrayList<String> usedList = new ArrayList<String>();
    private ArrayList<String> usedList1 = new ArrayList<String>();
    private Random myRandom;

    private String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private String dataSourceDirectory = "data";

    public GladLibMap () {
	initializeFromSource(dataSourceDirectory);
	myRandom = new Random();
	//myMap = new HashMap<String, ArrayList<String>>();
    }

    public GladLibMap (String source) {
	initializeFromSource(source);
	myRandom = new Random();
	//myMap = new HashMap<String, ArrayList<String>>();
    }
    
    public void initializeFromSource(String source){
	String[] labels = {"country", "noun", "animal", "adjective", "name", "color", "timeframe", "verb", "fruit"};
	for (String s : labels){
		ArrayList<String> list = readIt(source+"/"+s+".txt");
		myMap.put(s,list);
		//System.out.println(list);
	}
    }

    public String randomFrom(ArrayList<String> source){
	int index = myRandom.nextInt(source.size());
	return source.get(index);
    }

    public String getSubstitute(String label){
	if (label.equals("number")){
		return ""+myRandom.nextInt(50)+5;
	}
	return randomFrom(myMap.get(label));
    }

    private String processWord(String w){
	int f = w.indexOf("<");
	int l = w.indexOf(">");
	if (f==-1 || l==-1){
		return w;
	}
	String pre = w.substring(0,f);
	String suf = w.substring(l+1);
	String x = w.substring(f+1,l);
	if (!usedList1.contains(x)){usedList1.add(x);}
	String sub = getSubstitute(w.substring(f+1,l));
	if (usedList!=null){
		while (usedList.contains(sub)) {sub = getSubstitute(w.substring(f+1,l));}
	}
	usedList.add(sub);
	//System.out.println(usedList);
	//System.out.println(usedList1);
	return pre+sub+suf;
    }

    private void printOut(String s, int lineWidth){
	int c = 0;
	for (String w : s.split("\\s+")){
		if (c+w.length()>lineWidth){
			System.out.println();
			c = 0;
		}
		System.out.print(w+" ");
		c += w.length() + 1;
	}
    }

    private String fromTemplate(String source) {
	String story = "";
	if (source.startsWith("http")){
		URLResource r = new URLResource(source);
		for (String word : r.words()){
			story = story + processWord(word) + " ";
		}
	}
	else{
		FileResource r = new FileResource(source);
		for (String word : r.words()){
			story = story + processWord(word) + " ";
		}
	}
	return story;
    }

    private ArrayList<String> readIt(String source) {
	ArrayList<String> list = new ArrayList<String>();
	if (source.startsWith("http")){
		URLResource r = new URLResource(source);
		for (String line : r.lines()){
			list.add(line);
		}
	}
	else{
		FileResource r = new FileResource(source);
		for (String line : r.lines()){
			list.add(line);
		}
	}
	return list;
    }

    public int totalWordsInMap(){
	int x =0;
	for (String s : myMap.keySet()){
		x += myMap.get(s).size();
	}
	return x;
    }

    public int totalWordsConsidered(){
	int x = 0;
	for (String s : usedList1){
		if (!s.equals("number")){x += myMap.get(s).size();}
	}
	return x;
    }

    public void makeStoryOf() {
	String temp = fromTemplate(dataSourceDirectory+"/madtemplate2.txt");
	printOut(temp, 60);
	System.out.println("Words replaced: "+usedList.size());
	System.out.println(totalWordsInMap());
	System.out.println(totalWordsConsidered());
    }

    public static void main (String[] args) {
        GladLibMap pr = new GladLibMap();
        pr.makeStoryOf();
    }
}
