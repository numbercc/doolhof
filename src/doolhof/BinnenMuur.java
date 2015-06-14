/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doolhof;

/**
 *
 * @author Chie-cheung
 */
public class BinnenMuur extends Muur {

    // room in now, next room 
    //private boolean isGone = false;// is the muur there
    private Tegel nextRoom;
    // Two constructors will be created
    // which will account for muurs with or 
    // without neighbors

    public BinnenMuur(Tegel nextRoom, Tegel currentRoom) {
        super(currentRoom);
        this.nextRoom = nextRoom;
    }

    // with a neighbor 
    public Tegel getCurrentRoom() {
        return super.getCurrentRoom();
    }
    @Override
    public void wordGeraakt(int damage){
        super.setLeven(super.getLeven()-damage);
    }


    public Tegel getNextRoom() {
        return nextRoom;
    }

    public void setNextRoom(Tegel nextRoom) {
        this.nextRoom = nextRoom;
    }

    @Override
    public Muur maakKopie(Muur muur) {
        return new BinnenMuur(null, null);
    }

}