package Model;
 
import java.awt.Image;
import java.awt.image.BufferedImage;
 
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.Timer;
 
import Helper.Position;
 
public class Food {
   
     private Position position;
     private boolean isFresh;
     private boolean toBeDeleted;
     private int width;
     private int height;
     static ImageIcon fresh =  new ImageIcon("assets/food_60.png");
     static ImageIcon rotten = new ImageIcon("assets/rottenness_60.png");
   
 
     public Food(Position _position)
     {
         this.position = _position;        
         this.toBeDeleted = false;
         this.isFresh = true;
         this.width = fresh.getIconWidth();
         this.height = fresh.getIconHeight();
         Timer timer1 = new Timer(5000, action -> {
                if(isFresh) {
                    hasRotted();
                }
            });
    timer1.start();
     }
     
     public Food()
     {        
         this.toBeDeleted = false;
         this.isFresh = true;
         this.width = fresh.getIconWidth();
         this.height = fresh.getIconHeight();      
     }
 
 
     public boolean Fresh()
     {
         return isFresh;
     }
     
     public boolean needsToBeDeleted() {
         return toBeDeleted;
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
     
     private void hasRotted() {
         isFresh = false;
         Timer timer2 = new Timer(5000, action -> {
             if(!toBeDeleted) {
                 toDelete();
             }
         });
         timer2.start();
     }
     
    private void toDelete() {
        toBeDeleted = true;
    }
   
     public Image getImage()
     {
        if(isFresh) {
            return fresh.getImage();
        }
        else {
            return rotten.getImage();
        }
     }
}