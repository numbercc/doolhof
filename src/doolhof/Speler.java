/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doolhof;

import javax.swing.JLabel;

/**
 *
 * @author Chie-cheung
 */
public class Speler {

    private Tegel lokatie;
    JLabel score=new JLabel();
    public Speler(JLabel score) {
        this.score=score;
    }

    public Tegel getLokatie() {
        return lokatie;
    }

    public void setLokatie(Tegel Lokatie) {
        this.lokatie = Lokatie;
    }

    public void move(String richting) {
        int som;
        if (richting.equals("w") && lokatie.getNorth().getIsGone()) {
            lokatie.setSpeler(null);
            lokatie.getNorthBuur().setSpeler(this);
            lokatie=lokatie.getNorthBuur();
            som=Integer.parseInt(score.getText())+1;
            score.setText(""+som);
        }
        if (richting.equals("a") && lokatie.getWest().getIsGone()) {
            lokatie.setSpeler(null);
            lokatie.getWestBuur().setSpeler(this);
            lokatie=lokatie.getWestBuur();
            som=Integer.parseInt(score.getText())+1;
            score.setText(""+som);
        }
        if (richting.equals("s") && lokatie.getSouthBuur().getNorth().getIsGone()) {
            lokatie.setSpeler(null);
            lokatie.getSouthBuur().setSpeler(this);
            lokatie=lokatie.getSouthBuur();
            som=Integer.parseInt(score.getText())+1;
            score.setText(""+som);
        }
        if (richting.equals("d") && lokatie.getEastBuur().getWest().getIsGone()) {
            lokatie.setSpeler(null);
            lokatie.getEastBuur().setSpeler(this);
            lokatie=lokatie.getEastBuur();
            
            som=Integer.parseInt(score.getText())+1;
            score.setText(""+som);
        }
    }
}
