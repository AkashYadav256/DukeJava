import edu.duke.*;
import java.io.File;

public class StringO {
    public String twoOccurrences (String dna1, String dna2) {
        int sIndex = dna2.indexOf(dna1);
	if (sIndex == -1) {
		return "False";
	}
	int lIndex = dna2.indexOf(dna1, sIndex+dna1.length());
        if (lIndex == -1) {
		return "False";
	}
	
	return "True";
    }

    public String lastPart (String dna1, String dna2) {
        int sIndex = dna2.indexOf(dna1);
	if (sIndex == -1) {
		return dna2;
	}
	return dna2.substring(sIndex+dna1.length(), dna2.length());
    }

    public void testing () {
        String dna1 = "by";
	String dna2 = "A story by Abby Long";
	System.out.println("String 1 is:" + dna1);
	System.out.println("String 2 is:" + dna2);
	System.out.println(twoOccurrences(dna1, dna2));

	System.out.println(lastPart("an", "banana"));
	System.out.println(lastPart("zoo", "forest"));
    }

    public static void main (String[] args) {
        StringO pr = new StringO();
        pr.testing();
    }
}
