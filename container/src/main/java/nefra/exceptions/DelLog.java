package nefra.exceptions;

import nefra.misc.Debug;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static nefra.misc.Paths.baseLogPath;
import static nefra.misc.Paths.logExt;

public class DelLog {
    private FileInputStream input;
    private PrintStream output;
    private final File LogFile;
    private static DelLog instance;

    public static DelLog getInstance() { return (instance == null) ? instance = new DelLog() : instance; }

    private DelLog()
    {
        ZonedDateTime t = ZonedDateTime.now();
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern("'T='HH-mm-ss'++Z='z");
        String currentDirectory = String.format("%s%s/", baseLogPath, LocalDate.now());
        String currentFile = String.format("%s%s%s", currentDirectory, t.format(sdf), logExt);
        LogFile = new File(currentFile);
        if(Debug.debugMode)
            System.out.println(LogFile.getPath());
        CreateFiles();
        try {
            input = new FileInputStream(LogFile);
            output = new PrintStream(new FileOutputStream(LogFile, true), true);
            System.setOut(output);
            System.setErr(output);
        } catch (IOException ioe) { ioe.printStackTrace(); }
    }

    private void CreateFiles()
    {
        try{
            if(!LogFile.exists())
                if(!LogFile.getParentFile().exists())
                    if(!LogFile.getParentFile().mkdirs())
                        throw new CannotCreateException("Unable to create Log Directories!");
                if (!LogFile.createNewFile())
                    throw new CannotCreateException("Unable to create Log File!");
        }
        catch(CannotCreateException | IOException e) { e.printStackTrace(); }
        catch(Exception ex) { System.out.println("General Error"); ex.printStackTrace(); }
    }

    public void Log(String message)
    {
        String nl = "\n";
        try {
            output.write(nl.getBytes());
            output.write(new String(new char[16]).replace("\0", "-").getBytes());
            output.write(nl.getBytes());
            output.write(String.format("%s: %s\n", LocalTime.now(), message).getBytes());
            output.write(nl.getBytes());
            output.write(new String(new char[16]).replace("\0", "-").getBytes());
            output.write(nl.getBytes());
        }
        catch(IOException e) { e.printStackTrace(); }
    }

    public void Log(Exception ex)
    {
        String nl = "\n";
        try {
            output.write(nl.getBytes());
            output.write(new String(new char[16]).replace("\0", "!").getBytes());
            output.write(nl.getBytes());
            output.write(String.format("EXCEPTION OCCURRED: timestamp = %s\n", LocalTime.now()).getBytes());
            output.write(String.format("Exception is of %s type.\n", ex.getClass().getSimpleName().toUpperCase()).getBytes());
            output.write(String.format("Exception message is: %s\n", ex.getMessage()).getBytes());
            output.write(nl.getBytes());
            output.write("STARTING STACK TRACE\n".getBytes());
            output.write(nl.getBytes());
            ex.printStackTrace(output);
            output.write(nl.getBytes());
            output.write("END OF STACK TRACE\n".getBytes());
            output.write(nl.getBytes());
            output.write(new String(new char[16]).replace("\0", "!").getBytes());
            output.write(nl.getBytes());
        }
        catch(IOException e) { e.printStackTrace(); }
    }

    public void Log(Exception ex, String message)
    {
        String nl = "\n";
        try {
            output.write(nl.getBytes());
            output.write(new String(new char[16]).replace("\0", "-").getBytes());
            output.write(nl.getBytes());
            output.write(String.format("%s: %s\n", LocalTime.now(), message).getBytes());
            output.write(nl.getBytes());
            output.write(new String(new char[16]).replace("\0", "-").getBytes());
            output.write(nl.getBytes());
            output.write(new String(new char[16]).replace("\0", "!").getBytes());
            output.write(nl.getBytes());
            output.write(String.format("EXCEPTION OCCURRED: timestamp = %s\n", LocalTime.now()).getBytes());
            output.write(String.format("Exception is of %s type.\n", ex.getClass().getSimpleName().toUpperCase()).getBytes());
            output.write(String.format("Exception message is: %s\n", ex.getMessage()).getBytes());
            output.write(nl.getBytes());
            output.write("STARTING STACK TRACE\n".getBytes());
            output.write(nl.getBytes());
            ex.printStackTrace(output);
            output.write(nl.getBytes());
            output.write("END OF STACK TRACE\n".getBytes());
            output.write(nl.getBytes());
            output.write(new String(new char[16]).replace("\0", "!").getBytes());
            output.write(nl.getBytes());
        }
        catch(IOException e) { e.printStackTrace(); }
    }

    public void DumpLog()
    {
        String line;
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(input)))
        {
            System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
            System.setErr(new PrintStream(new FileOutputStream(FileDescriptor.err)));
           while((line = reader.readLine()) != null) { System.out.println(line); }
            System.setOut(output);
            System.setErr(output);
        } catch (IOException ex) { ex.printStackTrace(); }
    }

    public boolean Close()
    {
        boolean success = false;
        try
        {
            input.close();
            output.close();
            System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
            System.setErr(new PrintStream(new FileOutputStream(FileDescriptor.err)));
            success = true;
            instance = null;
        } catch (IOException ex) { ex.printStackTrace(); }

        return success;
    }

    public String getFileName() { return LogFile.getAbsolutePath(); }
    public String getFolderPath() { return LogFile.getParentFile().getParentFile().getAbsolutePath(); }
}
