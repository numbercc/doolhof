/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doolhof;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JComponent;
import javax.swing.JLabel;

/**
 *
 * @author Chie-cheung
 */
public class Doolhof extends JComponent {
    // the maze can be seen as a matrix of squares so well use a multidimensional array of the room 
    // class

    private Tegel[][] tegels;// the maze can be seen as a matrix of squares so well use
    private ArrayList<Muur> muren; // List of muren
    private Random rand;// We are going to have to randomize the tegels chosen to unionize
    private final int hoogte = 20;// users desired height of matrix 
    private final int breedte = 20;// users desired breedte of matrix
    private int num;// we will have to increment a few times so lets just use num as an incrementor
    private int[] set;
    private int randommuur;
    private Speler speler;
    private Valsspeler vsp;
    private Vriend vriend;

    public Doolhof(Speler speler, JLabel bulletCount) {
        this.speler = speler;
        tegels = new Tegel[hoogte][breedte];
        muren = new ArrayList<>((hoogte - 1) * (breedte - 1));
        maakRandomDoolhof();
        setPreferredSize(new Dimension(500, 500));
    }

    private void maakRandomDoolhof() {
        maakKamer();// see next method

        initSet(breedte * hoogte);
        rand = new Random(); // here is the random room generator
        num = breedte * hoogte;

        while (num > 1) {
            // when we pick a random muur we want to avoid the borders getting eliminated
            randommuur = rand.nextInt(muren.size());
            BinnenMuur temp =(BinnenMuur) muren.get(randommuur);
            // we will pick two tegels randomly 
            int roomA = temp.getCurrentRoom().getY() + temp.getCurrentRoom().getX() * breedte;
            int roomB = temp.getNextRoom().getY() + temp.getNextRoom().getX() * breedte;

            // check roomA and roomB to see if they are already members 
            if (find(roomA) != find(roomB)) {
                muren.remove(randommuur);
                unionRooms(find(roomA), find(roomB));
                temp.getCurrentRoom().getBuren().add(temp.getNextRoom());
                temp.getNextRoom().getBuren().add(temp.getCurrentRoom());
                Tegel tegel = temp.getCurrentRoom();
                Muur muur = (Muur)temp;
                if (temp != null) {
                    tegel.deleteMuur(muur);
                }
                Tegel tegel2 = temp.getNextRoom();
                if (temp != null) {
                    tegel2.deleteMuur(muur);
                }
                num--;
            }// end of if
        }// end of while
        tegels[0][0].setSpeler(speler);
        speler.setLocatie(tegels[0][0]);
        tegels[0][0].getBuren().get(0).setWapen(new Bazooka());
        Random r = new Random();

        for (int i = 0; i < 3; i++) {

            int waarde = r.nextInt(10)+3;


            vsp = new Valsspeler(tegels, waarde);

            int xR = r.nextInt(breedte - 2);
            int yR = r.nextInt(hoogte - 2);

            if (xR < 2) {
                xR = 2;
            }
            if (yR < 2) {
                yR = 2;
            }
            tegels[xR][yR].setPersoon(vsp);
            vsp.setLocatie(tegels[xR][yR]);
        }
        vriend = new Vriend();
        tegels[19][19].setPersoon(vriend);
        vriend.setLocatie(tegels[19][19]);
    }
    // name the room to display
    private int roomNumber = 0;

    /**
     * Sets the grid of tegels to be initially boxes This is self explanitory,
     * we are only creating an reverse L for all The tegels and there is an L
     * for the border
     */
    private void maakKamer() {
        for (int i = 0; i < hoogte; i++) {
            for (int j = 0; j < breedte; j++) {
                // create north muren
                tegels[i][j] = new Tegel(i, j);
                if (i == 0) {
                    tegels[i][j].setNorth(new Buitenmuur(tegels[i][j]));
                } else {
                    tegels[i][j].setNorth((Muur)new BinnenMuur(tegels[i - 1][j], tegels[i][j]));
                    muren.add(tegels[i][j].getNorth());
                    tegels[i][j].setNorthBuur(tegels[i - 1][j]);
                    tegels[i - 1][j].setSouthBuur(tegels[i][j]);
                    tegels[i - 1][j].setSouth(tegels[i][j].getNorth());
                }
                if (i == hoogte - 1) {
                    tegels[i][j].setSouth(new Buitenmuur(tegels[i][j]));
                }
                if (j == 0) {
                    tegels[i][j].setWest(new Buitenmuur(tegels[i][j]));
                } else {
                    tegels[i][j].setWest((Muur)new BinnenMuur(tegels[i][j - 1], tegels[i][j]));
                    muren.add(tegels[i][j].getWest());
                    tegels[i][j].setWestBuur(tegels[i][j - 1]);
                    tegels[i][j - 1].setEastBuur(tegels[i][j]);
                    tegels[i][j - 1].setEast(tegels[i][j].getWest());
                }
                if (j == breedte - 1) {
                    tegels[i][j].setEast(new Buitenmuur(tegels[i][j]));
                }
                tegels[i][j].setRoomName(roomNumber++);// we will name the tegels
            }
        }
        // initalize entrance and exit
        // this is just saying the roomName for top left is 0 
        tegels[0][0].setRoomName(0);
        // this is just saying the roomName for bottom right is the last element in the mxn room matrix
        tegels[hoogte - 1][breedte - 1].setRoomName(hoogte * breedte);
    }

    @Override
    public void paintComponent(Graphics g) {
        int x_cord; // x-axis rep
        int y_cord;// y-axis rep
        int kamerGrote = 20;
        x_cord = 50;
        y_cord = 50;
        
        int x = x_cord;
        int y = y_cord;

        for (int i = 0; i <= hoogte - 1; i++) {
            for (int j = 0; j <= breedte - 1; j++) {
                tegels[i][j].setComp(this);
                tegels[i][j].setX(x);
                tegels[i][j].setY(y);
                tegels[i][j].setKamerGrote(kamerGrote);
                tegels[i][j].setG(g);
                tegels[i][j].setPositieX(i);
                tegels[i][j].setPositieY(j);
                tegels[i][j].teken();
                
                x += kamerGrote;// change the horizontal
            }// end of inner for loop
            x = x_cord;
            y += kamerGrote;
        }// end of outer for loop
    }

    private int find(int r) {
        if (set[r] < 0) {
            return r;
        } else {
            return set[r] = find(set[r]);
        }
    }// end of find

    private void initSet(int elem) {
        set = new int[elem];
        // initialize every element in the set
        for (int i = 0; i < set.length; i++) {
            set[i] = -1;
        }
    }

    private void unionRooms(int roomA, int roomB) {
        if (set[roomB] < set[roomA]) {
            set[roomA] = roomB;
        } else {
            if (set[roomA] == set[roomB]) {
                set[roomA]--;
            }
            set[roomB] = roomA;
        }
    }// end of union rooms

    public ArrayList<Tegel> getLijstTegels() {
        ArrayList<Tegel> lijst = new ArrayList<>();
        for (int i = 0; i < hoogte - 1; i++) {
            for (int j = 0; j < breedte - 1; j++) {
                lijst.add(tegels[i][j]);

            }

        }
        return lijst;
    }
}// END OF CLASS 
