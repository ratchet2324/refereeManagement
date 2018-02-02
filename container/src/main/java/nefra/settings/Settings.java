package nefra.settings;

import nefra.exceptions.DelLog;
import org.apache.commons.io.FileUtils;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.Properties;

import static nefra.misc.Paths.settingsFile;

/**
 * Settings class is so the Properties files can be utilised, it allows settings to be added, removed and queried.
 * @author Cordel Murphy
 * @version 1.0
 * @since 1.0
 */
public class Settings {
    private static final Properties settingsPropertyFile = new Properties();
    private static Settings instance;

    public Settings() { instance = this; }

    @Contract(pure = true)
    public static Settings getInstance() {
        return instance;
    }

    /**
     * Loads the settings file upon starting program.
     */
    public static void initSettings() {
        try {
            FileUtils.openOutputStream(settingsFile, true);
            InputStream input = new FileInputStream(settingsFile.getAbsoluteFile());
            settingsPropertyFile.load(input);
            input.close();

            if(!settingsPropertyFile.containsKey("InitialisationNeeded"))
                writeSetting("InitialisationNeeded", "true");
            if(!settingsPropertyFile.containsKey("FirstRun"))
                writeSetting("FirstRun", "true");
        } catch (IOException IOE) {
            DelLog.getInstance().Log(IOE);
        }
    }

    /**
     * Save the settings to the file without a comment.
     */
    private static void storeSettings() {
        try {
            FileOutputStream output = FileUtils.openOutputStream(settingsFile, false);
            settingsPropertyFile.store(output, null);
        } catch (IOException e) {
            DelLog.getInstance().Log(e);
        }
    }

    /**
     * Save the settings to the file WITH a comment
     *
     * @param comment The comment to include in the settings file.
     */
    private static void storeSettings(String comment) {
        try {
            FileOutputStream output = FileUtils.openOutputStream(settingsFile, false);
            settingsPropertyFile.store(output, comment);
            output.close();
        } catch (IOException e) {
            DelLog.getInstance().Log(e);
        }
    }

    /**
     * Insert a setting into the settings file. WITHOUT a comment for the setting
     *
     * @param propertyName  The name of the setting to save as.
     * @param propertyValue The value of the setting to save
     */
    public static void writeSetting(@NotNull String propertyName, @NotNull String propertyValue) {
        settingsPropertyFile.setProperty(propertyName, propertyValue);
        storeSettings();
    }

    /**
     * Insert a setting into the settings file. WITH a comment for the setting
     *
     * @param propertyName  The name of the setting to save as.
     * @param propertyValue The value of the setting to save
     * @param comment       The comment to append alongside the setting
     */
    public static void writeSetting(@NotNull String propertyName, @NotNull String propertyValue, String comment) {
        settingsPropertyFile.setProperty(propertyName, propertyValue);
        storeSettings(comment);
    }

    /**
     * Return the setting from the file, if it is not found in the file, it will return null as a string.
     *
     * @param propertyName The name of the setting to find.
     * @return the value of the property searched for.
     */
    public static String getSetting(@NotNull String propertyName) {
        Settings.initSettings();
        return settingsPropertyFile.getProperty(propertyName, "null");
    }

    /**
     * Searches the settings for the particular setting
     *
     * @param propertyName The name of the setting to find
     * @return True if the setting is in the file, false otherwise.
     */
    public static boolean containsSetting(@NotNull String propertyName) {
        Settings.initSettings();
        return settingsPropertyFile.containsKey(propertyName);
    }
}