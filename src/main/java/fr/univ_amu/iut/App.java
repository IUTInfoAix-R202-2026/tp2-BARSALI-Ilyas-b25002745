package fr.univ_amu.iut;

import fr.univ_amu.iut.bonus10.SlowPong;
import fr.univ_amu.iut.bonus9.BalleRebondissante;
import fr.univ_amu.iut.exercice3.PaletteReactive;
import fr.univ_amu.iut.exercice5.CalculatriceTriangle;
import fr.univ_amu.iut.exercice6.FormulaireConnexion;
import fr.univ_amu.iut.exercice7.CercleInteractif;
import fr.univ_amu.iut.exercice8.ConvertisseurTemperatures;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.function.Supplier;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * Lanceur du TP2. Affiche un menu avec un bouton par exercice ; cliquer lance l'exercice
 * correspondant dans une nouvelle fenêtre.
 *
 * <p>Point d'entrée par défaut de {@code ./mvnw javafx:run}. Les exercices 1-2 sont en mode console
 * (pas de GUI) et ne sont pas lancés depuis ce menu - utiliser directement leur méthode {@code
 * main()}.
 */
public class App extends Application {

  @Override
  public void start(Stage primaryStage) {
    Label titre = new Label("TP2 - Propriétés et bindings");
    titre.setId("titre-tp2");
    titre.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

    Label info = new Label("Exercices 1-2 : mode console (lancer via leur main())");
    info.setStyle("-fx-font-size: 11px; -fx-font-style: italic; -fx-text-fill: gray;");

    VBox root = new VBox(10);
    root.setPadding(new Insets(20));
    root.setAlignment(Pos.CENTER);
    root.getChildren().add(titre);
    root.getChildren().add(info);
    root.getChildren().add(bouton("Exercice 3 - Palette réactive", PaletteReactive::new));
    root.getChildren().add(bouton("Exercice 5 - Calculatrice triangle", CalculatriceTriangle::new));
    root.getChildren().add(bouton("Exercice 6 - Formulaire connexion", FormulaireConnexion::new));
    root.getChildren().add(bouton("Exercice 7 - Cercle interactif", CercleInteractif::new));
    root.getChildren().add(bouton("Exercice 8 - Convertisseur", ConvertisseurTemperatures::new));
    root.getChildren().add(bouton("Bonus 9 - Balle rebondissante", BalleRebondissante::new));
    root.getChildren().add(bouton("Bonus 10 - Slow Pong", SlowPong::new));

    primaryStage.setTitle("TP2 - Propriétés et bindings - Lanceur");
    primaryStage.setScene(new Scene(root, 420, 450));
    primaryStage.show();
  }

  private Button bouton(String libelle, Supplier<Application> fabrique) {
    Button btn = new Button(libelle);
    btn.setMaxWidth(Double.MAX_VALUE);
    btn.setOnAction(e -> lancerExercice(libelle, fabrique));
    return btn;
  }

  private void lancerExercice(String libelle, Supplier<Application> fabrique) {
    int fenetresAvant = Window.getWindows().size();
    try {
      fabrique.get().start(new Stage());
    } catch (Exception ex) {
      StringWriter sw = new StringWriter();
      ex.printStackTrace(new PrintWriter(sw));
      afficherAlerte(
          AlertType.ERROR,
          "Exception pendant le lancement de " + libelle,
          "L'exercice a levé une exception. Extrait :\n\n" + sw.toString().split("\n")[0],
          sw.toString());
      return;
    }
    int fenetresApres = Window.getWindows().size();
    if (fenetresApres == fenetresAvant) {
      afficherAlerte(
          AlertType.INFORMATION,
          libelle + " - rien à afficher",
          "La méthode start() de cet exercice ne produit aucune fenêtre.",
          "C'est normal si vous n'avez pas encore implémenté l'exercice.\n\n"
              + "Prochaines étapes :\n"
              + "  1. Ouvrir la classe de l'exercice dans src/main/java/...\n"
              + "  2. Remplacer le TODO par l'implémentation attendue\n"
              + "  3. Vérifier que le test passe avec ./mvnw test\n"
              + "  4. Relancer l'application : le bouton ouvrira maintenant une fenêtre");
    }
  }

  private void afficherAlerte(AlertType type, String titre, String header, String content) {
    Alert alert = new Alert(type);
    alert.setTitle(titre);
    alert.setHeaderText(header);
    alert.setContentText(content);
    alert.setResizable(true);
    alert.getDialogPane().setPrefWidth(520);
    alert.showAndWait();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
