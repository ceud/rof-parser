package RoFParser.Utility;

/**
 * Opens and closes connections to and from a mySQL database
 * 
 * @author Ceud
 */
import java.sql.*;

public class MySQLDB
{
    private static Connection conn = null;
    
    private MySQLDB()
    {
        //unused
    }
    
    /**
     * Gets current database connection if set, else it calls Connect()
     */
    public static Connection Connection()
    {
        if (conn == null)
        {
            Connect();
        }
        return conn;
    }
    
    /**
     * Opens database connection
     */
    public static void Connect()
    {
        if (conn != null)
        {
            Disconnect();
        }
        try
        {
            Class.forName(Config.DBDriver()).newInstance();
            conn = DriverManager.getConnection(Config.DBURL() + Config.DBName(),Config.DBUser(),Config.DBPass());
            System.out.println("Connected to the database");
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * Closes database connection
     */
    public static void Disconnect()
    {
        if (conn != null)
        {
            try {
                conn.close();
            } catch (SQLException e) {
                //e.printStackTrace();
            } finally {
                conn = null;
                System.out.println("Disconnected from database");
            }
        }
    }
}