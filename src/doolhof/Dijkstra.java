/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doolhof;

import java.util.LinkedList;

/**
 *
 * @author Chie-cheung
 */
public class Dijkstra {

    private int afstand[];
    private Doolhof doolhof;
    public Dijkstra(Doolhof doolhof) {
        this.doolhof = doolhof;
    }
    public LinkedList<Tegel> kortsteRoute(Tegel start,Tegel eind) {
        LinkedList<Tegel> route = new LinkedList<>();
        Tegel huidige=start;
        while(huidige.equals(eind)){
            
        }
        return route;
    }
}
