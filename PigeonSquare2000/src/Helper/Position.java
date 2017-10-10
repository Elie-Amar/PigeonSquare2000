package Helper;
import View.PigeonWindow;
import static java.lang.Math.*;

public class Position implements Comparable<Position>{
	 public double x, y;
     public Position(Position p)
     {
         this.x = p.x;
         this.y = p.y;
     }

     public Position(double _x, double _y)
     {
         this.x = _x;
         this.y = _y;
     }
     

     public Position(double _x, double _y, Size size)
     {
         this.x = _x;
         this.y = _y;
         positionHandler(size);
     }    

     private Position positionHandler(int width, int height)
     {
    	 int eastLimit = PigeonWindow.getWidth_p() - PigeonWindow.border;
    	 int southLimit = PigeonWindow.getHeight_p() - PigeonWindow.border - height;
    	 int westLimit = PigeonWindow.border + width;
    	 int northLimit = PigeonWindow.border + height; 
    	 
         if (this.x + width > eastLimit)
         {
             this.x = PigeonWindow.getWidth_p() - width;
         }
         if (this.y + height > southLimit)
         {
             this.y = PigeonWindow.getHeight_p() - height - height/2;
         }
         if(this.x + width/2 < westLimit) {
        	 this.x = width/2;
         }
         if(this.y + height/2 < northLimit) {
        	 this.y = height/2;
         }
         return new Position(x,y);
     }
     
     public Position positionHandler(Size size)
     {
        return positionHandler(size.width, size.height);
     }
     
     public double getX() {
     	return x;
      }
      public double getY() {
      	return y;
       }
      
      
      public int getXInt() {
      	return (int)x;
       }
       public int getYInt(){
       	return (int)y;
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
