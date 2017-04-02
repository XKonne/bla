package bla;

import java.awt.Container;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;


import javax.swing.*;

public class GUI_Start extends JFrame implements ActionListener {
	//Objekte
	Spieler spielerEins = new Spieler();
	
	//Varialblen
	static boolean EingabeRichtig = false;
	
	//Buttons
	private JButton btn_ModusLeicht = new JButton();
	private JButton btn_ModusMittel = new JButton();
	private JButton btn_ModusSchwer = new JButton();
	private JButton btn_ModusBenutzer = new JButton();
	
	private JButton btn_SpielerAnlegen = new JButton();
	private JButton btn_SpielerProfil = new JButton();
	
	//Labels
	private JLabel lab_SpielerName = new JLabel();
	
	//Textfelder
	private JTextField txt_SpielerName = new JTextField();
	
	// Zufallszahlen
	Random rand = new Random();
	
    // Menüleiste
	JMenuBar menueLeiste = new JMenuBar();
	
	// Menüleiste Elemente
	JMenu men_spiel = new JMenu("Spiel");
	JMenu men_ueber = new JMenu("Hilfe");
	
	// Sub > Spiel
	JMenuItem men_spiel_neu = new JMenuItem("Neues Spiel /disabled");
	JMenuItem men_spiel_beenden = new JMenuItem("Beenden");
	
	// Sub > Hilfe
	JMenuItem men_ueber_anleitung = new JMenuItem("Anleitung /to-do");
	JMenuItem men_ueber_version = new JMenuItem("Über 'Seawolf'");	

	public GUI_Start()  {
		// TODO Auto-generated constructor stub
		//JFrame fenster = new JFrame("Ihr JFrame");
		setSize(585, 280);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		JTabbedPane tabLeiste = new JTabbedPane();
		JPanel panel = new JPanel();
		panel.setLayout(null);
		tabLeiste.addTab("Einzelspieler", panel);

		JPanel panel2 = new JPanel();
		panel2.setLayout(null);
		panel2.add(new JLabel("Dauert noch eine weile..."));
		tabLeiste.addTab("Mehrspieler", panel2);

		add(tabLeiste);
		setTitle("Projekt 'Seawolf'");
		setResizable(false);
		setVisible(true);
		
		
		// Untermenü > ActionListener-Aufruf
		// Spiel
		men_spiel_neu.addActionListener(this);
		men_spiel_beenden.addActionListener(this);

		// Hilfe
		men_ueber_anleitung.addActionListener(this);
		men_ueber_version.addActionListener(this);
		
		
		//Menüleiste
		//Menüleiste hinzufügen
		add(menueLeiste);
		//Hauptmenü-Punkte hinzufügen
		menueLeiste.add(men_spiel);
		menueLeiste.add(men_ueber);
		//Untermenü-Punkte hinzufügen
		men_spiel.add(men_spiel_neu);
		men_spiel.add(men_spiel_beenden);
		men_ueber.add(men_ueber_anleitung);
		men_ueber.add(men_ueber_version);
		//MenueLeiste dem JFrame zuordnen
		setJMenuBar(menueLeiste);
		
		
		
		// GUI Elemente
		btn_SpielerAnlegen.setBounds(435, 10, 125, 28);
		btn_SpielerAnlegen.setText("Spieler Anlegen");
		btn_SpielerAnlegen.setMargin(new Insets(2, 2, 2, 2));
		btn_SpielerAnlegen.setEnabled(true);
		panel.add(btn_SpielerAnlegen);
		btn_SpielerAnlegen.addActionListener(this);
		
		btn_SpielerProfil.setIcon(new ImageIcon(getClass().getResource("img/profil.jpg")));
		btn_SpielerProfil.setBounds(350, 5, 40, 40);
		btn_SpielerProfil.setMargin(new Insets(2, 2, 2, 2));
		btn_SpielerProfil.setEnabled(false);
		btn_SpielerProfil.setVisible(false);
		panel.add(btn_SpielerProfil);
		btn_SpielerProfil.addActionListener(this);
		
		// Textfelder
		txt_SpielerName.setBounds(275, 10, 150, 30);
		txt_SpielerName.setVisible(true);
		panel.add(txt_SpielerName);
		
		Border border = LineBorder.createGrayLineBorder();
		lab_SpielerName.setBounds(400, 5, 160, 40);
		lab_SpielerName.setFont(new Font("Dialog", Font.PLAIN, 35));
		lab_SpielerName.setBorder(border);
		lab_SpielerName.setVisible(false);
		panel.add(lab_SpielerName);
		
		// Buttons
		btn_ModusLeicht.setBounds(15, 60, 125, 125);
		btn_ModusLeicht.setIcon(new ImageIcon(getClass().getResource("img/modus_leicht.png")));
		btn_ModusLeicht.setMargin(new Insets(0, 0, 0, 0));
		btn_ModusLeicht.setEnabled(false);
		panel.add(btn_ModusLeicht);
		btn_ModusLeicht.addActionListener(this);
		
		btn_ModusMittel.setBounds(155, 60, 125, 125);
		btn_ModusMittel.setIcon(new ImageIcon(getClass().getResource("img/modus_mittel.png")));
		btn_ModusMittel.setMargin(new Insets(0, 0, 0, 0));
		btn_ModusMittel.setEnabled(false);
		panel.add(btn_ModusMittel);
		btn_ModusMittel.addActionListener(this);
		
		btn_ModusSchwer.setBounds(295, 60, 125, 125);
		btn_ModusSchwer.setIcon(new ImageIcon(getClass().getResource("img/modus_schwer.png")));
		btn_ModusSchwer.setMargin(new Insets(0, 0, 0, 0));
		btn_ModusSchwer.setEnabled(false);
		panel.add(btn_ModusSchwer);
		btn_ModusSchwer.addActionListener(this);
		
		btn_ModusBenutzer.setBounds(435, 60, 125, 125);
		btn_ModusBenutzer.setIcon(new ImageIcon(getClass().getResource("img/modus_benutzer.png")));
		btn_ModusBenutzer.setMargin(new Insets(0, 0, 0, 0));
		btn_ModusBenutzer.setEnabled(false);
		panel.add(btn_ModusBenutzer);
		btn_ModusBenutzer.addActionListener(this);
		
		panel.repaint();
	}
	
	public void actionPerformed(ActionEvent object) {
		//Linksklick Menüleiste
		if (object.getSource() == men_spiel_neu) {
			//do nothing
		}
		if (object.getSource() == men_spiel_beenden) {
			setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		}
		if (object.getSource() == men_ueber_anleitung) {
			//do nothing
		}
		if (object.getSource() == men_ueber_version) {
			JOptionPane.showMessageDialog(null, "Version: "+Test.versiont);
		}
		
		//Linksklick Buttons
		if (object.getSource() == btn_SpielerAnlegen) {
			
			// Name einlesen
			spielerEins.setSpielerName(txt_SpielerName.getText());
			// Es wurde kein Spielername eingegeben > Zufallsname
			if (spielerEins.getSpielerName().length() == 0 || spielerEins.getSpielerName() == "") {
				spielerEins.setSpielerName("Gast" + Integer.toString(rand.nextInt(99) + 1));
				EingabeRichtig = true;
			}
			// Spielername hat nur 1 oder 2 Zeichen. Fehlermeldung.
			if (spielerEins.getSpielerName().length() == 1 || spielerEins.getSpielerName().length() == 2) {
				JOptionPane.showMessageDialog(null,
						"Ein Spielername muss mindestens aus 3 Zeichen bestehen. Lass das Feld leer für einen Zufallsnamen.");
			}
			// Spielername hat 3 oder mehr Zeichen. Das Spiel kann gestartet werden.
			if (spielerEins.getSpielerName().length() >= 3) {
				EingabeRichtig = true;
			}
			
			txt_SpielerName.setVisible(false);
			lab_SpielerName.setVisible(true);
			lab_SpielerName.setText(spielerEins.getSpielerName());
			btn_SpielerProfil.setEnabled(true);
			btn_SpielerProfil.setVisible(true);
			
			btn_ModusLeicht.setEnabled(true);
			btn_ModusMittel.setEnabled(true);
			btn_ModusSchwer.setEnabled(true);
			btn_ModusBenutzer.setEnabled(true);
			
			btn_SpielerAnlegen.setVisible(false);
		}
		if (object.getSource() == btn_SpielerProfil) {
			GUI_Spielerprofil profil = new GUI_Spielerprofil(spielerEins);
		}
		if (object.getSource() == btn_ModusLeicht) {
			//Rufe in Spiel > setSpielModus auf und übergebe Spielfeld-Daten
			Spiel.setSpielModus(8, 8, 10, "Leicht", spielerEins);
			dispose();
		}
		if (object.getSource() == btn_ModusMittel) {
			Spiel.setSpielModus(16, 16, 40, "Mittel", spielerEins);
			dispose();
		}
		if (object.getSource() == btn_ModusSchwer) {
			Spiel.setSpielModus(24, 16, 60, "Schwer", spielerEins);
			dispose();
		}
		if (object.getSource() == btn_ModusBenutzer) {
			Spiel.setSpielModus(30, 24, 99, "Benutzer", spielerEins);
			dispose();
		}
	}

	public static Boolean SpielerAngelegt() {
		return EingabeRichtig;
		
	}
}
