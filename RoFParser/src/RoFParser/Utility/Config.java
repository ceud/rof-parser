package RoFParser.Utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Ceud
 */
public class Config {
    //Directory settings
    private static String LOG_DIR;
    private static String BAK_DIR;

    //Log settings
    private static boolean DELETE_LOGS;
    private static boolean CONCAT_LOGS;
    private static boolean ARCHIVE_LOGS;
    
    //Database settings
    private static String DB_DRIVER;
    private static String DB_URL;
    private static String DB_NAME;
    private static String DB_USER;
    private static String DB_PASS;
    private static String DB_PREFIX;
    
    //Game Objects
    public static final String[] AI = { "BotBoatSwain", "BotDriver1", "BotGunner", "BotGunnerBreguet14", "BotGunnerDFWC", "BotGunnerG5_1", "BotGunnerHowitzer", "BotGunnerHP400_1", "BotGunnerHP400_2", "BotGunnerHP400_3", "BotMachinist0", "Common Bot" };
    public static final String[] Airfield = { "Airfield", "bertangles_01", "bertangles_02", "bertangles_03", "bertangles_04", "bertangles_05", "bertangles_06", "bertangles_07", "bertangles_08", "Boistrancourt", "Cappy_01", "Cappy_02", "fakefield", "fr_lrg", "fr_med", "gengoult_01", "gengoult_02", "gengoult_03", "gengoult_04", "gengoult_05", "gengoult_06", "gengoult_07", "gengoult_08", "gengoult_09", "gengoult_10", "gengoult_11", "gengoult_12", "ger_lrg", "ger_med", "Lechelle", "Roucourt", "stomer_1", "stomer_10", "stomer_11", "stomer_12", "stomer_2", "stomer_3", "stomer_4", "stomer_5", "stomer_6", "stomer_7", "stomer_8", "stomer_9", "VertGaland_01", "VertGaland_02", "VertGaland_03" };
    public static final String[] Ammo = { "BOMB_FR_20kg", "BOMB_FR_40kg", "BOMB_FR_8kg", "BOMB_GBR_112lb", "BOMB_GBR_1650lb", "BOMB_GBR_20lb", "BOMB_GBR_250lb", "BOMB_GER_100kg", "BOMB_GER_10kg", "BOMB_GER_300kg", "BOMB_GER_50kg", "BULLET_GBR_77x56R_MK7", "BULLET_GBR_77x56R_WMK1_AP", "BULLET_GER_792x57_SMK_AP", "BULLET_GER_792x57_SS", "BULLET_PISTOL", "FlareGreen", "FlareRed", "FlareWhite", "FlareYellow" };
    public static final String[] Balloon = { "AeType", "Caquot", "Drachen", "Parseval" };
    public static final String[] Flag = { "Flag" };
    public static final String[] Fortification = { "gunpos01", "gunpos_G01", "pillbox01", "pillbox02", "pillbox03", "pillbox04" };
    public static final String[] Plane = { "Airco D.H.2", "Albatros D.II", "Albatros D.II lt", "Albatros D.III", "Albatros D.Va", "Breguet 14.B2", "Bristol F2B (F.II)", "Bristol F2B (F.III)", "DFW C.V", "Fokker D.VII", "Fokker D.VIIF", "Fokker D.VIII", "Fokker Dr.I", "Fokker E.III", "Gotha G.V", "Handley Page 0-400", "Nieuport 11.C1", "Nieuport 17.C1", "Nieuport 28.C1", "Pfalz D.IIIa", "Pfalz D.XII", "R.E.8", "S.E.5a", "Sopwith Camel", "Sopwith Dolphin", "Sopwith Pup", "Sopwith Triplane", "SPAD 13.C1", "SPAD 7.C1 150hp", "SPAD 7.C1 180hp" };
    public static final String[] Ship = { "FRpenicheAAA", "GERpenicheAAA" };
    public static final String[] Spotlight = { "GBR Searchlight", "GER Searchlight" };
    public static final String[] StaticBlock = { "amiens_cathedral", "Arras_Town_Hall", "barbed01", "barbed02", "Blandy_castle", "Block of buildings", "block_L_01", "block_L_02", "block_L_03", "block_L_04", "block_L_05", "block_L_06", "block_L_07", "block_L_08", "block_L_09", "block_L_10", "block_L_11", "block_L_12", "block_L_13", "block_L_14", "block_L_15", "block_L_16", "block_L_17", "block_L_18", "block_L_19", "block_L_20", "block_L_21", "block_L_22", "block_M_01", "block_M_02", "block_M_03", "block_M_04", "block_M_05", "block_M_06", "block_M_07", "block_M_08", "block_M_09", "block_M_10", "block_M_11", "block_M_12", "block_M_13", "block_M_14", "block_M_15", "block_M_16", "block_M_17", "block_M_18", "block_S_01", "block_S_02", "block_S_03", "block_S_04", "block_S_05", "block_S_06", "block_S_07", "block_S_08", "block_S_09", "block_S_10", "bridge_hw110", "bridge_hw130", "bridge_hw150", "bridge_hw170", "bridge_hw190", "bridge_hw70", "bridge_hw90", "bridge_rr110", "bridge_rr130", "bridge_rr150", "bridge_rr170", "bridge_rr190", "bridge_rr70", "bridge_rr90", "CappyChateau", "ChateauDeBertangless", "Church_01", "Church_02", "Church_03"};
    public static final String[] StaticVehicle = { "13PdrAAA", "45QF", "75FG1897", "77mmAAA", "FK96", "Hotchkiss", "HotchkissAAA", "LMG08", "LMG08AAA", "m13" };
    public static final String[] StaticVehicleAAA = { "13PdrAAA", "77mmAAA" };
    public static final String[] StaticVehicleAAAMGun = { "HotchkissAAA", "LMG08AAA" };
    public static final String[] StaticVehicleMGun = { "Hotchkiss", "LMG08" };
    public static final String[] Tank = { "A7V", "Ca1", "FT17C", "FT17M", "Mk4F", "Mk4FGER", "Mk4M", "Mk4MGER", "Mk5F", "Mk5M", "StChamond", "Whippet" };
    public static final String[] TrainLocomotive = { "G8" };
    public static final String[] TrainWagon = { "Wagon_BoxB", "Wagon_BoxNB", "Wagon_Car", "Wagon_G8T", "Wagon_GondolaB", "Wagon_GondolaNB", "Wagon_Pass", "Wagon_PassA", "Wagon_PassAC", "Wagon_PassC", "Wagon_PlatformA7V", "Wagon_PlatformB", "Wagon_PlatformEmptyB", "Wagon_PlatformEmptyNB", "Wagon_PlatformMk4", "Wagon_PlatformNB", "Wagon_Tank", "Wagon_TankB", "Wagon_TankNB" };
    public static final String[] Truck = { "Benz Searchlight", "benz_open", "benz_soft", "Crossley", "DaimlerAAA", "DaimlerMarienfelde", "DaimlerMarienfelde_S", "Leyland", "LeylandS", "Merc22", "Quad", "Quad Searchlight", "QuadA", "thornycroftaaa", "VehicleAuadA" };
    public static final String[] TruckAAA = { "DaimlerAAA", "thornycroftaaa" };
    public static final String[] Turret = { "TurretBreguet14_1", "TurretBristolF2B_1", "TurretDFWC_1", "TurretGothaG5_1", "TurretGothaG5_2", "TurretHP400_1", "TurretHP400_2", "TurretHP400_3", "TurretRE8_1" };
    public static final String[] TurretRiffle = { "TurretRiffle" };
    public static final String[] VehicleExplosionTurret = { "HowitzerTurret", "M13Turret" };
    public static final String[] VehicleTurretAI = { "VehicleTurretFieldCannon", "VehicleTurretInputController0", "VehicleTurretTankCannon" };
    public static final String[] Windsock = { "Windsock" };
    
    private Config()
    {
        //
    }

    public static String LogDir() { return Config.LOG_DIR; }
    public static String BakDir() { return Config.BAK_DIR; }
    public static boolean DeleteLogs() { return Config.DELETE_LOGS; }
    public static boolean ConcatLogs() { return Config.CONCAT_LOGS; }
    public static boolean ArchiveLogs() { return Config.ARCHIVE_LOGS; }
    public static String DBDriver() { return Config.DB_DRIVER; }
    public static String DBURL() { return Config.DB_URL; }
    public static String DBName() { return Config.DB_NAME; }
    public static String DBUser() { return Config.DB_USER; }
    public static String DBPass() { return Config.DB_PASS; }
    public static String DBPrefix() { return Config.DB_PREFIX; }
    
    public static void LoadConfig(String fileName)
    {
        String log = "";
        String bak = "";
        boolean del = true;
        boolean con = true;
        boolean arc = true;
        String driver = "";
        String url = "";
        String name = "";
        String user = "";
        String pass = "";
        String prefix = "";
        
        try {
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            String str;
            while ((str = in.readLine()) != null)
            {
                if (str.startsWith("LOG_DIR:"))
                {
                    log = str.substring(8).trim();
                }
                else if (str.startsWith("BAK_DIR:"))
                {
                    bak = str.substring(8).trim();
                }
                else if (str.startsWith("DELETE_LOGS:"))
                {
                    del = str.substring(12).trim().equals("1") ? true : false;
                }
                else if (str.startsWith("CONCAT_LOGS:"))
                {
                    con = str.substring(12).trim().equals("1") ? true : false;
                }
                else if (str.startsWith("ARCHIVE_LOGS:"))
                {
                    arc = str.substring(13).trim().equals("1") ? true : false;
                }
                else if (str.startsWith("DB_DRIVER:"))
                {
                    driver = str.substring(10).trim();
                }
                else if (str.startsWith("DB_URL:"))
                {
                    url = str.substring(7).trim();
                }
                else if (str.startsWith("DB_NAME:"))
                {
                    name = str.substring(8).trim();
                }
                else if (str.startsWith("DB_USER:"))
                {
                    user = str.substring(8).trim();
                }
                else if (str.startsWith("DB_PASS:"))
                {
                    pass = str.substring(8).trim();
                }
                else if (str.startsWith("DB_PREFIX:"))
                {
                    prefix = str.substring(10).trim();
                }
            }
            in.close();
            Config.ARCHIVE_LOGS = arc;
            Config.CONCAT_LOGS = con;
            Config.DELETE_LOGS = del;
            Config.BAK_DIR = bak;
            Config.LOG_DIR = log;
            Config.DB_DRIVER = driver;
            Config.DB_URL = url;
            Config.DB_NAME = name;
            Config.DB_USER = user;
            Config.DB_PASS = pass;
            Config.DB_PREFIX = prefix;
        } catch (IOException e) {
        }
    }
}
