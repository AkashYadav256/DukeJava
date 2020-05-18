
public class MinutesFilter implements Filter {
	private int min;
	private int max;
	
	public MinutesFilter(int mmin, int mmax) {
		min = mmin;
		max = mmax;
	}
	
	@Override
	public boolean satisfies(String id) {
		return (MovieDatabase.getMinutes(id)>=min && MovieDatabase.getMinutes(id)<=max);
	}

}
