package RoFParser.AType;

/**
 * AType=3 signifies a kill of mission object
 * 
 * @author Ceud
 */
public class Kill extends AType
{
    protected int attackerID;
    protected int targetID;
    protected float[] position;
    
    public Kill(String logEntry)
    {
        super(logEntry);
        parseLine();
    }

    public int getAttackerID() { return attackerID; }
    public float[] getPosition() { return position; }
    public int getTargetID() { return targetID; }

    @Override
    public final void parseLine()
    {
        this.tick = Integer.parseInt(this.logEntry.substring(this.logEntry.indexOf("T:") + 2, 
                                                             this.logEntry.indexOf("AType:") - 1).trim());
        this.aType = Integer.parseInt(this.logEntry.substring(this.logEntry.indexOf("AType:") + 6, 
                                                              this.logEntry.indexOf("AID:") - 1).trim());
        this.attackerID = Integer.parseInt(this.logEntry.substring(this.logEntry.indexOf("AID:") + 4, 
                                                                   this.logEntry.indexOf("TID:") - 1).trim());
        this.targetID = Integer.parseInt(this.logEntry.substring(this.logEntry.indexOf("TID:") + 4, 
                                                                 this.logEntry.indexOf("POS(") - 1).trim());

        String[] tmpS = this.logEntry.substring(this.logEntry.indexOf("POS(") + 4, 
                                                this.logEntry.indexOf(")") - 0).replaceAll(" ", "").split(",");
        this.position = new float[3];
        this.position[0] = Float.parseFloat(tmpS[0]);
        this.position[1] = Float.parseFloat(tmpS[1]);
        this.position[2] = Float.parseFloat(tmpS[2]);
    }
    
    @Override
    public String toString()
    {
        return this.tick + "; " + 
               this.aType + "; " + 
               this.attackerID + "; " + 
               this.targetID + "; " + 
               this.position[0] + ", " + 
               this.position[1] + ", " + 
               this.position[2] + ")";
    }
    
}
