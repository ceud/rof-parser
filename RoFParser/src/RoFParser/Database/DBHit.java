package RoFParser.Database;

import RoFParser.AType.Hit;
import RoFParser.Utility.Config;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Ceud
 */
public class DBHit implements IRecord
{
    protected int hitID;
    protected long missionID;
    protected int attackerVehicleID;
    protected int targetVehicleID;
    protected int tick;
    protected String ammoType;
    
    public DBHit(int hitID, long missionID, int attackerVehicleID,  int targetVehicleID, 
                 int tick, String ammoType)
    {
        this.hitID = hitID;
        this.missionID = missionID;
        this.attackerVehicleID = attackerVehicleID;
        this.targetVehicleID = targetVehicleID;
        this.tick = tick;
        this.ammoType = ammoType;
    }
    
    public static ArrayList<DBHit> GetHits(RoFParser.Mission mission)
    {
        ArrayList<DBHit> hitEvents = new ArrayList<DBHit>();
        
        ArrayList<Hit> hEvents = mission.getHits();
        Hit hit;
        int i = 1;
        
        Iterator<Hit> hitit = hEvents.iterator();
        while (hitit.hasNext())
        {
            hit = hitit.next();
            
            //add hit
            DBHit tmpHit = new DBHit(i, mission.getMissionID(), 
                    hit.getAttackerID(), hit.getTargetID(), hit.getTick(), hit.getAmmo());
            hitEvents.add(tmpHit);
            i++;
        }
        
        return hitEvents;
    }

    /**
     * Generates SQL value string for use with insert statements
     * 
     * @return String of SQL insertion values
     */
    @Override
    public String toSQL() {
        return hitID + ", " + missionID + ", " + 
                attackerVehicleID + ", " + targetVehicleID + ", " + 
                tick + ", '" + ammoType + "'";
    }

    /**
     * Generates insert SQL value
     * 
     * @return String of SQL insert
     */
    @Override
    public String insertSQL() {
        return "INSERT INTO `" + Config.DBPrefix() + "hits` " + 
                "(`hit_id`, `mission_id`, `attacker_vehicle_id`, `target_vehicle_id`, "
                + "`tick`, `ammo_type`) " + 
                "VALUES (" + 
                hitID + ", " + missionID + ", " + 
                attackerVehicleID + ", " + targetVehicleID + ", " + 
                tick + ", '" + ammoType + "')";
    }
    
    public static String MultipleInsertSQL(ArrayList<DBHit> dbHits)
    {
        String SQL = "/* No hits present! */";
        if (dbHits.size() > 0)
        {
            SQL = "";
            Iterator<DBHit> it = dbHits.iterator();
            SQL = "INSERT INTO `" + Config.DBPrefix() + "hits` " + 
                "(`hit_id`, `mission_id`, `attacker_vehicle_id`, `target_vehicle_id`, `tick`, `ammo_type`) VALUES \n";
            String tmpStr;
            while (it.hasNext())
            {
                DBHit hit = it.next();
                tmpStr = ",";
                if (!it.hasNext()) { tmpStr = ";"; }
                SQL += "\t(" + hit.toSQL() + ")" + tmpStr + "\n";
            }
        }
        return SQL;
    }
}