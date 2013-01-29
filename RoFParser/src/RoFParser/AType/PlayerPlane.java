package RoFParser.AType;

/**
 * AType=10 signifies that a player plane respawned
 * 
 * @author Ceud
 */
public class PlayerPlane extends AType
{
    protected int playerID;
    protected int planeID;
    protected int bullets;
    protected int sh;
    protected int bombs;
    protected int rct;
    protected float[] rctPos;
    
    protected String ids;
    protected String login;
    protected String name;
    protected String type;
    protected int country;
    protected int form;
    protected int field;
    protected int inAir;
    protected int parent;
    protected int payload;
    protected float fuel;
    protected String skin;
    
    public PlayerPlane(String logEntry)
    {
        super(logEntry);
        parseLine();
    }

    public int getBombs() { return bombs; }
    public int getBullets() { return bullets; }
    public int getCountry() { return country; }
    public int getField() { return field; }
    public int getForm() { return form; }
    public float getFuel() { return fuel; }
    public String getIds() { return ids; }
    public int getInAir() { return inAir; }
    public String getLogin() { return login; }
    public String getName() { return name; }
    public int getParent() { return parent; }
    public int getPayload() { return payload; }
    public int getPlayerID() { return playerID; }
    public int getPlaneID() { return planeID; }
    public int getRct() { return rct; }
    public float[] getRctPos() { return rctPos; }
    public int getSh() { return sh; }
    public String getSkin() { return skin; }
    public String getType() { return type; }
    
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
                                                this.logEntry.indexOf(") IDS:") - 1).replaceAll(" ", "").split(",");
        this.rctPos = new float[3];
        this.rctPos[0] = Float.parseFloat(tmpS[0]);
        this.rctPos[1] = Float.parseFloat(tmpS[1]);
        this.rctPos[2] = Float.parseFloat(tmpS[2]);
        
        this.rct = Integer.parseInt(this.logEntry.substring(this.logEntry.indexOf("RCT:") + 4, 
                                                            this.logEntry.indexOf("(") - 1).trim());

        this.ids = this.logEntry.substring(this.logEntry.indexOf("IDS:") + 4, this.logEntry.indexOf("LOGIN:") - 1).trim();
        this.login = this.logEntry.substring(this.logEntry.indexOf("LOGIN:") + 6, this.logEntry.indexOf("NAME:") - 1).trim();
        this.name = this.logEntry.substring(this.logEntry.indexOf("NAME:") + 5, this.logEntry.indexOf("TYPE:") - 1).trim();
        this.type = this.logEntry.substring(this.logEntry.indexOf("TYPE:") + 5, this.logEntry.indexOf("COUNTRY:") - 1).trim();
        
        this.country = Integer.parseInt(this.logEntry.substring(this.logEntry.indexOf("COUNTRY:") + 8, 
                                                                this.logEntry.indexOf("FORM:") - 1).trim());
        this.form = Integer.parseInt(this.logEntry.substring(this.logEntry.indexOf("FORM:") + 5, 
                                                             this.logEntry.indexOf("FIELD:") - 1).trim());
        this.field = Integer.parseInt(this.logEntry.substring(this.logEntry.indexOf("FIELD:") + 6, 
                                                              this.logEntry.indexOf("INAIR:") - 1).trim());
        this.inAir = Integer.parseInt(this.logEntry.substring(this.logEntry.indexOf("INAIR:") + 6, 
                                                              this.logEntry.indexOf("PARENT:") - 1).trim());
        this.parent = Integer.parseInt(this.logEntry.substring(this.logEntry.indexOf("PARENT:") + 7, 
                                                              this.logEntry.indexOf("PAYLOAD:") - 1).trim());
        this.payload = Integer.parseInt(this.logEntry.substring(this.logEntry.indexOf("PAYLOAD:") + 8, 
                                                              this.logEntry.indexOf("FUEL:") - 1).trim());
        this.fuel = Float.parseFloat(this.logEntry.substring(this.logEntry.indexOf("FUEL:") + 5, 
                                                              this.logEntry.indexOf("SKIN:") - 1).trim());
        this.skin = this.logEntry.substring(this.logEntry.indexOf("SKIN:") + 5).trim();
        
    }
    
    @Override
    public String toString()
    {
        return this.tick + "; " + 
               this.aType + "; " + 
               this.planeID + "; " + 
               this.playerID + "; " + 
               this.bullets + "; " + 
               this.sh + "; " + 
               this.bombs + "; " + 
               this.rct + " (" + 
               this.rctPos[0] + ", " + 
               this.rctPos[1] + ", " + 
               this.rctPos[2] + "); " + 
               this.ids + "; " + 
               this.login + "; " + 
               this.name + "; " + 
               this.type + "; " + 
               this.country + "; " + 
               this.form + "; " + 
               this.field + "; " + 
               this.inAir + "; " + 
               this.parent + "; " + 
               this.payload + "; " + 
               this.fuel + "; " + 
               this.skin;
    }
    
}
