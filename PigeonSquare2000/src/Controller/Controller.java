package Controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Model.*;
import Helper.*;


public class Controller extends MouseAdapter  {
	
    private Environnement environnement;

    public Controller(Environnement environnement){
    	this.environnement = environnement;
    }

    

    
    @Override
	public void mousePressed(MouseEvent e) {
		super.mouseClicked(e);
		Food f = new Food(new Position(e.getX(),e.getY()));
		environnement.addFood(f);
	}
}
