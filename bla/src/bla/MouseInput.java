package bla;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class MouseInput implements MouseListener {

	Test test;
	int i;

	public MouseInput(int pi) {
		i = pi;
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

	           	if (test.Spielfeldgesperrt==false) {
	        		if (e.getButton() == MouseEvent.BUTTON1 && test.Spielfeldgeklickt[i]!=5) {
	        			test.setText(i,test.a_btnText[i]);
	        			test.setDisabled(i);
	        			if (test.Spielfeldgeklickt[i]==2) {
	        				test.countMinenMarkierung(1);
	        			}
	        			if (test.a_btnText[i] == "M") {
	        				test.mineAufgedeckt();
	        			}
	        			test.setSpielfeldgeklickt(i,5);
	        		}
	        		
	        		if (e.getButton() == MouseEvent.BUTTON3 && test.Spielfeldgeklickt[i]!=5) {
	        			
	        			if (test.Spielfeldgeklickt[i]==0) {
	        				test.setText(i, "*");
	        				if (test.Spielfeldgeklickt[i]!=2) {
	        					if (test.a_btnText[i]==("M")) {
	        						test.countMineRichtig(1);
	        					}
	        					test.countMinenMarkierung(-1);
	        				}
	        				test.setSpielfeldgeklickt(i,2);
	        			}
	        			else {
	        				test.setText(i, ".");
	    					if (test.a_btnText[i]==("M")) {
	    						test.countMineRichtig(-1);
	    					}
	        				test.countMinenMarkierung(1);
	        				test.setSpielfeldgeklickt(i,0);
	        			}
	        		}
	        		
	        	}
	        	test.aufSiegpruefen();        	
	        } // end-mousePressed       

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

}