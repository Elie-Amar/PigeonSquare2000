package Model;
import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

import Helper.Position;
import Helper.Size;


public class Human {

	Position position;
	Caracter caracter;
	private Random random = new Random();
	private ImageIcon image;
	private Size size;
	
	public Human(Position _position)
	{		
		  
		this.caracter = Caracter.getCaracter(Random(4));
		this.image = Caracter.getImage(caracter);
		this.size = new Size(image.getIconWidth(), image.getIconHeight());
        this.position = _position.positionHandler(size);  
		
	}
	
    public int Random(int n) { return random.nextInt(n); }
	
    public Position getPosition()
    {
        return position;
    }
    
    public Image getImage() {
    	return image.getImage();
    }
	
	
	
}
