package bla;

import java.awt.Font;
import java.awt.Insets;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.event.*;
import javax.swing.*;


public class GUI_Start extends JFrame implements ActionListener {
	// Objekte
	private static Spieler spielerEins = new Spieler();
		
	// Frame-Container-Tabs
	private JFrame gui_Start = new JFrame();
	private JPanel panel = new JPanel();	//Tab Einzelspieler
	private JPanel panel2 = new JPanel();	//Tab Mehrspieler
	private JTabbedPane tabLeiste = new JTabbedPane();
	
	// Varialblen
	private static boolean spielerAuswahlAnzeigen = true;

	// Buttons
	private static JButton btn_ModusLeicht = new JButton();
	private static JButton btn_ModusMittel = new JButton();
	private static JButton btn_ModusSchwer = new JButton();
	private static JButton btn_ModusBenutzer = new JButton();
	private static JButton btn_SpielerProfil = new JButton();
		
	// Labels
	private static JLabel lab_SpielerName = new JLabel();
	
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
		setupGUI();
		
	}
	
	private void setupGUI() {
		
		initFrame();
		createButtons();
		createLabels();
		addMenubar();
		
		gui_Start.setVisible(true);
		
		if (spielerAuswahlAnzeigen == true) {
			GUI_SpielerAuswahl frame = new GUI_SpielerAuswahl(gui_Start, spielerEins);
		}
		else {
			aktiviereGUI_Start();
		}
		panel.repaint();	
	}
	
	
	private void addMenubar() {
		JMenuBar menubar = new GUI_AddMenubar();
		gui_Start.setJMenuBar(menubar);
	}

	private void createLabels() {
		Border border = LineBorder.createGrayLineBorder();
		lab_SpielerName.setBounds(400, 5, 160, 40);
		lab_SpielerName.setFont(new Font("Dialog", Font.PLAIN, 35));
		lab_SpielerName.setBorder(border);
		lab_SpielerName.setVisible(false);
		panel.add(lab_SpielerName);
	}

	private void createButtons() {
		btn_SpielerProfil.setIcon(new ImageIcon(getClass().getResource("img/profil.jpg")));
		btn_SpielerProfil.setBounds(350, 5, 40, 40);
		btn_SpielerProfil.setMargin(new Insets(2, 2, 2, 2));
		btn_SpielerProfil.setEnabled(false);
		btn_SpielerProfil.setVisible(false);
		panel.add(btn_SpielerProfil);
		btn_SpielerProfil.addActionListener(this);
		
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
	}

	private void initFrame() {
		gui_Start.setSize(585, 280);
		gui_Start.setLocationRelativeTo(null);
		gui_Start.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		gui_Start.add(tabLeiste);
		gui_Start.setTitle("Projekt 'Seawolf'");
		gui_Start.setResizable(false);
		
		panel.setLayout(null);
		tabLeiste.addTab("Einzelspieler", panel);

		panel2.setLayout(null);
		panel2.add(new JLabel("Dauert noch eine weile..."));
		tabLeiste.addTab("Mehrspieler", panel2);
		
		gui_Start.setVisible(true);
	}

	public void actionPerformed(ActionEvent object) {
		
		// Linksklick GUI-Buttons
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
			GUI_SpielModusBenutzer frame = new GUI_SpielModusBenutzer(gui_Start, spielerEins);
		}
	}

	public static void setSpielerAuswahlAnzeigen(Boolean anAus) {
		spielerAuswahlAnzeigen = anAus;
	}
	
	
	public static void aktiviereGUI_Start() {
		
		lab_SpielerName.setVisible(true);
		lab_SpielerName.setText(spielerEins.getSpielerName());
		btn_SpielerProfil.setEnabled(true);
		btn_SpielerProfil.setVisible(true);

		btn_ModusLeicht.setEnabled(true);
		btn_ModusMittel.setEnabled(true);
		btn_ModusSchwer.setEnabled(true);
		btn_ModusBenutzer.setEnabled(true);
	}

}
