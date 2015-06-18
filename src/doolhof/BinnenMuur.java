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
    public void wordGeraakt(int damage) {
        // muur word geraakt hier krijgt de muu schade als leven op 0 is dan word de muur verwijdert door zijn super classe.
        super.setLeven(super.getLeven() - damage);
    }

    public Tegel getNextRoom() {
        return nextRoom;
    }

    public void setNextRoom(Tegel nextRoom) {
        this.nextRoom = nextRoom;
    }

    @Override
    public Object maakKopie() {
        return new BinnenMuur(null, null);
    }

}
