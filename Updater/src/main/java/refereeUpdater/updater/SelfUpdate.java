package refereeUpdater.updater;

import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

public class SelfUpdate {

    public static void main(String[] args)
    {
        SelfUpdate su = new SelfUpdate();
        if(su.checkInternetConnection())
        {
            if(su.checkForNewVersion())
            {
                su.getNewVersion();
                su.copyNewVersion();
                su.startProgram();
            }
            else su.startProgram();
        }
        else su.startProgram();
    }

    private boolean checkInternetConnection()
    {
        try
        {
            URL url = new URL("http://www.google.com");
            URLConnection urlConnection = url.openConnection();
            urlConnection.connect();
            return true;
        } catch (IOException e) { e.printStackTrace(); }
        return false;
    }

    private boolean checkForNewVersion()
    {
        try
        {
            Properties server = new Properties();
            server.load(new InputStreamReader(
                        new URL("http://delstar404.com/progs/refmanage/ov-version.txt").openStream()
                        , "UTF-8")
                    );
            int serverMajor = Integer.parseInt(server.getProperty("versionMajor"));
            int serverMinor = Integer.parseInt(server.getProperty("versionMinor"));
            int serverMicro = Integer.parseInt(server.getProperty("versionMicro"));

            Properties internal = new Properties();
            internal.load(new InputStreamReader(getClass().getResourceAsStream("/version.properties")));
            int internalMajor = Integer.parseInt(internal.getProperty("versionMajor"));
            int internalMinor = Integer.parseInt(internal.getProperty("versionMinor"));
            int internalMicro = Integer.parseInt(internal.getProperty("versionMicro"));

            if(serverMajor > internalMajor) return true;
            else if(serverMajor == internalMajor && serverMinor > internalMinor) return true;
            else if(serverMajor == internalMajor && serverMinor == internalMinor && serverMicro > internalMicro) return true;

        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    private void getNewVersion()
    {
        try
        {
            URL url = new URL("http://delstar404.com/progs/refmanage/latest.jar");
            File newVersion = new File(getClass().getProtectionDomain().getCodeSource().getLocation().getPath());
            FileUtils.copyURLToFile(url, newVersion, 2000, 2000);
        } catch (IOException e) { e.printStackTrace(); }
    }

    private void copyNewVersion()
    {
        File latest = new File(getClass().getProtectionDomain().getCodeSource().getLocation().getPath());
        File reqLocation = new File("../Referee Management.jar");
        try { FileUtils.copyFile(latest, reqLocation); }
        catch(IOException e) { e.printStackTrace(); }
    }

    private void startProgram()
    {
        try { Runtime.getRuntime().exec("java -jar \"../Referee Management.jar\""); }
        catch(IOException e) { e.printStackTrace(); }
    }
}
