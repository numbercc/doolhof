/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doolhof;

import java.awt.event.KeyListener;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 *
 * @author Chie-cheung
 */
public class Spel {
        public static void main(String[] args) {
        // we will use the scanner for userInput
        
        // use JFrame to put the created panel on
        Speler speler=new Speler();// dit goede lokatie gemaakt??????
        JComponent doolhof=new Doolhof(speler);
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 800);
        frame.getContentPane().add(doolhof);
        frame.pack();
        frame.setVisible(true);
        KeyListener lissener=new PressListener(doolhof, speler);
        frame.addKeyListener(lissener);
        doolhof.addKeyListener(lissener);
    }// end of main
}
