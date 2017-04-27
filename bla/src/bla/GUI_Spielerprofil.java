package bla;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class GUI_Spielerprofil extends JFrame implements ActionListener {
	// Objekte
	private Spieler spieler;
	
	// Frame-Container
	private Container cp = getContentPane();
	
	// Variablen
	
	// GUI-Elemente
	// Buttons
	private JButton btn_SpielerprofilSchliessen = new JButton();
	private JButton btn_SpielerprofilAktualisieren = new JButton();

	// Labels
	private static JLabel lab_MinenGefunden = new JLabel();
	private static JLabel lab_profilBild = new JLabel();
	private static JLabel lab_SpieleGespielt = new JLabel();
	private static JLabel lab_SpieleGewonnen = new JLabel();
	private static JLabel lab_SpieleGewonnenProzent = new JLabel();
	private static JLabel lab_SpielHistorie = new JLabel();
	private static JLabel lab_SpielerName = new JLabel();
	private static JLabel lab_SpieleSiegesserie = new JLabel();
	private static JLabel lab_SpielVerlauf = new JLabel();
	private static JLabel lab_ZeitGesamt = new JLabel();
	private static JLabel lab_ZeitLetztesSpiel = new JLabel();
	private static JLabel lab_ZeitSchnellstesSpiel = new JLabel();
	
	

	public GUI_Spielerprofil(Spieler spieler) {
		
		this.spieler = spieler;

		setupGUI();
		
		setLabelText();
	}

	private void setupGUI() {
		
		initFrame();
		createButtons();
		createLabels();
	}
	
	public void actionPerformed(ActionEvent object) {
		if (object.getSource() == btn_SpielerprofilSchliessen) {
			dispose();
		}
		if (object.getSource() == btn_SpielerprofilAktualisieren) {
			setLabelText();
		}
	}

	private void createLabels() {
		
		lab_profilBild.setBounds(10, 10, 40, 40);
		lab_profilBild.setIcon(new ImageIcon(getClass().getResource("img/profil.jpg")));
		cp.add(lab_profilBild);
		
		// Labels - in der Reihenfolge der Anzeige
		lab_SpielerName.setBounds(60, 10, 180, 40);
		lab_SpielerName.setFont(new Font("Dialog", Font.PLAIN, 35));
		cp.add(lab_SpielerName);

		lab_SpieleGespielt.setBounds(10, 60, 230, 20);
		cp.add(lab_SpieleGespielt);

		lab_SpieleGewonnen.setBounds(10, 90, 230, 20);
		cp.add(lab_SpieleGewonnen);

		lab_SpieleSiegesserie.setBounds(10, 120, 230, 20);
		cp.add(lab_SpieleSiegesserie);

		lab_SpieleGewonnenProzent.setBounds(10, 150, 230, 20);
		cp.add(lab_SpieleGewonnenProzent);

		lab_MinenGefunden.setBounds(10, 180, 200, 20);
		cp.add(lab_MinenGefunden);

		lab_ZeitLetztesSpiel.setBounds(10, 210, 230, 20);
		cp.add(lab_ZeitLetztesSpiel);

		lab_ZeitSchnellstesSpiel.setBounds(10, 240, 230, 20);
		cp.add(lab_ZeitSchnellstesSpiel);

		lab_ZeitGesamt.setBounds(10, 270, 230, 20);
		cp.add(lab_ZeitGesamt);

		lab_SpielVerlauf.setBounds(10, 300, 75, 20);
		cp.add(lab_SpielVerlauf);

		lab_SpielHistorie.setBounds(90, 300, 200, 80);
		cp.add(lab_SpielHistorie);
	}

	private void createButtons() {
		
		btn_SpielerprofilSchliessen.setBounds(135, 430, 100, 30);
		btn_SpielerprofilSchliessen.setText("Profil schlieﬂen");
		btn_SpielerprofilSchliessen.setMargin(new Insets(2, 2, 2, 2));
		cp.add(btn_SpielerprofilSchliessen);
		btn_SpielerprofilSchliessen.addActionListener(this);

		btn_SpielerprofilAktualisieren.setBounds(10, 430, 100, 30);
		btn_SpielerprofilAktualisieren.setText("Aktualisieren");
		btn_SpielerprofilAktualisieren.setMargin(new Insets(2, 2, 2, 2));
		cp.add(btn_SpielerprofilAktualisieren);
		btn_SpielerprofilAktualisieren.addActionListener(this);
	}

	private void initFrame() {
		
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		int frameWidth = 250;
		int frameHeight = 500;
		setSize(frameWidth, frameHeight);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (d.width - getSize().width) / 2;
		int y = (d.height - getSize().height) / 2;
		setLocation(x - 250, y);
		setTitle("Spielerprofil");
		setResizable(false);
		
		cp.setLayout(null);
		setVisible(true);
	}

	public void setLabelText() {
		
		// Reihenfolge wie in der Anzeige
		lab_SpielerName.setText(spieler.getSpielerName());
		lab_SpieleGespielt.setText("Spiele gespielt: " + Integer.toString(spieler.getSpieleGespielt()));
		lab_SpieleGewonnen.setText("Spiele gewonnen: " + Integer.toString(spieler.getSpieleGewonnen()));
		lab_SpieleSiegesserie.setText("Aktuelle Siegesserie: " + spieler.getSpielerSiegesserie());
		lab_SpieleGewonnenProzent.setText("Spiele gewonnen in %: " + spieler.getSpieleGewonnenProzent() + "%");
		lab_MinenGefunden.setText("Minen gefunden: " + Integer.toString(spieler.getMinenGefunden()));
		lab_ZeitLetztesSpiel.setText("Spielzeit Letztes Spiel: " + spieler.getZeitLetztesSpiel() / 1000 + " Sekunden");
		lab_ZeitSchnellstesSpiel.setText("Schnellster Sieg: " + spieler.getZeitSchnellsterSiegl() / 1000 + " Sekunden");
		lab_ZeitGesamt.setText("Spielzeit gesamt: " + spieler.getZeitGesamt() / 1000 + " Sekunden");
		lab_SpielVerlauf.setText("Spielverlauf: ");
		lab_SpielHistorie.setText("<html>1: " + spieler.getSpielHistorie(0) + "<br>" + "2: "
				+ spieler.getSpielHistorie(1) + "<br>" + "3: " + spieler.getSpielHistorie(2) + "<br>" + "4: "
				+ spieler.getSpielHistorie(3) + "<br>" + "5: " + spieler.getSpielHistorie(4) + "</html>");
	}

}