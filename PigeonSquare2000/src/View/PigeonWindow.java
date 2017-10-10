package View;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import Controller.Controller;
import Controller.WindowCloser;
import Model.*;



public class PigeonWindow extends JFrame {

	private JPanel contentPane;
	private Environnement environnement;
	public static int Width = 1800; 
	public static int Height = 1000; 
	public static int border = 8;
	
	
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
		contentPane.setBorder(new EmptyBorder(border, border, border, border));
		contentPane.setLayout(new BorderLayout(0, 0));			
		setTitle("Pigeon Area");
	    setLocationRelativeTo(null);               
	    setContentPane(contentPane);               
	    
	    init();
				
	}
	
	
	public void init() {
		
		this.contentPane.add(new Drawings(environnement));
		this.addMouseListener(new Controller(environnement));
		this.addWindowListener(new WindowCloser(this));
		this.environnement.StartPigeonThread();
		Timer timer = new Timer(30, action -> {
					this.repaint();
				});
		timer.start();
		
	}

	public static int getWidth_p() {
		return Width;
	}
	
	public static int getHeight_p() {
		return Height;
	}
}
