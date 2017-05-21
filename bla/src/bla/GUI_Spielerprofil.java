package bla;

import java.awt.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class GUI_Spielerprofil extends JFrame {

	// Frame-Container
	private Container cp = getContentPane();
	
	// Objekte
	Spieler spieler = ObjectHandler.getSpieler();

	// GUI-Elemente
	// Labels
	private static JLabel lab_MinenGefunden = new JLabel();
	private static JLabel lab_profilBild = new JLabel();
	private static JLabel lab_SpieleGespielt = new JLabel();
	private static JLabel lab_SpieleGewonnen = new JLabel();
	private static JLabel lab_SpieleGewonnenProzent = new JLabel();
	private static JLabel lab_SpielHistorie = new JLabel();
	private static JLabel lab_SpielerName = new JLabel();
	private static JLabel lab_SpieleSiegesserie = new JLabel();
	private static JLabel lab_SpieleMaxSiegesserie = new JLabel();
	private static JLabel lab_SpielVerlauf = new JLabel();
	private static JLabel lab_ZeitGesamt = new JLabel();
	private static JLabel lab_ZeitStdMinSek = new JLabel();
	private static JLabel lab_ZeitLetztesSpiel = new JLabel();
	private static JLabel lab_ZeitSchnellstesSpiel = new JLabel();

	public GUI_Spielerprofil() {

		if (checkGUI_Spielerprofil_active() == false) {
			setupGUI();
			setLabelText();
		}

	}

	private static boolean checkGUI_Spielerprofil_active() {

		if (ObjectHandler.getGui_Spielerprofil() == null) {
			return false;
		} else {
			return true;
		}
	}

	private void closeGUI_Spielerprofil() {
		this.dispose();
		ObjectHandler.setGui_Spielerprofil(null);
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

		lab_SpieleGewonnen.setBounds(10, 85, 230, 20);
		cp.add(lab_SpieleGewonnen);

		lab_SpieleSiegesserie.setBounds(10, 110, 230, 20);
		cp.add(lab_SpieleSiegesserie);

		lab_SpieleMaxSiegesserie.setBounds(10, 135, 230, 20);
		cp.add(lab_SpieleMaxSiegesserie);

		lab_SpieleGewonnenProzent.setBounds(10, 160, 230, 20);
		cp.add(lab_SpieleGewonnenProzent);

		lab_MinenGefunden.setBounds(10, 185, 230, 20);
		cp.add(lab_MinenGefunden);

		lab_ZeitLetztesSpiel.setBounds(10, 210, 230, 20);
		cp.add(lab_ZeitLetztesSpiel);

		lab_ZeitSchnellstesSpiel.setBounds(10, 235, 230, 20);
		cp.add(lab_ZeitSchnellstesSpiel);

		lab_ZeitGesamt.setBounds(10, 260, 230, 20);
		cp.add(lab_ZeitGesamt);
		
		lab_ZeitStdMinSek.setBounds(110, 256, 230, 60);
		cp.add(lab_ZeitStdMinSek);

		lab_SpielVerlauf.setBounds(10, 315, 75, 20);
		cp.add(lab_SpielVerlauf);

		lab_SpielHistorie.setBounds(87, 317, 200, 80);
		cp.add(lab_SpielHistorie);
	}

	private void createButtons() {

		JButton btn_SpielerprofilSchliessen = new JButton();
		JButton btn_SpielerprofilAktualisieren = new JButton();

		btn_SpielerprofilSchliessen.setBounds(135, 410, 100, 30);
		btn_SpielerprofilSchliessen.setText("Profil schließen");
		btn_SpielerprofilSchliessen.setMargin(new Insets(2, 2, 2, 2));
		cp.add(btn_SpielerprofilSchliessen);
		btn_SpielerprofilSchliessen.addActionListener(e -> closeGUI_Spielerprofil());

		btn_SpielerprofilAktualisieren.setBounds(10, 410, 100, 30);
		btn_SpielerprofilAktualisieren.setText("Aktualisieren");
		btn_SpielerprofilAktualisieren.setMargin(new Insets(2, 2, 2, 2));
		cp.add(btn_SpielerprofilAktualisieren);
		btn_SpielerprofilAktualisieren.addActionListener(e -> setLabelText());
	}

	private void initFrame() {

		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); // Darf
																		// nicht
																		// schließen,
																		// weil
																		// sonst
																		// keine
																		// Rückmeldung
																		// an
																		// ObjectHandler
																		// geht.
		int frameWidth = 250;
		int frameHeight = 480;
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

	private void setLabelText() {

		// Reihenfolge wie in der Anzeige
		lab_SpielerName.setText(spieler.getSpielerName());
		lab_SpieleGespielt.setText("Spiele gespielt: " + Integer.toString(spieler.getSpieleGespielt()));
		lab_SpieleGewonnen.setText("Spiele gewonnen: " + Integer.toString(spieler.getSpieleGewonnen()));
		lab_SpieleSiegesserie.setText("Aktuelle Siegesserie: " + spieler.getSpielerSiegesserie());
		lab_SpieleMaxSiegesserie.setText("Längste Siegesserie: " + spieler.getMaxSiegesserie());
		lab_SpieleGewonnenProzent.setText("Spiele gewonnen in %: " + spieler.getSpieleGewonnenProzent() + "%");
		lab_MinenGefunden.setText("Minen gefunden: " + Integer.toString(spieler.getMinenGefunden()));
		lab_ZeitLetztesSpiel.setText("Spielzeit Letztes Spiel: " + spieler.getZeitLetztesSpiel() / 1000 + " Sekunden");
		lab_ZeitSchnellstesSpiel.setText("Schnellster Sieg: " + spieler.getZeitSchnellsterSiegl() / 1000 + " Sekunden");
		lab_ZeitGesamt.setText("Spielzeit gesamt: ");
		lab_ZeitStdMinSek.setText("<html>" + zeitStunden() + "<br>" + zeitMinuten() + " <br>" + zeitSekunden() + " </html>");
		lab_SpielVerlauf.setText("Spielverlauf: ");
		lab_SpielHistorie.setText("<html>1: " + spieler.getSpielHistorie(0) + "<br>" + "2: "
				+ spieler.getSpielHistorie(1) + "<br>" + "3: " + spieler.getSpielHistorie(2) + "<br>" + "4: "
				+ spieler.getSpielHistorie(3) + "<br>" + "5: " + spieler.getSpielHistorie(4) + "</html>");
	}
	

	private void setupGUI() {

		initFrame();
		createButtons();
		createLabels();

	}

	public static void updateGUI_Spielerprofil() {

		if (checkGUI_Spielerprofil_active() == true) {
			ObjectHandler.getGui_Spielerprofil().setLabelText();
		}

	}
	
	private String zeitStunden() {
		String ausgabeStd="";
		Double stunden = Math.floor((spieler.getZeitGesamt() / 1000) / 3600);
		int std = stunden.intValue(); 
		
		// String für Stunde(n)
		if (std == 0) {
			ausgabeStd = "0 Stunden";
		}
		if (std == 1) {
			ausgabeStd = Integer.toString(std) + " Stunde";
		}
		if (std >= 2) {
			ausgabeStd = Integer.toString(std) + " Stunden";
		}
		
		return ausgabeStd;
	}

	private String zeitMinuten() {
		String ausgabeMin="";
		Double minuten = Math.floor(((spieler.getZeitGesamt() / 1000) % 3600) / 60);
		int min = minuten.intValue();
		
		// String für Minute(n)
		if (min == 0) {
			ausgabeMin = "0 Minuten";
		}
		if (min == 1) {
			ausgabeMin = Integer.toString(min) + " Minute";
		}
		if (min >= 2) {
			ausgabeMin = Integer.toString(min) + " Minuten";
		}
		
		return ausgabeMin;
	}
	
	private String zeitSekunden() {
		String ausgabeSek="";
		Double sekunden = Math.floor(((spieler.getZeitGesamt() / 1000) % 60));
		int sek = sekunden.intValue();
		
		// String für Sekunde(n)
		if (sek == 0) {
			ausgabeSek = "0 Sekunden";
		}
		if (sek == 1) {
			ausgabeSek = Integer.toString(sek) + " Sekunde";
		}
		if (sek >= 2) {
			ausgabeSek = Integer.toString(sek) + " Sekunden";
		}
		
		return ausgabeSek;
	}

}