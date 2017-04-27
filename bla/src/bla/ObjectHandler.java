package bla;

import java.util.ArrayList;

public abstract class ObjectHandler {
	
//	private static Spiel spiel = new Spiel();
	
	private static ArrayList<Spieler> spielerList = new ArrayList<Spieler>();
	private static Spieler spieler;
	private static GUI_AddMenubar gui_AddMenubar;
	private static GUI_SpielerAuswahl gui_SpielerAuswahl;
	private static GUI_Spielerprofil gui_Spielerprofil;
	private static GUI_Spielfeld gui_Spielfeld;
	private static GUI_SpielModusBenutzer gui_SpielModusBenutzer;
	private static GUI_Start gui_Start;
	
public static void createGui_AddMenubar() {
	ObjectHandler.gui_AddMenubar = new GUI_AddMenubar();
}

public static void createGui_SpielerAuswahl() {
//	ObjectHandler.gui_SpielerAuswahl = new GUI_SpielerAuswahl();
}

public static void createGui_Spielerprofil() {
//	ObjectHandler.gui_Spielerprofil = new GUI_Spielerprofil();
}

public static void createGui_Spielfeld() {
//	ObjectHandler.gui_Spielfeld = new GUI_Spielfeld();
}

public static void createGui_SpielModusBenutzer() {
//	ObjectHandler.gui_SpielModusBenutzer = new GUI_SpielModusBenutzer();
}

public static void createGui_Start() {
	ObjectHandler.gui_Start = new GUI_Start();
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

public static Spieler getSpieler() {
	return spieler;
}

public ObjectHandler() {
	
	spielerList.add(spieler);
	
}


}
