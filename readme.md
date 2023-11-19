# Jeu de la Vie - Galsen Coding Challenge

Le Jeu de la Vie est un automate cellulaire imaginé par John Conway. Cette implémentation en Java suit une architecture MVC (Modèle-Vue-Contrôleur).
[![Game of Life]("./JeuDeLaVie.png")]("")

## Algorithme du Jeu de la Vie : le Jeu de la Vie est un automate cellulaire basé sur des règles simples :

1. **Sous-Population :** Toute cellule vivante avec moins de 2 voisins vivants meurt, comme si elle était causée par la sous-population.

2. **Survie :** Toute cellule vivante avec 2 ou 3 voisins vivants survit à la prochaine génération.

3. **Surpopulation :** Toute cellule vivante avec plus de 3 voisins vivants meurt, comme si elle était causée par la surpopulation.

4. **Régénération :** Toute cellule morte avec exactement 3 voisins vivants devient une cellule vivante, comme par reproduction.

Ces règles simples donnent lieu à des motifs complexes et imprévisibles à mesure que le jeu évolue. Chaque étape du jeu est déterminée par l'état des cellules voisines, créant ainsi des schémas de vie, de mort et de régénération.

## Architecture MVC

### Modèle (Model)
Elle contient les differentes entités qui seront utilisés lors du jeu. La classe `Cellule` dans le même package représente une cellule individuelle du jeu, avec ses états et ses méthodes d'évolution.

### Vue (View)

Le package `views` contient la classe `GameUI`, qui représente la vue du jeu. Elle utilise Swing pour créer une interface graphique permettant à l'utilisateur d'interagir avec le jeu. Les boutons et la grille sont associés au modèle pour refléter l'état actuel du jeu.

### Contrôleur (Controller)

Le package `Contoller` contient la classe `GameLogic`, qui représente la logique du jeu. Elle gère la logique du jeu, la création de la grille, l'initialisation aléatoire, la mise à jour de la grille, etc.

## Compilation et Exécution

### Compilation

Assurez-vous d'avoir Java JDK installé sur votre machine.

```bash
cd src
javac -d ../bin controllers/*.java models/*.java views/*.java Main.java

```

### Exécution

```bash
java -cp ../bin Main
```
### Creation du Jar 
cd ..
jar cfm JeuDeLaVie.jar Manifest.txt -C bin .

### Execution deu Jar
java -Jar JeuDeLaVie.jar

## Docker

### Création de l'image Docker

Assurez-vous que Docker est installé sur votre machine.

```bash
docker build -t jeudelavie:latest .
```

### Exécution du conteneur Docker

```bash
docker run -it --rm -e DISPLAY=$DISPLAY -v /tmp/.X11-unix:/tmp/.X11-unix jeudelavie:latest
```

Assurez-vous que l'affichage X11 est correctement configuré sur votre machine hôte.


