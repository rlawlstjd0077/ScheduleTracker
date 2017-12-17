package tracker.main;

import common.FileWriter;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tracker.main.ui.MainController;

import java.util.Locale;

public class ScheduleTrackerLauncher extends Application{
    private FileWriter fileWriter;
    public static void main(String args[]) {
        Locale.setDefault(Locale.ENGLISH);
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        fileWriter = new FileWriter();
        final MainController mainController = new MainController(fileWriter);
        final Scene scene = new Scene(mainController);

        primaryStage.setTitle("Schedule Tracker");
        primaryStage.setScene(scene);
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setOnCloseRequest(event -> System.exit(0));
        primaryStage.show();

    }
}
