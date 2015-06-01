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
    public PressListener(JComponent comp, Speler speler) {
        this.comp = comp;
        this.speler=speler;
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        speler.move(ke);

    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }
}
