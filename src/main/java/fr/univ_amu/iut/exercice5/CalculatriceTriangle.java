package fr.univ_amu.iut.exercice5;

import fr.univ_amu.iut.exercice4.AireTriangle;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class CalculatriceTriangle extends Application {

  private final AireTriangle modele = new AireTriangle();

  private final Slider sliderX1 = new Slider(0, 10, 0);
  private final Slider sliderY1 = new Slider(0, 10, 0);
  private final Slider sliderX2 = new Slider(0, 10, 0);
  private final Slider sliderY2 = new Slider(0, 10, 0);
  private final Slider sliderX3 = new Slider(0, 10, 0);
  private final Slider sliderY3 = new Slider(0, 10, 0);

  private final Label labelX1 = new Label("X1 :");
  private final Label labelY1 = new Label("Y1 :");
  private final Label labelX2 = new Label("X2 :");
  private final Label labelY2 = new Label("Y2 :");
  private final Label labelX3 = new Label("X3 :");
  private final Label labelY3 = new Label("Y3 :");

  private final Label labelP1 = new Label("P1");
  private final Label labelP2 = new Label("P2");
  private final Label labelP3 = new Label("P3");

  private final Label labelAire = new Label("Aire :");
  private final TextField textFieldAire = new TextField();

  private final Line ligneP1P2 = new Line();
  private final Line ligneP2P3 = new Line();
  private final Line ligneP3P1 = new Line();

  private final Pane panneauDessin = new Pane();

  private final GridPane grille = new GridPane();

  @Override
  public void start(Stage primaryStage) {
    // 1. Configurer le GridPane (gaps + colonnes)
    configGrille();

    // 2. Configurer les 6 sliders (tick marks, snap, ids)
    configSliders();

    // 3. Placer les sliders et leurs labels dans la grille
    ajouterSliders();

    // 4. Placer le label "Aire :" et le TextField dans la grille
    ajouterAire();

    // 5. Placer les titres de section P1, P2, P3
    ajouterLabelsPoints();

    // 6. Ajouter le panneau de dessin avec les 3 lignes
    ajouterPanneauDessin();

    // 7. Lier les sliders au modèle et les lignes aux coordonnées
    creerBindings();

    // 8. Créer la Scene, l'attacher au Stage et afficher
    primaryStage.setScene(new Scene(grille, 700, 800));
    primaryStage.setTitle("Calculatrice Triangle");
    primaryStage.show();
  }

  static void configSlider(Slider slider) {
    // Afficher les labels de valeur sur le slider
    slider.setShowTickLabels(true);

    // Afficher les marques de graduation
    slider.setShowTickMarks(true);

    // Une graduation principale tous les 1
    slider.setMajorTickUnit(1);

    // Pas de graduation intermédiaire
    slider.setMinorTickCount(0);

    // Incrément par bloc de 1
    slider.setBlockIncrement(1);

    // Accrocher la valeur à la graduation la plus proche
    slider.setSnapToTicks(true);
  }

  void configSliders() {
    // Appliquer la configuration commune à chaque slider
    configSlider(sliderX1);
    configSlider(sliderY1);
    configSlider(sliderX2);
    configSlider(sliderY2);
    configSlider(sliderX3);
    configSlider(sliderY3);

    // Donner un id CSS à chaque slider (utilisé par les tests)
    sliderX1.setId("slider-x1");
    sliderY1.setId("slider-y1");
    sliderX2.setId("slider-x2");
    sliderY2.setId("slider-y2");
    sliderX3.setId("slider-x3");
    sliderY3.setId("slider-y3");
  }

  void configGrille() {
    // Espacement horizontal et vertical entre les cellules
    grille.setHgap(10);
    grille.setVgap(10);

    // 3 colonnes de 200px chacune
    grille.getColumnConstraints().add(new ColumnConstraints(200));
    grille.getColumnConstraints().add(new ColumnConstraints(200));
    grille.getColumnConstraints().add(new ColumnConstraints(200));
  }

  void ajouterSliders() {
    // Ligne 1-2 : coordonnées de P1
    grille.add(labelX1, 0, 1);
    grille.add(sliderX1, 1, 1);
    grille.add(labelY1, 0, 2);
    grille.add(sliderY1, 1, 2);

    // Ligne 3-4 : coordonnées de P2
    grille.add(labelX2, 0, 3);
    grille.add(sliderX2, 1, 3);
    grille.add(labelY2, 0, 4);
    grille.add(sliderY2, 1, 4);

    // Ligne 5-6 : coordonnées de P3
    grille.add(labelX3, 0, 5);
    grille.add(sliderX3, 1, 5);
    grille.add(labelY3, 0, 6);
    grille.add(sliderY3, 1, 6);
  }

  void ajouterAire() {
    // Id pour les tests + non éditable (résultat calculé, pas saisi)
    textFieldAire.setId("aire");
    textFieldAire.setEditable(false);

    // Placer sur la ligne 7
    grille.add(labelAire, 0, 7);
    grille.add(textFieldAire, 1, 7);
  }

  void ajouterLabelsPoints() {
    // Titres de section en ligne 0
    grille.add(labelP1, 0, 0);
    grille.add(labelP2, 1, 0);
    grille.add(labelP3, 2, 0);
  }

  void ajouterPanneauDessin() {
    // Id pour les tests
    panneauDessin.setId("dessin");

    // Taille préférée du panneau
    panneauDessin.setPrefSize(500, 500);

    // Ajouter les 3 lignes au panneau
    panneauDessin.getChildren().addAll(ligneP1P2, ligneP2P3, ligneP3P1);

    // Placer le panneau sur toute la largeur (colspan 3), ligne 8
    grille.add(panneauDessin, 0, 8, 3, 1);
  }

  void creerBindings() {
    // 1. Lier les sliders au modèle (slider -> modèle)
    modele.x1Property().bind(sliderX1.valueProperty());
    modele.y1Property().bind(sliderY1.valueProperty());
    modele.x2Property().bind(sliderX2.valueProperty());
    modele.y2Property().bind(sliderY2.valueProperty());
    modele.x3Property().bind(sliderX3.valueProperty());
    modele.y3Property().bind(sliderY3.valueProperty());

    // 2. Lier le TextField à l'aire calculée par le modèle
    textFieldAire.textProperty().bind(modele.areaProperty().asString());

    // 3. Lier les coordonnées de la ligne P1-P2 (facteur d'échelle x50)
    ligneP1P2.startXProperty().bind(modele.x1Property().multiply(50));
    ligneP1P2.startYProperty().bind(modele.y1Property().multiply(50));
    ligneP1P2.endXProperty().bind(modele.x2Property().multiply(50));
    ligneP1P2.endYProperty().bind(modele.y2Property().multiply(50));

    // 4. Lier les coordonnées de la ligne P2-P3
    ligneP2P3.startXProperty().bind(modele.x2Property().multiply(50));
    ligneP2P3.startYProperty().bind(modele.y2Property().multiply(50));
    ligneP2P3.endXProperty().bind(modele.x3Property().multiply(50));
    ligneP2P3.endYProperty().bind(modele.y3Property().multiply(50));

    // 5. Lier les coordonnées de la ligne P3-P1 (ferme le triangle)
    ligneP3P1.startXProperty().bind(modele.x3Property().multiply(50));
    ligneP3P1.startYProperty().bind(modele.y3Property().multiply(50));
    ligneP3P1.endXProperty().bind(modele.x1Property().multiply(50));
    ligneP3P1.endYProperty().bind(modele.y1Property().multiply(50));
  }

  public static void main(String[] args) {
    launch(args);
  }
}
