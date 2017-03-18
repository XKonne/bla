package bla;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.*;

/**
 * Spielprojekt "Seawolf" GameApp-Name <not set/actually nameless>
 *
 * @version A.2.3 vom 18.03.2017
 * @author XKonne
 * @author p0sE-Git
 * 
 */

public class Test extends JFrame {
	
	// Attribute
	static boolean Spielfeldgesperrt = true;
	static int minen = 3;
	static int mine = 3;
	static String versiont="A.2.3";
	private JButton ButtonSpielNeustart = new JButton();
	private JButton ButtonResetSpielfeld = new JButton();
	private JButton ButtonSpielernameOK = new JButton();

	private JLabel labVersion = new JLabel();
	private JTextField textfSpielernameEingabe = new JTextField();
	
	//GUI Elemente
	//Bereich "Spielerinformationen"
	private JLabel labSpielername = new JLabel();
	private JLabel labSpielModus = new JLabel();	  
	
	private static JLabel labRestminen = new JLabel();
	static int minerichtig=0;
	private static JLabel labMinenRichtig = new JLabel();

	//Buttonsausrichtung
	int top=10;
	int left=70;
	int lenght=30;
	int height=30;
	int zeile=0;
	
	// Arrays
	// 0=Spielfeld noch nicht geklickt --- 1=Spielfeld bereits 1x gedrückt
	// 5=Linksklick. Spielfeld aufgedeckt. Keine weiteren Aktion möglich
	static int[] Spielfeldgeklickt = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }; // i=1..16
	static JButton[] buttons = new JButton[16];
	// MFeld = Minenfeld Testspielfeld 4x4
	// 4x4-Raster
	// 1 2 3 4 <=> - - 1 M
	// 5 6 7 8 <=> 1 2 3 2
	// 9 10 11 12 <=> 1 M M 1
	// 13 14 15 16 <=> 1 2 2 1
	static String a_btnText[] = { "-", "-", "1", "M", "1", "2", "3", "2", "1", "M", "M", "1", "1", "2", "2", "1" };
	String Spielername;
	// Ende Attribute

	public Test() {
		// Frame-Initialisierung
		super();
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		int frameWidth = 330;
		int frameHeight = 300;
		setSize(frameWidth, frameHeight);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (d.width - getSize().width) / 2;
		int y = (d.height - getSize().height) / 2;
		setLocation(x, y);
		setTitle("GameApp-Name actually not set");
		setResizable(false);
		Container cp = getContentPane();
		cp.setLayout(null);
		String a_btnNames[] = { "MFeld1", "MFeld2", "MFeld3", "MFeld4", "MFeld5", "MFeld6", "MFeld7", "MFeld8",
				"MFeld9", "MFeld10", "MFeld11", "MFeld12", "MFeld13", "MFeld14", "MFeld15", "MFeld16" };
		
		// Anfang Komponenten
		// Oberflaeche
		
		
		ButtonResetSpielfeld.setBounds(10, 230, 100, 30);
		ButtonResetSpielfeld.setText("Reset Spiel");
		ButtonResetSpielfeld.setMargin(new Insets(2, 2, 2, 2));

		// Spielername-Eingabe
		textfSpielernameEingabe.setBounds(10, 10, 150, 30);
		cp.add(textfSpielernameEingabe);

		ButtonSpielernameOK.setBounds(170, 10, 35, 28);
		ButtonSpielernameOK.setText("Los");
		ButtonSpielernameOK.setMargin(new Insets(2, 2, 2, 2));
		ButtonSpielernameOK.setEnabled(true);

		ButtonSpielNeustart.setBounds(120, 230, 100, 30);
		ButtonSpielNeustart.setText("Neustarten");
		ButtonSpielNeustart.setMargin(new Insets(2, 2, 2, 2));
		ButtonSpielNeustart.setEnabled(false);

		// Spielfeld
		
		// Buttons bauen
		for (int i = 0; i < buttons.length; i++) {
			
			buttons[i] = new JButton(a_btnNames[i]);
			// buttons[i].setBounds(10, 10, 30, 30);
			buttons[i].setText(".");
			buttons[i].setMargin(new Insets(2, 2, 2, 2));
			buttons[i].setEnabled(false);
			
			MouseInput mouse = new MouseInput(i);
			buttons[i].addMouseListener(mouse);
			
			cp.add(buttons[i]);

		}
		//x-y-Wert setzen bei Spielfeld-Buttons
		for (int i=0; i<16; i++) {
			    //x-Wert ergibt sich aus Button-Nr MOD spaltenwert (hier 4), damit 4 Buttons hinereinander liegen
				buttons[i].setBounds(top+(i%4)*lenght+(i%4)*10,left+zeile*height+zeile*10,lenght,height);
				// Wenn eine Zeile fertig ist, erhöhe Zeilen-Wert (=y-Koordinate)
				if (i%4==3) {
					zeile=zeile+1;
				}
		}
		
		/* ALT = Setzen des x-y-Wertes zur Position der Spielfeldbuttons
		 * HIER: Position mit Variablen, abhängig von einander
		 * => Muster erkennbar, damit das Ganze als Schleife programmiert werden kann
		 
		buttons[0].setBounds(top+0*lenght+0*10, left+0*height+0*10, lenght, height);
		buttons[1].setBounds(top+1*lenght+1*10, left+0*height+0*10, lenght, height);
		buttons[2].setBounds(top+2*lenght+2*10, left+0*height+0*10, lenght, height);
		buttons[3].setBounds(top+3*lenght+3*10, left+0*height+0*10, lenght, height);
		
		buttons[4].setBounds(top+0*lenght+0*10, left+1*height+1*10, lenght, height);
		buttons[5].setBounds(top+1*lenght+1*10, left+1*height+1*10, lenght, height);
		buttons[6].setBounds(top+2*lenght+2*10, left+1*height+1*10, lenght, height);
		buttons[7].setBounds(top+3*lenght+3*10, left+1*height+1*10, lenght, height);
		
		buttons[8].setBounds(top+0*lenght+0*10, left+2*height+2*10, lenght, height);
		buttons[9].setBounds(top+1*lenght+1*10, left+2*height+2*10, lenght, height);
		buttons[10].setBounds(top+2*lenght+2*10, left+2*height+2*10, lenght, height);
		buttons[11].setBounds(top+3*lenght+3*10, left+2*height+2*10, lenght, height);
		
		buttons[12].setBounds(top+0*lenght+0*10, left+3*height+3*10, lenght, height);
		buttons[13].setBounds(top+1*lenght+1*10, left+3*height+3*10, lenght, height);
		buttons[14].setBounds(top+2*lenght+2*10, left+3*height+3*10, lenght, height);
		buttons[15].setBounds(top+3*lenght+3*10, left+3*height+3*10, lenght, height);
		*/
		
		Border border = LineBorder.createGrayLineBorder();
		labSpielername.setBounds(5, 5, 160, 40);
		labSpielername.setFont(new Font("Dialog", Font.PLAIN, 35));
		labSpielername.setBorder(border);
		labSpielername.setVisible(false);
		cp.add(labSpielername);
		
	    labSpielModus.setBounds(170, 0, 100, 20);
	    labSpielModus.setVisible(false);
	    labSpielModus.setFont(new Font("Dialog", Font.PLAIN, 11));
	    labSpielModus.setText("Modus: 4x4");
	    cp.add(labSpielModus);

	    labRestminen.setBounds(170, 14, 100, 20);
	    labRestminen.setVisible(false);
	    labRestminen.setFont(new Font("Dialog", Font.PLAIN, 11));
	    labRestminen.setText("Minen: "+Integer.toString(minen));
	    cp.add(labRestminen);
	    
	    labMinenRichtig.setBounds(170, 28, 100, 20);
	    labMinenRichtig.setVisible(false);
	    labMinenRichtig.setFont(new Font("Dialog", Font.PLAIN, 11));
	    labMinenRichtig.setText("Mine Richtig "+Integer.toString(minerichtig));
	    cp.add(labMinenRichtig);
	    
	    labVersion.setBounds(285, 240, 100, 30);
	    labVersion.setVisible(true);
	    labVersion.setText(versiont);
	    cp.add(labVersion);
		
		
		ButtonSpielNeustart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				ButtonSpielNeustart_ActionPerformed(evt);
			}
		});

		// Methode zum Button-klick-ausfuehren
		ButtonResetSpielfeld.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				ButtonResetSpielfeld_ActionPerformed();
			}
		});

		ButtonSpielernameOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				ButtonSpielernameOK_ActionPerformed();
			}
		});

		// Komponenten erzeugen
		cp.add(ButtonSpielNeustart);
		cp.add(ButtonResetSpielfeld);
		cp.add(ButtonSpielernameOK);
		// Ende Komponenten

		setVisible(true);
	} // end of public Test

	
	// Anfang Methoden
	public static void main(String[] args) {
		new Test();
	}
	
	public void ButtonSpielNeustart_ActionPerformed(ActionEvent evt) {
		// TODO hier Quelltext einfÃ¼gen
		// Spielfeld Oberflaeche zu beginn

		for (int i = 0; i < buttons.length; i++) {
			buttons[i].setText(".");
		}

		// Spielfeld aktivieren
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].setEnabled(true);
		}

		Spielfeldgesperrt = false;

		// Spielfeldgeklickt Array zurücksetzen
		for (int i = 0; i < Spielfeldgeklickt.length; i++) {
			Spielfeldgeklickt[i] = 0;
		}
		minen=3;
		labRestminen.setText("Minen: "+Integer.toString(minen));
		minerichtig=0;
		labMinenRichtig.setText("Mine Richtig "+Integer.toString(minerichtig));	
	}

	public void ButtonSpielernameOK_ActionPerformed() {

		// Spielfeld aktivieren
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].setEnabled(true);
		}

		// Name einlesen
		Spielername = textfSpielernameEingabe.getText();
		textfSpielernameEingabe.setVisible(false);
		labSpielername.setText(Spielername);
		labSpielername.setVisible(true);
		labSpielModus.setVisible(true);
		labRestminen.setVisible(true);
		labMinenRichtig.setVisible(true);

		ButtonSpielNeustart.setEnabled(true);
		Spielfeldgesperrt = false;

		// Button zum Schluss deaktivieren
		ButtonSpielernameOK.setVisible(false);

	} // end of jButton1_ActionPerformed

	public void ButtonResetSpielfeld_ActionPerformed() {

		// TODO hier Quelltext einfÃ¼gen
		// Spielfeld Oberflaeche zu beginn
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].setText(".");
		}

		// Spielfeld deaktivieren
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].setEnabled(false);
		}

		Spielfeldgesperrt = true;
		// Spielfeldgeklickt Array zurücksetzen
		for (int i = 0; i < 17; i++) {
			Spielfeldgeklickt[i] = 0;
		}

		// Spielername zurücksetze, Label weg, Eingabe da
		Spielername = "none";
		textfSpielernameEingabe.setVisible(true);
		textfSpielernameEingabe.setText("");
		labSpielername.setVisible(false);
		labSpielModus.setVisible(false);
		labRestminen.setVisible(false);
		labMinenRichtig.setVisible(false);
		ButtonSpielernameOK.setVisible(true);
		ButtonSpielNeustart.setEnabled(false);
		minen=3;
		labRestminen.setText("Minen zähler "+Integer.toString(minen));
		minerichtig=0;
		labMinenRichtig.setText("Mine Richtig "+Integer.toString(minerichtig));	
	}

	public static void sieg() {

		   labMinenRichtig.setText("Mine Richtig "+Integer.toString(minerichtig));	  
			  if (minerichtig==mine && minen==0) {
			  		 JOptionPane.showMessageDialog(null, "Spiel gewonnen");

			// To-Do: Auswahl reset oder nochmal spielen (=neustarten) in der
			// Sieg-Meldung

			// Hier jetzt: Spiel gewonnen = Spielfeld deaktiviert. Reset und
			// Neustart können ausgewählt werden.
			for (int i = 0; i < buttons.length; i++) {
				buttons[i].setEnabled(false);
			}
		}
	}

	public static void mine() {

		JOptionPane.showMessageDialog(null, "Mine! Verloren");

		for (int i = 0; i < buttons.length; i++) {
			buttons[i].setEnabled(false);
		}
		
	}
	
	
	  public static void setMineMarkiert() {
		  minen=minen-1;
		  labRestminen.setText("Minen zähler "+Integer.toString(minen));
	  }
	  public static void setMarkierteMineFreigeben() {
		  minen=minen+1;
		  labRestminen.setText("Minen zähler "+Integer.toString(minen));
	  }
	  
	  public static void mineRichtig(int i) {
		  minerichtig=minerichtig+i;
	  }
	
	
	//Button aus MouseInput heraus deaktivieren
	public static void setDisabled(int i) {
		buttons[i].setEnabled(false);
	}

	public static void setText(int i) {
		buttons[i].setText(a_btnText[i]);
	}
	
	public static void setText(int i,String text) {
		buttons[i].setText(text);
	}
	
	public static void setSpielfeldgeklickt(int i, int j) {
		Spielfeldgeklickt[i] = j;
	}
	// Ende Methoden
	
} // end of class Test