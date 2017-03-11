package bla;

public class Codeschnipsel {

}

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