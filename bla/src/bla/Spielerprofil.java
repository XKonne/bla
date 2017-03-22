package bla;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.*;


public class Spielerprofil extends JFrame {
	// Variablen
	
	
	// Buttons
	private JButton btn_SpielerprofilSchliessen = new JButton();
	

	
	public Spielerprofil() {
		super();
		
		//Spielerprofil-JFrame initialisieren
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		int frameWidth = 250;
		int frameHeight = 400;
		setSize(frameWidth, frameHeight);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (d.width - getSize().width) / 2;
		int y = (d.height - getSize().height) / 2;
		setLocation(x-150, y);
		setTitle("Spielerprofil");
		setResizable(false);
		Container cp = getContentPane();
		cp.setLayout(null);
		setVisible(true);
		
		//GUI Elemente
		//Buttons
		btn_SpielerprofilSchliessen.setBounds(75, 10, 100, 30);
		btn_SpielerprofilSchliessen.setText("Profil schlieﬂen");
		btn_SpielerprofilSchliessen.setMargin(new Insets(2, 2, 2, 2));
		cp.add(btn_SpielerprofilSchliessen);
		
		btn_SpielerprofilSchliessen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
			dispose();
			}
		});
		
	}
	
	
	
	public static void main(String[] args) {	
		new Spielerprofil();
	}
	

}