import java.util.*;

public class DirectorsFilter implements Filter {
	private String myGenre;
	
	public DirectorsFilter(String genre) {
		myGenre = genre;
	}
	
	@Override
	public boolean satisfies(String id) {
		ArrayList<String> sr = new ArrayList<>(Arrays.asList(myGenre.split(",")));
		for (String s : sr) {
			if (MovieDatabase.getDirector(id).contains(s)) {return true;}
		}
	return false;
	}

}
