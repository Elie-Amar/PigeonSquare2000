package Controller;

import java.awt.*;
import java.awt.event.*;


public class WindowCloser extends WindowAdapter {
	

	private Frame frame;

	
	public WindowCloser(Frame f) {
		this.frame = f;
	}

	
	@Override
	public void windowClosing(WindowEvent e) {
		frame.dispose();
		System.exit(0);
	}

}
