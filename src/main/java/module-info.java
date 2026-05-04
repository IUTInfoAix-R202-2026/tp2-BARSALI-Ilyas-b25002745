/**
 * Module JavaFX pour le TP TP2.
 *
 * <p>Ce module exporte les paquetages nécessaires pour les exercices. Ajoutez les exports des
 * nouveaux paquetages d'exercices au fur et à mesure.
 */
open module tp2.javafx {
  // JavaFX dependencies
  requires transitive javafx.base;
  requires transitive javafx.controls;
  requires transitive javafx.graphics;
  requires transitive javafx.fxml;

  // Export base package
  exports fr.univ_amu.iut;

  // ========== EXERCICES - Ajouter les exports ici ==========
  exports fr.univ_amu.iut.exercice1;
  exports fr.univ_amu.iut.exercice2;
  exports fr.univ_amu.iut.exercice3;
  exports fr.univ_amu.iut.exercice4;
  exports fr.univ_amu.iut.exercice5;
  exports fr.univ_amu.iut.exercice6;
  exports fr.univ_amu.iut.exercice7;
  exports fr.univ_amu.iut.exercice8;
  exports fr.univ_amu.iut.bonus9;
  exports fr.univ_amu.iut.bonus10;
}
