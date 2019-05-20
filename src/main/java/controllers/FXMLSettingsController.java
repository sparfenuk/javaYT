package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

public class FXMLSettingsController {

    private double x, y;

    @FXML
    private Label LabelSettings;

    @FXML
    private JFXButton BtnPath;

    @FXML
    private Pane HBoxPaneFirst;

    @FXML
    private HBox HBox;

    @FXML
    private JFXCheckBox CacheTrue;

    @FXML
    private Pane HBoxPaneSecond;

    @FXML
    private JFXCheckBox CacheFalse;

    @FXML
    private JFXTextField PathTextField;

    @FXML
    private JFXCheckBox LoadTImeFalse;

    @FXML
    private Pane Pane;

    @FXML
    private JFXButton BtnCacheSave;

    @FXML
    private JFXButton MainMenu;

    @FXML
    private Label LabelLoadTime;

    @FXML
    private Pane HBoxPaneThird;

    @FXML
    private JFXButton BtnCacheCancel;

    @FXML
    private JFXCheckBox LoadTImeTrue;

    @FXML
    private JFXButton BtnLoadTimeSave;

    @FXML
    private Label LabelSaveCache;

    @FXML
    private AnchorPane Settings;

    @FXML
    private JFXButton BtnLoadTimeCancel;

    public void mainMenu(MouseEvent mouseEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("ABC");
            stage.setScene(new Scene(root1));
            stage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openPathToFile(MouseEvent mouseEvent) {
        JFileChooser fileopen = new JFileChooser();
        int ret = fileopen.showDialog(null, "Открыть файл");
        if (ret == JFileChooser.APPROVE_OPTION) {
            File file = fileopen.getSelectedFile();
            PathTextField.setText(file.getPath());
        }
    }




    public void checkTrueCache(ActionEvent actionEvent) { CacheFalse.setDisable(true);
    }

    public void checkFalseCache(ActionEvent actionEvent) { CacheTrue.setDisable(true);
    }


    public void btnLoadTimeCancel(MouseEvent mouseEvent) {
        LoadTImeTrue.setSelected(false);
        LoadTImeFalse.setSelected(false);
        LoadTImeTrue.setDisable(false);
        LoadTImeFalse.setDisable(false);
    }

    public void checkFalseLoadTime(ActionEvent actionEvent) {
        LoadTImeTrue.setDisable(true);}

    public void checkTrueLoadTime(ActionEvent actionEvent) {
        LoadTImeFalse.setDisable(true);}

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

    public void cancelSettingsBtn(MouseEvent event) {
        CacheFalse.setSelected(false);
        CacheTrue.setSelected(false);
        CacheFalse.setDisable(false);
        CacheTrue.setDisable(false);
    }

    public void saveSettingsBtn(MouseEvent event) {
        if (CacheTrue.isSelected() == true)
        {
            System.out.print("hello");
        }
        else if (CacheFalse.isSelected() == true)
        {

        }
        if (LoadTImeTrue.isSelected() == true)
        {

        }
        else if (LoadTImeFalse.isSelected() == true)
        {

        }
    }


    public void goBack(MouseEvent event) {
    }
}
