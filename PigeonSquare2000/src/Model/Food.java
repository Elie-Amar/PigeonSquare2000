package Model;

import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import Helper.Position;

public class Food {
	
	 private Position position;
     private boolean isEaten;
     private boolean isFresh;
     private int width;
     private int height;
     static ImageIcon image =  new ImageIcon("assets/food_60.png");

     public Food(Position _position)
     {
         this.position = _position;
        //System.out.println("Foodcreated at " + position.x+ " " + position.y);
         this.isEaten = false;
         this.isFresh = true;
         this.width = image.getIconWidth();
         this.height = image.getIconHeight();
     }
     
     public Food()
     {
        //System.out.println("Foodcreated at " + position.x+ " " + position.y);
         this.isEaten = false;
         this.isFresh = true;
         this.width = image.getIconWidth();
         this.height = image.getIconHeight();
         //System.out.println(Integer.toString(width) + "  " + Integer.toString(height));
     }

 

     public boolean Fresh()
     {
         return isFresh;
     }

     public Position getPosition()
     {
         return position;
     }
     
     public void setPosition (Position _position) {
    	 this.position = _position;
     }
     
     public void setPosition(int _x, int _y) {
    	 this.position.x = _x;
    	 this.position.y = _y;
     }

     public int getWidth() {
    	 return width;
     }
     
     public int getHeight() {
    	 return height;
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
