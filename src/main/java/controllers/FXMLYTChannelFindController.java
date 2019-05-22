package controllers;

import animation.RotationAnimation;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import models.channel.Channel;
import models.channel.Statistics;
import models.search.Item;
import utils.Requests;


public class FXMLYTChannelFindController {

    public Circle circle1;
    public Circle circle2;
    public Circle circle3;
    private RotateTransition rotation1;
    private RotateTransition rotation2;
    private RotateTransition rotation3;

    //1,2,3,4,5,6
    private int Type = 1;

    @FXML
    private Label name;

    @FXML
    private Label subs;
    @FXML
    private Label videos;
    @FXML
    private Label views;
    @FXML
    private Label date;

    @FXML
    private ImageView channelImage;

    @FXML
    private BorderPane borderPane;

    @FXML
    private ResourceBundle resources;

    @FXML
    private AnchorPane anchor;

    @FXML
    private AnchorPane contentAnc;

    @FXML
    private URL location;

    @FXML
    private JFXListView<Cell> channelList;

    @FXML
    private JFXTextField nickNameField;

    @FXML
    private JFXButton findBtn;

    @FXML
    private Text text;

    @FXML
    private JFXButton chooseBtn;

    @FXML
    private JFXButton cancelBtn;


    private String chosenChanelId;

    public void setType(int type){
        if(type > 6 || type < 1)
            return;

        this.Type = type;
    }

    private double x, y;
    RotationAnimation animation;

    public String getChosenChanelId() {
        return chosenChanelId;
    }



    private class Cell {
        private String imagePath;
        private String nickName;
        private String chanelId;

        public Cell() {
        }

        public Cell(String imagePath, String nickName, String chanelId) {
            this.imagePath = imagePath;
            this.nickName = nickName;
            this.chanelId = chanelId;
        }

        public String getImagePath() {
            return imagePath;
        }

        public String getNickName() {
            return nickName;
        }

        public String getChanelId() {
            return chanelId;
        }
    }

    static class ListCells extends ListCell<Cell> {
        HBox hBox;
        ImageView imageView;
        Label nickName;
        Label chanelId;

        public ListCells() {
            super();
            imageView = new ImageView();
            nickName = new Label();
            nickName.setAlignment(Pos.TOP_CENTER);
            chanelId = new Label();
            chanelId.setAlignment(Pos.BOTTOM_CENTER);
            VBox vBox = new VBox(nickName,chanelId);
            vBox.setMargin(nickName,new Insets(10,10, 10, 0));
            vBox.setMargin(chanelId,new Insets(10,10, 10, 0));
            hBox = new HBox(imageView, vBox);
            hBox.getStylesheets().add("/styles/cellStyle.css");
            hBox.getStyleClass().add("hBox");

        }

        @Override
        protected void updateItem(Cell item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null && !empty) {
                imageView.setImage(new Image(item.getImagePath(), true));
                nickName.setText(item.getNickName());
                chanelId.setText("ID: " + item.getChanelId());
                setGraphic(hBox);
            } else setGraphic(null);
        }
    }


    @FXML
    void cancelBtnClick(ActionEvent event) {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }


    @FXML
    void chooseBtnClick(ActionEvent event) {



//        if (channelList.getSelectionModel().getSelectedIndex() >= 0) {
//            chosenChanelId = channelList.getItems().get(channelList.getSelectionModel().getSelectedIndex()).chanelId;
//            cancelBtnClick(event);
//        }
    }

    @FXML
    void findBtnClick(ActionEvent event) {

       animation.play();
        channelList.getItems().clear();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    List<Item> channels = utils.Requests.search(nickNameField.getText(), "channel", 14);
                   animation.stop();
                    for (Item i : channels)
                        Platform.runLater(() ->
                                channelList.getItems().add(
                                        new Cell(i.getResult().getThumbnails().getDefault().getUrl(), i.getResult().getTitle(),i.getResult().getChannelId())
                                ));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        };
        Thread thread = new Thread(runnable);
        thread.start();
    }



    @FXML
    void initialize() {
        animation = new RotationAnimation();
        animation.add(setRotationSpec(circle1,10,360));
        animation.add(setRotationSpec(circle2,15,180));
        animation.add(setRotationSpec(circle3,19,145));
        animation.stop();

        channelList.setCellFactory(new Callback<ListView<Cell>, ListCell<Cell>>() {
            @Override
            public ListCell<Cell> call(ListView<Cell> listView) {
                return new ListCells();
            }
        });


        switch (this.Type) {

            case 1:
                chooseBtn.setVisible(false);
            channelList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Cell>() {
                @Override
                public void changed(ObservableValue<? extends Cell> observable, Cell oldValue, Cell newValue) {
                    try {

                        Channel channel = Requests.getChannel(observable.getValue().getChanelId());
                        name.setText(channel.getInfo().getTitle());
                        Statistics s = channel.getStatistics();
                        subs.setText("subscribers: " + s.getSubscriberCount());
                        views.setText("views: " + s.getViewCount());
                        channelImage.setImage(new Image(channel.getInfo().getThumbnails().getHigh().getUrl()));
                        videos.setText("videos: " + s.getVideoCount());

                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                        SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd");
                        Date d = sdf.parse(channel.getInfo().getPublishedAt());
                        date.setText(output.format(d));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            break;
            case 2:

                break;
            case 3:

            break;
            case 4:
                break;

            case 5:

                break;

            case 6:
        }


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

    private RotateTransition setRotationSpec(Circle circle, int duration, int angle)
    {   RotateTransition rotateTransition = new RotateTransition(Duration.seconds(duration),circle);
        rotateTransition.setByAngle(angle);
        rotateTransition.setAutoReverse(true);
        rotateTransition.setRate(3);
        rotateTransition.setCycleCount(18);
        rotateTransition.setDelay(Duration.millis(0));

        return rotateTransition;
    }
//    private void startRotationAndShow()
//    {
//        rotation1.play();
//        rotation2.play();
//        rotation3.play();
//        circle1.setVisible(true);
//        circle2.setVisible(true);
//        circle3.setVisible(true);
//    }
//    private void stopRotationAndHide()
//    {
//        rotation1.stop();
//        rotation2.stop();
//        rotation3.stop();
//        circle1.setVisible(false);
//        circle2.setVisible(false);
//        circle3.setVisible(false);
//
//    }
}
