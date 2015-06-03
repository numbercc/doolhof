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

    private static JComponent comp;
    private static JLabel ammoBazooka;
    private static JLabel pistoolAmmo;
    private static JLabel stappen;
    private static JFrame frame;
    private static Speler speler;
    public static void main(String[] args) {
        // we will use the scanner for userInput

        // use JFrame to put the created panel on
        frame=new JFrame();
        Spel spel = new Spel();
        
        JPanel spelerStat = new JPanel(new GridLayout(0, 2));
        stappen=new JLabel("0");
        JLabel textStappen = new JLabel("Aantal stappen: ");
        ammoBazooka = new JLabel("0");
        pistoolAmmo=new JLabel("0");
        JLabel textAmmoBazooka = new JLabel("Bazooka ammo: ");
        JLabel textAmmoPistool = new JLabel("Pistool ammo: ");
        
        
        frame.setLayout(new BorderLayout());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        spelerStat.add(textAmmoBazooka);
        spelerStat.add(ammoBazooka);
        spelerStat.add(textAmmoPistool);
        spelerStat.add(pistoolAmmo);
        spelerStat.add(textStappen);
        spelerStat.add(stappen);
        
        frame.add(spelerStat, BorderLayout.PAGE_END);
        speler = new Speler();
        spel.maakLevel();
        KeyListener lissener = new PressListener(spel.comp, speler);
        frame.addKeyListener(lissener);
        spel.comp.addKeyListener(lissener);
        
        frame.pack();
        frame.setVisible(true);
    }// end of ammoPistool

    public static void updateScore() {
        ammoBazooka.setText(""+speler.getBazooka().getBullet());
        pistoolAmmo.setText(""+speler.getPistool().getBullet());
        stappen.setText(""+speler.getStappen());
        if(speler.getWapen() instanceof Bazooka){
            ammoBazooka.setText(speler.getBazooka().getBullet()+" selected");
        }
        else if(speler.getWapen() instanceof Pistool){
            pistoolAmmo.setText(speler.getPistool().getBullet()+" selected");
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



}
