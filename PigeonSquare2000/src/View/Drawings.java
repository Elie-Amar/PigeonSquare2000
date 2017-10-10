package View;


import java.awt.Graphics;

import javax.swing.JPanel;


import Model.*;


public class Drawings extends JPanel{
	
	Environnement environnement;	
	
	//int pigeonOffset = 80;
	//int foodOffset = 60;
	public Drawings(Environnement _environnement) {
		this.environnement = _environnement;		
	}
	
	
	/**
	 * Gestion de l'affichage
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);		
		this.environnement.clean();		
		this.environnement.generateHuman();
		//Show food
		Environnement.foodLock.readLock().lock();
    	try {
    		for (Food food : this.environnement.getFood()) {
				int x = food.getPosition().getXInt() - food.getWidth()/2;
			    int y = food.getPosition().getYInt() - food.getHeight()/2;
				g.drawImage(food.getImage(), x, y, this);		
    		}
    	}
    	finally {
    		Environnement.foodLock.readLock().unlock();
    	}
		
		// Show pigeon
		for (Pigeon pigeon : this.environnement.getPigeon()) {	
			int x = pigeon.getPosition().getXInt() - 40;
		    int y = pigeon.getPosition().getYInt() - 40;
		    g.drawImage(Pigeon.getImage(),x, y, this);
		}
		
		for (Human human : this.environnement.getHuman()) {	
			int x = human.getPosition().getXInt() - 40;
		    int y = human.getPosition().getYInt() - 40;
		    g.drawImage(human.getImage(),x, y, this);
		}
	}
}
