/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doolhof;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JComponent;

public class Tegel {
    // represent four Buitenmuurs

    private Muur north, east, south, west; // the Muur class will be created next
    private int x, y;
    private int positieX,positieY;
    private List<Tegel> buren; // adjacency list using linked list
    private int roomName; // for this the room will be a number
    private int kamerGrote;
    private Speler speler;
    private Persoon persoon;
    private Tegel northBuur, eastBuur, southBuur, westBuur;
    private boolean dijkstra = false;
    private Wapen wapen;
    private Raket raket;
    private JComponent comp;
    private Graphics g;

    public void setG(Graphics g) {
        this.g = g;
    }
    public void teken() {
        Graphics2D g2 = (Graphics2D) g;

        if (getNorth() != null) {
            g2.drawLine(x, y, x + kamerGrote, y);
        }//end of north if
        // west Muur not there draw the line
        if (getWest() != null) {
            g2.drawLine(x, y, x, y + kamerGrote);
        }// end of west if
        if (getSouth() != null) {
            g2.drawLine(x, y + kamerGrote, x + kamerGrote,
                    y + kamerGrote);
        }// end of south if
        if (getEast() != null) {
            g2.drawLine(x + kamerGrote, y, x + kamerGrote,
                    y + kamerGrote);
        }// end of east if
        if (getRaket() != null) {
            getRaket().teken(kamerGrote, x, y, g);
        }
        if (getSpeler() != null) {
            getSpeler().teken(kamerGrote, x, y, g);
        }//tekent speler
        else if (getPersoon() != null) {
            getPersoon().teken(kamerGrote, x, y, g);
        }
        else if (getWapen() != null) {
            getWapen().teken(kamerGrote, x, y, g);
        }
        comp.repaint(x, x, kamerGrote, kamerGrote);
    }

    public JComponent getComp() {
        return comp;
    }

    public int getPositieX() {
        return positieX;
    }

    public int getPositieY() {
        return positieY;
    }

    public void setPositieY(int positieY) {
        this.positieY = positieY;
    }

    public void setPositieX(int positieX) {
        this.positieX = positieX;
    }

    public void setComp(JComponent comp) {
        this.comp = comp;
    }

    public int getKamerGrote() {
        return kamerGrote;
    }

    public void setKamerGrote(int kamerGrote) {
        this.kamerGrote = kamerGrote;
    }

    public void setRoomName(int roomName) {
        this.roomName = roomName;
    }
    private Tegel prev; // last room pointer

    public List<Tegel> getBuren() {
        return buren;
    }

    public boolean isDijkstra() {
        return dijkstra;
    }

    public Wapen getWapen() {
        return wapen;
    }

    public void setWapen(Wapen wapen) {
        this.wapen = wapen;
    }

    public Raket getRaket() {
        return raket;
    }

    public void setRaket(Raket raket) {
        this.raket = raket;
    }

    public void setDijkstra(boolean dijkstra) {
        this.dijkstra = dijkstra;
    }

    public Tegel getEastBuur() {
        return eastBuur;
    }

    public void setEastBuur(Tegel eastBuur) {
        this.eastBuur = eastBuur;
    }

    public Tegel getNorthBuur() {
        return northBuur;
    }

    public void setNorthBuur(Tegel northBuur) {
        this.northBuur = northBuur;
    }

    public Tegel getSouthBuur() {
        return southBuur;
    }

    public void setSouthBuur(Tegel southBuur) {

        this.southBuur = southBuur;
    }

    public Tegel getWestBuur() {
        return westBuur;
    }

    public void setWestBuur(Tegel westBuur) {
        this.westBuur = westBuur;
    }

    public void setburen(List<Tegel> buren) {
        this.buren = buren;
    }

    public Muur getEast() {
        return east;
    }

    public void setEast(Muur east) {
        this.east = east;
    }

    public Muur getNorth() {
        return north;
    }

    public void setNorth(Muur north) {
        this.north = north;
    }

    public Tegel getPrev() {
        return prev;
    }

    public void setPrev(Tegel prev) {
        this.prev = prev;
    }

    public Muur getSouth() {
        return south;
    }

    public void setSouth(Muur south) {

        this.south = south;
    }

    public Muur getWest() {
        return west;
    }

    public void setWest(Muur west) {
        this.west = west;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Persoon getPersoon() {
        return persoon;
    }

    public void setPersoon(Persoon persoon) {
        this.persoon = persoon;
    }

    public void deleteMuur(Muur muur) {
        if (north != null) {
            if (north.equals(muur)) {
                north = null;
            }
        }
        if (south != null) {
            if (south.equals(muur)) {
                south = null;
            }
        }
        if (east != null) {
            if (east.equals(muur)) {
                east = null;
            }
        }
        if (west != null) {
            if (west.equals(muur)) {
                west = null;
            }
        }
    }

    public Speler getSpeler() {
        return speler;
    }

    public void setSpeler(Speler speler) {
        this.speler = speler;
    }

    // now we code the constructer 
    public Tegel(int x, int y) {
        this.x = x;// row
        this.y = y;// column
        buren = new LinkedList<>();
        prev = null;// we have not progressed, so prev is nothing
        roomName = 0;// we will use the concept of arrays start 0
    }// end of constructor

    // we have to increment the room name so lets do it
    public int getRoomName() {
        return roomName++;
    }// end of getRoomName()

    
}