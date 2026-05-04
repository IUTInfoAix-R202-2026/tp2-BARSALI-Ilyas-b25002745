package fr.univ_amu.iut.exercice8;

import javafx.application.Application;
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
    // 2. Créer le panneau Fahrenheit (VBox) :
    //    - Label "°F" (style bold, 16px)
    //    - Slider vertical [0, 212], valeur initiale 32, id "slider-fahrenheit"
    //    - TextField, id "tf-fahrenheit", maxWidth 50
    //
    // 3. Ajouter un ChangeListener sur le slider Celsius :
    //    fahrenheit = celsius * 9/5 + 32
    //    (utiliser un flag "updating" pour éviter les boucles infinies)
    //
    // 4. Ajouter un ChangeListener sur le slider Fahrenheit :
    //    celsius = (fahrenheit - 32) * 5/9
    //
    // 5. Lier chaque TextField à son slider via
    //    Bindings.bindBidirectional(tf.textProperty(), slider.valueProperty(),
    //        new NumberStringConverter())
    //
    // 6. Composer les panneaux dans un HBox, créer la Scene, afficher.
  }

  public static void main(String[] args) {
    launch(args);
  }
}
