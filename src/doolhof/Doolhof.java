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
import javax.swing.Timer;

/**
 *
 * @author Chie-cheung
 */
public class Doolhof extends JComponent {
    // Een doohof met muren als attribuut het genereert automatisch een doolhof en benoemt de buren

    private Tegel[][] tegels;// de matrix van de tegels. soort van de plattegrond
    private ArrayList<Muur> muren; // lijst met muren die wordt gebruikt om muren te verwijderen
    private Random rand;// rondom getallen maken
    private final int hoogte = 20;// de hoogte van het doolhof
    private final int breedte = 20;// breedte van het doolhof
    private int num;// een getal om te verhogen
    private int[] set;
    private int randommuur;
    private Speler speler;
    private int lvl;
    private Spel spel;

    public Doolhof(Speler speler, int lvl,Spel spel) {
        this.speler = speler;
        this.lvl = lvl;
        this.spel=spel;
        tegels = new Tegel[hoogte][breedte];
        muren = new ArrayList<>((hoogte - 1) * (breedte - 1));
        maakRandomDoolhof();
        setPreferredSize(new Dimension(500, 500));

    }

    private void maakRandomDoolhof() {
        //maakkamer() maakt eerst doolhof met kleine kamers waar alle muren nog bestaan
        // random worden er dan muren verwijdert en checken we of wel elk tegel is verbonden met een andere
        maakKamer();
        
        initSet(breedte * hoogte);
        rand = new Random(); // random genereren
        num = breedte * hoogte;

        while (num > 1) {
            // when we pick a random muur we want to avoid the borders getting eliminated
            // we kiezen een random muur maar willen verkomen dat de buitenmuur niet worden verwijdert
            
            randommuur = rand.nextInt(muren.size());
            BinnenMuur temp = (BinnenMuur) muren.get(randommuur);
            // we pakken 2 random tegels
            int roomA = temp.getCurrentRoom().getY() + temp.getCurrentRoom().getX() * breedte;
            int roomB = temp.getNextRoom().getY() + temp.getNextRoom().getX() * breedte;

            // check roomA and roomB to see if they are already members 
            // kijk of roomA en roomB al buren zijn
            if (find(roomA) != find(roomB)) {
                muren.remove(randommuur);
                unionRooms(find(roomA), find(roomB));
                temp.getCurrentRoom().getBuren().add(temp.getNextRoom());
                temp.getNextRoom().getBuren().add(temp.getCurrentRoom());
                Tegel tegel = temp.getCurrentRoom();
                Muur muur = (Muur) temp;
                if (temp != null) {
                    tegel.deleteMuur(muur);
                }
                Tegel tegel2 = temp.getNextRoom();
                if (temp != null) {
                    tegel2.deleteMuur(muur);
                }
                num--;
            }
        }
        tegels[0][0].setSpeler(speler);
        speler.setLocatie(tegels[0][0]);

        Random r = new Random();
        // toevoegen van objecten
        for (int i = 0; i < 3; i++) {
            int x = r.nextInt(18) + 1;
            int y = r.nextInt(18) + 1;
            if (tegels[x][y].getPersoon() == null && tegels[x][y].getUpgrade() == null) {
                tegels[x][y].setUpgrade(new Pistool());
            }
            x = r.nextInt(18) + 1;
            y = r.nextInt(18) + 1;
            if (tegels[x][y].getPersoon() == null && tegels[x][y].getUpgrade() == null) {
                tegels[x][y].setUpgrade(new Bazooka());
            }
            int waarde = r.nextInt(10) + 3;
            Valsspeler vsp;
            if (lvl > 1) {
                vsp = new Valsspeler(waarde, true);
            } else {
                vsp = new Valsspeler(waarde, false);
            }
            vsp.setDoolhof(tegels);
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
        for (int j = 0; j < 2; j++) {
            int x = r.nextInt(18) + 1;
            int y = r.nextInt(18) + 1;
            if (tegels[x][y].getPersoon() == null && tegels[x][y].getUpgrade() == null) {
                Mount m = new Mount(2, 10);
                tegels[x][y].setUpgrade(m);
                m.setLocatie(tegels[x][y]);
            }
        }
        Vriend vriend = new Vriend(spel);
        tegels[19][19].setPersoon(vriend);
        vriend.setLocatie(tegels[19][19]);
        Helper helper;
        if (lvl > 1) {
            helper = new Helper(vriend, true);
        } else {
            helper = new Helper(vriend, false);
        }
        helper.setDoolhof(tegels);
        helper.setLocatie(tegels[r.nextInt(15) + 1][r.nextInt(15) + 1]);
        helper.getLocatie().setPersoon(helper);

    }
    private int roomNumber = 0;


    private void maakKamer() {
        //het maakt een doolhof waar elk vak gesloten is dus elke muur bestaat.
        for (int i = 0; i < hoogte; i++) {
            for (int j = 0; j < breedte; j++) {
                tegels[i][j] = new Tegel(i, j);
                if (lvl == 3) {
                    // maak fog of war als het lvl 3 is
                    tegels[i][j].setTegelKleur(Color.BLACK);
                }
                if (i == 0) {
                    tegels[i][j].setNorth(new Buitenmuur(tegels[i][j]));
                } else {
                    tegels[i][j].setNorth((Muur) new BinnenMuur(tegels[i - 1][j], tegels[i][j]));
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
                    tegels[i][j].setWest((Muur) new BinnenMuur(tegels[i][j - 1], tegels[i][j]));
                    muren.add(tegels[i][j].getWest());
                    tegels[i][j].setWestBuur(tegels[i][j - 1]);
                    tegels[i][j - 1].setEastBuur(tegels[i][j]);
                    tegels[i][j - 1].setEast(tegels[i][j].getWest());
                }
                if (j == breedte - 1) {
                    tegels[i][j].setEast(new Buitenmuur(tegels[i][j]));
                }
                tegels[i][j].setRoomName(roomNumber++);// voor eenvoudige debuggen
            }
        }

        tegels[0][0].setRoomName(0);
        tegels[hoogte - 1][breedte - 1].setRoomName(hoogte * breedte);
    }

    @Override
    public void paintComponent(Graphics g) {
        // tekenen van elk tegel word hier gedaan
        // positie begint bij 50 en dan elk tegel is kamergrote
        int x_cord;
        int y_cord;
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
    }

    private void initSet(int elem) {
        set = new int[elem];
        // set allemaal init
        for (int i = 0; i < set.length; i++) {
            set[i] = -1;
        }
    }

    private void unionRooms(int roomA, int roomB) {
        // hier word gekeken of het buren zijn en zetten we in set[] als buren
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

    public Speler getSpeler() {
        return speler;
    }

    public void setSpeler(Speler speler) {
        this.speler = speler;
    }

    public void setTegels(Tegel[][] tegels) {
        this.tegels = tegels;
    }

    public Doolhof maakKopie() {
        // kopieren van de doolhof
        Doolhof kopie = new Doolhof((Speler) speler.maakKopie(), lvl,spel);
        Vriend vriend = new Vriend(spel);
        ArrayList<Helper> lijstHelper = new ArrayList<>();
        ArrayList<Valsspeler> lijstValsspeler = new ArrayList<>();
        Tegel[][] kopieTegels = new Tegel[breedte][hoogte];
        for (int i = 0; i < hoogte; i++) {
            for (int j = 0; j < breedte; j++) {
                kopieTegels[i][j] = (Tegel) tegels[i][j].maakKopie();
                if (tegels[i][j].getNorth() != null) {
                    kopieTegels[i][j].setNorth((Muur) tegels[i][j].getNorth().maakKopie());
                }
                if (tegels[i][j].getWest() != null) {
                    kopieTegels[i][j].setWest((Muur) tegels[i][j].getWest().maakKopie());
                }

                if (i == hoogte - 1) {
                    kopieTegels[i][j].setSouth(new Buitenmuur(tegels[i][j]));
                }
                if (j == breedte - 1) {
                    kopieTegels[i][j].setEast(new Buitenmuur(tegels[i][j]));
                }
                 // muren aan elkaar refereren
                if (j > 0) {
                   
                    kopieTegels[i][j].setWestBuur(kopieTegels[i][j - 1]);
                    kopieTegels[i][j - 1].setEastBuur(kopieTegels[i][j]);
                    if (kopieTegels[i][j].getWest() != null) {
                        kopieTegels[i][j - 1].setEast(kopieTegels[i][j].getWest());
                    }
                }
                if (i > 0) {
                    kopieTegels[i][j].setNorthBuur(kopieTegels[i - 1][j]);
                    kopieTegels[i - 1][j].setSouthBuur(kopieTegels[i][j]);
                    if (kopieTegels[i][j].getNorth() != null) {
                        kopieTegels[i - 1][j].setSouth(kopieTegels[i][j].getNorth());
                    }
                }
                // personen kopieren van elk tegel somige personen hebben speciaal attributen nodig
                if (tegels[i][j].getPersoon() != null) {
                    kopieTegels[i][j].setPersoon((Persoon) tegels[i][j].getPersoon().maakKopie());
                    kopieTegels[i][j].getPersoon().setLocatie(kopieTegels[i][j]);
                    kopieTegels[i][j].getPersoon().setDoolhof(kopieTegels);

                    if (kopieTegels[i][j].getPersoon() instanceof Vriend) {
                        vriend = (Vriend) kopieTegels[i][j].getPersoon();

                    }
                    if (kopieTegels[i][j].getPersoon() instanceof Helper) {
                        lijstHelper.add((Helper) kopieTegels[i][j].getPersoon());
                        if (tegels[i][j].getPersoon().getTimer() != null) {
                            kopieTegels[i][j].getPersoon().setTimer(new Timer(0, null));
                        }
                    }
                    if (kopieTegels[i][j].getPersoon() instanceof Valsspeler) {
                        lijstValsspeler.add((Valsspeler) kopieTegels[i][j].getPersoon());
                        if (tegels[i][j].getPersoon().getTimer() != null) {
                            kopieTegels[i][j].getPersoon().setTimer(new Timer(0, null));
                        }
                    }

                }
                if (tegels[i][j].getUpgrade() != null) {
                    kopieTegels[i][j].setUpgrade((Upgrade) tegels[i][j].getUpgrade().maakKopie());
                }

            }

        }
        // lopende personen hebben timers nodig die kan je niet zomaar kopieren
        for (Helper helper : lijstHelper) {
            helper.setEind(vriend);
            if (helper.getTimer() != null) {
                helper.zetTimer();
            }
        }
        for (Valsspeler valsspeler : lijstValsspeler) {
            if (valsspeler.getTimer() != null) {
                valsspeler.zetTimer();
            }
        }
        kopie.setTegels(kopieTegels);
        kopie.setSpeler(kopieTegels[0][0].getSpeler());
        kopieTegels[0][0].getSpeler().setLocatie(kopieTegels[0][0]);
        kopie.getSpeler().getPistool().setSpeler(kopie.getSpeler());
        kopie.getSpeler().getBazooka().setSpeler(kopie.getSpeler());
        return kopie;
    }
}// END OF CLASS 
