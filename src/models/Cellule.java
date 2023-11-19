package models;

import javax.swing.JButton;
import java.awt.Color;

public class Cellule implements Cloneable {
    static final public Color coulVersActive = new Color(0, 255, 255);
    static final public Color coulActive = new Color(240, 248, 255);
    static final public Color coulVersDesactive = new Color(205, 92, 92);
    static final public Color coulDesactive = new Color(25, 0, 0);

    static final int sousPopulation = 1;
    static final int surPopulation = 4;
    static final int minPopulationRegeneratrice = 3;
    static final int maxPopulationRegeneratrice = 3;

    private boolean etatPrecedent;
    private boolean vivante;
    private int x, y;
    private Cellule[][] grille;
    private JButton button;

    public Cellule(Cellule[][] grille, int x, int y, boolean vivante) {
        this.grille = grille;
        this.vivante = etatPrecedent = vivante;
        this.x = x;
        this.y = y;
    }

    public boolean isVivante() {
        return vivante;
    }

    public void setVivante(boolean vivante) {
        this.vivante = vivante;
    }

    public void setButton(JButton button) {
        this.button = button;
    }

    public Object clone() {
        Object o = null;
        try {
            o = super.clone();
        } catch (CloneNotSupportedException e) {
            System.err.println("Cellule impossible à cloner...");
        }
        return o;
    }

    public void evoluer() {
        int taille = grille.length;
        int nbVivantes = 0;

        for (int i = -1; i < 2; i++) {
            int xx = ((x + i) + taille) % taille;
            for (int j = -1; j < 2; j++) {
                if (i == 0 && j == 0) continue;
                int yy = ((y + j) + taille) % taille;
                if (grille[xx][yy].vivante) nbVivantes++;
            }
        }

        etatPrecedent = vivante;

        if (vivante && (nbVivantes <= sousPopulation || nbVivantes >= surPopulation)) {
            vivante = false;
        } else if (nbVivantes >= minPopulationRegeneratrice && nbVivantes <= maxPopulationRegeneratrice) {
            vivante = true;
        }
        switchColor();
    }

    public void switchColor() {
        Color c = null;
        if (etatPrecedent != vivante) {
            if (vivante) c = coulActive;
            else c = coulDesactive;
            button.setBackground(c);
        }
    }
}
