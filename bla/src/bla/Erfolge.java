package bla;

public class Erfolge {

	public Erfolge() {
		// TODO Auto-generated constructor stub
	}
	
	public void spieleGespielt(String modus, int bisherigeSpiele) {
		if (modus == "Leicht" || modus == "Mittel" || modus== "Schwer") {
			if (bisherigeSpiele > 25) {
				setErfolge(1, true);
			}
		}
	}
	
	private void setErfolge(int idErfolg, boolean onOff) {
		switch(idErfolg) {
			case 0: System.out.println("Erfolg 0 errungen"); break;
			case 1: System.out.println("Erfolg 1 errungen"); break;
			case 2: System.out.println("Erfolg 2 errungen"); break;
			case 3: System.out.println("Erfolg 3 errungen"); break;
			case 4: System.out.println("Erfolg 4 errungen"); break;
			case 5: System.out.println("Erfolg 5 errungen"); break;
			case 6: System.out.println("Erfolg 6 errungen"); break;
			case 7: System.out.println("Erfolg 7 errungen"); break;
			case 8: System.out.println("Erfolg 8 errungen"); break;
			case 9: System.out.println("Erfolg 9 errungen"); break;
			case 10: System.out.println("Erfolg 10 errungen"); break;
			case 11: System.out.println("Erfolg 11 errungen"); break;
			case 12: System.out.println("Erfolg 12 errungen"); break;
			case 13: System.out.println("Erfolg 13 errungen"); break;
			case 14: System.out.println("Erfolg 14 errungen"); break;
			case 15: System.out.println("Erfolg 15 errungen"); break;
			case 16: System.out.println("Erfolg 16 errungen"); break;
			case 17: System.out.println("Erfolg 17 errungen"); break;
			case 18: System.out.println("Erfolg 18 errungen"); break;
			case 19: System.out.println("Erfolg 19 errungen"); break;
			case 20: System.out.println("Erfolg 20 errungen"); break;
			case 21: System.out.println("Erfolg 21 errungen"); break;
			case 22: System.out.println("Erfolg 22 errungen"); break;
			case 23: System.out.println("Erfolg 23 errungen"); break;
			
			default: System.out.println("Erfolge: Error - Erfolge-ID nicht gefunden."); 
        } 
	}

}
