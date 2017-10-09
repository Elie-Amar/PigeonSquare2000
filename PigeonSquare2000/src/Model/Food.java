package Model;

import javax.swing.ImageIcon;

import Helper.Position;

import java.awt.Image;

public class Food {
	
	 private Position position;
     private boolean isEaten;
     private boolean isFresh;
     static ImageIcon image =  new ImageIcon("assets/food.png");
     
     
     public Food(Position _position)
     {
         this.position = _position;
        //System.out.println("Foodcreated at " + position.x+ " " + position.y);
         isEaten = false;
         isFresh = true;
     }

 

     public boolean Fresh()
     {
         return isFresh;
     }

     public Position getPosition()
     {
         return position;
     }

     public void eaten()
     {
         isEaten = true; 

     }
     
     public static Image getImage() 
     {
    	 return image.getImage();
     }
}
