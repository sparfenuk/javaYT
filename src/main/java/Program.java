import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utils.Requests;
import utils.Settings;

import java.io.File;


public class Program extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));

        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);

        stage.setTitle("Youtube Analytics");
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws Exception {


        File cacheFolder = new File(Settings.deSerialize().getCachePath());
        cacheFolder.mkdirs();

        Application.launch();
    }
}