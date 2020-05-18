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
		if (i+4>dna.length()){break;}
		if (myMap.containsKey(dna.substring(i,i+3))){
			myMap.put(dna.substring(i,i+3), myMap.get(dna.substring(i,i+3))+1);
		}
		else{
			myMap.put(dna.substring(i,i+3),1);
		}
	}
    }

    public String getMostCommonCodon() {
	int x = 0;
	String ans = "";
	for (String sr : myMap.keySet()){
		if(x<myMap.get(sr)){
			x = myMap.get(sr);
			ans = sr;
		}
	}
	return ans+" "+x;
    }

    public void printCodonCounts(int s, int e) {
	for (String sr : myMap.keySet()){
		if (myMap.get(sr)>=s && myMap.get(sr)<=e){
			System.out.println("Codon: "+sr+"  "+"Count: "+myMap.get(sr));
		}
	}
    }

    public int countUniqueCodons(){
	
	return myMap.size();
    }

    public void test() {
	FileResource fr = new FileResource();
	String message = fr.asString();
	message = message.toUpperCase();
	newclass c1 = new newclass();
	c1.buildCodonMap(0,message);
	System.out.println(c1.getMostCommonCodon());
	System.out.println(c1.countUniqueCodons());
	c1.printCodonCounts(7,8);
	newclass c2 = new newclass();
	c2.buildCodonMap(1,message);
	System.out.println(c2.getMostCommonCodon());
	System.out.println(c2.countUniqueCodons());
	//c2.printCodonCounts(1,5);
	newclass c3 = new newclass();
	c3.buildCodonMap(2,message);
	System.out.println(c3.getMostCommonCodon());
	System.out.println(c3.countUniqueCodons());
	//c3.printCodonCounts(1,5);
    }

    public static void main (String[] args) {
        newclass pr = new newclass();
        pr.test();
    }
}
