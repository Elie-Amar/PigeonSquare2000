package Helper;

import static java.lang.Math.*;

public class Maths {

	
	 public static double computeDistance(Position start, Position end)
     {
		 return  sqrt(pow(end.x - start.x, 2) + pow(end.y - start.y, 2));
     }	
	 
	 public static double computeAngle(Position start, Position end){
	        return Math.atan2(end.getY() - start.getY(), end.getX() - start.getX());
	       
	}
}
