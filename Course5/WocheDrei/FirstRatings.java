import edu.duke.*;
import java.util.*;
import java.io.*;
import org.apache.commons.csv.*;

public class FirstRatings {
	private HashMap<String, Integer> hm; //key is director name and integer is number of movies accredited to his name

	public FirstRatings() {
		hm = new HashMap<String, Integer>();
	}

	private void buildMap(ArrayList<Movie> ar) {
		for (int i=0; i<ar.size(); i++) {
			Movie m = ar.get(i);
			String dr = m.getDirector();
			ArrayList<String> dir = new ArrayList<>(Arrays.asList(dr.split(",")));
			for (int k=0; k<dir.size(); k++) {
				if (hm.containsKey(dir.get(k))) {hm.put(dir.get(k), hm.get(dir.get(k))+1);}
				else {hm.put(dir.get(k), 1);}
			}
		}
	}

	public ArrayList<Movie> loadMovies(String filename) {
		ArrayList<Movie> ans = new ArrayList<Movie>();
		File f = new File(filename);
		FileResource fr = new FileResource(f);
		CSVParser parser = fr.getCSVParser();
		for (CSVRecord record : parser) {
			Movie m = new Movie(record.get("id"), record.get("title"), record.get("year"), record.get("genre"), record.get("director"), record.get("country"), record.get("poster"), record.get("minutes"));
			ans.add(m);
		}
		return ans;
	}

	public ArrayList<Rater> loadRaters(String filename) {
		ArrayList<Rater> ans = new ArrayList<Rater>();
		File f = new File(filename);
		FileResource fr = new FileResource(f);
		CSVParser parser = fr.getCSVParser();
		HashMap<String, ArrayList<String>> hm1 = new HashMap<String, ArrayList<String>>(); //key is ID of rater and is mapped to different movies rated
		HashMap<String, ArrayList<String>> hm2 = new HashMap<String, ArrayList<String>>(); //key is ID of rater and is mapped to different ratings provided
		for (CSVRecord record : parser) {
			if (hm1.containsKey(record.get("rater_id"))) {
			hm1.get(record.get("rater_id")).add(record.get("movie_id"));
			hm2.get(record.get("rater_id")).add(record.get("rating"));
				hm1.put(record.get("rater_id"), hm1.get(record.get("rater_id")));
				hm2.put(record.get("rater_id"), hm2.get(record.get("rater_id")));
			}
			else {
				ArrayList<String> s1 = new ArrayList<String>();
				ArrayList<String> s2 = new ArrayList<String>();
				s1.add(record.get("movie_id"));
				s2.add(record.get("rating"));
				hm1.put(record.get("rater_id"), s1);
				hm2.put(record.get("rater_id"), s2);
			}
		}
		for (String s : hm1.keySet()) {
			Rater r = new EfficientRater(s);
			for (int i=0; i<hm1.get(s).size(); i++) {
				r.addRating(hm1.get(s).get(i), hm2.get(s).get(i));
			}
			ans.add(r);
		}
		return ans;
	}

	public int numRtg(ArrayList<Rater> a, int i) {
		Rater m = a.get(i);
		return m.numRatings();
	}

	public HashMap<String, Integer> movieMapping(String filename) { //returns movie ID mapped to number of ratings it received
		File f = new File(filename);
		FileResource fr = new FileResource(f);
		CSVParser parser = fr.getCSVParser();
		HashMap<String, Integer> hm3 = new HashMap<String, Integer>();
		for (CSVRecord record : parser) {
			if (hm3.containsKey(record.get("movie_id"))) {
				hm3.put(record.get("movie_id"), hm3.get(record.get("movie_id"))+1);
			}
			else {
				hm3.put(record.get("movie_id"), 1);
			}
		}
		return hm3;
	}



	public int getNumOfRatingsOnMovie(HashMap<String, Integer> h, String id) {
		return h.get(id);
	}

	public void maxRtg(ArrayList<Rater> a) {
		int max = 0;
		for (int i=0; i<a.size(); i++) {
			if (numRtg(a,i) > max) {max = numRtg(a,i);}
		}
		int c = 0;
		ArrayList<String> al = new ArrayList<String>();
		for (int i=0; i<a.size(); i++) {
			if (numRtg(a,i) == max) {
				c += 1;
				al.add(a.get(i).getID());
			}
		}
		System.out.println("Maximum ratings: "+max+" and these were done by "+c+" number of raters.");
		System.out.println("Those raters were: "+al);
	}

	public void testLoadRaters() {
		ArrayList<Rater> a = loadRaters("data/ratings.csv");
		//System.out.println(a);
		System.out.println("Number of raters in file: "+a.size());
		for (int i=0; i<a.size(); i++) {
			Rater m = a.get(i);
			System.out.println("ID of rater "+m.getID()+" and number of ratings done: "+m.numRatings());
		}
		maxRtg(a);
		HashMap<String, Integer> x = movieMapping("data/ratings.csv");
		System.out.println("Number of movies in file: "+x.size());
		System.out.println(getNumOfRatingsOnMovie(x, "1798709"));
	}

	public void testLoadMovies() {
		ArrayList<Movie> a = loadMovies("data/ratedmoviesfull.csv");
		System.out.println("Number of movies in file: "+a.size());
		int com = 0;
		int len = 0;
		buildMap(a);
		for (int i=0; i<a.size(); i++) {
			Movie m = a.get(i);
			if (m.getGenres().indexOf("Comedy") != -1) {com += 1;}
			if (m.getMinutes() > 150) {len += 1;}
		}
		System.out.println("Number of films with Comedy genre: "+com);
		System.out.println("Number of films longer than 150 mins: "+len);
		int max = 0;
		for (String s : hm.keySet()) {
			if (hm.get(s) > max) {max = hm.get(s);}
		}
		int dr = 0;
		String ans = "";
		for (String s : hm.keySet()) {
			if (hm.get(s) == max) {dr += 1; ans = s;}
		}
		System.out.println("Number of maximum movies: "+max+" and number of directors with these many movies: "+dr+"name of director "+ans);
	}
/*
	public static void main (String[] args) {
        	FirstRatings pr = new FirstRatings();
        	pr.testLoadRaters();
    	}*/
}
