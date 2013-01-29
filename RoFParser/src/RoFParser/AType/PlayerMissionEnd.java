package RoFParser.AType;

/**
 * AType=4 signifies a player has finished mission
 * 
 * @author Ceud
 */
public class PlayerMissionEnd extends AType
{
    protected int playerID;
    protected int planeID;
    protected int bullets;
    protected int sh;
    protected int bombs;
    protected int rct;
    protected float[] rctPos;
    
    public PlayerMissionEnd(String logEntry)
    {
        super(logEntry);
        parseLine();
    }

    public int getBombs() { return bombs; }
    public int getBullets() { return bullets; }
    public int getPlaneID() { return planeID; }
    public int getPlayerID() { return playerID; }
    public int getRct() { return rct; }
    public float[] getRctPos() { return rctPos; }
    public int getSh() { return sh; }

    @Override
    public final void parseLine()
    {
        this.tick = Integer.parseInt(this.logEntry.substring(this.logEntry.indexOf("T:") + 2, 
                                                             this.logEntry.indexOf("AType:") - 1).trim());
        this.aType = Integer.parseInt(this.logEntry.substring(this.logEntry.indexOf("AType:") + 6, 
                                                              this.logEntry.indexOf("PLID:") - 1).trim());
        
        this.playerID = Integer.parseInt(this.logEntry.substring(this.logEntry.indexOf("PLID:") + 5, 
                                                                      this.logEntry.indexOf("PID:") - 1).trim());
        this.planeID = Integer.parseInt(this.logEntry.substring(this.logEntry.indexOf("PID:") + 4, 
                                                                this.logEntry.indexOf("BUL:") - 1).trim());
        
        this.bullets = Integer.parseInt(this.logEntry.substring(this.logEntry.indexOf("BUL:") + 4, 
                                                                this.logEntry.indexOf("SH:") - 1).trim());
        this.sh = Integer.parseInt(this.logEntry.substring(this.logEntry.indexOf("SH:") + 3, 
                                                                this.logEntry.indexOf("BOMB:") - 1).trim());
        this.bombs = Integer.parseInt(this.logEntry.substring(this.logEntry.indexOf("BOMB:") + 5, 
                                                                this.logEntry.indexOf("RCT:") - 1).trim());
        this.rct = Integer.parseInt(this.logEntry.substring(this.logEntry.indexOf("RCT:") + 4, 
                                                                this.logEntry.indexOf("(") - 1).trim());

        String[] tmpS = this.logEntry.substring(this.logEntry.indexOf("(") + 1, 
                                                            this.logEntry.indexOf(")") - 0).replaceAll(" ", "").split(",");
        this.rctPos = new float[3];
        this.rctPos[0] = Float.parseFloat(tmpS[0]);
        this.rctPos[1] = Float.parseFloat(tmpS[1]);
        this.rctPos[2] = Float.parseFloat(tmpS[2]);
    }
    
    @Override
    public String toString()
    {
        return this.tick + "; " + 
               this.aType + "; " + 
               this.playerID + "; " + 
               this.planeID + "; " + 
               this.bullets + "; " + 
               this.sh + "; " + 
               this.bombs + "; " + 
               this.rct + " (" + 
               this.rctPos[0] + ", " + 
               this.rctPos[1] + ", " + 
               this.rctPos[2] + ")";
    }
    
}
