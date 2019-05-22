package controllers;

import animation.TransitionAnimation;
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

import java.io.IOException;


public class FXMLMainController {

    private double x, y;

    @FXML
    private BorderPane borderPane;

    @FXML
    void initialize() {
       TransitionAnimation.stageFadeIn((Node)  borderPane);
    }

    @FXML
    public void goToYTAnalitics(MouseEvent event) throws Exception
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXMLYTAnalitics.fxml"));
        Parent root = (Parent) fxmlLoader.load();

        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setTitle("YouTube Analytic");
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);

        Stage current = (Stage)  ((Node)event.getSource()).getScene().getWindow();

        stage.show();
        current.close();

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

    public void goToSettings(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXMLSettings.fxml"));
        Parent root = (Parent) fxmlLoader.load();

        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setTitle("Settings");
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);

        Stage current = (Stage)  ((Node)event.getSource()).getScene().getWindow();

        stage.show();
        current.close();
    }
}

