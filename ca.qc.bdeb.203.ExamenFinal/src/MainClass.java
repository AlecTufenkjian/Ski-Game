
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alect
 */
public class MainClass {

public static final int LARGEUR = 800, HAUTEUR = 600; // Constantes publiques

    public static void main(String[] args) {
        try {
            AppGameContainer app = new AppGameContainer(new Jeu("Final Ski Challenge"));
            app.setDisplayMode(LARGEUR, HAUTEUR, false);
            app.setShowFPS(true);
            app.setTargetFrameRate(60);
            //app.setVSync(true);
            app.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }   
    
}
