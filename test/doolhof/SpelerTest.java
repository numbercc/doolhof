/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doolhof;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Chie-cheung
 */
public class SpelerTest {

    public SpelerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testMoveUp() {
        System.out.println("test Move up");
        Tegel[][] tegels = maakTestOmgeving();
        Speler speler = new Speler();
        tegels[2][2].setSpeler(speler);
        speler.setLocatie(tegels[2][2]);
        speler.moveUp();
        Tegel verwachte = tegels[2][2].getNorthBuur();
        if (verwachte != speler.getLocatie()) {
            fail("test speler omhoog niet gelukt");
        }
        if(speler.getLocatie()==tegels[2][2])
        {
            fail("speler is nog op de huidige plek");
        }
    }

    @Test
    public void testMoveLeft() {
        System.out.println("test Move left");
        Tegel[][] tegels = maakTestOmgeving();
        Speler speler = new Speler();
        tegels[2][2].setSpeler(speler);
        speler.setLocatie(tegels[2][2]);
        speler.moveLeft();
        Tegel verwachte = tegels[2][2].getWestBuur();
        if (verwachte != speler.getLocatie()) {
            fail("test speler naar links niet gelukt");
        }
        if(speler.getLocatie()==tegels[2][2])
        {
            fail("speler is nog op de huidige plek");
        }
    }
    @Test
    public void testMoveRightt() {
        System.out.println("test Move right");
        Tegel[][] tegels = maakTestOmgeving();
        Speler speler = new Speler();
        tegels[2][2].setSpeler(speler);
        speler.setLocatie(tegels[2][2]);
        speler.moveRight();
        Tegel verwachte = tegels[2][2].getEastBuur();
        if (verwachte != speler.getLocatie()) {
            fail("test speler naar links niet gelukt");
        }
        if(speler.getLocatie()==tegels[2][2])
        {
            fail("speler is nog op de huidige plek");
        }
    }
    @Test
    public void testMoveDown() {
        System.out.println("test Move Down");
        Tegel[][] tegels = maakTestOmgeving();
        Speler speler = new Speler();
        tegels[2][2].setSpeler(speler);
        speler.setLocatie(tegels[2][2]);
        speler.moveDown();
        Tegel verwachte = tegels[2][2].getSouthBuur();
        if (verwachte != speler.getLocatie()) {
            fail("test speler naar links niet gelukt");
        }
        if(speler.getLocatie()==tegels[2][2])
        {
            fail("speler is nog op de huidige plek");
        }
    }
    @Test
    public void testMoveUpMuur() {
        System.out.println("test Move Up against wall");
        Tegel[][] tegels = maakTestOmgeving();
        Speler speler = new Speler();
        tegels[2][2].setSpeler(speler);
        tegels[2][2].setNorth(new BinnenMuur(null, null));
        speler.setLocatie(tegels[2][2]);
        speler.moveUp();
        Tegel verwachte = tegels[2][2];
        if (verwachte != speler.getLocatie()) {
            fail("test speler loopt door muur");
        }
    }
    
//    @Test
//    public void testSpelerRaaktValsspeler() {
//        System.out.println("test Move against a valsspeler");
//        Tegel[][] tegels = maakTestOmgeving();
//        Speler speler = new Speler();
//        Valsspeler vsp = new Valsspeler(tegels, 2);
//        tegels[2][2].setSpeler(speler);
//        tegels[3][2].setPersoon(vsp);
//        Boolean verwacht = false;
//        if(speler.getLocatie() == vsp.getLocatie()) {
//            verwacht = true;
//        }
//        if (verwacht) {
//            fail("test speler loopt door vsp");
//        }
//    }

    private Tegel[][] maakTestOmgeving() {
        Tegel[][] tegels = new Tegel[5][5];
        for (int i = 0; i < tegels.length; i++) {
            for (int j = 0; j < tegels.length; j++) {
                tegels[i][j] = new Tegel(i, j);
                if (i > 0) {
                    tegels[i][j].setWestBuur(tegels[i - 1][j]);
                    tegels[i - 1][j].setEastBuur(tegels[i][j]);
                }
                if (j > 0) {
                    tegels[i][j].setNorthBuur(tegels[i][j - 1]);
                    tegels[i][j - 1].setSouthBuur(tegels[i][j]);
                }


            }


        }
        return tegels;
    }
}
