
package bla;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;


/**
  * Spielprojekt "Seawolf"
  * 	GameApp-Name <not set/actually nameless>
  *
  * @version A.1.3 vom 11.03.2017
  * @author XKonne
  * @author p0sE-Git
  * 
  */


public class test extends JFrame {
  // Anfang Attribute
  private JLabel jLabel1 = new JLabel();
  private JButton jButton1 = new JButton();
  
  //Spiel-Oberflaeche
  private JButton ButtonResetSpielfeld = new JButton();
  
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
  
  public test() { 
    // Frame-Initialisierung
    super();
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 300; 
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

    //Spielfeld
    //MFeld - erste Reihe
    MFeld1.setBounds(10, 10, 30, 30);
    MFeld1.setText(".");
    MFeld1.setMargin(new Insets(2, 2, 2, 2));
    MFeld2.setBounds(50, 10, 30, 30);
    MFeld2.setText(".");
    MFeld2.setMargin(new Insets(2, 2, 2, 2));
    MFeld3.setBounds(90, 10, 30, 30);
    MFeld3.setText(".");
    MFeld3.setMargin(new Insets(2, 2, 2, 2));
    MFeld4.setBounds(130, 10, 30, 30);
    MFeld4.setText(".");
    MFeld4.setMargin(new Insets(2, 2, 2, 2));
    //zweite Reihe
    MFeld5.setBounds(10, 50, 30, 30);
    MFeld5.setText(".");
    MFeld5.setMargin(new Insets(2, 2, 2, 2));
    MFeld6.setBounds(50, 50, 30, 30);
    MFeld6.setText(".");
    MFeld6.setMargin(new Insets(2, 2, 2, 2));
    MFeld7.setBounds(90, 50, 30, 30);
    MFeld7.setText(".");
    MFeld7.setMargin(new Insets(2, 2, 2, 2));
    MFeld8.setBounds(130, 50, 30, 30);
    MFeld8.setText(".");
    MFeld8.setMargin(new Insets(2, 2, 2, 2));
    //dritte Reihe
    MFeld9.setBounds(10, 90, 30, 30);
    MFeld9.setText(".");
    MFeld9.setMargin(new Insets(2, 2, 2, 2));
    MFeld10.setBounds(50, 90, 30, 30);
    MFeld10.setText(".");
    MFeld10.setMargin(new Insets(2, 2, 2, 2));
    MFeld11.setBounds(90, 90, 30, 30);
    MFeld11.setText(".");
    MFeld11.setMargin(new Insets(2, 2, 2, 2));
    MFeld12.setBounds(130, 90, 30, 30);
    MFeld12.setText(".");
    MFeld12.setMargin(new Insets(2, 2, 2, 2));
    //vierte Reihe
    MFeld13.setBounds(10, 130, 30, 30);
    MFeld13.setText(".");
    MFeld13.setMargin(new Insets(2, 2, 2, 2));
    MFeld14.setBounds(50, 130, 30, 30);
    MFeld14.setText(".");
    MFeld14.setMargin(new Insets(2, 2, 2, 2));
    MFeld15.setBounds(90, 130, 30, 30);
    MFeld15.setText(".");
    MFeld15.setMargin(new Insets(2, 2, 2, 2));
    MFeld16.setBounds(130, 130, 30, 30);
    MFeld16.setText(".");
    MFeld16.setMargin(new Insets(2, 2, 2, 2));
    
    jLabel1.setBounds(192, 112, 110, 20);
    jLabel1.setText("text");
    cp.add(jLabel1);
    jButton1.setBounds(200, 90, 75, 25);
    jButton1.setText("jButton1");
    jButton1.setMargin(new Insets(2, 2, 2, 2));
    jButton1.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        jButton1_ActionPerformed(evt);
      }
    });
    
    //Methode zum Button-klick-ausfuehren
    ButtonResetSpielfeld.addActionListener(new ActionListener() { 
        public void actionPerformed(ActionEvent evt) { 
          ButtonResetSpielfeld_ActionPerformed(evt);
        }
      });
    
    
    
    MFeld1.addActionListener(new ActionListener() { 
        public void actionPerformed(ActionEvent evt) { 
        	MFeld1_ActionPerformed(evt);
        }
      });
    MFeld2.addActionListener(new ActionListener() { 
        public void actionPerformed(ActionEvent evt) { 
        	MFeld2_ActionPerformed(evt);
        }
      });
    MFeld3.addActionListener(new ActionListener() { 
        public void actionPerformed(ActionEvent evt) { 
        	MFeld3_ActionPerformed(evt);
        }
      });
    MFeld4.addActionListener(new ActionListener() { 
        public void actionPerformed(ActionEvent evt) { 
        	MFeld4_ActionPerformed(evt);
        }
      });
    MFeld5.addActionListener(new ActionListener() { 
        public void actionPerformed(ActionEvent evt) { 
        	MFeld5_ActionPerformed(evt);
        }
      });
    MFeld6.addActionListener(new ActionListener() { 
        public void actionPerformed(ActionEvent evt) { 
        	MFeld6_ActionPerformed(evt);
        }
      });
    MFeld7.addActionListener(new ActionListener() { 
        public void actionPerformed(ActionEvent evt) { 
        	MFeld7_ActionPerformed(evt);
        }
      });
    MFeld8.addActionListener(new ActionListener() { 
        public void actionPerformed(ActionEvent evt) { 
        	MFeld8_ActionPerformed(evt);
        }
      });
    MFeld9.addActionListener(new ActionListener() { 
        public void actionPerformed(ActionEvent evt) { 
        	MFeld9_ActionPerformed(evt);
        }
      });
    MFeld10.addActionListener(new ActionListener() { 
        public void actionPerformed(ActionEvent evt) { 
        	MFeld10_ActionPerformed(evt);
        }
      });
    MFeld11.addActionListener(new ActionListener() { 
        public void actionPerformed(ActionEvent evt) { 
        	MFeld11_ActionPerformed(evt);
        }
      });
    MFeld12.addActionListener(new ActionListener() { 
        public void actionPerformed(ActionEvent evt) { 
        	MFeld12_ActionPerformed(evt);
        }
      });
    MFeld13.addActionListener(new ActionListener() { 
        public void actionPerformed(ActionEvent evt) { 
        	MFeld13_ActionPerformed(evt);
        }
      });
    MFeld14.addActionListener(new ActionListener() { 
        public void actionPerformed(ActionEvent evt) { 
        	MFeld14_ActionPerformed(evt);
        }
      });
    MFeld15.addActionListener(new ActionListener() { 
        public void actionPerformed(ActionEvent evt) { 
        	MFeld15_ActionPerformed(evt);
        }
      });
    MFeld16.addActionListener(new ActionListener() { 
        public void actionPerformed(ActionEvent evt) { 
        	MFeld16_ActionPerformed(evt);
        }
      });

    
    //Komponenten erzeugen
    cp.add(jButton1);
    cp.add(ButtonResetSpielfeld);
    
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
    new test();
  } // end of main
  
  public void jButton1_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einfügen
    jLabel1.setText("bla");
    jButton1.setText("bla");
  } // end of jButton1_ActionPerformed
  
  
  public void ButtonResetSpielfeld_ActionPerformed(ActionEvent evt) {
	    // TODO hier Quelltext einfügen
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
	  } // end of jButton1_ActionPerformed
  
  

  public void MFeld1_ActionPerformed(ActionEvent evt) {
	    // TODO hier Quelltext einfügen
	    MFeld1.setText("-");
	  } // end of jButton1_ActionPerformed
  
  public void MFeld2_ActionPerformed(ActionEvent evt) {
	    // TODO hier Quelltext einfügen
	    MFeld2.setText("-");
	  } // end of jButton1_ActionPerformed
  
  public void MFeld3_ActionPerformed(ActionEvent evt) {
	    // TODO hier Quelltext einfügen
	    MFeld3.setText("1");
	  } // end of jButton1_ActionPerformed
  
  public void MFeld4_ActionPerformed(ActionEvent evt) {
	    // TODO hier Quelltext einfügen
	    MFeld4.setText("M");
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
	  } // end of jButton1_ActionPerformed
  
    
  
  public void MFeld5_ActionPerformed(ActionEvent evt) {
	    // TODO hier Quelltext einfügen
	    MFeld5.setText("1");
	  } // end of jButton1_ActionPerformed
  
  public void MFeld6_ActionPerformed(ActionEvent evt) {
	    // TODO hier Quelltext einfügen
	    MFeld6.setText("2");
	  } // end of jButton1_ActionPerformed
  
  public void MFeld7_ActionPerformed(ActionEvent evt) {
	    // TODO hier Quelltext einfügen
	    MFeld7.setText("3");
	  } // end of jButton1_ActionPerformed
  
  public void MFeld8_ActionPerformed(ActionEvent evt) {
	    // TODO hier Quelltext einfügen
	    MFeld8.setText("2");
	  } // end of jButton1_ActionPerformed
  
  
  
  public void MFeld9_ActionPerformed(ActionEvent evt) {
	    // TODO hier Quelltext einfügen
	    MFeld9.setText("1");
	  } // end of jButton1_ActionPerformed
  
  public void MFeld10_ActionPerformed(ActionEvent evt) {
	    // TODO hier Quelltext einfügen
	    MFeld10.setText("M");
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
	  } // end of jButton1_ActionPerformed
  
  public void MFeld11_ActionPerformed(ActionEvent evt) {
	    // TODO hier Quelltext einfügen
	    MFeld11.setText("M");
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
	  } // end of jButton1_ActionPerformed
  
  public void MFeld12_ActionPerformed(ActionEvent evt) {
	    // TODO hier Quelltext einfügen
	    MFeld12.setText("1");
	  } // end of jButton1_ActionPerformed
  
  
  
  public void MFeld13_ActionPerformed(ActionEvent evt) {
	    // TODO hier Quelltext einfügen
	    MFeld13.setText("1");
	  } // end of jButton1_ActionPerformed
  
  public void MFeld14_ActionPerformed(ActionEvent evt) {
	    // TODO hier Quelltext einfügen
	    MFeld14.setText("2");
	  } // end of jButton1_ActionPerformed
  
  public void MFeld15_ActionPerformed(ActionEvent evt) {
	    // TODO hier Quelltext einfügen
	    MFeld15.setText("2");
	  } // end of jButton1_ActionPerformed
  
  public void MFeld16_ActionPerformed(ActionEvent evt) {
	    // TODO hier Quelltext einfügen
	    MFeld16.setText("1");
	  } // end of jButton1_ActionPerformed
 
  
  
  
  
  // Ende Methoden
} // end of class dqw

