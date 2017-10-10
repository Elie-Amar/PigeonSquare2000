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
		super.mousePressed(e);
		int x = e.getX() - 12;
		int y = e.getY() - 52;
		System.out.println("e: " + x + " " + y);
		
		
		/*
		Food f = new Food();
		f.setPosition(
				new Position(
					(int) (e.getX() - 50),
					(int) (e.getY() - 80)
					)); */
		Food f = new Food(new Position(x, y));
		environnement.addFood(f);
		
		//Pigeon f = new Pigeon(new Position(e.getX()-12,e.getY()-52));
		//environnement.addPigeon(f);

		
	}
}
