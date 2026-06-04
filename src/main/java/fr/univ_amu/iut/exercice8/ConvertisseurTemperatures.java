package fr.univ_amu.iut.exercice8;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

/**
 * Exercice 8 (capstone) - Convertisseur de températures.
 *
 * <p>Cet exercice synthétise tous les types de bindings vus dans le TP :
 *
 * <ul>
 *   <li>Binding unidirectionnel (Labels de lecture)
 *   <li>Binding bidirectionnel (TextField ↔ Slider via {@link NumberStringConverter})
 *   <li>{@code ChangeListener} pour la conversion avec formule (C = (F-32)*5/9)
 *   <li>Sliders verticaux ({@code Orientation.VERTICAL})
 * </ul>
 *
 * <p>L'application affiche deux panneaux côte à côte : un pour Celsius, un pour Fahrenheit.
 * Modifier l'un met à jour l'autre automatiquement.
 */
public class ConvertisseurTemperatures extends Application {

  private boolean updating = false;

  @Override
  public void start(Stage primaryStage) {
    // TODO exercice 8 : construire le convertisseur de températures.
    //
    // 1. Créer le panneau Celsius (VBox) :
    //    - Label "°C" (style bold, 16px)
    //    - Slider vertical [0, 100], valeur initiale 0, id "slider-celsius"
    //    - TextField, id "tf-celsius", maxWidth 50
    //
    Label LabelC = new Label("°C");
    LabelC.setStyle("-fx-font-weight: bold; -fx-font-size: 16;");
    Slider sliderCelsius = new Slider(0, 100, 0);
    sliderCelsius.setOrientation(Orientation.VERTICAL);
    sliderCelsius.setId("slider-celsius");

    TextField tfCelsius = new TextField();
    tfCelsius.setId("tf-celsius");
    tfCelsius.setMaxWidth(50);
    VBox panneauCelsius = new VBox(10, LabelC, sliderCelsius, tfCelsius);
    panneauCelsius.setAlignment(Pos.CENTER);

    // 2. Créer le panneau Fahrenheit (VBox) :
    //    - Label "°F" (style bold, 16px)
    //    - Slider vertical [0, 212], valeur initiale 32, id "slider-fahrenheit"
    //    - TextField, id "tf-fahrenheit", maxWidth 50
    //
    Label labelF = new Label("°F");
    labelF.setStyle("-fx-font-weight: bold; -fx-font-size: 16;");
    Slider sliderFahrenheit = new Slider(0, 212, 32);
    sliderFahrenheit.setOrientation(Orientation.VERTICAL);
    sliderFahrenheit.setId("slider-fahrenheit");
    TextField tfFahrenheit = new TextField();
    tfFahrenheit.setId("tf-fahrenheit");
    tfFahrenheit.setMaxWidth(50);
    VBox panneauFahrenheit = new VBox(10, labelF, sliderFahrenheit, tfFahrenheit);
    panneauFahrenheit.setAlignment(Pos.CENTER);

    // 3. Ajouter un ChangeListener sur le slider Celsius :
    //    fahrenheit = celsius * 9/5 + 32
    //    (utiliser un flag "updating" pour éviter les boucles infinies)
    //
    sliderCelsius
        .valueProperty()
        .addListener(
            (observable, oldValue, newValue) -> {
              if (updating) {
                return;
              }

              updating = true;

              try {
                double celsius = newValue.doubleValue();
                double fahrenheit = celsius * 9.0 / 5.0 + 32.0;

                tfCelsius.setText(String.format("%.1f", celsius));
                tfFahrenheit.setText(String.format("%.1f", fahrenheit));
                sliderFahrenheit.setValue(fahrenheit);
              } finally {
                updating = false;
              }
            });

    // 4. Ajouter un ChangeListener sur le slider Fahrenheit :
    //    celsius = (fahrenheit - 32) * 5/9
    //
    sliderFahrenheit
        .valueProperty()
        .addListener(
            (observable, oldValue, newValue) -> {
              if (updating) {
                return;
              }

              updating = true;

              try {
                double fahrenheit = newValue.doubleValue();
                double celsius = (fahrenheit - 32.0) * 5.0 / 9.0;

                tfFahrenheit.setText(String.format("%.1f", fahrenheit));
                tfCelsius.setText(String.format("%.1f", celsius));
                sliderCelsius.setValue(celsius);
              } finally {
                updating = false;
              }
            });

    // 5. Lier chaque TextField à son slider via
    //    Bindings.bindBidirectional(tf.textProperty(), slider.valueProperty(),
    //        new NumberStringConverter())
    //
    Bindings.bindBidirectional(
        tfCelsius.textProperty(), sliderCelsius.valueProperty(), new NumberStringConverter());
    Bindings.bindBidirectional(
        tfFahrenheit.textProperty(), sliderFahrenheit.valueProperty(), new NumberStringConverter());

    // 6. Composer les panneaux dans un HBox, créer la Scene, afficher.
    HBox conteneurPrincipal = new HBox(20, panneauCelsius, panneauFahrenheit);
    conteneurPrincipal.setAlignment(Pos.CENTER);

    Scene scene = new Scene(conteneurPrincipal, 400, 300);
    primaryStage.setTitle("Convertisseur de Température");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
