package fr.univ_amu.iut.bonus10;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

/**
 * Bonus 10 - Balle du jeu Pong.
 *
 * <p>La balle est un {@link Circle} avec une vélocité (en pixels par nanoseconde). La détection de
 * collision utilise {@code Shape.intersect()}.
 */
public class BallePong extends Circle {

  private final DoubleProperty velociteX;
  private final DoubleProperty velociteY;

  public BallePong() {
    // TODO bonus 10 : initialiser la balle.
    //
    // 1. Appeler super(250, 250, 10) pour centrer la balle avec rayon 10.
    // 2. Initialiser velociteX et velociteY à ~120e-9 pixels/ns.
    // 3. Couleur : Color.WHITE.
    velociteX = new SimpleDoubleProperty(0);
    velociteY = new SimpleDoubleProperty(0);
  }

  /**
   * Vérifie si la balle entre en collision avec une autre forme.
   *
   * @param other la forme à tester
   * @return true si les deux formes se chevauchent
   */
  public boolean collided(Shape other) {
    // TODO bonus 10 : détecter la collision.
    //
    // Utiliser Shape.intersect(this, other) et vérifier que le résultat
    // a des bounds de largeur > 0.
    boolean result = false;
    return result;
  }

  public double getVelociteX() {
    return velociteX.get();
  }

  public void setVelociteX(double value) {
    velociteX.set(value);
  }

  public DoubleProperty velociteXProperty() {
    return velociteX;
  }

  public double getVelociteY() {
    return velociteY.get();
  }

  public void setVelociteY(double value) {
    velociteY.set(value);
  }

  public DoubleProperty velociteYProperty() {
    return velociteY;
  }
}
