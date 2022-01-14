/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.ExamenFinal.res;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author alect
 */
public class Skieur extends Entite{
    
    private int direction = 3;
    private Image imageDroit;
    private Image imageGauche;
    
    public Skieur(int x, int y, int width, int height, String imagepath) throws SlickException {
        super(x, y, width, height, imagepath);
        imageDroit = new Image("/images/skieurd.png");
        imageGauche = new Image("/images/skieurg.png");
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public Image getImageDroit() {
        return imageDroit;
    }

    public Image getImageGauche() {
        return imageGauche;
    }
    
    
    
    
    
    
    
    
}
