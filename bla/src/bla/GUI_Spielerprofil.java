package bla;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.*;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class GUI_Spielerprofil extends JFrame {

	// Frame-Container
	private Container cp = getContentPane();

	// Objekte
	Spieler spieler = ObjectHandler.getSpieler();

	int aktiv = 0;
	
	// GUI-Elemente

	
	DefaultTableModel spielerStats;
	
	// Spalten anlegen
	String spalten[] = { "Modus", "Schwierigkeit", "Minen", "Zeit", "Ergebnis"};

	//JTable Tut
	// http://wiki.byte-welt.net/wiki/JTable_(Tutorial)©#1._M.C3.B6glichkeit_-_2d_Array
	
	// Datenmodelle anlegen
//	String stats[][] = {
//			{ "Klassik", "Leicht", "11", "0:48", "Sieg" },
//			{ "PvP", "Normal", "11-14", "2:16", "Niederlage" },
//			{ "Punktejagt", "Schwer", "60", "4:01", "Sieg" },
//			{ "FFA-3", "Groß", "3-7-11", "8:26", "Niederlage" }
//		};

	final DefaultTableModel model = new DefaultTableModel(spalten, 0);
	JTable tabelle = new JTable(model);
	
	// Tables
//	JTable tabelle = new JTable(stats, spalten);
	JScrollPane sPane = new JScrollPane(tabelle);
	
	// Labels
	private JLabel lab_MinenGefunden = new JLabel();
	private JLabel lab_profilBild = new JLabel();
	private JLabel lab_SpieleGespielt = new JLabel();
	private JLabel lab_SpieleGewonnen = new JLabel();
	private JLabel lab_SpieleGewonnenProzent = new JLabel();
	private JLabel lab_SpielHistorie = new JLabel();
	private JLabel lab_SpielerName = new JLabel();
	private JLabel lab_SpieleSiegesserie = new JLabel();
	private JLabel lab_SpieleMaxSiegesserie = new JLabel();
	private JLabel lab_SpielVerlauf = new JLabel();
	private JLabel lab_ZeitGesamt = new JLabel();
	private JLabel lab_ZeitStdMinSek = new JLabel();
	private JLabel lab_ZeitLetztesSpiel = new JLabel();
	private JLabel lab_ZeitSchnellstesSpiel = new JLabel();

	//String query;
	
	
	public static void start() {
		new GUI_Spielerprofil().getDB(); 
	}
	
	public void getDB() {
        System.out.println("DB aktiv"); 
        ResultSet rs = null; 
        Statement stmt = null; 
        Connection c = null; 
        try {
    	    
//            String fileName = "datenDB";
//            c = DriverManager.getConnection("jdbc:hsqldb:file:" + fileName + ";shutdown=true", "jb", "123");
//                       
//            stmt = c.createStatement(); 
//            
//            String query = "SELECT * FROM USERS;"; 
//            
//            rs = stmt.executeQuery(query); 
            
            
            
            String fileName = "datenDB";
            c = DriverManager.getConnection("jdbc:hsqldb:file:" + fileName + ";shutdown=true", "jb", "123");
            
            String query = "CREATE TABLE IF NOT EXISTS PUBLIC.USERS (spiel_id CHAR(4),  modus char(20), schwierigkeit CHAR(20),  minen CHAR(3), ergebnis CHAR(20));"; 
//            String query = "CREATE TABLE IF NOT EXISTS PUBLIC.USERS (spiel_id INTEGER NOT NULL, modus char(20), schwierigkeit CHAR(20));";
            
            stmt = c.createStatement(); 
            stmt.executeQuery(query); 
//            query = "INSERT INTO USERS (spiel_id, modus, schwierigkeit, minen, zeit_std, zeit_min, zeit_sek, ergebnis) VALUES ('0001', 'Klassik', 'Leicht', 10, 0, 0, 12, 'Sieg')";
            query = "INSERT INTO USERS (spiel_id, modus, schwierigkeit, minen, ergebnis) VALUES ('1', 'Klassik', 'Leicht', '10', 'Sieg')"; 
            rs = stmt.executeQuery(query); 
            rs.close(); 
            query = "SELECT * FROM USERS;"; 
            rs = stmt.executeQuery(query); 
            
            while (rs.next()) {
            
            Vector daten = new Vector(5);
	          for (int i=0; i<5; i++) {
	        	  daten.add(rs.getString("modus"));
	        	  daten.add(rs.getString("schwierigkeit"));
	        	  daten.add(rs.getString("minen"));
	        	  daten.add("00 : 11");
	        	  daten.add(rs.getString("ergebnis"));
	          }
	          model.addRow(daten);
//            
//            while (rs.next()) { 
//              Vector daten = new Vector(1);
//              daten.add(rs.getString("spiel_id"));
//            //  System.out.println(rs.getInt("spiel_id"));
//           //   System.out.println(daten);
//              model.addRow(daten);
            	
            	
            	
                System.out.println("vector done"); 
//                System.out.println(rs.getString("modus")); 
//                System.out.println(rs.getString("schwierigkeit")); 
//                System.out.println(rs.getString("minen")); 
//                System.out.println(rs.getString("ergebnis")); 
            } 
            
//            Vector newDatas = createDataVector(rs.getString("spiel_id"), 1);
//            String name = String.valueOf( model.getColumnCount() );
//            model.addColumn( name, newDatas );
            
//            Vector daten = new Vector(1);
//            daten.add(rs.getString("spiel_id"));
//            System.out.println(rs.getInt("spiel_id"));
//            System.out.println(daten);
//            model.addRow(daten);
            
//    		String stats[][] = {
//    				{ rs.getString("spiel_id"), rs.getString("modus"), rs.getString("minen"), "0:48", rs.getString("ergebnis") },
//    				{ "PvP", "Normal", "11-14", "2:16", "Niederlage" },
//    				{ "Punktejagt", "Schwer", "60", "4:01", "Sieg" },
//    				{ "FFA-3", "Groß", "3-7-11", "8:26", "Niederlage" }
//    			};
            
//            while (rs.next()) { 
//                System.out.println(rs.getInt("spiel_id")); 
//                System.out.println(rs.getString("modus")); 
//                System.out.println(rs.getString("schwierigkeit")); 
//                System.out.println(rs.getInt("minen")); 
//                System.out.println(rs.getString("ergebnis")); 
//            } 
            
  
            
            rs.close(); 
            stmt.close(); 
            c.close(); 
        } catch (SQLException e) { 
            e.printStackTrace(); 
        } finally { 
            try { 
                if (rs != null && !rs.isClosed()) 
                    rs.close(); 
                if (stmt != null && !stmt.isClosed()) 
                    stmt.close(); 
                if (c != null && !c.isClosed()) 
                    c.close(); 
            } catch (SQLException e) { 
            } 
        } 
        

        
    } 
	
	
	
	
	
	
	
	








    public static Vector createDataVector( String prefix, int size ){
        Vector vector = new Vector( size );
        for( int i = 0; i < size; i++ )
            vector.add( prefix );
        
        return vector;
    }
















	public GUI_Spielerprofil() {
		setupGUI();
	}

	private boolean checkGUI_Spielerprofil_active() {

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
	
	private void createTables() {
//		JTable tabelle = new JTable(stats, spalten);
//		spielerStats = new DefaultTableModel(stats, spalten);
//		tabelle = new JTable(spielerStats);
		//tabelle.setBounds(5, 450, 350, 80);
		
//		tabelle.getColumnModel().getColumn(0).setWidth(10);
//		tabelle.getColumnModel().getColumn(1).setWidth(40);
//		tabelle.getColumnModel().getColumn(2).setWidth(28);
//		tabelle.getColumnModel().getColumn(3).setWidth(28);
//		tabelle.getColumnModel().getColumn(4).setWidth(30);
		
		tabelle.changeSelection(0, 0, true, false);
		
//		cp.add(new JScrollPane(tabelle));
//		JScrollPane sPane = new JScrollPane(tabelle);
		sPane.setBounds(10+115, 70, 400, 240);
		cp.add(sPane);

		//cp.add(tabelle);
	}

	private void createLabels() {
				
		lab_profilBild.setBounds(10, 10, 40, 40);
		lab_profilBild.setIcon(new ImageIcon(getClass().getResource("img/profil.jpg")));
		cp.add(lab_profilBild);

		// Labels - in der Reihenfolge der Anzeige
		lab_SpielerName.setBounds(60, 10, 180, 40);
		lab_SpielerName.setFont(new Font("Dialog", Font.PLAIN, 35));
		cp.add(lab_SpielerName);

		lab_SpieleGespielt.setBounds(10+110, 60, 230, 20);
		cp.add(lab_SpieleGespielt);

		lab_SpieleGewonnen.setBounds(10+110, 85, 230, 20);
		cp.add(lab_SpieleGewonnen);

		lab_SpieleSiegesserie.setBounds(10+110, 110, 230, 20);
		cp.add(lab_SpieleSiegesserie);

		lab_SpieleMaxSiegesserie.setBounds(10+110, 135, 230, 20);
		cp.add(lab_SpieleMaxSiegesserie);

		lab_SpieleGewonnenProzent.setBounds(10+110, 160, 230, 20);
		cp.add(lab_SpieleGewonnenProzent);

		lab_MinenGefunden.setBounds(10+110, 185, 230, 20);
		cp.add(lab_MinenGefunden);

		lab_ZeitLetztesSpiel.setBounds(10+110, 210, 230, 20);
		cp.add(lab_ZeitLetztesSpiel);

		lab_ZeitSchnellstesSpiel.setBounds(10+110, 235, 230, 20);
		cp.add(lab_ZeitSchnellstesSpiel);

		lab_ZeitGesamt.setBounds(10+110, 260, 230, 20);
		cp.add(lab_ZeitGesamt);

		lab_ZeitStdMinSek.setBounds(110+110, 256, 230, 60);
		cp.add(lab_ZeitStdMinSek);

		lab_SpielVerlauf.setBounds(10+110, 315, 75, 20);
		cp.add(lab_SpielVerlauf);

		lab_SpielHistorie.setBounds(87+110, 317, 200, 80);
		cp.add(lab_SpielHistorie);
	}

	private void createButtons() {
		
		JButton btn_uebersicht = new JButton();
		JButton btn_spielverlauf = new JButton();
		JButton btn_erfolge = new JButton();
		
		btn_uebersicht.setBounds(10, 70, 105, 30);
		btn_uebersicht.setText("Übersicht");
		btn_uebersicht.setMargin(new Insets(0, 0, 0, 0));
		cp.add(btn_uebersicht);
		btn_uebersicht.addActionListener(e -> setProfilAktiv(1));
		
		btn_spielverlauf.setBounds(10, 110, 105, 30);
		btn_spielverlauf.setText("Spielverlauf");
		btn_uebersicht.setMargin(new Insets(0, 0, 0, 0));
		cp.add(btn_spielverlauf);
		btn_spielverlauf.addActionListener(e -> setProfilAktiv(2));
		
		btn_erfolge.setBounds(10, 150, 105, 30);
		btn_erfolge.setText("Erfolge");
		btn_erfolge.setMargin(new Insets(0, 0, 0, 0));
		btn_erfolge.setEnabled(false);
		cp.add(btn_erfolge);
		btn_erfolge.addActionListener(e -> setProfilAktiv(3));
		
		JSeparator sep_Oben = new JSeparator();
	    sep_Oben.setBounds(10, 59, 515, 3);
	    cp.add(sep_Oben);
		
		

		JButton btn_SpielerprofilSchliessen = new JButton();
		JButton btn_SpielerprofilAktualisieren = new JButton();

		btn_SpielerprofilSchliessen.setBounds(495, 10, 30, 30);
		btn_SpielerprofilSchliessen.setText("X");
		btn_SpielerprofilSchliessen.setMargin(new Insets(0, 0, 0, 0));
		cp.add(btn_SpielerprofilSchliessen);
		btn_SpielerprofilSchliessen.addActionListener(e -> closeGUI_Spielerprofil());

		btn_SpielerprofilAktualisieren.setBounds(460, 10, 30, 30);
		btn_SpielerprofilAktualisieren.setText("Re");
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
		int frameWidth = 540; //250
		int frameHeight = 350; //480
		setSize(frameWidth, frameHeight);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (d.width - getSize().width) / 2;
		int y = (d.height - getSize().height) / 2;
		setLocation(x - 325, y);
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
		lab_ZeitStdMinSek
				.setText("<html>" + zeitStunden() + "<br>" + zeitMinuten() + " <br>" + zeitSekunden() + " </html>");
		lab_SpielVerlauf.setText("Spielverlauf: ");
		lab_SpielHistorie.setText("<html>1: " + spieler.getSpielHistorie(0) + "<br>" + "2: "
				+ spieler.getSpielHistorie(1) + "<br>" + "3: " + spieler.getSpielHistorie(2) + "<br>" + "4: "
				+ spieler.getSpielHistorie(3) + "<br>" + "5: " + spieler.getSpielHistorie(4) + "</html>");
	}

	public void setupGUI() {

		initFrame();
		
		createTables();
		
		createButtons();
		createLabels();
		setLabelText();
		
		setProfilAktiv(1);
		

	}
	
	private void setProfilAktiv(int aktiv) {
		if (aktiv == 1) {
			setProfilSpielverlauf(false);
			setProfilErfolge(false);
			
			setProfilUebersicht(true);
		}
		
		if (aktiv == 2) {
			getDB();
	         			
			setProfilUebersicht(false);
			setProfilErfolge(false);
			
			setProfilSpielverlauf(true);
		}
		if (aktiv == 3) {
			setProfilUebersicht(false);
			setProfilSpielverlauf(false);
			
			setProfilErfolge(true);
		}
	}

	private void setProfilUebersicht(boolean anAus) {
		lab_MinenGefunden.setVisible(anAus);
		lab_SpieleGespielt.setVisible(anAus);
		lab_SpieleGewonnen.setVisible(anAus);
		lab_SpieleGewonnenProzent.setVisible(anAus);
		lab_SpielHistorie.setVisible(anAus);
		lab_SpieleSiegesserie.setVisible(anAus);
		lab_SpieleMaxSiegesserie.setVisible(anAus);
		lab_SpielVerlauf.setVisible(anAus);
		lab_ZeitGesamt.setVisible(anAus);
		lab_ZeitStdMinSek.setVisible(anAus);
		lab_ZeitLetztesSpiel.setVisible(anAus);
		lab_ZeitSchnellstesSpiel.setVisible(anAus);
	}
	
	private void setProfilSpielverlauf(boolean anAus) {
		sPane.setVisible(anAus);		
		tabelle.setVisible(anAus);
	}
	
	private void setProfilErfolge(boolean anAus) {
		//inaktiv
	}

	public void updateGUI_Spielerprofil() {

		if (checkGUI_Spielerprofil_active() == true) {
			ObjectHandler.getGui_Spielerprofil().setLabelText();
		}

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

}