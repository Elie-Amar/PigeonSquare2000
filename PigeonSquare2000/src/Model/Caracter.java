package Model;


import javax.swing.ImageIcon;

public enum Caracter {

	Mario, Yoshi, Peach, Toad;
	
	public static Caracter getCaracter(int n) {
		switch(n) {
			case 0 : return Mario;
			case 1 : return Yoshi;
			case 2 : return Peach; 
			case 3 : return Toad;		
		}
		
		return null;
	}
	
	public static ImageIcon getImage(Caracter caracter) {
		switch(caracter) {
			case Mario : return new ImageIcon("assets/mario_107_91.png");
			case Yoshi : return new ImageIcon("assets/yoshi_93_101.png");
			case Peach : return new ImageIcon("assets/peach_97_107.png");
			case Toad :  return new ImageIcon("assets/toad_96_80.png");		
		}
		return null;
		
	}
	
}

