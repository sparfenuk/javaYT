package controllers;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class FXMLYTAController {

    private double x, y;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private GridPane grid;

    @FXML
    private JFXButton globalInfBtn;

    @FXML
    private JFXButton compareChnlsBtn;

    @FXML
    private JFXButton sortChnlsBtn;

    @FXML
    private JFXButton mediaResonanceBtn;

    @FXML
    private JFXButton compareMediaResonanceBtn;

    @FXML
    private JFXButton sortMediaResonanceBtn;

    @FXML
    void compareChnlsBtnClick(ActionEvent event) {

    }

    @FXML
    void compareMediaResonanceBtnClick(ActionEvent event) {

    }

    @FXML
    void globalInfBtnClick(ActionEvent event) {

    }

    @FXML
    void mediaResonanceBtnClick(ActionEvent event) {

    }

    @FXML
    void sortChnlsBtnClick(ActionEvent event) {

    }

    @FXML
    void sortMediaResonanceBtnClick(ActionEvent event) {

    }

    @FXML
    void initialize() {
        ChangeListener<Number> stageSizeListener = (observable, oldValue, newValue) -> {
            double width = grid.getWidth()/3.2;
            double height = grid.getHeight()/2.2;
            int fontSize = (int)((width + height)/18);

            for (int i = 0 ; i < grid.getChildren().size() ; i++){
                ((JFXButton)grid.getChildren().get(i)).setPrefSize(width,height);
                grid.getChildren().get(i).setStyle("-fx-font: " + fontSize +" arial;");
            }
        };

        grid.widthProperty().addListener(stageSizeListener);
        grid.heightProperty().addListener(stageSizeListener);
    }


    @FXML
    public void close(MouseEvent event)
    {
        Stage stage = (Stage)  ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void minimize(MouseEvent event) {
        Stage stage = (Stage)  ((Node)event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }



    public void pressed(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
    }

    public void dragged(MouseEvent event) {
        Stage stage = (Stage)  ((Node)event.getSource()).getScene().getWindow();
        stage.setX(event.getScreenX()-x);
        stage.setY(event.getScreenY()-y);
    }

    public void maximize(MouseEvent event)
    {
        Stage stage = (Stage)  ((Node)event.getSource()).getScene().getWindow();
        if (!stage.isFullScreen())
            stage.setFullScreen(true);
        else
            stage.setFullScreen(false);

    }
}
