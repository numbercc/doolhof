/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doolhof;

/**
 *
 * @author Chie-cheung
 */
public abstract class Muur {
    private Tegel currentRoom;
    private int leven=100;
    public Muur(Tegel currentRoom) {
        this.currentRoom = currentRoom;
    }

    public Tegel getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Tegel currentRoom) {
        this.currentRoom = currentRoom;
    }
    public abstract void wordGeraakt(int damage);

    public int getLeven() {
        return leven;
    }

    public void setLeven(int leven) {
        this.leven = leven;
    }

    
}
