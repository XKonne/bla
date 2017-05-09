package bla;

import java.awt.event.KeyEvent;

import javax.swing.*;

@SuppressWarnings("serial")
public class GUI_AddMenubar extends JMenuBar {

	public GUI_AddMenubar() {
		setupGUI();
	}
	
	public void neueRunde() {
		 ObjectHandler.getGui_Spielfeld().spielNeueRunde();
	}
	
	public void wiederholeRunde() {
		 ObjectHandler.getGui_Spielfeld().spielNochmal();
	}
	
	private void setupGUI() {
		
		JMenu jm_Spiel, jm_AktuellesSpiel, jm_Spieler, jm_Hilfe;
		JMenuItem jmi_neuesSpiel, jmi_akt_nochmalSpielen, jmi_akt_neueRunde, jmi_spielSpeichern, jmi_spielLaden,
				jmi_spielEinstellung, jmi_beenden;
		JMenuItem jmi_profil, jmi_optionen;
		JMenuItem jmi_anleitung, jmi_ueber;

		JSeparator sep1 = new JSeparator();
		JSeparator sep2 = new JSeparator();
		JSeparator sep3 = new JSeparator();
		JSeparator sep4 = new JSeparator();
		JSeparator sep5 = new JSeparator();

		// Menu > Spiel
		jm_Spiel = new JMenu("Spiel");
		// "S" unterstreichen
		 jm_Spiel.setMnemonic(KeyEvent.VK_S);
		// Info
		 jm_Spiel.getAccessibleContext().setAccessibleDescription("Bla blablu");
		this.add(jm_Spiel);

		// Menu > Spiel > Sub
		jmi_neuesSpiel = new JMenuItem("Neues Spiel");
		jmi_neuesSpiel.setEnabled(false);
		jm_Spiel.add(jmi_neuesSpiel);
		jm_Spiel.add(sep1);

		jm_AktuellesSpiel = new JMenu("Aktuelles Spiel");
		
		jm_Spiel.add(jm_AktuellesSpiel);
		// Menu > Spiel > Aktuelles Spiel > Sub1
		jmi_akt_nochmalSpielen = new JMenuItem("Nochmal spielen");
		jmi_akt_nochmalSpielen.setEnabled(true);
		jm_AktuellesSpiel.add(jmi_akt_nochmalSpielen);
		jmi_akt_nochmalSpielen.addActionListener(e -> wiederholeRunde());
		// Menu > Spiel > Aktuelles Spiel > Sub2
		jmi_akt_neueRunde = new JMenuItem("Neue Runde");
		jmi_akt_neueRunde.setEnabled(true);
		jm_AktuellesSpiel.add(jmi_akt_neueRunde);
		jmi_akt_neueRunde.addActionListener(e -> neueRunde());

		jm_Spiel.add(sep2);

		jmi_spielLaden = new JMenuItem("Spiel laden");
		jmi_spielLaden.setEnabled(false);
		jm_Spiel.add(jmi_spielLaden);
		jmi_spielSpeichern = new JMenuItem("Spiel speichern");
		jmi_spielSpeichern.setEnabled(false);
		jm_Spiel.add(jmi_spielSpeichern);

		jm_Spiel.add(sep3);

		jmi_spielEinstellung = new JMenuItem("Einstellungen");
		jmi_spielEinstellung.setEnabled(false);
		jm_Spiel.add(jmi_spielEinstellung);

		jm_Spiel.add(sep4);

		jmi_beenden = new JMenuItem("Beenden");
		jm_Spiel.add(jmi_beenden);
		jmi_beenden.addActionListener(e -> System.exit(0));
		//
		//// // Menüitem
		//// close = new JMenuItem("Beenden", KeyEvent.VK_B);
		//// // Darstellung Tastenkürzel
		//// close.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B,
		// ActionEvent.ALT_MASK));
		//// close.getAccessibleContext().setAccessibleDescription("Beende");
		//// spiel.add(close);
		//
		// Menu > Spieler
		jm_Spieler = new JMenu("Spieler");
		// spieler.setMnemonic(KeyEvent.VK_HELP);
		// spieler.getAccessibleContext().setAccessibleDescription("Nicht
		// implementiert!");
		this.add(jm_Spieler);

		// Menu > Spieler > Sub
		jmi_profil = new JMenuItem("Profil");
		jmi_profil.setEnabled(true);
		jm_Spieler.add(jmi_profil);
		jmi_profil.addActionListener(e -> ObjectHandler.createGui_Spielerprofil());
		jmi_optionen = new JMenuItem("Optionen");
		jmi_optionen.setEnabled(false);
		jm_Spieler.add(jmi_optionen);

		// Menu > Hilfe
		jm_Hilfe = new JMenu("Hilfe");
		// hilfe.setMnemonic(KeyEvent.VK_HELP);
		// hilfe.getAccessibleContext().setAccessibleDescription("Nicht
		// implementiert!");
		this.add(jm_Hilfe);

		// Menu > Hilfe > Sub
		jmi_anleitung = new JMenuItem("Anleitung");
		jmi_anleitung.setEnabled(false);
		jm_Hilfe.add(jmi_anleitung);

		jm_Hilfe.add(sep5);

		jmi_ueber = new JMenuItem("Über");
		jm_Hilfe.add(jmi_ueber);
		jmi_ueber.addActionListener(e -> JOptionPane.showMessageDialog(null, "Version: " + GUI_Spielfeld.versiont));

	}

}
