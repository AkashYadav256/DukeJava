import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class MaxTemp {
    
    public double maxTemp(CSVParser parser) {
	double ans = 0;
	for (CSVRecord record : parser) {
		String exp = record.get("TemperatureF");
		double x = Double.parseDouble(exp);
		if (ans<x) {ans = x;}
	}
	return ans;
    }

    public void maxTempDays() {
	double mny = 0;
	DirectoryResource dr = new DirectoryResource();
	for (File f : dr.selectedFiles()) {
		FileResource fr = new FileResource(f);
		double max1 = maxTemp(fr.getCSVParser());
		if (mny<max1) {mny=max1;}
	}
	System.out.println(mny);
    }

    public void test() {
	//FileResource fr = new FileResource();
	//CSVParser parser = fr.getCSVParser();
	//listExporters(parser, "coffee");
	//countryInfo(parser, "Nauru");
	//listExportersTwoProducts(parser, "gold", "diamonds");
	//System.out.println(numberOfExporters(parser, "sugar"));
	maxTempDays();
    }

    public static void main (String[] args) {
        MaxTemp pr = new MaxTemp();
        pr.test();
    }
}
