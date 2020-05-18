import edu.duke.*;
import java.io.File;

public class WordPlay {
    
    public boolean isVowel(char ch) {
	if (Character.isUpperCase(ch)){
		if ("AEIOU".indexOf(ch)!=-1){return true;}
	}
	else if (Character.isLowerCase(ch)){
		if ("aeiou".indexOf(ch)!=-1){return true;}
	}
	return false;
    }

    public String replaceVowels(String p, char ch){
	StringBuilder ans = new StringBuilder(p);
	for (int i=0; i<p.length(); i++){
		if (isVowel(ans.charAt(i))){ans.setCharAt(i, ch);}
	}
	return ans.toString();
    }

    public String emphasize(String p, char ch){
	StringBuilder ans = new StringBuilder(p);
	p = p.toLowerCase();
	ch = Character.toLowerCase(ch);
	for (int i=0; i<p.length(); i++){
		if (p.charAt(i)==ch){
			if ((i+1)%2==0){
				ans.setCharAt(i, '+');
			}
			else{
				ans.setCharAt(i, '*');
			}
		}
	}
	return ans.toString();
    }

    public void test(){
	//System.out.println(isVowel('!'));
	//System.out.println(replaceVowels("Hello World", '*'));
	System.out.println(emphasize("Mary Bella Abracadabra", 'a'));
    }

    public static void main (String[] args) {
        WordPlay pr = new WordPlay();
        pr.test();
    }

}
