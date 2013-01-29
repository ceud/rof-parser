package RoFParser.Database;

import RoFParser.AType.GameObjectInvolved;
import RoFParser.AType.PlayerPlane;
import RoFParser.Utility.Config;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author Ceud
 */
public class DBCrew implements IRecord
{
    protected int crewID;
    protected long missionID;
    protected int parentID;
    protected int pilotID;
    protected String profileID;
    protected String gameObjectType;
    protected int tick;
    protected int country;
    protected int ai;
    
    public DBCrew(int crewID, long missionID, int parentID, int pilotID, 
                  String profileID, String gameObjectType, int tick, int country, int ai)
    {
        this.crewID = crewID;
        this.missionID = missionID;
        this.parentID = parentID;
        this.pilotID = pilotID;
        this.profileID = profileID;
        this.gameObjectType = gameObjectType;
        this.tick = tick;
        this.country = country;
        this.ai = ai;
    }
    
    public static ArrayList<DBCrew> GetCrew(RoFParser.Mission mission)
    {
        ArrayList<DBCrew> crew = new ArrayList<DBCrew>();
        
        //
        ArrayList<PlayerPlane> players = mission.getPlayerPlanes();
        Iterator<PlayerPlane> pit;
        
        //get all crew entries from GOI arraylist
        ArrayList<GameObjectInvolved> gois = mission.getGameObjects();
        
        HashMap<Integer, GameObjectInvolved> hmgoi = mission.getGameObjectsHM();
        HashMap<String, PlayerPlane> hmpp = mission.getPlayerPlanesHM();
        
        
        
        Iterator<GameObjectInvolved> it = gois.iterator();
        while (it.hasNext())
        {
            GameObjectInvolved goi = it.next();
            if (goi.isCrew())
            {
                String profileID = null;
                int isAI = 1;
                int pilotID; //aka driver/pilot (main controller) of a vehicle???
                String vType = goi.getType();
                
                pit = players.iterator();
                while (pit.hasNext())
                {
                    PlayerPlane player = pit.next();
                    if (player.getPlayerID() == goi.getID())
                    {
                        //current goi crew IS a player!
                        profileID = player.getIds();
                        isAI = 0;
                        vType = hmpp.get(profileID).getName();
                        
                    }
                }
                
                //get pilot ID
                GameObjectInvolved parentGOI = hmgoi.get(goi.getParentID());
                if (parentGOI.getParentID() == -1)
                {
                    pilotID = goi.getID();
                }
                else
                {
                    //initialise as 'grandparent'
                    GameObjectInvolved tmpParentGOI = hmgoi.get(parentGOI.getParentID());
                    
                    while (tmpParentGOI.getParentID() != -1)
                    {
                        tmpParentGOI = hmgoi.get(tmpParentGOI.getParentID());
                    }
                    pilotID = tmpParentGOI.getID();
                }
                
                //add crew to dbcrew
                DBCrew tmpCrew = new DBCrew(goi.getID(), mission.getMissionID(), goi.getParentID(), 
                                            pilotID, profileID, vType, goi.getTick(), goi.getCountry(), isAI);
                crew.add(tmpCrew);
            }
        }
        return crew;
    }

    /**
     * Generates SQL value string for use with insert statements
     * 
     * @return String of SQL insertion values
     */
    @Override
    public String toSQL() {
        String profID;
        if (profileID == null)
        {
            profID = "NULL";
        }
        else
        {
            profID = "'" + profileID + "'";
        }
        return crewID + ", " + missionID + ", " + parentID + ", " + pilotID + 
                ", " + profID + ", '" + gameObjectType + 
                "', " + tick + ", " + country + ", " + ai;
    }

    /**
     * Generates insert SQL value
     * 
     * @return String of SQL insert
     */
    @Override
    public String insertSQL() {
        String profID;
        if (profileID == null)
        {
            profID = "NULL";
        }
        else
        {
            profID = "'" + profileID + "'";
        }
        
        return "INSERT INTO `" + Config.DBPrefix() + "crew` " + 
                "(`crewID`, `missionID`, `parentID`, `pilotID`, `profileID`, "
                + "`gameObjectType`, `tick`, `country`, `ai`) " + 
                "VALUES (" + 
                crewID + ", " + missionID + ", " + parentID + ", " + pilotID + 
                ", " + profID + ", '" + gameObjectType + 
                "', " + tick + ", " + country + ", " + ai + ")";
    }
    
    public static String MultipleInsertSQL(ArrayList<DBCrew> dbCrew)
    {
        String SQL = "/* No crew present! */";
        if (dbCrew.size() > 0)
        {
            SQL = "";
            Iterator<DBCrew> it = dbCrew.iterator();
            SQL = "INSERT INTO `" + Config.DBPrefix() + "crew` " + 
                "(`crewID`, `missionID`, `parentID`, `pilotID`, `profileID`, "
                + "`gameObjectType`, `tick`, `country`, `ai`) VALUES \n";
            String tmpStr;
            while (it.hasNext())
            {
                DBCrew crew = it.next();
                tmpStr = ",";
                if (!it.hasNext()) { tmpStr = ";"; }
                SQL += "\t(" + crew.toSQL() + ")" + tmpStr + "\n";
            }
        }
        return SQL;
    }
}