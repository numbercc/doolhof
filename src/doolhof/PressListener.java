/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doolhof;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JComponent;

/**
 *
 * @author Chie-cheung
 */
public class PressListener implements KeyListener {

    private JComponent comp;
    private Speler speler;
    private Spel spel;
    //private Spel spel;
    public PressListener(JComponent comp, Speler speler,Spel spel) {
        this.comp = comp;
        this.speler=speler;
        this.spel=spel;
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        // check of er een actie moet worden uitgevoerd
        speler.move(ke);
        speler.schieten(ke);
        speler.switchWaepon(ke);
        spel.updateScore();
    }
    @Override
    public void keyReleased(KeyEvent ke) {
    }
}
