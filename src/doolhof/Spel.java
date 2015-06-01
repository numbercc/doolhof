/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doolhof;

import java.awt.BorderLayout;
import java.awt.GridLayout;
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

    public static JComponent comp;

    public static void main(String[] args) {
        // we will use the scanner for userInput

        // use JFrame to put the created panel on
        JPanel spelerStat = new JPanel(new GridLayout(0, 2));
        JLabel score = new JLabel();
        JLabel textScore = new JLabel();
        JLabel ammo = new JLabel();
        JLabel textAmmoBazooka = new JLabel();
        textAmmoBazooka.setText("Bazooka ammo: ");
        textScore.setText("Aantal stappen: ");
        ammo.setText("0");
        score.setText("0");

        Speler speler = new Speler(score);
        JComponent doolhof = new Doolhof(speler, ammo);
        comp = doolhof;
        speler.setComp(comp);
        spelerStat.add(textAmmoBazooka);
        spelerStat.add(ammo);
        spelerStat.add(textScore);
        spelerStat.add(score);

        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(doolhof, BorderLayout.CENTER);
        frame.add(spelerStat, BorderLayout.PAGE_END);
        frame.pack();
        frame.setVisible(true);

        KeyListener lissener = new PressListener(doolhof, speler);
        frame.addKeyListener(lissener);
        doolhof.addKeyListener(lissener);
        frame.validate();
        frame.repaint();

    }// end of main

}
