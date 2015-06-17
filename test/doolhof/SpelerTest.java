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
    public void statementCoverageMount() {
        // Statement Coverage: Mount
        // Fysiek testgeval 1
        System.out.println("Statement coverage test 1: Mount");
        Tegel[][] tegels = maakTestOmgeving();
        Speler speler = new Speler();
        Mount mount = new Mount(2, 10);
        speler.setScore(0);
        tegels[5][5].setSpeler(speler);
        tegels[5][4].setUpgrade(mount);
        speler.setLocatie(tegels[5][5]);
        speler.setRichting(Richting.up);
        speler.moveUp();
        double verwachteScore = 1.5;
        if(speler.getLocatie() != tegels[5][4]) {
            fail("speler is niet bewogen");
        }
        speler.moveUp();
        if(speler.getMount() == null) {
            fail("speler heeft geen mount");
        }
        
        if(verwachteScore != speler.getStappen())
        {
            fail("speler score niet gelijk aan verwachte score");
        }
    }
    
    @Test
    public void algoritmeTest2() {
        // hierin word de formele fysieke algoritme getest van testgeval 1
        // speler raakt eerst geen persoon en daarna vriend.
        System.out.println("algoritmetest2");
        Spel spel=new Spel();
        
        Tegel[][] tegels = maakTestOmgeving();
        Speler speler = new Speler();
        spel.setSpeler(speler);
        spel.beginspel();
        Vriend vriend= new Vriend(spel);
        tegels[5][3].setPersoon(vriend);
        tegels[5][5].setSpeler(speler);
        speler.setLocatie(tegels[5][5]);
        speler.setRichting(Richting.up);
        speler.moveUp();
        if(speler.getLocatie()!=tegels[5][4]){
            fail("speler is niet bewogen");
        }
        speler.moveUp();
        if(spel.getLevelInt()!=2){
            fail("level niet gehaald");
        }
    }
    @Test
    public void algoritmeTest1() {
        // hierin word de formele fysieke algoritme getest van testgeval 1
        // speler raakt eerst geen persoon en daarna vriend.
        System.out.println("algoritmetest1");
        Spel spel=new Spel();
        
        Tegel[][] tegels = maakTestOmgeving();
        Speler speler = new Speler();
        spel.setSpeler(speler);
        spel.beginspel();
        Vriend vriend= new Vriend(spel);
        tegels[5][3].setPersoon(vriend);
        tegels[5][5].setSpeler(speler);
        speler.setLocatie(tegels[5][4]);
        speler.setRichting(Richting.up);
        speler.moveUp();
        if(spel.getLevelInt()!=2){
            fail("level niet gehaald");
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
