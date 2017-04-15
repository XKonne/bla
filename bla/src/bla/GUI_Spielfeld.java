package bla;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class GUI_Spielfeld extends JFrame implements ActionListener {
	
	// Objekte
//	private static Spiel spiel;			// ich glaube das Objekt Spiel wird gar nicht mehr hier benutzt
	private static Spieler spielerT;
	
	// Frame-Containe-Panel
	private JFrame gui_Spielfeld = new JFrame();
	private JFrame gui_SpielEnde = new JFrame();
	
	// Hauptcontainer mit Borderlayout
	private Container cp = gui_Spielfeld.getContentPane();
	// Container f�r Borderlayout: TOP, LEFT, CENTER, RIGHT, BOT
	private Container cpTop = new Container();
	private Container cpLeft = new Container();
	private JPanel panSpielfeld = new JPanel();		//Gridlayout
	private Container cpRight = new Container();
	private Container cpBot = new Container();
		
	// Variablen
	// Boolean
	static boolean Spielfeldgesperrt = true;
	
	// Integer
	private int frameHeight=0;
	private int frameWidth=0;
	private int feldHeight = 25;
	private int feldWidth = 25;
	private int feldAbstand = 2;
	
	// Long
	private static long zeittmp;

	// String
	static String versiont = "A.3.10";

	// GUI-Elemente
	// Buttons - guiSpielfeld
	private JButton btn_SpielerProfil = new JButton();
	private JButton btn_SpielNeustart = new JButton();

	// Buttons - guiSpielEnde
	private JButton btn_SpielZurueck = new JButton();
	private JButton btn_SpielNochmal = new JButton();
	private JButton btn_SpielNeueRunde = new JButton();
	private JButton btn_SpielNeues = new JButton();
	
	// Labels - guiSpielfeld
	private JLabel lab_SpielerName = new JLabel();
	private JLabel lab_SpielModus = new JLabel();
	private JLabel lab_Version = new JLabel();
	private static JLabel lab_MinenRichtig = new JLabel();
	private static JLabel lab_Restminen = new JLabel();
	
	// Labels - guiSpielEnde
	private JLabel lab_SpielEndeInformation = new JLabel();
		
	// Arrays
	Feld[][] Felder = new Feld[Spiel.getSpielfeldZeilen()][Spiel.getSpielfeldSpalten()];
	
	
	public GUI_Spielfeld(Spieler spieler) {
		spielerT = spieler;
		
		setupGUI();
		
		neueRunde();
		
		spielStart();
	}

	private void setupGUI() {
		initFrame();
		createButtons();
		createLabels();
		addMenubar();
		
	}
	
	public void neueRunde() {
		createSpielfeldFeld();
		setSpielfeldStatusZuFeld();
	}
	
	private void addMenubar() {
		JMenuBar menubar = new GUI_AddMenubar();
		gui_Spielfeld.setJMenuBar(menubar);
	}
	
	private void spielStart() {
		// TODO muss �berpr�ft werden was man hier noch braucht
//		// Spielfeld aktivieren
//		setSpielfeldAnAus(true);
//		Spielfeldgesperrt = false;
//
//		btn_SpielerProfil.setVisible(true);
//
//		zeittmp = Spiel.zeitmessungStart();
//		
//		// Spielfeldgeklickt Array initialisieren
//		for (int i = 0; i < Spielfeldgeklickt.length; i++) {
//			Spielfeldgeklickt[i] = 0;
//		}
		
		// Labels Text setzen
		lab_SpielerName.setText(spielerT.getSpielerName());
		Spiel.setRestMinen(Spiel.getMinenGesamt());
		lab_Restminen.setText("Minen: " + Integer.toString(Spiel.getRestMinen()));
		Spiel.setMinenRichtig(0);
		lab_MinenRichtig.setText("Mine Richtig: " + Integer.toString(Spiel.getMinenRichtig()));
		lab_SpielModus.setText("Modus: "+Spiel.getSpielModus());
}
		
	private void createLabels() {
		
		Border border = LineBorder.createGrayLineBorder();

		//GUI-Spielfeld
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

		lab_Version.setBounds(213, 2, 80, 20);
		lab_Version.setVisible(true);
		lab_Version.setText(versiont);
		cpTop.add(lab_Version);
	}

	private void initFrame() {
		int extraBreite = 0;
		
		cp.setLayout(new BorderLayout());
		
		// Bei 8x8 Spielen ist das Spielfeld zu klein, durch einen Rand wird es gro� genug
		if (Spiel.getSpielfeldZeilen()*feldHeight+50+75+7 < 400) {
			extraBreite = 65;
		}

		cpTop.setLayout(null);
		cpTop.setPreferredSize(new Dimension(600,50));
		gui_Spielfeld.getContentPane().add(cpTop, BorderLayout.PAGE_START);
		
		cpLeft.setLayout(null);
		cpLeft.setPreferredSize(new Dimension(extraBreite,0));
		gui_Spielfeld.getContentPane().add(cpLeft, BorderLayout.LINE_START);
		
		cpRight.setLayout(null);
		cpRight.setPreferredSize(new Dimension(extraBreite,0));
		gui_Spielfeld.getContentPane().add(cpRight, BorderLayout.LINE_END);
		
		cpBot.setLayout(null);
		cpBot.setPreferredSize(new Dimension(0,0));
		gui_Spielfeld.getContentPane().add(cpBot, BorderLayout.PAGE_END);
		
		panSpielfeld.setLayout(new GridLayout(Spiel.getSpielfeldZeilen(), Spiel.getSpielfeldSpalten(), feldAbstand,feldAbstand));
		
		
		gui_Spielfeld.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		// zeilen*feldHeight + 50 page start + 75 ?? + 30 menubar
		frameHeight=Spiel.getSpielfeldZeilen()*feldHeight+50+75+30;
		frameWidth=Spiel.getSpielfeldSpalten()*feldWidth+75+2*extraBreite;
		gui_Spielfeld.setSize(frameWidth, frameHeight);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (d.width - getSize().width) / 2;
		int y = (d.height - getSize().height) / 2;
		gui_Spielfeld.setLocation(x-frameWidth/2, y-frameHeight/2);
		
		gui_Spielfeld.setTitle("Projekt 'Seawolf' <no GameApp actually found>");
		gui_Spielfeld.setResizable(false);
		
		gui_Spielfeld.setVisible(true);
	}	
	
	private void createButtons() {
		
		btn_SpielerProfil.setIcon(new ImageIcon(getClass().getResource("img/profil.jpg")));
		btn_SpielerProfil.setBounds(4, 5, 40, 40);
		btn_SpielerProfil.setVisible(true);
		btn_SpielerProfil.setMargin(new Insets(2, 2, 2, 2));
		cpTop.add(btn_SpielerProfil);
		btn_SpielerProfil.addActionListener(this);

		btn_SpielNeustart.setIcon(new ImageIcon(getClass().getResource("img/neustart.png")));
		btn_SpielNeustart.setToolTipText("Startet die Runde neu.");
		btn_SpielNeustart.setBounds(frameWidth-50, 5, 40, 40);
		btn_SpielNeustart.setMargin(new Insets(2, 2, 2, 2));
		cpTop.add(btn_SpielNeustart);
		btn_SpielNeustart.addActionListener(this);
		
		// Button-Rahmen f�r sp�ter n�tzlich
		
//		reload.addMouseListener(new java.awt.event.MouseAdapter() {
//		    public void mouseEntered(java.awt.event.MouseEvent evt) {
//		        //reload.setBackground(Color.GREEN);
//
//		        reload.setBorder(BorderFactory.createLineBorder(Color.red));
//		    }
//
//		    public void mouseExited(java.awt.event.MouseEvent evt) {
//		       // reload.setBackground(UIManager.getColor("control"));
//		        reload.setBorder(BorderFactory.createLineBorder(null));
//		    }
//		});
	}
	
	private void setSpielfeldStatusZuFeld() {

		for (int z=0; z<Spiel.getSpielfeldZeilen(); z++) {
			for (int sp=0; sp<Spiel.getSpielfeldSpalten(); sp++) {
				Felder[z][sp].setText(Integer.toString(Spiel.getSpielfeldStatus(z+1,sp+1)));
			}
		}
	}
	
	private void createSpielfeldFeld() {
				
		for (int z=0; z<Spiel.getSpielfeldZeilen(); z++) {
			for (int sp=0; sp<Spiel.getSpielfeldSpalten(); sp++) {
				Felder[z][sp] = new Feld();
				Felder[z][sp].setMargin(new Insets(0, 0, 0, 0));
				panSpielfeld.add(Felder[z][sp]);
			}
		}

		gui_Spielfeld.getContentPane().add(panSpielfeld, BorderLayout.CENTER);
		pack();
	}
	
	
	// Methoden aus "TEST"
	
	// Sieg-Niederlage Frame
	private void GUI_SpielEnde(boolean mineGetroffen) {
		{
		// Lokale Variablen
		String siegNiederlage="";
			
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
		//Container cp2 = getContentPane();
		cpGUI_SpielEnde.setLayout(null);
		
		// Frame-Elemente
		// Buttons
		btn_SpielZurueck.setBounds(10, 100, 80, 30);
		btn_SpielZurueck.setText("Zur�ck");
		btn_SpielZurueck.setMargin(new Insets(2, 2, 2, 2));
		cpGUI_SpielEnde.add(btn_SpielZurueck);
		btn_SpielZurueck.addActionListener(this);
		
		btn_SpielNochmal.setBounds(100, 100, 80, 30);
		btn_SpielNochmal.setText("Nochmal");
		btn_SpielNochmal.setMargin(new Insets(2, 2, 2, 2));
		cpGUI_SpielEnde.add(btn_SpielNochmal);
		btn_SpielNochmal.addActionListener(this);
		
		btn_SpielNeueRunde.setBounds(190, 100, 80, 30);
		btn_SpielNeueRunde.setText("Neue Runde");
		btn_SpielNeueRunde.setMargin(new Insets(2, 2, 2, 2));
		cpGUI_SpielEnde.add(btn_SpielNeueRunde);
		btn_SpielNeueRunde.addActionListener(this);
		
		btn_SpielNeues.setBounds(280, 100, 80, 30);
		btn_SpielNeues.setText("Neues Spiel");
		btn_SpielNeues.setMargin(new Insets(2, 2, 2, 2));
		cpGUI_SpielEnde.add(btn_SpielNeues);
		btn_SpielNeues.addActionListener(this);
		
		// Ausgabetext Sieg oder Niederlage
		if (mineGetroffen==false) {
			siegNiederlage = "Sieg";
		}
		else {
			siegNiederlage = "Niederlage";
		}
		
		// Labels
		lab_SpielEndeInformation.setBounds(10, 10, 250, 65);
		lab_SpielEndeInformation.setVisible(true);
		lab_SpielEndeInformation.setText("<HTML><font size=14><i>"+siegNiederlage+"!</i></font><br>Spieldauer: "+zeittmp / 1000+" Sekunden. </HTML>");
		cpGUI_SpielEnde.add(lab_SpielEndeInformation);
		
		gui_SpielEnde.setVisible(true);
	}
	}
	
	// TODO Muss an Feld angepasst werden.
	// Einzelnen Spielfeld-Button [aus MouseInput heraus] deaktivieren
	public static void setDisabled(int i) {
//		buttons[i].setEnabled(false);
	}
	
	// Alle Spielfeld-Button deaktivieren (false) oder aktivieren (true)
	private static void setSpielfeldAnAus(boolean anAus) {
		// TODO muss an Felder angepasst werden 
//		for (int i = 0; i < buttons.length; i++) {
//			buttons[i].setEnabled(anAus);
//		}
	}
	
	// GUI-Buttons Linksklick
	public void actionPerformed(ActionEvent object) {
		
		// Linksklick Men�leiste
		if (object.getSource() == btn_SpielNeustart) {
			spielNochmal();
		}
		if (object.getSource() == btn_SpielerProfil) {
			GUI_Spielerprofil profil = new GUI_Spielerprofil(spielerT);
		}
		
		// GUI_SpielEnde
		if (object.getSource() == btn_SpielZurueck) {
			gui_SpielEnde.dispose();
		}
		if (object.getSource() == btn_SpielNochmal) {
			spielNochmal();
			gui_SpielEnde.dispose();
		}
		if (object.getSource() == btn_SpielNeueRunde) {
			Spiel.createSpiel();
			setSpielfeldStatusZuFeld();
			gui_SpielEnde.dispose();
		}
		if (object.getSource() == btn_SpielNeues) {
			gui_SpielEnde.dispose();
			GUI_Start frame = new GUI_Start();
		}			
	}
	
	private static void spielNochmal() {
		// TODO an Felder anpassen
		// Felder auf Anfangswert
//		for (int i = 0; i < buttons.length; i++) {
//			buttons[i].setText(".");
//		}

		// Spielfeld aktivieren
		setSpielfeldAnAus(true);
		Spielfeldgesperrt = false;

		// Spielfeldgeklickt Array zur�cksetzen
		// TODO siehe in Spiel: doppelschleife
//		for (int i = 0; i < Spielfeldgeklickt.length; i++) {
//			Spielfeldgeklickt[i] = 0;
//		}
		
		// GUI Elemente zur Anzeige. Zur�cksetzen der Minen in Spiel
		Spiel.setRestMinen(Spiel.getMinenGesamt());
		lab_Restminen.setText("Minen: " + Integer.toString(Spiel.getRestMinen()));
		Spiel.setMinenRichtig(0);
		lab_MinenRichtig.setText("Mine Richtig: " + Integer.toString(Spiel.getMinenRichtig()));

		// TODO Eventuell nach Spiel auslagern...
		// Zeiterfassung starten
		zeittmp = Spiel.zeitmessungStart();
	}

	

}