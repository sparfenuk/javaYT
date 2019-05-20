package animation;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TransitionAnimation {
   public static  void stageFadeOut(Node node)
    {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(5000));
        fadeTransition.setNode(node);
        fadeTransition.setFromValue(5);
        fadeTransition.setToValue(0);
        fadeTransition.play();
        fadeTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage)  ((Node)event.getSource()).getScene().getWindow();
                stage.close();
            }
        });
    }
    public static void stageFadeIn(Node node)
    {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000));
        fadeTransition.setNode(node);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();

    }
}
