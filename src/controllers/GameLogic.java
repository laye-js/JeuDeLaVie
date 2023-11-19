package controllers;

import models.Cellule;

import java.util.Random;

public class GameLogic {
    private Cellule[][] grille;
    private Cellule[][] grilleAncienne;
    private int taille;
    private double densite;

    public GameLogic(int taille, double densite) {
        this.taille = taille;
        this.densite = densite;
        init();
    }

    private void init() {
        grille = new Cellule[taille][taille];
        grilleAncienne = new Cellule[taille][taille];
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                Cellule c = new Cellule(grilleAncienne, i, j, false);
                grille[i][j] = c;
                grilleAncienne[i][j] = (Cellule) c.clone();
            }
        }
        initHasard();
    }

    public void initHasard() {
        Random r = new Random();
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                if (r.nextDouble() < densite) {
                    grille[i][j].setVivante(true);
                    grilleAncienne[i][j].setVivante(true);
                }
            }
        }
    }

    public void change(int i, int j) {
        grille[i][j].setVivante(!grille[i][j].isVivante());
        grille[i][j].switchColor();
    }

    public void copieGrille() {
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                grilleAncienne[i][j].setVivante(grille[i][j].isVivante());
            }
        }
    }

    public void animGrille() {
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                grille[i][j].evoluer();
            }
        }
    }

    public Cellule[][] getGrille() {
        return grille;
    }
public int getTaille() {
    return taille;
}

}
