package fr.univ_amu.iut.bonus10;

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

/** Tests du bonus 10 - Slow Pong. */
@ExtendWith(ApplicationExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SlowPongTest {

  private Stage stage;

  @Start
  void start(Stage stage) throws Exception {
    stage.setScene(null); // évite la fuite de Scene entre tests (TestFX réutilise le Stage)
    this.stage = stage;
    new SlowPong().start(stage);
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
  void laSceneExiste(FxRobot robot) {
    assertThat(stage.getScene()).as("le Stage doit avoir une Scene").isNotNull();
  }

  @Disabled("Retire cette annotation pour activer le test")
  @Test
  @Order(3)
  void leBoutonStartExiste(FxRobot robot) {
    assertThat(robot.lookup("Start").tryQuery())
        .as("un bouton 'Start' doit être présent")
        .isPresent();
  }
}
