package bla;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Codeschnipsel {

}

// action listener kürzer gestalten durch objekt-übergabe
// http://blog.mynotiz.de/programmieren/java-jmenubar-beispiel-tutorial-235/


// Ausführliche video tutorial wie ein dame spiel programmiert wird
// http://www.java-tutorial.org/video-tutorial-dame3.html



// jLabel mit icon
// http://www.java-tutorial.org/jlabel.html




// Linksklick aus Beispiel
// http://www.java2s.com/Tutorials/Java/java.awt.event/MouseEvent/Java_MouseEvent_getButton_.htm

/* Alte Rechtsklick mit Linksklick Routine
 * 
 *    MFeld2.addActionListener(new ActionListener() { 
        public void actionPerformed(ActionEvent evt) { 
        	MFeld2_ActionPerformed(evt);
        }
      });
    //Links
    MFeld2.addMouseListener(new MouseAdapter() {
        public void mousePressed(MouseEvent e) {
          if (Spielfeldgesperrt==true)
          {
        	  //do nothing
          }
          else MFeld2.setText("*");
        }
      });
      
 */

/*

// Eine Methode wie man Buttons dynamisch erstellen kann

package de.tutorials.swing;
 
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import javax.swing.JButton;
import javax.swing.JFrame;
 
public class GenericButtonExample extends JFrame implements ActionListener{
    
    public GenericButtonExample() {
        
        setSize(600,600);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5,6));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        for(int i = 0; i < 30; i++){
            JButton button = new JButton("button"+i);
            button.setActionCommand("button"+i);
            button.addActionListener(this);
            add(button);
            
        }
    }
    
    
    public static void main(String[] args) {
        new GenericButtonExample().setVisible(true);
    }
 
 
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("button1")){
            System.out.println("TEST");
        }
        
    }
 
}

*/