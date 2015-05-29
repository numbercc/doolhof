/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doolhof;

/**
 *
 * @author Chie-cheung
 */
public class Raket {

    private Tegel locatie;
    private int vluchtLeven;
    private int tegelVoortgang;
    private Richting richting;

    public Raket(Tegel locatie) {
        this.locatie = locatie;
        vluchtLeven = 100;
        tegelVoortgang = 0;
    }

    public Tegel getLocatie() {
        return locatie;
    }

    public Richting getRichting() {
        return richting;
    }

    public void setRichting(Richting richting) {
        this.richting = richting;
    }

    public void vlieg() {
        if (vluchtLeven > 0) {
            vluchtLeven=vluchtLeven-10;
            if (tegelVoortgang < 100) {
                tegelVoortgang = tegelVoortgang + 10;

            } else {
                tegelVoortgang = 0;
                volgendeLocatie();
  
            }
            
        }
        else{
            locatie.setRaket(null);
        }

    }

    private void volgendeLocatie() {
        if (richting == richting.down) {
            if(locatie.getSouth()!=null){
            locatie = locatie.getSouthBuur();
            }
            else{
                locatie.setSouth(null);
            }
        } else if (richting == richting.left) {
            if(locatie.getWest()!=null){
            locatie = locatie.getWestBuur();
            }
            else{
                locatie.setWest(null);
            }
        } else if (richting == richting.right) {
            if(locatie.getEast()!=null){
            locatie = locatie.getEastBuur();
            }
            else{
                locatie.setEast(null);
            }
        } else if (richting == richting.up) {
            if(locatie.getNorth()!=null){
            locatie = locatie.getNorthBuur();
            }
            else{
                locatie.setNorth(null);
            }
        }
    }

    public void setLocatie(Tegel locatie) {
        this.locatie = locatie;
    }

    public int getVluchtLeven() {
        return vluchtLeven;
    }

    public void setVluchtLeven(int vluchtLeven) {
        this.vluchtLeven = vluchtLeven;
    }

    public int getTegelVoortgang() {
        return tegelVoortgang;
    }

    public void setTegelVoortgang(int tegelVoortgang) {
        this.tegelVoortgang = tegelVoortgang;
    }

}
