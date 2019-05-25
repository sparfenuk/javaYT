import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utils.Requests;
import utils.Settings;

import java.io.File;


public class Program extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
//        stage.setMinHeight(150.0);
//        stage.setMinWidth(300.0);

        Scene scene = new Scene(root);

        //stage.setOpacity(0);

        stage.setTitle("Youtube Analytics");
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws Exception {

        //<init>
        File cacheFolder = new File(Settings.deSerealize().getCachePath());
        cacheFolder.mkdirs();
       //</init>
        //System.out.println(Requests.search("PewDiePie","channel",1).get(0).getResult().getChannelTitle());

        //System.out.println(Requests.getChannelsResonanse("UCd8LNXKST3VEgVeRnRcZJmg")[1]);
        Application.launch();
    }
}