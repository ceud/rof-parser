package RoFParser.Database;

import RoFParser.AType.GroupInit;
import RoFParser.Utility.Config;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Ceud
 */
public class DBGroup implements IRecord
{
    protected int groupID;
    protected long missionID;
    protected int tick;
    
    public DBGroup(int groupID, long missionID, int tick)
    {
        this.groupID = groupID;
        this.missionID = missionID;
        this.tick = tick;
    }
    
    public static ArrayList<DBGroup> GetGroups(RoFParser.Mission mission)
    {
        ArrayList<DBGroup> groups = new ArrayList<DBGroup>();
        
        ArrayList<GroupInit> grpInits = mission.getGroupInits();
        GroupInit grpInit;
        
        Iterator<GroupInit> it = grpInits.iterator();
        while (it.hasNext())
        {
            grpInit = it.next();
            
            //add crew to dbcrew
            DBGroup tmpGrpInit = new DBGroup(grpInit.getgID(), 
                                             mission.getMissionID(), 
                                             grpInit.getTick());
            groups.add(tmpGrpInit);
        }
        return groups;
    }

    /**
     * Generates SQL value string for use with insert statements
     * 
     * @return String of SQL insertion values
     */
    @Override
    public String toSQL() {
        return groupID + ", " + missionID + ", " + tick;
    }

    /**
     * Generates insert SQL value
     * 
     * @return String of SQL insert
     */
    @Override
    public String insertSQL() {
        return "INSERT INTO `" + Config.DBPrefix() + "groups` " + 
                "(`group_id`, `mission_id`, `tick`) " + 
                "VALUES (" + groupID + ", " + missionID + ", " + tick + ")";
    }
    
    public static String MultipleInsertSQL(ArrayList<DBGroup> dbGroups)
    {
        String SQL = "/* No groups present! */";
        if (dbGroups.size() > 0)
        {
            SQL = "";
            Iterator<DBGroup> it = dbGroups.iterator();
            SQL = "INSERT INTO `" + Config.DBPrefix() + "groups` " + 
                "(`group_id`, `mission_id`, `tick`) VALUES \n";
            String tmpStr;
            while (it.hasNext())
            {
                DBGroup group = it.next();
                tmpStr = ",";
                if (!it.hasNext()) { tmpStr = ";"; }
                SQL += "\t(" + group.toSQL() + ")" + tmpStr + "\n";
            }
        }
        return SQL;
    }
}