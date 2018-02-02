package nefra.jfx.misc;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import nefra.club.Club;
import nefra.game.Division;
import nefra.game.Game;
import nefra.jfx.Main;
import nefra.jfx.club.CreateClubGUI;
import nefra.jfx.game.CreateDivisionGUI;
import nefra.jfx.game.CreateGameGUI;
import nefra.jfx.referee.CreateRefereeGUI;
import nefra.referee.Referee;
import nefra.settings.Settings;

public class NewSetup {
    public boolean referee()
    {
        Alert ref = new Alert(Alert.AlertType.NONE);
        ref.setHeaderText(null);
        ref.setGraphic(null);
        ref.setTitle(null);
        ref.setContentText("You have no referees.\nWould you like to add some now?");
        ref.getDialogPane().getButtonTypes().clear();
        ref.getDialogPane().getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);
        ref.showAndWait();
        if(ref.getResult() == ButtonType.YES)
        {
            Main.getInstance().changeScene(new Scene(new CreateRefereeGUI().initGUI()));
            return true;
        }
        return false;
    }

    public boolean club()
    {
        Alert club = new Alert(Alert.AlertType.NONE);
        club.setHeaderText(null);
        club.setGraphic(null);
        club.setTitle(null);
        club.setContentText("You have no clubs.\nWould you like to add some now?");
        club.getDialogPane().getButtonTypes().clear();
        club.getDialogPane().getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);
        club.showAndWait();
        if(club.getResult() == ButtonType.YES)
        {
            Main.getInstance().changeScene(new Scene(new CreateClubGUI().initGUI()));
            return true;
        }
            return false;
    }

    public boolean division()
    {
        Alert div = new Alert(Alert.AlertType.NONE);
        div.setHeaderText(null);
        div.setGraphic(null);
        div.setTitle(null);
        div.setContentText("You have no divisions.\nWould you like to add some now?");
        div.getDialogPane().getButtonTypes().clear();
        div.getDialogPane().getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);
        div.showAndWait();
        if(div.getResult() == ButtonType.YES)
        {
            Main.getInstance().changeScene(new Scene(new CreateDivisionGUI().initGUI()));
            return true;
        }
            return false;
    }

    public boolean game()
    {
        Alert game = new Alert(Alert.AlertType.NONE);
        game.setHeaderText(null);
        game.setGraphic(null);
        game.setTitle(null);
        game.setContentText("You have no games.\nWould you like to add some now?");
        game.getDialogPane().getButtonTypes().clear();
        game.getDialogPane().getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);
        game.showAndWait();
        if(game.getResult() == ButtonType.YES)
        {
            Main.getInstance().changeScene(new Scene(new CreateGameGUI().initGUI()));
            return true;
        }
        return false;
    }

    public void FirstRun()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setGraphic(null);
        alert.setHeaderText(null);
        alert.setTitle("First Time Setup");
        alert.setContentText("This is the first time you have run this program.\n" +
                "The following windows will allow you to set up your first referees, clubs, divisions and games.\n" +
                "If you encounter any issues, please see the 'Help' menu for information on how to get in contact.\n" +
                "Thank you for using the program!");
        alert.showAndWait();
        Settings.writeSetting("FirstRun", "false");
    }
}
