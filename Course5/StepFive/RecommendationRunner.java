import java.util.*;


/**
 * Implement this interface to allow your code to be integrated with our
 * web site.
 *
 * When users first visit the recommender website, our code will call the
 * method <code>getItemsToRate()</code> to get a list of movies to display
 * on the web page for users to rate.
 *
 * When a user submits their ratings, our code will call the method <code>
 * printRecommendationsFor</code> to get your recommendations based on the
 * user's ratings.  The ID given to this method is for a new Rater that we
 * have already added to the RaterDatabase with ratings for the movies
 * returned by the first method.  Whatever is printed from that method will
 * be displayed on the web page: HTML, plain text, or debugging information.
 *
 */
public class RecommendationRunner implements Recommender {
    /**
     * This method returns a list of movie IDs that will be used to look up
     * the movies in the MovieDatabase and present them to users to rate.
     *
     * The movies returned in the list will be displayed on a web page, so
     * the number you choose may affect how long the page takes to load and
     * how willing users are to rate the movies.  For example, 10-20 should
     * be fine, 50 or more would be too many.
     *
     * There are no restrictions on the method you use to generate this list
     * of movies: the most recent movies, movies from a specific genre,
     * randomly chosen movies, or simply your favorite movies.
     *
     * The ratings for these movies will make the profile for a new Rater
     * that will be used to compare to for finding recommendations.
     */
     @Override
    public ArrayList<String> getItemsToRate () {
      ArrayList<String> ans = new ArrayList<String>();
      AllFilters af = new AllFilters();
      YearAfterFilter yaf = new YearAfterFilter(2015);
      GenreFilter gf = new GenreFilter("Horror");
      af.addFilter(yaf);
      af.addFilter(gf);
      //System.out.println("Number of movies: "+sr.getMovieSize());
      RaterDatabase.initialize("ratings.csv");
      //System.out.println("Number of raters: "+RaterDatabase.size());
      MovieDatabase.initialize("ratedmoviesfull.csv");
      ans = MovieDatabase.filterBy(af);

      //System.out.println(ans);
      return ans;
    }

    /**
     * This method returns nothing, but prints out an HTML table of the
     * movies recommended for the given rater.
     *
     * The HTML printed will be displayed on a web page, so the number you
     * choose to display may affect how long the page takes to load.  For
     * example, you may want to limit the number printed to only the top
     * 20-50 movies recommended or to movies not rater by the given rater.
     *
     * You may also include CSS styling for your table using the &lt;style&gt;
     * tag before you print the table.  There are no restrictions on which
     * movies you print, what order you print them in, or what information
     * you include about each movie.
     *
     * @param webRaterID the ID of a new Rater that has been already added to
     *        the RaterDatabase with ratings for the movies returned by the
     *        method getItemsToRate
     */
     @Override
    public void printRecommendationsFor (String webRaterID) {
      FourthRatings fr = new FourthRatings();
      RaterDatabase.initialize("ratings.csv");
      //System.out.println("Number of raters: "+RaterDatabase.size());
      MovieDatabase.initialize("ratedmoviesfull.csv");
      YearAfterFilter yaf = new YearAfterFilter(2000);
      ArrayList<Rating> ans1 = new ArrayList<Rating>();
      List<Rating> ans = new ArrayList<Rating>();
      ans1 = fr.getSimilarRatingsByFilter(webRaterID, 5, 1, yaf);
      if (ans1.size() == 0) {System.out.println("<h1>No suitable movies for you unfortunately :-(</h1>");}
      else {
        ans = ans1.subList(0, 15);
        System.out.println("<h1>Please grab your propcorn and enjoy your favorite movies! :-)</h1>");
        System.out.println("  ");
        System.out.println("<table border='1' align='center'>");
				System.out.println("<tr>");
				System.out.println("<th>Title</th>");
				System.out.println("<th>Year</th>");
        System.out.println("<th>Genres</th>");
				System.out.println("<th>Runtime(mins)</th>");
        System.out.println("<th>Director(s)</th>");
				System.out.println("</tr>");
        for (Rating r : ans) {
          System.out.println("<tr>");
          System.out.println("<td><b>"+MovieDatabase.getTitle(r.getItem())+"</b></td>");
          System.out.println("<td>"+MovieDatabase.getYear(r.getItem())+"</td>");
          System.out.println("<td>"+MovieDatabase.getGenres(r.getItem())+"</td>");
          System.out.println("<td>"+MovieDatabase.getMinutes(r.getItem())+"</td>");
          System.out.println("<td><b>"+MovieDatabase.getDirector(r.getItem())+"</b></td>");
          System.out.println("</tr>");
          //ans.add(r.getItem());
        }
        System.out.println("</table>");
    }
      //System.out.println(ans.size());
    }

    public static void main (String[] args) {
      RecommendationRunner pr = new RecommendationRunner();
      pr.printRecommendationsFor("150");
  	}
}
