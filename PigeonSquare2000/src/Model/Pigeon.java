package Model;

import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

import Helper.Maths;
import Helper.Position;
import Helper.Size;

public class Pigeon implements Runnable
    {
	  private boolean isHungry;
      private Position position;
      private boolean isAfraid;
      private boolean Alive;
      private boolean changeAngle;
      private Food targetFood;
      private Human antiTargetHuman;
      private Size size;
      public static ImageIcon image =  new ImageIcon("assets/pigeon_80.png");
      private Environnement environnement;
      private static final double MAX_STEP_MOVE = 0.5;      
      private int refreshTime;
      int number;
      double pi = Math.PI;
      
        
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
            changeAngle = false;
            this.environnement = _environnement;
            this.refreshTime = 4;
            this.number = number;
            //Console.WriteLine("Pigeon created at " + position.x + " " + position.y);
            
        }

       
        @Override
        public void run()
        {
        
            while (this.Alive)
            { 
            	//System.out.println(number);
            	
            	if(!isAfraid()) {
            	
            		this.targetFood = this.environnement.getNearestFood(this);

            		if (targetFood != null) 
	    			{    				    				
	    				//MoveToFood();
	    				move();    				
	    				
	    				if (Maths.computeDistance(position, targetFood.getPosition()) < 1) 
	    					 this.environnement.eatFood(targetFood);       			             
	    			}
            	}
            	else {
            		this.antiTargetHuman = this.environnement.getNearestHuman(this);
            		runAway();
            	}        	
            	try {
    				Thread.sleep(refreshTime);
    			} catch (InterruptedException e) {
    				e.printStackTrace();
    			}

            }
        }
        

        private boolean isAfraid() 
        {
        	if(this.environnement.isThereHuman())
        		isAfraid = this.environnement.closeToHuman(this);     	
        	
        	return isAfraid;
        }
        

        private void move(){
            double angle = Maths.computeAngle(this.position, targetFood.getPosition());
            this.position.x +=  MAX_STEP_MOVE * Math.cos(angle);
            this.position.y +=  MAX_STEP_MOVE * Math.sin(angle); 
        }
   
        private void runAway(){
        	double angle = changeAngle();
            this.position.x -=  MAX_STEP_MOVE * Math.cos(angle);
            this.position.y -=  MAX_STEP_MOVE * Math.sin(angle); 
        }
        
        private double changeAngle() {
        	double angle = Maths.computeAngle(this.position, antiTargetHuman.getPosition());
        	/*
        	if(changeAngle) {
        		Random random = new Random();
            	double randomAngle = ((double)random.nextInt(314) / 100) - 1.57;
                angle += randomAngle;
                System.out.println(randomAngle + " " + angle);
                changeAngle = false;
                //timer 500 -> changeAngle = true;
        	}
        	return angle;
        	*/
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


