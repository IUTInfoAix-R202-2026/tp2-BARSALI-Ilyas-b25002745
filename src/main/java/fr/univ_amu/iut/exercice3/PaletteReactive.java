package fr.univ_amu.iut.exercice3;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringExpression;
import javafx.geometry.Pos;
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

    // 2. Top : un HBox avec trois BoutonCouleur :
    //    - new BoutonCouleur("Rouge", "red")   id: "btn-rouge"
    //    - new BoutonCouleur("Vert", "green")   id: "btn-vert"
    //    - new BoutonCouleur("Bleu", "blue")    id: "btn-bleu"
    BoutonCouleur btnRouge = new BoutonCouleur("Rouge", "red");
    btnRouge.setId("btn-rouge");
    BoutonCouleur btnVert = new BoutonCouleur("Vert", "green");
    btnVert.setId("btn-vert");
    BoutonCouleur btnBleu = new BoutonCouleur("Bleu", "blue");
    btnBleu.setId("btn-bleu");
    HBox hbox = new HBox(10, btnRouge, btnVert, btnBleu);
    hbox.setPadding(new javafx.geometry.Insets(10));
    root.setTop(hbox);

    // 3. Center : un Pane avec l'id "zone", taille minimale 300x200.
    Pane zone = new Pane();
    zone.setId("zone");
    zone.setMinSize(300, 200);
    root.setCenter(zone);

    // 4. Bottom : un Label avec l'id "compteurs".
    Label labelCompteurs = new Label();
    labelCompteurs.setId("compteurs");
    labelCompteurs.setMaxWidth(Double.MAX_VALUE);
    labelCompteurs.setAlignment(Pos.CENTER);
    root.setBottom(labelCompteurs);

    // 5. Appeler createBindings() pour lier le label et la zone aux boutons.
    createBindings(btnRouge, btnVert, btnBleu, zone, labelCompteurs);

    // 6. Créer la Scene, l'attacher au Stage, afficher.
    Scene scene = new Scene(root);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  void createBindings(
      BoutonCouleur btnRouge,
      BoutonCouleur btnVert,
      BoutonCouleur btnBleu,
      Pane zone,
      Label labelCompteurs) {

    // 1. ChangeListener sur chaque bouton pour changer la couleur de la zone
    btnRouge
        .nbClicsProperty()
        .addListener(
            (obs, oldVal, newVal) -> {
              zone.setStyle("-fx-background-color: " + btnRouge.getCouleur() + ";");
            });
    btnVert
        .nbClicsProperty()
        .addListener(
            (obs, oldVal, newVal) -> {
              zone.setStyle("-fx-background-color: " + btnVert.getCouleur() + ";");
            });
    btnBleu
        .nbClicsProperty()
        .addListener(
            (obs, oldVal, newVal) -> {
              zone.setStyle("-fx-background-color: " + btnBleu.getCouleur() + ";");
            });

    // 2. StringExpression avec Bindings.concat()
    StringExpression texteCompteurs =
        Bindings.concat(
            "Rouge: ", btnRouge.nbClicsProperty().asString(),
            "  Vert: ", btnVert.nbClicsProperty().asString(),
            "  Bleu: ", btnBleu.nbClicsProperty().asString());

    // 3. Somme des trois compteurs pour le Bindings.when()
    javafx.beans.binding.NumberBinding total =
        btnRouge.nbClicsProperty().add(btnVert.nbClicsProperty()).add(btnBleu.nbClicsProperty());

    // 4. Expression conditionnelle : "Bienvenue !" si aucun clic, sinon les compteurs
    javafx.beans.binding.StringBinding texteAvecBienvenue =
        Bindings.when(total.isEqualTo(0)).then("Bienvenue !").otherwise(texteCompteurs);

    // 5. Lier labelCompteurs.textProperty() à cette expression via bind()
    labelCompteurs.textProperty().bind(texteAvecBienvenue);
  }

  public static void main(String[] args) {
    launch(args);
  }
}
