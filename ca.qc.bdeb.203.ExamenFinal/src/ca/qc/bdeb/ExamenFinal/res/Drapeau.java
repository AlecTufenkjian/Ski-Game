/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.ExamenFinal.res;

/**
 *
 * @author alect
 */
public class Drapeau extends Entite implements Bougeable{
    
    public Drapeau(int x, int y, int width, int height, String imagepath) {
        super(x, y, width, height, imagepath);
    }

    @Override
    public void bouger() {
        this.setY(this.getY() - 1);
    }
    
}
