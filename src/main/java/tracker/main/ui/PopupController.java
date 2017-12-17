package tracker.main.ui;

import common.UiUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class PopupController extends VBox {
    @FXML
    private TextField contentField;
    @FXML
    private Button confirmButton;

    public interface OnConfirmButtonClicked {
        void onClick(String result) throws IOException;
    }

    OnConfirmButtonClicked onConfirmButtonClicked;

    public void setOnConfirmButtonClicked(OnConfirmButtonClicked onConfirmButtonClicked) {
        this.onConfirmButtonClicked = onConfirmButtonClicked;
    }

    public PopupController() {
        UiUtil.loadFxml(this);
        confirmButton.setOnMouseClicked((event) -> {
            try {
                onConfirmButtonClicked.onClick(contentField.getText());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
