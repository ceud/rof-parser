package RoFParser.Database;

import RoFParser.AType.Damage;
import RoFParser.Utility.Config;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Ceud
 */
public class DBDamage implements IRecord
{
    protected int damageID;
    protected long missionID;
    protected int attackerVehicleID;
    protected int targetVehicleID;
    protected int tick;
    protected float damage;
    protected float posX;
    protected float posY;
    protected float posZ;
    
    public DBDamage(int killID, long missionID, int attackerVehicleID, int targetVehicleID, 
            int tick, float damage, float posX, float posY, float posZ)
    {
        this.damageID = killID;
        this.missionID = missionID;
        this.attackerVehicleID = attackerVehicleID;
        this.targetVehicleID = targetVehicleID;
        this.tick = tick;
        this.damage = damage;
        this.posX = posX;
        this.posY = posY;
        this.posZ = posZ;
    }
        
    public static ArrayList<DBDamage> GetDamage(RoFParser.Mission mission)
    {
        ArrayList<DBDamage> damages = new ArrayList<DBDamage>();
        
        ArrayList<Damage> dEvents = mission.getDamage();
        Damage damage;
        int i = 1;
        float[] tmpPos;
        
        Iterator<Damage> dmgit = dEvents.iterator();
        while (dmgit.hasNext())
        {
            damage = dmgit.next();
            tmpPos = damage.getPosition();
            
            //add damage
            DBDamage tmpDmg = new DBDamage(i, mission.getMissionID(), 
                    damage.getAttackerID(), damage.getTargetID(), damage.getTick(), 
                    damage.getDamage(), tmpPos[0], tmpPos[1], tmpPos[2]);
            damages.add(tmpDmg);
            i++;
        }
        
        return damages;
    }

    /**
     * Generates SQL value string for use with insert statements
     * 
     * @return String of SQL insertion values
     */
    @Override
    public String toSQL() {
        return damageID + ", " + missionID + ", " + attackerVehicleID + ", " + 
                targetVehicleID + ", " + tick + ", " + damage + ", " + posX + ", " + posY + ", " + posZ;
    }

    /**
     * Generates insert SQL value
     * 
     * @return String of SQL insert
     */
    @Override
    public String insertSQL() {
        return "INSERT INTO `" + Config.DBPrefix() + "damages` " + 
                "(`damage_id`, `mission_id`, `attacker_vehicle_id`, `target_vehicle_id`, "
                + "`tick`, `damage`, `pos_x`, `pos_y`, `pos_z`) " + 
                "VALUES (" + 
                damageID + ", " + missionID + ", " + attackerVehicleID + ", " + 
                targetVehicleID + ", " + tick + ", " + damage + ", " + posX + ", " + posY + ", " + posZ + ")";
    }
    
    public static String MultipleInsertSQL(ArrayList<DBDamage> dbDamage)
    {
        String SQL = "/* No damages present! */";
        if (dbDamage.size() > 0)
        {
            SQL = "";
            Iterator<DBDamage> it = dbDamage.iterator();
            SQL = "INSERT INTO `" + Config.DBPrefix() + "damages` " + 
                "(`damage_id`, `mission_id`, `attacker_vehicle_id`, `target_vehicle_id`, "
                + "`tick`, `damage`, `pos_x`, `pos_y`, `pos_z`) VALUES \n";
            String tmpStr;
            while (it.hasNext())
            {
                DBDamage dmg = it.next();
                tmpStr = ",";
                if (!it.hasNext()) { tmpStr = ";"; }
                SQL += "\t(" + dmg.toSQL() + ")" + tmpStr + "\n";
            }
        }
        return SQL;
    }
}