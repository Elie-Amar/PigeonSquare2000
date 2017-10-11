package Model;
 
import java.awt.Image;
import java.awt.image.BufferedImage;
 
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.Timer;
 
import Helper.Position;
import Helper.Size;
 
public class Food {
   
     private Position position;
     private boolean isFresh;
     private boolean toBeDeleted;     
     static ImageIcon fresh =  new ImageIcon("assets/food_60.png");
     static ImageIcon rotten = new ImageIcon("assets/rottenness_60.png");
     private Size size;
   
 
     public Food(Position _position)
     {
    	 this.size = new Size(fresh.getIconWidth(), fresh.getIconHeight());
    	 this.position = _position.positionHandler(size);
         this.toBeDeleted = false;
         this.isFresh = true;
         
         Timer timer1 = new Timer(5000, action -> {
                if(isFresh) {
                    hasRotted();
                }
            });
    timer1.start();
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
 
     public Size getSize(){
     	return this.size;
     }
     
     private void hasRotted() {
         isFresh = false;
         this.size.setSize(rotten.getIconWidth(), rotten.getIconHeight());
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