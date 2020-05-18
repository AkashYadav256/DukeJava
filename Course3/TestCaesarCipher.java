import edu.duke.*;
import java.io.File;

public class TestCaesarCipher {

    private int[] countLetters(String message) {
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

    public String breakCaesarCipher (String enc) {
	
	int[] freq = countLetters(enc);
	int maxDex = maxIndex(freq);
	int dkey = maxDex - 4;
	if (dkey<0){
	dkey = 26+maxDex-4;
	}
	CaesarCipher1 cc = new CaesarCipher1(26-dkey);
	return cc.encrypt(enc);
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
	CaesarCipher1 cc = new CaesarCipher1(15);
	String encrypted = cc.encrypt(message);
	System.out.println("Encypted: "+encrypted);
	System.out.println("Original: "+cc.decrypt(encrypted));
	System.out.println("Decoded attempt: "+breakCaesarCipher(encrypted));
	//System.out.println(halfOfString("Qbkm Zgis", 1));
    }

    public static void main (String[] args) {
        TestCaesarCipher pr = new TestCaesarCipher();
        pr.test();
    }

}
