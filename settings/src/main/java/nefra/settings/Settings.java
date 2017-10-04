package nefra.settings;

import org.apache.commons.io.FileUtils;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.Properties;

public class Settings {
    private static Properties settingsPropertyFile = new Properties();
    private static File settingsFile = new File("./NEFRA Data/Settings/settings");

    private static Settings instance;

    public Settings() {
        instance = this;
    }

    @Contract(pure = true)
    public static Settings getInstance() {
        return instance;
    }

    public static void initSettings()
    {
        try {
            FileUtils.openOutputStream(settingsFile, true);
            InputStream input = new FileInputStream(settingsFile.getAbsoluteFile());
            settingsPropertyFile.load(input);
            input.close();
        }
        catch (IOException IOE) { System.out.println("An IO Error occurred"); }
    }

    private static void storeSettings() {
        try {
            FileOutputStream output = FileUtils.openOutputStream(settingsFile, false);
            settingsPropertyFile.store(output, null);
        }
        catch (IOException e) { e.printStackTrace(); }
    }

    private static void storeSettings(String comment) {
        try {
            FileOutputStream output = FileUtils.openOutputStream(settingsFile, false);
            settingsPropertyFile.store(output, comment);
            output.close();
        }
        catch (IOException e) { e.printStackTrace(); }
    }

    public static void writeSetting(@NotNull String propertyName, @NotNull String propertyValue) {
        settingsPropertyFile.setProperty(propertyName, propertyValue);
        storeSettings();
    }

    public static void writeSetting(@NotNull String propertyName, @NotNull String propertyValue, String comment) {
        settingsPropertyFile.setProperty(propertyName, propertyValue);
        storeSettings(comment);
    }

    public static String getSetting(@NotNull String propertyName) {
        Settings.initSettings();
        return settingsPropertyFile.getProperty(propertyName, "null");
    }

    public static boolean containsSetting(@NotNull String propertyName) {
        Settings.initSettings();
        return settingsPropertyFile.containsKey(propertyName);
    }

    public static void removeSetting(@NotNull String propertyName) {
        settingsPropertyFile.remove(propertyName);
        storeSettings();
    }
}
