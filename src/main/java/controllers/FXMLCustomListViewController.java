package controllers;

import com.jfoenix.controls.JFXListView;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import models.channel.Channel;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;




public class FXMLCustomListViewController {

    private double x, y;

    @FXML
    private JFXListView<Cell> listView;

    @FXML
    private ComboBox<String> sortBy;


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
        Label commentsCount;


        public LCell() {
            super();
            imageView = new ImageView();
            nickName = new Label();
            creationDate = new Label();
            subsCount = new Label();
            videoCount = new Label();
            viewCount = new Label();
            commentsCount = new Label();

            VBox vBox = new VBox(imageView, nickName, creationDate, subsCount, videoCount, viewCount,commentsCount);
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
                creationDate.setText("Created: " + item.getCreationDate());
                subsCount.setText("Subs: " + item.getSubsCount());
                videoCount.setText("Videos: " + item.getVideoCount());
                viewCount.setText("Views: " + item.getViewsCount());
                if(item.getCommentsCount() != null)
                    commentsCount.setText("Comments: " + item.getCommentsCount());

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
                    output.format(d),
                    Long.parseLong(c.getStatistics().getSubscriberCount()),
                    Integer.parseInt(c.getStatistics().getVideoCount()),
                    Long.parseLong(c.getStatistics().getViewCount())));
        } catch (Exception e) {
            System.out.println("Invalid creation date");
        }
    }

    public void setItems(List<Channel> channels){
        listView.getItems().clear();
        this.sortBy.setValue("Comments");

        channels.sort(Comparator.comparing(Channel::getCommentCount).reversed());

        for (Channel c : channels)
        {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd");
                Date d = sdf.parse(c.getInfo().getPublishedAt());

                listView.getItems().add(new Cell(c.getInfo().getThumbnails().getDefault().getUrl(),
                        c.getInfo().getTitle(),
                        output.format(d),
                        Long.parseLong(c.getStatistics().getSubscriberCount()),
                        Integer.parseInt(c.getStatistics().getVideoCount()),
                        c.getViewCount(),
                        c.getCommentCount())
                );
            } catch (Exception e) {
                System.out.println("Invalid creation date");
            }
        }
    }

    public void setItems(List<Channel> channels, String sortBy) {
        listView.getItems().clear();
        this.sortBy.setValue(sortBy);

        sortOut(channels,sortBy);

        for (Channel c : channels)
            addItem(c);


    }

    public List<Channel> sortOut(List<Channel> channels, String sortBy){
        switch (sortBy) {
            case "Name":
                channels.sort(new Comparator<Channel>() {
                    @Override
                    public int compare(Channel o1, Channel o2) {
                        return o1.getInfo().getTitle().compareTo(o2.getInfo().getTitle());
                    }
                });
                break;
            case "Date":
                channels.sort(new Comparator<Channel>() {
                    @Override
                    public int compare(Channel o1, Channel o2) {
                        return o1.getInfo().getPublishedAt().compareTo(o2.getInfo().getPublishedAt());
                    }
                });
                break;
            case "Subs":
                channels.sort(new Comparator<Channel>() {//reverse
                    @Override
                    public int compare(Channel o2, Channel o1) {
                        return o1.getStatistics().getSubscriberCount().compareTo(o2.getStatistics().getSubscriberCount());
                    }
                });
                break;
            case "Video":
                channels.sort(new Comparator<Channel>() {//reverse
                    @Override
                    public int compare(Channel o2, Channel o1) {
                        return o1.getStatistics().getVideoCount().compareTo(o2.getStatistics().getVideoCount());
                    }
                });
                break;
            case "View":
                channels.sort(new Comparator<Channel>() {
                    @Override
                    public int compare(Channel o2, Channel o1) {//reverse
                        return o1.getStatistics().getViewCount().compareTo(o2.getStatistics().getViewCount());
                    }
                });
                break;
        }
        return channels;
    }

    @FXML
    void initialize() {
        listView.setCellFactory(new Callback<ListView<Cell>, ListCell<Cell>>() {
            @Override
            public ListCell<Cell> call(ListView<Cell> listView) {
                return new LCell();
            }
        });
        listView.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode().equals(KeyCode.DELETE) && listView.getSelectionModel().getSelectedItem() != null) {
                    listView.getItems().remove(listView.getSelectionModel().getSelectedIndex());
                }
            }
        });

        sortBy.getSelectionModel().selectedItemProperty().addListener( (options, oldValue, newValue) -> {
            switch (newValue){
                case "Name":
                    listView.getItems().sort(Comparator.comparing(Cell::getChannelName));
                    break;
                case "Date":
                    listView.getItems().sort(Comparator.comparing(Cell::getCreationDate));
                    break;
                case "Subs":
                    listView.getItems().sort(Comparator.comparing(Cell::getSubsCount).reversed());
                    break;
                case "Video":
                    listView.getItems().sort(Comparator.comparing(Cell::getVideoCount).reversed());
                    break;
                case "View":
                    listView.getItems().sort(Comparator.comparing(Cell::getViewsCount).reversed());
                    break;
            }
                }
        );

//        listView.getItems().add(new Cell("https://yt3.ggpht.com/a/AGF-l78jKS1dQTlvI282DRahMFh62R3Gl2vFXZr6Vg=s88-mo-c-c0xffffffff-rj-k-no","P","22.12.2012",44654687l,10000,43242343242938192l));
//        listView.getItems().add(new Cell("https://yt3.ggpht.com/a/AGF-l78jKS1dQTlvI282DRahMFh62R3Gl2vFXZr6Vg=s88-mo-c-c0xffffffff-rj-k-no","P","22.12.2012",44654687l,10000,43242343242938192l,321312L));
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

}

