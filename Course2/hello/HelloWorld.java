import edu.duke.*;

public class HelloWorld {

	public static void main(String[] args) {
	    HelloWorld db = new HelloWorld();
	    db.runHello();
	}

	public void runHello () {
		FileResource res = new FileResource("hello_unicode.txt");
		for (String line : res.lines()) {
			System.out.println(line);
		}
	}
}
