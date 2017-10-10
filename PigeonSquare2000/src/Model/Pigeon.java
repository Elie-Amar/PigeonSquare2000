package Model;

import javax.swing.ImageIcon;

import Helper.*;
import Helper.Maths;

import java.awt.Image;

public class Pigeon implements Runnable
    {
        private boolean isHungry;
        private Position position;
        private boolean isAfraid;
      //  private Size size = new Size(PigeonFeed.Properties.Resources.pigeon.Width, PigeonFeed.Properties.Resources.pigeon.Height);
        private boolean Alive;
        private Food targetFood;
        private Thread thread;
        private Size size;
        public static ImageIcon image =  new ImageIcon("assets/pigeon_80.png");
        
        public Pigeon()
        {

        }

        public Pigeon(Position _position)
        {
        	this.size = new Size(image.getIconWidth(), image.getIconHeight());
        	//System.out.println("p:" +  image.getIconWidth() + " " + image.getIconHeight());
            this.position = _position.positionHandler(size);  
        	//this.position = _position;
            isAfraid = false;
            isHungry = true;
            Alive = true;
            //Console.WriteLine("Pigeon created at " + position.x + " " + position.y);
            
        }

        public void Start()
        {
          
        }

        @Override
        public void run()
        {
         //   Console.WriteLine("Pigeon thread starts");
            while (this.Alive)
            {
                if (!isAfraid)
                {
                    //if(isThereFood())
                      //     chooseFood();
                    if (targetFood != null)
                        MoveToFood();
                    
                }
                else
                    runAway();
                
            }
        }
        

        private void runAway()
        {

        }

      
     
     /*   private Food chooseFood()
        {
            float best_dist = 1000;
            float tmp_dist;

            
            for(Food f : Environnement.foodList)
            {
            	    tmp_dist = Math.computeDistance(position, f.getPosition());
                     if (best_dist > tmp_dist)
                     {
                         best_dist = tmp_dist;
                         targetFood = f;
                     }
                 
            }                
            
            return null;            
        }*/
              

        private void MoveToFood()
        {
            position = targetFood.getPosition();
        }

        private void resetState()
        {
            //x_dest = x;
            //y_dest = y;
            //angle = 0.0f;
            targetFood = null;
            isAfraid = false;
        }

        private void eat()
        {
            targetFood.eaten();
            //resetState();
        }
        
        public Position getPosition()
        {
        	return position;
        }

        public static Image getImage() 
        {
       	 	return image.getImage();
        }

       
    }


