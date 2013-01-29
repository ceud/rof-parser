package RoFParser.AType;

/**
 * AType=8 signifies the mission objective has been completed
 * 
 * @author Ceud
 */
public class MissionObject extends AType
{
    protected int objID;
    protected float[] position;
    protected int coalition;
    protected int type;
    protected int result;
    
    public MissionObject(String logEntry)
    {
        super(logEntry);
        parseLine();
    }

    public int getCoalition() { return coalition; }
    public int getObjID() { return objID; }
    public float[] getPosition() { return position; }
    public int getResult() { return result; }
    public int getType() { return type; }

    @Override
    public final void parseLine()
    {
        this.tick = Integer.parseInt(
            this.logEntry.substring(this.logEntry.indexOf("T:") + 2, 
                                    this.logEntry.indexOf("AType:") - 1).trim());
        this.aType = Integer.parseInt(
            this.logEntry.substring(this.logEntry.indexOf("AType:") + 6, 
                                    this.logEntry.indexOf("OBJID:") - 1).trim());
        this.objID = Integer.parseInt(this.logEntry.substring(this.logEntry.indexOf("OBJID:") + 6, 
                                      this.logEntry.indexOf("POS(") - 1).trim());

        String[] tmpS = this.logEntry.substring(this.logEntry.indexOf("POS(") + 4, this.logEntry.indexOf(") COAL:") - 1).replaceAll(" ", "").split(",");
        this.position = new float[3];
        this.position[0] = Float.parseFloat(tmpS[0]);
        this.position[1] = Float.parseFloat(tmpS[1]);
        this.position[2] = Float.parseFloat(tmpS[2]);
        
        this.coalition = Integer.parseInt(this.logEntry.substring(this.logEntry.indexOf("COAL:") + 5, 
                                          this.logEntry.indexOf("TYPE:") - 1).trim());
        
        this.type = Integer.parseInt(this.logEntry.substring(this.logEntry.indexOf("TYPE:") + 5, 
                                     this.logEntry.indexOf("RES:") - 1).trim());
        
        this.result = Integer.parseInt(this.logEntry.substring(this.logEntry.indexOf("RES:") + 4, 
                                     this.logEntry.indexOf("RES:") + 5).trim());
    }
    
    @Override
    public String toString()
    {
        return this.tick + "; " + 
               this.aType + "; " + 
               this.objID + "; (" + 
               this.position[0] + ", " + 
               this.position[1] + ", " + 
               this.position[2] + ") " + 
               this.coalition + "; " + 
               this.type + "; " + 
               this.result;
    }
    
}
