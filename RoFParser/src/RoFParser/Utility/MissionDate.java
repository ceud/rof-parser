package RoFParser.Utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ceud
 */
public class MissionDate extends Date
{
    protected long unixTime;
    protected String missionDate;
    
    public MissionDate(long unixTime)
    {
        super(unixTime * 1000l);
        this.unixTime = unixTime;
        this.missionDate = MissionDate.LogTime(this.unixTime);
    }
    
    public static String LogTime(long unixTime)
    {
        //dow mon dd hh:mm:ss zzz yyyy
        //2011-12-14_00-55-11
        String logTime = "";
        
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        
        Date date = new Date((unixTime * 1000l));
        logTime = format.format(date); //format.toString();
        
        return logTime;
    }
    
    /**
     * Gets the current system time in unixtime format
     * 
     * @return Unixtime value for current system time
     */
    public static long UnixTime()
    {
        long unixTime = System.currentTimeMillis() / 1000l;
        return unixTime;
    }
    
    /**
     * Gets the specified time in unixtime format (yyyy-MM-dd_HH-mm-ss)
     * 
     * @param dateTime String representing a datetime
     * @return Unixtime value for current system time
     */
    public static long UnixTime(String dateTime)
    {
        
        try
        {
            
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
            //Date date = format.parse("" + (unixTime * 1000l));
            Date date = format.parse(dateTime); //format.toString();
            return date.getTime() / 1000l;
        }
        catch (ParseException ex)
        {
            Logger.getLogger(MissionDate.class.getName()).log(Level.SEVERE, null, ex);
            return 0l;
        }
    }
    
    /**
     * Gets the specified time in unixtime format
     * 
     * @param dateTimeMS String representing a datetime in milliseconds
     * @return Unixtime value for current system time
     */
    public static long UnixTime(long dateTimeMS)
    {
        return dateTimeMS / 1000l;
    }
    
    @Override
    public String toString()
    {
        //dow mon dd hh:mm:ss zzz yyyy
        return this.missionDate;
    }
    
    public long getUnixTime()
    {
        return this.unixTime;
    }
    
    public String getMissionDate()
    {
        return this.missionDate;
    }
}
