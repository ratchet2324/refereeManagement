package nefra.misc;

import java.io.File;

 public class Paths {
    //Data Path
    final static private String base = "./NEFRA Data/";

    //IO Paths
    final static public String ref = String.format("%sref/", base);
    final static public String clu = String.format("%sclu/", base);
    final static public String gam = String.format("%sgam/", base);
    final static public String div = String.format("%sdiv/", base);
    final static public File refFile = new File(ref);
    final static public File cluFile = new File(clu);
    final static public File gamFile = new File(gam);
    final static public File divFile = new File(div);

    //Log Paths
    final static public String baseLogPath = String.format("%sLogs/", base);


    //Settings Paths
    final private static String baseSettingsPath = String.format("%sSettings/", base);
    final public static File settingsFile = new File(String.format("%ssettings.properties", baseSettingsPath));

    //Extensions
    final static public String refExt = ".ref";
    final static public String cluExt = ".clu";
    final static public String gamExt = ".gam";
    final static public String divExt = ".div";
    final static public String logExt = ".dellog";
}
