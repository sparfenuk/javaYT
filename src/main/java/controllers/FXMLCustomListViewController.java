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
import models.channel.Channel;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

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
        private Long commentsCount;

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

        public Cell(String imagePath, String channelName, String creationDate, Long subsCount, int videoCount, Long viewsCount, Long commentsCount) {
            this.imagePath = imagePath;
            this.channelName = channelName;
            this.creationDate = creationDate;
            this.subsCount = subsCount;
            this.videoCount = videoCount;
            this.viewsCount = viewsCount;
            this.commentsCount = commentsCount;
        }

        public Long getCommentsCount() {
            return commentsCount;
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
                viewCount.setText("Views: " + item.getViewsCount());
                setGraphic(hBox);
            } else setGraphic(null);
        }
    }

    private void addItem(Channel c) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd");
            Date d = sdf.parse(c.getInfo().getPublishedAt());

            listView.getItems().add(new Cell(c.getInfo().getThumbnails().getDefault().getUrl(),
                    c.getInfo().getTitle(),
                    d.toString(),
                    Long.parseLong(c.getStatistics().getSubscriberCount()),
                    Integer.parseInt(c.getStatistics().getVideoCount()),
                    Long.parseLong(c.getStatistics().getViewCount())));
        } catch (Exception e) {
            System.out.println("Invalid creation date");
        }
    }

    public void setItems(List<Channel> channels){
        listView.getItems().clear();

        channels.sort(Comparator.comparing(Channel::getCommentCount).reversed());

        for (Channel c : channels)
            addItem(c);
    }

    public void setItems(List<Channel> channels, String sortBy) {
        listView.getItems().clear();
        switch (sortBy) {
            case "name":
                channels.sort(new Comparator<Channel>() {
                    @Override
                    public int compare(Channel o1, Channel o2) {
                        return o1.getInfo().getTitle().compareTo(o2.getInfo().getTitle());
                    }
                });
                break;
            case "date":
                channels.sort(new Comparator<Channel>() {
                    @Override
                    public int compare(Channel o1, Channel o2) {
                        return o1.getInfo().getPublishedAt().compareTo(o2.getInfo().getPublishedAt());
                    }
                });
                break;
            case "subs":
                channels.sort(new Comparator<Channel>() {
                    @Override
                    public int compare(Channel o1, Channel o2) {
                        return o1.getStatistics().getSubscriberCount().compareTo(o2.getStatistics().getSubscriberCount());
                    }
                });
                break;
            case "video":
                channels.sort(new Comparator<Channel>() {
                    @Override
                    public int compare(Channel o1, Channel o2) {
                        return o1.getStatistics().getVideoCount().compareTo(o2.getStatistics().getVideoCount());
                    }
                });
                break;
            case "view":
                channels.sort(new Comparator<Channel>() {
                    @Override
                    public int compare(Channel o1, Channel o2) {
                        return o1.getStatistics().getViewCount().compareTo(o2.getStatistics().getViewCount());
                    }
                });
                break;
        }


        for (Channel c : channels)
            addItem(c);


    }


    @FXML
    void initialize() {
        listView.setCellFactory(new Callback<ListView<Cell>, ListCell<Cell>>() {
            @Override
            public ListCell<Cell> call(ListView<Cell> listView) {
                return new LCell();
            }
        });
        //listView.getItems().add(new Cell("https://yt3.ggpht.com/a/AGF-l78jKS1dQTlvI282DRahMFh62R3Gl2vFXZr6Vg=s88-mo-c-c0xffffffff-rj-k-no","P","22.12.2012",44654687l,10000,43242343242938192l));
    }

}

