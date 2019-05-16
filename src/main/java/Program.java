import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.channel.statistics.Statistics;
import models.video.Item;
import models.video.Response;
import models.video.Video;

import java.util.List;


public class Program extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
//        stage.setMinHeight(150.0);
//        stage.setMinWidth(300.0);

        Scene scene = new Scene(root);

        stage.setTitle("Youtube Analytics");
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws Exception {

//        List<Item> videos = Requests.searchVideos("Mister max",5);
//        Video video = videos.get(1).getVideo();
//        //channel name
//        System.out.println(Requests.findChannel(video.getChannelId()).getTitle());
//        //sub count
//        System.out.println(Requests.findChannelStatistics(video.getChannelId()).getSubscriberCount());
//        //video name
//        System.out.println(video.getTitle());

        Statistics s = Requests.findChannelStatistics("UC-lHJZR3Gqxm24_Vd_AJ5Yw");
        System.out.println(s.getSubscriberCount());
        System.out.println(s.getVideoCount());
        System.out.println(s.getViewCount());
        Application.launch();
    }
}