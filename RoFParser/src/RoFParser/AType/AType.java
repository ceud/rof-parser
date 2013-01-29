package RoFParser.AType;

/**
 * The AType class serves as an abstract parent class for all other AType classes
 * 
 * @author Ceud
 */
public abstract class AType implements IAType
{
    protected String logEntry;
    protected int tick;
    protected int aType;  //use strategy pattern for AType classes?
    
    /**
     * Array of 15 strings (0-14) representing the ATypes of mission log entries
     */
    public static String[] ATypes = {"MissionStart", 
                                     "Hit", 
                                     "Damage", 
                                     "Kill", 
                                     "PlayerMissionEnd", 
                                     "Takeoff", 
                                     "Landing", 
                                     "MissionEnd", 
                                     "MissionObject", 
                                     "Airfield", 
                                     "PlayerPlane", 
                                     "GroupInit", 
                                     "GameObjectInvolved", 
                                     "InfluenceAreaHeader", 
                                     "InfluenceAreaBoundary"}; //, 
                                     //"Unknown15", 
                                     //"Unknown16"};
    
    public AType(String logEntry)
    {
        this.logEntry = logEntry.trim();
    }

    public int getAType() { return aType; }
    public String getLogEntry() { return logEntry; }
    public int getTick() { return tick; }
    
    @Override
    public abstract void parseLine();
}
