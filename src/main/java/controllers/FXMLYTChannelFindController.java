package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import models.search.Item;


public class FXMLYTChannelFindController{

    @FXML
    private ResourceBundle resources;

    @FXML
    private AnchorPane anchor;

    @FXML
    private URL location;

    @FXML
    private ListView<Cell> channelList;

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

    private class Cell{
        private String imagePath;
        private String nickName;

        public Cell() {
        }

        public Cell(String imagePath, String nickName) {
            this.imagePath = imagePath;
            this.nickName = nickName;
        }

        public String getImagePath() {
            return imagePath;
        }

        public void setImagePath(String imagePath) {
            this.imagePath = imagePath;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }
    }

    static class ListCells extends ListCell<Cell>{
        HBox hBox;
        ImageView imageView;
        Label nickName;

        public ListCells() {
            super();
            imageView = new ImageView();
            nickName = new Label();
            hBox = new HBox(imageView,nickName);
        }

        @Override
        protected void updateItem(Cell item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null && !empty) {
                imageView.setImage(new Image(item.getImagePath(),true));
                nickName.setText(item.getNickName());
                setGraphic(hBox);
            }
            else setGraphic(null);
        }
    }


    @FXML
    void cancelBtnClick(ActionEvent event) {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }



    @FXML
    void chooseBtnClick(ActionEvent event) {
        if (channelList.getSelectionModel().getSelectedIndex() >= 0){
            //todo:
        }
    }

    @FXML
    void findBtnClick(ActionEvent event) {
        try {
            List<Item> channels = utils.Requests.search(nickNameField.getText(), "channel", 5);

            for(Item i:channels)
                channelList.getItems().add(new Cell(i.getResult().getThumbnails().getDefault().getUrl(),i.getResult().getTitle()));

        }
        catch (Exception e){}
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
}
