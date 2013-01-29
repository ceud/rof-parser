package RoFParser.AType;

/**
 * AType=6 signifies an aircraft has landed
 * 
 * @author Ceud
 */
public class Landing extends AType
{
    protected int planeID;
    protected float[] position;
    
    public Landing(String logEntry)
    {
        super(logEntry);
        parseLine();
    }

    public int getPlaneID() { return planeID; }
    public float[] getPosition() { return position; }

    @Override
    public final void parseLine()
    {
        this.tick = Integer.parseInt(this.logEntry.substring(this.logEntry.indexOf("T:") + 2, 
                                                             this.logEntry.indexOf("AType:") - 1).trim());
        this.aType = Integer.parseInt(this.logEntry.substring(this.logEntry.indexOf("AType:") + 6, 
                                                              this.logEntry.indexOf("PID:") - 1).trim());
        this.planeID = Integer.parseInt(this.logEntry.substring(this.logEntry.indexOf("PID:") + 4, 
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
               this.planeID + "; (" + 
               this.position[0] + ", " + 
               this.position[1] + ", " + 
               this.position[2] + ")";
    }
    
}
