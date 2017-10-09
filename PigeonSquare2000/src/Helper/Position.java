package Helper;
import View.PigeonWindow;
import static java.lang.Math.*;

public class Position implements Comparable<Position>{
	 public int x, y;
     public Position(Position p)
     {
         this.x = p.x;
         this.y = p.y;
     }

     public Position(int _x, int _y)
     {
         this.x = _x;
         this.y = _y;
     }

     public Position(int _x, int _y, Size size)
     {
         this.x = _x;
         this.y = _y;
         positionHandler(size);
     }    

     private Position positionHandler(int width, int height)
     {
         if (this.x + width > PigeonWindow.getWidth_p())
         {
             this.x = this.x - width;
         }
         if (this.y + height > PigeonWindow.getHeight_p())
         {
             this.y = this.y - height;
         }
         return new Position(x,y);
     }
     
     public Position positionHandler(Size size)
     {
        return positionHandler(size.width, size.height);
     }
     
     public boolean equals(Position p) 
     { 		 		
    	 return this.x == p.x && this.y == p.y; 		
 		
 	}

	@Override
	public int compareTo(Position p) {
		if (this.x == p.x && this.y == p.y) {
			return 0;
		} else {
			double d1 = sqrt(pow(this.x, 2) + pow(this.y, 2));
			double d2 = sqrt(pow(p.x, 2) + pow(p.y, 2));

			if (d1 > d2) {
				return 1;
			} else {
				return -1;
			}
		}
	}
}
