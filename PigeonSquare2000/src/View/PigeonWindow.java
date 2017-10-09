package View;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import Controller.Controller;
import Model.*;



public class PigeonWindow extends JFrame {

	private JPanel contentPane;
	private Environnement environnement;
	private static int Width = 1200; 
	public static int Height = 800; 
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
				PigeonWindow frame = new PigeonWindow();
				frame.setVisible(true);
		} catch (Exception e) {
				e.printStackTrace();
		}
	}
			
				

	/**
	 * Create the frame.
	 */
	public PigeonWindow() {
		
		this.environnement = new Environnement() ;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, Width, Height);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		init();
				
	}
	
	
	public void init() {
		
		this.add(new Drawings(environnement));
		this.addMouseListener(new Controller(environnement));
		Timer timer = new Timer(30, action -> {
					this.repaint();
				});
		timer.start();
		
	}
	
	public static int getWidht() {
		return Width;
	}

	public int getHeight() {
		return Height;
	}
}
