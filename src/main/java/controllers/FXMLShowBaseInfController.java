package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;


import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.channel.Channel;
import utils.Requests;

public class FXMLShowBaseInfController {

    private double x, y;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField channelId1;

    @FXML
    private JFXButton findBtn1;

    @FXML
    private JFXTextField channelId2;

    @FXML
    private JFXButton findBtn2;

    @FXML
    private ImageView channelImageView1;

    @FXML
    private Label chaneNameText1;

    @FXML
    private Label createdDateText1;

    @FXML
    private Label subsCountText1;

    @FXML
    private Label videosCountText1;

    @FXML
    private Label watchesCountText1;

    @FXML
    private ImageView channelImageView2;

    @FXML
    private Label chaneNameText2;

    @FXML
    private Label createdDateText2;

    @FXML
    private Label subsCountText2;

    @FXML
    private Label videosCountText2;

    @FXML
    private Label watchesCountText2;

    private boolean isComments;

    public void setComments(boolean comments) {
        isComments = comments;
    }

    public void setTextId1(String channelId){
        channelId1.setText(channelId);
        channelId1KeyInput(null);
    }
    public void setTextId2(String channelId){
        channelId2.setText(channelId);
        channelId2KeyInput(null);
    }

    @FXML
    void channelId1KeyInput(KeyEvent event) {
        if (channelId1.getText().length() == 24) {

            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        Channel channel = Requests.getChannel(channelId1.getText());
                        Platform.runLater(() -> {
                            chaneNameText1.setText(channel.getInfo().getTitle());

                            try {
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                                SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd");
                                Date d = sdf.parse(channel.getInfo().getPublishedAt());
                                createdDateText1.setText(output.format(d));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            channelImageView1.setImage(new Image(channel.getInfo().getThumbnails().getDefault().getUrl()));
                            subsCountText1.setText(channel.getStatistics().getSubscriberCount());
                            videosCountText1.setText(channel.getStatistics().getVideoCount());
                            watchesCountText1.setText(channel.getStatistics().getViewCount());

                            check();
                        });

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            Thread t = new Thread(runnable);
            t.start();
        }
    }

    @FXML
    void channelId2KeyInput(KeyEvent event) {
        if (channelId2.getText().length() == 24) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        Channel channel = Requests.getChannel(channelId2.getText());
                        Platform.runLater(() -> {
                            chaneNameText2.setText(channel.getInfo().getTitle());

                            try {
                                SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd");
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                                Date d = sdf.parse(channel.getInfo().getPublishedAt());
                                createdDateText2.setText(output.format(d));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }


                            subsCountText2.setText(channel.getStatistics().getSubscriberCount());
                            videosCountText2.setText(channel.getStatistics().getVideoCount());
                            watchesCountText2.setText(channel.getStatistics().getViewCount());
                            channelImageView2.setImage(new Image(channel.getInfo().getThumbnails().getDefault().getUrl()));

                            check();
                        });

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            Thread t = new Thread(runnable);
            t.start();
        }
    }

//    @FXML
//    void findBtn1OnAction(ActionEvent event) {
//        String channelId = getChannelId();
//        channelId1.setText(channelId);
//        channelId1KeyInput(null);
//    }
//
//    @FXML
//    void findBtn2OnAction(ActionEvent event) {
//        String chanelId = getChannelId();
//        channelId2.setText(chanelId);
//        channelId2KeyInput(null);
//    }

    @FXML
    void initialize() {


    }
    @FXML
    public void goBack(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXMLYTAnalitics.fxml"));
        Parent root = (Parent) fxmlLoader.load();

        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setTitle("Youtube channels");
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

//    private String getChannelId() {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLYTChannelFind.fxml"));
//            Parent root1 = (Parent) loader.load();
//            Stage stage = new Stage();
//            stage.initModality(Modality.APPLICATION_MODAL);
//            stage.initStyle(StageStyle.UNDECORATED);
//            stage.setTitle("Find Channel");
//            stage.setScene(new Scene(root1));
//            FXMLYTChannelFindController controllerEditBook = loader.<FXMLYTChannelFindController>getController();
//            stage.showAndWait();
//
//            return controllerEditBook.getChosenChanelId();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "not found";
//        }
//    }

    private void check() {
        if (chaneNameText1.getText().length() > 0 && chaneNameText2.getText().length() > 0) {

            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                if (sdf.parse(createdDateText1.getText()).compareTo(sdf.parse(createdDateText2.getText())) < 0) {
                    createdDateText1.setTextFill(Color.GREEN);
                    createdDateText2.setTextFill(Color.RED);
                } else {
                    createdDateText1.setTextFill(Color.RED);
                    createdDateText2.setTextFill(Color.GREEN);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (Integer.parseInt(subsCountText1.getText()) > Integer.parseInt(subsCountText2.getText())) {
                subsCountText1.setTextFill(Color.GREEN);
                subsCountText2.setTextFill(Color.RED);
            } else {
                subsCountText1.setTextFill(Color.RED);
                subsCountText2.setTextFill(Color.GREEN);
            }


            if (Integer.parseInt(videosCountText1.getText()) > Integer.parseInt(videosCountText2.getText())) {
                videosCountText1.setTextFill(Color.GREEN);
                videosCountText2.setTextFill(Color.RED);
            } else {
                videosCountText1.setTextFill(Color.RED);
                videosCountText2.setTextFill(Color.GREEN);
            }

            if (Long.parseLong(watchesCountText1.getText()) > Long.parseLong(watchesCountText2.getText())) {
                watchesCountText1.setTextFill(Color.GREEN);
                watchesCountText2.setTextFill(Color.RED);
            } else {
                watchesCountText1.setTextFill(Color.RED);
                watchesCountText2.setTextFill(Color.GREEN);
            }
        }

    }
}
