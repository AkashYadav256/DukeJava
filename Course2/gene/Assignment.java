import edu.duke.*;
import java.io.File;

public class Assignment {
    
    public void findAbc(String input) {
    int index = input.indexOf("abc");
    while (true) {
        if (index == -1 || index >= input.length() - 3) {
            break;
        }
	System.out.println(index);
        String found = input.substring(index+1, index+4);
        System.out.println(found);
        index = input.indexOf("abc", index+1);
	System.out.println(index);
        }
    }

    public void test() {
    // findAbc("abcd");
    findAbc("abcabcabcabca");
    }

    public static void main (String[] args) {
        Assignment pr = new Assignment();
        pr.test();
    }
}