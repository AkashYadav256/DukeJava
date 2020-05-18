import java.util.*;
import edu.duke.*;
import java.io.File;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        //REPLACE WITH YOUR CODE
	StringBuilder ans = new StringBuilder();
	for (int i=whichSlice; i<message.length(); i+=totalSlices){
		//if (i+totalSlices+1>message.length()) {break;}
		ans.append(message.charAt(i));
	}
        return ans.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        //WRITE YOUR CODE HERE
	String[] sr = new String[klength];
	CaesarCracker cc = new CaesarCracker();
	for (int i=0; i<klength; i++) {
		sr[i] = sliceString(encrypted, i, klength);
		key[i] = cc.getKey(sr[i]);
	}
        return key;
    }

    public HashSet<String> readDictionary(FileResource fr) {
	HashSet<String> hs = new HashSet<String>();
	for (String line : fr.lines()) {
		line = line.toLowerCase();
		hs.add(line);
	}
	return hs;
    }

    public int countWords(String message, HashSet<String> hs) {
	int c = 0;
	for (String word : message.split("\\W+")) {
		if (hs.contains(word.toLowerCase())) {c += 1;}
	}
	return c;
    }

    public String breakForLanguage(String enc, HashSet<String> hs) {
	int x = 0;
	int kl = 0;
	String ans = "";
	char chr = mostCommonCharIn(hs);
	for (int i=1; i<=100; i++) {
		int[] tmp = tryKeyLength(enc, i, chr);
		VigenereCipher c = new VigenereCipher(tmp);
		String dec = c.decrypt(enc);
		int num = countWords(dec, hs);
		if (x<num) {
			x = num;
			ans = dec;
			kl = i;
		}
	}
	return ans;
    }

    public char mostCommonCharIn(HashSet<String> hs) {
	HashMap<String, Integer> hm = new HashMap<String, Integer>();
	for (String s : hs) {
		for (char c : s.toCharArray()) {
			if (hm.containsKey(Character.toString(c))) {
				hm.put(Character.toString(c), hm.get(Character.toString(c))+1);
			}
			else {
				hm.put(Character.toString(c), 1);
			}
		}
	}
	int max = 0;
	String ans = "";
        for(String s : hm.keySet()){
            if (hm.get(s) > max){
                max = hm.get(s);
		ans = s;
            }
        }
	return ans.charAt(0);
    }

    public void breakForAllLangs(String enc, HashMap<String, HashSet<String>> hm) {
	HashMap<String, Integer> ans = new HashMap<String, Integer>();
	String lang = "";
	int x = 0;
	String y = "";
	for (String s : hm.keySet()) {
		ans.put(breakForLanguage(enc, hm.get(s)), countWords(breakForLanguage(enc, hm.get(s)), hm.get(s)));
		if (x<countWords(breakForLanguage(enc, hm.get(s)),hm.get(s))) {
			x = countWords(breakForLanguage(enc, hm.get(s)),hm.get(s));
			lang = s;
			y = breakForLanguage(enc, hm.get(s));
		}
	}
	System.out.println(ans.size());
	System.out.println(y);
	System.out.println("Language is: "+lang);
    }

    public void breakVigenere () {
	DirectoryResource dr = new DirectoryResource();
	HashMap<String, HashSet<String>> yh = new HashMap<String, HashSet<String>>();
        for (File f : dr.selectedFiles()) {
		FileResource fr = new FileResource(f);
		yh.put(f.getName(), readDictionary(fr));
	}
	System.out.println(yh.size());
	
        //WRITE YOUR CODE HERE
	//VigenereBreaker pr = new VigenereBreaker();
        //System.out.println(sliceString("abcdefghijklm", 4, 5));
	FileResource fr = new FileResource();
	String message = fr.asString();
	breakForAllLangs(message, yh);

	//FileResource fr1 = new FileResource();
	//HashSet<String> hs = readDictionary(fr1);
	//System.out.println(Arrays.toString(tryKeyLength(message, 4, 'e')));
	//int[] x = tryKeyLength(message, 4, 'e');
	//System.out.println(mostCommonCharIn(hs));
	//System.out.println(c1.decrypt(message));
	//System.out.println(readDictionary(fr));
	//System.out.println(breakForLanguage(message, hs));
	//System.out.println(Arrays.toString(tryKeyLength(message, breakForLanguage(message, hs), 'e')));
	//int[] tmp = tryKeyLength(message, 38, 'e');
	//VigenereCipher c1 = new VigenereCipher(tmp);
	//System.out.println(countWords(c1.decrypt(message), hs));
    }

    public static void main (String[] args) {
        VigenereBreaker pr = new VigenereBreaker();
        //System.out.println(pr.sliceString("abcdefghijklm", 4, 5));
	pr.breakVigenere();
    }
    
}
