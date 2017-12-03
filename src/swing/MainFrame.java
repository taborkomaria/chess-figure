package swing;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.*;
public class MainFrame extends JFrame{
	public MainFrame() {
		setSize(520,550);
		setLocationRelativeTo(null); 
		Panel panel = new Panel();
		add(panel);
	}
}

