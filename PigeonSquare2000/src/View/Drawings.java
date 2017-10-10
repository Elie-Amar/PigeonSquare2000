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
				
		//Show food
		for (Food food : this.environnement.getFood()) {
			if (food.Fresh()) {
				int x = food.getPosition().x - food.getWidth()/2;
			    int y = food.getPosition().y - food.getHeight()/2;
				g.drawImage(Food.getImage(), x, y, this);
			}
		}

		// Show pigeon
		for (Pigeon pigeon : this.environnement.getPigeon()) {	
			int x = pigeon.getPosition().x - 40;
		    int y = pigeon.getPosition().y - 40;
		    //System.out.println(Food.getImage().getWidth(null) + " " + Food.getImage().getHeight(null));
			g.drawImage(Pigeon.getImage(),x, y, this);
		}
	}
}
