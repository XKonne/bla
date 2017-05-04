package bla;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 * Spielprojekt "Seawolf" GameApp-Name <not set/actually nameless>
 *
 * @version A.3.7b vom 09.04.2017
 * @author XKonne
 * @author p0sE-Git
 */

@SuppressWarnings("serial")
public class Test extends JFrame {

	// Objekte
	private static Spiel spiel;
	private static Spieler spielerT;
	
	// Frame-Container
	private JFrame gui_SpielEnde = new JFrame();
	private Container cp = getContentPane();

	// Variablen
	// String
	static String versiont = "A.3.7b";

	// Boolean
	static boolean Spielfeldgesperrt = true;

	// Long
	private static long zeittmp;

	// GUI-Elemente
	// Buttons
	private JButton btn_SpielerProfil = new JButton();
	private JButton btn_SpielNeustart = new JButton();

	// Buttons - SpielEnde
	private JButton btn_SpielZurueck = new JButton();
	private JButton btn_SpielNochmal = new JButton();
	private JButton btn_SpielNeueRunde = new JButton();
	private JButton btn_SpielNeues = new JButton();
	
	// Labels - Spielfeld
	private JLabel lab_SpielerName = new JLabel();
	private JLabel lab_SpielModus = new JLabel();
	private JLabel lab_Version = new JLabel();
	private static JLabel lab_MinenRichtig = new JLabel();
	private static JLabel lab_Restminen = new JLabel();
	
	// Labels - SpielEnde
	private JLabel lab_SpielEndeInformation = new JLabel();

	// Arrays
	
	// !!! Verschoben nach Spiel, angepasst an 2dim
	// 0=Spielfeld noch nicht geklickt --- 1=Spielfeld bereits 1x gedrückt
	// 5=Linksklick. Spielfeld aufgedeckt. Keine weiteren Aktion möglich
	static int[] Spielfeldgeklickt = { 0, 0, 0, 0, 0, 0, 0, 0, 
									   0, 0, 0, 0, 0, 0, 0, 0, 
									   0, 0, 0, 0, 0, 0, 0, 0,
									   0, 0, 0, 0, 0, 0, 0, 0,
									   0, 0, 0, 0, 0, 0, 0, 0,
									   0, 0, 0, 0, 0, 0, 0, 0,
									   0, 0, 0, 0, 0, 0, 0, 0,
									   0, 0, 0, 0, 0, 0, 0, 0 }; // i=1..65
	static JButton[] buttons = new JButton[64];
	// MFeld = Minenfeld Testspielfeld 4x4
	// 4x4-Raster
	// 1 2 3 4 <=> - - 1 M
	// 5 6 7 8 <=> 1 2 3 2
	// 9 10 11 12 <=> 1 M M 1
	// 13 14 15 16 <=> 1 2 2 1
	static String a_btnText[] = { "1", "1", "1", "1", "1", "1", "-", "-",	// Überlfüssig, da durch Spiel_spielfeld ersetzt
								  "1", "M", "1", "1", "M", "2", "1", "1",
								  "1", "1", "1", "1", "1", "2", "M", "1",
								  "-", "1", "2", "2", "1", "2", "2", "2",
								  "-", "1", "M", "M", "2", "1", "M", "2",
								  "-", "1", "3", "M", "2", "1", "2", "M",
								  "1", "1", "2", "1", "2", "1", "2", "1",
								  "1", "M", "1", "-", "1", "M", "1", "-" };
	// Ende Attribute

	public Test(Spiel spiel, Spieler spieler) {

		Test.spiel = ObjectHandler.getSpiel();
		spielerT = ObjectHandler.getSpieler();
		
		setupGUI();
		
//		spielStart();
	}

	// !!! Überlfüssig, da Felder erzeugen eine neue Routine ist
	private void createSpielfeld() {

		// Buttons bauen
		for (int i = 0; i < buttons.length; i++) {

			buttons[i] = new JButton("");
			buttons[i].setText(".");
			buttons[i].setMargin(new Insets(2, 2, 2, 2));
			buttons[i].setEnabled(false);

			MouseInput mouse = new MouseInput(i);
			buttons[i].addMouseListener(mouse);

			cp.add(buttons[i]);

		}

		setBoundsSpielfeldButtons();
	}

	private void createButtons() {

//		btn_SpielerProfil.setIcon(new ImageIcon(getClass().getResource("img/profil.jpg")));
//		btn_SpielerProfil.setBounds(10, 5, 40, 40);
//		btn_SpielerProfil.setVisible(false);
//		btn_SpielerProfil.setMargin(new Insets(2, 2, 2, 2));
//		cp.add(btn_SpielerProfil);
//		btn_SpielerProfil.addActionListener(this);
//
//		btn_SpielNeustart.setIcon(new ImageIcon(getClass().getResource("img/neustart.png")));
//		btn_SpielNeustart.setToolTipText("Startet die Runde neu.");
//		btn_SpielNeustart.setBounds(340, 5, 40, 40);
//		btn_SpielNeustart.setMargin(new Insets(2, 2, 2, 2));
//		cp.add(btn_SpielNeustart);
//		btn_SpielNeustart.addActionListener(this);
	}
	

	private void createLabels() {

//		Border border = LineBorder.createGrayLineBorder();
//
//		lab_SpielerName.setBounds(55, 5, 160, 40);
//		lab_SpielerName.setFont(new Font("Dialog", Font.PLAIN, 35));
//		lab_SpielerName.setBorder(border);
//		lab_SpielerName.setVisible(true);
//		cp.add(lab_SpielerName);
//
//		lab_SpielModus.setBounds(220, 0, 120, 20);
//		lab_SpielModus.setVisible(false);
//		lab_SpielModus.setFont(new Font("Dialog", Font.PLAIN, 11));
//		lab_SpielModus.setVisible(true);
//		cp.add(lab_SpielModus);
//
//		lab_Restminen.setBounds(220, 14, 100, 20);
//		lab_Restminen.setVisible(true);
//		lab_Restminen.setFont(new Font("Dialog", Font.PLAIN, 11));
//		cp.add(lab_Restminen);
//
//		lab_MinenRichtig.setBounds(220, 28, 100, 20);
//		lab_MinenRichtig.setVisible(true);
//		lab_MinenRichtig.setFont(new Font("Dialog", Font.PLAIN, 11));
//		cp.add(lab_MinenRichtig);
//
//		lab_Version.setBounds(12, 47, 100, 20);
//		lab_Version.setVisible(true);
//		lab_Version.setText(versiont);
//		cp.add(lab_Version);
	}

	// in GUI_Spielfeld überlfüssig da Gridlayout
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


	
	// Verschoben nach Spiel
	public void aufSiegpruefen(boolean mineGetroffen) {
//		boolean winlose = false;
//		// aufSiegpruefen wird nach _jedem_ Mausklick ausgeführt.
//
//		// Minen-Markiert-Zähler und Minen-Richtig-Zähler aktualisieren
//		lab_Restminen.setText("Minen: " + Integer.toString(spiel.getRestMinen()));
//		lab_MinenRichtig.setText("Mine Richtig: " + Integer.toString(spiel.getMinenRichtig()));
//
//		// Sieg
//		if (spiel.getMinenRichtig() == spiel.getMinenGesamt() && spiel.getRestMinen() == 0
//				&& Spielfeldgesperrt == false) {
//			// Variable für Spielhistorie
//			winlose = true;
//			
//			// zeitmesser stoppen und Wert in zeittmp speichern
//			zeittmp = Spiel.zeitmessungEnde(zeittmp);
//
//			// Ausgabe
//			GUI_SpielEnde(mineGetroffen);
//
//			// Spieler Stats aktualsieren
//			spielerT.spielerAktualisieren(zeittmp, spiel.getMinenRichtig(), winlose);
//
//			// Spielfeld deaktivieren
//			setSpielfeldAnAus(false);
//			Spielfeldgesperrt = true;
//		}
//		
//		// Niederlage (=Mine aufgedeckt)
//		if (mineGetroffen == true) {
//			
//			winlose = false;
//			// zeitmesser stoppen und Wert in zeittmp speichern
//			zeittmp = Spiel.zeitmessungEnde(zeittmp);
//			
//			// Ausgabe
//			GUI_SpielEnde(mineGetroffen);
//
//			// Spieler Stats aktualisieren
//			spielerT.spielerAktualisieren(zeittmp, spiel.getMinenRichtig(), winlose);
//
//			// Spielfeld deaktivieren
//			setSpielfeldAnAus(false);
//			Spielfeldgesperrt = true;
//		}
	}

	private void initFrame() {
	
		// Frame-Initialisierung
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		int frameWidth = 390;
		int frameHeight = 395;
		setSize(frameWidth, frameHeight);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (d.width - getSize().width) / 2;
		int y = (d.height - getSize().height) / 2;
		setLocation(x, y);
		setTitle("Projekt 'Seawolf' <no GameApp actually found>");
		setResizable(false);
		cp.setLayout(null);

		// !!! Übernommen in GUI_Spielfeld
//		//  Menüleiste
//		JMenuBar menueLeiste = new JMenuBar();
//
//		//  Menüleiste Elemente
//		JMenu men_spiel = new JMenu("Spiel");
//		JMenu men_ueber = new JMenu("Über");
//
//		// Untermenü + Linksklick-Methode
//		JMenuItem men_spiel_neu = new JMenuItem("Neues Spiel");
//		men_spiel_neu.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent evt) {
//				dispose();
//				GUI_Start frame = new GUI_Start();
//			}
//		});
//		JMenuItem men_spiel_beenden = new JMenuItem("Beenden");
//		men_spiel_beenden.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent evt) {
//				System.exit(0);
//			}
//		});
//		JMenuItem men_ueber_version = new JMenuItem("Version");
//		men_ueber_version.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent evt) {
//				JOptionPane.showMessageDialog(null, "Version: " + versiont);
//			}
//		});
//
//		// Menüleiste
//		// Menüleiste hinzufügen
//		cp.add(menueLeiste);
//		// Hauptmenü-Punkte hinzufügen
//		menueLeiste.add(men_spiel);
//		menueLeiste.add(men_ueber);
//		// Untermenü-Punkte hinzufügen
//		men_spiel.add(men_spiel_neu);
//		men_spiel.add(men_spiel_beenden);
//		men_ueber.add(men_ueber_version);
//		// MenueLeiste dem JFrame zuordnen
//		setJMenuBar(menueLeiste);
	}

	// !!! Verschoben nach GUI_Spielfeld
	// Einzelnen Spielfeld-Button [aus MouseInput heraus] deaktivieren
	public static void setDisabled(int i) {
//		buttons[i].setEnabled(false);
	}

	// !!! Verschoben nach GUI_Spielfeld
	// Alle Spielfeld-Button deaktivieren (false) oder aktivieren (true)
	public static void setSpielfeldAnAus(boolean x) {
//		for (int i = 0; i < buttons.length; i++) {
//			buttons[i].setEnabled(x);
//		}
	}

	// !!! Verschoben nach Spiel
	public static void setSpielfeldgeklickt(int i, int j) {
//		Spielfeldgeklickt[i] = j;
	}

	// !!! Überlfüssig, da neue Routine für Felder geschrieben
	public static void setText(int i, String text) {
//		buttons[i].setText(text);
	}

	private void setupGUI() {

		initFrame();

		createButtons();
		createSpielfeld();
		createLabels();

		// frame spielfeld
		setVisible(true);
		repaint();
		

	} // Ende Methoden
	
	// !!! Übernommen nach GUI_Spielfeld
	private void GUI_SpielEnde(boolean mineGetroffen) {
//		{
//		// Lokale Variablen
//		String siegNiederlage="";
//			
//		// Frame-Initialisierung
//		gui_SpielEnde.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
//		gui_SpielEnde.setSize(380, 170);
//		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
//		int x = (d.width - getSize().width) / 2;
//		int y = (d.height - getSize().height) / 2;
//		gui_SpielEnde.setLocation(x, y);
//		gui_SpielEnde.setTitle("Spielende");
//		gui_SpielEnde.setResizable(false);
//		Container cp2 = gui_SpielEnde.getContentPane();
//		//Container cp2 = getContentPane();
//		cp2.setLayout(null);
//		
//		// Frame-Elemente
//		// Buttons
//		btn_SpielZurueck.setBounds(10, 100, 80, 30);
//		btn_SpielZurueck.setText("Zurück");
//		btn_SpielZurueck.setMargin(new Insets(2, 2, 2, 2));
//		cp2.add(btn_SpielZurueck);
//		btn_SpielZurueck.addActionListener(this);
//		
//		btn_SpielNochmal.setBounds(100, 100, 80, 30);
//		btn_SpielNochmal.setText("Nochmal");
//		btn_SpielNochmal.setMargin(new Insets(2, 2, 2, 2));
//		cp2.add(btn_SpielNochmal);
//		btn_SpielNochmal.addActionListener(this);
//		
//		btn_SpielNeueRunde.setBounds(190, 100, 80, 30);
//		btn_SpielNeueRunde.setText("Neue Runde");
//		btn_SpielNeueRunde.setMargin(new Insets(2, 2, 2, 2));
//		btn_SpielNeueRunde.setEnabled(false);
//		cp2.add(btn_SpielNeueRunde);
//		btn_SpielNeueRunde.addActionListener(this);
//		
//		btn_SpielNeues.setBounds(280, 100, 80, 30);
//		btn_SpielNeues.setText("Neues Spiel");
//		btn_SpielNeues.setMargin(new Insets(2, 2, 2, 2));
//		btn_SpielNeues.setEnabled(false);
//		cp2.add(btn_SpielNeues);
//		btn_SpielNeues.addActionListener(this);
//		
//		// Ausgabetext Sieg oder Niederlage
//		if (mineGetroffen==false) {
//			siegNiederlage = "Sieg";
//		}
//		else {
//			siegNiederlage = "Niederlage";
//		}
//		
//		// Labels
//		lab_SpielEndeInformation.setBounds(10, 10, 250, 65);
//		lab_SpielEndeInformation.setVisible(true);
//		lab_SpielEndeInformation.setText("<HTML><font size=14><i>"+siegNiederlage+"!</i></font><br>Spieldauer: "+zeittmp / 1000+" Sekunden. </HTML>");
//		cp2.add(lab_SpielEndeInformation);
//		
//		gui_SpielEnde.setVisible(true);
//	}
	}
	
}