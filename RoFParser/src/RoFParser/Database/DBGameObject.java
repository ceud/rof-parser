package RoFParser.Database;

import RoFParser.AType.GameObjectInvolved;
import RoFParser.Utility.Config;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Ceud
 */
public class DBGameObject implements IRecord
{
    protected int gameObjectID;
    protected long missionID;
    protected int parentID;
    protected int tick;
    protected String gameObjectType;
    protected String descriptiveType;
    protected String title;
    
    public DBGameObject(int gameObjectID, long missionID, int parentID, int tick, String gameObjectType, String descriptiveType, String title)
    {
        this.gameObjectID = gameObjectID;
        this.missionID = missionID;
        this.parentID = parentID;
        this.tick = tick;
        this.gameObjectType = gameObjectType;
        this.descriptiveType = descriptiveType;
        this.title = title;
    }
    
    public static ArrayList<DBGameObject> GetGameObjects(RoFParser.Mission mission)
    {
        ArrayList<DBGameObject> gameObjects = new ArrayList<DBGameObject>();
        ArrayList<GameObjectInvolved> gois = mission.getGameObjects();
        
        Iterator<GameObjectInvolved> itGOI = gois.iterator();
        while (itGOI.hasNext())
        {
            GameObjectInvolved goi = itGOI.next();
            gameObjects.add(new DBGameObject(goi.getID(), mission.getMissionID(), 
                                             goi.getParentID(), goi.getTick(), 
                                             goi.getType(), goi.getDescriptiveType(), 
                                             goi.getName()));
        }
        
        return gameObjects;
    }

    @Override
    public String toSQL() {
        String parID;
        if (parentID == -1)
        {
            parID = "NULL";
        }
        else
        {
            parID = "" + parentID;
        }
        return gameObjectID + ", " + missionID + ", " + parID + ", " + tick + ", '" + gameObjectType + "', '" + descriptiveType + "', '" + title + "'";
    }

    /**
     * Generates insert SQL value
     * 
     * @return String of SQL insert
     */
    @Override
    public String insertSQL() {
        String parID;
        if (parentID == -1)
        {
            parID = "NULL";
        }
        else
        {
            parID = "" + parentID;
        }
        return "INSERT INTO `" + Config.DBPrefix() + "game_objects` " + 
                "(`game_object_id`, `mission_id`, `parent_id`, `tick`, `game_object_type`, `descriptive_type`, `title`) " + 
                "VALUES (" + 
                gameObjectID + ", " + missionID + ", " + parID + ", " + tick + ", '" + gameObjectType + "', '" + descriptiveType + "', '" + title + "')";
    }
    
    public static String MultipleInsertSQL(ArrayList<DBGameObject> dbGameObjects)
    {
        String SQL = "/* No game objects present! */";
        if (dbGameObjects.size() > 0)
        {
            SQL = "";
            Iterator<DBGameObject> it = dbGameObjects.iterator();
            SQL = "INSERT INTO `" + Config.DBPrefix() + "vehicles` " + 
                    "(`crewID`, `missionID`, `parentID`, `pilotID`, `profileID`, "
                    + "`gameObjectType`, `tick`, `country`, `ai`) VALUES \n";
            String tmpStr;
            while (it.hasNext())
            {
                DBGameObject gameObj = it.next();
                tmpStr = ",";
                if (!it.hasNext()) { tmpStr = ";"; }
                SQL += "\t(" + gameObj.toSQL() + ")" + tmpStr + "\n";
            }
        }
        return SQL;
    }
}
