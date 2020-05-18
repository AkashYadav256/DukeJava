import edu.duke.*;
import java.io.File;
import java.util.*;

public class WordsInFiles {

    private HashMap<String, ArrayList<String>> myMap;

    public WordsInFiles () {
	myMap = new HashMap<String, ArrayList<String>>();
    }
    
    private void addWordsFromFile (File f) {
	//myWords = myWords.clear();
	//myFreqs = myFreqs.clear();
	FileResource fr = new FileResource(f);
	for (String word : fr.words()){
		if (myMap.containsKey(word)){
			ArrayList<String> tmp = myMap.get(word);
			tmp.add(f.getName());
			myMap.put(word,tmp);
		}
		else{
			ArrayList<String> tmp = new ArrayList<String>();
			tmp.add(f.getName());
			myMap.put(word,tmp);
		}
	}
    }

    public void buildWordFileMap(){
	DirectoryResource dr = new DirectoryResource();
	for (File f : dr.selectedFiles()) {
		addWordsFromFile(f);
	}
    }

    public int maxNumber(){
	int x = 0;
	for (String s : myMap.keySet()){
		int a = myMap.get(s).size();
		if (x<a){x=a;}
	}
	return x;
    }

    public ArrayList<String> wordsInNumFiles(int n){
	ArrayList<String> ans = new ArrayList<String>();
	for (String s : myMap.keySet()){
		int a = myMap.get(s).size();
		if (a==n){ans.add(s);}
	}
	return ans;
    }

    public void printCodonCounts() {
	for (String sr : myMap.keySet()){
		System.out.println("Codon: "+sr+"  "+"Count: "+myMap.get(sr));
	}
    }

    public void printFilesIn(String word) {
	System.out.println(myMap.get(word));
    }

    public void test() {
	//File n = new File("file.txt");
	//addWordsFromFile(n);
	WordsInFiles c1 = new WordsInFiles();
	c1.buildWordFileMap();
	c1.printCodonCounts();
	System.out.println(c1.maxNumber());
	System.out.println(c1.wordsInNumFiles(4).size());
	//c1.printFilesIn("cats");
    }

    public static void main (String[] args) {
        WordsInFiles pr = new WordsInFiles();
        pr.test();
    }
}
