package bla;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Scrollbar;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.WindowConstants;

@SuppressWarnings("serial")
public class GUI_SpielModusBenutzer extends JFrame implements ActionListener {	
	
	// Objekte
	JFrame gui_Start;
	Spieler spielerEins;
	
	// Frame-Container
	JFrame gui_SpielModusBenutzer = new JFrame();
	Container cp2 = gui_SpielModusBenutzer.getContentPane();
	
	// Double
	Double maxMinen;
		
	// Zufallszahlen
	Random randSpalte = new Random();
	Random randZeile = new Random();
	Random randMine = new Random();
	
	// GUI-Elemente
	// Buttons
	private JButton btn_SpielZurueck = new JButton();
	private JButton btn_SpielStarten = new JButton();
	private JButton btn_Zufallswert = new JButton();
	
	// Labels
	private JLabel lab_Spalten = new JLabel();
	private JLabel lab_SpaltenZahl = new JLabel();
	private JLabel lab_Zeilen = new JLabel();
	private JLabel lab_ZeilenZahl = new JLabel();
	private JLabel lab_Felder = new JLabel();
	private JLabel lab_Minen = new JLabel();
	private JLabel lab_MinenZahl = new JLabel();
	
	// Scrollbar
	private JScrollBar sb_spalten = new JScrollBar();
	private JScrollBar sb_zeilen = new JScrollBar();
	private JScrollBar sb_minen = new JScrollBar();
	
	
	
	public GUI_SpielModusBenutzer(JFrame guiStart, Spieler spieler) {
		gui_Start = guiStart;
		spielerEins = spieler;
		
		setupGUI();
	}
	
	private void setupGUI() {
		
		initFrame();
		createButtons();
		createLabels();
		createScrollBars();
		
		gui_SpielModusBenutzer.setVisible(true);
	}

	public void actionPerformed(ActionEvent object) {
		if (object.getSource() == btn_SpielZurueck) {
			gui_SpielModusBenutzer.dispose();
		}
		if (object.getSource() == btn_SpielStarten) {
			Spiel.setSpielModus(sb_spalten.getValue(), sb_zeilen.getValue(), sb_minen.getValue(), "Benutzer", spielerEins);
			gui_SpielModusBenutzer.dispose();
			gui_Start.dispose();
		}
	if (object.getSource() == btn_Zufallswert) {
			int spaltenZufall = randSpalte.nextInt(30);
			int zeilenZufall = randZeile.nextInt(24);
			int minenZufall = randMine.nextInt(144);
		
			sb_spalten.setValue(spaltenZufall);
			sb_zeilen.setValue(zeilenZufall);
			sb_minen.setValue(minenZufall);
		
			lab_SpaltenZahl.setText(Integer.toString(sb_spalten.getValue()));
			lab_ZeilenZahl.setText(Integer.toString(sb_zeilen.getValue()));
			lab_MinenZahl.setText(Integer.toString(sb_minen.getValue()));		
		}
	}

	private void createButtons() {
		btn_SpielZurueck.setBounds(10, 120, 90, 30);
		btn_SpielZurueck.setText("Zurück");
		btn_SpielZurueck.setMargin(new Insets(2, 2, 2, 2));
		cp2.add(btn_SpielZurueck);
		btn_SpielZurueck.addActionListener(this);
		
		btn_SpielStarten.setBounds(110, 120, 90, 30);
		btn_SpielStarten.setText("Spiel starten");
		btn_SpielStarten.setMargin(new Insets(2, 2, 2, 2));
		cp2.add(btn_SpielStarten);
		btn_SpielStarten.addActionListener(this);
		
		btn_Zufallswert.setBounds(242, 120, 90, 30);
		btn_Zufallswert.setText("Zufallswerte");
		btn_Zufallswert.setMargin(new Insets(2, 2, 2, 2));
		cp2.add(btn_Zufallswert);
		btn_Zufallswert.addActionListener(this);
	}

	private void createLabels() {
		lab_Spalten.setBounds(10, 10, 50, 20);
		lab_Spalten.setText("Spalten: ");
		cp2.add(lab_Spalten);
	    
		lab_Zeilen.setBounds(10, 40, 50, 20);
		lab_Zeilen.setText("Zeilen: ");
		cp2.add(lab_Zeilen);
				
		lab_Minen.setBounds(10, 80, 50, 20);
		lab_Minen.setText("Minen: ");
		cp2.add(lab_Minen);

		lab_Felder.setBounds(269, 60, 70, 20);
		lab_Felder.setText("Felder: "+sb_spalten.getValue()*sb_zeilen.getValue());
		cp2.add(lab_Felder);

		lab_SpaltenZahl.setBounds(310, 10, 20, 20);
		lab_SpaltenZahl.setText(Integer.toString(sb_spalten.getValue()));
		cp2.add(lab_SpaltenZahl);
	    
		lab_ZeilenZahl.setBounds(310, 40, 20, 20);
		lab_ZeilenZahl.setText(Integer.toString(sb_zeilen.getValue()));
		cp2.add(lab_ZeilenZahl);
				
		lab_MinenZahl.setBounds(310, 80, 40, 20);
		lab_MinenZahl.setText(Integer.toString(sb_minen.getValue()));
		cp2.add(lab_MinenZahl);
	}
	
	private void createScrollBars() {

	    sb_spalten.setBounds(60, 10, 240, 20);
	    sb_spalten.setOrientation(Scrollbar.HORIZONTAL);
	    sb_spalten.setBlockIncrement(1);
	    sb_spalten.setUnitIncrement(1);
	    sb_spalten.setMinimum(8);
	    sb_spalten.setMaximum(30+10);
	    sb_spalten.setValue(8);
	    cp2.add(sb_spalten);

	    sb_spalten.addAdjustmentListener(new AdjustmentListener() {
	          @Override
	          public void adjustmentValueChanged(AdjustmentEvent e) {
	        	  lab_SpaltenZahl.setText(Integer.toString(sb_spalten.getValue()));
	      		  lab_Felder.setText("Felder: "+sb_spalten.getValue()*sb_zeilen.getValue());
	      	   maxMinen = (sb_spalten.getValue()*sb_zeilen.getValue())*0.2;
	      	   sb_minen.setMaximum(maxMinen.intValue());
	             }
	       });
		
	    sb_zeilen.setBounds(60, 40, 240, 20);
	    sb_zeilen.setOrientation(Scrollbar.HORIZONTAL);
	    sb_zeilen.setBlockIncrement(1);
	    sb_zeilen.setUnitIncrement(1);
	    sb_zeilen.setMinimum(8);
	    sb_zeilen.setMaximum(24+10);
	    sb_zeilen.setValue(8);
	    cp2.add(sb_zeilen);
	    
	    sb_zeilen.addAdjustmentListener(new AdjustmentListener() {
	          @Override
	          public void adjustmentValueChanged(AdjustmentEvent e) {
	        	  lab_ZeilenZahl.setText(Integer.toString(sb_zeilen.getValue()));
	      		  lab_Felder.setText("Felder: "+sb_spalten.getValue()*sb_zeilen.getValue());
	      	    maxMinen = (sb_spalten.getValue()*sb_zeilen.getValue())*0.2;
	      	    sb_minen.setMaximum(maxMinen.intValue());
	             }
	       });

	    maxMinen = (sb_spalten.getValue()*sb_zeilen.getValue())*0.2;
	    
	    sb_minen.setBounds(60, 80, 240, 20);
	    sb_minen.setOrientation(Scrollbar.HORIZONTAL);
	    sb_minen.setBlockIncrement(1);
	    sb_minen.setUnitIncrement(1);
	    sb_minen.setMinimum(10);
	    sb_minen.setMaximum(144+10);
	    sb_minen.setValue(10);
	    cp2.add(sb_minen);
	    
	    sb_minen.addAdjustmentListener(new AdjustmentListener() {
	          @Override
	          public void adjustmentValueChanged(AdjustmentEvent e) {
	        	  lab_MinenZahl.setText(Integer.toString(sb_minen.getValue()));
	             }
	       });
	}

	private void initFrame() {
		
		int width=347;
		int heigth=190;
		gui_SpielModusBenutzer.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		gui_SpielModusBenutzer.setSize(width, heigth);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (d.width - getSize().width) / 2;
		int y = (d.height - getSize().height) / 2;
		gui_SpielModusBenutzer.setLocation(x-width/2, y-heigth/2);
		gui_SpielModusBenutzer.setTitle("Benutzerdefinierter Spielmodus");
		gui_SpielModusBenutzer.setResizable(false);
		cp2.setLayout(null);
	}
}
