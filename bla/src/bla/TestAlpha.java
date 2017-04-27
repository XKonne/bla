
package bla;



import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


/**
  * Spielprojekt "Seawolf"
  * 	GameApp-Name <not set/actually nameless>
  *
  * @version A.1.8 vom 17.03.2017 - finished
  * @author XKonne
  * @author p0sE-Git
  * 
  */

@SuppressWarnings("serial")
public class TestAlpha extends JFrame {
  // Anfang Attribute
	
  String Spielername;
  int gewonnen=16;
  //für Label minenzähler
  int minen=3;
  //anzahl der minen im spiel
  int mine=3;
  //zähler richtig markierte mine
  int minerichtig=0;
  
  String a_Spielfeld[] = { "X", "-", "-", "1", "M", "1", "2", "3", "2", "1", "M", "M", "1", "1", "2", "2", "1" };
  
  //Variable für Abfrage für Linkslick ob Spielfeld freigegeben
  boolean Spielfeldgesperrt=true;
  //0=Spielfeld noch nicht geklickt --- 1=Spielfeld bereits 1x gedrückt
  int[] Spielfeldgeklickt = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}; //i=1..16 genutzt
	
  long startTime = System.nanoTime();
  long estimatedTime = System.nanoTime() - startTime;
  
  private JLabel jLabel1 = new JLabel();
  private JLabel RestMinen = new JLabel();
  private JLabel MinenRichtig = new JLabel();
  
  private JLabel Spielfeldaufdecken = new JLabel();
  
  private JButton ButtonSpielNeustart = new JButton();
  
  //Spiel-Oberflaeche
  private JButton ButtonResetSpielfeld = new JButton();
  
  //Spielername-Eingabefeld
  private JTextField jTextField1 = new JTextField();
  private JButton ButtonSpielernameOK = new JButton(); 
  
  //MFeld = Minenfeld	            Testspielfeld 4x4
  //4x4-Raster   1   2   3  4       -  -  1  M
  //             5   6   7  8  <=>  1  2  3  2
  //             9   10  11 12      1  M  M  1
  //             13  14  15 16      1  2  2  1
  private JButton MFeld1 = new JButton();
  private JButton MFeld2 = new JButton();
  private JButton MFeld3 = new JButton();
  private JButton MFeld4 = new JButton();  // Mine
  private JButton MFeld5 = new JButton();
  private JButton MFeld6 = new JButton();
  private JButton MFeld7 = new JButton(); 
  private JButton MFeld8 = new JButton(); 
  private JButton MFeld9 = new JButton(); 
  private JButton MFeld10 = new JButton();  // Mine 
  private JButton MFeld11 = new JButton();  // Mine
  private JButton MFeld12 = new JButton(); 
  private JButton MFeld13 = new JButton(); 
  private JButton MFeld14 = new JButton(); 
  private JButton MFeld15 = new JButton(); 
  private JButton MFeld16 = new JButton(); 
  // Ende Attribute
  
  
  
  public TestAlpha() { 
    // Frame-Initialisierung
    super();
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 330; 
    int frameHeight = 300;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setTitle("GameApp-Name actually not set");
    setResizable(false);
    Container cp = getContentPane();
    cp.setLayout(null);
    // Anfang Komponenten
    
    //Oberflaeche
    ButtonResetSpielfeld.setBounds(10, 200, 100, 30);
    ButtonResetSpielfeld.setText("Reset Spiel");
    ButtonResetSpielfeld.setMargin(new Insets(2, 2, 2, 2));
    
    //Spielername-Eingabe
    jTextField1.setBounds(170, 10, 100, 30);
    cp.add(jTextField1);

    ButtonSpielernameOK.setBounds(280, 10, 30, 30);
    ButtonSpielernameOK.setText("Go");
    ButtonSpielernameOK.setMargin(new Insets(2, 2, 2, 2));
    ButtonSpielernameOK.setEnabled(true); 
    
    ButtonSpielNeustart.setBounds(120, 200, 100, 30);
    ButtonSpielNeustart.setText("Neustarten");
    ButtonSpielNeustart.setMargin(new Insets(2, 2, 2, 2));
    ButtonSpielNeustart.setEnabled(false);

    
    //Spielfeld
    //MFeld - erste Reihe
    MFeld1.setBounds(10, 10, 30, 30);
    MFeld1.setText(".");
    MFeld1.setMargin(new Insets(2, 2, 2, 2));
    MFeld1.setEnabled(false);
    MFeld2.setBounds(50, 10, 30, 30);
    MFeld2.setText(".");
    MFeld2.setMargin(new Insets(2, 2, 2, 2));
    MFeld2.setEnabled(false);
    MFeld3.setBounds(90, 10, 30, 30);
    MFeld3.setText(".");
    MFeld3.setMargin(new Insets(2, 2, 2, 2));
    MFeld3.setEnabled(false);
    MFeld4.setBounds(130, 10, 30, 30);
    MFeld4.setText(".");
    MFeld4.setMargin(new Insets(2, 2, 2, 2));
    MFeld4.setEnabled(false);
    //zweite Reihe
    MFeld5.setBounds(10, 50, 30, 30);
    MFeld5.setText(".");
    MFeld5.setMargin(new Insets(2, 2, 2, 2));
    MFeld5.setEnabled(false);
    MFeld6.setBounds(50, 50, 30, 30);
    MFeld6.setText(".");
    MFeld6.setMargin(new Insets(2, 2, 2, 2));
    MFeld6.setEnabled(false);
    MFeld7.setBounds(90, 50, 30, 30);
    MFeld7.setText(".");
    MFeld7.setMargin(new Insets(2, 2, 2, 2));
    MFeld7.setEnabled(false);
    MFeld8.setBounds(130, 50, 30, 30);
    MFeld8.setText(".");
    MFeld8.setMargin(new Insets(2, 2, 2, 2));
    MFeld8.setEnabled(false);
    //dritte Reihe
    MFeld9.setBounds(10, 90, 30, 30);
    MFeld9.setText(".");
    MFeld9.setMargin(new Insets(2, 2, 2, 2));
    MFeld9.setEnabled(false);
    MFeld10.setBounds(50, 90, 30, 30);
    MFeld10.setText(".");
    MFeld10.setMargin(new Insets(2, 2, 2, 2));
    MFeld10.setEnabled(false);
    MFeld11.setBounds(90, 90, 30, 30);
    MFeld11.setText(".");
    MFeld11.setMargin(new Insets(2, 2, 2, 2));
    MFeld11.setEnabled(false);
    MFeld12.setBounds(130, 90, 30, 30);
    MFeld12.setText(".");
    MFeld12.setMargin(new Insets(2, 2, 2, 2));
    MFeld12.setEnabled(false);
    //vierte Reihe
    MFeld13.setBounds(10, 130, 30, 30);
    MFeld13.setText(".");
    MFeld13.setMargin(new Insets(2, 2, 2, 2));
    MFeld13.setEnabled(false);
    MFeld14.setBounds(50, 130, 30, 30);
    MFeld14.setText(".");
    MFeld14.setMargin(new Insets(2, 2, 2, 2));
    MFeld14.setEnabled(false);
    MFeld15.setBounds(90, 130, 30, 30);
    MFeld15.setText(".");
    MFeld15.setMargin(new Insets(2, 2, 2, 2));
    MFeld15.setEnabled(false);
    MFeld16.setBounds(130, 130, 30, 30);
    MFeld16.setText(".");
    MFeld16.setMargin(new Insets(2, 2, 2, 2));
    MFeld16.setEnabled(false);
    
    RestMinen.setBounds(170, 50, 100, 30);
    RestMinen.setVisible(true);
    RestMinen.setText("Minen zähler "+Integer.toString(minen));
    cp.add(RestMinen);
    
    MinenRichtig.setBounds(170, 80, 100, 30);
    MinenRichtig.setVisible(true);
    MinenRichtig.setText("Mine Richtig "+Integer.toString(minerichtig));
    cp.add(MinenRichtig);
    
    jLabel1.setBounds(170, 10, 100, 30);
    jLabel1.setVisible(false);
    cp.add(jLabel1);
    
    Spielfeldaufdecken.setBounds(170, 150, 100, 30);
    Spielfeldaufdecken.setVisible(true);
    Spielfeldaufdecken.setText(Integer.toString(gewonnen));
    cp.add(Spielfeldaufdecken);
    
       
    
    ButtonSpielNeustart.addActionListener(new ActionListener() { 
        public void actionPerformed(ActionEvent evt) { 
            ButtonSpielNeustart_ActionPerformed(evt);
          }
        });
    
    //Methode zum Button-klick-ausfuehren
    ButtonResetSpielfeld.addActionListener(new ActionListener() { 
        public void actionPerformed(ActionEvent evt) { 
          ButtonResetSpielfeld_ActionPerformed(evt);
        }
      });
    
    ButtonSpielernameOK.addActionListener(new ActionListener() { 
        public void actionPerformed(ActionEvent evt) { 
          ButtonSpielernameOK_ActionPerformed(evt);
        }
      });
    
    
    //Rechts-Links auf Spielfeld
    MFeld1.addMouseListener(new MouseAdapter() {
        public void mousePressed(MouseEvent e) {
           	if (Spielfeldgesperrt==false) {
        		if (e.getButton() == MouseEvent.BUTTON1) {
        			MFeld1.setText(a_Spielfeld[1]);
        			MFeld1.setEnabled(false);
        			if (Spielfeldgeklickt[1]==2) {
        				minendemarkiert();
        			}
        			if (MFeld1.getText()==("M")) {
        				mine();
        			}
        		}
        		if (e.getButton() == MouseEvent.BUTTON3) {
        			if (MFeld1.getText()!="*") {
        				MFeld1.setText("*");
        				if (Spielfeldgeklickt[1]!=2) {
        					if (a_Spielfeld[1]==("M")) {
        						minerichtig=minerichtig+1;
        					}
        					minenmarkiert();
        				}
        				Spielfeldgeklickt[1]=2;
        			}
        			else {
        				MFeld1.setText(".");
    					if (a_Spielfeld[1]==("M")) {
    						minerichtig=minerichtig-1;
    					}
        				minendemarkiert();
        				Spielfeldgeklickt[1]=0;
        			}
        		}
        	}
        	sieg();        	
        } // end-mousePressed       
    }); // end-addMouseListener  	

    /*
        			
        			if (Spielfeldgeklickt[1]==0) {
        				Spielfeldgeklickt[1]=1;
        				if (MFeld1.getText()=="*") {
        					MFeld1.setText(".");
        					minen=minen+2;
        					RestMinen.setText(Integer.toString(minen));
        				}
        				else {	
        					MFeld1.setText("*");
        					minenmarkiert();
        					if (a_Spielfeld[1]==("M")) {
        						minerichtig=minerichtig+1;
        					}
        				}
        			sieg();
        		}
        		}
        	}
        } }
        	// end-mousePressed       
      }); // end-addMouseListener
      */
    
    MFeld2.addMouseListener(new MouseAdapter() {
        public void mousePressed(MouseEvent e) {
        	if (e.getButton() == MouseEvent.BUTTON1) {
        		if (Spielfeldgesperrt==false) {
        			MFeld2.setText("-");
        			if (Spielfeldgeklickt[2]==0) {
        	        	Spielfeldgeklickt[2]=1;
        				//gewonnen=gewonnen-1;
        				sieg();
        				}
        			}
        		}
        	if (e.getButton() == MouseEvent.BUTTON3) {
        		if (Spielfeldgesperrt==false) {
        			if (MFeld2.getText()=="*") {
        				MFeld2.setText(".");
        				minen=minen+2;
        				RestMinen.setText(Integer.toString(minen));
        			}
        			else	
        			MFeld2.setText("*");
        			minenmarkiert();
        			if (a_Spielfeld[2]==("M")) {
        				minerichtig=minerichtig+1;
        				sieg();
        			}
        			}
        		}
        } // end-mousePressed       
      }); // end-addMouseListener
    
    MFeld3.addMouseListener(new MouseAdapter() {
        public void mousePressed(MouseEvent e) {
        	if (e.getButton() == MouseEvent.BUTTON1) {
        		if (Spielfeldgesperrt==false) {
        			MFeld3.setText("1");
        			if (Spielfeldgeklickt[3]==0) {
        	        	Spielfeldgeklickt[3]=1;
        				//gewonnen=gewonnen-1;
        				sieg();
        				}
        			}
        		}
        	if (e.getButton() == MouseEvent.BUTTON3) {
        		if (Spielfeldgesperrt==false) {
        			if (MFeld3.getText()=="*") {
        				MFeld3.setText(".");
        				minen=minen+2;
        				RestMinen.setText(Integer.toString(minen));
        			}
        			else	
        			MFeld3.setText("*");
        			minenmarkiert();
        			if (a_Spielfeld[3]==("M")) {
        				minerichtig=minerichtig+1;
        				sieg();
        			}
        			}
        		}
        } // end-mousePressed       
      }); // end-addMouseListener
    
    MFeld4.addMouseListener(new MouseAdapter() {
        public void mousePressed(MouseEvent e) {
        	if (e.getButton() == MouseEvent.BUTTON1) {
        		if (Spielfeldgesperrt==false) {
        			MFeld4.setText("M");
        			mine();
        			Spielfeldgeklickt[4]=1;
        			if (Spielfeldgeklickt[4]==0) {
        	        	Spielfeldgeklickt[4]=1;
        				//gewonnen=gewonnen-1;
        				sieg();
        				}
        			}
        		}
        	if (e.getButton() == MouseEvent.BUTTON3) {
        		if (Spielfeldgesperrt==false) {
        			if (MFeld4.getText()=="*") {
        				MFeld4.setText(".");
        				minen=minen+2;
        				RestMinen.setText(Integer.toString(minen));
        			}
        			else	
        			MFeld4.setText("*");
        			minenmarkiert();
        			if (a_Spielfeld[4]==("M")) {
        				minerichtig=minerichtig+1;
        				//sieg();
        			}
        			}
        		sieg();
        		}
        } // end-mousePressed       
      }); // end-addMouseListener
    
    MFeld5.addMouseListener(new MouseAdapter() {
        public void mousePressed(MouseEvent e) {
        	if (e.getButton() == MouseEvent.BUTTON1) {
        		if (Spielfeldgesperrt==false) {
        			MFeld5.setText("1");
        			if (Spielfeldgeklickt[5]==0) {
        	        	Spielfeldgeklickt[5]=1;
        				//gewonnen=gewonnen-1;
        				sieg();
        				}
        			}
        		}
        	if (e.getButton() == MouseEvent.BUTTON3) {
        		if (Spielfeldgesperrt==false) {
        			if (MFeld5.getText()=="*") {
        				MFeld5.setText(".");
        				minen=minen+2;
        				RestMinen.setText(Integer.toString(minen));
        			}
        			else	
        			MFeld5.setText("*");
        			minenmarkiert();
        			if (a_Spielfeld[5]==("M")) {
        				minerichtig=minerichtig+1;
        				sieg();
        			}
        			}
        		}
        } // end-mousePressed       
      }); // end-addMouseListener
    
    MFeld6.addMouseListener(new MouseAdapter() {
        public void mousePressed(MouseEvent e) {
        	if (e.getButton() == MouseEvent.BUTTON1) {
        		if (Spielfeldgesperrt==false) {
        			MFeld6.setText("2");
        			if (Spielfeldgeklickt[6]==0) {
        				Spielfeldgeklickt[6]=1;
        				//gewonnen=gewonnen-1;
        				sieg();
        				}
        			}
        		}
        	if (e.getButton() == MouseEvent.BUTTON3) {
        		if (Spielfeldgesperrt==false) {
        			if (MFeld6.getText()=="*") {
        				MFeld6.setText(".");
        				minen=minen+2;
        				RestMinen.setText(Integer.toString(minen));
        			}
        			else	
        			MFeld6.setText("*");
        			minenmarkiert();
        			if (a_Spielfeld[6]==("M")) {
        				minerichtig=minerichtig+1;
        				sieg();
        			}
        			}
        		}
        } // end-mousePressed       
      }); // end-addMouseListener
    
    MFeld7.addMouseListener(new MouseAdapter() {
        public void mousePressed(MouseEvent e) {
        	if (e.getButton() == MouseEvent.BUTTON1) {
        		if (Spielfeldgesperrt==false) {
        			MFeld7.setText("3");
        			if (Spielfeldgeklickt[7]==0) {
        				Spielfeldgeklickt[7]=1;
        				//gewonnen=gewonnen-1;
        				sieg();
        				}
        			}
        		}
        	if (e.getButton() == MouseEvent.BUTTON3) {
        		if (Spielfeldgesperrt==false) {
        			if (MFeld7.getText()=="*") {
        				MFeld7.setText(".");
        				minen=minen+2;
        				RestMinen.setText(Integer.toString(minen));
        			}
        			else	
        			MFeld7.setText("*");
        			minenmarkiert();
        			if (a_Spielfeld[7]==("M")) {
        				minerichtig=minerichtig+1;
        				sieg();
        			}
        			}
        		}
        } // end-mousePressed       
      }); // end-addMouseListener
    
    MFeld8.addMouseListener(new MouseAdapter() {
        public void mousePressed(MouseEvent e) {
        	if (e.getButton() == MouseEvent.BUTTON1) {
        		if (Spielfeldgesperrt==false) {
        			MFeld8.setText("2");
        			if (Spielfeldgeklickt[8]==0) {
        	        	Spielfeldgeklickt[8]=1;
        				//gewonnen=gewonnen-1;
        				sieg();
        				}
        			}
        		}
        	if (e.getButton() == MouseEvent.BUTTON3) {
        		if (Spielfeldgesperrt==false) {
        			if (MFeld8.getText()=="*") {
        				MFeld8.setText(".");
        				minen=minen+2;
        				RestMinen.setText(Integer.toString(minen));
        			}
        			else	
        			MFeld8.setText("*");
        			minenmarkiert();
        			if (a_Spielfeld[8]==("M")) {
        				minerichtig=minerichtig+1;
        				sieg();
        			}
        			}
        		}
        } // end-mousePressed       
      }); // end-addMouseListener
    
    MFeld9.addMouseListener(new MouseAdapter() {
        public void mousePressed(MouseEvent e) {
        	if (e.getButton() == MouseEvent.BUTTON1) {
        		if (Spielfeldgesperrt==false) {
        			MFeld9.setText("1");
        			if (Spielfeldgeklickt[9]==0) {
        	        	Spielfeldgeklickt[9]=1;
        				//gewonnen=gewonnen-1;
        				sieg();
        				}
        			}
        		}
        	if (e.getButton() == MouseEvent.BUTTON3) {
        		if (Spielfeldgesperrt==false) {
        			if (MFeld9.getText()=="*") {
        				MFeld9.setText(".");
        				minen=minen+2;
        				RestMinen.setText(Integer.toString(minen));
        			}
        			else	
        			MFeld9.setText("*");
        			minenmarkiert();
        			if (a_Spielfeld[9]==("M")) {
        				minerichtig=minerichtig+1;
        				sieg();
        			}
        			}
        		}
        } // end-mousePressed       
      }); // end-addMouseListener
    
    MFeld10.addMouseListener(new MouseAdapter() {
        public void mousePressed(MouseEvent e) {
        	if (e.getButton() == MouseEvent.BUTTON1) {
        		if (Spielfeldgesperrt==false) {
        			MFeld10.setText("M");
        			mine();
        			Spielfeldgeklickt[10]=1;
        			if (Spielfeldgeklickt[10]==0) {
        	        	Spielfeldgeklickt[10]=1;
        				//gewonnen=gewonnen-1;
        				sieg();
        				}
        			}
        		}
        	if (e.getButton() == MouseEvent.BUTTON3) {
        		if (Spielfeldgesperrt==false) {
        			if (MFeld10.getText()=="*") {
        				MFeld10.setText(".");
        				minen=minen+2;
        				RestMinen.setText(Integer.toString(minen));
        			}
        			else	
        			MFeld10.setText("*");
        			minenmarkiert();
        			if (a_Spielfeld[10]==("M")) {
        				minerichtig=minerichtig+1;
        				sieg();
        			}
        			}
        		}
        } // end-mousePressed       
      }); // end-addMouseListener
    
    MFeld11.addMouseListener(new MouseAdapter() {
        public void mousePressed(MouseEvent e) {
        	if (e.getButton() == MouseEvent.BUTTON1) {
        		if (Spielfeldgesperrt==false) {
        			MFeld11.setText("M");
        			mine();
        			Spielfeldgeklickt[11]=1;
        			if (Spielfeldgeklickt[11]==0) {
        	        	Spielfeldgeklickt[11]=1;
        				//gewonnen=gewonnen-1;
        				sieg();
        				}
        			}
        		}
        	if (e.getButton() == MouseEvent.BUTTON3) {
        		if (Spielfeldgesperrt==false) {
        			if (MFeld11.getText()=="*") {
        				MFeld11.setText(".");
        				minen=minen+2;
        				RestMinen.setText(Integer.toString(minen));
        			}
        			else	
        			MFeld11.setText("*");
        			minenmarkiert();
        			if (a_Spielfeld[11]==("M")) {
        				minerichtig=minerichtig+1;
        				sieg();
        			}
        			}
        		}
        } // end-mousePressed       
      }); // end-addMouseListener
    
    MFeld12.addMouseListener(new MouseAdapter() {
        public void mousePressed(MouseEvent e) {
        	if (e.getButton() == MouseEvent.BUTTON1) {
        		if (Spielfeldgesperrt==false) {
        			MFeld12.setText("1");
        			if (Spielfeldgeklickt[12]==0) {
        	        	Spielfeldgeklickt[12]=1;
        				//gewonnen=gewonnen-1;
        				sieg();
        				}
        			}
        		}
        	if (e.getButton() == MouseEvent.BUTTON3) {
        		if (Spielfeldgesperrt==false) {
        			if (MFeld12.getText()=="*") {
        				MFeld12.setText(".");
        				minen=minen+2;
        				RestMinen.setText(Integer.toString(minen));
        			}
        			else	
        			MFeld12.setText("*");
        			minenmarkiert();
        			if (a_Spielfeld[12]==("M")) {
        				minerichtig=minerichtig+1;
        				sieg();
        			}
        			}
        		}
        } // end-mousePressed       
      }); // end-addMouseListener
    
    MFeld13.addMouseListener(new MouseAdapter() {
        public void mousePressed(MouseEvent e) {
        	if (e.getButton() == MouseEvent.BUTTON1) {
        		if (Spielfeldgesperrt==false) {
        			MFeld13.setText("1");
        			if (Spielfeldgeklickt[13]==0) {
        	        	Spielfeldgeklickt[13]=1;
        				//gewonnen=gewonnen-1;
        				sieg();
        				}
        			}
        		}
        	if (e.getButton() == MouseEvent.BUTTON3) {
        		if (Spielfeldgesperrt==false) {
        			if (MFeld13.getText()=="*") {
        				MFeld13.setText(".");
        				minen=minen+2;
        				RestMinen.setText(Integer.toString(minen));
        			}
        			else	
        			MFeld13.setText("*");
        			minenmarkiert();
        			if (a_Spielfeld[13]==("M")) {
        				minerichtig=minerichtig+1;
        				sieg();
        			}
        			}
        		}
        } // end-mousePressed       
      }); // end-addMouseListener
    
    MFeld14.addMouseListener(new MouseAdapter() {
        public void mousePressed(MouseEvent e) {
        	if (e.getButton() == MouseEvent.BUTTON1) {
        		if (Spielfeldgesperrt==false) {
        			MFeld14.setText("2");
        			if (Spielfeldgeklickt[14]==0) {
        	        	Spielfeldgeklickt[14]=1;
        				//gewonnen=gewonnen-1;
        				sieg();
        				}
        			}
        		}
        	if (e.getButton() == MouseEvent.BUTTON3) {
        		if (Spielfeldgesperrt==false) {
        			if (MFeld14.getText()=="*") {
        				MFeld14.setText(".");
        				minen=minen+2;
        				RestMinen.setText(Integer.toString(minen));
        			}
        			else	
        			MFeld14.setText("*");
        			minenmarkiert();
        			if (a_Spielfeld[14]==("M")) {
        				minerichtig=minerichtig+1;
        				sieg();
        			}
        			}
        		}
        } // end-mousePressed       
      }); // end-addMouseListener
    
    MFeld15.addMouseListener(new MouseAdapter() {
        public void mousePressed(MouseEvent e) {
        	if (e.getButton() == MouseEvent.BUTTON1) {
        		if (Spielfeldgesperrt==false) {
        			MFeld15.setText("2");
        			if (Spielfeldgeklickt[15]==0) {
        	        	Spielfeldgeklickt[15]=1;
        				//gewonnen=gewonnen-1;
        				sieg();
        				}
        			}
        		}
        	if (e.getButton() == MouseEvent.BUTTON3) {
        		if (Spielfeldgesperrt==false) {
        			if (MFeld15.getText()=="*") {
        				MFeld15.setText(".");
        				minen=minen+2;
        				RestMinen.setText(Integer.toString(minen));
        			}
        			else	
        			MFeld15.setText("*");
        			minenmarkiert();
        			if (a_Spielfeld[15]==("M")) {
        				minerichtig=minerichtig+1;
        				sieg();
        			}
        			}
        		}
        } // end-mousePressed       
      }); // end-addMouseListener
    
    MFeld16.addMouseListener(new MouseAdapter() {
        public void mousePressed(MouseEvent e) {
        	if (e.getButton() == MouseEvent.BUTTON1) {
        		if (Spielfeldgesperrt==false) {
        			MFeld16.setText("1");
        			if (Spielfeldgeklickt[16]==0) {
        	        	Spielfeldgeklickt[16]=1;
        				//gewonnen=gewonnen-1;
        				sieg();
        				}
        			}
        		}
        	if (e.getButton() == MouseEvent.BUTTON3) {
        		if (Spielfeldgesperrt==false) {
        			if (MFeld16.getText()=="*") {
        				MFeld16.setText(".");
        				minen=minen+2;
        				RestMinen.setText(Integer.toString(minen));
        			}
        			else	
        			MFeld16.setText("*");
        			minenmarkiert();
        			if (a_Spielfeld[16]==("M")) {
        				minerichtig=minerichtig+1;
        				sieg();
        			}
        			}
        		}
        } // end-mousePressed       
      }); // end-addMouseListener
    
    
       
    //Komponenten erzeugen
    cp.add(ButtonSpielNeustart);
    cp.add(ButtonResetSpielfeld);
    cp.add(ButtonSpielernameOK);
    
    cp.add(MFeld1);
    cp.add(MFeld2);
    cp.add(MFeld3);
    cp.add(MFeld4);
    cp.add(MFeld5);
    cp.add(MFeld6);
    cp.add(MFeld7);
    cp.add(MFeld8);
    cp.add(MFeld9);
    cp.add(MFeld10);
    cp.add(MFeld11);
    cp.add(MFeld12);
    cp.add(MFeld13);
    cp.add(MFeld14);
    cp.add(MFeld15);
    cp.add(MFeld16);
    // Ende Komponenten
    
    setVisible(true);
  } // end of public dqw
  
  // Anfang Methoden
  

  public static void main(String[] args) {
    new TestAlpha();


  } // end of main
  

  
  public void ButtonSpielNeustart_ActionPerformed(ActionEvent evt) {
	  // Spielfeld Oberflaeche zu beginn
	    MFeld1.setText(".");
	    MFeld2.setText(".");
	    MFeld3.setText(".");
	    MFeld4.setText(".");
	    MFeld5.setText(".");
	    MFeld6.setText(".");
	    MFeld7.setText(".");
	    MFeld8.setText(".");
	    MFeld9.setText(".");
	    MFeld10.setText(".");
	    MFeld11.setText(".");
	    MFeld12.setText(".");
	    MFeld13.setText(".");
	    MFeld14.setText(".");
	    MFeld15.setText(".");
	    MFeld16.setText(".");
	    
	    //Spielfeld aktivieren
	    MFeld1.setEnabled(true);
	    MFeld2.setEnabled(true);
	    MFeld3.setEnabled(true);
	    MFeld4.setEnabled(true);
	    MFeld5.setEnabled(true);
	    MFeld6.setEnabled(true);
	    MFeld7.setEnabled(true);
	    MFeld8.setEnabled(true);
	    MFeld9.setEnabled(true);
	    MFeld10.setEnabled(true);
	    MFeld11.setEnabled(true);
	    MFeld12.setEnabled(true);
	    MFeld13.setEnabled(true);
	    MFeld14.setEnabled(true);
	    MFeld15.setEnabled(true);
	    MFeld16.setEnabled(true);
	    
	    Spielfeldgesperrt=false;
	    //Spielfeldgeklickt Array zurücksetzen
	    for (int i=0; i<17; i++) {
	    	Spielfeldgeklickt[i]=0;
	    }
	  
	    gewonnen=16;
  } // end of jButton1_ActionPerformed
  
  public void ButtonSpielernameOK_ActionPerformed(ActionEvent evt) {
	    /* !!! Noch zu implementieren
	     * 		"Go"-Button erst aktiviert / klickbar, wenn mindestens 3 Zeichen eingegeben sind
	     */
	  	  
	    // Spielfeld aktivieren
	    MFeld1.setEnabled(true);
	    MFeld2.setEnabled(true);
	    MFeld3.setEnabled(true);
	    MFeld4.setEnabled(true);
	    MFeld5.setEnabled(true);
	    MFeld6.setEnabled(true);
	    MFeld7.setEnabled(true);
	    MFeld8.setEnabled(true);
	    MFeld9.setEnabled(true);
	    MFeld10.setEnabled(true);
	    MFeld11.setEnabled(true);
	    MFeld12.setEnabled(true);
	    MFeld13.setEnabled(true);
	    MFeld14.setEnabled(true);
	    MFeld15.setEnabled(true);
	    MFeld16.setEnabled(true);
	    
	    // Name einlesen
	    Spielername=jTextField1.getText();
	    jTextField1.setVisible(false);
	    jLabel1.setText(Spielername);
	    jLabel1.setVisible(true);
	    
	    ButtonSpielNeustart.setEnabled(true);
	    Spielfeldgesperrt=false;
	    	    
	    //Button zum Schluss deaktivieren
	    ButtonSpielernameOK.setEnabled(false);
	  
	  } // end of jButton1_ActionPerformed  
  
  public void ButtonResetSpielfeld_ActionPerformed(ActionEvent evt) {
	    // Spielfeld Oberflaeche zu beginn
	    MFeld1.setText(".");
	    MFeld2.setText(".");
	    MFeld3.setText(".");
	    MFeld4.setText(".");
	    MFeld5.setText(".");
	    MFeld6.setText(".");
	    MFeld7.setText(".");
	    MFeld8.setText(".");
	    MFeld9.setText(".");
	    MFeld10.setText(".");
	    MFeld11.setText(".");
	    MFeld12.setText(".");
	    MFeld13.setText(".");
	    MFeld14.setText(".");
	    MFeld15.setText(".");
	    MFeld16.setText(".");
	    
	    // Spielfeld aktivieren
	    MFeld1.setEnabled(false);
	    MFeld2.setEnabled(false);
	    MFeld3.setEnabled(false);
	    MFeld4.setEnabled(false);
	    MFeld5.setEnabled(false);
	    MFeld6.setEnabled(false);
	    MFeld7.setEnabled(false);
	    MFeld8.setEnabled(false);
	    MFeld9.setEnabled(false);
	    MFeld10.setEnabled(false);
	    MFeld11.setEnabled(false);
	    MFeld12.setEnabled(false);
	    MFeld13.setEnabled(false);
	    MFeld14.setEnabled(false);
	    MFeld15.setEnabled(false);
	    MFeld16.setEnabled(false);
	    
	    Spielfeldgesperrt=true;
	    //Spielfeldgeklickt Array zurücksetzen
	    for (int i=0; i<17; i++) {
	    	Spielfeldgeklickt[i]=0;
	    }
	    
	    
	    //Spielername zurücksetze, Label weg, Eingabe da
	    Spielername="none";
	    jTextField1.setVisible(true);
	    jTextField1.setText("");
	    jLabel1.setVisible(false);
	    ButtonSpielernameOK.setEnabled(true);
	    ButtonSpielNeustart.setEnabled(false);
	    
	    gewonnen=16;
	  } // end of jButton1_ActionPerformed
  
    
  
  public void sieg() {
	    MinenRichtig.setText("Mine Richtig "+Integer.toString(minerichtig));	  
	  if (minerichtig==mine && minen==0) {
	  		 JOptionPane.showMessageDialog(null, "Spiel gewonnen");
	  	     
	  	      //To-Do: Auswahl reset oder nochmal spielen (=neustarten) in der Sieg-Meldung
	  		 
	  		  // Hier jetzt: Spiel gewonnen = Spielfeld deaktiviert. Reset und Neustart können ausgewählt werden.
	 	    MFeld1.setEnabled(false);
		    MFeld2.setEnabled(false);
		    MFeld3.setEnabled(false);
		    MFeld4.setEnabled(false);
		    MFeld5.setEnabled(false);
		    MFeld6.setEnabled(false);
		    MFeld7.setEnabled(false);
		    MFeld8.setEnabled(false);
		    MFeld9.setEnabled(false);
		    MFeld10.setEnabled(false);
		    MFeld11.setEnabled(false);
		    MFeld12.setEnabled(false);
		    MFeld13.setEnabled(false);
		    MFeld14.setEnabled(false);
		    MFeld15.setEnabled(false);
		    MFeld16.setEnabled(false);
	  	  }
	  	  
  }
  
  public void mine() {
	    JOptionPane.showMessageDialog(null, "Mine! Verloren");
	    MFeld1.setEnabled(false);
	    MFeld2.setEnabled(false);
	    MFeld3.setEnabled(false);
	    MFeld4.setEnabled(false);
	    MFeld5.setEnabled(false);
	    MFeld6.setEnabled(false);
	    MFeld7.setEnabled(false);
	    MFeld8.setEnabled(false);
	    MFeld9.setEnabled(false);
	    MFeld10.setEnabled(false);
	    MFeld11.setEnabled(false);
	    MFeld12.setEnabled(false);
	    MFeld13.setEnabled(false);
	    MFeld14.setEnabled(false);
	    MFeld15.setEnabled(false);
	    MFeld16.setEnabled(false);
	  	}

  public void minenmarkiert() {
	  minen=minen-1;
	  RestMinen.setText("Minen zähler "+Integer.toString(minen));
  }
  public void minendemarkiert() {
	  minen=minen+1;
	  RestMinen.setText("Minen zähler "+Integer.toString(minen));
  }
  // Ende Methoden
} // end of class dqw


