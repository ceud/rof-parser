package RoFParser;

import RoFParser.AType.InfluenceAreaBoundary;
import RoFParser.AType.InfluenceAreaHeader;
import RoFParser.Database.*;
import RoFParser.Utility.*;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Ceud
 */
public class app
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        //Load configuration values from ini file into memory
        Config.LoadConfig("config.ini");
        
        //Create a container ready to hold any parsed missions
        ArrayList<Mission> missions = new ArrayList<Mission>();
        
        //Load, Concatenate, Save and Delete "ended" mission logs
        String[] allLogs = LogFile.AllLogNames(Config.LogDir());
        String[] endedLogDates = LogFile.UniqueEndedMissionDates(Config.LogDir(), allLogs);
        
        File[] endedLogs;
        String[] concatLog;
        for(int i = 0; i < endedLogDates.length; i++)
        {
            endedLogs = LogFile.AllLogs(Config.LogDir(), endedLogDates[i]);
            concatLog = LogFile.Concatenate(endedLogs);
            
            //create mission based on concatenated log
            Mission miss = new Mission(MissionDate.UnixTime(endedLogDates[i]));
            miss.parseMissionLog(concatLog);
            missions.add(miss);
            
            //LogFile.Save(Config.BakDir(), "missionReport(" + endedLogDates[i] + ").txt", concatLog);
            //LogFile.Delete(endedLogs);
            
            //Testing Database package
            DBMission dbMission = new DBMission(miss);
            ArrayList<DBProfile> dbProfiles = DBProfile.GetPlayerProfiles(miss);
            ArrayList<DBCrew> dbCrew = DBCrew.GetCrew(miss);
            ArrayList<DBGameObject> dbGameObjects = DBGameObject.GetGameObjects(miss);
            ArrayList<DBMissionObjective> dbMissionObjectives = DBMissionObjective.GetObjectives(miss);
            ArrayList<DBGroup> dbGroups = DBGroup.GetGroups(miss);
            ArrayList<DBGroupVehicle> dbGroupVehicles = DBGroupVehicle.GetGroupVehicles(miss);
            ArrayList<DBSortie> dbSorties = DBSortie.GetSorties(miss);
            ArrayList<DBVehicleEvent> dbVehicleEvents = DBVehicleEvent.GetVehicleEvents(miss);
            ArrayList<DBHit> dbHits = DBHit.GetHits(miss);
            ArrayList<DBDamage> dbDamage = DBDamage.GetDamage(miss);
            ArrayList<DBKill> dbKills = DBKill.GetKills(miss);
            ArrayList<DBInfluenceArea> dbInfluenceAreas = DBInfluenceArea.GetInfluenceAreas(miss);
            ArrayList<DBInfluenceAreaBoundary> dbIABoundaries = DBInfluenceAreaBoundary.GetInfluenceAreaBoundaries(miss);
            ArrayList<DBInfluenceAreaHeader> dbIAHeaders = DBInfluenceAreaHeader.GetInfluenceAreaHeaders(miss);
            
            //print mission
            System.out.println(dbMission.insertSQL());
            
            //print profiles (players)
            System.out.println(DBProfile.MultipleInsertSQL(dbProfiles));
            
            //print crew
            System.out.println(DBCrew.MultipleInsertSQL(dbCrew));
            
            //print game objects (vehicles)
            System.out.println(DBGameObject.MultipleInsertSQL(dbGameObjects));
            
            //print mission objectives
            System.out.println(DBMissionObjective.MultipleInsertSQL(dbMissionObjectives));
            
            //print groups
            System.out.println(DBGroup.MultipleInsertSQL(dbGroups));
            
            //print group vehicles
            System.out.println(DBGroupVehicle.MultipleInsertSQL(dbGroupVehicles));
            
            //print sorties
            System.out.println(DBSortie.MultipleInsertSQL(dbSorties));
            
            //print vehicle events (takeoff and landing)
            System.out.println(DBVehicleEvent.MultipleInsertSQL(dbVehicleEvents));
            
            //print hits
            System.out.println(DBHit.MultipleInsertSQL(dbHits));
            
            //print damages
            System.out.println(DBDamage.MultipleInsertSQL(dbDamage));
            
            //print kills
            System.out.println(DBKill.MultipleInsertSQL(dbKills));
            
            //print influence areas
            System.out.println(DBInfluenceArea.MultipleInsertSQL(dbInfluenceAreas));
            
            //print influence area boundaries (points)
            System.out.println(DBInfluenceAreaBoundary.MultipleInsertSQL(dbIABoundaries));
            
            //print influence area headers (coalition plane counts present)
            System.out.println(DBInfluenceAreaHeader.MultipleInsertSQL(dbIAHeaders));
        }
        
        System.out.println("Next stage is to process the parsed data, then ultimately upload to a database.\n"
                         + "Note: Mission saving of concatenated log and deletion of original logs is currently disabled!");
        
    }
}
