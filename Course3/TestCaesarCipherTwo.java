import edu.duke.*;
import java.io.File;

public class TestCaesarCipherTwo {

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

    private String halfOfString (String enc, int s) {
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

    public String breakCaesarCipher (String enc) {
	String h1 = halfOfString(enc, 0);
	String h2 = halfOfString(enc, 1);
	int k1 = getKey(h1);
	int k2 = getKey(h2);
	System.out.println(k1);
	System.out.println(k2);
	CaesarCipherTwo cc = new CaesarCipherTwo(26-k1, 26-k2);
	String s1 = cc.encrypt(enc);
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
	CaesarCipherTwo c = new CaesarCipherTwo(21,8);
	String encrypted = c.encrypt(message);
	System.out.println("Encrypted: "+encrypted);
	System.out.println("Original: "+c.decrypt(encrypted));
	System.out.println("Attempt to decode: "+breakCaesarCipher(encrypted));
	//System.out.println(halfOfString("Qbkm Zgis", 1));
    }

    public static void main (String[] args) {
        TestCaesarCipherTwo pr = new TestCaesarCipherTwo();
        pr.test();
    }

}
