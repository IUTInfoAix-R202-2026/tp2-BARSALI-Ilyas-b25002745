package fr.univ_amu.iut.bonus9;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Bonus 9 - Application balle rebondissante.
 *
 * <p>Cette classe fournit le cadre de l'application (panneau, boutons, boucle d'animation).
 * L'étudiant doit implémenter la classe {@link Balle} qui gère la physique par bindings.
 */
public class BalleRebondissante extends Application {

  private Balle balle;
  private BorderPane root;
  private HBox commandes;
  private Pane panneau;
  private AnimationTimer animation;

  private void initialiser() {
    panneau = creerPanneauBalle();
    animation = creerAnimation();
    commandes = creerPanneauCommandes();
    root = creerRacine();
  }

  private BorderPane creerRacine() {
    BorderPane racine = new BorderPane();
    BorderPane.setMargin(commandes, new Insets(20));
    racine.setTop(commandes);
    racine.setCenter(panneau);
    return racine;
  }

  private Pane creerPanneauBalle() {
    Pane pane = new Pane();
    pane.setPrefHeight(500);
    pane.setPrefWidth(500);
    balle = new Balle(pane);
    return pane;
  }

  private AnimationTimer creerAnimation() {
    final LongProperty derniereMiseAJour = new SimpleLongProperty(0);

    return new AnimationTimer() {
      @Override
      public void handle(long timestamp) {
        if (derniereMiseAJour.get() > 0) {
          long tempsEcoule = timestamp - derniereMiseAJour.get();
          balle.move(tempsEcoule);
        }
        derniereMiseAJour.set(timestamp);
      }

      @Override
      public void start() {
        derniereMiseAJour.set(System.nanoTime());
        super.start();
      }

      @Override
      public void stop() {
        derniereMiseAJour.set(System.nanoTime());
        super.stop();
      }
    };
  }

  private HBox creerPanneauCommandes() {
    Button btnStart = new Button("Démarrer");
    btnStart.setOnAction(e -> animation.start());
    Button btnStop = new Button("Stop");
    btnStop.setOnAction(e -> animation.stop());

    HBox hbox = new HBox(10, btnStart, btnStop);
    hbox.setAlignment(Pos.CENTER);
    return hbox;
  }

  @Override
  public void start(Stage primaryStage) {
    initialiser();
    primaryStage.setScene(new Scene(root));
    primaryStage.setTitle("Balle rebondissante");
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
