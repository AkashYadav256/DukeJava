import edu.duke.*;
import org.apache.commons.csv.*;

public class WhichCountriesExport {
    
    public void listExporters(CSVParser parser, String exportOfInterest) {
	for (CSVRecord record : parser) {
		String exp = record.get("Exports");
		int x = exp.indexOf(exportOfInterest);
		if (x!=-1) {System.out.println(record.get("Country"));}
	}
    }

    public void countryInfo(CSVParser parser, String country) {
	for (CSVRecord record : parser) {
		String exp = record.get("Country");
		if (exp.equals(country)) {System.out.println(country+": "+record.get("Exports")+": "+record.get("Value (dollars)"));}
	}
    }

    public void listExportersTwoProducts(CSVParser parser, String eI1, String eI2) {
	for (CSVRecord record : parser) {
		String exp = record.get("Exports");
		int x = exp.indexOf(eI1);
		int y = exp.indexOf(eI2);
		if (x!=-1 && y!=-1) {System.out.println(record.get("Country"));}
	}
    }

    public int numberOfExporters(CSVParser parser, String eI) {
	int ans = 0;
	for (CSVRecord record : parser) {
		String exp = record.get("Exports");
		int x = exp.indexOf(eI);
		if (x!=-1) {ans= ans+1;}
	}
	return ans;
    }

    public void bigExporters(CSVParser parser, String amt) {
	for (CSVRecord record : parser) {
		String exp = record.get("Value (dollars)");
		if (exp.length()>amt.length()) {System.out.println(record.get("Country")+exp);}
	}
    }

    public void test() {
	FileResource fr = new FileResource();
	CSVParser parser = fr.getCSVParser();
	//listExporters(parser, "cocoa");
	//countryInfo(parser, "Nauru");
	//listExportersTwoProducts(parser, "cotton", "flowers");
	//System.out.println(numberOfExporters(parser, "sugar"));
	bigExporters(parser, "$999,999,999,999");
    }

    public static void main (String[] args) {
        WhichCountriesExport pr = new WhichCountriesExport();
        pr.test();
    }
}
