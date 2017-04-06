package bla;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Scrollbar;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import javax.swing.*;

public class GUI_Start extends JFrame implements ActionListener {
	// Objekte
	Spieler spielerEins = new Spieler();
	
    // Frames
	JFrame gui_Start = new JFrame();
	JFrame gui_ModusBenutzer = new JFrame();
	Double maxMinen;
	// Varialblen
	static boolean EingabeRichtig = false;

	// Buttons
	private JButton btn_ModusLeicht = new JButton();
	private JButton btn_ModusMittel = new JButton();
	private JButton btn_ModusSchwer = new JButton();
	private JButton btn_ModusBenutzer = new JButton();

	private JButton btn_SpielerAnlegen = new JButton();
	private JButton btn_SpielerProfil = new JButton();
	
	// Buttons - GUI_ModusBenuzrt
	private JButton btn_SpielZurueck = new JButton();
	private JButton btn_SpielStarten = new JButton();
	private JButton btn_SpielZufall = new JButton();

	// Labels
	private JLabel lab_SpielerName = new JLabel();
	
	// Labels - GUI_ModusBenutzer
	private JLabel lab_mb_SpaltenZahl = new JLabel();
	private JLabel lab_mb_ZeilenZahl = new JLabel();
	private JLabel lab_mb_MinenZahl = new JLabel();
	private JLabel lab_mb_Spalten = new JLabel();
	private JLabel lab_mb_Zeilen = new JLabel();
	private JLabel lab_mb_Minen = new JLabel();
	private JLabel lab_mb_Felder = new JLabel();

	// Textfelder
	private JTextField txt_SpielerName = new JTextField();

	// Zufallszahlen
	Random rand = new Random();
	Random randSpalte = new Random();
	Random randZeile = new Random();
	Random randMine = new Random();

	// Scrollbar - GUI_ModusBenutzer
	private JScrollBar sb_mb_spalten = new JScrollBar();
	private JScrollBar sb_mb_zeilen = new JScrollBar();
	private JScrollBar sb_mb_minen = new JScrollBar();
	
	//  Menüleiste
	JMenuBar menueLeiste = new JMenuBar();

	//  Menüleiste Elemente
	JMenu men_spiel = new JMenu("Spiel");
	JMenu men_ueber = new JMenu("Hilfe");

	// Sub > Spiel
	JMenuItem men_spiel_neu = new JMenuItem("Neues Spiel /disabled");
	JMenuItem men_spiel_beenden = new JMenuItem("Beenden");

	// Sub > Hilfe
	JMenuItem men_ueber_anleitung = new JMenuItem("Anleitung /to-do");
	JMenuItem men_ueber_version = new JMenuItem("Über 'Seawolf'");

	public GUI_Start() {
		// TODO Auto-generated constructor stub
		// JFrame fenster = new JFrame("Ihr JFrame");
		gui_Start.setSize(585, 280);
		gui_Start.setLocationRelativeTo(null);
		gui_Start.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		JTabbedPane tabLeiste = new JTabbedPane();
		JPanel panel = new JPanel();
		panel.setLayout(null);
		tabLeiste.addTab("Einzelspieler", panel);

		JPanel panel2 = new JPanel();
		panel2.setLayout(null);
		panel2.add(new JLabel("Dauert noch eine weile..."));
		tabLeiste.addTab("Mehrspieler", panel2);

		gui_Start.add(tabLeiste);
		gui_Start.setTitle("Projekt 'Seawolf'");
		gui_Start.setResizable(false);
		gui_Start.setVisible(true);

		// Untermenü > ActionListener-Aufruf
		// Spiel
		men_spiel_neu.addActionListener(this);
		men_spiel_beenden.addActionListener(this);

		// Hilfe
		men_ueber_anleitung.addActionListener(this);
		men_ueber_version.addActionListener(this);

		// Menüleiste
		// Menüleiste hinzufügen
		gui_Start.add(menueLeiste);
		// Hauptmenü-Punkte hinzufügen
		menueLeiste.add(men_spiel);
		menueLeiste.add(men_ueber);
		// Untermenü-Punkte hinzufügen
		men_spiel.add(men_spiel_neu);
		men_spiel.add(men_spiel_beenden);
		men_ueber.add(men_ueber_anleitung);
		men_ueber.add(men_ueber_version);
		// MenueLeiste dem JFrame zuordnen
		gui_Start.setJMenuBar(menueLeiste);

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
	
	
	public void GUI_ModusBenutzer() {
		{
		// Lokale Variablen
			
			
		// Frame-Initialisierung
		gui_ModusBenutzer.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		gui_ModusBenutzer.setSize(347, 190);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (d.width - getSize().width) / 2;
		int y = (d.height - getSize().height) / 2;
		gui_ModusBenutzer.setLocation(x, y);
		gui_ModusBenutzer.setTitle("Benutzerdefinierter Spielmodus");
		gui_ModusBenutzer.setResizable(false);
		Container cp2 = gui_ModusBenutzer.getContentPane();
		cp2.setLayout(null);
		
		// Frame-Elemente
		// Scrollbar
	    sb_mb_spalten.setBounds(60, 10, 240, 20);
	    sb_mb_spalten.setOrientation(Scrollbar.HORIZONTAL);
	    sb_mb_spalten.setBlockIncrement(1);
	    sb_mb_spalten.setUnitIncrement(1);
	    sb_mb_spalten.setMinimum(8);
	    sb_mb_spalten.setMaximum(30+10);
	    sb_mb_spalten.setValue(8);
	    cp2.add(sb_mb_spalten);
	    
	    sb_mb_spalten.addAdjustmentListener(new AdjustmentListener() {
	          @Override
	          public void adjustmentValueChanged(AdjustmentEvent e) {
	        	  lab_mb_SpaltenZahl.setText(Integer.toString(sb_mb_spalten.getValue()));
	      		  lab_mb_Felder.setText("Felder: "+sb_mb_spalten.getValue()*sb_mb_zeilen.getValue());
	      	   maxMinen = (sb_mb_spalten.getValue()*sb_mb_zeilen.getValue())*0.2;
	      	   sb_mb_minen.setMaximum(maxMinen.intValue());
	             }
	       });
		
	    sb_mb_zeilen.setBounds(60, 40, 240, 20);
	    sb_mb_zeilen.setOrientation(Scrollbar.HORIZONTAL);
	    sb_mb_zeilen.setBlockIncrement(1);
	    sb_mb_zeilen.setUnitIncrement(1);
	    sb_mb_zeilen.setMinimum(8);
	    sb_mb_zeilen.setMaximum(24+10);
	    sb_mb_zeilen.setValue(8);
	    cp2.add(sb_mb_zeilen);
	    
	    sb_mb_zeilen.addAdjustmentListener(new AdjustmentListener() {
	          @Override
	          public void adjustmentValueChanged(AdjustmentEvent e) {
	        	  lab_mb_ZeilenZahl.setText(Integer.toString(sb_mb_zeilen.getValue()));
	      		  lab_mb_Felder.setText("Felder: "+sb_mb_spalten.getValue()*sb_mb_zeilen.getValue());
	      	    maxMinen = (sb_mb_spalten.getValue()*sb_mb_zeilen.getValue())*0.2;
	      	    sb_mb_minen.setMaximum(maxMinen.intValue());
	             }
	       });

	    maxMinen = (sb_mb_spalten.getValue()*sb_mb_zeilen.getValue())*0.2;
	    
	    sb_mb_minen.setBounds(60, 80, 240, 20);
	    sb_mb_minen.setOrientation(Scrollbar.HORIZONTAL);
	    sb_mb_minen.setMinimum(10);
	    sb_mb_minen.setMaximum(99);
	    sb_mb_minen.setValue(10);
	    //sb_mb_minen.setVisibleAmount(100);
	    cp2.add(sb_mb_minen);
	    
	    sb_mb_minen.addAdjustmentListener(new AdjustmentListener() {
	          @Override
	          public void adjustmentValueChanged(AdjustmentEvent e) {
	        	  lab_mb_MinenZahl.setText(Integer.toString(sb_mb_minen.getValue()));
	             }
	       });
	    
	    // Labels
		lab_mb_Spalten.setBounds(10, 10, 50, 20);
		lab_mb_Spalten.setText("Spalten: ");
		cp2.add(lab_mb_Spalten);
	    
		lab_mb_Zeilen.setBounds(10, 40, 50, 20);
		lab_mb_Zeilen.setText("Zeilen: ");
		cp2.add(lab_mb_Zeilen);
				
		lab_mb_Minen.setBounds(10, 80, 50, 20);
		lab_mb_Minen.setText("Minen: ");
		cp2.add(lab_mb_Minen);
		
		lab_mb_Felder.setBounds(269, 60, 70, 20);
		lab_mb_Felder.setText("Felder: "+sb_mb_spalten.getValue()*sb_mb_zeilen.getValue());
		cp2.add(lab_mb_Felder);
				
		lab_mb_SpaltenZahl.setBounds(310, 10, 20, 20);
		lab_mb_SpaltenZahl.setText(Integer.toString(sb_mb_spalten.getValue()));
		cp2.add(lab_mb_SpaltenZahl);
	    
		lab_mb_ZeilenZahl.setBounds(310, 40, 20, 20);
		lab_mb_ZeilenZahl.setText(Integer.toString(sb_mb_zeilen.getValue()));
		cp2.add(lab_mb_ZeilenZahl);
				
		lab_mb_MinenZahl.setBounds(310, 80, 40, 20);
		lab_mb_MinenZahl.setText(Integer.toString(sb_mb_minen.getValue()));
		cp2.add(lab_mb_MinenZahl);
	    
	    // Buttons
		btn_SpielZurueck.setBounds(10, 120, 90, 30);
		btn_SpielZurueck.setText("Zurück");
		btn_SpielZurueck.setMargin(new Insets(2, 2, 2, 2));
		cp2.add(btn_SpielZurueck);
		btn_SpielZurueck.addActionListener(this);
		
		btn_SpielStarten.setBounds(110, 120, 90, 30);
		btn_SpielStarten.setText("Spiel starten");
		btn_SpielStarten.setMargin(new Insets(2, 2, 2, 2));
		cp2.add(btn_SpielStarten);
		btn_SpielStarten.addActionListener(this);
		
		btn_SpielZufall.setBounds(242, 120, 90, 30);
		btn_SpielZufall.setText("Zufallswerte");
		btn_SpielZufall.setMargin(new Insets(2, 2, 2, 2));
		cp2.add(btn_SpielZufall);
		btn_SpielZufall.addActionListener(this);
		
		// Button Linksklick Methoden
		btn_SpielZurueck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				// Schließe GUI_SpielEnde und zeige Spielfeld nochmal an
				gui_ModusBenutzer.dispose();
			}
		});
		
		btn_SpielStarten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {

			}
		});
		
		btn_SpielZufall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {

			}
		});
						
		gui_ModusBenutzer.setVisible(true);
	}
	}
	

	public void actionPerformed(ActionEvent object) {
		// Linksklick Menüleiste
		if (object.getSource() == men_spiel_neu) {
			// do nothing
		}
		if (object.getSource() == men_spiel_beenden) {
			System.exit(0);
		}
		if (object.getSource() == men_ueber_anleitung) {
			// do nothing
		}
		if (object.getSource() == men_ueber_version) {
			JOptionPane.showMessageDialog(null, "Version: " + Test.versiont);
		}

		// Linksklick Buttons
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
			// Spielername hat 3 oder mehr Zeichen. Das Spiel kann gestartet
			// werden.
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
			// Rufe in Spiel > setSpielModus auf und übergebe Spielfeld-Daten
			Spiel.setSpielModus(8, 8, 10, "Leicht", spielerEins);
			gui_Start.dispose();
		}
		if (object.getSource() == btn_ModusMittel) {
			Spiel.setSpielModus(16, 16, 40, "Mittel", spielerEins);
			gui_Start.dispose();
		}
		if (object.getSource() == btn_ModusSchwer) {
			Spiel.setSpielModus(24, 16, 60, "Schwer", spielerEins);
			gui_Start.dispose();
		}
		if (object.getSource() == btn_ModusBenutzer) {
			GUI_ModusBenutzer();
		}
		
		// GUI_modusBenutzer
		if (object.getSource() == btn_SpielZurueck) {
			gui_ModusBenutzer.dispose();
		}
		if (object.getSource() == btn_SpielStarten) {
			Spiel.setSpielModus(sb_mb_spalten.getValue(), sb_mb_zeilen.getValue(), sb_mb_minen.getValue(), "Benutzer", spielerEins);
			gui_ModusBenutzer.dispose();
			gui_Start.dispose();
		}
		if (object.getSource() == btn_SpielZufall) {
			int spaltenZufall = randSpalte.nextInt(30);
			int zeilenZufall = randZeile.nextInt(24);
			int minenZufall = randMine.nextInt(99);
			
			sb_mb_spalten.setValue(spaltenZufall);
			sb_mb_zeilen.setValue(zeilenZufall);
			sb_mb_minen.setValue(minenZufall);
			
			lab_mb_SpaltenZahl.setText(Integer.toString(sb_mb_spalten.getValue()));
			lab_mb_ZeilenZahl.setText(Integer.toString(sb_mb_zeilen.getValue()));
			lab_mb_MinenZahl.setText(Integer.toString(sb_mb_minen.getValue()));		
		}
	}

	public static Boolean SpielerAngelegt() {
		return EingabeRichtig;
	}
	
}
