package common;

import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import java.text.DecimalFormat;

public class UiUtil {
    /* * Fxml을 Controller 클래스와 동일한 위치에서 가져오기.
    * 예) LoginController 의 경우 Login.fxml 로 변환 *
    * * @param clazz Controller 이름. * @return FXML Loader. /
     */
    public static FXMLLoader getFxmlLoader(Class<?> clazz) {
        FXMLLoader loader = new FXMLLoader();
        final String fxmlName = clazz.getSimpleName().replace("Controller", "") + ".fxml";
        loader.setLocation(clazz.getResource(fxmlName));
        return loader;
    }

    public static void loadFxml(Parent controller) {
        try {
            final FXMLLoader loader = getFxmlLoader(controller.getClass());
            loader.setRoot(controller);
            loader.setController(controller);
            loader.load();
        } catch (Exception e) {
            throw new RuntimeException("Failed to load FXML", e);
        }
    }


    private static final double SIZE_BYTE = 1024.0;
    private static final DecimalFormat SIZE_FORMAT = new DecimalFormat("#.##");

    /* * Filesize를 문자열 형태로 변환. * * @param sizeByte Byte 사이즈 * @return 문자열 */
    public static String sizeToText(long sizeByte) {
        if (sizeByte < SIZE_BYTE) {
            return sizeByte + " Bytes";
        }
        double sizeKb = sizeByte / SIZE_BYTE;
        double sizeMb = sizeKb / SIZE_BYTE;
        double sizeGb = sizeMb / SIZE_BYTE;
        if (sizeGb >= 1.0) {
            return SIZE_FORMAT.format(sizeGb) + " GB";
        }
        if (sizeMb >= 1.0) {
            return SIZE_FORMAT.format(sizeMb) + " MB";
        }
        if (sizeKb >= 1.0) {
            return SIZE_FORMAT.format(sizeKb) + " KB";
        }
        return Long.toString(sizeByte);
    }

    public static void progressIndicator(Runnable runnable) {
        progressIndicator(runnable, null);
    }

    public static void progressIndicator(Runnable runnable, Window window) {
        final Stage progressStage = new Stage(StageStyle.TRANSPARENT);
        final ProgressIndicator pi = new ProgressIndicator();
        pi.setStyle("-fx-background-color: transparent;");
        final Scene scene = new Scene(pi);
        scene.setFill(Color.TRANSPARENT);
        progressStage.setAlwaysOnTop(true);
        if (window != null) {
            progressStage.initOwner(window);
        }
        progressStage.setScene(scene);
        progressStage.setWidth(230);
        progressStage.setHeight(230);
        progressStage.show();
        final Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                runnable.run();
                return null;
            }
        };
        task.setOnSucceeded(event -> progressStage.close());
        task.setOnCancelled(event -> progressStage.close());
        final Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }
}
