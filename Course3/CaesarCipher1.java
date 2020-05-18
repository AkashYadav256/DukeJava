import edu.duke.*;
import java.io.File;

public class CaesarCipher1 {

    private String alph;
    private String alt;
    private int mainKey;

    public CaesarCipher1(int key){
	alph = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	alt = alph.substring(key) + alph.substring(0,key);
	mainKey = key;
    }

    public String encrypt(String input) {
    StringBuilder enc = new StringBuilder(input);
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

    public String decrypt (String enc) {
	//int[] freq = countLetters(enc);
	//int maxDex = maxIndex(freq);
	//int dkey = maxDex - 4;
	//if (dkey<0){
	//dkey = 26+maxDex-4;
	//}
	CaesarCipher1 cc = new CaesarCipher1(26-mainKey);
	return cc.encrypt(enc);
    }

    public String encryptTwoKeys(String input, int key1, int key2) {
	CaesarCipher1 cc1 = new CaesarCipher1(key1);
	CaesarCipher1 cc2 = new CaesarCipher1(key2);
	String enc1 = cc1.encrypt(input);
	String enc2 = cc2.encrypt(input);
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

    public void test() {
	FileResource fr = new FileResource();
	String message = fr.asString();
	String encrypted = encrypt(message);
	System.out.println(encrypted);
	//System.out.println(halfOfString("Qbkm Zgis", 1));
    }

    public static void main (String[] args) {
        CaesarCipher1 pr = new CaesarCipher1(23);
        pr.test();
    }

}
