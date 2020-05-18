import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class BabyBirths {
    
    public void printNames() {
	FileResource fr = new FileResource();
	CSVParser parser = fr.getCSVParser(false); //indicating no header row
	for (CSVRecord record : parser) {
		int num = Integer.parseInt(record.get(2));
		if (num<=100) {	
			System.out.println("The name is "+record.get(0));
			System.out.println("The gender is "+record.get(1));
			System.out.println("Num Born "+record.get(2));
		}
	}
    }

    public int getRank(int year, String name, String g) {
	String fname = "data/yob" + year + ".csv";
	int f = 0;
	int ans = 0;
	FileResource fr = new FileResource(fname);
	CSVParser parser = fr.getCSVParser(false); //indicating no header row
	for (CSVRecord record : parser) {
		ans += 1;
		if (record.get(1).equals("F")) {f+=1;}
		if (record.get(0).equals(name) && record.get(1).equals(g)){
			if (record.get(1).equals("F")) {return ans;}
			else {return (ans-f);}
		}
	}
	return -1;
    }

    public String getName(int year, int rank, String g) {
	String fname = "data/yob" + year + ".csv";
	int f = 0;
	int ans = 0;
	FileResource fr = new FileResource(fname);
	CSVParser parser = fr.getCSVParser(false); //indicating no header row
	if (g.equals("F")) {
		for (CSVRecord record : parser) {
			ans += 1;
			if (record.get(1).equals("F")) {f+=1;}
			if (ans==rank && ans==f) {return record.get(0);}
		}
	}
	else {
		for (CSVRecord record : parser) {
			ans += 1;
			if (record.get(1).equals("F")) {f+=1;}
			if (f+rank==ans) {return record.get(0);}
		}
	}
	return "No Name";
    }

    public void whatIsNameInYear(String name, int year, int newYear, String g) {
	int cRank = getRank(year, name, g);
	String nName = getName(newYear, cRank, g);
	System.out.println(nName);
    }

    public int yearOfHighestRank(String name, String g) {
	int ans = 1000;
	int year = 0;
	DirectoryResource dr = new DirectoryResource();
	for (File f : dr.selectedFiles()) {
		int yr = Integer.parseInt(f.getName().substring(3, 7));
		int cRank = getRank(yr, name, g);
		if (ans>cRank && cRank!=-1) {
			year = yr;
			ans=cRank;
		}
	}
	System.out.println(year);
	return ans;
    }

    public double getAverageRank(String name, String g) {
	double ans = 0;
	double r = 0;
	DirectoryResource dr = new DirectoryResource();
	for (File f : dr.selectedFiles()) {
		ans += 1;
		r += getRank(Integer.parseInt(f.getName().substring(3, 7)), name, g);
	}
	return r/ans;
    }

    public int getTotalBirthsRankedHigher(int year, String name, String g) {
	int ans = 0;
	int rank = getRank(year, name, g);
	String fname = "data/yob" + year + ".csv";
	int f = 0;
	int ans1 = 0;
	FileResource fr = new FileResource(fname);
	CSVParser parser = fr.getCSVParser(false); //indicating no header row
	if (g.equals("F")) {
		for (CSVRecord record : parser) {
			ans += 1;
			if (record.get(1).equals("F")) {f+=1;}
			if (ans<rank && ans==f) {ans1 += Integer.parseInt(record.get(2));}
		}
	}
	else {
		for (CSVRecord record : parser) {
			ans += 1;
			if (record.get(1).equals("F")) {f+=1;}
			if (ans>f && f+rank>ans) {ans1 += Integer.parseInt(record.get(2));}
		}
	}
	return ans1;
    }

    public void totalBirths() {
	int ans = 0;
	int ansm = 0;
	FileResource fr = new FileResource();
	CSVParser parser = fr.getCSVParser(false); //indicating no header row
	for (CSVRecord record : parser) {
		int num = Integer.parseInt(record.get(2));
		ans += num;
		String g = record.get(1);
		if (g.equals("M")) {ansm += num;}
	}
	System.out.println("Total Births: "+ans);
	System.out.println("Boys' Births: "+ansm);
	System.out.println("Girls' Births: "+(ans-ansm));
    }

    public static void main (String[] args) {
        BabyBirths pr = new BabyBirths();
        //System.out.println(pr.getName(2014, 3, "M"));
	//System.out.println(pr.getTotalBirthsRankedHigher(1990, "Drew", "M"));
	//pr.totalBirths();
	//System.out.println(pr.getRank(2014, "Mich", "M"));
	//System.out.println(pr.getName(1982, 450, "M"));
	//pr.whatIsNameInYear("Owen", 1974, 2014, "M");
	//System.out.println(pr.yearOfHighestRank("Genevieve", "F"));
	//System.out.println(pr.getAverageRank("Robert", "M"));
    }

}
