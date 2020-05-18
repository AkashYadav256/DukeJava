import edu.duke.*;
import java.io.File;

public class GeneAssignment {
    public String findSimpleGene (String dna, String sCod, String eCod) {
	String result = "";
        int sIndex = dna.indexOf(sCod);
	if (sIndex == -1) {
		return result;
	}
	int lIndex = dna.indexOf(eCod, sIndex);
        if (lIndex == -1) {
		return result;
	}
	
	if ((lIndex-sIndex) % 3 == 0) {
		result = dna.substring(sIndex, lIndex+3);
	}
	return result;
    }

    public void testSimpleGene () {
        String dna1 = "AAATGCCCTAACTAGATTAAGAAACC";
	System.out.println("DNA is:" + dna1);
	System.out.println("Gene is:" + findSimpleGene(dna1, "ATG", "TAA"));
    }

    public static void main (String[] args) {
        GeneAssignment pr = new GeneAssignment();
        pr.testSimpleGene();
    }
}
