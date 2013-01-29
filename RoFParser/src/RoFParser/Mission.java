package RoFParser;

import RoFParser.AType.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author Ceud
 */
public class Mission {
    protected long missionID;
    protected MissionStart missionStart;
    protected MissionEnd missionEnd;
    protected ArrayList<MissionObject> missionObjects;
    protected ArrayList<Airfield> airfields;
    protected ArrayList<Hit> hits;
    protected ArrayList<Damage> damage;
    protected ArrayList<Kill> kills;
    protected ArrayList<GameObjectInvolved> gameObjects;
    protected ArrayList<GroupInit> groupInits;
    protected ArrayList<InfluenceAreaHeader> influenceAreaHeaders;
    protected ArrayList<InfluenceAreaBoundary> influenceAreaBoundaries;
    protected ArrayList<Landing> landings;
    protected ArrayList<Takeoff> takeoffs;
    protected ArrayList<PlayerMissionEnd> playerMissionEnds;
    protected ArrayList<PlayerPlane> playerPlanes;

    public ArrayList<Airfield> getAirfields() { return airfields; }
    public ArrayList<Damage> getDamage() { return damage; }
    public ArrayList<GameObjectInvolved> getGameObjects() { return gameObjects; }
    public ArrayList<GroupInit> getGroupInits() { return groupInits; }
    public ArrayList<Hit> getHits() { return hits; }
    public ArrayList<InfluenceAreaBoundary> getInfluenceAreaBoundaries() { return influenceAreaBoundaries; }
    public ArrayList<InfluenceAreaHeader> getInfluenceAreaHeaders() { return influenceAreaHeaders; }
    public ArrayList<Kill> getKills() { return kills; }
    public ArrayList<Landing> getLandings() { return landings; }
    public MissionEnd getMissionEnd() { return missionEnd; }
    public long getMissionID() { return missionID; }
    public ArrayList<MissionObject> getMissionObjects() { return missionObjects; }
    public MissionStart getMissionStart() { return missionStart; }
    public ArrayList<PlayerMissionEnd> getPlayerMissionEnds() { return playerMissionEnds; }
    public ArrayList<PlayerPlane> getPlayerPlanes() { return playerPlanes; }
    public ArrayList<Takeoff> getTakeoffs() { return takeoffs; }
    
    /**
     * Gets all game objects (crew, vehicles, planes, turrets, buildings, etc)
     * 
     * @return HashMap<Integer, GameObjectInvolved> All game objects
     */
    public HashMap<Integer, GameObjectInvolved> getGameObjectsHM()
    {
        HashMap<Integer, GameObjectInvolved> hmgoi = new HashMap<Integer, GameObjectInvolved>();
        GameObjectInvolved tmpgoi;
        Iterator<GameObjectInvolved> it = gameObjects.iterator();
        while (it.hasNext())
        {
            tmpgoi = it.next();
            hmgoi.put(new Integer(tmpgoi.getID()), tmpgoi);
            
        }
        
        return hmgoi;
    }
    
    /**
     * Gets all player planes
     * 
     * @return HashMap<Integer, GameObjectInvolved> All game objects
     */
    public HashMap<String, PlayerPlane> getPlayerPlanesHM()
    {
        HashMap<String, PlayerPlane> hmpp = new HashMap<String, PlayerPlane>();
        PlayerPlane tmppp;
        Iterator<PlayerPlane> it = playerPlanes.iterator();
        while (it.hasNext())
        {
            tmppp = it.next();
            hmpp.put(tmppp.getIds(), tmppp);
            
        }
        
        return hmpp;
    }
    
    /**
     * Creates a new mission object based on logs for supplied date and time
     * 
     * @param missID Mission date/time in unixtime format
     */
    public Mission(long missID)
    {
        missionID = missID;
        missionStart = null;
        missionEnd = null;
        missionObjects = new ArrayList<MissionObject>();
        airfields = new ArrayList<Airfield>();
        hits = new ArrayList<Hit>();
        damage = new ArrayList<Damage>();
        kills = new ArrayList<Kill>();
        gameObjects = new ArrayList<GameObjectInvolved>();
        groupInits = new ArrayList<GroupInit>();
        influenceAreaHeaders = new ArrayList<InfluenceAreaHeader>();
        influenceAreaBoundaries = new ArrayList<InfluenceAreaBoundary>();
        landings = new ArrayList<Landing>();
        takeoffs = new ArrayList<Takeoff>();
        playerMissionEnds = new ArrayList<PlayerMissionEnd>();
        playerPlanes = new ArrayList<PlayerPlane>();
    }
    
    public void parseMissionLog(String[] log)
    {
        for (int i = 0; i < log.length; i++)
        {
            int aType = Integer.parseInt(log[i].substring(log[i].indexOf("AType:") + 6, log[i].indexOf("AType:") + 8).trim());
            try {
            switch(aType)
            {
                case 0:
                    this.missionStart = new MissionStart(log[i]);
                    break;
                case 1:
                    this.hits.add(new Hit(log[i]));
                    break;
                case 2:
                    this.damage.add(new Damage(log[i]));
                    break;
                case 3:
                    this.kills.add(new Kill(log[i]));
                    break;
                case 4:
                    this.playerMissionEnds.add(new PlayerMissionEnd(log[i]));
                    break;
                case 5:
                    this.takeoffs.add(new Takeoff(log[i]));
                    break;
                case 6:
                    this.landings.add(new Landing(log[i]));
                    break;
                case 7:
                    this.missionEnd = new MissionEnd(log[i]);
                    break;
                case 8:
                    this.missionObjects.add(new MissionObject(log[i]));
                    break;
                case 9:
                    this.airfields.add(new Airfield(log[i]));
                    break;
                case 10:
                    this.playerPlanes.add(new PlayerPlane(log[i]));
                    break;
                case 11:
                    this.groupInits.add(new GroupInit(log[i]));
                    break;
                case 12:
                    this.gameObjects.add(new GameObjectInvolved(log[i]));
                    break;
                case 13:
                    this.influenceAreaHeaders.add(new InfluenceAreaHeader(log[i]));
                    break;
                case 14:
                    this.influenceAreaBoundaries.add(new InfluenceAreaBoundary(log[i]));
                    break;
                default:
                    //System.out.println("Error: Unrecognisable log type: " + log[i]);
            }
            } catch (NullPointerException e) {
                //System.out.println("[" + aType + "]");
                e.printStackTrace();
                //System.exit(0);
            }
        }
        System.out.println("Mission logs parsed!");
    }
    
    @Override
    public String toString()
    {
        String str = "";
        String tmpStr = "";
        try {
            tmpStr = this.missionStart != null ? this.missionStart.toString() + "\n" : "null mission start\n";
            str = str + tmpStr;
            tmpStr = this.missionEnd != null ? this.missionEnd.toString() + "\n" : "null mission end\n";
            str = str + tmpStr;
            tmpStr = this.missionObjects.size() > 0 ? this.missionObjects.toString() + "\n" : "null mission object\n";
            str = str + tmpStr;
            tmpStr = this.airfields.size() > 0 ? this.airfields.toString() + "\n" : "null airfield\n";
            str = str + tmpStr;
            tmpStr = this.hits.size() > 0 ? this.hits.toString() + "\n" : "null hits\n";
            str = str + tmpStr;
            tmpStr = this.damage.size() > 0 ? this.damage.toString() + "\n" : "null damage\n";
            str = str + tmpStr;
            tmpStr = this.kills.size() > 0 ? this.kills.toString() + "\n" : "null kills\n";
            str = str + tmpStr;
            tmpStr = this.gameObjects.size() > 0 ? this.gameObjects.toString() + "\n" : "null game object\n";
            str = str + tmpStr;
            tmpStr = this.groupInits.size() > 0 ? this.groupInits.toString() + "\n" : "null group inits\n";
            str = str + tmpStr;
            tmpStr = this.influenceAreaHeaders.size() > 0 ? this.influenceAreaHeaders.toString() + "\n" : "null influence area headers\n";
            str = str + tmpStr;
            tmpStr = this.influenceAreaBoundaries != null ? this.influenceAreaBoundaries.toString() + "\n" : "null influence area boundaries\n";
            str = str + tmpStr;
            tmpStr = this.landings != null ? this.landings.toString() + "\n" : "null landings\n";
            str = str + tmpStr;
            tmpStr = this.takeoffs != null ? this.takeoffs.toString() + "\n" : "null takeoffs\n";
            str = str + tmpStr;
            tmpStr = this.playerMissionEnds != null ? this.playerMissionEnds.toString() + "\n" : "null player mission end\n";
            str = str + tmpStr;
            tmpStr = this.playerPlanes != null ? this.playerPlanes.toString() + "\n" : "null player plane\n";
            str = str + tmpStr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }
}
