/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doolhof;

import java.awt.BorderLayout;
import java.awt.event.KeyListener;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Chie-cheung
 */
public class Spel {
        public static void main(String[] args) {
        // we will use the scanner for userInput
        
        // use JFrame to put the created panel on
        JLabel score=new JLabel();
        JLabel ammo=new JLabel();
        JLabel kogel=new JLabel();
        kogel.setText("Ammo");
        ammo.setText("0");
        score.setText("0");
        
        Speler speler=new Speler(score);
        JComponent doolhof=new Doolhof(speler,ammo);
        JPanel panel=new JPanel();
        panel.add(score);
        panel.add(kogel);
        panel.add(ammo);
        
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 800);
        frame.getContentPane().add(doolhof);
        frame.pack();
        frame.setVisible(true);
        frame.add(panel);
        
        KeyListener lissener=new PressListener(doolhof, speler);
        frame.addKeyListener(lissener);
        doolhof.addKeyListener(lissener);
        frame.validate();
        frame.repaint();
            
    }// end of main
}
