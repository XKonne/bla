package bla;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.*;


public class GUI_SpielerAuswahl extends JFrame implements ActionListener {
	// Objekte
	Spieler spielerA;
	JFrame guiStart;
	
	// Frame-Container
	JFrame gui_SpielerAuswahl = new JFrame();
	Container cp3 = gui_SpielerAuswahl.getContentPane();
	
	// Variablen
	// Boolean
	static boolean EingabeRichtig = false;
	// Integer
	int spielerIndex=-2;
	
	// Arrays
	String arr_SpielerListe[] = new String[8];
	
	// GUI-Elemente
	// Buttons
	private JButton btn_NeuerSpielerAnlegen = new JButton();
	private JButton btn_SpielerAusgewaehlt = new JButton();
	
	// Checkbox
	 private JCheckBox chb_SpielerMerken = new JCheckBox();
	
	// Labels
	private JLabel lab_Spielerliste = new JLabel();
	private JLabel lab_NeuerSpieler = new JLabel();
	
	// List
	private JList lis_Spieler = new JList();
	
	// Textfelder
	private JTextField txt_SpielerNameEingabe = new JTextField();
	
	
	
	public GUI_SpielerAuswahl(JFrame gui_Start, Spieler spieler) {
		
		guiStart = gui_Start;
		spielerA = spieler;
		
		initFrame();
		createButtons();
		createLabels();
		createTextfield();
		
		goOn();
		gui_SpielerAuswahl.setVisible(true);
	}
	
	private void goOn() {
		
		txt_SpielerNameEingabe.setText("");
		loadSpielerListe();	
		createJList();
	}

	private void createJList() {
		
        JList lis_Spieler = new JList(arr_SpielerListe);
	    lis_Spieler.setBounds(10, 30, 120, 145);
	    lis_Spieler.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    cp3.add(lis_Spieler);
	    
	    ListSelectionListener listSelectionListener = new ListSelectionListener() {
	        public void valueChanged(ListSelectionEvent listSelectionEvent) {
	         spielerIndex=lis_Spieler.getSelectedIndex();
	         btn_SpielerAusgewaehlt.setEnabled(true);
	        }
	      };
	      lis_Spieler.addListSelectionListener(listSelectionListener);
	}

	private void createTextfield() {
		
		txt_SpielerNameEingabe.setBounds(140, 30, 150, 30);
		txt_SpielerNameEingabe.setVisible(true);
		cp3.add(txt_SpielerNameEingabe);
	}

	public void actionPerformed(ActionEvent object) {
		
		if (object.getSource() == btn_SpielerAusgewaehlt) {
			spielerA.setSpielerName(arr_SpielerListe[spielerIndex]);
			GUI_Start.aktiviereGUI_Start();
						
			gui_SpielerAuswahl.dispose();
		}
		if (object.getSource() == btn_NeuerSpielerAnlegen) {
			EingabeRichtig = pruefeSpielerNameEingabe();

			if (EingabeRichtig == true) {
				GUI_Start.aktiviereGUI_Start();
				gui_SpielerAuswahl.dispose();
			}
		}
	}

	private Boolean pruefeSpielerNameEingabe() {
		
		// Eingabebedingungen
		if (txt_SpielerNameEingabe.getText().length() == 0 || txt_SpielerNameEingabe.getText().length() == 1 || txt_SpielerNameEingabe.getText().length() == 2 || txt_SpielerNameEingabe.getText().length() > 8) {
			JOptionPane.showMessageDialog(null,
					"Ein Spielername darf aus mindestens aus 3 und maximal 8 Zeichen bestehen.");
			EingabeRichtig = false;
		}
		// Eingabe erfüllt Bedingungen.
		else {
			EingabeRichtig = true;
			// Eingabe im Spielerobjekt setzen
			spielerA.setSpielerName(txt_SpielerNameEingabe.getText());
		}

		return EingabeRichtig;
	}

	private void createButtons() {

		btn_SpielerAusgewaehlt.setBounds(140, 150, 90, 25);
		btn_SpielerAusgewaehlt.setText("Auswählen");
		btn_SpielerAusgewaehlt.setMargin(new Insets(2, 2, 2, 2));
		btn_SpielerAusgewaehlt.setEnabled(false);
		cp3.add(btn_SpielerAusgewaehlt);
		btn_SpielerAusgewaehlt.addActionListener(this);
		
		btn_NeuerSpielerAnlegen.setBounds(200, 65, 90, 25);
		btn_NeuerSpielerAnlegen.setText("Anlegen");
		btn_NeuerSpielerAnlegen.setMargin(new Insets(2, 2, 2, 2));
		cp3.add(btn_NeuerSpielerAnlegen);
		btn_NeuerSpielerAnlegen.addActionListener(this);
		
		// Checkbox
	    chb_SpielerMerken.setBounds(10, 180, 300, 20);
	    chb_SpielerMerken.setText("Ausgewählten Spieler speichern.");
	    chb_SpielerMerken.setOpaque(false);
	    chb_SpielerMerken.setEnabled(false);
	    cp3.add(chb_SpielerMerken);
	}

	private void createLabels() {

		lab_Spielerliste.setBounds(10, 10, 100, 20);
		lab_Spielerliste.setText("Spielerliste");
		cp3.add(lab_Spielerliste);
		
		lab_NeuerSpieler.setBounds(140, 10, 100, 20);
		lab_NeuerSpieler.setText("Neuer Spieler");
		cp3.add(lab_NeuerSpieler);
	}

	private void initFrame() {
		
		gui_SpielerAuswahl.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		gui_SpielerAuswahl.setSize(310, 240);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (d.width - getSize().width) / 2;
		int y = (d.height - getSize().height) / 2;
		gui_SpielerAuswahl.setLocation(x, y);
		gui_SpielerAuswahl.setTitle("Spieler");
		gui_SpielerAuswahl.setResizable(false);
		Container cp3 = gui_SpielerAuswahl.getContentPane();
		cp3.setLayout(null);
		gui_SpielerAuswahl.setVisible(true);
	}
	
	public void loadSpielerListe() {
		
		DataIO data = new DataIO();
		
		data.createSpielerList();
		
		for (int i=0; i<data.returnLengthSpielerListe(); i++) {
			arr_SpielerListe[i]=data.getSpielerListe(i);
		}	
	}

}
