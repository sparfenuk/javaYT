package controllers;

import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public class FXMLCustomListViewController {

    @FXML
    private JFXListView<Cell> listView;

    private class Cell {
        private String imagePath;
        private String channelName;
        private String creationDate;
        private Long subsCount;
        private int videoCount;
        private Long viewsCount;

        public Cell() {
        }

        public Cell(String imagePath, String channelName, String creationDate, Long subsCount, int videoCount, Long viewsCount) {
            this.imagePath = imagePath;
            this.channelName = channelName;
            this.creationDate = creationDate;
            this.subsCount = subsCount;
            this.videoCount = videoCount;
            this.viewsCount = viewsCount;
        }

        public String getImagePath() {
            return imagePath;
        }

        public String getChannelName() {
            return channelName;
        }

        public String getCreationDate() {
            return creationDate;
        }

        public Long getSubsCount() {
            return subsCount;
        }

        public int getVideoCount() {
            return videoCount;
        }

        public Long getViewsCount() {
            return viewsCount;
        }
    }

    private static class LCell extends ListCell<Cell> {
        HBox hBox;
        ImageView imageView;
        Label nickName;
        Label creationDate;
        Label subsCount;
        Label videoCount;
        Label viewCount;


        public LCell() {
            super();
            imageView = new ImageView();
            nickName = new Label();
            creationDate = new Label();
            subsCount = new Label();
            videoCount = new Label();
            viewCount = new Label();

            VBox vBox = new VBox(imageView, nickName, creationDate, subsCount, videoCount, viewCount);
            hBox = new HBox(imageView, vBox);

            hBox.getStylesheets().add("/styles/cellStyle.css");
            hBox.getStyleClass().add("hBox");

        }

        @Override
        protected void updateItem(Cell item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null && !empty) {
                imageView.setImage(new Image(item.getImagePath(), true));
                nickName.setText("Name: " + item.getChannelName());
                creationDate.setText("Created: " + item.getChannelName());
                subsCount.setText("Subs: " + item.getSubsCount());
                videoCount.setText("Videos: " + item.getSubsCount());
                viewCount.setText("Views: "+item.getViewsCount());
                setGraphic(hBox);
            } else setGraphic(null);
        }
    }

    @FXML
    void initialize() {
        listView.setCellFactory(new Callback<ListView<Cell>, ListCell<Cell>>() {
            @Override
            public ListCell<Cell> call(ListView<Cell> listView) {
                return new LCell();
            }
        });
        listView.getItems().add(new Cell("https://yt3.ggpht.com/a/AGF-l78jKS1dQTlvI282DRahMFh62R3Gl2vFXZr6Vg=s88-mo-c-c0xffffffff-rj-k-no","P","22.12.2012",44654687l,10000,43242343242938192l));
    }

}

