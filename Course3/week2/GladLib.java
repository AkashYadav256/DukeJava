import edu.duke.*;
import java.io.File;
import java.util.*;

public class GladLib {

    private ArrayList<String> adjectiveList;
    private ArrayList<String> nounList;
    private ArrayList<String> colorList;
    private ArrayList<String> countryList;
    private ArrayList<String> nameList;
    private ArrayList<String> animalList;
    private ArrayList<String> timeList;
    private ArrayList<String> verbList;
    private ArrayList<String> fruitList;
    private ArrayList<String> usedList = new ArrayList<String>();
    private Random myRandom;

    private String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private String dataSourceDirectory = "data";

    public GladLib () {
	initializeFromSource(dataSourceDirectory);
	myRandom = new Random();
    }

    public GladLib (String source) {
	initializeFromSource(source);
	myRandom = new Random();
    }
    
    public void initializeFromSource(String source){
	adjectiveList = readIt(source+"/adjective.txt");
	nounList = readIt(source+"/noun.txt");
	colorList = readIt(source+"/color.txt");
	countryList = readIt(source+"/country.txt");
	nameList = readIt(source+"/name.txt");
	animalList = readIt(source+"/animal.txt");
	timeList = readIt(source+"/timeframe.txt");
	verbList = readIt(source+"/verb.txt");
	fruitList = readIt(source+"/fruit.txt");
    }

    public String randomFrom(ArrayList<String> source){
	int index = myRandom.nextInt(source.size());
	return source.get(index);
    }

    public String getSubstitute(String label){
	if (label.equals("adjective")){
		return randomFrom(adjectiveList);
	}
	if (label.equals("noun")){
		return randomFrom(nounList);
	}
	if (label.equals("color")){
		return randomFrom(colorList);
	}
	if (label.equals("country")){
		return randomFrom(countryList);
	}
	if (label.equals("name")){
		return randomFrom(nameList);
	}
	if (label.equals("animal")){
		return randomFrom(animalList);
	}
	if (label.equals("timeframe")){
		return randomFrom(timeList);
	}
	if (label.equals("verb")){
		return randomFrom(verbList);
	}
	if (label.equals("fruit")){
		return randomFrom(fruitList);
	}
	if (label.equals("number")){
		return ""+myRandom.nextInt(50)+5;
	}
	return "*UNKNOWN*";
    }

    private String processWord(String w){
	int f = w.indexOf("<");
	int l = w.indexOf(">");
	if (f==-1 || l==-1){
		return w;
	}
	String pre = w.substring(0,f);
	String suf = w.substring(l+1);
	String sub = getSubstitute(w.substring(f+1,l));
	if (usedList!=null){
		while (usedList.contains(sub)) {sub = getSubstitute(w.substring(f+1,l));}
	}
	usedList.add(sub);
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

    public void makeStoryOf() {
	String temp = fromTemplate(dataSourceDirectory+"/madtemplate2.txt");
	printOut(temp, 60);
	System.out.println("Words replaced: "+usedList.size());
    }

    public static void main (String[] args) {
        GladLib pr = new GladLib();
        pr.makeStoryOf();
    }
}