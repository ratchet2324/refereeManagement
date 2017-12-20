package nefra.db;

import nefra.club.Club;
import nefra.exceptions.DelLog;
import nefra.game.Division;
import nefra.game.Game;
import nefra.referee.Referee;

import java.io.*;

import static nefra.misc.Paths.*;

public class SysLoader {
    private static SysLoader instance;
    private boolean setup = false;
    private ObjectInputStream input;

    public static SysLoader getInstance() { return (instance == null) ? instance = new SysLoader() : instance; }

    private SysLoader()
    {
        if(!setup)
        {
            Setup();
            LoadRef();
            LoadClu();
            LoadDiv();
            LoadGam();
        }
    }

    private void Setup()
    {
        try {
            if (!refFile.exists())
                throw new FileNotFoundException("Cannot find Ref Directory");
            if (!cluFile.exists())
                throw new FileNotFoundException("Cannot find Clu Directory");
            if (!gamFile.exists())
                throw new FileNotFoundException("Cannot find Gam Directory");
            if (!divFile.exists())
                throw new FileNotFoundException("Cannot find Div Directory");
            setup = true;
        }
        catch(FileNotFoundException ex) { DelLog.getInstance().Log(ex); }
    }

    private void LoadRef()
    {
        try {
            File[] files = refFile.listFiles();
            assert files != null;
            for (File file : files) {
                input = new ObjectInputStream(new FileInputStream(file));
                Referee t = (Referee) input.readObject();
                Referee.refereeList.add(t);
                input.close();
            }
        }
        catch(IOException | NullPointerException | ClassNotFoundException ex) {DelLog.getInstance().Log(ex); }
    }

    private void LoadClu()
    {
        try {
            File[] files = cluFile.listFiles();
            assert files != null;
            for (File file : files) {
                input = new ObjectInputStream(new FileInputStream(file));
                Club t = (Club) input.readObject();
                Club.clubList.add(t);
                input.close();
            }
        }
        catch(IOException | NullPointerException | ClassNotFoundException ex) {DelLog.getInstance().Log(ex); }
    }

    private void LoadDiv()
    {
        try {
            File[] files = divFile.listFiles();
            assert files != null;
            for (File file : files) {
                input = new ObjectInputStream(new FileInputStream(file));
                Division t = (Division) input.readObject();
                Division.divisionList.add(t);
                input.close();
            }
        }
        catch(IOException | NullPointerException | ClassNotFoundException ex) {DelLog.getInstance().Log(ex); }
    }

    private void LoadGam()
    {
        try {
            File[] files = gamFile.listFiles();
            assert files != null;
            for (File file : files) {
                input = new ObjectInputStream(new FileInputStream(file));
                Game t = (Game) input.readObject();
                Game.gameList.add(t);
                input.close();
            }
        }
        catch(IOException | NullPointerException | ClassNotFoundException ex) {DelLog.getInstance().Log(ex); }
    }
}
