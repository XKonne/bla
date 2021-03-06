package bla;

public abstract class ObjectHandler {

	private static Spiel spiel;
	private static Spieler spieler;
	private static GUI_AddMenubar gui_AddMenubar;
	private static GUI_SpielerAuswahl gui_SpielerAuswahl;
	private static GUI_Spielerprofil gui_Spielerprofil;
	private static GUI_Spielfeld gui_Spielfeld;
	private static GUI_SpielfeldMP gui_SpielfeldMP;
	private static GUI_SpielModusBenutzer gui_SpielModusBenutzer;
	private static GUI_Start gui_Start;

	public static void createGui_AddMenubar() {
		ObjectHandler.gui_AddMenubar = new GUI_AddMenubar();
	}

	public static void createGui_SpielerAuswahl() {
		ObjectHandler.gui_SpielerAuswahl = new GUI_SpielerAuswahl();
	}

	public static void createGui_Spielerprofil() {

		if (gui_Spielerprofil == null) {
			ObjectHandler.gui_Spielerprofil = new GUI_Spielerprofil();
		} else {
			gui_Spielerprofil.setupGUI();
		}

	}

	public static void createGui_Spielfeld() {

		if (gui_Spielfeld == null) {
			ObjectHandler.gui_Spielfeld = new GUI_Spielfeld();
		}

	}
	
	public static void createGui_SpielfeldMP() {

		if (gui_SpielfeldMP == null) {
			ObjectHandler.gui_SpielfeldMP = new GUI_SpielfeldMP();
		}

	}

	public static void createGui_SpielModusBenutzer() {

		if (gui_SpielModusBenutzer == null) {
			ObjectHandler.gui_SpielModusBenutzer = new GUI_SpielModusBenutzer();
		}

	}

	public static void createGui_Start() {
		ObjectHandler.gui_Start = new GUI_Start();
	}

	public static void createSpiel() {
		ObjectHandler.spiel = new Spiel();
	}

	public static void createSpieler() {
		ObjectHandler.spieler = new Spieler();
	}

	public static GUI_AddMenubar getGui_AddMenubar() {
		return gui_AddMenubar;
	}

	public static GUI_SpielerAuswahl getGui_SpielerAuswahl() {
		return gui_SpielerAuswahl;
	}

	public static GUI_Spielerprofil getGui_Spielerprofil() {

		if (gui_Spielerprofil == null) {
			createGui_Spielerprofil();
		}
		return gui_Spielerprofil;

	}

	public static GUI_Spielfeld getGui_Spielfeld() {
		return gui_Spielfeld;
	}

	public static GUI_SpielModusBenutzer getGui_SpielModusBenutzer() {
		return gui_SpielModusBenutzer;
	}

	public static GUI_Start getGui_Start() {
		return gui_Start;
	}

	public static Spiel getSpiel() {
		return spiel;
	}

	public static Spieler getSpieler() {
		return spieler;
	}

	public static void setSpiel(Spiel spiel) {
		ObjectHandler.spiel = spiel;
	}

	public static void setSpieler(Spieler spieler) {
		ObjectHandler.spieler = spieler;
	}

	public static void setGui_AddMenubar(GUI_AddMenubar gui_AddMenubar) {
		ObjectHandler.gui_AddMenubar = gui_AddMenubar;
	}

	public static void setGui_SpielerAuswahl(GUI_SpielerAuswahl gui_SpielerAuswahl) {
		ObjectHandler.gui_SpielerAuswahl = gui_SpielerAuswahl;
	}

	public static void setGui_Spielerprofil(GUI_Spielerprofil gui_Spielerprofil) {
		ObjectHandler.gui_Spielerprofil = gui_Spielerprofil;
	}

	public static void setGui_Spielfeld(GUI_Spielfeld gui_Spielfeld) {
		ObjectHandler.gui_Spielfeld = gui_Spielfeld;
	}

	public static void setGui_SpielModusBenutzer(GUI_SpielModusBenutzer gui_SpielModusBenutzer) {
		ObjectHandler.gui_SpielModusBenutzer = gui_SpielModusBenutzer;
	}

	public static void setGui_Start(GUI_Start gui_Start) {
		ObjectHandler.gui_Start = gui_Start;
	}
}
