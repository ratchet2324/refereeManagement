package nefra.misc;

import org.apache.commons.lang3.StringUtils;

public class Debug {
    public static boolean debugMode = false;

    public static void setDebugMode(boolean debugMode) { Debug.debugMode = debugMode; }

    public static void debugInfo()
    {
        System.out.println(
                StringUtils.leftPad("DEBUG MODE ACTIVATED",50) + "\n" +
                        StringUtils.repeat('-', 80) + "\n" +
                        StringUtils.leftPad("**NEFRA DEBUG INFO**",50) + "\n" +
                        StringUtils.repeat('-', 80) + "\n" +
                        "OS Name: " + System.getProperty("os.name") + "\n" +
                        "OS Version: " + System.getProperty("os.version") + "\n" +
                        "OS Type: " + System.getProperty("os.arch") + "\n" +
                        "Java Version: " + System.getProperty("java.version") + "\n" +
                        StringUtils.repeat('-', 80)
        );
    }

    public static void printSection(boolean enter, String section)
    {
        String s = enter ? "entering" : "exiting";
        System.out.printf("\n%s section: %s\n", s, section);
    }
}
