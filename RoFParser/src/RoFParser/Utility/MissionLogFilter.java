package RoFParser.Utility;

import java.io.*;

/**
 * Filters mission logs from all other files in a directory
 * 
 * @author Ceud
 */
public class MissionLogFilter implements FilenameFilter
{
    /**
     * String of date as found in filename of mission logs
     */
    protected String missionDate;
    
    /**
     * Constructs a new filter object
     */
    public MissionLogFilter()
    {
        this.missionDate = "";
    }
    
    /**
     * Constructs a new filter object with a specified date
     * 
     * @param missionDate The datetime as listed in mission log filename
     */
    public MissionLogFilter(String missionDate)
    {
        this.missionDate = missionDate;
    }

    /**
     * Tests if a specified file should be included in a file list.
     * 
     * @param dir The directory in which the file was found.
     * @param name The name of the file.
     * @return True if filename is valid, else false
     */
    @Override
    public boolean accept(File dir, String name)
    {
        if (name.endsWith(".txt") && name.startsWith("missionReport(" + missionDate))
        {
            return true;
        }
        return false;
    }
}