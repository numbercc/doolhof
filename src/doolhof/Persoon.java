/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doolhof;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.swing.Timer;

/**
 *
 * @author Montoo
 */
public abstract class Persoon implements MaakKopie {

    private Tegel locatie;
    private Tegel[][] doolhof;
    private Timer timer;
    private ArrayList<Tegel> route = new ArrayList<>();

    @Override
    public abstract Object maakKopie();

    public abstract void teken(int kamerGrote, int x, int y, Graphics g);

    public abstract void wordGeraakt(Speler speler);

    public void zetDoel() {
        // persoon gaat ergens naar toe lopen hier wordt beslist waar er is voor gekozten dat hij niet in de eerste stukje mag lopen dus de matrix van 0 tot 5
        int x = (int) (Math.random() * 14 + 5);
        int y = (int) (Math.random() * 14 + 5);
        route = kortsteRoute(locatie, doolhof[x][y]);
    }

    public void beweeg() {
        // hier wordt er gechecked of hij wel mag lopen hij mag niet tegen andere personen lopen 
            if (route != null) {
                if (route.size() > 0) {
                    if (route.get(0).getSpeler() != null) {
                        wordGeraakt(route.get(0).getSpeler());
                    } else {
                        if (route.get(0).getPersoon() == null) {
                            Tegel temp = locatie;
                            locatie = route.get(0);
                            temp.setPersoon(null);
                            locatie.setPersoon(this);
                            route.remove(0);
                            return;
                        }
                    }

                }
            }
            zetDoel();
    }

    public void verwijderpersoon() {
        //persoon verwijdert en timer moet worden gestopt als het is gebruikt
        locatie.setPersoon(null);
        if (timer != null) {
            timer.stop();
        }
    }

    public Tegel[][] getDoolhof() {
        return doolhof;
    }

    public void setDoolhof(Tegel[][] doolhof) {
        this.doolhof = doolhof;
    }

    public Tegel getLocatie() {
        return locatie;
    }

    public void setLocatie(Tegel locatie) {
        this.locatie = locatie;
    }

    public ArrayList<Tegel> kortsteRoute(Tegel huidige, Tegel doel) {
        // kortste route bepalen doormiddel van dijkstra 
        Tegel[][] doolhof = getDoolhof();
        ArrayList<Tegel> route;
        Map<Tegel, Integer> afstand = new HashMap<>();
        Map<Tegel, Tegel> vorige = new HashMap<>();
        ArrayList<Tegel> nietBezocht = new ArrayList<>();
        Tegel locatie = getLocatie();
        afstand.put(locatie, 0);
        vorige.put(locatie, null);
        // elke afstand word het maximale als init.
        // en berbinding met vorige word ook init met als niks
        for (int i = 0; i < doolhof.length; i++) {
            for (int j = 0; j < doolhof.length; j++) {
                Tegel v = doolhof[i][j];
                if (v != locatie) {
                    afstand.put(v, Integer.MAX_VALUE);
                    vorige.put(v, null);
                }
                nietBezocht.add(v);
            }
        }
        // hierin word elke tegel verbonden met de korste route naar de beginpunt
        // beginpunt is locatie helper.  met de vorige map pak je steeds de tegel 
        //die bij hoort tot je bij de begin locatie ben
        while (nietBezocht.size() > 0) {
            Tegel u = null;
            for (Tegel tegel : nietBezocht) {
                if (u == null || afstand.get(tegel) < afstand.get(u)) {
                    u = tegel;
                }

            }
            if (u == doel) {
                break;
            }
            // verwijder bezochte tegels
            nietBezocht.remove(u);
            // als tegels kunnen meedere wegen hebben. 
            // om de korste weg te kiezen word er naar de afstand gekeken.
            // hier word de graphische werkelijke afstand gebruik
            // om een afstand aan te geven
            for (Tegel tegel : u.getloopbaarBuren()) {
                int alt = afstand.get(u) + u.afstandNaar(tegel);
                if (alt < afstand.get(tegel)) {
                    afstand.put(tegel, alt);
                    vorige.put(tegel, u);
                }
            }

        }
        // je heb je doel(vriend) gevonden. 
        // het is niet nodig om verder te gaan.
        if (vorige.get(doel) == null) {
            return null;
        }
        route = new ArrayList<>();
        huidige = doel;
        // alles netjes van doel tot huidige locatie in een array gezet
        while (huidige != null) {
            route.add(huidige);
            huidige = vorige.get(huidige);
        }
        // als je het kijk in het array is alles van doel tot huidige locatie.
        // we willen graag dat je van huidige locatie naar je doel gaat.
        // dus draaien we het om.
        Collections.reverse(route);
        // huidige locatie hebben we niet nodig
        route.remove(0);
        return route;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public ArrayList<Tegel> getRoute() {
        return route;
    }

    public void setRoute(ArrayList<Tegel> route) {
        this.route = route;
    }

    public void zetTimer() {
        timer = new Timer(400, new ActionListener() {
            // hier word timer gezet als het nodig is
            @Override
            public void actionPerformed(ActionEvent ae) {
                beweeg();
            }

        });
        timer.start();
    }
}
