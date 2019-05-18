package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import java.net.DatagramPacket;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.channel.Channel;
import utils.Requests;

public class FXMLShowBaseInfController {

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
    private Text chaneNameText1;

    @FXML
    private Text createdDateText1;

    @FXML
    private Text subsCountText1;

    @FXML
    private Text videosCountText1;

    @FXML
    private Text watchesCountText1;

    @FXML
    private ImageView channelImageView2;

    @FXML
    private Text chaneNameText2;

    @FXML
    private Text createdDateText2;

    @FXML
    private Text subsCountText2;

    @FXML
    private Text videosCountText2;

    @FXML
    private Text watchesCountText2;

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

    @FXML
    void findBtn1OnAction(ActionEvent event) {
        String channelId = getChannelId();
        channelId1.setText(channelId);
        channelId1KeyInput(null);
    }

    @FXML
    void findBtn2OnAction(ActionEvent event) {
        String chanelId = getChannelId();
        channelId2.setText(chanelId);
        channelId2KeyInput(null);
    }

    @FXML
    void initialize() {


    }

    private String getChannelId() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLYTChannelFind.fxml"));
            Parent root1 = (Parent) loader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Find Channel");
            stage.setScene(new Scene(root1));
            FXMLYTChannelFindController controllerEditBook = loader.<FXMLYTChannelFindController>getController();
            stage.showAndWait();

            return controllerEditBook.getChosenChanelId();
        } catch (Exception e) {
            e.printStackTrace();
            return "not found";
        }
    }

    private void check() {
        if (chaneNameText1.getText().length() > 0 && chaneNameText2.getText().length() > 0) {

            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                if (sdf.parse(createdDateText1.getText()).compareTo(sdf.parse(createdDateText2.getText())) < 0) {
                    createdDateText1.setFill(Color.GREEN);
                    createdDateText2.setFill(Color.RED);
                } else {
                    createdDateText1.setFill(Color.RED);
                    createdDateText2.setFill(Color.GREEN);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (Integer.parseInt(subsCountText1.getText()) > Integer.parseInt(subsCountText2.getText())) {
                subsCountText1.setFill(Color.GREEN);
                subsCountText2.setFill(Color.RED);
            } else {
                subsCountText1.setFill(Color.RED);
                subsCountText2.setFill(Color.GREEN);
            }


            if (Integer.parseInt(videosCountText1.getText()) > Integer.parseInt(videosCountText2.getText())) {
                videosCountText1.setFill(Color.GREEN);
                videosCountText2.setFill(Color.RED);
            } else {
                videosCountText1.setFill(Color.RED);
                videosCountText2.setFill(Color.GREEN);
            }

            if (Long.parseLong(watchesCountText1.getText()) > Long.parseLong(watchesCountText2.getText())) {
                watchesCountText1.setFill(Color.GREEN);
                watchesCountText2.setFill(Color.RED);
            } else {
                watchesCountText1.setFill(Color.RED);
                watchesCountText2.setFill(Color.GREEN);
            }
        }

    }
}
