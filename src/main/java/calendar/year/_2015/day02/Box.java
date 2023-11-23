package calendar.year._2015.day02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public record Box(int largeur, int longueur, int profondeur) {

    int computeNeededPaper() {
        int largeurLongueur = largeur * longueur;
        int largeurProfondeur = largeur * profondeur;
        int longueurProfondeur = longueur * profondeur;
        int minProduct = Math.min(
                largeurProfondeur,
                Math.min(largeurLongueur, longueurProfondeur)
        );
        return 2 * largeurLongueur
                + 2 * largeurProfondeur
                + 2 * longueurProfondeur
                + minProduct;
    }

    int computePaperForRibbon() {
        List<Integer> orderedDimensions = new ArrayList<>(List.of(largeur, longueur, profondeur));

        Collections.sort(orderedDimensions);

        return 2 * orderedDimensions.get(0)
                + 2 * orderedDimensions.get(1)
                + largeur * longueur * profondeur;
    }
}
