package nefra.misc;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import nefra.exceptions.DelLog;
import nefra.exceptions.FailedToCloseException;
import org.jetbrains.annotations.Contract;

import java.util.Optional;

/**
 * Class to exit the program from anywhere, GUI, shortcut, X-button
 * @author Cordel Murphy
 * @version 1.0
 * @since 1.0
 */
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
     * @since 1.0
     * @return exit code to indicate successful shutdown or not.
     */
    public int exit(Event e) {
        int exitCode = 0;
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
            try {
                if (!DelLog.getInstance().Close()) {
                    exitCode = 240;
                    throw new FailedToCloseException("Failed to Close ");
                }
            }
            catch(FailedToCloseException ex) { ex.printStackTrace(); }
            Platform.exit();
        }
        return exitCode;
    }
}
