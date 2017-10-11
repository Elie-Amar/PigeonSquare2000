package Model;
import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

import Helper.Coord;
import Helper.Position;
import Helper.Size;


public class Human implements Runnable{

	Position position;
	Caracter caracter;
	private Random random = new Random();
	private ImageIcon image;
	private Size size;
	private Position target;
    private static final double MAX_STEP_MOVE = 0.5; 
    private boolean Alive;
    private int refreshTime;
	
	public Human(Position _position, Position _target)
	{		
		  
		this.caracter = Caracter.getCaracter(Random(4));
		this.image = Caracter.getImage(caracter);
		this.size = new Size(image.getIconWidth(), image.getIconHeight());
        this.position = _position.positionHandler(size);  
        this.target = _target;
        this.refreshTime = 4;
        this.Alive = true;
        Thread t = new Thread(this);				
		t.start();
	}
	
	
	//for debug only
	public Human(Position _position) {
		this.caracter = Caracter.getCaracter(Random(4));
		this.image = Caracter.getImage(caracter);
		this.size = new Size(image.getIconWidth(), image.getIconHeight());
        this.position = _position.positionHandler(size); 
        this.Alive = true;
			// TODO Auto-generated constructor stub
		}

	@Override
	public void run() {
		while(Alive) {
			move();
			try {
				Thread.sleep(refreshTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
    public int Random(int n) { return random.nextInt(n); }
	
    public Position getPosition()
    {
        return position;
    }
    
    public Image getImage() {
    	return image.getImage();
    }
    
    public Size getSize(){
    	return this.size;
    }
    

    private void move(){
    	if(Coord.computeDistance(position,target) > 0.5) 
    	{
    		double angle = Coord.computeAngle(this.position, target);
    		this.position.x +=  MAX_STEP_MOVE * Math.cos(angle);
    		this.position.y +=  MAX_STEP_MOVE * Math.sin(angle); 
    	}
    	else
    		Alive = false;
    	
    }
	
    public boolean isAlive() {
    	return Alive;
    }
	
	
}
