package bla;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

	int buttonnummer;
	private static Spiel spiel;
	private static Test test;

	public MouseInput(int i_buttonnummer) {
		buttonnummer = i_buttonnummer;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent e) {

		if (test.Spielfeldgesperrt == false) {
			if (e.getButton() == MouseEvent.BUTTON1 && test.Spielfeldgeklickt[buttonnummer] != 5) {
				Test.setText(buttonnummer, test.a_btnText[buttonnummer]);
				Test.setDisabled(buttonnummer);
				if (test.Spielfeldgeklickt[buttonnummer] == 2) {
					spiel.countMinenMarkierung(1);
				}
				if (test.a_btnText[buttonnummer] == "M") {
//					test.mineAufgedeckt();
					test.aufSiegpruefen(true);
				}
				Test.setSpielfeldgeklickt(buttonnummer, 5);
			}

			if (e.getButton() == MouseEvent.BUTTON3 && test.Spielfeldgeklickt[buttonnummer] != 5) {

				if (test.Spielfeldgeklickt[buttonnummer] == 0) {
					Test.setText(buttonnummer, "*");
					if (test.Spielfeldgeklickt[buttonnummer] != 2) {
						if (test.a_btnText[buttonnummer] == ("M")) {
							spiel.countMineRichtig(1);
						}
						spiel.countMinenMarkierung(-1);
					}
					Test.setSpielfeldgeklickt(buttonnummer, 2);
				} else {
					Test.setText(buttonnummer, ".");
					if (test.a_btnText[buttonnummer] == ("M")) {
						spiel.countMineRichtig(-1);
					}
					spiel.countMinenMarkierung(1);
					Test.setSpielfeldgeklickt(buttonnummer, 0);
				}
			}

		}
		test.aufSiegpruefen(false);
	} // end-mousePressed

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

	public static void setupMouseInput(Spiel spiel, Test test) {
		MouseInput.spiel = spiel;
		MouseInput.test = test;
	}

}