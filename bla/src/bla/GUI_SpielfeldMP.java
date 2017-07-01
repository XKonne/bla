package bla;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class GUI_SpielfeldMP extends JFrame {

	// Frame-Container-Panel
	private JFrame gui_SpielEnde;
	GridBagConstraints gbc;

	// Container für Borderlayout: TOP
	private Container cpTop;
	private static JPanel panSpielfeld; // Gridlayout

	static int spielerAnzahl = 2;
	String spielArt = "lok_SP";
	static JButton l_minenFortschritt[] = new JButton[Spiel.getMinenGesamt()/spielerAnzahl];
	
	// Variablen
	// Boolean
	static boolean Spielfeldgesperrt;

	// Integer
	private int frameHeight;
	private int frameWidth;
	private int feldHeight;
	private int feldWidth;

	// Timer
	static Timer spielzeit;

	// GUI-Elemente
	// Labels - guiSpielfeld
	private JLabel lab_SpielerName;
	private static JLabel lab_SpielModus;

	private static JLabel lab_Spielzeit; // deaktiviert, wird später für die
											// Spielzeit verwendet
	private static JLabel lab_Restminen;

	// Buttons
	static JButton btn_SpielZeit;

	// GUI - SpielEnde
	JLabel lab_SpielEndeInformation;
	JButton btn_SpielZurueck;
	JButton btn_SpielNochmal;
	JButton btn_SpielNeueRunde;
	JButton btn_SpielNeues;

	// Arrays
	static Feld[][] Felder;

	public GUI_SpielfeldMP() {

		initClassVariables();
		setupGUI();
		neueRunde();
		spielStart();

	}

	private void addMenubar() {
		ObjectHandler.createGui_AddMenubar();
		this.setJMenuBar(ObjectHandler.getGui_AddMenubar());
	}

	private void closeGUI_SpielEnde() {
		gui_SpielEnde.dispose();
	}

	private void closeGUI_SpielfeldMP() {
		
		clearClassVariables();
		this.setVisible(false);
		this.dispose();
		ObjectHandler.setGui_Spielfeld(null);
		ObjectHandler.setGui_AddMenubar(null);
		
	}

	private void createButtons() {
		
		Border border = LineBorder.createGrayLineBorder();
		
		JButton b_SpielerProfil[] = new JButton[spielerAnzahl];
		JLabel l_SpielerName[] = new JLabel[spielerAnzahl];
		
		for (int i=0; i<spielerAnzahl; i++) {
			b_SpielerProfil[i] = new JButton();
			b_SpielerProfil[i].setIcon(new ImageIcon(getClass().getResource("img/profil.jpg")));
			b_SpielerProfil[i].setVisible(true);
			b_SpielerProfil[i].setMargin(new Insets(2, 2, 2, 2));
			cpTop.add(b_SpielerProfil[i]);
			b_SpielerProfil[i].addActionListener(e -> ObjectHandler.createGui_Spielerprofil());
			
			l_SpielerName[i] = new JLabel();
			l_SpielerName[i].setFont(new Font("Dialog", Font.PLAIN, 35));
			l_SpielerName[i].setBorder(border);
			l_SpielerName[i].setVisible(true);
			cpTop.add(l_SpielerName[i]);
						
			switch (i) {
				case 0: b_SpielerProfil[i].setBounds(4, 5, 40, 40); 
						l_SpielerName[i].setBounds(50, 5, 204, 40);
						break;
				case 1: b_SpielerProfil[i].setBounds(this.getWidth() - 45 - 15, 5, 40, 40);
						l_SpielerName[i].setBounds(this.getWidth() - 250 - 15 - 4, 5, 204, 40); break;
			}
		}
		
		
		
		for (int i=0; i<Spiel.getMinenGesamt()/spielerAnzahl; i++) {
			l_minenFortschritt[i] = new JButton();
			l_minenFortschritt[i].setVisible(true);
			l_minenFortschritt[i].setBounds(4+i*(250/(Spiel.getMinenGesamt()/spielerAnzahl)), 47, 250/(Spiel.getMinenGesamt()/spielerAnzahl), 8);
			l_minenFortschritt[i].setBackground(Color.RED);
			cpTop.add(l_minenFortschritt[i]);
		}
		
		if (spielerAnzahl > 1) {
		
			JButton l_minenFortschritt2[] = new JButton[Spiel.getMinenGesamt()/2];
			
			for (int i=0; i<Spiel.getMinenGesamt()/2; i++) {
				l_minenFortschritt2[i] = new JButton();
				l_minenFortschritt2[i].setVisible(true);
				l_minenFortschritt2[i].setBounds(this.getWidth() - 70 - i*(250/(Spiel.getMinenGesamt()/2)), 47, 250/(Spiel.getMinenGesamt()/2), 8);
				l_minenFortschritt2[i].setBackground(Color.RED);
				cpTop.add(l_minenFortschritt2[i]);
			}
		}
		

//		JButton btn_SpielerProfil = new JButton();
		JButton btn_SpielNeustart = new JButton();

//		btn_SpielerProfil.setIcon(new ImageIcon(getClass().getResource("img/profil.jpg")));
//		btn_SpielerProfil.setBounds(4, 5, 40, 40);
//		btn_SpielerProfil.setVisible(true);
//		btn_SpielerProfil.setMargin(new Insets(2, 2, 2, 2));
//		cpTop.add(btn_SpielerProfil);
//		btn_SpielerProfil.addActionListener(e -> ObjectHandler.createGui_Spielerprofil());

//		btn_SpielNeustart.setIcon(new ImageIcon(getClass().getResource("img/neustart.png")));
//		btn_SpielNeustart.setToolTipText("Startet aktuelle Runde neu.");
//		btn_SpielNeustart.setBounds(this.getWidth() - 60, 5, 40, 40);
//		btn_SpielNeustart.setMargin(new Insets(2, 2, 2, 2));
//		cpTop.add(btn_SpielNeustart);
//		btn_SpielNeustart.addActionListener(e -> spielNochmal());

		Border loweredbevel;
		loweredbevel = BorderFactory.createLineBorder(Color.black);

		// btn_SpielZeit.setIcon(new
		// ImageIcon(getClass().getResource("img/neustart.png")));
		btn_SpielZeit.setBounds(255, 30, 115, 15);
		btn_SpielZeit.setFont(new Font("Dialog", Font.PLAIN, 11));
		btn_SpielZeit.setText("(>) Spielzeit: 00:00");
		btn_SpielZeit.setMargin(new Insets(2, 2, 2, 2));
		btn_SpielZeit.setBorder(loweredbevel);
		btn_SpielZeit.setBackground(Color.LIGHT_GRAY);
		cpTop.add(btn_SpielZeit);
		btn_SpielZeit.addActionListener(e -> zeitAnzeigenPause());
	}

	private void createLabels() {

		Border border = LineBorder.createGrayLineBorder();

		// GUI-Spielfeld
//		lab_SpielerName.setBounds(50, 5, 200, 40);
//		lab_SpielerName.setFont(new Font("Dialog", Font.PLAIN, 35));
//		lab_SpielerName.setBorder(border);
//		lab_SpielerName.setVisible(true);
//		cpTop.add(lab_SpielerName);

		lab_SpielModus.setBounds(255, 0, 120, 20);
		lab_SpielModus.setVisible(false);
		lab_SpielModus.setFont(new Font("Dialog", Font.PLAIN, 11));
		lab_SpielModus.setVisible(true);
		cpTop.add(lab_SpielModus);

		lab_Restminen.setBounds(255, 12, 100, 20);
		lab_Restminen.setVisible(true);
		lab_Restminen.setFont(new Font("Dialog", Font.PLAIN, 11));
		cpTop.add(lab_Restminen);

		// lab_Spielzeit.setBounds(255, 28, 100, 20);
		// lab_Spielzeit.setVisible(true);
		// lab_Spielzeit.setFont(new Font("Dialog", Font.PLAIN, 11));
		// lab_Spielzeit.setText("Spielzeit: 0 Sek.");
		// cpTop.add(lab_Spielzeit);
	}

	private void createSpielfeldFeld() {

		for (int z = 1; z < Spiel.getSpielfeldZeilen() + 1; z++) {
			for (int sp = 1; sp < Spiel.getSpielfeldSpalten() + 1; sp++) {
				// Erzeugen der Felder und Eigenschaften setzen
				Felder[z][sp] = new Feld();
				// System.out.println("Feld x: " +
				// GUI_Spielfeld.Felder[z][sp].getmyX() + " , y: " +
				// GUI_Spielfeld.Felder[z][sp].getmyY());
				// System.out.println("Feld Geklickt: " +
				// GUI_Spielfeld.Felder[z][sp].getGeklickt());
				Felder[z][sp].setMargin(new Insets(0, 0, 0, 0));

				MouseInput mouse = new MouseInput(z, sp);
				Felder[z][sp].addMouseListener(mouse);

				// Eigenschaften-Anordnung für das GridBagLayout
				gbc.gridx = sp;
				gbc.gridy = z;
				gbc.insets = new Insets(1, 1, 1, 1);
				gbc.gridwidth = gbc.gridheight = 1;
				gbc.fill = GridBagConstraints.BOTH;
				gbc.anchor = GridBagConstraints.NORTHWEST;
				gbc.weightx = feldWidth;
				gbc.weighty = feldHeight;

				panSpielfeld.add(Felder[z][sp], gbc);
			}
		}
		// ich hatte auch mal in Zeile 154 die Zahlen z und sp übergeben um
		// direkt die Koordinate zu setzen. Hat auch nichts genützt
		// for (int z = 0; z < Spiel.getSpielfeldZeilen(); z++) {
		// for (int sp = 0; sp < Spiel.getSpielfeldSpalten(); sp++) {
		// //System.out.println("Feld x: " +
		// GUI_Spielfeld.Felder[z][sp].getmyX() + " , y: " +
		// GUI_Spielfeld.Felder[z][sp].getmyY());
		// Felder[z][sp].setmyX(z);
		// Felder[z][sp].setmyY(sp);
		// //System.out.println("init Feld x: " +
		// GUI_Spielfeld.Felder[z][sp].getmyX() + " , y: " +
		// GUI_Spielfeld.Felder[z][sp].getmyY());
		// }
		// }

		this.getContentPane().add(panSpielfeld, BorderLayout.CENTER);
	}

	public void GUI_SpielEnde(String SiegNiederlage) { // SpielEnde:
														// Sieg-Niederlage Frame

		// Frame-Initialisierung
		gui_SpielEnde.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		int sp_frameWidth = 380;
		int sp_frameHeight = 170;
		gui_SpielEnde.setSize(sp_frameWidth, sp_frameHeight);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = d.width / 2;
		int y = d.height / 2;
		gui_SpielEnde.setLocation(x - sp_frameWidth / 2, y - sp_frameHeight / 4);
		gui_SpielEnde.setTitle("Spielende");
		gui_SpielEnde.setResizable(false);
		Container cpGUI_SpielEnde = gui_SpielEnde.getContentPane();
		cpGUI_SpielEnde.setLayout(null);

		// Frame-Elemente
		// Buttons
		btn_SpielZurueck.setBounds(10, 100, 80, 30);
		btn_SpielZurueck.setText("Zurück");
		btn_SpielZurueck.setMargin(new Insets(2, 2, 2, 2));
		btn_SpielZurueck.setToolTipText("Kehr zum Spielfeld zurück.");
		cpGUI_SpielEnde.add(btn_SpielZurueck);
		btn_SpielZurueck.addActionListener(e -> closeGUI_SpielEnde());

		btn_SpielNochmal.setBounds(100, 100, 80, 30);
		btn_SpielNochmal.setText("Nochmal");
		btn_SpielNochmal.setMargin(new Insets(2, 2, 2, 2));
		btn_SpielNochmal.setToolTipText("Startet die aktuelle Runde neu.");
		cpGUI_SpielEnde.add(btn_SpielNochmal);
		btn_SpielNochmal.addActionListener(e -> spielNochmal());

		btn_SpielNeueRunde.setBounds(190, 100, 80, 30);
		btn_SpielNeueRunde.setText("Neue Runde");
		btn_SpielNeueRunde.setMargin(new Insets(2, 2, 2, 2));
		btn_SpielNeueRunde.setToolTipText("Generiert im aktuellen Spielmodus eine neue Runde.");
		cpGUI_SpielEnde.add(btn_SpielNeueRunde);
		btn_SpielNeueRunde.addActionListener(e -> spielNeueRunde());

		btn_SpielNeues.setBounds(280, 100, 80, 30);
		btn_SpielNeues.setText("Neues Spiel");
		btn_SpielNeues.setMargin(new Insets(2, 2, 2, 2));
		btn_SpielNeues.setToolTipText("Kehrt zum Hauptmenü zurück, um einen anderen Spielmodus zu wählen.");
		cpGUI_SpielEnde.add(btn_SpielNeues);
		btn_SpielNeues.addActionListener(e -> spielNeues());

		// Labels
		lab_SpielEndeInformation.setBounds(10, 10, 250, 65);
		lab_SpielEndeInformation.setVisible(true);
		lab_SpielEndeInformation.setText("<HTML><font size=14><i>" + SiegNiederlage + "!</i></font><br>Spieldauer: "
				+ zeitformatMMSS(ObjectHandler.getSpieler().getZeitLetztesSpiel() / 1000) + "</HTML>");
		cpGUI_SpielEnde.add(lab_SpielEndeInformation);

		// Alle Minen aufdecken, die noch nicht aufgedeckt sind oder nicht
		// richtig markiert sind
		spielEndeMinenAufdecken();

		gui_SpielEnde.setVisible(true);
	}

	private void initFrame() {

		// Hauptcontainer mit Borderlayout
		Container cp = this.getContentPane();
		// Container für Borderlayout: LEFT, RIGHT, BOT
		Container cpLeft = new Container();
		Container cpRight = new Container();
		Container cpBot = new Container();

		int extraBreiteLinks = 0;
		int extraBreiteRechts = 0;

		cp.setLayout(new BorderLayout());

		if (Spiel.getSpielfeldSpalten() < 15) {
			// unter 15 Spalten ist das Spielfeld zu klein um top vollständig
			// anzuzeigen.
			// Füge links und rechts leeren Raum ein, damit restliche Größen
			// wieder passen
			// pro Spalte unter 15 ist das Spielfeld um 29 zu klein
			extraBreiteLinks = 15 * (15 - Spiel.getSpielfeldSpalten());
			extraBreiteRechts = 14 * (15 - Spiel.getSpielfeldSpalten());
			
			if (spielerAnzahl == 2) {
				extraBreiteLinks = 15 * (15 - Spiel.getSpielfeldSpalten()) + 75;
				extraBreiteRechts = 14 * (15 - Spiel.getSpielfeldSpalten()) + 74; 
			}
			
		}

		cpTop.setLayout(null);
		cpTop.setPreferredSize(new Dimension(600, 50+15));
		this.getContentPane().add(cpTop, BorderLayout.PAGE_START);

		cpLeft.setLayout(null);
		cpLeft.setPreferredSize(new Dimension(extraBreiteLinks, 0));
		this.getContentPane().add(cpLeft, BorderLayout.LINE_START);

		cpRight.setLayout(null);
		cpRight.setPreferredSize(new Dimension(extraBreiteRechts, 0));
		this.getContentPane().add(cpRight, BorderLayout.LINE_END);

		cpBot.setLayout(null);
		cpBot.setPreferredSize(new Dimension(0, 0));
		this.getContentPane().add(cpBot, BorderLayout.PAGE_END);

		// Center > Spielfeld
		panSpielfeld.setLayout(new GridBagLayout());

		// 73 anscheinend top und 23 menubar
		frameHeight = Spiel.getSpielfeldZeilen() * (feldHeight) + Spiel.getSpielfeldZeilen() * 4 + 16 + 73 + 23 + 15;
		frameWidth = Spiel.getSpielfeldSpalten() * (feldWidth) + Spiel.getSpielfeldSpalten() * 4 + 16 + extraBreiteLinks
				+ extraBreiteRechts;

		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		switch (spielerAnzahl) {
			case 1: this.setSize(frameWidth, frameHeight); break;
			case 2: this.setSize(600, frameHeight); break;
		}
				
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (d.width - getSize().width) / 2;
		int y = (d.height - getSize().height) / 2;
		this.setLocation(x, y);

		this.setTitle("MP test");
		this.setResizable(true);
		this.repaint();
		this.setVisible(true);
	}
	
//	  @Override public void paint(Graphics g) { // @Override ermöglicht dem Compiler die Kontrolle
//			//Upcast --> mehr Funktionen in Graphics2D
//			Graphics2D g2d=(Graphics2D)g;
//			
//			// "normales Rechteck" erstellen:
//			Rectangle2D r1 = new Rectangle2D.Double( 
//				100,60, //Ecke links oben (X,Y)
//				200,10 //Breite, Höhe
//				);
//			
//			Rectangle2D r2 = new Rectangle2D.Double( 
//					50,60, //Ecke links oben (X,Y)
//					r1.getWidth()*0.7,10 //Breite, Höhe
//					);
//			
//			// nun füllen wir die Rechtecke mit Farben:
//			g2d.setPaint( Color.red );
//			g2d.fill( r1 );		
//			
//			// nun füllen wir die Rechtecke mit Farben:
//			g2d.setPaint( Color.blue );
//			g2d.fill( r2 );	
//		  }

	public void neueRunde() {
		createSpielfeldFeld();
		setSpielfeldStatusVerdeckt();
	}

	public static void refreshLabels() {
		lab_SpielModus.setText("Modus: " + Spiel.getSpielModus());
		lab_Restminen.setText("Minen: " + Integer.toString(Spiel.getRestMinen()));
	}

	private void resetMinenWerte() {
		Spiel.setRestMinen(Spiel.getMinenGesamt());
		Spiel.setMinenRichtig(0);
	}

	private void resetSpielfeldStatusToFeld() {
		for (int z = 1; z < Spiel.getSpielfeldZeilen() + 1; z++) {
			for (int sp = 1; sp < Spiel.getSpielfeldSpalten() + 1; sp++) {

				// Text bzw. Bilder bzw. Rahmen entfernen
				Felder[z][sp].setText(null);
				Felder[z][sp].setIcon(null);
				Felder[z][sp].setBorder(null);
			}
		}
	}

	public void setSpielfeldStatusVerdeckt() {

		for (int z = 1; z < Spiel.getSpielfeldZeilen() + 1; z++) {
			for (int sp = 1; sp < Spiel.getSpielfeldSpalten() + 1; sp++) {
				Felder[z][sp].setIcon(new ImageIcon(getClass().getResource("img/felder/nicht-aufgedeckt.gif")));

				// Debug um aufgedeckte Spielfeld zu erhalten

				// switch (Spiel.getSpielfeldStatus(z,sp))
				// {
				// case -1: GUI_Spielfeld.Felder[z][sp].setIcon(new
				// ImageIcon(getClass().getResource("img/felder/aufgedeckt-mine.gif")));
				// break;
				// case 0: GUI_Spielfeld.Felder[z][sp].setIcon(new
				// ImageIcon(getClass().getResource("img/felder/aufgedeckt-leer.gif")));
				// break;
				// case 1: GUI_Spielfeld.Felder[z][sp].setIcon(new
				// ImageIcon(getClass().getResource("img/felder/aufgedeckt-1.gif")));
				// break;
				// case 2: GUI_Spielfeld.Felder[z][sp].setIcon(new
				// ImageIcon(getClass().getResource("img/felder/aufgedeckt-2.gif")));
				// break;
				// case 3: GUI_Spielfeld.Felder[z][sp].setIcon(new
				// ImageIcon(getClass().getResource("img/felder/aufgedeckt-3.gif")));
				// break;
				// case 4: GUI_Spielfeld.Felder[z][sp].setIcon(new
				// ImageIcon(getClass().getResource("img/felder/aufgedeckt-4.gif")));
				// break;
				// case 5: GUI_Spielfeld.Felder[z][sp].setIcon(new
				// ImageIcon(getClass().getResource("img/felder/aufgedeckt-5.gif")));
				// break;
				// case 6: GUI_Spielfeld.Felder[z][sp].setIcon(new
				// ImageIcon(getClass().getResource("img/felder/aufgedeckt-6.gif")));
				// break;
				// case 7: GUI_Spielfeld.Felder[z][sp].setIcon(new
				// ImageIcon(getClass().getResource("img/felder/aufgedeckt-7.gif")));
				// break;
				// case 8: GUI_Spielfeld.Felder[z][sp].setIcon(new
				// ImageIcon(getClass().getResource("img/felder/aufgedeckt-8.gif")));
				// break;
				// }
			}
		}
	}

	public void setSpielfeldStatusNachPause() {

		for (int z = 1; z < Spiel.getSpielfeldZeilen() + 1; z++) {
			for (int sp = 1; sp < Spiel.getSpielfeldSpalten() + 1; sp++) {

				if (Spiel.spielfeldGeklickt[z][sp] == 1) {
					switch (Spiel.getSpielfeldStatus(z, sp)) {
					case -1:
						GUI_Spielfeld.Felder[z][sp]
								.setIcon(new ImageIcon(getClass().getResource("img/felder/aufgedeckt-mine.gif")));
						break;
					case 0:
						GUI_Spielfeld.Felder[z][sp]
								.setIcon(new ImageIcon(getClass().getResource("img/felder/aufgedeckt-leer.gif")));
						break;
					case 1:
						GUI_Spielfeld.Felder[z][sp]
								.setIcon(new ImageIcon(getClass().getResource("img/felder/aufgedeckt-1.gif")));
						break;
					case 2:
						GUI_Spielfeld.Felder[z][sp]
								.setIcon(new ImageIcon(getClass().getResource("img/felder/aufgedeckt-2.gif")));
						break;
					case 3:
						GUI_Spielfeld.Felder[z][sp]
								.setIcon(new ImageIcon(getClass().getResource("img/felder/aufgedeckt-3.gif")));
						break;
					case 4:
						GUI_Spielfeld.Felder[z][sp]
								.setIcon(new ImageIcon(getClass().getResource("img/felder/aufgedeckt-4.gif")));
						break;
					case 5:
						GUI_Spielfeld.Felder[z][sp]
								.setIcon(new ImageIcon(getClass().getResource("img/felder/aufgedeckt-5.gif")));
						break;
					case 6:
						GUI_Spielfeld.Felder[z][sp]
								.setIcon(new ImageIcon(getClass().getResource("img/felder/aufgedeckt-6.gif")));
						break;
					case 7:
						GUI_Spielfeld.Felder[z][sp]
								.setIcon(new ImageIcon(getClass().getResource("img/felder/aufgedeckt-7.gif")));
						break;
					case 8:
						GUI_Spielfeld.Felder[z][sp]
								.setIcon(new ImageIcon(getClass().getResource("img/felder/aufgedeckt-8.gif")));
						break;
					}
				}
				if (Spiel.spielfeldGeklickt[z][sp] == 3) {
					GUI_Spielfeld.Felder[z][sp].setIcon(new ImageIcon(getClass().getResource("img/felder/fahne.gif")));
				}
			}
		}
	}

	private void setupGUI() {
		initFrame();
		addMenubar();
		ObjectHandler.getGui_AddMenubar().setOnOffForGUIspielfeld(true);
		createButtons();
		createLabels();
		zeitAnzeigen();
	}

	public void spielNeueRunde() {
		Spiel.createSpiel();
		Spielfeldgesperrt = false;
		resetMinenWerte();
		refreshLabels();
		resetSpielfeldStatusToFeld();
		setSpielfeldStatusVerdeckt();

		lab_Spielzeit.setText("Spielzeit: 0");
		zeitAnzeigenStart();

		closeGUI_SpielEnde();
	}

	private void spielNeues() {
		
		closeGUI_SpielEnde();
		ObjectHandler.getGui_Spielfeld().dispose();
		ObjectHandler.setGui_Spielfeld(null);
		ObjectHandler.setSpiel(null);
		ObjectHandler.createGui_Start();

	}

	public void spielNochmal() {
		Spielfeldgesperrt = false;
		resetMinenWerte();
		refreshLabels();
		Spiel.initSpielfeldGeklickt();
		resetSpielfeldStatusToFeld();
		setSpielfeldStatusVerdeckt();

		Spiel.zeitMessungStart();

		lab_Spielzeit.setText("Spielzeit: 0 Sek.");
		zeitAnzeigenStart();

		closeGUI_SpielEnde();
	}

	private void spielStart() {
		Spieler spieler = ObjectHandler.getSpieler();

		// Labels Text setzen
		lab_SpielerName.setText(spieler.getSpielerName());
		resetMinenWerte();
		refreshLabels();
	}

	public void spielEndeMinenAufdecken() {
		for (int z = 1; z < Spiel.getSpielfeldZeilen() + 1; z++) {
			for (int sp = 1; sp < Spiel.getSpielfeldSpalten() + 1; sp++) {

				if (Spiel.getSpielfeldStatus(z, sp) == -1) {
					if (Spiel.spielfeldGeklickt[z][sp] != 1 && Spiel.spielfeldGeklickt[z][sp] != 3) {
						Felder[z][sp].setIcon(new ImageIcon(getClass().getResource("img/felder/aufgedeckt-mine.gif")));

						// Rahmen setzen
						Border roterRahmen = new LineBorder(Color.RED, 1);
						Felder[z][sp].setBorder(roterRahmen);
					}
				}

				if (Spiel.spielfeldGeklickt[z][sp] == 3) {
					if (Spiel.getSpielfeldStatus(z, sp) != -1) {
						Felder[z][sp].setIcon(new ImageIcon(getClass().getResource("img/felder/fahne-falsch.gif")));

						// Rahmen setzen
						Border roterRahmen = new LineBorder(Color.ORANGE, 1);
						Felder[z][sp].setBorder(roterRahmen);
					}
				}
			}
		}
	}

	public static void zeitAnzeigen() {

		spielzeit = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Spiel.spielPause == 0) {
					btn_SpielZeit
							.setText("(>) Spielzeit: " + zeitformatMMSS((Spiel.zeitMessungAktuell() - 1000) / 1000));
				} else {
					btn_SpielZeit
							.setText("(||) Spielzeit: " + zeitformatMMSS((Spiel.zeitMessungAktuell() - 1000) / 1000));
				}
				if (Spielfeldgesperrt == true) {
					spielzeit.stop();
				}
			}
		});
		zeitAnzeigenStart();
	}

	private void zeitAnzeigenPause() {
		if (Spiel.spielPause == 0) {
			setSpielfeldStatusVerdeckt();
			Spielfeldgesperrt = true;
			Spiel.zeitMessungPause();
			Spiel.spielPause = 1;
		} else {
			setSpielfeldStatusNachPause();
			Spielfeldgesperrt = false;
			Spiel.zeitMessungPause();
			zeitAnzeigenStart();
			Spiel.spielPause = 0;
		}
	}

	private static void zeitAnzeigenStart() {
		spielzeit.start();
	}

	private static String zeitformatMMSS(long spielZeit) {

		String ausgabe = "";
		String minuten = "";
		String sekunden = "";

		Double minute = Math.floor(((spielZeit) % 3600) / 60);
		int min = minute.intValue();

		Double sekunde = Math.floor(((spielZeit) % 60));
		int sek = sekunde.intValue();

		// Zeit Minute
		if ((min) < 10) {
			minuten = "0" + (min);
		} else {
			minuten = "" + min;
		}
		if ((sek) < 10) {
			sekunden = "0" + (sek);
		} else {
			sekunden = "" + sek;
		}

		ausgabe = minuten + ":" + sekunden;

		return ausgabe;
	}

	private void initClassVariables() {

		gui_SpielEnde = new JFrame();
		gbc = new GridBagConstraints();

		cpTop = new Container();
		panSpielfeld = new JPanel(); // Gridlayout

		Spielfeldgesperrt = false;

		frameHeight = 0;
		frameWidth = 0;
		feldHeight = 25;
		feldWidth = 25;

		lab_SpielerName = new JLabel();
		lab_SpielModus = new JLabel();
		lab_Spielzeit = new JLabel(); // deaktiviert, wird später für die
										// Spielzeit verwendet
		lab_Restminen = new JLabel();
		lab_SpielEndeInformation = new JLabel();

		btn_SpielZurueck = new JButton();
		btn_SpielNochmal = new JButton();
		btn_SpielNeueRunde = new JButton();
		btn_SpielNeues = new JButton();
		btn_SpielZeit = new JButton();

		Felder = new Feld[Spiel.getSpielfeldZeilen() + 2][Spiel.getSpielfeldSpalten() + 2];

	}

	private void clearClassVariables() {

		gui_SpielEnde = null;
		gbc = null;

		cpTop = null;
		panSpielfeld = null;

		Spielfeldgesperrt = false;

		frameHeight = 0;
		frameWidth = 0;
		feldHeight = 25;
		feldWidth = 25;

		lab_SpielerName = null;
		lab_SpielModus = null;
		lab_Spielzeit = null;
		lab_Restminen = null;
		lab_SpielEndeInformation = null;

		btn_SpielZurueck = null;
		btn_SpielNochmal = null;
		btn_SpielNeueRunde = null;
		btn_SpielNeues = null;
		btn_SpielZeit = null;

		Felder = null;

	}

}