package RoFParser.AType;

/**
 * AType=1 signifies a bullet hit on mission object
 * 
 * @author Ceud
 */
public class Hit extends AType
{
    protected String ammo;
    protected int attackerID;
    protected int targetID;
    
    public Hit(String logEntry)
    {
        super(logEntry);
        parseLine();
    }

    public String getAmmo() { return ammo; }
    public int getAttackerID() { return attackerID; }
    public int getTargetID() { return targetID; }

    @Override
    public final void parseLine()
    {
        this.tick = Integer.parseInt(this.logEntry.substring(this.logEntry.indexOf("T:") + 2, 
                                                             this.logEntry.indexOf("AType:") - 1).trim());
        this.aType = Integer.parseInt(this.logEntry.substring(this.logEntry.indexOf("AType:") + 6, 
                                                              this.logEntry.indexOf("AMMO:") - 1).trim());
        this.ammo = this.logEntry.substring(this.logEntry.indexOf("AMMO:") + 5, 
                                            this.logEntry.indexOf("AID:") - 1).trim();
        this.attackerID = Integer.parseInt(this.logEntry.substring(this.logEntry.indexOf("AID:") + 4, 
                                                                   this.logEntry.indexOf("TID:") - 1).trim());
        this.targetID = Integer.parseInt(this.logEntry.substring(this.logEntry.indexOf("TID:") + 4).trim());
    }
    
    @Override
    public String toString()
    {
        String str = this.tick + "; " + 
                     this.aType + "; " + 
                     this.ammo + "; " + 
                     this.attackerID + "; " + 
                     this.targetID;
        return str;
    }
    
}
