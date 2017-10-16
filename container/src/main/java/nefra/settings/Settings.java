package nefra.settings;

import org.apache.commons.io.FileUtils;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.ArrayList;
import java.util.Properties;

public class Settings {
    private static Properties settingsPropertyFile = new Properties();
    public static String base = "./NEFRA Data/Settings/";
    private static File settingsFile = new File(base + "settings.properties");

    private static Settings instance;

    public Settings() {
        instance = this;
    }

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

            if(!settingsPropertyFile.containsKey("DatabaseInstantiation"))
                writeSetting("DatabaseInstantiation", "true");
        } catch (IOException IOE) {
            System.out.println("An IO Error occurred");
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
            e.printStackTrace();
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
            e.printStackTrace();
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

    /**
     * Remove a setting from the file.
     *
     * @param propertyName The name of the setting to remove.
     */
    public static void removeSetting(@NotNull String propertyName) {
        settingsPropertyFile.remove(propertyName);
        storeSettings();
    }

    /**
     * Save the content of a file without a comment.
     */
    private static void saveFile(File file) {
        try {
            FileOutputStream output = FileUtils.openOutputStream(file, false);
            settingsPropertyFile.store(output, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}