/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doolhof;

import java.awt.Color;
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
        tegels[5][5].setSpeler(speler);
        speler.setLocatie(tegels[5][5]);
        speler.setRichting(Richting.up);
        speler.moveUp();
        Tegel verwachte = tegels[5][5].getNorthBuur();
        if (verwachte != speler.getLocatie()) {
            fail("test speler omhoog niet gelukt");
        }
        if(speler.getLocatie()==tegels[5][5])
        {
            fail("speler is nog op de huidige plek");
        }
    }

    @Test
    public void testMoveLeft() {
        System.out.println("test Move left");
        Tegel[][] tegels = maakTestOmgeving();
        Speler speler = new Speler();
        tegels[5][5].setSpeler(speler);
        speler.setLocatie(tegels[5][5]);
        speler.setRichting(Richting.left);
        speler.moveLeft();
        Tegel verwachte = tegels[5][5].getWestBuur();
        if (verwachte != speler.getLocatie()) {
            fail("test speler naar links niet gelukt");
        }
        if(speler.getLocatie()==tegels[5][5])
        {
            fail("speler is nog op de huidige plek");
        }
    }
    @Test
    public void testMoveRightt() {
        System.out.println("test Move right");
        Tegel[][] tegels = maakTestOmgeving();
        Speler speler = new Speler();
        tegels[5][5].setSpeler(speler);
        speler.setLocatie(tegels[5][5]);
        speler.setRichting(Richting.right);
        speler.moveRight();
        Tegel verwachte = tegels[5][5].getEastBuur();
        if (verwachte != speler.getLocatie()) {
            fail("test speler naar links niet gelukt");
        }
        if(speler.getLocatie()==tegels[5][5])
        {
            fail("speler is nog op de huidige plek");
        }
    }
    @Test
    public void testMoveDown() {
        System.out.println("test Move Down");
        Tegel[][] tegels = maakTestOmgeving();
        Speler speler = new Speler();
        tegels[5][5].setSpeler(speler);
        speler.setLocatie(tegels[5][5]);
        speler.setRichting(Richting.down);
        speler.moveDown();
        Tegel verwachte = tegels[5][5].getSouthBuur();
        if (verwachte != speler.getLocatie()) {
            fail("test speler naar links niet gelukt");
        }
        if(speler.getLocatie()==tegels[5][5])
        {
            fail("speler is nog op de huidige plek");
        }
    }
    @Test
    public void testMoveUpMuur() {
        System.out.println("test Move Up against wall");
        Tegel[][] tegels = maakTestOmgeving();
        Speler speler = new Speler();
        tegels[5][5].setSpeler(speler);
        tegels[5][5].setNorth(new BinnenMuur(null, null));
        speler.setLocatie(tegels[5][5]);
        speler.setRichting(Richting.up);
        speler.moveUp();
        Tegel verwachte = tegels[5][5];
        if (verwachte != speler.getLocatie()) {
            fail("test speler loopt door muur");
        }
    }
    @Test
    public void algoritmeTest1() {
        System.out.println("algoritmetest");
        Tegel[][] tegels = maakTestOmgeving();
        Speler speler = new Speler();
        Vriend vriend= new Vriend();
        tegels[5][4].setPersoon(vriend);
        tegels[5][5].setSpeler(speler);
        speler.setLocatie(tegels[5][5]);
        speler.setRichting(Richting.up);
        speler.moveUp();
        if(vriend!=null){
            fail("vriend bestaat nog");
        }
    }
    
    
    

    private Tegel[][] maakTestOmgeving() {
        Tegel[][] tegels = new Tegel[10][10];
        for (int i = 0; i < tegels.length; i++) {
            for (int j = 0; j < tegels.length; j++) {
                tegels[i][j] = new Tegel(i, j);
                tegels[i][j].setTegelKleur(Color.GRAY);
                if (i == 0) {
                    tegels[i][j].setNorth(new Buitenmuur(tegels[i][j]));
                }
                if (j == 0) {
                    tegels[i][j].setWest(new Buitenmuur(tegels[i][j]));
                }
                if (i ==  tegels.length- 1) {
                    tegels[i][j].setSouth(new Buitenmuur(tegels[i][j]));
                }
                if (j == tegels.length- 1) {
                    tegels[i][j].setEast(new Buitenmuur(tegels[i][j]));
                }
                
                    
                
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
