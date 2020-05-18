import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class MinTemp {
    
    public double minTemp(CSVParser parser) {
	double ans = 1000;
	for (CSVRecord record : parser) {
		String exp = record.get("TemperatureF");
		double x = Double.parseDouble(exp);
		if (ans>x && x>0) {ans = x;}
	}
	return ans;
    }

    public String minTempDays() {
	double mny = 1000;
	String name = "";
	DirectoryResource dr = new DirectoryResource();
	for (File f : dr.selectedFiles()) {
		FileResource fr = new FileResource(f);
		double max1 = minTemp(fr.getCSVParser());
		if (mny>max1) {
			name = f.getName();
			mny=max1;
		}
	}
	System.out.println(mny);
	return name;
    }

    public double minHum(CSVParser parser) {
	double ans = 1000;
	CSVRecord y = null;
	for (CSVRecord record : parser) {
		String exp = record.get("Humidity");
		if (exp.equals("N/A")) {continue;}
		double x = Double.parseDouble(exp);
		if (ans>x && x>0) {
			y = record;
			ans = x;
		}
	}
	System.out.println("Time: "+y.get("DateUTC"));
	return ans;
    }

    public String minHumDays() {
	double mny = 1000;
	String name = "";
	DirectoryResource dr = new DirectoryResource();
	for (File f : dr.selectedFiles()) {
		FileResource fr = new FileResource(f);
		double max1 = minHum(fr.getCSVParser());
		if (mny>max1) {
			name = f.getName();
			mny=max1;
		}
	}
	System.out.println(mny);
	return name;
    }

    public double avgTemp(CSVParser parser) {
	double ans = 0;
	double c = 0;
	for (CSVRecord record : parser) {
		String exp = record.get("TemperatureF");
		double x = Double.parseDouble(exp);
		ans += x;
		c += 1;
	}
	return ans/c;
    }

    public void last(CSVParser parser, int a) {
	double ans = 0;
	double c = 0;
	for (CSVRecord record : parser) {
		String exp = record.get("TemperatureF");
		double x = Double.parseDouble(exp);
		if (Double.parseDouble(record.get("Humidity"))>=a) {
			ans += x;
			c += 1;
		}
	}
	if (c!=0){
		System.out.println(ans/c);
	}
	else {
		System.out.print("No temperatures with that humidity");
	}
    }

    public void test() {
	System.out.println(minTempDays());
	//FileResource fr = new FileResource();
	//CSVParser parser = fr.getCSVParser();
	//System.out.println(minHumDays());
	//System.out.println(avgTemp(parser));
	//last(parser, 80);
    }

    public static void main (String[] args) {
        MinTemp pr = new MinTemp();
        pr.test();
    }
}
