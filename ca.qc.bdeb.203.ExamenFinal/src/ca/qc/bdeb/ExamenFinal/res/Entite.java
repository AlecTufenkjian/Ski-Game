/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.ExamenFinal.res;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author alect
 */
public abstract class Entite {
    protected int x, y, width, height;
    protected Image image;


    public Entite(int x, int y, int width, int height, String imagepath) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        try {
            image = new Image(imagepath);
        } catch (SlickException e) {
            System.out.println("Image non trouvée pour " + getClass());
        }
    }
    
    public Entite(int x, int y, int width, int height, SpriteSheet spriteSheet) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = spriteSheet.getSubImage(0, 0);
    }

    public int getX() {
        return x;
    }   
    public void setX(int x) {
        this.x = x;
    }
    
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }

    public Image getImage() {
        return image;
    }
    public void setImage(Image image) {
        this.image = image;
    }
    
    public Rectangle getRectangle() {
        return new Rectangle(x, y, width, height);
    }
    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }
  
}

