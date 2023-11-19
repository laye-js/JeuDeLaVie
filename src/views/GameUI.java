package views;

import javax.swing.*;
import controllers.GameLogic;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

public class GameUI extends JFrame {
    private static final long serialVersionUID = 1L;
    private GameLogic gameLogic;
    public static JButton[][] buttons;
    private int taille = 50;
    private Timer timer;
    private int tempo = 500;

    private JButton demarrerBtn, arreterBtn, pauseBtn, resetBtn, guideBtn, reglesBtn, changerVitesseBtn, enregistrerBtn, chargerBtn;
    private JTextField vitesseField;

    private final String reglesText = "Le Jeu de la Vie est un automate cellulaire imaginé par John Conway.\n" +
            "Les cellules vivantes évoluent à chaque étape selon les règles suivantes :\n" +
            "1. Toute cellule vivante avec moins de 2 voisins vivants meurt, comme si elle était causée par la sous-population.\n" +
            "2. Toute cellule vivante avec 2 ou 3 voisins vivants survit à la prochaine génération.\n" +
            "3. Toute cellule vivante avec plus de 3 voisins vivants meurt, comme si elle était causée par la surpopulation.\n" +
            "4. Toute cellule morte avec exactement 3 voisins vivants devient une cellule vivante, comme par reproduction.\n";

    private final String guideText = "Guide:\n- Cliquez sur une cellule pour changer son état.\n- Utilisez le bouton Reset pour réinitialiser la grille.";

    public GameUI() {
        gameLogic = new GameLogic(taille, 0.1);
        buttons = new JButton[taille][taille];
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());

        // Ajout de la grille
        JPanel panel = new JPanel(new GridLayout(taille, taille));
        panel.setBackground(Color.WHITE);  // Définir la couleur de fond du panneau

        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setBackground(Color.WHITE);
                buttons[i][j].setBorderPainted(false);
                buttons[i][j].addActionListener(new CellButtonListener(i, j));
                gameLogic.getGrille()[i][j].setButton(buttons[i][j]);

                panel.add(buttons[i][j]);
            }
        }

        add(panel, BorderLayout.CENTER);

        // Ajout des boutons dans un panneau séparé avec BorderLayout
        JPanel boutonsPanel = new JPanel(new FlowLayout());

        demarrerBtn = new JButton("Démarrer");
        demarrerBtn.addActionListener(e -> demarrer());
        boutonsPanel.add(demarrerBtn);

        arreterBtn = new JButton("Arrêter");
        arreterBtn.addActionListener(e -> arreter());
        boutonsPanel.add(arreterBtn);

        pauseBtn = new JButton("Pause");
        pauseBtn.addActionListener(e -> pause());
        boutonsPanel.add(pauseBtn);

        resetBtn = new JButton("Reset");
        resetBtn.addActionListener(e -> reset());
        boutonsPanel.add(resetBtn);

        guideBtn = new JButton("Guide");
        guideBtn.addActionListener(e -> guide());
        boutonsPanel.add(guideBtn);

        reglesBtn = new JButton("Règles");
        reglesBtn.addActionListener(e -> regles());
        boutonsPanel.add(reglesBtn);

        changerVitesseBtn = new JButton("Changer Vitesse");
        changerVitesseBtn.addActionListener(e -> changerVitesse());
        boutonsPanel.add(changerVitesseBtn);

        vitesseField = new JTextField(5);
        boutonsPanel.add(new JLabel("Vitesse:"));
        boutonsPanel.add(vitesseField);

        enregistrerBtn = new JButton("Enregistrer");
        enregistrerBtn.addActionListener(e -> enregistrer());
        boutonsPanel.add(enregistrerBtn);

        chargerBtn = new JButton("Charger");
        chargerBtn.addActionListener(e -> charger());
        boutonsPanel.add(chargerBtn);

        add(boutonsPanel, BorderLayout.NORTH);

        JLabel titleLabel = new JLabel("Galsen Coding Challenge: Le Jeu de la vie", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.SOUTH);

        guideBtn.setBackground(Color.GREEN);
        guideBtn.setOpaque(true);
        guideBtn.setBorderPainted(false);
        reglesBtn.setBackground(Color.GREEN);
        reglesBtn.setOpaque(true);
        reglesBtn.setBorderPainted(false);
        demarrerBtn.setBackground(Color.GREEN);
        demarrerBtn.setOpaque(true);
        demarrerBtn.setBorderPainted(false);
        arreterBtn.setBackground(Color.RED);
        arreterBtn.setOpaque(true);
        arreterBtn.setBorderPainted(false);
        pauseBtn.setBackground(Color.YELLOW);
        pauseBtn.setOpaque(true);
        pauseBtn.setBorderPainted(false);
        resetBtn.setBackground(Color.ORANGE);
        resetBtn.setOpaque(true);
        resetBtn.setBorderPainted(false);
        changerVitesseBtn.setBackground(Color.CYAN);
        changerVitesseBtn.setOpaque(true);
        changerVitesseBtn.setBorderPainted(false);
        enregistrerBtn.setBackground(Color.MAGENTA);
        enregistrerBtn.setOpaque(true);
        enregistrerBtn.setBorderPainted(false);
        chargerBtn.setBackground(Color.MAGENTA);
        chargerBtn.setBackground(Color.MAGENTA);
        chargerBtn.setBorderPainted(false);

        timer = new Timer(tempo, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                gameLogic.copieGrille();
                gameLogic.animGrille();
                updateUI();
            }
        });
        timer.start();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 1000); 
        setTitle("Jeu de la vie");
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void demarrer() {
        timer.setDelay(tempo);
        timer.start();
    }

    private void arreter() {
        timer.stop();
    }

    private void pause() {
        timer.stop();
    }

    private void reset() {
        timer.stop();
        gameLogic.initHasard();
        updateUI();
    }

    private void guide() {
        JOptionPane.showMessageDialog(null, guideText, "Guide", JOptionPane.INFORMATION_MESSAGE);
    }

    private void regles() {
        JOptionPane.showMessageDialog(null, reglesText, "Règles", JOptionPane.INFORMATION_MESSAGE);
    }

    private void changerVitesse() {
        try {
            int nouvelleVitesse = Integer.parseInt(vitesseField.getText());
            if (nouvelleVitesse > 0) {
                tempo = nouvelleVitesse;
                timer.setDelay(tempo);
            } else {
                JOptionPane.showMessageDialog(null, "Veuillez entrer une valeur de vitesse valide (entier positif).", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Veuillez entrer une valeur numérique valide pour la vitesse.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void enregistrer() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("config.txt"));
            writer.write(Integer.toString(tempo));
            writer.newLine();
            writer.write(Integer.toString(gameLogic.getTaille()));
            writer.newLine();
            for (int i = 0; i < gameLogic.getTaille(); i++) {
                for (int j = 0; j < gameLogic.getTaille(); j++) {
                    if (gameLogic.getGrille()[i][j].isVivante()) {
                        writer.write("1");
                    } else {
                        writer.write("0");
                    }
                }
                writer.newLine();
            }
            writer.close();
            JOptionPane.showMessageDialog(null, "Configuration enregistrée avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erreur lors de l'enregistrement de la configuration.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void charger() {
        try {
            Scanner scanner = new Scanner(new File("config.txt"));
            tempo = Integer.parseInt(scanner.nextLine());
            timer.setDelay(tempo);
            int nouvelleTaille = Integer.parseInt(scanner.nextLine());
            if (nouvelleTaille != gameLogic.getTaille()) {
                JOptionPane.showMessageDialog(null, "La taille de la grille ne correspond pas à la configuration enregistrée.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
            for (int i = 0; i < nouvelleTaille; i++) {
                String ligne = scanner.nextLine();
                for (int j = 0; j < nouvelleTaille; j++) {
                    if (ligne.charAt(j) == '1') {
                        gameLogic.getGrille()[i][j].setVivante(true);
                        gameLogic.getGrille()[i][j].switchColor();
                    } else {
                        gameLogic.getGrille()[i][j].setVivante(false);
                    }
                }
            }
            scanner.close();
            updateUI();
            JOptionPane.showMessageDialog(null, "Configuration chargée avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Aucune configuration enregistrée trouvée.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateUI() {
        SwingUtilities.invokeLater(() -> {
            for (int i = 0; i < gameLogic.getTaille(); i++) {
                for (int j = 0; j < gameLogic.getTaille(); j++) {
                    models.Cellule cell = gameLogic.getGrille()[i][j];
                    if (cell.isVivante()) {
                        buttons[i][j].setBackground(Color.BLACK);
                        buttons[i][j].setOpaque(true);
                        buttons[i][j].setBorderPainted(false);
                    } else {
                        buttons[i][j].setBackground(Color.WHITE);
                    }
                }
            }
            repaint();
        });
    }

    private class CellButtonListener implements ActionListener {
        private int x;
        private int y;

        public CellButtonListener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void actionPerformed(ActionEvent e) {
            gameLogic.change(x, y);
            updateUI();
        }
    }

   
}
