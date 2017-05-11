package bla;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class GUI_Spielfeld extends JFrame {

	static String versiont = "A.3.17";

	// Frame-Containe-Panel
	private JFrame gui_SpielEnde = new JFrame();
	GridBagConstraints gbc = new GridBagConstraints();

	// Container für Borderlayout: TOP
	private Container cpTop = new Container();
	private static JPanel panSpielfeld = new JPanel(); // Gridlayout

	// Variablen
	// Boolean
	static boolean Spielfeldgesperrt = false;

	// Integer
	private int frameHeight = 0;
	private int frameWidth = 0;
	private int feldHeight = 25;
	private int feldWidth = 25;
	// private int feldAbstand = 2;

	// GUI-Elemente
	// Labels - guiSpielfeld
	private JLabel lab_SpielerName = new JLabel();
	private static JLabel lab_SpielModus = new JLabel();

	private static JLabel lab_MinenRichtig = new JLabel();
	private static JLabel lab_Restminen = new JLabel();
	
	// GUI - SpielEnde
	JLabel lab_SpielEndeInformation = new JLabel();
	JButton btn_SpielZurueck = new JButton();
	JButton btn_SpielNochmal = new JButton();
	JButton btn_SpielNeueRunde = new JButton();
	JButton btn_SpielNeues = new JButton();

	// Arrays
	static Feld[][] Felder = new Feld[Spiel.getSpielfeldZeilen()][Spiel.getSpielfeldSpalten()];

	public GUI_Spielfeld() {

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

	private void createButtons() {

		JButton btn_SpielerProfil = new JButton();
		JButton btn_SpielNeustart = new JButton();

		btn_SpielerProfil.setIcon(new ImageIcon(getClass().getResource("img/profil.jpg")));
		btn_SpielerProfil.setBounds(4, 5, 40, 40);
		btn_SpielerProfil.setVisible(true);
		btn_SpielerProfil.setMargin(new Insets(2, 2, 2, 2));
		cpTop.add(btn_SpielerProfil);
		btn_SpielerProfil.addActionListener(e -> ObjectHandler.createGui_Spielerprofil());

		btn_SpielNeustart.setIcon(new ImageIcon(getClass().getResource("img/neustart.png")));
		btn_SpielNeustart.setToolTipText("Startet aktuelle Runde neu.");
		btn_SpielNeustart.setBounds(this.getWidth() - 60, 5, 40, 40);
		btn_SpielNeustart.setMargin(new Insets(2, 2, 2, 2));
		cpTop.add(btn_SpielNeustart);
		btn_SpielNeustart.addActionListener(e -> spielNochmal());

		// Button-Rahmen für später nützlich

		// reload.addMouseListener(new java.awt.event.MouseAdapter() {
		// public void mouseEntered(java.awt.event.MouseEvent evt) {
		// //reload.setBackground(Color.GREEN);
		//
		// reload.setBorder(BorderFactory.createLineBorder(Color.red));
		// }
		//
		// public void mouseExited(java.awt.event.MouseEvent evt) {
		// // reload.setBackground(UIManager.getColor("control"));
		// reload.setBorder(BorderFactory.createLineBorder(null));
		// }
		// });
	}

	private void createLabels() {

		Border border = LineBorder.createGrayLineBorder();
		JLabel lab_Version = new JLabel();

		// GUI-Spielfeld
		lab_SpielerName.setBounds(50, 5, 200, 40);
		lab_SpielerName.setFont(new Font("Dialog", Font.PLAIN, 35));
		lab_SpielerName.setBorder(border);
		lab_SpielerName.setVisible(true);
		cpTop.add(lab_SpielerName);

		lab_SpielModus.setBounds(255, 0, 120, 20);
		lab_SpielModus.setVisible(false);
		lab_SpielModus.setFont(new Font("Dialog", Font.PLAIN, 11));
		lab_SpielModus.setVisible(true);
		cpTop.add(lab_SpielModus);

		lab_Restminen.setBounds(255, 14, 100, 20);
		lab_Restminen.setVisible(true);
		lab_Restminen.setFont(new Font("Dialog", Font.PLAIN, 11));
		cpTop.add(lab_Restminen);

		// debug anzeige
		lab_MinenRichtig.setBounds(255, 28, 100, 20);
		lab_MinenRichtig.setVisible(true);
		lab_MinenRichtig.setFont(new Font("Dialog", Font.PLAIN, 11));
		cpTop.add(lab_MinenRichtig);

		lab_Version.setBounds(213, 2, 120, 20); // 100
		lab_Version.setVisible(true);
		lab_Version.setText(versiont);
		// debug: lab_Version.setText(" W: "+this.getWidth()+", H:
		// "+this.getHeight());
		cpTop.add(lab_Version);
	}

	private void createSpielfeldFeld() {

		for (int z = 0; z < Spiel.getSpielfeldZeilen(); z++) {
			for (int sp = 0; sp < Spiel.getSpielfeldSpalten(); sp++) {
				// Erzeugen der Felder und Eigenschaften setzen
				Felder[z][sp] = new Feld();
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

		this.getContentPane().add(panSpielfeld, BorderLayout.CENTER);
	}

	public void GUI_SpielEnde(String SiegNiederlage) { // SpielEnde: Sieg-Niederlage Frame

		// Frame-Initialisierung
		gui_SpielEnde.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		gui_SpielEnde.setSize(380, 170);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (d.width - getSize().width) / 2;
		int y = (d.height - getSize().height) / 2;
		gui_SpielEnde.setLocation(x, y);
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
		btn_SpielNeues.setEnabled(false);

		// Labels
		lab_SpielEndeInformation.setBounds(10, 10, 250, 65);
		lab_SpielEndeInformation.setVisible(true);
		lab_SpielEndeInformation.setText("<HTML><font size=14><i>" + SiegNiederlage + "!</i></font><br>Spieldauer: "
				+ ObjectHandler.getSpieler().getZeitLetztesSpiel() / 1000 + " Sekunden. </HTML>");
		cpGUI_SpielEnde.add(lab_SpielEndeInformation);

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
		}

		cpTop.setLayout(null);
		cpTop.setPreferredSize(new Dimension(600, 50));
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
		// panSpielfeld.setLayout(new GridLayout(Spiel.getSpielfeldZeilen(),
		// Spiel.getSpielfeldSpalten(), feldAbstand,feldAbstand));
		panSpielfeld.setLayout(new GridBagLayout());

		// 73 anscheinend top und 23 menubar
		frameHeight = Spiel.getSpielfeldZeilen() * (feldHeight) + Spiel.getSpielfeldZeilen() * 4 + 16 + 73 + 23;
		frameWidth = Spiel.getSpielfeldSpalten() * (feldWidth) + Spiel.getSpielfeldSpalten() * 4 + 16 + extraBreiteLinks
				+ extraBreiteRechts;

		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		this.setSize(frameWidth, frameHeight);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (d.width - getSize().width) / 2;
		int y = (d.height - getSize().height) / 2;
		this.setLocation(x, y);
		//this.setLocation(x - frameWidth / 2, y - frameHeight / 2);

		this.setTitle("Projekt 'Seawolf' <no GameApp actually found>");
		this.setResizable(true);
		this.repaint();
		this.setVisible(true);
	}

	public void neueRunde() {
		createSpielfeldFeld();
		setSpielfeldStatusVerdeckt();
	}
	
	public static void refreshLabels() {
		lab_SpielModus.setText("Modus: " + Spiel.getSpielModus());
		lab_Restminen.setText("Minen: " + Integer.toString(Spiel.getRestMinen()));
		lab_MinenRichtig.setText("Mine Richtig: " + Integer.toString(Spiel.getMinenRichtig()));
	}
	
	private void resetMinenWerte() {
		Spiel.setRestMinen(Spiel.getMinenGesamt());
		Spiel.setMinenRichtig(0);
	}

	private void resetSpielfeldStatusToFeld() {
		for (int z = 0; z < Spiel.getSpielfeldZeilen(); z++) {
			for (int sp = 0; sp < Spiel.getSpielfeldSpalten(); sp++) {
				Felder[z][sp].setText(null);
				Felder[z][sp].setIcon(null);
			}
		}
	}

	public void setSpielfeldStatusVerdeckt () {
		
		for (int z=0; z<Spiel.getSpielfeldZeilen(); z++) {
			for (int sp=0; sp<Spiel.getSpielfeldSpalten(); sp++) {
				Felder[z][sp].setIcon(new ImageIcon(getClass().getResource("img/felder/nicht-aufgedeckt.gif")));
			}
		}
	}

	private void setupGUI() {
		initFrame();
		addMenubar();
		createButtons();
		createLabels();

	}

	public void spielNeueRunde() {

		Spiel.createSpiel();
		Spielfeldgesperrt = false;
		resetMinenWerte();
		refreshLabels();
		resetSpielfeldStatusToFeld();
		setSpielfeldStatusVerdeckt();
		
		closeGUI_SpielEnde();

	}

	private void spielNeues() {

		closeGUI_SpielEnde();
		//Spielfeld Frame schließen
		dispose();
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

		closeGUI_SpielEnde();
	}

	private void spielStart() {
		Spieler spieler = ObjectHandler.getSpieler();
		
		// Labels Text setzen
		lab_SpielerName.setText(spieler.getSpielerName());
		resetMinenWerte();
		refreshLabels();
	}	
}
