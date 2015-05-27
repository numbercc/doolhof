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
    private int x_cord; // x-axis rep
    private int y_cord;// y-axis rep
    private final int kamerGrote = 20;
    private int randommuur;
    private Speler speler;
    private Valsspeler vsp;
    private JLabel bulletCount;

    public Doolhof(Speler speler,JLabel bulletCount) {
        this.speler = speler;
        this.bulletCount=bulletCount;
        tegels = new Tegel[hoogte][breedte];
        muren = new ArrayList<>((hoogte - 1) * (breedte - 1));
        maakRandomDoolhof();
        setPreferredSize(new Dimension(800, 700));
    }

    private void maakRandomDoolhof() {
        maakKamer();// see next method

        initSet(breedte * hoogte);
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
            if (find(roomA) != find(roomB)) {
                muren.remove(randommuur);
                unionRooms(find(roomA), find(roomB));
                temp.getCurrentRoom().getBuren().add(temp.getNextRoom());
                temp.getNextRoom().getBuren().add(temp.getCurrentRoom());
                Tegel tegel=temp.getCurrentRoom();
                if (temp!= null){
                tegel.deleteMuur(temp);
                }
                Tegel tegel2=temp.getNextRoom();
                if (temp!= null){
                tegel2.deleteMuur(temp);
                }
                num--;
            }// end of if
        }// end of while
        tegels[0][0].setSpeler(speler);
        speler.setLokatie(tegels[0][0]);
        tegels[0][0].getBuren().get(0).setWapen(new Bazooka(bulletCount));
        Random r = new Random();
        
        for (int i = 0; i < 3; i++) {
            
            int waarde = r.nextInt(10);
            
            if(waarde == 0) {
                waarde = 5;
            }
            
            vsp = new Valsspeler(tegels, waarde);
            
            int xR = r.nextInt(breedte - 2);
            int yR = r.nextInt(hoogte - 2);
            
            if(xR < 2) {
                xR = 2;
            }
            if(yR < 2) {
                yR = 2;
            }
            
            tegels[xR][yR].setValsspeler(vsp);
            vsp.setLokatie(tegels[xR][yR]);
            
        }
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
                    tegels[i - 1][j].setSouthBuur(tegels[i][j]);
                    tegels[i - 1][j].setSouth(tegels[i][j].getNorth());
                }
                if (i == hoogte - 1) {
                    tegels[i][j].setSouth(new Muur(tegels[i][j]));
                }
                if (j == 0) {
                    tegels[i][j].setWest(new Muur(tegels[i][j]));
                } else {
                    tegels[i][j].setWest(new Muur(tegels[i][j - 1], tegels[i][j]));
                    muren.add(tegels[i][j].getWest());
                    tegels[i][j].setWestBuur(tegels[i][j - 1]);
                    tegels[i][j - 1].setEastBuur(tegels[i][j]);
                    tegels[i][j - 1].setEast(tegels[i][j].getWest());
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
                if ((tegels[i][j].getNorth()!=null)) {
                    g.drawLine(x, y, x + kamerGrote, y);
                }//end of north if
                // west muur not there draw the line
                if (tegels[i][j].getWest()!=null) {
                    g.drawLine(x, y, x, y + kamerGrote);
                }// end of west if
                if ((i == hoogte - 1) && tegels[i][j].getSouth()!=null) {
                    g.drawLine(x, y + kamerGrote, x + kamerGrote,
                            y + kamerGrote);
                }// end of south if
                if ((j == breedte - 1) && tegels[i][j].getEast() !=null) {
                    g.drawLine(x + kamerGrote, y, x + kamerGrote,
                            y + kamerGrote);
                }// end of east if
                if (tegels[i][j].getSpeler() != null) {
                    g.setColor(Color.blue);
                    g.fillOval(x + kamerGrote / 4, y + kamerGrote / 4, kamerGrote / 2, kamerGrote / 2);
                    g.setColor(Color.BLACK);
                }//tekent speler
                if (tegels[i][j].getValsspeler() != null) {
                    g.setColor(Color.red);
                    g.fillOval(x + kamerGrote / 4, y + kamerGrote / 4, kamerGrote / 2, kamerGrote / 2);
                    g.setColor(Color.BLACK); 
                }
                if (tegels[i][j].getWapen()!= null) {
                    g.fillOval(x + kamerGrote / 4, y + kamerGrote / 4, kamerGrote / 2, kamerGrote / 2);
                }
                
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
    private void initSet(int elem){
        set = new int[elem];
      // initialize every element in the set
      for(int i = 0; i < set.length; i++){
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
        ArrayList<Tegel> lijst=new ArrayList<>();
        for (int i = 0; i < hoogte-1; i++) {
            for (int j = 0; j < breedte-1; j++) {
               lijst.add(tegels[i][j]);
                
            }
            
        }
        return lijst;
    }
    
}// END OF CLASS 
