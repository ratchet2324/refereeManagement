package nefra.jfx.help;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import nefra.exceptions.DelLog;
import nefra.misc.Debug;

public class AboutGUI {
    public void initGUI() {
        Label line1 = new Label("Referee Management Program.");
        Label line2 = new Label("Created by Cordel Murphy");
        TextFlow line3 = emailLabel();
        Label line4 = new Label("and include the following required specifications, as well as the log files located at");
        Label line5 = new Label(DelLog.getInstance().getFolderPath() + " (Include whole \'Logs\" folder.)");
        VBox gr1 = new VBox(line1, line2, line3, line4, line5);
        gr1.setStyle("-fx-alignment: center;" +
                "-fx-font-size: 16px;");
        line1.setStyle("-fx-font-weight: bold;");

        Label line6 = new Label("--- START SYSTEM SPECS ---");
        Label line7 = new Label("OS: " + System.getProperty("os.name") + " " + System.getProperty("os.arch"));
        Label line8 = new Label("JRE: " + System.getProperty("java.vendor") + " v" + System.getProperty("java.version"));
        Label line9 = new Label("App version: " + getClass().getPackage().getImplementationVersion());
        Label line10 = new Label("Log File Name: " + DelLog.getInstance().getFileName());
        Label line11 = new Label("--- END SYSTEM SPECS ---");
        VBox gr2 = new VBox(line6, line7, line8, line9, line10, line11);
        gr2.setStyle("-fx-alignment: center;" +
                "-fx-font-size: 16px;");
        line6.setStyle("-fx-font-weight: bold;");
        line11.setStyle("-fx-font-weight: bold;");

        VBox centre = new VBox(gr1, gr2);
        centre.setSpacing(100);

        Button close = new Button("Close");
        HBox bottom = new HBox(close);
        bottom.setAlignment(Pos.CENTER);
        BorderPane root = new BorderPane(centre, null, null, bottom, null);
        Stage stage = new Stage();
        stage.setMinHeight(800);
        stage.setMinWidth(800);
        close.setOnAction(e -> stage.close());
        if(Debug.debugMode)
        {
            Button diagEmail = new Button("Send Diagnostic Email");
            diagEmail.setOnAction(e -> {
                SendDiagEmail sde = new SendDiagEmail();
                sde.sendEmail();
                close.fire();
            });
            bottom.getChildren().add(diagEmail);
        }
        stage.setScene(new Scene(root));
        stage.setTitle("About");
        System.out.println(DelLog.getInstance().getFolderPath());
        stage.show();
    }

    private TextFlow emailLabel()
    {
        Text a = new Text("For any issues, please email ");
        Text e = new Text("cordel@delstar404.com");
        e.setStyle("-fx-font-weight: bold;");
        Text b = new Text(" with a description of the problem");
        TextFlow tf = new TextFlow(a, e, b);
        tf.setTextAlignment(TextAlignment.CENTER);
        return tf;
    }
}
