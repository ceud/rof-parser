package RoFParser.Database;

import RoFParser.AType.Landing;
import RoFParser.AType.Takeoff;
import RoFParser.Utility.Config;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Ceud
 */
public class DBVehicleEvent implements IRecord
{
    protected int vehicleID;
    protected long missionID;
    protected int tick;
    protected String eventType;
    protected float posX;
    protected float posY;
    protected float posZ;
    
    public DBVehicleEvent(int vehicleID, long missionID, int tick, 
                          String eventType, float posX, float posY, float posZ)
    {
        this.vehicleID = vehicleID;
        this.missionID = missionID;
        this.tick = tick;
        this.eventType = eventType;
        this.posX = posX;
        this.posY = posY;
        this.posZ = posZ;
    }
    
    public static ArrayList<DBVehicleEvent> GetVehicleEvents(RoFParser.Mission mission)
    {
        ArrayList<DBVehicleEvent> vehicleEvents = new ArrayList<DBVehicleEvent>();
        
        ArrayList<Takeoff> takeoffEvents = mission.getTakeoffs();
        ArrayList<Landing> landingEvents = mission.getLandings();
        Takeoff takeoff;
        Landing landing;
        
        float[] tmpPos;
        
        Iterator<Takeoff> tit = takeoffEvents.iterator();
        while (tit.hasNext())
        {
            takeoff = tit.next();
            tmpPos = takeoff.getPosition();
            
            //add takeoff to events
            DBVehicleEvent tmpTakeoff = new DBVehicleEvent(takeoff.getPlaneID(), 
                                                mission.getMissionID(), takeoff.getTick(), 
                                                "takeoff", tmpPos[0], tmpPos[1], tmpPos[2]);
            vehicleEvents.add(tmpTakeoff);
        }
        
        
        Iterator<Landing> lit = landingEvents.iterator();
        while (lit.hasNext())
        {
            landing = lit.next();
            tmpPos = landing.getPosition();
            
            //add landing to events
            DBVehicleEvent tmpLanding = new DBVehicleEvent(landing.getPlaneID(), 
                                                mission.getMissionID(), landing.getTick(), 
                                                "landing", tmpPos[0], tmpPos[1], tmpPos[2]);
            vehicleEvents.add(tmpLanding);
        }
        
        return vehicleEvents;
    }

    /**
     * Generates SQL value string for use with insert statements
     * 
     * @return String of SQL insertion values
     */
    @Override
    public String toSQL() {
        return vehicleID + ", " + missionID + ", " + tick + ", '" + eventType + 
                "', " + posX + ", " + posY + ", " + posZ;
    }

    /**
     * Generates insert SQL value
     * 
     * @return String of SQL insert
     */
    @Override
    public String insertSQL() {
        return "INSERT INTO `" + Config.DBPrefix() + "vehicle_events` " + 
                "(`vehicle_id`, `mission_id`, `tick`, `event_type`, `pos_x`, `pos_y`, `pos_z`) " + 
                "VALUES (" + 
                vehicleID + ", " + missionID + ", " + tick + ", '" + eventType + 
                "', " + posX + ", " + posY + ", " + posZ + ")";
    }
    
    public static String MultipleInsertSQL(ArrayList<DBVehicleEvent> dbVehicleEvents)
    {
        String SQL = "/* No vehicle events (takeoff/landing) present! */";
        if (dbVehicleEvents.size() > 0)
        {
            SQL = "";
            Iterator<DBVehicleEvent> it = dbVehicleEvents.iterator();
            SQL = "INSERT INTO `" + Config.DBPrefix() + "vehicle_events` " + 
                "(`vehicle_id`, `mission_id`, `tick`, `event_type`, `pos_x`, `pos_y`, `pos_z`) VALUES \n";
            String tmpStr;
            while (it.hasNext())
            {
                DBVehicleEvent vehEvent = it.next();
                tmpStr = ",";
                if (!it.hasNext()) { tmpStr = ";"; }
                SQL += "\t(" + vehEvent.toSQL() + ")" + tmpStr + "\n";
            }
        }
        return SQL;
    }
}