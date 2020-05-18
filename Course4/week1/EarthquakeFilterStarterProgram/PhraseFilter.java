
/**
 * Write a description of class MinMaxFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter
{
    private String where;
    private String phrase; 
    
    public PhraseFilter(String s1, String s2) { 
        where = s1;
	phrase = s2;
    } 

    public boolean satisfies(QuakeEntry qe) {
	if (where.equals("start")) {
		return qe.getInfo().indexOf(phrase)==0;
	}
	else if (where.equals("end")) {
		return qe.getInfo().indexOf(phrase)+phrase.length()==qe.getInfo().length();
	}
	else if (where.equals("any")) {
		return qe.getInfo().contains(phrase);
	}
        return false; 
    }

    public String getName() {
	return "PhraseFilter ";
    }

}
