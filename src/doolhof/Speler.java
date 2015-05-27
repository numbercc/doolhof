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
    JLabel score;
    
    public Speler(JLabel score) {
        this.score=score;
    }

    public Tegel getLocatie() {
        return lokatie;
    }

    public void setLokatie(Tegel Lokatie) {
        this.lokatie = Lokatie;
    }

    public void move(String richting) {
        int som;
        if (richting.equals("w") && lokatie.getNorth()==null && lokatie.getNorthBuur().getValsspeler() == null) {
            lokatie.setSpeler(null);
            lokatie.getNorthBuur().setSpeler(this);
            lokatie=lokatie.getNorthBuur();
            som=Integer.parseInt(score.getText())+1;
            score.setText(""+som);
        }
        if (richting.equals("a") && lokatie.getWest()==null && lokatie.getWestBuur().getValsspeler() == null) {
            lokatie.setSpeler(null);
            lokatie.getWestBuur().setSpeler(this);
            lokatie=lokatie.getWestBuur();
            som=Integer.parseInt(score.getText())+1;
            score.setText(""+som);
        }
        if (richting.equals("s") && lokatie.getSouthBuur().getNorth()==null && lokatie.getSouthBuur().getValsspeler() == null) {
            lokatie.setSpeler(null);
            lokatie.getSouthBuur().setSpeler(this);
            lokatie=lokatie.getSouthBuur();
            som=Integer.parseInt(score.getText())+1;
            score.setText(""+som);
        }
        if (richting.equals("d") && lokatie.getEastBuur().getWest()==null && lokatie.getEastBuur().getValsspeler() == null) {
            lokatie.setSpeler(null);
            lokatie.getEastBuur().setSpeler(this);
            lokatie=lokatie.getEastBuur();
            
            som=Integer.parseInt(score.getText())+1;
            score.setText(""+som);
        }
        //Hieronder Valsspeler collision
        
        if (richting.equals("w") && lokatie.getNorth()==null && lokatie.getNorthBuur().getValsspeler() != null) {
            Valsspeler vsp=lokatie.getNorthBuur().getValsspeler() ;
            lokatie.setSpeler(null);
            getLocatie().setSpeler(null);
            vsp.zetSpelerTerug(this);
            lokatie.setSpeler(this);
            som=Integer.parseInt(score.getText())+1;
            score.setText(""+som);
        }
        if (richting.equals("a") && lokatie.getWest()==null && lokatie.getWestBuur().getValsspeler() != null) {
            Valsspeler vsp=lokatie.getWestBuur().getValsspeler() ;
            Tegel temp=lokatie;
            getLocatie().setSpeler(null);
            temp.setSpeler(null);
            vsp.zetSpelerTerug(this);
            lokatie.setSpeler(this);
            som=Integer.parseInt(score.getText())+1;
            score.setText(""+som);
        }
        if (richting.equals("s") && lokatie.getSouthBuur().getNorth()==null && lokatie.getSouthBuur().getValsspeler() != null) {
            Valsspeler vsp=lokatie.getSouthBuur().getValsspeler() ;
            Tegel temp=lokatie;
            getLocatie().setSpeler(null);
            temp.setSpeler(null);
            vsp.zetSpelerTerug(this);
            lokatie.setSpeler(this);
            som=Integer.parseInt(score.getText())+1;
            score.setText(""+som);
        }
        if (richting.equals("d") && lokatie.getEastBuur().getWest()==null && lokatie.getEastBuur().getValsspeler() != null) {
            Valsspeler vsp=lokatie.getEastBuur().getValsspeler() ;
            Tegel temp=lokatie;
            getLocatie().setSpeler(null);
            temp.setSpeler(null);
            vsp.zetSpelerTerug(this);

            lokatie.setSpeler(this);
            
            som=Integer.parseInt(score.getText())+1;
            score.setText(""+som);
        }
    }

}
