package bla;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import java.awt.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class TestSpielfeld extends JFrame implements ActionListener {
	
	public int zeilen=4+2; 
	public int spalten=4+2; 
	public int minen=3;
	public int [][] spielfeld = new int [zeilen][spalten];
	
	Random random = new Random();
	Random random2 = new Random();
	
	private JButton reload = new JButton();
	
	private JLabel lab_Spielerliste = new JLabel();
	
	private JPanel jPanel1 = new JPanel();
	private JPanel jPanel2 = new JPanel();
	Feld[][] Felder = new Feld[zeilen][spalten];
	
	JButton Spielfel = new JButton();
	
	// Frame-Container
	private JFrame gui_testspiel = new JFrame();
	private Container cp = gui_testspiel.getContentPane();
	
	private Container cp2 = new Container();

	public TestSpielfeld() {
		
		initFrame();
		createSpielfeld();
		createButtons();
		initSpielfeldStatus();
		setMinen();
		setZahlen();
		
		setButtontext();
	}
	


	private void initSpielfeldStatus() {
		
		for (int i=0; i<zeilen; i++) {
			for (int j=0; j<spalten; j++) {
				spielfeld[i][j]=0;
			}
		}
	}
	
	private void setMinen() {
		
		for (int i=1; i<4; i++) {
			int x = random.nextInt(zeilen-2)+1;
			int y = random2.nextInt(spalten-2)+1;
			if (spielfeld[x][y] == -1) {
				i=i-1;
				JOptionPane.showMessageDialog(null, "boing");
			}
			else {
				spielfeld[x][y]=-1;
			}
			JOptionPane.showMessageDialog(null, "x: " + x + "/ y: " + y);
		}
	}
	
	private void setZahlen() {
		for (int i=1; i<zeilen-1; i++) {
			for (int j=1; j<spalten-1; j++) {
				if (spielfeld[i][j]==-1) {
					if (spielfeld[i-1][j-1] != -1) {
						spielfeld[i-1][j-1]=spielfeld[i-1][j-1]+1;
					}
					if (spielfeld[i][j-1] != -1) {
						spielfeld[i][j-1]=spielfeld[i][j-1]+1;
					}
					if (spielfeld[i+1][j-1] != -1) {
						spielfeld[i+1][j-1]=spielfeld[i+1][j-1]+1;
					}
					if (spielfeld[i-1][j] != -1) {
						spielfeld[i-1][j]=spielfeld[i-1][j]+1;
					}
					if (spielfeld[i+1][j] != -1) {
						spielfeld[i+1][j]=spielfeld[i+1][j]+1;
					}
					if (spielfeld[i-1][j+1] != -1) {
						spielfeld[i-1][j+1]=spielfeld[i-1][j+1]+1;
					}
					if (spielfeld[i][j+1] != -1) {
						spielfeld[i][j+1]=spielfeld[i][j+1]+1;
					}
					if (spielfeld[i+1][j+1] != -1) {
						spielfeld[i+1][j+1]=spielfeld[i+1][j+1]+1;
					}
				}
			}
		}
	}
	
	
	

	
	private void initFrame() {
		cp.setLayout(new BorderLayout());
//		jPanel2.setLayout(new BoxLayout(jPanel2, BoxLayout.X_AXIS));
//		gui_testspiel.getContentPane().add(jPanel2, BorderLayout.PAGE_START);

		cp2.setLayout(null);
		cp2.setPreferredSize(new Dimension(400,50));
		gui_testspiel.getContentPane().add(cp2, BorderLayout.PAGE_START);
		
		jPanel1.setLayout(new GridLayout(zeilen-2, spalten-2));
		
		gui_testspiel.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		int height=zeilen*35+50;
		int width=spalten*35;
		gui_testspiel.setSize(width, height);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (d.width - getSize().width) / 2;
		int y = (d.height - getSize().height) / 2;
		gui_testspiel.setLocation(x-width/2, y-height/2);
		
		gui_testspiel.setTitle("testspiel");
		gui_testspiel.setResizable(false);
		
		gui_testspiel.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent object) {
		if (object.getSource() == reload) {
			nochmal();
		}
	}
	
	private void nochmal() {
		initSpielfeldStatus();
		setMinen();
		setZahlen();
		setButtontext();
	}

	private void createButtons() {

		reload.setBounds(140, 10, 200, 25);
		reload.setMargin(new Insets(2, 2, 2, 2));
		reload.setText("reload");
		cp2.add(reload);
		reload.addActionListener(this);
		
		reload.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		        //reload.setBackground(Color.GREEN);

		        reload.setBorder(BorderFactory.createLineBorder(Color.red));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		       // reload.setBackground(UIManager.getColor("control"));
		        reload.setBorder(BorderFactory.createLineBorder(null));
		    }
		});
		
		lab_Spielerliste.setBounds(10, 10, 100, 20);
		lab_Spielerliste.setText("Spielerliste");
		cp2.add(lab_Spielerliste);
		
	//	gui_testspiel.getContentPane().add(cp2, BorderLayout.PAGE_START);
	}
	
	private void setButtontext() {

		for (int z=1; z<zeilen-1; z++) {
			for (int sp=1; sp<spalten-1; sp++) {
				Felder[z][sp].setText(Integer.toString(spielfeld[z][sp]));
			}
		}
	}
	
	private void createSpielfeld() {
		
		for (int z=1; z<zeilen-1; z++) {
			for (int sp=1; sp<spalten-1; sp++) {
				Felder[z][sp] = new Feld();
				jPanel1.add(Felder[z][sp]);
			}
		}

		gui_testspiel.getContentPane().add(jPanel1, BorderLayout.CENTER);
		pack();
	}

}
