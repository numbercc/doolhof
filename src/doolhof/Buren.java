/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doolhof;

/**
 *
 * @author Chie-cheung
 */
public class Buren{
   private int[] set; // this is an array to store a set of rooms
   public Buren(int elem){
      set = new int[elem];
      // initialize every element in the set
      for(int i = 0; i < set.length; i++){
         set[i] = -1;
      }
   }// end of constructor

   // find using compression
   public int find(int r){
      if(set[r] < 0){
         return r;
      } else {
         return set[r] = find(set[r]);
      }
  }// end of find

  public void unionRooms(int roomA, int roomB){
      if(set[roomB] < set[roomA]){
          set[roomA] = roomB;
      } else {
         if(set[roomA] == set[roomB]){
            set[roomA]--;
         }
         set[roomB] = roomA;
     }
 }// end of union rooms

}