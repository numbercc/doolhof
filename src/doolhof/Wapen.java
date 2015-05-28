/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doolhof;

/**
 *
 * @author Chie-cheung
 */
public abstract class Wapen {
    private int bullet=0;
    public abstract void  schieten();
    public abstract void updateScore();
    public void minderKogels(){
        bullet=bullet-1;
    }

    public int getBullet() {
        return bullet;
    }

    public void setBullet(int bullet) {
        this.bullet = bullet;
        updateScore();
    }
    
}
