package bla;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.*;


public class GUI_Spielerprofil extends JFrame {
	// Variablen

	
	// Buttons
	private JButton btn_SpielerprofilSchliessen = new JButton();
	
	// Labels
	private JLabel lab_SpielerName = new JLabel();
	private JLabel lab_SpieleGespielt = new JLabel();
	private JLabel lab_SpieleGewonnen = new JLabel();
	private JLabel lab_SpieleGewonnenProzent = new JLabel();
	private JLabel lab_MinenGefunden = new JLabel();
	private JLabel lab_ZeitGesamt = new JLabel();
	private JLabel lab_ZeitSchnellstesSpiel = new JLabel();
	private JLabel lab_profilBild = new JLabel();
	
	public GUI_Spielerprofil() {
		super();
		
		//Spielerprofil-JFrame initialisieren
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		int frameWidth = 250;
		int frameHeight = 320;
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
		btn_SpielerprofilSchliessen.setBounds(75, 250, 100, 30);
		btn_SpielerprofilSchliessen.setText("Profil schlieﬂen");
		btn_SpielerprofilSchliessen.setMargin(new Insets(2, 2, 2, 2));
		cp.add(btn_SpielerprofilSchliessen);
		
			
		//Labels
		lab_SpielerName.setBounds(70, 10, 100, 20);
		lab_SpielerName.setText(Spieler.getSpielerName());
		cp.add(lab_SpielerName);
		
		lab_SpieleGespielt.setBounds(10, 60, 200, 20);
		lab_SpieleGespielt.setText("Spiele gespielt: "+Integer.toString(Spieler.getSpieleGespielt()));
		cp.add(lab_SpieleGespielt);
		
		lab_SpieleGewonnen.setBounds(10, 90, 200, 20);
		lab_SpieleGewonnen.setText("Spiele gewonnen: "+Integer.toString(Spieler.getSpieleGewonnen()));
		cp.add(lab_SpieleGewonnen);
		
		lab_SpieleGewonnenProzent.setBounds(10, 120, 200, 20);
		lab_SpieleGewonnenProzent.setText("Spiele gewonnen in %: "+Spieler.getSpieleGewonnenProzent()+ "%");
		cp.add(lab_SpieleGewonnenProzent);
		
		lab_MinenGefunden.setBounds(10, 150, 200, 20);
		lab_MinenGefunden.setText("Minen gefunden: "+Integer.toString(Spieler.getMinenGefunden()));
		cp.add(lab_MinenGefunden);
		
		lab_ZeitGesamt.setBounds(10, 180, 200, 20);
		lab_ZeitGesamt.setText("Spielzeit gesamt: to do");
		cp.add(lab_ZeitGesamt);
		
		lab_ZeitSchnellstesSpiel.setBounds(10, 210, 200, 20);
		lab_ZeitSchnellstesSpiel.setText("Schnellstes Spiel: to do");
		cp.add(lab_ZeitSchnellstesSpiel);
		
		lab_profilBild.setBounds(10, 10, 40, 40);
		lab_profilBild.setIcon(new ImageIcon(getClass().getResource("img/profil.jpg")));
		cp.add(lab_profilBild);
		
		
		btn_SpielerprofilSchliessen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
			dispose();
			}
		});
		
	}
	
	
	
	public static void main(String[] args) {	
		new GUI_Spielerprofil();
	}
	

}