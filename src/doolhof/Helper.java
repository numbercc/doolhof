/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doolhof;

import java.awt.Color;
import java.awt.Graphics;
import java.util.*;

/**
 *
 * @author Montoo
 */
public class Helper extends Persoon {

    private Tegel eind;

    public Helper(Tegel[][] tegel, Tegel vriendLocatie) {
        this.doolhof = tegel;
        eind = vriendLocatie;
    }
    private Tegel[][] doolhof;
    private Speler speler;

    @Override
    public void teken(int kamerGrote, int x, int y, Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillOval(x + kamerGrote / 4, y + kamerGrote / 4, kamerGrote / 2, kamerGrote / 2);
        g.setColor(Color.BLACK);
    }

    @Override
    public void wordGeraakt(Speler speler) {
        kortsteRoute();
        super.verwijderpersoon();
    }

//    public void kortsteRoute() {
//        LinkedList<Tegel> route = new LinkedList<>();
//        LinkedList<Tegel> knooppunt = new LinkedList<>();
//        LinkedList<Integer> richting = new LinkedList<>();
//
//        ArrayList<Tegel> nietBezocht = new ArrayList<>();
//        for (int i = 0; i < doolhof.length; i++) {
//            for (int j = 0; j < doolhof.length; j++) {
//                nietBezocht.add(doolhof[i][j]);
//            }
//        }
//
//        route.add(super.getLocatie());
//        super.getLocatie().setTegelKleur(Color.yellow);
//        while (nietBezocht.size() > 0) {
//            if (route.getLast().getloopbaarBuren().size() == 1) {
//                //verwijder alle tegels tot de laaste knooppunt
//                int i = route.indexOf(knooppunt.getLast());
//                while (i < route.size()) {
//                    route.remove(i + 1);
//                }
//                //voeg een andere tegel toe aan route 
//                boolean andereRoute = false;
//                while (route.size() > 0 || !andereRoute) {
//                    if (richting.getLast() < route.getLast().getloopbaarBuren().size()) {
//                        richting.set(richting.size() - 1, richting.getLast() + 1);
//                        route.add(knooppunt.getLast().getloopbaarBuren().get(richting.getLast()));
//                        andereRoute = true;
//                    } //laatste knooppunt heeft geen andere mogelijkheid dus gebruiken we de knooppunt nog eerder
//                    else {
//                        if (knooppunt.size() > 1) {
//                            knooppunt.removeLast();
//                            richting.removeLast();
//                            i = route.indexOf(knooppunt.getLast());
//                            while (i < route.size()) {
//                                route.remove(i + 1);
//                            }
//                            richting.set(richting.size() - 1, richting.getLast() + 1);
//                            route.add(knooppunt.getLast().getloopbaarBuren().get(richting.getLast()));
//                            andereRoute = true;
//                        } else {
//                            return;
//                        }
//
//                    }
//                }
//
//
//            }
//            else if(route.getLast().getloopbaarBuren().size() >1){
//                
//                
//            }
//        }
//    }
    public void kortsteRoute() {
        ArrayList<Tegel> route;
        Map<Tegel, Integer> afstand = new HashMap<>();
        Map<Tegel, Tegel> vorige = new HashMap<>();
        ArrayList<Tegel> nietBezocht = new ArrayList<>();
        Tegel locatie = super.getLocatie();
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
            if (u == eind) {
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
        if (vorige.get(eind) == null) {
            return;
        }
        route=new ArrayList<>();
        Tegel huidige=eind;
        while(huidige!=null){
            route.add(huidige);
            huidige=vorige.get(huidige);
        }
        routeKleuren(route);


    }

    public void routeKleuren(ArrayList<Tegel> route) {
        for (Tegel tegel : route) {
            tegel.setTegelKleur(Color.yellow);
        }
    }
}
