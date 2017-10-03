package nefra.settings;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.Properties;

public class Settings {
    private static Properties settingsPropertyFile = new Properties();
    private static File settingsFile = new File("./NEFRA Data/Settings/settings");
    private static InputStream input;
    private static FileOutputStream output;

    public void initSettings()
    {
        try {
            FileUtils.openOutputStream(settingsFile, true);
            input = new FileInputStream(settingsFile.getAbsoluteFile());
            settingsPropertyFile.load(input);
            input.close();
        }
        catch (IOException IOE) { System.out.println("An IO Error occurred"); }
        try {
            output = new FileOutputStream(settingsFile.getAbsoluteFile());
            settingsPropertyFile.store(output, null);
            output.close();
        }
        catch (FileNotFoundException FNFE) { System.out.println("File Not Found!!");}
        catch (IOException IOE) { System.out.println("An IO Error occurred"); }
    }

    public static void writeSetting(String propertyName, String propertyValue)
    {
        try {
            FileOutputStream output = FileUtils.openOutputStream(settingsFile, false);
            settingsPropertyFile.setProperty(propertyName, propertyValue);
            settingsPropertyFile.store(output, null);
        }
        catch (IOException e) { e.printStackTrace(); }
    }

    public static void writeSetting(String propertyName, String propertyValue, String comment)
    {
        try {
            output = FileUtils.openOutputStream(settingsFile, false);
            settingsPropertyFile.setProperty(propertyName, propertyValue);
            settingsPropertyFile.store(output, comment);
        }
        catch (IOException e) { e.printStackTrace(); }
    }
}
