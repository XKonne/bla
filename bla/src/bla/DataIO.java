package bla;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JOptionPane;


public abstract class DataIO {

	private static String dataFilename = "./data.txt";
	private static String SpielerListe[] = new String[0];
	
	public DataIO() {
	}

	
	/**
	 * Erstellt eine Datei (hier data.txt).
	 */
	private static void createFile() {

		FileWriter writer;
		try {
			writer = new FileWriter(DataIO.dataFilename);
			writer.close();
		} catch (IOException e) {
		}

	}
	
	public static void createSpielerList() {
		int anzahlSpieler = zaehlerSpieler();
		
		SpielerListe = new String[anzahlSpieler];
		
		spielernameAuslesen();
	}
		
	/**
	 * Liest alle Spielernamen aus der "data.txt"-Datei und schreibt sie in das String-Array Spielerliste[].
	 */
	private static void spielernameAuslesen() {
		
		int indexSpieler=0;
		String data;
		
		try {
			Scanner sc = new Scanner(new File(dataFilename));
			while (sc.hasNext()) {
				data = sc.nextLine();
				
				String[] dataArray = data.split("\\|");
				SpielerListe[indexSpieler]=dataArray[0];
				indexSpieler=indexSpieler+1;
				
			}
			sc.close();
			
		} catch (FileNotFoundException e) {
			createFile();
		}
	}
	
	
	/**
	 * Die alte Datei wird gelöscht.
	 * Die neue Datei wird erstellt und am Ende umbenannt.
	 */
	private static void deleteAndRenameFile() {
		File realName = new File(dataFilename);
		realName.delete();
		new File("./tmp_data.txt").renameTo(realName);
	}
	
	
	/**
	 * Das Ergebnis wird hiermit persistiert.
	 * 
	 * Editieren nicht möglich, daher wird alte Datei eingelesen und jede Zeile
	 * kopiert. Die Zeile des Spielers wird dabei aktualisiert.
	 * 
	 * @param
	 * 		spieler Objekt der Klasse Spieler.
	 * 
	 * @throws IOException
	 */
	private static void editSpielerData(Spieler spieler) {

		String tmpFileName = "./tmp_data.txt";

		Scanner sc = null;
		FileWriter updateWriter = null;
		try {
			sc = new Scanner(new File(dataFilename));
			updateWriter = new FileWriter(tmpFileName);
			String line;
			while ((line = sc.nextLine()) != null) {
				if (line.contains(spieler.getSpielerName())) {
					line = fetchSpielerData(spieler);
				}

				updateWriter.write(line + "\n");

			}
		} catch (Exception e) {
			return;
		} finally {
			if (sc != null) {
				sc.close();
			}
			try {
				if (updateWriter != null) {
					updateWriter.flush();
					updateWriter.close();
				}
			} catch (IOException e) {
			}

		}
		
	}
	
	
	/**	 * Lädt alle Spielerdaten, um diese anschließend schreiben zu können.
	 * 
	 * @param
	 * 		spieler Objekt der Klasse Spieler.
	 * 
	 * @return String mit allen Spielerdaten zum Schreiben
	 */
	private static String fetchSpielerData(Spieler spieler) {

		return spieler.getSpielerName() + "|" + spieler.getSpieleGespielt() + "|" + spieler.getSpieleGewonnen() + "|"
				+ spieler.getSpielerSiegesserie() + "|" + spieler.getMaxSiegesserie() + "|" + spieler.getMinenGefunden() + "|" + spieler.getZeitGesamt() + "|" 
				+ spieler.getZeitLetztesSpiel() + "|" + spieler.getZeitSchnellsterSiegl() + "|" + spieler.getSpielfolge();
	}
	
	
	public static String getSpielerListe(int i) {
		return SpielerListe[i];
	}
	
	
	/**
	 * Hier wird die Datei ("data.txt") geladen, sofern sie existiert. Falls
	 * nicht, wird sie erstellt.
	 * 
	 * Die geladene Datei wird zeilenweise gelesen. Wenn der Spielername des
	 * aktiven Spielers in der Datei gefunden wird, werden dessen Stats geladen.
	 * 
 	 * @param
	 * 		spieler Objekt der Klasse Spieler.
	 */
	public static void loadData(Spieler spieler) {

		Boolean spielerGefunden = false;
		String data;

		try {
			Scanner sc = new Scanner(new File(dataFilename));
			while (sc.hasNext() && spielerGefunden == false) {

				data = sc.nextLine();
				spielerGefunden = loadSpieler(spieler, data);

			}
			sc.close();

			if (spielerGefunden == false) {
				writeNewSpieler(spieler);
			}
			
		} catch (FileNotFoundException e) {
			createFile();
		}
	}
	

	/**
	 * Splittet den übergebenen String bei jedem "|". Die gesplitteten Strings
	 * werden zwischengespeichert und dann das entsprechende Spielerattribut
	 * gesetzt.
	 *
	 * 		
	 * @param 
	 * 		spieler Objekt der Klasse Spieler.
	 * @param 
	 * 		data String, der die Spielerdaten enthält.
	 * 
	 * @return spielerGefunden
	 * 			  Boolean, der die Schleife der aufrufenden Methode
	 *            abbricht, sobald der Spielername in der Datei gefunden wurde.
	 */
	private static boolean loadSpieler(Spieler spieler, String data) {

		Boolean spielerGefunden = false;
		String[] dataArray = data.split("\\|");

		// Suche....
		if (dataArray[0].equals(spieler.getSpielerName())) {

			spieler.setSpieleGespielt(Integer.parseInt(dataArray[1]));
			spieler.setSpieleGewonnen(Integer.parseInt(dataArray[2]));
			spieler.setSpielerSiegesserie(Integer.parseInt(dataArray[3]));
			spieler.setMaxSiegesserieFromData(Integer.parseInt(dataArray[4]));
			spieler.calculateSpieleGewonnenProzent();
			spieler.setMinenGefunden(Integer.parseInt(dataArray[5]));
			spieler.setZeitGesamt(Long.parseLong(dataArray[6]));
			spieler.setZeitLetztesSpiel(Long.parseLong(dataArray[7]));
			spieler.setZeitSchnellsterSieg(Long.parseLong(dataArray[8]));
			// spieler.setProfilBild = 0; // Profilbild noch nicht ausreichend
			// implementiert
			spieler.setSpielHistorieFromDataIO(Integer.parseInt(dataArray[9]));

			spielerGefunden = true;
		}

		return spielerGefunden;
	}
	
	
	public static Integer returnLengthSpielerListe() {
		return SpielerListe.length;
	}
	
	/**
	 * Bei Sieg oder Niederlage wird das Ergebnis persistiert.
	 * Editieren und anschließendes Löschen der Altdatei + umbenennen der neuen Datei.
	 * 
	 * @param 
	 * 		spieler Objekt der Klasse Spieler.
	 * 
	 */
	public static void updateSpielerData() {
		
		editSpielerData(ObjectHandler.getSpieler());
		deleteAndRenameFile();
		
	}


	/**
	 * Wenn der Spielername nicht in der Datei gefunden wird, erstellt diese
	 * Methode einen neuen Eintrag für diesen Spieler.
	 * 
	 * @param 
	 * 		spieler Objekt der Klasse Spieler.
	 */
	private static void writeNewSpieler(Spieler spieler) {

		FileWriter writer;
		try {
			
			writer = new FileWriter(DataIO.dataFilename, true);
			writer.write(fetchSpielerData(spieler) + "\n");
			writer.close();

		} catch (IOException e) {
		}

	}
	
	private static Integer zaehlerSpieler() {
		int indexSpieler=0;
		String data;
		
		try {
			Scanner sc = new Scanner(new File(dataFilename));
			while (sc.hasNext()) {
				
				data = sc.nextLine();
				String[] dataArray = data.split("\\|");
				
				indexSpieler=indexSpieler+1;
				
			}
			sc.close();
			
		} catch (FileNotFoundException e) {
			createFile();
		}
		return indexSpieler;
	}

}
