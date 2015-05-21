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
        int key = ke.getKeyCode();
        if (key == KeyEvent.VK_W) {
            speler.move("w");
            comp.repaint();
        }
        if (key == KeyEvent.VK_S) {
            speler.move("s");
            comp.repaint();
        }
        if (key == KeyEvent.VK_A) {
            speler.move("a");
            comp.repaint();
        }
        if (key == KeyEvent.VK_D) {
            speler.move("d");
            comp.repaint();
        }

    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }
}
