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
      private boolean Alive;
      private Food targetFood;
      private Size size;
      public static ImageIcon image =  new ImageIcon("assets/pigeon_80.png");
      private Environnement environnement;
      private static final double MAX_STEP_MOVE = 0.5;
      double angle =0;
      private int refreshTime;
      int number;
      
        
        public Pigeon()
        {

        }

        public Pigeon(Position _position, Environnement _environnement, int number)
        {
        	this.size = new Size(image.getIconWidth(), image.getIconHeight());
            this.position = _position.positionHandler(size);  
        	//this.position = _position;
            isAfraid = false;
            isHungry = true;
            Alive = true;
            this.environnement = _environnement;
            this.refreshTime = (int) (1. / 25. * 100.);
            this.number = number;
            //Console.WriteLine("Pigeon created at " + position.x + " " + position.y);
            
        }

       
        @Override
        public void run()
        {
        
            while (this.Alive)
            { 
            	System.out.println(number);
            	
    			this.targetFood = this.environnement.getNearestFood(this);

    			if (targetFood != null) 
    			{    				    				
    				//MoveToFood();
    				move();    				
    				
    				if (Maths.computeDistance(position, targetFood.getPosition()) < 1) 
    					 this.environnement.eatFood(targetFood);       			
    			                
    			}
    			try {
    				Thread.sleep(refreshTime);
    			} catch (InterruptedException e) {
    				e.printStackTrace();
    			}

            }
        }
        

        private void runAway()
        {

        }

        private void move(){
            angle = Maths.computeAngle(this.position, targetFood.getPosition());
            this.position.x +=  MAX_STEP_MOVE * Math.cos(angle);
            this.position.y +=  MAX_STEP_MOVE * Math.sin(angle); 
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


