package bla;

import java.awt.event.*;
import javax.swing.*;


public class GUI_AddMenubar extends JMenuBar implements ActionListener {
	
	JMenu spiel, aktuellesSpiel, spieler, hilfe;
	JMenuItem neuesSpiel, akt_nochmalSpielen, akt_neueRunde, spielSpeichern, spielLaden, spielEinstellung, beenden;
	JMenuItem profil, optionen;
	JMenuItem anleitung, ueber;
	
	JSeparator sep1 = new JSeparator();
	JSeparator sep2 = new JSeparator();
	JSeparator sep3 = new JSeparator();
	JSeparator sep4 = new JSeparator();
	JSeparator sep5 = new JSeparator();

	public GUI_AddMenubar() {
		super();
		
		// Menu > Spiel
		spiel = new JMenu("Spiel");
		// "S" unterstreichen
		//spiel.setMnemonic(KeyEvent.VK_S);
		// Info
		//spiel.getAccessibleContext().setAccessibleDescription("Bla bla blu");
		this.add(spiel);
		
		// Menu > Spiel > Sub
		neuesSpiel = new JMenuItem("Neues Spiel");
		neuesSpiel.setEnabled(false);
		spiel.add(neuesSpiel);
		neuesSpiel.addActionListener(this);
		
		spiel.add(sep1);
		
		aktuellesSpiel  = new JMenu("Aktuelles Spiel");
		spiel.add(aktuellesSpiel);
		// Menu > Spiel > Aktuelles Spiel > Sub
			akt_nochmalSpielen  = new JMenuItem("Nochmal spielen");
			akt_nochmalSpielen.setEnabled(false);
			aktuellesSpiel.add(akt_nochmalSpielen);
			akt_nochmalSpielen.addActionListener(this);
			akt_neueRunde  = new JMenuItem("Neue Runde");
			akt_neueRunde.setEnabled(false);
			aktuellesSpiel.add(akt_neueRunde);
			akt_neueRunde.addActionListener(this);
		
		spiel.add(sep2);
		
		spielLaden = new JMenuItem("Spiel laden");
		spielLaden.setEnabled(false);
		spiel.add(spielLaden);
		spielLaden.addActionListener(this);
		spielSpeichern = new JMenuItem("Spiel speichern");
		spielSpeichern.setEnabled(false);
		spiel.add(spielSpeichern);
		spielSpeichern.addActionListener(this);
		
		spiel.add(sep3);
		
		spielEinstellung = new JMenuItem("Einstellungen");
		spielEinstellung.setEnabled(false);
		spiel.add(spielEinstellung);
		spielEinstellung.addActionListener(this);
		
		spiel.add(sep4);
		
		beenden = new JMenuItem("Beenden");
		spiel.add(beenden);
		beenden.addActionListener(this);
//		
////		// Menüitem
////		close = new JMenuItem("Beenden", KeyEvent.VK_B);
////		// Darstellung Tastenkürzel
////		close.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.ALT_MASK));
////		close.getAccessibleContext().setAccessibleDescription("Beende");
////		spiel.add(close);
//		
		// Menu > Spieler
		spieler = new JMenu("Spieler");
		//spieler.setMnemonic(KeyEvent.VK_HELP);
		//spieler.getAccessibleContext().setAccessibleDescription("Nicht implementiert!");
		this.add(spieler);
		
		// Menu > Spieler > Sub
		profil = new JMenuItem("Profil");
		profil.setEnabled(false);
		spieler.add(profil);
		profil.addActionListener(this);
		optionen  = new JMenuItem("Optionen");
		optionen.setEnabled(false);
		spieler.add(optionen);
		optionen.addActionListener(this);
		
		// Menu > Hilfe
		hilfe = new JMenu("Hilfe");
		//hilfe.setMnemonic(KeyEvent.VK_HELP);
		//hilfe.getAccessibleContext().setAccessibleDescription("Nicht implementiert!");
		this.add(hilfe);
		
		// Menu > Hilfe > Sub
		anleitung = new JMenuItem("Anleitung");
		anleitung.setEnabled(false);
		hilfe.add(anleitung);
		anleitung.addActionListener(this);
		
		hilfe.add(sep5);
		
		ueber  = new JMenuItem("Über");
		hilfe.add(ueber);
		ueber.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent object) {
		
		if (object.getSource() == neuesSpiel) {
			// do nothing
		}
		if (object.getSource() == akt_nochmalSpielen) {
			// do nothing
		}
		if (object.getSource() == akt_neueRunde) {
			//TODO folgender Befehl geht nicht weil nicht static.
			//GUI_Spielfeld.neueRunde();
		}
		if (object.getSource() == spielLaden) {
			// do nothing
		}
		if (object.getSource() == spielSpeichern) {
			// do nothing
		}
		if (object.getSource() == spielEinstellung) {
			// do nothing
		}
		if (object.getSource() == beenden) {
			System.exit(0);
		}
		
		// Spieler
		if (object.getSource() == profil) {
			// do nothing
		}
		if (object.getSource() == optionen) {
			// do nothing
		}
		
		// Hilfe
		if (object.getSource() == anleitung) {
			// do nothing
		}
		if (object.getSource() == ueber) {
			JOptionPane.showMessageDialog(null, "Version: " + GUI_Spielfeld.versiont);
		}
	}

}

