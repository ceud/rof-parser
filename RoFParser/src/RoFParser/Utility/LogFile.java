package RoFParser.Utility;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Basic utilities to allow interaction with generated mission log files
 * 
 * @author Ceud
 */
public class LogFile
{
    
    private LogFile()
    {
        //
    }
    
    /**
     * Concatenates all supplied mission logs into a single file
     * 
     * @param missionLogs Array of File objects of mission logs
     * @return String array of all lines of all concatenated logs
     */
    public static String[] Concatenate(File[] missionLogs)
    {
        if (Config.ConcatLogs())
        {
            ArrayList<String> wholeLog = new ArrayList<String>();
            //String[] wholeLog = new String[0];
            try
            {
                int j = 0;
                System.out.println("Processing " + missionLogs[0].getPath());
                for (int i = 0; i < missionLogs.length; i++)
                {
                        BufferedReader br = new BufferedReader(new FileReader(missionLogs[i].getPath()));

                        String line = br.readLine();
                        while (line != null)
                        {
                            wholeLog.add(line);
                            //wholeLog[wholeLog.length] = line;
                            line = br.readLine();
                        }
                        br.close();
                }
                System.out.println("Mission logs have been concatenated");
            }
            catch (IOException e)
            {
                System.out.println("Failed to concatenate logs!\n" + e.getMessage());
            }
            return wholeLog.toArray(new String[wholeLog.size()]);
        }
        else
        {
            System.out.println("Cannot concatenate logs. Disallowed in config.ini");
            return null;
        }
        
    }
    
    /**
     * Gets unique mission dates based on the datetime in log filename
     * 
     * @param fileNames String array of mission log filenames
     * @return Unique mission dates from all supplied filenames
     */
    public static String[] UniqueMissionDates(String[] fileNames)
    {
        ArrayList<String> missionDates = new ArrayList<String>();
        
        for (int i = 0; i < fileNames.length; i++)
        {
            String tmpMissDate = fileNames[i].substring(14, 33);
            if (!missionDates.contains(tmpMissDate))
            {
                missionDates.add(tmpMissDate);
            }
        }
        return missionDates.toArray(new String[missionDates.size()]);
    }
    
    /**
     * Gets unique ended mission dates based on the datetime in log filename
     * 
     * @param fileNames String array of mission log filenames
     * @return Unique ended mission dates from all supplied filenames
     */
    public static String[] UniqueEndedMissionDates(String folder, String[] fileNames)
    {
        ArrayList<String> missionDates = new ArrayList<String>();
        
        for (int i = 0; i < fileNames.length; i++)
        {
            
            String tmpMissDate = fileNames[i].substring(14, 33);
            try {
                BufferedReader in = new BufferedReader(new FileReader(folder + fileNames[i]));
                String str = in.readLine();
                while (str != null) {
                    if (str.trim().endsWith("AType:7"))
                    {
                        if (!missionDates.contains(tmpMissDate)) {
                            missionDates.add(tmpMissDate);
                        }
                    }
                    str = in.readLine();
                }
                in.close();
            } catch (IOException e) {
            }
        }
        return missionDates.toArray(new String[missionDates.size()]);
    }
    
    /**
     * Gets all mission logs
     * 
     * @param folder Location of mission log files. i.e. "/data"
     * @return Array of files containing all log-files
     */
    public static File[] AllLogs(String folder)
    {
        File f1 = new File(folder);
        FilenameFilter only = new MissionLogFilter();
        return f1.listFiles(only);
    }
    
    /**
     * Gets all mission logs for given date, sorted by ascending file modification date
     * 
     * @param folder Location of mission log files. i.e. "/data"
     * @return Array of files containing all log-files for given date
     */
    public static File[] AllLogs(String folder, String missDate)
    {
        File f1 = new File(folder);
        FilenameFilter only = new MissionLogFilter(missDate);
        File[] files = f1.listFiles(only);
        
        Arrays.sort(files, new Comparator<File>(){
        public int compare(File f1, File f2)
        {
            return Long.valueOf(f1.lastModified()).compareTo(f2.lastModified());
        } });
        return files;
    }
    
    /**
     * Gets all filenames for mission logs
     * 
     * @param folder Location of mission log files. i.e. "/data"
     * @return Array of strings containing the filenames for all log-files
     */
    public static String[] AllLogNames(String folder)
    {
        File f1 = new File(folder);
        FilenameFilter only = new MissionLogFilter();
        return f1.list(only);
    }
    
    /**
     * Saves mission log to specified directory
     * 
     * @param folder Location to store mission log file. i.e. "/log-archive"
     * @param fileName String of mission log filename
     * @param log String array of mission log entries
     */
    public static void Save(String folder, String fileName, String[] log)
    {
        if (Config.ArchiveLogs())
        {
            try
            {
                PrintWriter out = new PrintWriter(new FileWriter(folder + "/" + fileName));
                for (int i = 0; i < log.length; i++)
                {
                    // Write text to file
                    out.println(log[i]);
                }
                out.close();
                System.out.println("File saved!");
            }
            catch (IOException e)
            {
                //e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
        else
        {
            System.out.println("Cannot save log. Disallowed in config.ini");
        }
    }
    
    /**
     * Deletes all supplied files
     * 
     * @param log Array of File objects to be deleted
     */
    public static void Delete(File[] log)
    {
        if (Config.DeleteLogs())
        {
            for (int i = 0; i < log.length; i++)
            {
                if (!log[i].delete())
                {
                    System.out.println("Failed to delete file: " + log[i].getName());
                }
            }
            System.out.println("Files deleted!");
        }
        else
        {
            System.out.println("Cannot delete logs. Disallowed in config.ini");
        }
    }
    
}