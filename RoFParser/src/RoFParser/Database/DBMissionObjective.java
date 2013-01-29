package RoFParser.Database;

import RoFParser.AType.MissionObject;
import RoFParser.Utility.Config;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Ceud
 */
public class DBMissionObjective implements IRecord
{
    protected int objectiveID;
    protected long missionID;
    protected int tick;
    protected float posX;
    protected float posY;
    protected float posZ;
    protected int coalition;
    protected int objectiveType;
    protected int result;
    
    public DBMissionObjective(int objectiveID, long missionID, int tick, float posX, float posY, 
                  float posZ, int coalition, int objectiveType, int result)
    {
        this.objectiveID = objectiveID;
        this.missionID = missionID;
        this.tick = tick;
        this.posX = posX;
        this.posY = posY;
        this.posZ = posZ;
        this.coalition = coalition;
        this.objectiveType = objectiveType;
        this.result = result;
    }
    
    public static ArrayList<DBMissionObjective> GetObjectives(RoFParser.Mission mission)
    {
        ArrayList<DBMissionObjective> missionObjectives = new ArrayList<DBMissionObjective>();
        
        ArrayList<MissionObject> missObjs = mission.getMissionObjects();
        MissionObject missObj;
        float[] tmpPos;
        
        Iterator<MissionObject> it = missObjs.iterator();
        while (it.hasNext())
        {
            missObj = it.next();
            tmpPos = missObj.getPosition();
            
            //add crew to dbcrew
            DBMissionObjective tmpMissObj = new DBMissionObjective(missObj.getObjID(), 
                                                mission.getMissionID(), missObj.getTick(), 
                                                tmpPos[0], tmpPos[1], tmpPos[2], missObj.getCoalition(), 
                                                missObj.getType(), missObj.getResult());
            missionObjectives.add(tmpMissObj);
        }
        return missionObjectives;
    }

    /**
     * Generates SQL value string for use with insert statements
     * 
     * @return String of SQL insertion values
     */
    @Override
    public String toSQL() {
        return objectiveID + ", " + missionID + ", " + tick + 
                ", " + posX + ", " + posY + ", " + posZ + 
                ", " + coalition + ", " + objectiveType + ", " + result;
    }

    /**
     * Generates insert SQL value
     * 
     * @return String of SQL insert
     */
    @Override
    public String insertSQL() {
        return "INSERT INTO `" + Config.DBPrefix() + "mission_objectives` " + 
                "(`objective_id`, `mission_id`, `tick`, `pos_x`, `pos_y`, "
                + "`pos_z`, `coalition`, `obj_type`, `res`) " + 
                "VALUES (" + 
                objectiveID + ", " + missionID + ", " + tick + 
                ", " + posX + ", " + posY + ", " + posZ + 
                ", " + coalition + ", " + objectiveType + ", " + result + ")";
    }
    
    public static String MultipleInsertSQL(ArrayList<DBMissionObjective> dbMissionObjectives)
    {
        String SQL = "/* No mission objectives present! */";
        if (dbMissionObjectives.size() > 0)
        {
            SQL = "";
            Iterator<DBMissionObjective> it = dbMissionObjectives.iterator();
            SQL = "INSERT INTO `" + Config.DBPrefix() + "mission_objectives` " + 
                        "(`objective_id`, `mission_id`, `tick`, `pos_x`, `pos_y`, `pos_z`, " + 
                        "`coalition`, `obj_type`, `res`) VALUES \n";
            String tmpStr;
            while (it.hasNext())
            {
                DBMissionObjective missObj = it.next();
                tmpStr = ",";
                if (!it.hasNext()) { tmpStr = ";"; }
                SQL += "\t(" + missObj.toSQL() + ")" + tmpStr + "\n";
            }
        }
        return SQL;
    }
}