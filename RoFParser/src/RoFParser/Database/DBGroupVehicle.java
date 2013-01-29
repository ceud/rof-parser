package RoFParser.Database;

import RoFParser.AType.GroupInit;
import RoFParser.Utility.Config;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Ceud
 */
public class DBGroupVehicle implements IRecord
{
    protected int groupID;
    protected long missionID;
    protected int vehicleID;
    protected int isLeader;
    
    public DBGroupVehicle(int groupID, long missionID, int vehicleID, int isLeader)
    {
        this.groupID = groupID;
        this.missionID = missionID;
        this.vehicleID = vehicleID;
        this.isLeader = isLeader;
    }
    
    public static ArrayList<DBGroupVehicle> GetGroupVehicles(RoFParser.Mission mission)
    {
        ArrayList<DBGroupVehicle> grpVehicles = new ArrayList<DBGroupVehicle>();
        
        ArrayList<GroupInit> grpVehs = mission.getGroupInits();
        GroupInit grpVeh;
        
        Iterator<GroupInit> it = grpVehs.iterator();
        while (it.hasNext())
        {
            grpVeh = it.next();
            int leads;
            
            for (int vID : grpVeh.getIDS())
            {
                leads = 0;
                if (vID == grpVeh.getlID()) { leads = 1; }
                //add crew to dbcrew
                DBGroupVehicle tmpGrpVeh = new DBGroupVehicle(grpVeh.getgID(), 
                                                 mission.getMissionID(), 
                                                 vID, leads);
                grpVehicles.add(tmpGrpVeh);
            }
        }
        return grpVehicles;
    }

    /**
     * Generates SQL value string for use with insert statements
     * 
     * @return String of SQL insertion values
     */
    @Override
    public String toSQL() {
        return groupID + ", " + missionID + ", " + vehicleID + ", " + isLeader;
    }

    /**
     * Generates insert SQL value
     * 
     * @return String of SQL insert
     */
    @Override
    public String insertSQL() {
        return "INSERT INTO `" + Config.DBPrefix() + "group_vehicles` " + 
                "(`group_id`, `mission_id`, `vehicle_id`, `is_leader`) " + 
                "VALUES (" + groupID + ", " + missionID + ", " + vehicleID +  ", " + isLeader + ")";
    }
    
    public static String MultipleInsertSQL(ArrayList<DBGroupVehicle> dbGroupVehicles)
    {
        String SQL = "/* No group vehicles present! */";
        if (dbGroupVehicles.size() > 0)
        {
            SQL = "";
            Iterator<DBGroupVehicle> it = dbGroupVehicles.iterator();
            SQL = "INSERT INTO `" + Config.DBPrefix() + "group_vehicles` " + 
                    "(`group_id`, `mission_id`, `vehicle_id`, `is_leader`) VALUES \n";
            String tmpStr;
            while (it.hasNext())
            {
                DBGroupVehicle grpVehicle = it.next();
                tmpStr = ",";
                if (!it.hasNext()) { tmpStr = ";"; }
                SQL += "\t(" + grpVehicle.toSQL() + ")" + tmpStr + "\n";
            }
        }
        return SQL;
    }
}