package bla;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DataIO {

	static String dataFilename = "data.txt";
	Spieler spieler;

	public DataIO(Spieler spieler) {
		this.spieler = spieler;
	}

	/**
	 * Hier wird die Datei ("data.txt") geladen, sofern sie existiert. Falls
	 * nicht, wird sie erstellt.
	 * 
	 * Die geladene Datei wird zeilenweise gelesen. Wenn der Spielername des
	 * aktiven Spielers in der Datei gefunden wird, werden dessen Stats geladen.
	 */
	public void loadData() {

		Boolean spielerGefunden = false;
		String data;

		try {
			Scanner sc = new Scanner(new File(dataFilename));
			while (sc.hasNext() && spielerGefunden == false) {

				data = sc.nextLine();
				spielerGefunden = loadSpieler(data);

			}
			sc.close();
		} catch (FileNotFoundException e) {
			createFile();
		}
	}

	/**
	 * 
	 * Erstellt eine Datei (hier data.txt)
	 */
	private void createFile() {

		FileWriter writer;
		try {
			writer = new FileWriter(DataIO.dataFilename);
		} catch (IOException e) {
		}

	}

	/**
	 * Splittet den übergebenen String bei jedem "|". Die gesplitteten Strings
	 * werden zwischengespeichert und dann das entsprechende Spielerattribut
	 * gesetzt.
	 * 
	 * @param data
	 *            String, der die Spielerdaten enthält
	 * 
	 * @return spielerGefunden
	 * 			  Boolean, der die Schleife der aufrufenden Methode
	 *            abbricht, sobald der Spielername in der Datei gefunden wurde.
	 */
	private boolean loadSpieler(String data) {

		Boolean spielerGefunden = false;
		String[] dataArray = data.split("\\|");

		// Suche....
		if (dataArray[0].equals(spieler.getSpielerName())) {

			spieler.setSpieleGespielt(Integer.parseInt(dataArray[1]));
			spieler.setSpieleGewonnen(Integer.parseInt(dataArray[2]));
			spieler.calculateSpieleGewonnenProzent();
			spieler.setMinenGefunden(Integer.parseInt(dataArray[3]));
			spieler.setZeitGesamt(Long.parseLong(dataArray[4]));
			spieler.setZeitLetztesSpiel(Long.parseLong(dataArray[5]));
			spieler.setZeitSchnellsterSieg(Long.parseLong(dataArray[6]));
			// spieler.setProfilBild = 0; // Profilbild noch nicht ausreichend
			// implementiert
			spieler.setSpielerSiegesserie(Integer.parseInt(dataArray[7]));

			spielerGefunden = true;
		}

		return spielerGefunden;
	}

}
