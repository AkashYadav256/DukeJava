import edu.duke.*;
import java.io.File;
import java.util.*;

public class newclass {

    private HashMap<String, Integer> myMap;

    public newclass () {
	myMap = new HashMap<String, Integer>();
    }
    
    public void buildCodonMap (int s, String dna) {
	//myWords = myWords.clear();
	//myFreqs = myFreqs.clear();
	for (int i=s; i<dna.length(); i+=3){
		if (myMap.containsKey(s.substring(i,i+3))){
			myMap.put(s.substring(i,i+3), myMap.get(s.substring(i,i+3))+1);
		}
		else{
			myMap.put(s.substring(i,i+3),1);
		}
	}
	for (String s : myMap.keySet()){
		System.out.println("Codon: "+s+"  "+"Count: "+myMap.get(s));
	}
    }

    public void test() {
	newclass c1 = new newclass();
	c1.buildCodonMap(0,"CGTTCAAGTTCAA");
    }

    public static void main (String[] args) {
        newclass pr = new newclass();
        pr.test();
    }
}
