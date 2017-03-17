package bla;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class MouseInput implements MouseListener {

	Test test;
	int i;

	public MouseInput(int pi) {
		super();
		i = pi;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		if (e.getButton() == MouseEvent.BUTTON1) {
			if (test.Spielfeldgesperrt == false) {

				if (test.a_btnText[i] == "M") {
					test.mine();
					test.setSpielfeldgeklickt(i);
				}

				if (test.Spielfeldgeklickt[i] == 0) {
					test.setSpielfeldgeklickt(i);
					test.gewonnen -= 1;
					test.sieg();
				}
				test.setText(i);
			}
		}

		if (e.getButton() == MouseEvent.BUTTON3) {
			if (test.Spielfeldgesperrt == false) {
				test.setText(i, "*");
			}
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

}