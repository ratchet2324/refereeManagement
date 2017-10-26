package nefra.misc;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import nefra.db.DBConnect;
import org.jetbrains.annotations.Contract;

import java.util.Optional;

public class Exit {

    private static Exit instance = new Exit();

    private Exit() {
        instance = this;
    }

    @Contract(pure = true)
    public static Exit getInstance() {
        return instance;
    }

    /**
     * Exit the program with a prompt. This uses Event rather than ActionEvent and WindowEvent, so it is universal.
     *
     * @param e the event passed through
     */
    public void exit(Event e) {
        Alert exit = new Alert(Alert.AlertType.CONFIRMATION);
        exit.setTitle("Exit");
        exit.setHeaderText(null);
        exit.setGraphic(null);
        exit.setContentText("Are you sure you want to exit?");
        e.consume();

        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        exit.getDialogPane().getButtonTypes().setAll(yes, no);

        Optional<ButtonType> result = exit.showAndWait();
        if (result.isPresent() && result.get() == yes) {
            DBConnect.closeConnections();
            Platform.exit();
        }
    }
}
