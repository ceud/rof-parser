package RoFParser.Database;

import RoFParser.AType.InfluenceAreaBoundary;
import RoFParser.Utility.Config;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Ceud
 */
public class DBInfluenceArea implements IRecord
{
    protected int areaID;
    protected long missionID;
    
    public DBInfluenceArea(int areaID, long missionID)
    {
        this.areaID = areaID;
        this.missionID = missionID;
    }
    
    public static ArrayList<DBInfluenceArea> GetInfluenceAreas(RoFParser.Mission mission)
    {
        ArrayList<DBInfluenceArea> iaboundaries = new ArrayList<DBInfluenceArea>();
        
        ArrayList<InfluenceAreaBoundary> iabs = mission.getInfluenceAreaBoundaries();
        InfluenceAreaBoundary iab;
        
        Iterator<InfluenceAreaBoundary> it = iabs.iterator();
        while (it.hasNext())
        {
            iab = it.next();
            
            //add area
            DBInfluenceArea tmpIAB = new DBInfluenceArea(iab.getAreaID(), 
                                             mission.getMissionID());
            iaboundaries.add(tmpIAB);
        }
        return iaboundaries;
    }

    /**
     * Generates SQL value string for use with insert statements
     * 
     * @return String of SQL insertion values
     */
    @Override
    public String toSQL() {
        return areaID + ", " + missionID;
    }

    /**
     * Generates insert SQL value
     * 
     * @return String of SQL insert
     */
    @Override
    public String insertSQL() {
        return "INSERT INTO `" + Config.DBPrefix() + "influence_areas` " + 
                "(`area_id`, `mission_id`) " + 
                "VALUES (" + areaID + ", " + missionID + ")";
    }
    
    public static String MultipleInsertSQL(ArrayList<DBInfluenceArea> dbIABs)
    {
        String SQL = "/* No influence areas present! */";
        if (dbIABs.size() > 0)
        {
            SQL = "";
            Iterator<DBInfluenceArea> it = dbIABs.iterator();
            SQL = "INSERT INTO `" + Config.DBPrefix() + "influence_areas` " + 
                "(`area_id`, `mission_id`) VALUES \n";
            String tmpStr;
            while (it.hasNext())
            {
                DBInfluenceArea group = it.next();
                tmpStr = ",";
                if (!it.hasNext()) { tmpStr = ";"; }
                SQL += "\t(" + group.toSQL() + ")" + tmpStr + "\n";
            }
        }
        return SQL;
    }
}