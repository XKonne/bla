package bla;

import java.awt.Font;
import java.awt.Insets;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.*;

@SuppressWarnings("serial")
public class GUI_Start extends JFrame {
	// Objekte
	private static Spieler spieler;

	// Frame-Container-Tabs
	private JPanel panel = new JPanel(); // Tab Einzelspieler

	// Varialblen
	private static boolean spielerAuswahlAnzeigen = true;
	static String version = "A.3.22";

	// Buttons
	private static JButton btn_ModusLeicht = new JButton();
	private static JButton btn_ModusMittel = new JButton();
	private static JButton btn_ModusSchwer = new JButton();
	private static JButton btn_ModusBenutzer = new JButton();
	private static JButton btn_SpielerProfil = new JButton();
	private static JButton btn_SpielerWechseln = new JButton();

	// Labels
	private static JLabel lab_SpielerName = new JLabel();
	private static JLabel lab_Version = new JLabel();

	public GUI_Start() {

		ObjectHandler.setGui_Start(this);
		spieler = ObjectHandler.getSpieler();
		setupGUI();
		
	}

	private void addMenubar() {

		ObjectHandler.createGui_AddMenubar();
		this.setJMenuBar(ObjectHandler.getGui_AddMenubar());

	}

	public static void aktiviereGUI_Start() {

		lab_SpielerName.setVisible(true);
		lab_SpielerName.setText(ObjectHandler.getSpieler().getSpielerName());
		btn_SpielerProfil.setEnabled(true);
		btn_SpielerProfil.setVisible(true);
		btn_SpielerWechseln.setEnabled(true);
		btn_SpielerWechseln.setVisible(true);

		btn_ModusLeicht.setEnabled(true);
		btn_ModusMittel.setEnabled(true);
		btn_ModusSchwer.setEnabled(true);
		btn_ModusBenutzer.setEnabled(true);
	}

	public void closeGUI_Start() {
		this.dispose();
		ObjectHandler.setGui_Start(null);
	}

	private void createButtons() {
		btn_SpielerProfil.setIcon(new ImageIcon(getClass().getResource("img/profil.jpg")));
		btn_SpielerProfil.setBounds(305, 5, 40, 40);
		btn_SpielerProfil.setMargin(new Insets(2, 2, 2, 2));
		btn_SpielerProfil.setEnabled(false);
		btn_SpielerProfil.setVisible(false);
		panel.add(btn_SpielerProfil);
		btn_SpielerProfil.addActionListener(e->ObjectHandler.createGui_Spielerprofil());
		
		btn_SpielerWechseln.setIcon(new ImageIcon(getClass().getResource("img/spielerWechseln.gif")));
		btn_SpielerWechseln.setBounds(540, 5, 20, 40);
		btn_SpielerWechseln.setMargin(new Insets(2, 2, 2, 2));
		btn_SpielerWechseln.setEnabled(false);
		btn_SpielerWechseln.setVisible(false);
		panel.add(btn_SpielerWechseln);
		btn_SpielerWechseln.addActionListener(e->spielerWechseln());

		btn_ModusLeicht.setBounds(15, 60, 125, 125);
		btn_ModusLeicht.setIcon(new ImageIcon(getClass().getResource("img/modus_leicht.png")));
		btn_ModusLeicht.setMargin(new Insets(0, 0, 0, 0));
		btn_ModusLeicht.setEnabled(false);
		panel.add(btn_ModusLeicht);
		btn_ModusLeicht.addActionListener(e->setSpielModusLeicht());

		btn_ModusMittel.setBounds(155, 60, 125, 125);
		btn_ModusMittel.setIcon(new ImageIcon(getClass().getResource("img/modus_mittel.png")));
		btn_ModusMittel.setMargin(new Insets(0, 0, 0, 0));
		btn_ModusMittel.setEnabled(false);
		panel.add(btn_ModusMittel);
		btn_ModusMittel.addActionListener(e->setSpielModusMittel());

		btn_ModusSchwer.setBounds(295, 60, 125, 125);
		btn_ModusSchwer.setIcon(new ImageIcon(getClass().getResource("img/modus_schwer.png")));
		btn_ModusSchwer.setMargin(new Insets(0, 0, 0, 0));
		btn_ModusSchwer.setEnabled(false);
		panel.add(btn_ModusSchwer);
		btn_ModusSchwer.addActionListener(e->setSpielModusSchwer());

		btn_ModusBenutzer.setBounds(435, 60, 125, 125);
		btn_ModusBenutzer.setIcon(new ImageIcon(getClass().getResource("img/modus_benutzer.png")));
		btn_ModusBenutzer.setMargin(new Insets(0, 0, 0, 0));
		btn_ModusBenutzer.setEnabled(false);
		panel.add(btn_ModusBenutzer);
		btn_ModusBenutzer.addActionListener(e -> ObjectHandler.createGui_SpielModusBenutzer());
	}

	private void spielerWechseln() {
		ObjectHandler.createGui_SpielerAuswahl();
	}

	private void createLabels() {
		Border border = LineBorder.createGrayLineBorder();
		lab_SpielerName.setBounds(350, 5, 185, 40);
		lab_SpielerName.setFont(new Font("Dialog", Font.PLAIN, 35));
		lab_SpielerName.setBorder(border);
		lab_SpielerName.setVisible(false);
		panel.add(lab_SpielerName);
		
		lab_Version.setBounds(5, 0, 120, 20);
		lab_Version.setVisible(true);
		lab_Version.setText(version);
		panel.add(lab_Version);
	}

	private void initFrame() {

		JTabbedPane tabLeiste = new JTabbedPane();

		this.setSize(585, 280);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.add(tabLeiste);
		this.setTitle("Namenloses Programmierprojekt");
		this.setResizable(false);

		panel.setLayout(null);
		tabLeiste.addTab("Einzelspieler", panel);

		// Tab Mehrspieler
		// TODO erst einmal deaktiviert
//		JPanel panel2 = new JPanel();
//		panel2.setLayout(null);
//		panel2.add(new JLabel("Dauert noch eine weile..."));
//		tabLeiste.addTab("Mehrspieler", panel2);

		this.setVisible(true);
	}

	public static void setSpielerAuswahlAnzeigen(Boolean anAus) {
		spielerAuswahlAnzeigen = anAus;
	}
	
	private void setSpielModusLeicht() {
		Spiel.setSpielModus(8, 8, 10, "Leicht", spieler);
		closeGUI_Start();
	}

	private void setSpielModusMittel() {
		Spiel.setSpielModus(16, 16, 40, "Mittel", spieler);
		closeGUI_Start();
	}

	private void setSpielModusSchwer() {
		Spiel.setSpielModus(24, 16, 60, "Schwer", spieler);
		closeGUI_Start();
	}

	private void setupGUI() {

		initFrame();
		createButtons();
		createLabels();
		addMenubar();
		ObjectHandler.getGui_AddMenubar().setOnOffForGUIstart(false);
		this.setVisible(true);

		if (spielerAuswahlAnzeigen == true) {
			ObjectHandler.createGui_SpielerAuswahl();
		} else {
			aktiviereGUI_Start();
		}

		panel.repaint();

	}

}
