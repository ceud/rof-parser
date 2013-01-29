package RoFParser.Database;

import RoFParser.AType.PlayerPlane;
import RoFParser.Utility.Config;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Ceud
 */
public class DBProfile implements IRecord
{
    protected String profileID;
    protected String accountID;
    protected String name;
    
    public DBProfile(String profileID, String accountID, String name)
    {
        this.profileID = profileID;
        this.accountID = accountID;
        this.name = name;
    }
    
    /**
     * Retrieves all unique player profiles listed in logs
     * 
     * @param mission 
     * @return ArrayList of player profiles that participated in the mission
     */
    public static ArrayList<DBProfile> GetPlayerProfiles(RoFParser.Mission mission)
    {
        ArrayList<DBProfile> profiles = new ArrayList<DBProfile>();
        
        ArrayList<PlayerPlane> planes = mission.getPlayerPlanes();
        Iterator<PlayerPlane> it = planes.iterator();
        while (it.hasNext())
        {
            PlayerPlane plane = it.next();
            DBProfile tmp = new DBProfile(plane.getIds(), plane.getLogin(), plane.getName());
            
            //Internally iterate existing profiles
            boolean result1 = false;
            Iterator<DBProfile> it2 = profiles.iterator();
            while (it2.hasNext())
            {
                result1 = false;
                DBProfile tmp2 = it2.next();
                if (tmp.matches(tmp2))
                {
                    result1 = true;
                }
            }
            
            if (!result1)
            {
                profiles.add(tmp);
            }
        
        }
        
        return profiles;
    }
    
    protected boolean matches(DBProfile profile)
    {
        boolean result = false;
        
        if (this.profileID.equals(profile.profileID))
        {
            result = true;
        }
        
        return result;
    }
    
    /**
     * Generates SQL value string for use with insert statements
     * 
     * @return String of SQL insertion values
     */
    @Override
    public String toSQL() {
        return "'" + profileID + "', '" + accountID + "', '" + name + "'";
    }

    /**
     * Generates insert SQL value
     * 
     * @return String of SQL insert
     */
    @Override
    public String insertSQL() {
        return "INSERT INTO `" + Config.DBPrefix() + "profiles` " + 
                "(`profile_id`, `account_id`, `name`) " + 
                "VALUES ('" + profileID + "', '" + accountID + "', '" + name + "')";
    }
    
    public static String MultipleInsertSQL(ArrayList<DBProfile> dbProfiles)
    {
        String SQL = "/* No profiles present! */";
        if (dbProfiles.size() > 0)
        {
            SQL = "";
            Iterator<DBProfile> it = dbProfiles.iterator();
            SQL = "INSERT INTO `" + Config.DBPrefix() + "profiles` " + 
                "(`profile_id`, `account_id`, `name`) VALUES \n";
            String tmpStr;
            while (it.hasNext())
            {
                DBProfile profile = it.next();
                tmpStr = ",";
                if (!it.hasNext()) { tmpStr = ";"; }
                SQL += "\t(" + profile.toSQL() + ")" + tmpStr + "\n";
            }
        }
        return SQL;
    }
}
