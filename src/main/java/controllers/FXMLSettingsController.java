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

    public void MainMenu(MouseEvent mouseEvent) {
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

    public void OpenPathToFile(MouseEvent mouseEvent) {
        JFileChooser fileopen = new JFileChooser();
        int ret = fileopen.showDialog(null, "Открыть файл");
        if (ret == JFileChooser.APPROVE_OPTION) {
            File file = fileopen.getSelectedFile();
            PathTextField.setText(file.getPath());
        }
    }

    public void BtnCacheSave(MouseEvent mouseEvent) {
        if (CacheTrue.isSelected() == true)
        {
            System.out.print("hello");
        }
        else if (CacheFalse.isSelected() == true)
        {

        }
    }

    public void BtnCacheCancel(MouseEvent mouseEvent) {
        CacheFalse.setSelected(false);
        CacheTrue.setSelected(false);
        CacheFalse.setDisable(false);
        CacheTrue.setDisable(false);
    }
    public void CheckTrueCache(ActionEvent actionEvent) { CacheFalse.setDisable(true);
    }

    public void CheckFalseCache(ActionEvent actionEvent) { CacheTrue.setDisable(true);
    }

    public void BtnLoadTimeSave(MouseEvent mouseEvent) {
        if (LoadTImeTrue.isSelected() == true)
        {

        }
        else if (LoadTImeFalse.isSelected() == true)
        {

        }
    }
    public void BtnLoadTimeCancel(MouseEvent mouseEvent) {
        LoadTImeTrue.setSelected(false);
        LoadTImeFalse.setSelected(false);
        LoadTImeTrue.setDisable(false);
        LoadTImeFalse.setDisable(false);
    }

    public void CheckFalseLoadTime(ActionEvent actionEvent) {
        LoadTImeTrue.setDisable(true);}

    public void CheckTrueLoadTime(ActionEvent actionEvent) {
        LoadTImeFalse.setDisable(true);}
}
