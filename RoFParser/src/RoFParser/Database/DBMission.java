package RoFParser.Database;

import RoFParser.Utility.Config;

/**
 * Represents an object equivalent to a database record for missions
 * 
 * @author Ceud
 */
public class DBMission implements IRecord
{
    protected long id;
    protected String name;
    protected long timeStart;
    protected long timeEnd;
    protected String gameDate;
    protected String gameTime;
    protected int gameType;
    protected String settings;
    protected int mods;
    
    public static final int SINGLE = 0;
    public static final int COOPERATIVE = 1;
    public static final int DOGFIGHT = 2;
    public static final int CUSTOM = 3;
    
    
    public DBMission(RoFParser.Mission mission)
    {
        id = mission.getMissionID();
        name = mission.getMissionStart().getMissionName();
        timeStart = id;
        timeEnd = timeStart + (mission.getMissionEnd().getTick() / 50);
        gameDate = mission.getMissionStart().getgDate();
        gameTime = mission.getMissionStart().getgTime();
        gameType = mission.getMissionStart().getgType();
        settings = mission.getMissionStart().getSETTS();
        mods = mission.getMissionStart().getMods();
    }

    /**
     * Generates SQL value string for use with insert statements
     * 
     * @return String of SQL insertion values
     */
    @Override
    public String toSQL() {
        return id + ", " + name + ", " + timeStart + ", " + timeEnd + ", " + 
                gameDate + ", " + gameTime + ", " + gameType + ", " + 
                settings + ", " + mods;
    }

    /**
     * Generates insert SQL value
     * 
     * @return String of SQL insert
     */
    @Override
    public String insertSQL() {
        return "INSERT INTO `" + Config.DBPrefix() + "missions` " + 
                "(`mission_id`, `name`, `start`, `end`, `game_date`, `game_time`, `game_type`, `settings`, `mods`) " + 
                "VALUES (" + 
                id + ", '" + name + "', " + timeStart + ", " + timeEnd + ", '" + 
                gameDate + "', '" + gameTime + "', " + gameType + ", '" + 
                settings + "', " + mods + ")";
    }
}
