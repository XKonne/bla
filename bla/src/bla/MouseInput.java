package bla;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;

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
				test.setText(buttonnummer, test.a_btnText[buttonnummer]);
				test.setDisabled(buttonnummer);
				if (test.Spielfeldgeklickt[buttonnummer] == 2) {
					spiel.countMinenMarkierung(1);
				}
				if (test.a_btnText[buttonnummer] == "M") {
//					test.mineAufgedeckt();
					test.aufSiegpruefen(true);
				}
				test.setSpielfeldgeklickt(buttonnummer, 5);
			}

			if (e.getButton() == MouseEvent.BUTTON3 && test.Spielfeldgeklickt[buttonnummer] != 5) {

				if (test.Spielfeldgeklickt[buttonnummer] == 0) {
					test.setText(buttonnummer, "*");
					if (test.Spielfeldgeklickt[buttonnummer] != 2) {
						if (test.a_btnText[buttonnummer] == ("M")) {
							spiel.countMineRichtig(1);
						}
						spiel.countMinenMarkierung(-1);
					}
					test.setSpielfeldgeklickt(buttonnummer, 2);
				} else {
					test.setText(buttonnummer, ".");
					if (test.a_btnText[buttonnummer] == ("M")) {
						spiel.countMineRichtig(-1);
					}
					spiel.countMinenMarkierung(1);
					test.setSpielfeldgeklickt(buttonnummer, 0);
				}
			}

		}
		test.aufSiegpruefen(false);
	} // end-mousePressed

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	public static void setupMouseInput(Spiel spiel, Test test) {
		MouseInput.spiel = spiel;
		MouseInput.test = test;
	}

}