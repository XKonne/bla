package bla;

import java.awt.*;
import java.awt.event.*;
import java.lang.management.ClassLoadingMXBean;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.*;
import java.util.Random;

/**
 * Spielprojekt "Seawolf" GameApp-Name <not set/actually nameless>
 *
 * @version A.2.15 vom 01.04.2017
 * @author XKonne
 * @author p0sE-Git
 */


public class Test extends JFrame {
	

	private static Spiel spiel;
	
	// Variablen
	Spieler spielerEins = new Spieler();


	
	// String
	static String versiont = "A.2.15";
	// String Spielername="";

	// Boolean
	static boolean Spielfeldgesperrt = true;
	static boolean EingabeRichtig = false;

	// Long
	static long zeittmp;

	// Zufallszahlen
	Random rand = new Random();

	// Buttons
	private JButton btn_SpielBeenden = new JButton();
	private JButton btn_SpielerProfil = new JButton();
	private JButton btn_SpielInformation = new JButton();
	private JButton btn_SpielNeustart = new JButton();
	private JButton btn_SpielNeueRunde = new JButton();
	private JButton btn_SpielReset = new JButton();
	private JButton btn_Spielstarten = new JButton();

	// Container
	private Container cp = getContentPane();

	// Labels
	private JLabel lab_Spielername = new JLabel();
	private JLabel lab_Spielmodus = new JLabel();
	private JLabel lab_Version = new JLabel();
	private static JLabel lab_MinenRichtig = new JLabel();
	private static JLabel lab_Restminen = new JLabel();

	// TextField
	private JTextField txt_SpielerName = new JTextField();

	// Arrays
	// 0=Spielfeld noch nicht geklickt --- 1=Spielfeld bereits 1x gedrückt
	// 5=Linksklick. Spielfeld aufgedeckt. Keine weiteren Aktion möglich
	static int[] Spielfeldgeklickt = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }; // i=1..16
	static JButton[] buttons = new JButton[16];
	// MFeld = Minenfeld Testspielfeld 4x4
	// 4x4-Raster
	// 1 2 3 4 <=> - - 1 M
	// 5 6 7 8 <=> 1 2 3 2
	// 9 10 11 12 <=> 1 M M 1
	// 13 14 15 16 <=> 1 2 2 1
    static String a_btnText[] = { "-", "-", "1", "M", "1", "2", "3", "2", "1", "M", "M", "1", "1", "2", "2", "1" };
    // Ende Attribute

	public Test(Spiel spiel) {

		super();
		Test.spiel = spiel;

		setupGUI();
	}

	private void createSpielfeldButtons() {

		// TODO Button-Namen wirklich notwendig?
		String a_btnNames[] = { "MFeld1", "MFeld2", "MFeld3", "MFeld4", "MFeld5", "MFeld6", "MFeld7", "MFeld8",
				"MFeld9", "MFeld10", "MFeld11", "MFeld12", "MFeld13", "MFeld14", "MFeld15", "MFeld16" };

		// Buttons bauen
		for (int i = 0; i < buttons.length; i++) {

			buttons[i] = new JButton(a_btnNames[i]);
			buttons[i].setText(".");
			buttons[i].setMargin(new Insets(2, 2, 2, 2));
			buttons[i].setEnabled(false);

			MouseInput mouse = new MouseInput(i);
			buttons[i].addMouseListener(mouse);

			cp.add(buttons[i]);

		}

		setBoundsSpielfeldButtons();
	}

	private void createStartScreenButtons() {

		btn_SpielerProfil.setIcon(new ImageIcon(getClass().getResource("img/profil.jpg")));
		btn_SpielerProfil.setBounds(10, 5, 40, 40);
		btn_SpielerProfil.setVisible(false);
		btn_SpielerProfil.setMargin(new Insets(2, 2, 2, 2));
		cp.add(btn_SpielerProfil);

		btn_Spielstarten.setBounds(170, 10, 90, 28);
		btn_Spielstarten.setText("Spiel starten");
		btn_Spielstarten.setMargin(new Insets(2, 2, 2, 2));
		btn_Spielstarten.setEnabled(true);
		cp.add(btn_SpielNeustart);

		btn_SpielReset.setBounds(10, 230, 80, 30);
		btn_SpielReset.setText("Reset");
		btn_SpielReset.setMargin(new Insets(2, 2, 2, 2));
		btn_SpielReset.setEnabled(false);
		cp.add(btn_SpielReset);

		btn_SpielNeueRunde.setBounds(100, 230, 80, 30);
		btn_SpielNeueRunde.setText("Neue Runde");
		btn_SpielNeueRunde.setMargin(new Insets(2, 2, 2, 2));
		btn_SpielNeueRunde.setEnabled(false);
		cp.add(btn_SpielNeueRunde);

		btn_SpielNeustart.setBounds(190, 230, 80, 30);
		btn_SpielNeustart.setText("Nochmal");
		btn_SpielNeustart.setMargin(new Insets(2, 2, 2, 2));
		btn_SpielNeustart.setEnabled(false);
		cp.add(btn_Spielstarten);

		btn_SpielBeenden.setBounds(280, 230, 80, 30);
		btn_SpielBeenden.setText("Beenden");
		btn_SpielBeenden.setMargin(new Insets(2, 2, 2, 2));
		cp.add(btn_SpielBeenden);

		btn_SpielInformation.setBounds(320, 5, 40, 40);
		btn_SpielInformation.setText("?");
		btn_SpielInformation.setMargin(new Insets(2, 2, 2, 2));
		cp.add(btn_SpielInformation);

	}

	private void createStartScreenLabels() {

		Border border = LineBorder.createGrayLineBorder();

		lab_Spielername.setBounds(55, 5, 160, 40);
		lab_Spielername.setFont(new Font("Dialog", Font.PLAIN, 35));
		lab_Spielername.setBorder(border);
		lab_Spielername.setVisible(false);
		cp.add(lab_Spielername);

		lab_Spielmodus.setBounds(220, 0, 100, 20);
		lab_Spielmodus.setVisible(false);
		lab_Spielmodus.setFont(new Font("Dialog", Font.PLAIN, 11));
		lab_Spielmodus.setText("Modus: 4x4");
		cp.add(lab_Spielmodus);

		lab_Restminen.setBounds(220, 14, 100, 20);
		lab_Restminen.setVisible(false);
		lab_Restminen.setFont(new Font("Dialog", Font.PLAIN, 11));
		lab_Restminen.setText("Minen: " + Integer.toString(spiel.getRestMinen()));
		cp.add(lab_Restminen);

		lab_MinenRichtig.setBounds(220, 28, 100, 20);
		lab_MinenRichtig.setVisible(false);
		lab_MinenRichtig.setFont(new Font("Dialog", Font.PLAIN, 11));
		lab_MinenRichtig.setText("Mine Richtig " + Integer.toString(spiel.getMinenRichtig()));
		cp.add(lab_MinenRichtig);

		lab_Version.setBounds(325, 205, 100, 30);
		lab_Version.setVisible(true);
		lab_Version.setText(versiont);
		cp.add(lab_Version);

	}

	/**
	 * x-y-Wert setzen bei den Spielfeld-Buttons
	 */
	private void setBoundsSpielfeldButtons() {

		int top = 10;
		int left = 70;
		int lenght = 30;
		int height = 30;
		int zeile = 0;
		int spalte = 4; // Spalte setzt das Layout fest
		int abstand = 10;

		for (int i = 0; i < buttons.length; i++) {
			// x-Wert ergibt sich aus Button-Nr MOD spaltenwert (hier 4), damit
			// 4 Buttons hinereinander liegen
			buttons[i].setBounds(top + (i % spalte) * lenght + (i % spalte) * abstand,
					left + zeile * height + zeile * abstand, lenght, height);
			// Wenn eine Zeile fertig ist, erhöhe Zeilen-Wert (=y-Koordinate)
			if (i % spalte == spalte - 1) {
				zeile = zeile + 1;
			}
		}
	}

	// Anfang Methoden

	public void aufSiegpruefen(boolean mineGetroffen) {
		boolean winlose = false;
		// aufSiegpruefen wird nach _jedem_ Mausklick ausgeführt.

		// Minen-Markiert-Zähler und Minen-Richtig-Zähler aktualisieren
		lab_Restminen.setText("Minen: " + Integer.toString(spiel.getRestMinen()));
		lab_MinenRichtig.setText("Mine Richtig: " + Integer.toString(spiel.getMinenRichtig()));

		// Sieg-Bedingung pruefen
		if (spiel.getMinenRichtig() == spiel.getMinenGesamt() && spiel.getRestMinen() == 0
				&& Spielfeldgesperrt == false) {
			// sieg
			winlose = true;
			// zeitmesser stoppen und Wert in zeittmp speichern
			zeittmp = Spiel.zeitmessungEnde(zeittmp);

			// Ausgabe
			JOptionPane.showMessageDialog(null, "Spiel gewonnen! Spielzeit: " + zeittmp / 1000 + " Sekunden.");

			// Spieler Stats
			spielerEins.spielerAktualisieren(zeittmp, spiel.getMinenRichtig(), winlose);

			// Spielfeld deaktivieren
			setSpielfeldAnAus(false);
			Spielfeldgesperrt = true;

		}
		// mine aufgedeckt
		if (mineGetroffen == true) {
			winlose = false;
			// zeitmesser stoppen und Wert in zeittmp speichern
			zeittmp = Spiel.zeitmessungEnde(zeittmp);
			// Ausgabe
			JOptionPane.showMessageDialog(null, "Mine! Spiel Verloren. Spielzeit: " + zeittmp / 1000 + " Sekunden.");

			// Spieler Stats
			spielerEins.spielerAktualisieren(zeittmp, spiel.getMinenRichtig(), winlose);

			// Spielfeld deaktivieren
			setSpielfeldAnAus(false);
			Spielfeldgesperrt = true;
		}
	}

	public void btn_Spielstarten_ActionPerformed() {
		// Spieler-Objekt erstellen
		
		// Name einlesen
		spielerEins.setSpielerName(txt_SpielerName.getText());
		// Es wurde kein Spielername eingegeben > Zufallsname
		if (spielerEins.getSpielerName().length() == 0 || spielerEins.getSpielerName() == "") {
			spielerEins.setSpielerName("Rand" + Integer.toString(rand.nextInt(99) + 1));
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

		if (EingabeRichtig == true) {
			// Spielfeld aktivieren
			setSpielfeldAnAus(true);

			txt_SpielerName.setVisible(false);
			lab_Spielername.setText(spielerEins.getSpielerName());
			lab_Spielername.setVisible(true);
			lab_Spielmodus.setVisible(true);
			lab_Restminen.setVisible(true);
			lab_MinenRichtig.setVisible(true);

			btn_SpielNeustart.setEnabled(true);
			Spielfeldgesperrt = false;
			btn_SpielerProfil.setVisible(true);

			// Button zum Schluss deaktivieren
			btn_Spielstarten.setVisible(false);
			btn_SpielReset.setEnabled(true);

			zeittmp = Spiel.zeitmessungStart();
		}

	} // end of jButton1_ActionPerformed

	public void ButtonResetSpielfeld_ActionPerformed() {

		// TODO hier Quelltext einfÃ¼gen
		// Spielfeld Oberflaeche zu beginn
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].setText(".");
		}

		// Spielfeld deaktivieren
		setSpielfeldAnAus(false);

		Spielfeldgesperrt = true;
		// Spielfeldgeklickt Array zurücksetzen
		for (int i = 0; i < 17; i++) {
			Spielfeldgeklickt[i] = 0;
		}

		// Spielername zurücksetze, Label weg, Eingabe da
		EingabeRichtig = false;
		btn_SpielerProfil.setVisible(false);
		txt_SpielerName.setVisible(true);
		txt_SpielerName.setText("");
		lab_Spielername.setVisible(false);
		lab_Spielmodus.setVisible(false);
		lab_Restminen.setVisible(false);
		lab_MinenRichtig.setVisible(false);
		btn_Spielstarten.setVisible(true);
		btn_SpielNeustart.setEnabled(false);
		spiel.setRestMinen(3);
		lab_Restminen.setText("Minen: " + Integer.toString(spiel.getRestMinen()));
		spiel.setMinenRichtig(0);
		lab_MinenRichtig.setText("Mine Richtig: " + Integer.toString(spiel.getMinenRichtig()));
	}

	public void ButtonSpielNeustart_ActionPerformed(ActionEvent evt) {
		// TODO hier Quelltext einfÃ¼gen
		// Spielfeld Oberflaeche zu beginn

		// Spielfeld-Buttons auf Anfangswert
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].setText(".");
		}

		// Spielfeld aktivieren
		setSpielfeldAnAus(true);
		Spielfeldgesperrt = false;

		// Spielfeldgeklickt Array zurücksetzen
		for (int i = 0; i < Spielfeldgeklickt.length; i++) {
			Spielfeldgeklickt[i] = 0;
		}
		spiel.setRestMinen(3);
		lab_Restminen.setText("Minen: " + Integer.toString(spiel.getRestMinen()));
		spiel.setMinenRichtig(0);
		lab_MinenRichtig.setText("Mine Richtig: " + Integer.toString(spiel.getMinenRichtig()));

		// Zeiterfassung starten
		zeittmp = Spiel.zeitmessungStart();

	}

	// Mine gefunden in aufSiegpruefen integriert

	// public void mineAufgedeckt() {
	// //niederlage
	// boolean winlose=false;
	// //zeitmesser stoppen und Wert in zeittmp speichern
	// zeittmp=Spiel.zeitmessungEnde(zeittmp);
	// Spieler.spielerAktualisieren(zeittmp, spiel.getMinenRichtig(), winlose);
	//
	// // Ausgabe
	// JOptionPane.showMessageDialog(null, "Mine! Spiel Verloren. Spielzeit:
	// "+zeittmp/1000+" Sekunden.");
	//
	// // Spielfeld deaktivieren
	// setSpielfeldAnAus(false);
	// Spielfeldgesperrt = true;
	// }

	private void initialiseFrame() {

		// Frame-Initialisierung
		//setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		int frameWidth = 375;
		int frameHeight = 320;
		setSize(frameWidth, frameHeight);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (d.width - getSize().width) / 2;
		int y = (d.height - getSize().height) / 2;
		setLocation(x, y);
		setTitle("GameApp-Name actually not set");
		setResizable(false);
		cp.setLayout(null);
		
	    // Menüleiste
		JMenuBar menueLeiste = new JMenuBar();
		
		// Menüleiste Elemente
		JMenu men_spiel = new JMenu("Spiel");
		JMenu men_ueber = new JMenu("Über");
		
		// Untermenü + Linksklick-Methode
		JMenuItem men_spiel_neu = new JMenuItem("Neues Spiel");
		men_spiel_neu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				GUI_Start neu = new GUI_Start();		
			}
		});
		JMenuItem men_spiel_beenden = new JMenuItem("Beenden");
		men_spiel_beenden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				dispose();		
			}
		});
		JMenuItem men_ueber_version = new JMenuItem("Version");
		men_ueber_version.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				JOptionPane.showMessageDialog(null, "Version: "+versiont);
			}
		});
		
		//Menüleiste
		//Menüleiste hinzufügen
		cp.add(menueLeiste);
		//Hauptmenü-Punkte hinzufügen
		menueLeiste.add(men_spiel);
		menueLeiste.add(men_ueber);
		//Untermenü-Punkte hinzufügen
		men_spiel.add(men_spiel_neu);
		men_spiel.add(men_spiel_beenden);
		men_ueber.add(men_ueber_version);
		//MenueLeiste dem JFrame zuordnen
		setJMenuBar(menueLeiste);
	}

	// Einzelnen Spielfeld-Button [aus MouseInput heraus] deaktivieren
	public static void setDisabled(int i) {
		buttons[i].setEnabled(false);
	}

	// Alle Spielfeld-Button deaktivieren (false) oder aktivieren (true)
	public static void setSpielfeldAnAus(boolean x) {
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].setEnabled(x);
		}
	}

	public static void setSpielfeldgeklickt(int i, int j) {
		Spielfeldgeklickt[i] = j;
	}

	public static void setText(int i, String text) {
		buttons[i].setText(text);
	}

	private void setupGUI() {

		initialiseFrame();


		// Spielername-Eingabe
		txt_SpielerName.setBounds(10, 10, 150, 30);
		cp.add(txt_SpielerName);

		createStartScreenButtons();
		createSpielfeldButtons();
		createStartScreenLabels();

		btn_SpielNeustart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				ButtonSpielNeustart_ActionPerformed(evt);
			}
		});

		// Methode zum Button-klick-ausfuehren
		btn_SpielReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				ButtonResetSpielfeld_ActionPerformed();
			}
		});

		btn_Spielstarten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btn_Spielstarten_ActionPerformed();
			}
		});

		btn_SpielBeenden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				dispose();
			}
		});

		btn_SpielerProfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				GUI_Spielerprofil spieler = new GUI_Spielerprofil(spielerEins);		
			}
		});

		btn_SpielInformation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				// Erzeugung eines neuen JDialogs
				JOptionPane.showMessageDialog(null, "Finde (=richtig markiert) alle Minen um zu gewinnen."
						+ " Ein Linksklick deckt ein Feld auf. Ein Rechtsklick markiert ein Feld. Wird ein Feld mit einer Mine aufgedeckt, so ist das Spiel verloren.");
			}
		});

		// Ende Komponenten

		setVisible(true);

	
	
	}

	// Ende Methoden

} // end of class Test