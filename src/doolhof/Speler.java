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
    private Wapen wapen;
    private Richting richting;
    public Speler(JLabel score) {
        this.score = score;
        richting= Richting.left;
    }

    public Tegel getLocatie() {
        return lokatie;
    }

    public Wapen getWapen() {
        return wapen;
    }

    public void setWapen(Wapen wapen) {
        this.wapen = wapen;
    }

    public void setLokatie(Tegel Lokatie) {
        this.lokatie = Lokatie;
    }

    public void move(String richting) {
        
        if (richting.equals("w") && lokatie.getNorth() == null) {
            moveUp();
        }
        if (richting.equals("a") && lokatie.getWest() == null) {
            moveLeft();
        }
        if (richting.equals("s") && lokatie.getSouthBuur().getNorth() == null) {
            moveDown();
        }
        if (richting.equals("d") && lokatie.getEastBuur().getWest() == null) {
            moveRight();
        } //Hieronder Valsspeler collision
    }
    private void pickUpWaepon(){
        if(wapen==null){
        setWapen(lokatie.getWapen());
        wapen.setBullet(1);
        }
        else{
            wapen.setBullet(wapen.getBullet()+1);
        }
        lokatie.setWapen(null);
        wapen.setSpeler(this);
    }
    private void moveUp() {
        int som;

        if (lokatie.getNorthBuur().getValsspeler() == null) {
            lokatie.setSpeler(null);
            lokatie.getNorthBuur().setSpeler(this);
            lokatie = lokatie.getNorthBuur();
            som = Integer.parseInt(score.getText()) + 1;
            score.setText("" + som);
            if(lokatie.getWapen()!=null){
                pickUpWaepon();
            }
        } else {
            Valsspeler vsp = lokatie.getNorthBuur().getValsspeler();
            valsspelerCollision(vsp);
            som = Integer.parseInt(score.getText()) + 1;
            score.setText("" + som);
        }
    }
    
    private void moveLeft() {
        int som;

        if (lokatie.getWestBuur().getValsspeler() == null) {
            lokatie.setSpeler(null);
            lokatie.getWestBuur().setSpeler(this);
            lokatie = lokatie.getWestBuur();
            som = Integer.parseInt(score.getText()) + 1;
            score.setText("" + som);
            if(lokatie.getWapen()!=null){
                pickUpWaepon();
            }
        } else {
            Valsspeler vsp = lokatie.getWestBuur().getValsspeler();
            valsspelerCollision(vsp);
        }
    }

    private void moveDown() {
        int som;

        if (lokatie.getSouthBuur().getValsspeler() == null) {
            lokatie.setSpeler(null);
            lokatie.getSouthBuur().setSpeler(this);
            lokatie = lokatie.getSouthBuur();
            som = Integer.parseInt(score.getText()) + 1;
            score.setText("" + som);
            if(lokatie.getWapen()!=null){
                pickUpWaepon();
            }
        } else {
            Valsspeler vsp = lokatie.getSouthBuur().getValsspeler();
            valsspelerCollision(vsp);
        }
    }

    private void moveRight() {
        int som;

        if (lokatie.getEastBuur().getValsspeler() == null) {
            lokatie.setSpeler(null);
            lokatie.getEastBuur().setSpeler(this);
            lokatie = lokatie.getEastBuur();
            som = Integer.parseInt(score.getText()) + 1;
            score.setText("" + som);
            if(lokatie.getWapen()!=null){
                pickUpWaepon();
            }
        } else {
            Valsspeler vsp = lokatie.getEastBuur().getValsspeler();
            valsspelerCollision(vsp);
        }
        
    }

    private void valsspelerCollision(Valsspeler vsp) {
        Tegel temp = lokatie;
        getLocatie().setSpeler(null);
        temp.setSpeler(null);
        vsp.zetSpelerTerug(this, vsp);
        lokatie.setSpeler(this);
    }

    public Richting getRichting() {
        return richting;
    }

    public void setRichting(Richting richting) {
        this.richting = richting;
    }
}
