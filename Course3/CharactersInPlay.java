import edu.duke.*;
import java.io.File;
import java.util.ArrayList;

public class CharactersInPlay {

    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;

    public CharactersInPlay () {
	myWords = new ArrayList<String>();
	myFreqs = new ArrayList<Integer>();
    }
    
    public void update (String s) {
	//myWords = myWords.clear();
	//myFreqs = myFreqs.clear();
	//FileResource fr = new FileResource();
	//s = person.toLowerCase();
	int idx = myWords.indexOf(s);
	if (idx==-1){
		myWords.add(s);
		myFreqs.add(1);
	}
	else{
		int x = myFreqs.get(idx);
		myFreqs.set(idx, x+1);
	}
    }

    public void findAllCharacters () {
	//myWords = myWords.clear();
	//myFreqs = myFreqs.clear();
	FileResource fr = new FileResource();
	for (String s: fr.lines()) {
		int idx = s.indexOf(".");
		if (idx!=-1){
			String x = s.substring(0,idx);
			update(x);
		}
	}
    }

    public void charactersWithNumParts(int n1, int n2){
	for (int i =0; i<myWords.size(); i++){
		if (myFreqs.get(i)>=n1 && myFreqs.get(i)<=n2){
			System.out.println(myWords.get(i)+" "+myFreqs.get(i));
		}
	}
    }

    public void test() {
	findAllCharacters();
	//for (int i =0; i<myWords.size(); i++){
	//	System.out.println(myWords.get(i)+" "+myFreqs.get(i));
	//}
	charactersWithNumParts(10,15);
    //System.out.println(findIndexOfMax());
    //System.out.println("Number of unique words: "+myWords.size());
    //System.out.println("Word with maximum frequency: "+myWords.get(findIndexOfMax()));
    //System.out.println("Its count: "+myFreqs.get(findIndexOfMax()));
    }

    public static void main (String[] args) {
        CharactersInPlay pr = new CharactersInPlay();
        pr.test();
    }
}
