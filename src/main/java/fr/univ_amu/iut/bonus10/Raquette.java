package fr.univ_amu.iut.bonus10;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.shape.Rectangle;

/**
 * Bonus 10 - Raquette du jeu Pong.
 *
 * <p>La raquette est un {@link Rectangle} déplaçable verticalement par drag souris. Sa position Y
 * est gérée par une {@link DoubleProperty} liée au {@code translateY}.
 */
public class Raquette extends Rectangle {

  private final DoubleProperty positionY = new SimpleDoubleProperty();
  private double initTranslateY;
  private double dragAnchorY;

  /**
   * Crée une raquette à la position X donnée.
   *
   * @param x la position horizontale de la raquette
   */
  public Raquette(double x) {
    // TODO bonus 10 : initialiser la raquette.
    //
    // 1. Appeler super(x, 200, 10, 100) : position (x, 200), largeur 10, hauteur 100.
    // 2. Couleur : Color.LIGHTGRAY.
    // 3. Lier translateYProperty() à positionY.
    // 4. Ajouter un handler setOnMousePressed qui stocke :
    //    - initTranslateY = getTranslateY()
    //    - dragAnchorY = event.getSceneY()
    // 5. Ajouter un handler setOnMouseDragged qui met à jour :
    //    - positionY = initTranslateY + event.getSceneY() - dragAnchorY
  }
}
