package nefra.jfx.misc;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;


public class BorderTitlePane extends StackPane{
    public BorderTitlePane(String titleString, Node module, double minWidth, double minHeight) {
        getStylesheets().add(getClass().getResource("/nefra/jfx/misc/BorderTitlePane.css").toExternalForm());
        Label title = new Label(" " + titleString + " ");
        title.getStyleClass().add("bordered-titled-title");
        StackPane.setAlignment(title, Pos.TOP_CENTER);

        StackPane modulePane = new StackPane();
        module.getStyleClass().add("bordered-titled-content");
        modulePane.getChildren().add(module);

        setMinHeight(minHeight);
        setMinWidth(minWidth);
        getStyleClass().add("bordered-titled-border");
        getChildren().addAll(title, modulePane);
    }

    public BorderTitlePane(Node module, double minWidth, double minHeight) {
        getStylesheets().add(getClass().getResource("/nefra/jfx/misc/BorderTitlePane.css").toExternalForm());

        StackPane modulePane = new StackPane();
        module.getStyleClass().add("bordered-titled-content");
        modulePane.getChildren().add(module);

        setMinHeight(minHeight);
        setMinWidth(minWidth);
        getStyleClass().add("bordered-titled-border");
        getChildren().addAll(modulePane);
    }

    public void addTitle(String titleString)
    {
        Label title = new Label(" " + titleString + " ");
        title.getStyleClass().add("bordered-titled-title");
        StackPane.setAlignment(title, Pos.TOP_CENTER);
        getChildren().add(title);
    }
}
