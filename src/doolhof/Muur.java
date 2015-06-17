/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doolhof;

/**
 *
 * @author Chie-cheung
 */
public abstract class Muur implements MaakKopie {

    private Tegel currentRoom;
    private int leven = 100;

    public abstract void wordGeraakt(int damage);

    @Override
    public abstract Object maakKopie();

    public Muur(Tegel currentRoom) {
        this.currentRoom = currentRoom;
    }

    public Tegel getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Tegel currentRoom) {
        this.currentRoom = currentRoom;
    }

    public int getLeven() {
        return leven;
    }

    public void setLeven(int leven) {
        this.leven = leven;
    }

}
