package fr.univ_amu.iut.bonus9;

import static org.assertj.core.api.Assertions.assertThat;

import javafx.stage.Stage;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

/** Tests du bonus 9 - Balle rebondissante avec bindings. */
@ExtendWith(ApplicationExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BalleRebondissanteTest {

  private Stage stage;

  @Start
  void start(Stage stage) throws Exception {
    stage.setScene(null); // évite la fuite de Scene entre tests (TestFX réutilise le Stage)
    this.stage = stage;
    new BalleRebondissante().start(stage);
  }

  @Disabled("Retire cette annotation pour activer le test")
  @Test
  @Order(1)
  void laFenetreEstVisible(FxRobot robot) {
    assertThat(stage.isShowing()).as("le Stage doit être affiché").isTrue();
  }

  @Disabled("Retire cette annotation pour activer le test")
  @Test
  @Order(2)
  void laBallExiste(FxRobot robot) {
    long nbCircles =
        robot.lookup(".root").queryAll().stream()
            .flatMap(n -> n.lookupAll("Circle").stream())
            .count();
    // La balle est un Circle ajouté au Pane
    assertThat(robot.lookup("Circle")).as("un Circle doit être présent (la balle)");
  }

  @Disabled("Retire cette annotation pour activer le test")
  @Test
  @Order(3)
  void laSceneExiste(FxRobot robot) {
    assertThat(stage.getScene()).as("le Stage doit avoir une Scene").isNotNull();
  }
}
