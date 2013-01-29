package RoFParser.Database;

import RoFParser.AType.InfluenceAreaHeader;
import RoFParser.Utility.Config;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Ceud
 */
public class DBInfluenceAreaHeader implements IRecord
{
    protected int areaID;
    protected long missionID;
    protected int tick;
    protected int country;
    protected int enabled;
    protected int neutral;
    protected int allied;
    protected int central;
    protected int warDogs;
    protected int mercenaries;
    protected int knights;
    protected int corsairs;
    protected int future;
    
    public DBInfluenceAreaHeader(int areaID, long missionID, int tick, int country, int enabled, 
            int neutral, int allied, int central, int warDogs, int mercenaries, int knights, int corsairs, int future)
    {
        this.areaID = areaID;
        this.missionID = missionID;
        this.tick = tick;
        this.country = country;
        this.enabled = enabled;
        this.neutral = neutral;
        this.allied = allied;
        this.central = central;
        this.warDogs = warDogs;
        this.mercenaries = mercenaries;
        this.knights = knights;
        this.corsairs = corsairs;
        this.future = future;
    }
    
    public static ArrayList<DBInfluenceAreaHeader> GetInfluenceAreaHeaders(RoFParser.Mission mission)
    {
        ArrayList<DBInfluenceAreaHeader> iaHeaders = new ArrayList<DBInfluenceAreaHeader>();
        
        ArrayList<InfluenceAreaHeader> iahs = mission.getInfluenceAreaHeaders();
        InfluenceAreaHeader iah;
        int[] tmpCP;
        
        Iterator<InfluenceAreaHeader> it = iahs.iterator();
        while (it.hasNext())
        {
            iah = it.next();
            tmpCP = iah.getCoalitionsPresent();

            //add header
            DBInfluenceAreaHeader tmpIAH = new DBInfluenceAreaHeader(
                    iah.getAreaID(), mission.getMissionID(), iah.getTick(), iah.getCountry(), iah.getEnabled(), 
                    tmpCP[0], tmpCP[1], tmpCP[2], tmpCP[3], tmpCP[4], tmpCP[5], tmpCP[6], tmpCP[7]);

            iaHeaders.add(tmpIAH);
        }
        return iaHeaders;
    }

    /**
     * Generates SQL value string for use with insert statements
     * 
     * @return String of SQL insertion values
     */
    @Override
    public String toSQL() {
        return areaID + ", " + missionID + ", " + tick + ", " + country + ", " + enabled + ", " + 
                neutral + ", " + allied + ", " + central + ", " + warDogs + ", " + mercenaries + ", " + 
                knights + ", " + corsairs + ", " + future;
    }

    /**
     * Generates insert SQL value
     * 
     * @return String of SQL insert
     */
    @Override
    public String insertSQL() {
        return "INSERT INTO `" + Config.DBPrefix() + "influence_area_headers` " + 
                "(`area_id`, `mission_id`, `tick`, `country`, `enabled`, " + 
                "`neutral`, `allied`, `central`, `war_dogs`, `mercenaries`, `knights`, `corsairs`, `future`) " + 
                "VALUES (" + areaID + ", " + missionID + ", " + tick + ", " + country + ", " + enabled + ", " + 
                neutral + ", " + allied + ", " + central + ", " + warDogs + ", " + mercenaries + ", " + 
                knights + ", " + corsairs + ", " + future + ")";
    }
    
    public static String MultipleInsertSQL(ArrayList<DBInfluenceAreaHeader> dbIABPoints)
    {
        String SQL = "/* No influence area boundaries present! */";
        if (dbIABPoints.size() > 0)
        {
            SQL = "";
            Iterator<DBInfluenceAreaHeader> it = dbIABPoints.iterator();
            SQL = "INSERT INTO `" + Config.DBPrefix() + "influence_area_headers` " + 
                "(`area_id`, `mission_id`, `tick`, `country`, `enabled`, " + 
                "`neutral`, `allied`, `central`, `war_dogs`, `mercenaries`, `knights`, `corsairs`, `future`) " + 
                "VALUES \n";
            String tmpStr;
            while (it.hasNext())
            {
                DBInfluenceAreaHeader group = it.next();
                tmpStr = ",";
                if (!it.hasNext()) { tmpStr = ";"; }
                SQL += "\t(" + group.toSQL() + ")" + tmpStr + "\n";
            }
        }
        return SQL;
    }
}