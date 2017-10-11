package Helper;
import View.PigeonWindow;
import static java.lang.Math.*;

public class Position{
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

     public Position positionHandler(double width, double height)
     {
    	 double eastLimit = PigeonWindow.getWidth_p() - PigeonWindow.border;
    	 double southLimit = PigeonWindow.getHeight_p() - PigeonWindow.border;
    	 double westLimit = PigeonWindow.border;
    	 double northLimit = PigeonWindow.border; 
    	 
         if (this.x + width > eastLimit)
         {
             this.x = PigeonWindow.getWidth_p() - PigeonWindow.border - width;
         }
         if (this.y + height*1.5 > southLimit)
         {
             this.y = PigeonWindow.getHeight_p() - PigeonWindow.border - 1.5*height;
         }
         if(this.x - width/2 < westLimit) {
        	 this.x = PigeonWindow.border + width/2;
         }
         if(this.y - height/2 < northLimit) {
        	 this.y = PigeonWindow.border + height/2;
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
      
      
      public void setX(double x) {
    	  this.x = x;
      }
      public void setY(double y) {
    	  this.y = y;
      }
      
      public int getXInt() {
      	return (int)x;
       }
       public int getYInt(){
       	return (int)y;
        }
     
     public boolean equals(Position p) 
     { 		 		
    	 return this.getXInt() == p.getXInt() && this.getYInt() == p.getYInt(); 		
 		
 	}   
     

}
