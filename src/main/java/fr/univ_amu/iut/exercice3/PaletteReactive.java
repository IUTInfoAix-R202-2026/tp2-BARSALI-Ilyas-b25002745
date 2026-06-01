package fr.univ_amu.iut.exercice3;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringExpression;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class PaletteReactive extends Application {

  @Override
  public void start(Stage primaryStage) {

    // 1. Créer un BorderPane comme racine.
    BorderPane root = new BorderPane();

    // 2. Top : un HBox avec trois BoutonCouleur.
    BoutonCouleur btnRouge = new BoutonCouleur("Rouge", "red");
    btnRouge.setId("btn-rouge");

    BoutonCouleur btnVert = new BoutonCouleur("Vert", "green");
    btnVert.setId("btn-vert");

    BoutonCouleur btnBleu = new BoutonCouleur("Bleu", "blue");
    btnBleu.setId("btn-bleu");

    HBox hbox = new HBox(10, btnRouge, btnVert, btnBleu);
    root.setTop(hbox);

    // 3. Center : un Pane avec l'id "zone", taille minimale 300x200.
    Pane zone = new Pane();
    zone.setId("zone");
    zone.setMinSize(300, 200);
    root.setCenter(zone);

    // 4. Bottom : un Label avec l'id "compteurs".
    Label compteurs = new Label();
    compteurs.setId("compteurs");
    root.setBottom(compteurs);

    // 5. Appeler createBindings().
    createBindings(btnRouge, btnVert, btnBleu, zone, compteurs);

    // 6. Créer la Scene, l'attacher au Stage, afficher.
    Scene scene = new Scene(root);
    primaryStage.setScene(scene);
    primaryStage.setTitle("Palette Reactive");
    primaryStage.show();
  }

  void createBindings(
      BoutonCouleur btnRouge,
      BoutonCouleur btnVert,
      BoutonCouleur btnBleu,
      Pane zone,
      Label labelCompteurs) {

    btnRouge
        .nbClicsProperty()
        .addListener(
            (obs, oldVal, newVal) ->
                zone.setStyle("-fx-background-color: " + btnRouge.getCouleur() + ";"));

    btnVert
        .nbClicsProperty()
        .addListener(
            (obs, oldVal, newVal) ->
                zone.setStyle("-fx-background-color: " + btnVert.getCouleur() + ";"));

    btnBleu
        .nbClicsProperty()
        .addListener(
            (obs, oldVal, newVal) ->
                zone.setStyle("-fx-background-color: " + btnBleu.getCouleur() + ";"));

    StringExpression texte =
        Bindings.concat(
            "Rouge: ", btnRouge.nbClicsProperty().asString(),
            "  Vert: ", btnVert.nbClicsProperty().asString(),
            "  Bleu: ", btnBleu.nbClicsProperty().asString());

    labelCompteurs.textProperty().bind(texte);
  }

  public static void main(String[] args) {
    launch(args);
  }
}
