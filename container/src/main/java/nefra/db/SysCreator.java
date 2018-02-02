package nefra.db;

import nefra.club.Club;
import nefra.exceptions.CannotCreateException;
import nefra.exceptions.DelLog;
import nefra.game.Division;
import nefra.game.Game;
import nefra.referee.Referee;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.UUID;

import static nefra.misc.Paths.*;

public class SysCreator {
    private ObjectOutputStream output;
    private static SysCreator instance;

    public static SysCreator getInstance() { return (instance == null) ? instance = new SysCreator() : instance; }

    public void Setup()
    {
        try
        {
            if(!refFile.exists()) {
                if (!refFile.mkdirs())
                    throw new CannotCreateException("Unable to create Ref Directory!");
            }
            if(!cluFile.exists()) {
                if (!cluFile.mkdirs())
                    throw new CannotCreateException("Unable to create Clu Directory!");
            }
            if(!gamFile.exists()) {
                if (!gamFile.mkdirs())
                    throw new CannotCreateException("Unable to create Gam Directory!");
            }
            if(!divFile.exists()) {
                if (!divFile.mkdirs())
                    throw new CannotCreateException("Unable to create Div Directory!");
            }
        }
        catch(CannotCreateException cce) { DelLog.getInstance().Log(cce); }
        catch (Exception e) { DelLog.getInstance().Log(e, "General Exception"); }
    }

    public boolean Referee(Referee referee, boolean create)
    {
        boolean success;
        if(create)
            referee.setReferee_id(UUID.randomUUID());
        try
        {
            output = new ObjectOutputStream(new FileOutputStream(String.format("%s%s%s",ref, referee.getRefereeId(), refExt)));
            output.writeObject(referee);
        }
        catch (IOException ex) { DelLog.getInstance().Log(ex); }
        finally
        {
            success = closeOutput(output);
        }
        return success;
    }

    public boolean Club(Club club, boolean create)
    {
        boolean success;
        if(create)
            club.setClub_id(UUID.randomUUID());
        try
        {
            output = new ObjectOutputStream(new FileOutputStream(String.format("%s%s%s",clu, club.getClubId(), cluExt)));
            output.writeObject(club);
        }
        catch (IOException ex) { DelLog.getInstance().Log(ex); }
        finally
        {
            success = closeOutput(output);
        }
        return success;
    }

    public boolean Division(Division division, boolean create)
    {
        boolean success;
        if(create)
            division.setDivisionId(UUID.randomUUID());
        try
        {
            output = new ObjectOutputStream(new FileOutputStream(String.format("%s%s%s",div, division.getDivisionId(), divExt)));
            output.writeObject(division);
        }
        catch (IOException ex) { DelLog.getInstance().Log(ex); }
        finally
        {
            success = closeOutput(output);
        }
        return success;
    }

    public boolean Game(Game game, boolean create)
    {
        boolean success;
        if(create)
            game.setGameId(UUID.randomUUID());
        try
        {
            output = new ObjectOutputStream(new FileOutputStream(String.format("%s%s%s",gam, game.getGameId(), gamExt)));
            output.writeObject(game);
        }
        catch (IOException ex) { DelLog.getInstance().Log(ex); }
        finally
        {
            success = closeOutput(output);
        }
        return success;
    }

    public boolean Remove(String type, UUID id)
    {
        try {
            String filename;
            switch (type.toLowerCase()) {
                case "referee":
                    filename = String.format("%s%s%s", ref, id, refExt);
                    break;
                case "club":
                    filename = String.format("%s%s%s", clu, id, cluExt);
                    break;
                case "division":
                    filename = String.format("%s%s%s", div, id, divExt);
                    break;
                case "game":
                    filename = String.format("%s%s%s", gam, id, gamExt);
                    break;
                default:
                    throw new IllegalArgumentException("Not a valid type!");
            }
            File file = new File(filename);
            return file.delete();
        } catch (IllegalArgumentException e) { DelLog.getInstance().Log(e); }
        return false;
    }

    private boolean closeOutput(ObjectOutputStream output) {
        if(output != null) {
            try {
                output.close();
                return true;
            } catch (IOException e) { DelLog.getInstance().Log(e); }
        }
        return false;
    }
}
