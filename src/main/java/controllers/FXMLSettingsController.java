package controllers;

import javafx.scene.paint.Color;
import utils.*;
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
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import javax.swing.*;
import java.io.File;

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
    private Settings settings;

    @FXML
    void initialize()
    {
        if (utils.Settings.isFileExists())
        {
           settings = utils.Settings.deSerialize();
           // System.out.println(settings);
        }
        else
        {
            settings = new Settings();
        }


        if(settings.getCacheSave())
            CacheTrue.setSelected(true);
        else CacheFalse.setSelected(true);

        if(settings.getLoadTimeShow())
            LoadTImeTrue.setSelected(true);
        else LoadTImeFalse.setSelected(true);

         PathTextField.setText(settings.cachePath);


    }

    public void mainMenu(MouseEvent mouseEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Youtube Analytics");
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setResizable(false);
            stage.setScene(new Scene(root1));
            stage.getScene().setFill(Color.TRANSPARENT);
            stage.show();
            this.close(mouseEvent);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void openPathToFile(MouseEvent mouseEvent) {

        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));
        chooser.setDialogTitle("Open Directory");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
           //PathTextField.setText(String.valueOf(chooser.getCurrentDirectory()));
            PathTextField.setText(String.valueOf(chooser.getSelectedFile()));

        } else {
            System.out.println("No Selection ");
        }
    }




    public void checkTrueCache(ActionEvent actionEvent) {
        CacheFalse.setSelected(false);
    }

    public void checkFalseCache(ActionEvent actionEvent) {  CacheTrue.setSelected(false);
    }


    public void checkFalseLoadTime(ActionEvent actionEvent) {
        LoadTImeTrue.setSelected(false);}

    public void checkTrueLoadTime(ActionEvent actionEvent) {
        LoadTImeFalse.setSelected(false);}

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
//        CacheFalse.setSelected(false);
//        CacheTrue.setSelected(false);
//        CacheFalse.setDisable(false);
//        CacheTrue.setDisable(false);
//
//        LoadTImeFalse.setSelected(false);
//        LoadTImeTrue.setSelected(false);
//        LoadTImeFalse.setDisable(false);
//        LoadTImeTrue.setDisable(false);

        mainMenu(event);
    }



    public void saveSettingsBtn(MouseEvent event) {

            if (!PathTextField.getText().equals(settings.cachePath))
            {
                File f = new File(PathTextField.getText());
                if (f.exists() && f.isDirectory()) {
                   settings.setCachePath(PathTextField.getText());
                }
                else {
                    JOptionPane.showMessageDialog(null, "there is no such folder");
                }

            }


            settings.setCacheSave(CacheTrue.isSelected());
            settings.setLoadTimeShow(LoadTImeTrue.isSelected());

        try {
            utils.Settings.serialize(settings);
           // System.out.println(settings);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Can not save");
        }
    }



}
