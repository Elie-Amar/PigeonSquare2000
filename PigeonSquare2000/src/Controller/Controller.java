package Controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

import Model.*;
import Helper.*;


public class Controller extends MouseAdapter  {
	
    private Environnement environnement;

    public Controller(Environnement environnement){
    	this.environnement = environnement;
    }
    
    @Override
	public void mousePressed(MouseEvent e) {
		super.mousePressed(e);
		int x = e.getX() - 12;
		int y = e.getY() - 52;
		//System.out.println("e: " + x + " " + y);
		if(SwingUtilities.isLeftMouseButton(e)) {
			Food f = new Food(new Position(x, y));
			environnement.addFood(f);
		}		
		
	}
}
