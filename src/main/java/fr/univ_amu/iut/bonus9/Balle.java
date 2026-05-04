package fr.univ_amu.iut.bonus9;

import javafx.beans.binding.BooleanExpression;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

/**
 * Bonus 9 - Modèle de balle rebondissante avec bindings.
 *
 * <p>Contrairement au TP1 bonus 7 qui utilisait {@code TranslateTransition} (animation
 * déclarative), cette version gère la physique par des <b>bindings</b> : la vélocité est inversée
 * automatiquement quand la position touche un bord, grâce à {@code
 * Bindings.when().then().otherwise()}.
 *
 * <p>Concepts :
 *
 * <ul>
 *   <li>{@link BooleanExpression} pour détecter les collisions avec les bords
 *   <li>{@link NumberBinding} avec {@code when().then().otherwise()} pour inverser la vélocité
 *   <li>{@link DoubleProperty} pour position et vélocité
 * </ul>
 */
public class Balle {

  private final DoubleProperty positionX;
  private final DoubleProperty positionY;
  private final DoubleProperty velociteX; // en pixel par nanoseconde
  private final DoubleProperty velociteY; // en pixel par nanoseconde
  private final DoubleProperty rayon;
  private final Pane parent;
  private final Circle cercle;

  private BooleanExpression toucheMurVertical;
  private BooleanExpression toucheMurHorizontal;

  private NumberBinding rebondVertical;
  private NumberBinding rebondHorizontal;

  /**
   * Crée une balle dans le panneau donné.
   *
   * @param parent le Pane contenant la balle
   */
  public Balle(Pane parent) {
    // TODO bonus 9 : initialiser la balle.
    //
    // 1. Initialiser les propriétés :
    //    - positionX, positionY : position au centre du panneau (~250)
    //    - velociteX, velociteY : vitesse initiale (~100e-9 pixels/ns)
    //    - rayon : 20
    //
    // 2. Créer un Circle, lier centerX/centerY à positionX/positionY,
    //    lier radius à rayon. Couleur : Color.CORAL.
    //
    // 3. Ajouter le cercle au parent.
    //
    // 4. Appeler createBindings().
    this.parent = parent;
    positionX = new SimpleDoubleProperty(0);
    positionY = new SimpleDoubleProperty(0);
    velociteX = new SimpleDoubleProperty(0);
    velociteY = new SimpleDoubleProperty(0);
    rayon = new SimpleDoubleProperty(0);
    cercle = new Circle();
  }

  private void createBindings() {
    // TODO bonus 9 : créer les bindings de rebond.
    //
    // 1. toucheMurVertical = positionX <= rayon OU positionX >= parent.width - rayon
    //    toucheMurVertical = positionX.subtract(rayon).lessThanOrEqualTo(0)
    //        .or(positionX.add(rayon).greaterThanOrEqualTo(parent.widthProperty()));
    //
    // 2. toucheMurHorizontal = idem pour Y/height
    //
    // 3. rebondVertical = when(toucheMurVertical).then(velociteX.negate()).otherwise(velociteX)
    //
    // 4. rebondHorizontal = idem pour Y
  }

  /**
   * Déplace la balle en fonction du temps écoulé.
   *
   * @param tempsEcouleEnNanosecondes le temps écoulé depuis le dernier appel
   */
  public void move(long tempsEcouleEnNanosecondes) {
    // TODO bonus 9 : déplacer la balle.
    //
    // 1. Mettre à jour la vélocité en fonction des rebonds :
    //    velociteX.set(rebondVertical.doubleValue())
    //    velociteY.set(rebondHorizontal.doubleValue())
    //
    // 2. Mettre à jour la position :
    //    positionX += velociteX * tempsEcoule
    //    positionY += velociteY * tempsEcoule
  }
}
