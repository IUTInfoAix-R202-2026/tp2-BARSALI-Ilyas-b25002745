package fr.univ_amu.iut.bonus10;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Bonus 10 - Jeu Pong complet.
 *
 * <p>Jeu de Pong avec deux raquettes (drag souris), une balle rebondissante, et détection de
 * collision. Tout repose sur les bindings et les propriétés JavaFX.
 */
public class SlowPong extends Application {

  private final BooleanProperty startVisible = new SimpleBooleanProperty(true);
  private BallePong balle;
  private Pane pongPane;
  private Raquette raquetteGauche;
  private Raquette raquetteDroite;
  private Button startButton;
  private AnimationTimer animation;

  @Override
  public void start(Stage primaryStage) {
    // TODO bonus 10 : assembler le jeu Pong.
  }

  private Pane creerPanneauPong() {
    // TODO bonus 10 : créer le panneau de jeu avec balle, raquettes et bouton.
    Pane pane = new Pane();
    return pane;
  }

  private Button creerBoutonStart() {
    // TODO bonus 10 : créer le bouton Start au centre.
    Button btn = new Button("Start");
    return btn;
  }

  private void demarrerNouvellePartie() {
    // TODO bonus 10 : replacer la balle au centre.
  }

  private void mettreAJour(long tempsEcouleNs) {
    verifierRebonds();
    deplacerBalle(tempsEcouleNs);
  }

  private void verifierRebonds() {
    // TODO bonus 10 : vérifier les rebonds et inverser la vélocité.
  }

  private void deplacerBalle(long tempsEcouleNs) {
    // TODO bonus 10 : déplacer la balle en fonction du temps écoulé.
  }

  private AnimationTimer creerAnimation() {
    final LongProperty derniereMiseAJour = new SimpleLongProperty(0);

    return new AnimationTimer() {
      @Override
      public void handle(long timestamp) {
        if (derniereMiseAJour.get() > 0) {
          long tempsEcoule = timestamp - derniereMiseAJour.get();
          mettreAJour(tempsEcoule);
        }
        derniereMiseAJour.set(timestamp);
      }

      @Override
      public void start() {
        derniereMiseAJour.set(System.nanoTime());
        startVisible.set(false);
        super.start();
      }

      @Override
      public void stop() {
        derniereMiseAJour.set(System.nanoTime());
        startVisible.set(true);
        super.stop();
      }
    };
  }

  public static void main(String[] args) {
    launch(args);
  }
}
