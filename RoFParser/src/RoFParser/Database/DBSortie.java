package RoFParser.Database;

import RoFParser.AType.PlayerMissionEnd;
import RoFParser.AType.PlayerPlane;
import RoFParser.Utility.Config;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Ceud
 */
public class DBSortie implements IRecord
{
    protected int sortieID;
    protected long missionID;
    protected long sortieStart;
    protected long sortieEnd;
    
    public DBSortie(int sortieID, long missionID, long sortieStart, long sortieEnd)
    {
        this.sortieID = sortieID;
        this.missionID = missionID;
        this.sortieStart = sortieStart;
        this.sortieEnd = sortieEnd;
    }
    
    public static ArrayList<DBSortie> GetSorties(RoFParser.Mission mission)
    {
        ArrayList<DBSortie> missSorties = new ArrayList<DBSortie>();
        
        ArrayList<PlayerPlane> spawnedPlanes = mission.getPlayerPlanes();
        Iterator<PlayerPlane> spawnit = spawnedPlanes.iterator();
        ArrayList<PlayerMissionEnd> despawnedPlanes = mission.getPlayerMissionEnds();
        Iterator<PlayerMissionEnd> despawnit;
        PlayerPlane spawn;
        PlayerMissionEnd despawn;
        int i = 1;
        while (spawnit.hasNext())
        {
            spawn = spawnit.next();
            
            //Internally iterate existing despawns
            despawnit = despawnedPlanes.iterator();
        
            gotspawned:
            while (despawnit.hasNext())
            {
                despawn = despawnit.next();
                
                if (spawn.getPlaneID() == despawn.getPlaneID() &&
                    spawn.getPlayerID() == despawn.getPlayerID())
                {
                    long tmp1 = mission.getMissionID() + Math.round(spawn.getTick() / 1000d);
                    long tmp2 = mission.getMissionID() + Math.round(despawn.getTick() / 1000d);
                    missSorties.add(new DBSortie(i, mission.getMissionID(), tmp1, tmp2));
                    i++;
                    break gotspawned;
                }
            }
        }
        return missSorties;
    }

    /**
     * Generates SQL value string for use with insert statements
     * 
     * @return String of SQL insertion values
     */
    @Override
    public String toSQL() {
        return sortieID + ", " + missionID + ", " + sortieStart + ", " + sortieEnd;
    }

    /**
     * Generates insert SQL value
     * 
     * @return String of SQL insert
     */
    @Override
    public String insertSQL() {
        return "INSERT INTO `" + Config.DBPrefix() + "sorties` " + 
                "(`sortie_id`, `mission_id`, `sortie_start`, `sortie_end`) " + 
                "VALUES (" + 
                sortieID + ", " + missionID + ", " + sortieStart + ", " + sortieEnd + ")";
    }
    
    public static String MultipleInsertSQL(ArrayList<DBSortie> dbSorties)
    {
        String SQL = "/* No sorties present! */";
        if (dbSorties.size() > 0)
        {
            SQL = "";
            Iterator<DBSortie> it = dbSorties.iterator();
            SQL = "INSERT INTO `" + Config.DBPrefix() + "sorties` " + 
                    "(`sortie_id`, `mission_id`, `sortie_start`, `sortie_end`) VALUES \n";
            String tmpStr;
            while (it.hasNext())
            {
                DBSortie sortie = it.next();
                tmpStr = ",";
                if (!it.hasNext()) { tmpStr = ";"; }
                SQL += "\t(" + sortie.toSQL() + ")" + tmpStr + "\n";
            }
        }
        return SQL;
    }
}