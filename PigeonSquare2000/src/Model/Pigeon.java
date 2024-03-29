package Model;

import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import Helper.Coord;
import Helper.Position;
import Helper.Size;
import View.PigeonWindow;



public class Pigeon implements Runnable
    {
	  private Position position;
      private boolean isAfraid;
      private boolean Alive;
      private boolean changeAngle;
      private boolean hasLaunchedTimer;
      private Food targetFood;
      private Human antiTargetHuman;
      private Size size;
      public static ImageIcon image =  new ImageIcon("assets/pigeon_80.png");
      private Environnement environnement;
      private static final double MAX_STEP_MOVE = 0.5;      
      private int refreshTime;         
      private double angle;
      private double previousAngle;       
      Random random; 
        
        public Pigeon(Position _position, Environnement _environnement)
        {
        	this.size = new Size(image.getIconWidth(), image.getIconHeight());
            this.position = _position.positionHandler(size);                 	
            isAfraid = false;          
            Alive = true;
            changeAngle = true;
            hasLaunchedTimer = false;
            this.environnement = _environnement;
            this.refreshTime = 4;          
            random = new Random();
          
        }       
       

       
        @Override
        public void run()
        {
        
            while (this.Alive)
            { 
            	           	
            	if(!isAfraid()) {
            	
            		this.targetFood = this.environnement.getNearestFood(this);

            		if (targetFood != null) 
	    			{    				    				
	    					move();    				
	    				
	    				if (Coord.computeDistance(position, targetFood.getPosition()) < 5) 
	    					 this.environnement.eatFood(targetFood);       			             
	    			}
            	}
            	else {
            		this.antiTargetHuman = this.environnement.getNearestHuman(this);
            		if(antiTargetHuman!= null)
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
            this.angle = Coord.computeAngle(this.position, targetFood.getPosition());
            double nextX = this.position.getX() + MAX_STEP_MOVE * Math.cos(angle);
            double nextY = this.position.getY() + MAX_STEP_MOVE * Math.sin(angle);            
            this.position.x = nextX;
            this.position.y = nextY; 
            this.position =  this.position.positionHandler(size);              
          
        }	
   
        private void runAway(){
        	if(!hasLaunchedTimer && !changeAngle) {
        		Timer timer = new Timer(500, action -> {
					changeAngle = true;
					hasLaunchedTimer = false;
				});
        		hasLaunchedTimer = true;
        		//System.out.println("launched timer");
        		timer.start();
        	}
            if(!changeAngle) {
            	angle = previousAngle;
            }
            else {
            	angle = Coord.computeAngle(this.position, antiTargetHuman.getPosition());            	
            	double randomAngle = ((double)random.nextInt(157) / 100) - 0.78;
                angle += randomAngle;
                previousAngle = angle;
                changeAngle = false;
              
            }                              
            
	            this.position.x -=  MAX_STEP_MOVE * Math.cos(angle);
	            this.position.y -=  MAX_STEP_MOVE * Math.sin(angle); 
	            this.position =  this.position.positionHandler(size);             
        }
                
        
        public Position getPosition()
        {
        	return position;
        }
        
        public Size getSize() {
        	return size;
        }

        public static Image getImage() 
        {
       	 	return image.getImage();
        }
        
        
        
       
       
    }


