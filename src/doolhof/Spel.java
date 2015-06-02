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

    private static JComponent doolhof;
    public static JComponent comp;
    private static JFrame frame;

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
        Doolhof doolhofD = new Doolhof(speler, ammo);

        spelerStat.add(textAmmoBazooka);
        spelerStat.add(ammo);
        spelerStat.add(textScore);
        spelerStat.add(score);

        frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        generateGame(speler, doolhofD);
        frame.add(spelerStat, BorderLayout.PAGE_END);
        frame.pack();
        frame.setVisible(true);

        frame.validate();
        frame.repaint();

    }

    public void removeDoolhof() {
        frame.remove(doolhof);
    }

    public static void generateGame(Speler speler, Doolhof dh) {
        doolhof = dh;
        comp = doolhof;
        speler.setComp(comp);
        frame.add(doolhof, BorderLayout.CENTER);
        KeyListener lissener = new PressListener(doolhof, speler);
        frame.addKeyListener(lissener);
        doolhof.addKeyListener(lissener);
        frame.validate();
        frame.repaint();
    }

}
