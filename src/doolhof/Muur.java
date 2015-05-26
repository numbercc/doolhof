/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doolhof;

/**
 *
 * @author Chie-cheung
 */
public class Muur{
   
   private Tegel currentRoom, nextRoom;// room in now, next room 
   //private boolean isGone = false;// is the muur there
   

   // Two constructors will be created
   // which will account for muurs with or 
   // without neighbors

   // with a neighbor 
   public Muur(Tegel huidige, Tegel buur){
      currentRoom = huidige;
      nextRoom = buur;
   }

   // without a neighbor
   public Muur(Tegel r){
      currentRoom = r;
      nextRoom = null;
   }


    public Tegel getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Tegel currentRoom) {
        this.currentRoom = currentRoom;
    }

//    public boolean getIsGone() {
//        return isGone;
//    }
//
//    public void setIsGone(boolean isGone) {
//        this.isGone = isGone;
//    }

    public Tegel getNextRoom() {
        return nextRoom;
    }

    public void setNextRoom(Tegel nextRoom) {
        this.nextRoom = nextRoom;
    }
}