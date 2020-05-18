import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
	int ans = 0;
	for (Point x : s.getPoints()) {
		ans = ans + 1;
	}
        return ans;
    }

    public double getAverageLength(Shape s) {
        // Put code here
	double avg = getPerimeter(s)/getNumPoints(s);
        return avg;
    }

    public double getLargestSide(Shape s) {
        // Put code here
	double maxS = 0.0;
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
		// Find distance from prevPt point to currPt
		double currDist = prevPt.distance(currPt);
		if (maxS < currDist) {maxS = currDist;}
		// Update prevPt to be currPt
		prevPt = currPt;
        }
        return maxS;
    }

    public double getLargestX(Shape s) {
        // Put code here
	double maxX = 0.0;
        for (Point currPt : s.getPoints()) {
		// Find distance from prevPt point to currPt
		double currX = currPt.getX();
		if (maxX < currX) {maxX = currX;}
        }
        return maxX;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
	double maxP = 0;
	DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
	    Shape s = new Shape(fr);
	    double length = getPerimeter(s);
	    if (maxP < length) {maxP = length;}
        }
        return maxP;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        String ans = new String("ans");
	double maxP = 0;
	DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
	    Shape s = new Shape(fr);
	    double length = getPerimeter(s);
	    if (maxP < length) {
		maxP = length;
	        ans = f.getName();
	    }
        }
        return ans;
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
	int numP = getNumPoints(s);
	double avgL = getAverageLength(s);
	double maxL = getLargestSide(s);
	double max_X = getLargestX(s);
        System.out.println("Number of points = " + numP);
        System.out.println("perimeter = " + length);
        System.out.println("Average length = " + avgL);
        System.out.println("Maximum Length = " + maxL);
        System.out.println("Maximum X = " + max_X);
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
	double max_P = getLargestPerimeterMultipleFiles();
        System.out.println("Maximum perimeter = " + max_P);
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
	String ans = getFileWithLargestPerimeter();
	System.out.println("File with maximum perimeter = " + ans);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeterMultipleFiles();
    }
}
