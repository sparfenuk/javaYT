package controllers;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

public class FXMLYTAController {

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
            double width = grid.getWidth()/3;
            double height = grid.getHeight()/2;
            int fontSize = (int)((width + height)/18);

            for (int i = 0 ; i < grid.getChildren().size() ; i++){
                ((JFXButton)grid.getChildren().get(i)).setPrefSize(width,height);
                grid.getChildren().get(i).setStyle("-fx-font: " + fontSize +" arial;");
            }

//            globalInfBtn.setPrefSize(width,height);
//            globalInfBtn.setStyle("-fx-font: " + fontSize +" arial;");
//            compareChnlsBtn.setPrefSize(width,height);
//            compareChnlsBtn.setStyle("-fx-font: " + fontSize +" arial;");
//            sortChnlsBtn.setPrefSize(width,height);
//            sortChnlsBtn.setStyle("-fx-font: " + fontSize +" arial;");
//            mediaResonanceBtn.setPrefSize(width,height);
//            mediaResonanceBtn.setStyle("-fx-font: " + fontSize +" arial;");
//            compareMediaResonanceBtn.setPrefSize(width,height);
//            compareMediaResonanceBtn.setStyle("-fx-font: " + fontSize +" arial;");
//            sortMediaResonanceBtn.setPrefSize(width,height);
//            sortMediaResonanceBtn.setStyle("-fx-font: " + fontSize +" arial;");
        };

        grid.widthProperty().addListener(stageSizeListener);
        grid.heightProperty().addListener(stageSizeListener);
    }
}
