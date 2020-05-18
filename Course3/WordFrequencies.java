import edu.duke.*;
import java.io.File;
import java.util.ArrayList;

public class WordFrequencies {

    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;

    public WordFrequencies () {
	myWords = new ArrayList<String>();
	myFreqs = new ArrayList<Integer>();
    }
    
    public void findUnique () {
	//myWords = myWords.clear();
	//myFreqs = myFreqs.clear();
	FileResource fr = new FileResource();
	for (String s: fr.words()) {
		s = s.toLowerCase();
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
    }

    public int findIndexOfMax () {
	int ans = 0;
	for (int i=0; i<myFreqs.size(); i++){
		if (myFreqs.get(i)>myFreqs.get(ans)){ans=i;}
	}
	return ans;
    }

    public void test() {
    findUnique();
    System.out.println(myWords.size());
    for (int i =0; i<myWords.size(); i++){
	System.out.println(myWords.get(i)+" "+myFreqs.get(i));
    }
    System.out.println(findIndexOfMax());
    System.out.println("Number of unique words: "+myWords.size());
    System.out.println("Word with maximum frequency: "+myWords.get(findIndexOfMax()));
    System.out.println("Its count: "+myFreqs.get(findIndexOfMax()));
    }

    public static void main (String[] args) {
        WordFrequencies pr = new WordFrequencies();
        pr.test();
    }
}
