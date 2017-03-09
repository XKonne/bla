
package bla;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
  * Spielprojekt "Seawolf"
  * 	Programm-Name <not set/actually nameless>
  *
  * @version A.1.1 vom 09.03.2017
  * @author XKonne
  * @author p0sE-Git
  * 
  */


public class test extends JFrame {
  // Anfang Attribute
  private JLabel jLabel1 = new JLabel();
  private JButton jButton1 = new JButton();
  private JButton jButton2 = new JButton();
  
  //MFeld = Minenfeld
  //3x3-Raster   1   2   3
  //             4   5   6
  //             7   8   9
  private JButton MFeld1 = new JButton();
  private JButton MFeld2 = new JButton();
  private JButton MFeld3 = new JButton();
  private JButton MFeld4 = new JButton();
  private JButton MFeld5 = new JButton();
  private JButton MFeld6 = new JButton();
  private JButton MFeld7 = new JButton(); // Mine
  private JButton MFeld8 = new JButton(); 
  private JButton MFeld9 = new JButton(); // Mine
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
    setTitle("dqw");
    setResizable(false);
    Container cp = getContentPane();
    cp.setLayout(null);
    // Anfang Komponenten
    
    //MFeld - erste Reihe
    MFeld1.setBounds(10, 10, 30, 30);
    MFeld1.setText("..");
    MFeld2.setBounds(50, 10, 30, 30);
    MFeld2.setText("..");
    MFeld3.setBounds(90, 10, 30, 30);
    MFeld3.setText("..");
    //zweite Reihe
    MFeld4.setBounds(10, 50, 30, 30);
    MFeld4.setText("..");
    MFeld5.setBounds(50, 50, 30, 30);
    MFeld5.setText("..");
    MFeld6.setBounds(90, 50, 30, 30);
    MFeld6.setText("..");
    //dritte Reihe
    MFeld7.setBounds(10, 90, 30, 30);
    MFeld7.setText("..");
    MFeld8.setBounds(50, 90, 30, 30);
    MFeld8.setText("..");
    MFeld9.setBounds(90, 90, 30, 30);
    MFeld9.setText("..");
    
    
    jLabel1.setBounds(192, 112, 110, 20);
    jLabel1.setText("text");
    cp.add(jLabel1);
    jButton1.setBounds(10, 160, 75, 25);
    jButton1.setText("jButton1");
    jButton1.setMargin(new Insets(2, 2, 2, 2));
    jButton1.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        jButton1_ActionPerformed(evt);
      }
    });
    //Komponenten erzeugen
    cp.add(jButton1);
    cp.add(MFeld1);
    cp.add(MFeld2);
    cp.add(MFeld3);
    cp.add(MFeld4);
    cp.add(MFeld5);
    cp.add(MFeld6);
    cp.add(MFeld7);
    cp.add(MFeld8);
    cp.add(MFeld9);
    
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
  } // end of jButton1_ActionPerformed

  // Ende Methoden
} // end of class dqw

