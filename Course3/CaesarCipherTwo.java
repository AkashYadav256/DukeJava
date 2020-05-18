import edu.duke.*;
import java.io.File;

public class CaesarCipherTwo {

    private int mainKey1;
    private int mainKey2;

    public CaesarCipherTwo (int key1, int key2){
	mainKey1 = key1;
	mainKey2 = key2;
    }

    public String encrypt(String input) {
	CaesarCipher1 cc1 = new CaesarCipher1(mainKey1);
	CaesarCipher1 cc2 = new CaesarCipher1(mainKey2);
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

    public String decrypt(String enc) {
	//String h1 = halfOfString(enc, 0);
	//String h2 = halfOfString(enc, 1);
	//int k1 = getKey(h1);
	//int k2 = getKey(h2);
	//System.out.println(k1);
	//System.out.println(k2);
	CaesarCipherTwo c = new CaesarCipherTwo(26-mainKey1, 26-mainKey2);
	String s1 = c.encrypt(enc);
	return s1;
    }
}
