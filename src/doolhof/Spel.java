/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doolhof;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Chie-cheung
 */
public class Spel {

    private static JComponent comp;
    private static JLabel ammoBazooka;
    private static JLabel pistoolAmmo;
    private static JLabel mountLabel;
    private static JLabel mountWaardeLabel;
    private static JLabel stappen;
    private static JFrame frame;
    private static Speler speler;
    private static JLabel level;
    private static int levelInt;
    private static KeyListener lissener;
    private static Doolhof copy;
    public static void main(String[] args) {
        // we will use the scanner for userInput

        // use JFrame to put the created panel on
        frame = new JFrame();
        Spel spel = new Spel();
        JButton reset = new JButton("reset level");
        reset.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                resetLevel();
            }
        });
        JPanel spelerStat = new JPanel(new GridLayout(0, 2));
        stappen = new JLabel("0");
        JLabel textStappen = new JLabel("Aantal stappen: ");
        ammoBazooka = new JLabel("0");
        pistoolAmmo = new JLabel("0");
        mountLabel = new JLabel("Geen Mount");
        mountWaardeLabel = new JLabel();
        levelInt = 0;
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
        spelerStat.add(mountLabel);
        spelerStat.add(mountWaardeLabel);

        frame.add(spelerStat, BorderLayout.PAGE_END);
        speler = new Speler();
        spel.maakLevel();
        lissener = new PressListener(spel.comp, speler);
        frame.addKeyListener(lissener);
        reset.addKeyListener(lissener);
        spel.comp.addKeyListener(lissener);
        frame.add(reset, BorderLayout.NORTH);
        frame.pack();
        frame.setVisible(true);
    }// end of ammoPistool

    public static void updateScore() {
        ammoBazooka.setText("" + speler.getBazooka().getBullet());
        pistoolAmmo.setText("" + speler.getPistool().getBullet());
        double stap = speler.getStappen();
        int stapInt = (int) stap;
        String stapString = String.valueOf(stapInt);
        stappen.setText("" + stapString);
        if (speler.getWapen() instanceof Bazooka) {
            ammoBazooka.setText(speler.getBazooka().getBullet() + " selected");
        } else if (speler.getWapen() instanceof Pistool) {
            pistoolAmmo.setText(speler.getPistool().getBullet() + " selected");
        }
        if (speler.getMount() != null) {
            int mountWaarde = (int) speler.getMount().getWaarde();
            mountLabel.setText("Mount actief met waarde " + mountWaarde);
            mountLabel.setVisible(true);
            String waardeString = String.valueOf(speler.getMount().getStapWaarde());
            mountWaardeLabel.setVisible(true);
            mountWaardeLabel.setText(waardeString);
        } else if (speler.getMount() == null) {
            mountLabel.setText("Geen Mount");
            mountWaardeLabel.setVisible(false);
        }
    }

    private static void resetLevel() {
        frame.remove(comp);
        comp = copy;
        frame.add(comp, BorderLayout.CENTER);
        frame.validate();
        frame.repaint();
    }

    public void removeDoolhof() {
        frame.remove(comp);
    }

    public void maakLevel() {
        comp = null;
        comp = new Doolhof(speler);
        Doolhof doolhof = (Doolhof) comp;
        try {
            copy = (Doolhof) doolhof.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Spel.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    public static int getLevelInt() {
        return levelInt;
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
