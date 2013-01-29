package RoFParser.Database;

import RoFParser.AType.Kill;
import RoFParser.Utility.Config;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Ceud
 */
public class DBKill implements IRecord
{
    protected int killID;
    protected long missionID;
    protected int attackerVehicleID;
    protected int targetVehicleID;
    protected int tick;
    protected float posX;
    protected float posY;
    protected float posZ;
    
    public DBKill(int killID, long missionID, int attackerVehicleID, int targetVehicleID, 
            int tick, float posX, float posY, float posZ)
    {
        this.killID = killID;
        this.missionID = missionID;
        this.attackerVehicleID = attackerVehicleID;
        this.targetVehicleID = targetVehicleID;
        this.tick = tick;
        this.posX = posX;
        this.posY = posY;
        this.posZ = posZ;
    }
        
    public static ArrayList<DBKill> GetKills(RoFParser.Mission mission)
    {
        ArrayList<DBKill> kills = new ArrayList<DBKill>();
        
        ArrayList<Kill> kEvents = mission.getKills();
        Kill kill;
        int i = 1;
        float[] tmpPos;
        
        Iterator<Kill> killit = kEvents.iterator();
        while (killit.hasNext())
        {
            kill = killit.next();
            tmpPos = kill.getPosition();
            
            //add kill
            DBKill tmpKill = new DBKill(i, mission.getMissionID(), 
                    kill.getAttackerID(), kill.getTargetID(), kill.getTick(), tmpPos[0], tmpPos[1], tmpPos[2]);
            kills.add(tmpKill);
            i++;
        }
        
        return kills;
    }

    /**
     * Generates SQL value string for use with insert statements
     * 
     * @return String of SQL insertion values
     */
    @Override
    public String toSQL() {
        return killID + ", " + missionID + ", " + attackerVehicleID + ", " + 
                targetVehicleID + ", " + tick + ", " + posX + ", " + posY + ", " + posZ;
    }

    /**
     * Generates insert SQL value
     * 
     * @return String of SQL insert
     */
    @Override
    public String insertSQL() {
        return "INSERT INTO `" + Config.DBPrefix() + "kills` " + 
                "(`kill_id`, `mission_id`, `attacker_vehicle_id`, `target_vehicle_id`, "
                + "`tick`, `pos_x`, `pos_y`, `pos_z`) " + 
                "VALUES (" + 
                killID + ", " + missionID + ", " + attackerVehicleID + ", " + 
                targetVehicleID + ", " + tick + ", " + posX + ", " + posY + ", " + posZ + ")";
    }
    
    public static String MultipleInsertSQL(ArrayList<DBKill> dbKill)
    {
        String SQL = "/* No kills present! */";
        if (dbKill.size() > 0)
        {
            SQL = "";
            Iterator<DBKill> it = dbKill.iterator();
            SQL = "INSERT INTO `" + Config.DBPrefix() + "kills` " + 
                "(`kill_id`, `mission_id`, `attacker_vehicle_id`, `target_vehicle_id`, "
                + "`tick`, `pos_x`, `pos_y`, `pos_z`) VALUES \n";
            String tmpStr;
            while (it.hasNext())
            {
                DBKill kill = it.next();
                tmpStr = ",";
                if (!it.hasNext()) { tmpStr = ";"; }
                SQL += "\t(" + kill.toSQL() + ")" + tmpStr + "\n";
            }
        }
        return SQL;
    }
}