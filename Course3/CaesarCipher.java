import edu.duke.*;
import java.io.File;

public class CaesarCipher {
    
    public String encrypt(String input, int key) {
    StringBuilder enc = new StringBuilder(input);
    String alph = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String alt = alph.substring(key) + alph.substring(0,key);
    for (int i=0; i<enc.length(); i++) {
	char c = enc.charAt(i);
	if (Character.isUpperCase(c)){
		int x = alph.indexOf(c);
		if (x!=-1){
			char cn = alt.charAt(x);
			enc.setCharAt(i, cn);
		}
	}
	else if (Character.isLowerCase(c)){
		String alph1 = alph.toLowerCase();
		String alt1 = alt.toLowerCase();
		int x = alph1.indexOf(c);
		if (x!=-1){
			char cn = alt1.charAt(x);
			enc.setCharAt(i, cn);
		}
	}
    }
    return enc.toString();
    }

    public String encryptTwoKeys(String input, int key1, int key2) {
	String enc1 = encrypt(input, key1);
	String enc2 = encrypt(input, key2);
	StringBuilder enc = new StringBuilder(input);
	for (int i=0; i<enc.length(); i++) {
		if (i%2==0){
			enc.setCharAt(i, enc1.charAt(i));
		}
		else{
			enc.setCharAt(i, enc2.charAt(i));
		}
	}
	return enc.toString();
    }

    public int[] countLetters(String message) {
	String alph = "abcdefghijklmnopqrstuvwxyz";
	int[] ans = new int[26];
	for (int i = 0; i<message.length(); i++) {
		char c = Character.toLowerCase(message.charAt(i));
		int x = alph.indexOf(c);
		if (x!=-1){
			ans[x]++;
		}
	}
	return ans;
    }

    public String decrypt (String enc) {
	//CaesarCipher cc = new CaesarCipher();
	int[] freq = countLetters(enc);
	int maxDex = maxIndex(freq);
	int dkey = maxDex - 4;
	if (dkey<0){
	dkey = 26+maxDex-4;
	}
	return encrypt(enc,26-dkey);
    }

    public String halfOfString (String enc, int s) {
	//CaesarCipher cc = new CaesarCipher();
	String s1 = "";
	for (int i=s; i<enc.length(); i=i+2) {
		s1 = s1 + Character.toString(enc.charAt(i));
	}
	return s1;
    }

    public int getKey (String s) {
	int[] fr = countLetters(s);
	int a = maxIndex(fr);
	int dkey = a - 4;
	if (dkey<0){
	dkey = 26+a-4;
	}
	return dkey;
    }

    public String decryptTwoKeys (String enc) {
	//CaesarCipher cc = new CaesarCipher();
	String h1 = halfOfString(enc, 0);
	String h2 = halfOfString(enc, 1);
	int k1 = getKey(h1);
	int k2 = getKey(h2);
	System.out.println(k1);
	System.out.println(k2);
	String s1 = encryptTwoKeys(enc, 26-k1, 26-k2);
	return s1;
    }

    public int maxIndex (int[] a) {
	int ans = 0;
	for (int i =0; i<a.length; i++){
		if (a[ans]<a[i]){ans=i;}
	}
	return ans;
    }

    public void test() {
	FileResource fr = new FileResource();
	String message = fr.asString();
	String encrypted = decryptTwoKeys(message);
	//String encrypted = decryptTwoKeys("Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!");
	System.out.println(encrypted);
	//System.out.println(halfOfString("Qbkm Zgis", 1));
    }

    public static void main (String[] args) {
        CaesarCipher pr = new CaesarCipher();
        pr.test();
    }

}
