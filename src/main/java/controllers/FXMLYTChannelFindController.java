package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Platform;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import models.search.Item;


public class FXMLYTChannelFindController {

    @FXML
    private BorderPane borderPane;

    @FXML
    private ResourceBundle resources;

    @FXML
    private AnchorPane anchor;

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


    private double x, y;

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
        if (channelList.getSelectionModel().getSelectedIndex() >= 0) {
            chosenChanelId = channelList.getItems().get(channelList.getSelectionModel().getSelectedIndex()).chanelId;
            cancelBtnClick(event);
        }
    }

    @FXML
    void findBtnClick(ActionEvent event) {

        channelList.getItems().clear();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    List<Item> channels = utils.Requests.search(nickNameField.getText(), "channel", 4);

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

        channelList.setCellFactory(new Callback<ListView<Cell>, ListCell<Cell>>() {
            @Override
            public ListCell<Cell> call(ListView<Cell> listView) {
                return new ListCells();
            }
        });


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
