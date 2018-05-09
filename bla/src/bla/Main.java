package bla;

import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.*;
import java.awt.*;
  
public class Main extends JFrame 
{
  	// Objekte
  	Spieler spieler = ObjectHandler.getSpieler();
  	
  	// Panels
	JPanel panelMid = new JPanel();
  	
  	// Labels
	JLabel lab_MinenGefunden = new JLabel();
	JLabel lab_SpieleGespielt = new JLabel();
	JLabel lab_SpieleGewonnen = new JLabel();
	JLabel lab_SpieleGewonnenProzent = new JLabel();
	JLabel lab_SpielHistorie = new JLabel();
	JLabel lab_SpielerName = new JLabel();
	JLabel lab_SpieleSiegesserie = new JLabel();
	JLabel lab_SpieleMaxSiegesserie = new JLabel();
	JLabel lab_SpielVerlauf = new JLabel();
	JLabel lab_ZeitGesamt = new JLabel();
	JLabel lab_ZeitStdMinSek = new JLabel();
	JLabel lab_ZeitLetztesSpiel = new JLabel();
	JLabel lab_ZeitSchnellstesSpiel = new JLabel();
	
	
	
   public Main() {
      addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent we) {
            System.exit(0);
         }
      });
      
  	
  
//      JPanel panel = createContactPanel();
      JPanel panelmid = createMid();
      
      JPanel panelleft = createLeft();
      JPanel paneltop = createTop();
  
      JScrollPane sp = new JScrollPane();
      sp.setViewportView(panelmid);
  
      getContentPane().setLayout(new BorderLayout());
      getContentPane().add(sp, BorderLayout.CENTER); 
      
      getContentPane().add(panelleft, BorderLayout.WEST);
      getContentPane().add(paneltop, BorderLayout.NORTH);
      
      setLabelText();
   }
   
   public JPanel createLeft() {
	   
	JPanel panelleft = new JPanel();
	panelleft.setLayout(null);
	panelleft.setBackground(Color.RED);
	panelleft.setPreferredSize(new Dimension(115, 500)); 
	
	JButton btn_uebersicht = new JButton();
	JButton btn_spielverlauf = new JButton();
	JButton btn_erfolge = new JButton();
	
	btn_uebersicht.setBounds(5, 10, 105, 30);
	btn_uebersicht.setText("Übersicht");
	btn_uebersicht.setMargin(new Insets(0, 0, 0, 0));
	panelleft.add(btn_uebersicht);
//	btn_uebersicht.addActionListener(e -> setProfilAktiv(1));
	
	btn_spielverlauf.setBounds(5, 10+30+5, 105, 30);
	btn_spielverlauf.setText("Spielverlauf");
	btn_uebersicht.setMargin(new Insets(0, 0, 0, 0));
	panelleft.add(btn_spielverlauf);
//	btn_spielverlauf.addActionListener(e -> setProfilAktiv(2));
	
	btn_erfolge.setBounds(5, 10+30+5+30+5, 105, 30);
	btn_erfolge.setText("Erfolge");
	btn_erfolge.setMargin(new Insets(0, 0, 0, 0));
	btn_erfolge.setEnabled(true);
	panelleft.add(btn_erfolge);
//	btn_erfolge.addActionListener(e -> setProfilAktiv(3));
	
	return panelleft;
	   
   }
   
   public JPanel createTop() {
	   
	JPanel panelTop = new JPanel();
	panelTop.setLayout(null);
	panelTop.setBackground(Color.BLUE);
	panelTop.setPreferredSize(new Dimension(500, 70)); 
	
	JLabel lab_profilBild = new JLabel();
	JLabel lab_SpielerName = new JLabel();
	
	lab_profilBild.setBounds(10, 10, 40, 40);
	lab_profilBild.setIcon(new ImageIcon(getClass().getResource("img/profil.jpg")));
	panelTop.add(lab_profilBild);

	// Labels - in der Reihenfolge der Anzeige
	lab_SpielerName.setBounds(60, 10, 180, 40);
	lab_SpielerName.setFont(new Font("Dialog", Font.PLAIN, 35));
	lab_SpielerName.setText("Spielername");
	panelTop.add(lab_SpielerName);
	
	JSeparator sep_Oben = new JSeparator();
    sep_Oben.setBounds(10, 59, 515, 3);
    panelTop.add(sep_Oben);
	
	return panelTop;
	   
   }
   
   public JPanel createMid() {
	   
//	JPanel panelMid = new JPanel();
	panelMid.setLayout(null);
	panelMid.setPreferredSize(new Dimension(380, 620));
	   
//	JLabel lab_MinenGefunden = new JLabel();
//	JLabel lab_SpieleGespielt = new JLabel();
//	JLabel lab_SpieleGewonnen = new JLabel();
//	JLabel lab_SpieleGewonnenProzent = new JLabel();
//	JLabel lab_SpielHistorie = new JLabel();
//	JLabel lab_SpielerName = new JLabel();
//	JLabel lab_SpieleSiegesserie = new JLabel();
//	JLabel lab_SpieleMaxSiegesserie = new JLabel();
//	JLabel lab_SpielVerlauf = new JLabel();
//	JLabel lab_ZeitGesamt = new JLabel();
//	JLabel lab_ZeitStdMinSek = new JLabel();
//	JLabel lab_ZeitLetztesSpiel = new JLabel();
//	JLabel lab_ZeitSchnellstesSpiel = new JLabel();
	
	lab_SpielerName.setBounds(60, 10, 180, 40);
	lab_SpielerName.setFont(new Font("Dialog", Font.PLAIN, 35));
	panelMid.add(lab_SpielerName);

	lab_SpieleGespielt.setBounds(10+110, 60, 230, 20);
	panelMid.add(lab_SpieleGespielt);

	lab_SpieleGewonnen.setBounds(10+110, 85, 230, 20);
	panelMid.add(lab_SpieleGewonnen);

	lab_SpieleSiegesserie.setBounds(10+110, 110, 230, 20);
	panelMid.add(lab_SpieleSiegesserie);

	lab_SpieleMaxSiegesserie.setBounds(10+110, 135, 230, 20);
	panelMid.add(lab_SpieleMaxSiegesserie);

	lab_SpieleGewonnenProzent.setBounds(10+110, 160, 230, 20);
	panelMid.add(lab_SpieleGewonnenProzent);

	lab_MinenGefunden.setBounds(10+110, 185, 230, 20);
	panelMid.add(lab_MinenGefunden);

	lab_ZeitLetztesSpiel.setBounds(10+110, 210, 230, 20);
	panelMid.add(lab_ZeitLetztesSpiel);

	lab_ZeitSchnellstesSpiel.setBounds(10+110, 235, 230, 20);
	panelMid.add(lab_ZeitSchnellstesSpiel);

	lab_ZeitGesamt.setBounds(10+110, 260, 230, 20);
	panelMid.add(lab_ZeitGesamt);

	lab_ZeitStdMinSek.setBounds(110+110, 256, 230, 60);
	panelMid.add(lab_ZeitStdMinSek);

	lab_SpielVerlauf.setBounds(10+110, 315, 75, 20);
	panelMid.add(lab_SpielVerlauf);

	lab_SpielHistorie.setBounds(87+110, 317, 200, 80);
	panelMid.add(lab_SpielHistorie);

	return panelMid;
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
		lab_ZeitStdMinSek
				.setText("<html>" + zeitStunden() + "<br>" + zeitMinuten() + " <br>" + zeitSekunden() + " </html>");
		lab_SpielVerlauf.setText("Spielverlauf: ");
		lab_SpielHistorie.setText("<html>1: " + spieler.getSpielHistorie(0) + "<br>" + "2: "
				+ spieler.getSpielHistorie(1) + "<br>" + "3: " + spieler.getSpielHistorie(2) + "<br>" + "4: "
				+ spieler.getSpielHistorie(3) + "<br>" + "5: " + spieler.getSpielHistorie(4) + "</html>");
	}
  
   public JPanel createContactPanel() {
      JLabel titleLbl      = new JLabel("Title");
      JLabel firstNameLbl  = new JLabel("First name");
      JLabel lastNameLbl   = new JLabel("Last name");
      JLabel addressLbl    = new JLabel("Address");
      JLabel cityLbl       = new JLabel("City");
      JLabel zipLbl        = new JLabel("Postal code");
      JLabel countryLbl    = new JLabel("Country");
      JLabel phoneLbl      = new JLabel("Phone number");
      JLabel faxLbl        = new JLabel("Fax number");
      JLabel emailLbl      = new JLabel("E-mail");
      JLabel birthdayLbl   = new JLabel("Birthdate");
      JLabel pickchoiceLbl = new JLabel("Pick a choice");
      JLabel creditCardTypeLbl     = new JLabel("Credit card type");
      JLabel creditCardNumberLbl   = new JLabel("Credit card number");
      JLabel expirationLbl         = new JLabel("Expiration date");
  
      JComboBox titleCombo = new JComboBox(
             new String[] { "-", "Mr", "Mrs", "Miss" });
      JTextField firstNameTf    = new JTextField();
      JTextField lastNameTf     = new JTextField();
      JTextField address1Tf     = new JTextField();
      JTextField address2Tf     = new JTextField();
      JTextField cityTf         = new JTextField();
      JTextField zipTf          = new JTextField();
      JTextField countryTf      = new JTextField();
      JTextField phoneTf        = new JTextField();
      JTextField faxTf          = new JTextField();
      JTextField emailTf        = new JTextField();
      JComboBox bd1Combo = new JComboBox();
      for (int i=1; i<=12; i++) bd1Combo.addItem(""+i);
      JComboBox bd2Combo = new JComboBox();
      for (int i=1; i<=31; i++) bd2Combo.addItem(""+i);
      JComboBox bd3Combo = new JComboBox();
      for (int i=1900; i<2000; i++) bd3Combo.addItem(""+i);
      JComboBox referCombo = new JComboBox();
      referCombo.addItem("Friend");
      referCombo.addItem("Search engine");
      referCombo.addItem("Print Media");
      referCombo.addItem("Banner Add");
      referCombo.addItem("Other");
      JComboBox creditCardTypeCombo = new JComboBox();
      creditCardTypeCombo.addItem("VISA");
      creditCardTypeCombo.addItem("MasterCard");
      creditCardTypeCombo.addItem("American Express");
      JTextField creditCardNumberTf = new JTextField();
      JComboBox expiration1Combo = new JComboBox();
      for (int i=1; i<=12; i++) expiration1Combo.addItem(""+i);
      JComboBox expiration2Combo = new JComboBox();
      for (int i=1; i<=31; i++) expiration2Combo.addItem(""+i);
      JComboBox expiration3Combo = new JComboBox();
      for (int i=1900; i<=2000; i++) expiration3Combo.addItem(""+i);
  
      JPanel panelGeneral = new JPanel();
      panelGeneral.setLayout(null);
      panelGeneral.add(titleLbl);
      panelGeneral.setBorder(new TitledBorder("General Information"));
      titleLbl.setBounds(20, 20, 150, 20);
      panelGeneral.add(firstNameLbl);
      firstNameLbl.setBounds(20, 50, 150, 20);
      panelGeneral.add(lastNameLbl);
      lastNameLbl.setBounds(20, 80, 150, 20);
      panelGeneral.add(addressLbl);
      addressLbl.setBounds(20, 110, 150, 20);
      panelGeneral.add(cityLbl);
      cityLbl.setBounds(20, 170, 150, 20);
      panelGeneral.add(zipLbl);
      zipLbl.setBounds(20, 200, 150, 20);
      panelGeneral.add(countryLbl);
      countryLbl.setBounds(20, 230, 150, 20);
      panelGeneral.add(phoneLbl);
      phoneLbl.setBounds(20, 260, 150, 20);
      panelGeneral.add(faxLbl);
      faxLbl.setBounds(20, 290, 150, 20);
      panelGeneral.add(emailLbl);
      emailLbl.setBounds(20, 320, 150, 20);
      panelGeneral.add(birthdayLbl);
      birthdayLbl.setBounds(20, 350, 150, 20); 
      panelGeneral.add(titleCombo);
      titleCombo.setBounds(150, 20, 60, 20);
      panelGeneral.add(firstNameTf);
      firstNameTf.setBounds(150, 50, 200, 20);
      panelGeneral.add(lastNameTf);
      lastNameTf.setBounds(150, 80, 200, 20);
      panelGeneral.add(address1Tf);
      address1Tf.setBounds(150, 110, 200, 20);  
      panelGeneral.add(address2Tf);
      address2Tf.setBounds(150, 140, 200, 20);  
      panelGeneral.add(cityTf);
      cityTf.setBounds(150, 170, 200, 20);
      panelGeneral.add(zipTf);
      zipTf.setBounds(150, 200, 200, 20);
      panelGeneral.add(countryTf);
      countryTf.setBounds(150, 230, 200, 20);
      panelGeneral.add(phoneTf);
      phoneTf.setBounds(150, 260, 200, 20);
      panelGeneral.add(faxTf);
      faxTf.setBounds(150, 290, 200, 20);
      panelGeneral.add(emailTf);
      emailTf.setBounds(150, 320, 200, 20);
      panelGeneral.add(bd1Combo);
      bd1Combo.setBounds(150, 350, 60, 20);
      panelGeneral.add(bd2Combo);
      bd2Combo.setBounds(220, 350, 60, 20);
      panelGeneral.add(bd3Combo);
      bd3Combo.setBounds(290, 350, 60, 20);
  
      JPanel panelReferral = new JPanel();
      panelReferral.setLayout(null);
      panelReferral.setBorder(new TitledBorder("Where did you hear about us?"));
      panelReferral.add(pickchoiceLbl);
      pickchoiceLbl.setBounds(20, 20, 150, 20);
      panelReferral.add(referCombo);
      referCombo.setBounds(150, 20, 100, 20);
  
      JPanel panelCreditCard = new JPanel();
      panelCreditCard.setLayout(null);
      panelCreditCard.setBorder(new TitledBorder("Payment method"));
      panelCreditCard.add(creditCardTypeLbl);
      creditCardTypeLbl.setBounds(20, 20, 150, 20);
      panelCreditCard.add(creditCardNumberLbl);
      creditCardNumberLbl.setBounds(20, 50, 150, 20);
      panelCreditCard.add(expirationLbl);
      expirationLbl.setBounds(20, 80, 150, 20);
  
      panelCreditCard.add(creditCardTypeCombo);
      creditCardTypeCombo.setBounds(150, 20, 100, 20);
      panelCreditCard.add(creditCardNumberTf);
      creditCardNumberTf.setBounds(150, 50, 150, 20);
      panelCreditCard.add(expiration2Combo);
      expiration2Combo.setBounds(220, 80, 60, 20);
      panelCreditCard.add(expiration3Combo);
      expiration3Combo.setBounds(290, 80, 60, 20);
  
      JPanel panel = new JPanel();
      panel.setLayout(null);
      panel.add(panelGeneral);
      panelGeneral.setBounds(10, 20, 370, 400);
      panel.add(panelReferral);
      panelReferral.setBounds(10, 430, 370, 50);
      panel.add(panelCreditCard);
      panelCreditCard.setBounds(10, 490, 370, 120);
       
      panel.setPreferredSize(new Dimension(380, 620)); 
  
      return panel;
   }
   
	private String zeitStunden() {
		String ausgabeStd = "";
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
		String ausgabeMin = "";
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
		String ausgabeSek = "";
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
  
   public static void main(String []args) {
      Main main = new Main();
      main.setSize(540, 350);
      Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (d.width - main.getSize().width) / 2;
		int y = (d.height - main.getSize().height) / 2;
		main.setLocation(x - 325, y);
		main.setTitle("Neues Spielerprofil");
		main.setResizable(false);
      main.setVisible(true);
   }
}