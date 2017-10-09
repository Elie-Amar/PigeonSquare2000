package View;


import java.awt.Graphics;

import javax.swing.JPanel;


import Model.*;


public class Drawings extends JPanel{
	
	Environnement environnement;	
	
	int offset =96;
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
				g.drawImage(Food.getImage(), food.getPosition().x, food.getPosition().y, offset, offset,
						this);
			}
		}

		// Show pigeon
		for (Pigeon pigeon : this.environnement.getPigeon()) {	
			
			g.drawImage(Pigeon.getImage(),pigeon.getPosition().x, pigeon.getPosition().y, offset, offset, this);
		}
	}
}
