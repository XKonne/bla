package bla;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class GUI_SpielerAuswahl extends JFrame {
	// Objekte
	private Spieler spieler;

	// Frame-Container
	private Container cp = this.getContentPane();

	// Variablen
	// Boolean
	private static boolean EingabeRichtig = false;
	// Integer
	private int spielerIndex = 0;

	// Arrays
	String arr_SpielerListe[] = new String[8];

	// GUI-Elemente
	// Buttons
	private JButton btn_SpielerAusgewaehlt = new JButton();

	// Checkbox
	private JCheckBox chb_SpielerMerken = new JCheckBox();

	// Textfelder
	private JTextField txt_SpielerNameEingabe = new JTextField();

	public GUI_SpielerAuswahl() {

		spieler = ObjectHandler.getSpieler();
		setupGUI();

	}

	private void closeGUI_SpielerAuswahl() {
		this.dispose();
	}

	private void createButtons() {

		JButton btn_NeuerSpielerAnlegen = new JButton();

		btn_SpielerAusgewaehlt.setBounds(140, 150, 90, 25);
		btn_SpielerAusgewaehlt.setText("Auswählen");
		btn_SpielerAusgewaehlt.setMargin(new Insets(2, 2, 2, 2));
		btn_SpielerAusgewaehlt.setEnabled(false);
		cp.add(btn_SpielerAusgewaehlt);
		btn_SpielerAusgewaehlt.addActionListener(e -> spielerAuswaehlen());

		btn_NeuerSpielerAnlegen.setBounds(200, 65, 90, 25);
		btn_NeuerSpielerAnlegen.setText("Anlegen");
		btn_NeuerSpielerAnlegen.setMargin(new Insets(2, 2, 2, 2));
		cp.add(btn_NeuerSpielerAnlegen);
		btn_NeuerSpielerAnlegen.addActionListener(e -> neuenSpielerAnlegen());
	}

	private void createCheckbox() {

		chb_SpielerMerken.setBounds(10, 180, 300, 20);
		chb_SpielerMerken.setText("Ausgewählten Spieler speichern.");
		chb_SpielerMerken.setOpaque(false);
		chb_SpielerMerken.setEnabled(false);
		cp.add(chb_SpielerMerken);
		chb_SpielerMerken.addActionListener(e -> GUI_Start.setSpielerAuswahlAnzeigen(false));
	}

	private void createJList() {

		JList<String> lis_Spieler = new JList<String>(arr_SpielerListe);
		lis_Spieler.setBounds(10, 30, 120, 145);
		lis_Spieler.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cp.add(lis_Spieler);

		ListSelectionListener listSelectionListener = new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent listSelectionEvent) {
				spielerIndex = lis_Spieler.getSelectedIndex();
				btn_SpielerAusgewaehlt.setEnabled(true);
				chb_SpielerMerken.setEnabled(true);
			}
		};

		lis_Spieler.addListSelectionListener(listSelectionListener);
		this.repaint();

	}

	private void createLabels() {

		JLabel lab_Spielerliste = new JLabel();
		JLabel lab_NeuerSpieler = new JLabel();

		lab_Spielerliste.setBounds(10, 10, 100, 20);
		lab_Spielerliste.setText("Spielerliste");
		cp.add(lab_Spielerliste);

		lab_NeuerSpieler.setBounds(140, 10, 100, 20);
		lab_NeuerSpieler.setText("Neuer Spieler");
		cp.add(lab_NeuerSpieler);
	}

	private void createTextfield() {

		txt_SpielerNameEingabe.setBounds(140, 30, 150, 30);
		txt_SpielerNameEingabe.setVisible(true);
		cp.add(txt_SpielerNameEingabe);
	}

	private void initFrame() {

		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		int height = 240;
		int width = 310;
		this.setSize(width, height);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (d.width - getSize().width) / 2;
		int y = (d.height - getSize().height) / 2;
		this.setLocation(x - width / 2, y - height / 2);

		this.setTitle("Spieler");
		this.setResizable(false);
		Container cp2 = this.getContentPane();
		cp2.setLayout(null);
		this.setVisible(true);
	}

	private void loadSpielerListe() {

		DataIO.createSpielerList();

		for (int i = 0; i < DataIO.returnLengthSpielerListe(); i++) {
			arr_SpielerListe[i] = DataIO.getSpielerListe(i);
		}
	}

	private void neuenSpielerAnlegen() {
		EingabeRichtig = pruefeSpielerNameEingabe();

		if (EingabeRichtig == true) {
			GUI_Start.aktiviereGUI_Start();
			closeGUI_SpielerAuswahl();
		}
	}

	private Boolean pruefeSpielerNameEingabe() {

		// Eingabebedingungen
		if (txt_SpielerNameEingabe.getText().length() == 0 || txt_SpielerNameEingabe.getText().length() == 1
				|| txt_SpielerNameEingabe.getText().length() == 2 || txt_SpielerNameEingabe.getText().length() > 8) {
			JOptionPane.showMessageDialog(null,
					"Ein Spielername darf aus mindestens aus 3 und maximal 8 Zeichen bestehen.");
			EingabeRichtig = false;
		}
		// Eingabe erfüllt Bedingungen.
		else {
			EingabeRichtig = true;
			// Eingabe im Spielerobjekt setzen
			spieler.setSpielerName(txt_SpielerNameEingabe.getText());
		}

		return EingabeRichtig;
	}

	private void setupGUI() {

		initFrame();
		createButtons();
		createCheckbox();
		createLabels();
		createTextfield();
		this.setVisible(true);

		txt_SpielerNameEingabe.setText("");
		loadSpielerListe();
		createJList();

	}

	private void spielerAuswaehlen() {
		spieler.setSpielerName(arr_SpielerListe[spielerIndex]);
		DataIO.loadData(spieler);
		GUI_Start.aktiviereGUI_Start();

		closeGUI_SpielerAuswahl();
	}

}
