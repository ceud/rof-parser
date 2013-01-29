package RoFParser.Database;

import RoFParser.AType.GroupInit;
import RoFParser.AType.InfluenceAreaBoundary;
import RoFParser.Utility.Config;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Ceud
 */
public class DBInfluenceAreaBoundary implements IRecord
{
    protected int pointID;
    protected int areaID;
    protected long missionID;
    protected float posX;
    protected float posY;
    protected float posZ;
    
    public DBInfluenceAreaBoundary(int pointID, int areaID, long missionID, float posX, float posY, float posZ)
    {
        this.pointID = pointID;
        this.areaID = areaID;
        this.missionID = missionID;
        this.posX = posX;
        this.posY = posY;
        this.posZ = posZ;
    }
    
    public static ArrayList<DBInfluenceAreaBoundary> GetInfluenceAreaBoundaries(RoFParser.Mission mission)
    {
        ArrayList<DBInfluenceAreaBoundary> iabPoints = new ArrayList<DBInfluenceAreaBoundary>();
        
        ArrayList<InfluenceAreaBoundary> iabs = mission.getInfluenceAreaBoundaries();
        InfluenceAreaBoundary iab;
        float tmpX, tmpY, tmpZ;
        
        Iterator<InfluenceAreaBoundary> it = iabs.iterator();
        while (it.hasNext())
        {
            iab = it.next();
            String[] tmpBP = iab.getBoundaryPoints();
            
            for (int i = 0; i < tmpBP.length; i++)
            {
                tmpX = Float.parseFloat(tmpBP[i].split(",")[0].trim());
                tmpY = Float.parseFloat(tmpBP[i].split(",")[1].trim());
                tmpZ = Float.parseFloat(tmpBP[i].split(",")[2].trim());
                
                //add boundary point
                DBInfluenceAreaBoundary tmpIAB = new DBInfluenceAreaBoundary(
                        (i + 1), iab.getAreaID(), mission.getMissionID(), tmpX, tmpY, tmpZ);
                
                iabPoints.add(tmpIAB);
            }
        }
        return iabPoints;
    }

    /**
     * Generates SQL value string for use with insert statements
     * 
     * @return String of SQL insertion values
     */
    @Override
    public String toSQL() {
        return pointID + ", " + areaID + ", " + missionID + ", " + posX + ", " + posY + ", " + posZ;
    }

    /**
     * Generates insert SQL value
     * 
     * @return String of SQL insert
     */
    @Override
    public String insertSQL() {
        return "INSERT INTO `" + Config.DBPrefix() + "influence_area_boundaries` " + 
                "(`point_id`, `area_id`, `mission_id`, `posX`, `posY`, `posZ`) " + 
                "VALUES (" + pointID + ", " + areaID + ", " + missionID + ", " + 
                posX + ", " + posY + ", " + posZ + ")";
    }
    
    public static String MultipleInsertSQL(ArrayList<DBInfluenceAreaBoundary> dbIABPoints)
    {
        String SQL = "/* No influence area boundaries present! */";
        if (dbIABPoints.size() > 0)
        {
            SQL = "";
            Iterator<DBInfluenceAreaBoundary> it = dbIABPoints.iterator();
            SQL = "INSERT INTO `" + Config.DBPrefix() + "influence_area_boundaries` " + 
                "(`point_id`, `area_id`, `mission_id`, `posX`, `posY`, `posZ`) " + 
                "VALUES \n";
            String tmpStr;
            while (it.hasNext())
            {
                DBInfluenceAreaBoundary group = it.next();
                tmpStr = ",";
                if (!it.hasNext()) { tmpStr = ";"; }
                SQL += "\t(" + group.toSQL() + ")" + tmpStr + "\n";
            }
        }
        return SQL;
    }
}