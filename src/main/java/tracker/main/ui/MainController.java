package tracker.main.ui;

import common.FileWriter;
import common.UiUtil;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class MainController extends AnchorPane {
    @FXML
    private AnchorPane root;

    Popup popup;
    PopupController popupController;

    private Map<String, String> scheduleMap = new HashMap<>();
    private ZonedDateTime startTime;
    private ZonedDateTime endTime;
    private FileWriter fileWriter;

    private boolean isActive = false;
    public MainController(FileWriter fileWriter) throws IOException {
        this.fileWriter = fileWriter;
        UiUtil.loadFxml(this);
        root.setOnMouseClicked((event) -> {
            if(isActive) {
                popupController = new PopupController();
                root.setStyle("-fx-background-color: white");
                isActive = false;

                final Stage stage = new Stage();
                stage.setScene(new Scene(popupController));
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setAlwaysOnTop(true);

                popupController.setOnConfirmButtonClicked((result) -> {
                    endTime = ZonedDateTime.now();
                    String timeResult = DateTimeFormatter.ofPattern("hh:mm").format(startTime)
                            +  "~"  +DateTimeFormatter.ofPattern("hh:mm").format(startTime);
                    System.out.println(timeResult);
                    scheduleMap.put(timeResult, result);
                    fileWriter.writeFile(timeResult, result);
                    stage.close();
                });

                stage.showAndWait();
            } else {
                isActive = true;
                root.setStyle("-fx-background-color: green");
                startTime = ZonedDateTime.now();
            }
        });
    }

    public Map<String, String> getScheduleMap() {
        return scheduleMap;
    }
}
