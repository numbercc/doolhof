/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doolhof;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JComponent;
import javax.swing.JPanel;

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
    private int hoogte = 20;// users desired height of matrix 
    private int breedte = 20;// users desired breedte of matrix
    private int num;// we will have to increment a few times so lets just use num as an incrementor
    private Buren ds;// we are going to join tegels so well label the variable ds for disjoint set
    // we are going to need variables for our panel 
    private int x_cord; // x-axis rep
    private int y_cord;// y-axis rep
    private int kamerGrote = 20;
    private int randommuur;
    private Speler speler;

    public Doolhof(Speler speler) {
        this.speler = speler;
        tegels = new Tegel[hoogte][breedte];
        muren = new ArrayList<>((hoogte - 1) * (breedte - 1));
        maakRandomDoolhof();
        setPreferredSize(new Dimension(800, 700));
    }

    private void maakRandomDoolhof() {
        maakKamer();// see next method
        ds = new Buren(breedte * hoogte);
        rand = new Random(); // here is the random room generator
        num = breedte * hoogte;

        while (num > 1) {
            // when we pick a random muur we want to avoid the borders getting eliminated
            randommuur = rand.nextInt(muren.size());
            Muur temp = muren.get(randommuur);
            // we will pick two tegels randomly 
            int roomA = temp.getCurrentRoom().getY() + temp.getCurrentRoom().getX() * breedte;
            int roomB = temp.getNextRoom().getY() + temp.getNextRoom().getX() * breedte;

            // check roomA and roomB to see if they are already members 
            if (ds.find(roomA) != ds.find(roomB)) {
                muren.remove(randommuur);
                ds.unionRooms(ds.find(roomA), ds.find(roomB));
                temp.setIsGone(true);
                temp.getCurrentRoom().getBuren().add(temp.getNextRoom());
                temp.getNextRoom().getBuren().add(temp.getCurrentRoom());
                num--;
            }// end of if
        }// end of while
        tegels[0][0].setSpeler(speler);
        speler.setLokatie(tegels[0][0]);
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
                    tegels[i][j].setNorth(new Muur(tegels[i][j]));
                } else {
                    tegels[i][j].setNorth(new Muur(tegels[i - 1][j], tegels[i][j]));
                    muren.add(tegels[i][j].getNorth());
                    tegels[i][j].setNorthBuur(tegels[i - 1][j]);
                    tegels[i-1][j].setSouthBuur(tegels[i][j]);
                }
                if (i == hoogte - 1) {
                    tegels[i][j].setSouth(new Muur(tegels[i][j]));                    
                }
                if (j == 0) {
                    tegels[i][j].setWest(new Muur(tegels[i][j]));
                } else {
                    tegels[i][j].setWest(new Muur(tegels[i][j - 1], tegels[i][j]));
                    muren.add(tegels[i][j].getWest());
                    tegels[i][j].setWestBuur(tegels[i][j-1]);
                    tegels[i][j-1].setEastBuur(tegels[i][j]);
                }
                if (j == breedte - 1) {
                    tegels[i][j].setEast(new Muur(tegels[i][j]));
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
        x_cord = 50;
        y_cord = 50;
        // could have taken height as well as breedte
        // just need something to base the roomsize


        // temp variables used for painting
        int x = x_cord;
        int y = y_cord;

        for (int i = 0; i <= hoogte - 1; i++) {
            for (int j = 0; j <= breedte - 1; j++) {
                if (!(tegels[i][j].getNorth().getIsGone())) {
                    g.drawLine(x, y, x + kamerGrote, y);
                }//end of north if
                // west muur not there draw the line
                if (tegels[i][j].getWest().getIsGone() == false) {
                    g.drawLine(x, y, x, y + kamerGrote);
                }// end of west if
                if ((i == hoogte - 1) && tegels[i][j].getSouth().getIsGone() == false) {
                    g.drawLine(x, y + kamerGrote, x + kamerGrote,
                            y + kamerGrote);
                }// end of south if
                if ((j == breedte - 1) && tegels[i][j].getEast().getIsGone() == false) {
                    g.drawLine(x + kamerGrote, y, x + kamerGrote,
                            y + kamerGrote);
                }// end of east if
                if (tegels[i][j].getSpeler() != null) {
                    g.setColor(Color.red);
                    g.fillOval(x + kamerGrote / 4, y + kamerGrote / 4, kamerGrote / 2, kamerGrote / 2);
                    g.setColor(Color.BLACK);
                }//tekent speler
                x += kamerGrote;// change the horizontal
            }// end of inner for loop
            x = x_cord;
            y += kamerGrote;
        }// end of outer for loop
    }
}// END OF CLASS 
