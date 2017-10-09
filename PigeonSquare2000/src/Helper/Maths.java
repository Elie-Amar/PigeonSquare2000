package Helper;

import static java.lang.Math.*;

public class Maths {

	
	 public static int computeDistance(Position start, Position end)
     {
		 return  (int) sqrt(pow(end.x - start.x, 2) + pow(end.y - start.y, 2));
     }
}
