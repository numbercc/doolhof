/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doolhof;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Chie-cheung
 */
public class Spel {

    private static JComponent comp;
    private static JLabel ammoBazooka;
    private static JLabel pistoolAmmo;
    private static JLabel stappen;
    private static JFrame frame;
    private static Speler speler;
    private static JLabel level;
    private static int levelInt;
    private static KeyListener lissener;

    public static void main(String[] args) {
        // we will use the scanner for userInput

        // use JFrame to put the created panel on
        frame = new JFrame();
        Spel spel = new Spel();

        JPanel spelerStat = new JPanel(new GridLayout(0, 2));
        stappen = new JLabel("0");
        JLabel textStappen = new JLabel("Aantal stappen: ");
        ammoBazooka = new JLabel("0");
        pistoolAmmo = new JLabel("0");
        levelInt = 2;
        String levelS = String.valueOf(levelInt);
        level = new JLabel(levelS);
        JLabel textAmmoBazooka = new JLabel("Bazooka ammo: ");
        JLabel textAmmoPistool = new JLabel("Pistool ammo: ");
        JLabel textLevel = new JLabel("Level: ");

        frame.setLayout(new BorderLayout());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        spelerStat.add(textLevel);
        spelerStat.add(level);
        spelerStat.add(textAmmoBazooka);
        spelerStat.add(ammoBazooka);
        spelerStat.add(textAmmoPistool);
        spelerStat.add(pistoolAmmo);
        spelerStat.add(textStappen);
        spelerStat.add(stappen);

        frame.add(spelerStat, BorderLayout.PAGE_END);
        speler = new Speler();
        spel.maakLevel();
        lissener = new PressListener(spel.comp, speler);
        frame.addKeyListener(lissener);
        spel.comp.addKeyListener(lissener);

        frame.pack();
        frame.setVisible(true);
    }// end of ammoPistool

    public static void updateScore() {
        ammoBazooka.setText("" + speler.getBazooka().getBullet());
        pistoolAmmo.setText("" + speler.getPistool().getBullet());
        stappen.setText("" + speler.getStappen());
        if (speler.getWapen() instanceof Bazooka) {
            ammoBazooka.setText(speler.getBazooka().getBullet() + " selected");
        } else if (speler.getWapen() instanceof Pistool) {
            pistoolAmmo.setText(speler.getPistool().getBullet() + " selected");
        }
    }

    public void removeDoolhof() {
        frame.remove(comp);
    }

    public void maakLevel() {
        comp = null;
        comp = new Doolhof(speler);
        frame.add(comp, BorderLayout.CENTER);
        frame.validate();
        frame.repaint();
        levelInt++;

        if (levelInt >= 4) {
            frame.remove(comp);
            winScherm();
            frame.removeKeyListener(lissener);
            
        } else {
            String levelS = String.valueOf(levelInt);
            level.setText(levelS);
        }

    }

    public static void setAmmoBazooka(JLabel AmmoBazooka) {
        Spel.ammoBazooka = AmmoBazooka;
    }

    public static void setPistoolAmmo(JLabel pistoolAmmo) {
        Spel.pistoolAmmo = pistoolAmmo;
    }

    public static void setStappen(JLabel stappen) {
        Spel.stappen = stappen;
    }
    
    public void winScherm() {
        JPanel winScherm = new JPanel();
        JLabel win = new JLabel("U heeft gewonnen!");
        JLabel textStappen = new JLabel("Final Score: ");
        
        winScherm.add(win);
        winScherm.add(textStappen);
        winScherm.add(stappen);
        
        frame.add(winScherm, BorderLayout.CENTER);
    }

}
