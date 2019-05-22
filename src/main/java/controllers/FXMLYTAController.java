package controllers;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
        openFind(2);
    }

    @FXML
    void compareMediaResonanceBtnClick(ActionEvent event) {
        openFind(5);
    }

    @FXML
    void globalInfBtnClick(ActionEvent event) {
        openFind(1);
    }

    @FXML
    void mediaResonanceBtnClick(ActionEvent event) {
        openFind(4);
    }

    @FXML
    void sortChnlsBtnClick(ActionEvent event) {
        openFind(3);
    }

    @FXML
    void sortMediaResonanceBtnClick(ActionEvent event) {
        openFind(6);
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

    public void openFind(int type){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLYTChannelFind.fxml"));
            Parent root1 = (Parent) loader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Find Channel");
            stage.setScene(new Scene(root1));
            FXMLYTChannelFindController controllerEditBook = loader.<FXMLYTChannelFindController>getController();
            controllerEditBook.setType(type);
            stage.show();
            this.close(null);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
