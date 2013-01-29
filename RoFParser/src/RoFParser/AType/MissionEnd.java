package RoFParser.AType;

/**
 * AType=7 signifies the end of the mission
 * 
 * @author Ceud
 */
public class MissionEnd extends AType
{
    
    public MissionEnd(String logEntry)
    {
        super(logEntry);
        parseLine();
    }

    @Override
    public final void parseLine()
    {
        this.tick = Integer.parseInt(this.logEntry.substring(this.logEntry.indexOf("T:") + 2, 
                                                             this.logEntry.indexOf("AType:") - 1).trim());
        this.aType = Integer.parseInt(this.logEntry.substring(this.logEntry.indexOf("AType:") + 6).trim());
    }
    
    @Override
    public String toString()
    {
        return this.tick + "; " + 
               this.aType;
    }
    
}
