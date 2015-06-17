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
        int x = (int) (Math.random() * 14 + 5);
        int y = (int) (Math.random() * 14 + 5);
        route = kortsteRoute(locatie, doolhof[x][y]);
    }

    public void beweeg() {
        try {
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
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("uugh");
        }
    }

    public void verwijderpersoon() {
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
        Tegel[][] doolhof = getDoolhof();
        ArrayList<Tegel> route;
        Map<Tegel, Integer> afstand = new HashMap<>();
        Map<Tegel, Tegel> vorige = new HashMap<>();
        ArrayList<Tegel> nietBezocht = new ArrayList<>();
        Tegel locatie = getLocatie();
        afstand.put(locatie, 0);
        vorige.put(locatie, null);
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
            nietBezocht.remove(u);
            for (Tegel tegel : u.getloopbaarBuren()) {
                int alt = afstand.get(u) + u.afstandNaar(tegel);
                if (alt < afstand.get(tegel)) {
                    afstand.put(tegel, alt);
                    vorige.put(tegel, u);
                }
            }

        }
        if (vorige.get(doel) == null) {
            return null;
        }
        route = new ArrayList<>();
        huidige = doel;
        while (huidige != null) {
            route.add(huidige);
            huidige = vorige.get(huidige);
        }
        Collections.reverse(route);
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
        timer = new Timer(250, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                beweeg();
            }

        });
        timer.start();
    }
}
