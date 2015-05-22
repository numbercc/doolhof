/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doolhof;

import java.util.LinkedList;
import java.util.List;

public class Tegel {
    // represent four muurs

    private Muur north, east, south, west; // the muur class will be created next
    private int x, y; // represent the row and column of the maze
    private List<Tegel> buren; // adjacency list using linked list
    private int roomName; // for this the room will be a number
    private Speler speler;
    private Valsspeler vsp;
    private Tegel northBuur, eastBuur, southBuur, westBuur;
    private boolean dijkstra=false;
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

    public Speler getSpeler() {
        return speler;
    }

    public void setSpeler(Speler speler) {
        this.speler = speler;
    }
    
    public Valsspeler getValsspeler() {
        return vsp;
    }
    
    public void setValsspeler(Valsspeler vsp) {
        this.vsp = vsp;
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