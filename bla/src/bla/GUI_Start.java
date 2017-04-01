package bla;

import java.awt.Container;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GUI_Start extends JFrame {
	
	private JButton btn_ModusLeicht = new JButton();
	private JButton btn_ModusMittel = new JButton();
	private JButton btn_ModusSchwer = new JButton();
	private JButton btn_ModusBenutzer = new JButton();

	public GUI_Start() {
		// TODO Auto-generated constructor stub
		//JFrame fenster = new JFrame("Ihr JFrame");
		setSize(600, 280);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		JTabbedPane tabLeiste = new JTabbedPane();
		JPanel panel = new JPanel();
		tabLeiste.addTab("Einzelspieler", panel);

		JPanel panel2 = new JPanel();
		panel2.add(new JLabel("Dauert noch eine weile..."));
		tabLeiste.addTab("Mehrspieler", panel2);

		add(tabLeiste);
		setTitle("Neues Spiel");
		setResizable(false);
		setVisible(true);
		
		
	    // Menüleiste
		JMenuBar menueLeiste = new JMenuBar();
		
		// Menüleiste Elemente
		JMenu men_spiel = new JMenu("Spiel");
		JMenu men_ueber = new JMenu("Über");
		
		// Untermenü + Linksklick-Methode
		JMenuItem men_spiel_neu = new JMenuItem("Neues Spiel /disabled");
		men_spiel_neu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				//GUI_Start neu = new GUI_Start();		
			}
		});
		JMenuItem men_spiel_beenden = new JMenuItem("Beenden /Fenster schliessen");
		men_spiel_beenden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				dispose();		
			}
		});
		JMenuItem men_ueber_version = new JMenuItem("Version");
		men_ueber_version.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				JOptionPane.showMessageDialog(null, "Version: "+Test.versiont);
			}
		});
		
		//Menüleiste
		//Menüleiste hinzufügen
		add(menueLeiste);
		//Hauptmenü-Punkte hinzufügen
		menueLeiste.add(men_spiel);
		menueLeiste.add(men_ueber);
		//Untermenü-Punkte hinzufügen
		men_spiel.add(men_spiel_neu);
		men_spiel.add(men_spiel_beenden);
		men_ueber.add(men_ueber_version);
		//MenueLeiste dem JFrame zuordnen
		setJMenuBar(menueLeiste);
		
		
		
		// GUI Elemente
		// Buttons
		btn_ModusLeicht.setBounds(20, 60, 125, 125);
		btn_ModusLeicht.setIcon(new ImageIcon(getClass().getResource("img/modus_leicht.png")));
		btn_ModusLeicht.setMargin(new Insets(0, 0, 0, 0));
		panel.add(btn_ModusLeicht);
		
		btn_ModusMittel.setBounds(165, 100, 125, 125);
		btn_ModusMittel.setIcon(new ImageIcon(getClass().getResource("img/modus_mittel.png")));
		btn_ModusMittel.setMargin(new Insets(0, 0, 0, 0));
		panel.add(btn_ModusMittel);
		
		btn_ModusSchwer.setBounds(310, 60, 125, 125);
		btn_ModusSchwer.setIcon(new ImageIcon(getClass().getResource("img/modus_schwer.png")));
		btn_ModusSchwer.setMargin(new Insets(0, 0, 0, 0));
		panel.add(btn_ModusSchwer);
		
		btn_ModusBenutzer.setBounds(455, 60, 125, 125);
		btn_ModusBenutzer.setIcon(new ImageIcon(getClass().getResource("img/modus_benutzer.png")));
		btn_ModusBenutzer.setMargin(new Insets(0, 0, 0, 0));
		panel.add(btn_ModusBenutzer);
	}
}
