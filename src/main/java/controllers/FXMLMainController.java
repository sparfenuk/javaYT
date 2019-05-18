package controllers;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;


public class FXMLMainController {

    private double x, y;

    @FXML
    private BorderPane borderPane;

    @FXML
    void initialize() {
        borderPane.setOpacity(0);
        stageFadeIn();
    }

    @FXML
    public void goToYTAnalitics(MouseEvent event) throws Exception
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLAnalitics.fxml"));
        Parent root = (Parent) fxmlLoader.load();

        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setTitle("Youtube Analytics");
        stage.setOpacity(0.5);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        stageFadeOut();


    }



    @FXML
    public void close(MouseEvent event)
    {
        Stage stage = (Stage)  ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    public void minimize(MouseEvent event) {
        Stage stage = (Stage)  ((Node)event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }


    @FXML
    public void pressed(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
    }

    @FXML
    public void dragged(MouseEvent event) {
        Stage stage = (Stage)  ((Node)event.getSource()).getScene().getWindow();
        stage.setX(event.getScreenX()-x);
        stage.setY(event.getScreenY()-y);
    }
    private void stageFadeOut()
    {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(5000));
        fadeTransition.setNode(borderPane);
        fadeTransition.setFromValue(5);
        fadeTransition.setToValue(0);
        fadeTransition.play();
    }
    private void stageFadeIn()
    {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000));
        fadeTransition.setNode(borderPane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();

    }
}

