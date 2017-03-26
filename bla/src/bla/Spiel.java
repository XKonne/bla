package bla;

public class Spiel {
	
	private static int minen = 3;
	

	public Spiel() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
		Spiel spiel = new Spiel();
		Test test = new Test(spiel);

	}

	public static int getMinen() {
		return minen;
	}

	public static void setMinen(int minen) {
		Spiel.minen = minen;
	}
	
	

}
