
import ca.qc.bdeb.ExamenFinal.res.*;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Color;

import java.util.ArrayList;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alect
 */
public class Jeu extends BasicGame{

    private final int LARGEUR_SKIEUR = 43;
    private final int HAUTEUR_SKIEUR = 39;
    
    private final int LARGEUR_ARBRE = 95;
    private final int HAUTEUR_ARBRE = 97;
    
    private final int LARGEUR_DRAPEAU = 14;
    private final int HAUTEUR_DRAPEAU = 32;
    
    
    private ArrayList<Entite> listeEntite = new ArrayList<>();
    
    private Skieur skieur;
    
    private int tempsPasseApresLaDerniereGeneration = 0;
    private static long tempsInitial = System.currentTimeMillis();
    
    private int scoreDrapeau = 0;
    private int scoreTemps = 0;
    private int scoreTotal = 0;

    private boolean estFini = false;

    public Jeu(String nomJeu) {
        super(nomJeu);
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        skieur = new Skieur(200, 50, LARGEUR_SKIEUR, HAUTEUR_SKIEUR, "images/skieurd.png");
        listeEntite.add(skieur);
    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
        if(!estFini){
            ajusterPositionSkieur();
            genererArbresEtDrapeaux();

            for (Entite entite : listeEntite) {
                if (entite instanceof Bougeable) {
                    ((Bougeable) entite).bouger();
                }
            }

            gererCollisions();  
            
            scoreTemps = (int)((System.currentTimeMillis() - tempsInitial)/10);
            
            scoreTotal = scoreDrapeau + scoreTemps;
            
            supprimerEntitesHorsEcran();
        }       
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        g.setBackground(Color.white);
        
        for(Entite entite: listeEntite){
            g.drawImage(entite.getImage(), entite.getX(), entite.getY());
        }

        g.setColor(Color.black);
        g.drawString("Points: " + Integer.toString(scoreTotal), 675,  10);
    }

    @Override
    public void keyPressed(int key, char c) {
        switch (key) {
            case Input.KEY_A:
                skieur.setDirection(1);
                skieur.setImage(skieur.getImageGauche());
                break;
            case Input.KEY_LEFT:
                skieur.setDirection(1);
                skieur.setImage(skieur.getImageGauche());
                break;
            case Input.KEY_D:
                skieur.setDirection(3);
                skieur.setImage(skieur.getImageDroit());
                break;
            case Input.KEY_RIGHT:
                skieur.setDirection(3);
                skieur.setImage(skieur.getImageDroit());
                break;
        }
    }
    
    private void ajusterPositionSkieur(){
        switch (skieur.getDirection()) {
            case 1:
                skieur.setX(skieur.getX() - 1);
                break;
            case 3:
               skieur.setX(skieur.getX() + 1);
               break;
        }
        
        if(skieur.getX() < 0){
            skieur.setImage(skieur.getImageDroit());
            skieur.setDirection(3);
        }
        if(skieur.getX() > 800 - skieur.getWidth()){
            skieur.setImage(skieur.getImageGauche());
            skieur.setDirection(1);
        }
    }
    
    private void genererArbresEtDrapeaux(){
        Random rnd = new Random();
        
        if(tempsPasseApresLaDerniereGeneration > rnd.nextInt(100) + 100){
            int nbArbres = rnd.nextInt(2) + 1;
            int nbDrapeaux = rnd.nextInt(2) + 1;
            for(int i = 0; i < nbArbres; i++){
                listeEntite.add(new Arbre(rnd.nextInt(MainClass.LARGEUR - LARGEUR_ARBRE), MainClass.HAUTEUR, LARGEUR_ARBRE, HAUTEUR_ARBRE, "images/arbre.png"));
            }
            for(int i = 0; i < nbDrapeaux; i++){
                listeEntite.add(new Drapeau(rnd.nextInt(MainClass.LARGEUR - LARGEUR_DRAPEAU), MainClass.HAUTEUR, LARGEUR_DRAPEAU, HAUTEUR_DRAPEAU, "images/drapeau.png"));
            }
            tempsPasseApresLaDerniereGeneration = 0;
        }else{
            tempsPasseApresLaDerniereGeneration++;
        }
        
    }
    
    private int determinerTypeCollision(Entite entite1, Entite entite2){
        int typeCollision = 0;
        
        if (entite1 instanceof Skieur && entite2 instanceof Drapeau) {
            typeCollision = 1;
        }

        if (entite2 instanceof Drapeau && entite1 instanceof Skieur) {
            typeCollision = 1;
        }
        
        if (entite1 instanceof Skieur && entite2 instanceof Arbre) {
            typeCollision = 2;
        }

        if (entite2 instanceof Arbre && entite1 instanceof Skieur) {
            typeCollision = 2;
        }
        return typeCollision;        
    }
    
    private void gererCollisions() {
        ArrayList<Entite> listeTemp = new ArrayList<>();
        
        for (int i = 0; i < listeEntite.size(); i++) {
            for (int j = i; j < listeEntite.size(); j++) {
                Entite entite1 = listeEntite.get(i);
                Entite entite2 = listeEntite.get(j);
                if (sontEnCollision(entite1, entite2)) {
                    int typeCollision = determinerTypeCollision(entite1, entite2);
                    switch (typeCollision) {
                        case 1:
                            gererCollisionSkieurDrapeau(listeTemp, entite1, entite2);
                            break;
                        case 2:
                            gererCollisionSkieurArbre();
                            break;
                    }
                }
            }
        }
        
        listeEntite.removeAll(listeTemp);
        listeTemp.clear();
    }
    
    private void gererCollisionSkieurDrapeau(ArrayList<Entite> listeTemp, Entite entite1, Entite entite2){
        if(entite1 instanceof Drapeau){
            listeTemp.add(entite1);
        }else{
            listeTemp.add(entite2);
        }
        scoreDrapeau = scoreDrapeau + 50;
    }
    
    private void gererCollisionSkieurArbre(){
        estFini = true;
    }
    
    private boolean sontEnCollision(Entite entite1, Entite entite2){
        return entite1.getRectangle().intersects(entite2.getRectangle());        
    }
    
    private void supprimerEntitesHorsEcran(){
        ArrayList<Entite> listeTemp = new ArrayList<>();
        
        for (int i = 0; i < listeEntite.size(); i++){
            if(listeEntite.get(i).getY() < 0 - LARGEUR_SKIEUR){
                listeTemp.add(listeEntite.get(i));
            }
        }
        
        listeEntite.removeAll(listeTemp);
        listeTemp.clear();
    }
    
       
    
}
