package bla;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 * Spielprojekt "Seawolf" GameApp-Name <not set/actually nameless>
 *
 * @version A.3.3 vom 02.04.2017
 * @author XKonne
 * @author p0sE-Git
 */

public class Test extends JFrame {

	private static Spiel spiel;

	// Variablen
	static Spieler spielerT;


	// String
	static String versiont = "A.3.3";

	// Boolean
	static boolean Spielfeldgesperrt = true;
	static boolean EingabeRichtig = false;

	// Long
	static long zeittmp;

	// Buttons
	// private JButton btn_SpielBeenden = new JButton(); wird eventuell noch mal
	// gebraucht
	private JButton btn_SpielerProfil = new JButton();
	private JButton btn_SpielAnleitung = new JButton();
	private JButton btn_SpielNochmal = new JButton();
	private JButton btn_SpielNeueRunde = new JButton();
	private JButton btn_SpielReset = new JButton();

	// Container
	private Container cp = getContentPane();

	// Labels
	private JLabel lab_SpielerName = new JLabel();
	private JLabel lab_SpielModus = new JLabel();
	private JLabel lab_Version = new JLabel();
	private static JLabel lab_MinenRichtig = new JLabel();
	private static JLabel lab_Restminen = new JLabel();

	// Arrays
	// 0=Spielfeld noch nicht geklickt --- 1=Spielfeld bereits 1x gedrückt
	// 5=Linksklick. Spielfeld aufgedeckt. Keine weiteren Aktion möglich
	static int[] Spielfeldgeklickt = { 0, 0, 0, 0, 0, 0, 0, 0, 
									   0, 0, 0, 0, 0, 0, 0, 0, 
									   0, 0, 0, 0, 0, 0, 0, 0,
									   0, 0, 0, 0, 0, 0, 0, 0,
									   0, 0, 0, 0, 0, 0, 0, 0,
									   0, 0, 0, 0, 0, 0, 0, 0,
									   0, 0, 0, 0, 0, 0, 0, 0,
									   0, 0, 0, 0, 0, 0, 0, 0, 0 }; // i=1..65
	static JButton[] buttons = new JButton[64];
	// MFeld = Minenfeld Testspielfeld 4x4
	// 4x4-Raster
	// 1 2 3 4 <=> - - 1 M
	// 5 6 7 8 <=> 1 2 3 2
	// 9 10 11 12 <=> 1 M M 1
	// 13 14 15 16 <=> 1 2 2 1
	static String a_btnText[] = { "1", "1", "1", "1", "1", "1", "-", "-",
								  "1", "M", "1", "1", "M", "2", "1", "1",
								  "1", "1", "1", "1", "1", "2", "M", "1",
								  "-", "1", "2", "2", "1", "2", "2", "2",
								  "-", "1", "M", "M", "2", "1", "M", "2",
								  "-", "1", "3", "M", "2", "1", "2", "M",
								  "1", "1", "2", "1", "2", "1", "2", "1",
								  "1", "M", "1", "-", "1", "M", "1", "-" };
	// Ende Attribute

	public Test(Spiel spiel, Spieler spieler) {

		super();
		Test.spiel = spiel;
		spielerT = spieler;
		setupGUI();
		spielStart();
	}

	private void spielStart() {
		if (GUI_Start.SpielerAngelegt() == true) {
			// Spielfeld aktivieren
			setSpielfeldAnAus(true);

			lab_SpielerName.setText(spielerT.getSpielerName());
			lab_SpielerName.setVisible(true);
			lab_SpielModus.setVisible(true);
			lab_Restminen.setVisible(true);
			lab_MinenRichtig.setVisible(true);

			btn_SpielNochmal.setEnabled(true);
			Spielfeldgesperrt = false;
			btn_SpielerProfil.setVisible(true);

			// Button zum Schluss deaktivieren
			btn_SpielReset.setEnabled(false);

			zeittmp = Spiel.zeitmessungStart();
		}
	}

	private void createSpielfeldButtons() {

		// TODO Button-Namen wirklich notwendig?
		String a_btnNames[] = { "MFeld1", "MFeld2", "MFeld3", "MFeld4", "MFeld5", "MFeld6", "MFeld7", "MFeld8",
								"MFeld9", "MFeld10", "MFeld11", "MFeld12", "MFeld13", "MFeld14", "MFeld15", "MFeld16",
								"MFeld17", "MFeld18", "MFeld19", "MFeld20", "MFeld21", "MFeld22", "MFeld23", "MFeld24",
								"MFeld25", "MFeld26", "MFeld27", "MFeld28", "MFeld29", "MFeld30", "MFeld31", "MFeld32",
								"MFeld33", "MFeld34", "MFeld35", "MFeld36", "MFeld37", "MFeld38", "MFeld39", "MFeld40",
								"MFeld41", "MFeld42", "MFeld43", "MFeld44", "MFeld45", "MFeld46", "MFeld47", "MFeld48",
								"MFeld49", "MFeld50", "MFeld51", "MFeld52", "MFeld53", "MFeld54", "MFeld55", "MFeld56",
								"MFeld57", "MFeld58", "MFeld59", "MFeld60", "MFeld61", "MFeld62", "MFeld63", "MFeld64" };

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

		btn_SpielReset.setBounds(10, 340, 80, 30);
		btn_SpielReset.setText("Reset");
		btn_SpielReset.setMargin(new Insets(2, 2, 2, 2));
		btn_SpielReset.setEnabled(false);
		cp.add(btn_SpielReset);

		btn_SpielNeueRunde.setBounds(100, 340, 80, 30);
		btn_SpielNeueRunde.setText("Neue Runde");
		btn_SpielNeueRunde.setMargin(new Insets(2, 2, 2, 2));
		btn_SpielNeueRunde.setEnabled(false);
		cp.add(btn_SpielNeueRunde);

		btn_SpielNochmal.setBounds(190, 340, 80, 30);
		btn_SpielNochmal.setText("Nochmal");
		btn_SpielNochmal.setMargin(new Insets(2, 2, 2, 2));
		btn_SpielNochmal.setEnabled(false);
		cp.add(btn_SpielNochmal);

		btn_SpielAnleitung.setBounds(340, 5, 40, 40);
		btn_SpielAnleitung.setText("?");
		btn_SpielAnleitung.setMargin(new Insets(2, 2, 2, 2));
		cp.add(btn_SpielAnleitung);

	}

	private void createStartScreenLabels() {

		Border border = LineBorder.createGrayLineBorder();

		lab_SpielerName.setBounds(55, 5, 160, 40);
		lab_SpielerName.setFont(new Font("Dialog", Font.PLAIN, 35));
		lab_SpielerName.setBorder(border);
		lab_SpielerName.setVisible(false);
		cp.add(lab_SpielerName);

		lab_SpielModus.setBounds(220, 0, 120, 20);
		lab_SpielModus.setVisible(false);
		lab_SpielModus.setFont(new Font("Dialog", Font.PLAIN, 11));
		lab_SpielModus.setText("Modus: "+Spiel.getSpielModus());
		cp.add(lab_SpielModus);

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

		lab_Version.setBounds(325, 340, 100, 30);
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
		int spalte = 8; // Spalte setzt das Layout fest
		int abstand = 3;

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
			spielerT.spielerAktualisieren(zeittmp, spiel.getMinenRichtig(), winlose);

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
			spielerT.spielerAktualisieren(zeittmp, spiel.getMinenRichtig(), winlose);

			// Spielfeld deaktivieren
			setSpielfeldAnAus(false);
			Spielfeldgesperrt = true;
		}
	}

	public void ButtonResetSpielfeld_ActionPerformed() { //aktuell deaktiviert

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
		// EingabeRichtig = false;
		btn_SpielerProfil.setVisible(false);
		// txt_SpielerName.setVisible(true);
		// txt_SpielerName.setText("");
		lab_SpielerName.setVisible(false);
		lab_SpielModus.setVisible(false);
		lab_Restminen.setVisible(false);
		lab_MinenRichtig.setVisible(false);
		// btn_Spielstarten.setVisible(true);
		btn_SpielNochmal.setEnabled(false);
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
		//Zurücksetzen der Minen
		spiel.setRestMinen(spiel.getMinenGesamt());
		lab_Restminen.setText("Minen: " + Integer.toString(spiel.getRestMinen()));
		spiel.setMinenRichtig(0);
		lab_MinenRichtig.setText("Mine Richtig: " + Integer.toString(spiel.getMinenRichtig()));

		// Zeiterfassung starten
		zeittmp = Spiel.zeitmessungStart();
	}

	private void initialiseFrame() {

		// Frame-Initialisierung
		// setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		int frameWidth = 390;
		int frameHeight = 430;
		setSize(frameWidth, frameHeight);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (d.width - getSize().width) / 2;
		int y = (d.height - getSize().height) / 2;
		setLocation(x, y);
		setTitle("Projekt 'Seawolf' <no GameApp actually found>");
		setResizable(false);
		cp.setLayout(null);

		//  Menüleiste
		JMenuBar menueLeiste = new JMenuBar();

		//  Menüleiste Elemente
		JMenu men_spiel = new JMenu("Spiel");
		JMenu men_ueber = new JMenu("Über");

		// Untermenü + Linksklick-Methode
		JMenuItem men_spiel_neu = new JMenuItem("Neues Spiel");
		men_spiel_neu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				dispose();
				GUI_Start neu = new GUI_Start();
			}
		});
		JMenuItem men_spiel_beenden = new JMenuItem("Beenden");
		men_spiel_beenden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				System.exit(0);
			}
		});
		JMenuItem men_ueber_version = new JMenuItem("Version");
		men_ueber_version.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				JOptionPane.showMessageDialog(null, "Version: " + versiont);
			}
		});

		// Menüleiste
		// Menüleiste hinzufügen
		cp.add(menueLeiste);
		// Hauptmenü-Punkte hinzufügen
		menueLeiste.add(men_spiel);
		menueLeiste.add(men_ueber);
		// Untermenü-Punkte hinzufügen
		men_spiel.add(men_spiel_neu);
		men_spiel.add(men_spiel_beenden);
		men_ueber.add(men_ueber_version);
		// MenueLeiste dem JFrame zuordnen
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

		createStartScreenButtons();
		createSpielfeldButtons();
		createStartScreenLabels();

		btn_SpielNochmal.addActionListener(new ActionListener() {
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

		btn_SpielerProfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				GUI_Spielerprofil profil = new GUI_Spielerprofil(spielerT);
			}
		});

		btn_SpielAnleitung.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				// Erzeugung eines neuen JDialogs
				JOptionPane.showMessageDialog(null, "Finde (=richtig markiert) alle Minen um zu gewinnen."
						+ " Ein Linksklick deckt ein Feld auf. Ein Rechtsklick markiert ein Feld. Wird ein Feld mit einer Mine aufgedeckt, so ist das Spiel verloren.");
			}
		});

		// Ende Komponenten
		// frame spielfeld
		setVisible(true);

	} // Ende Methoden

} // end of class Test