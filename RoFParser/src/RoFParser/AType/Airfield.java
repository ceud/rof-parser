package RoFParser.AType;

/**
 * AType=9 signifies an info record for an airfield in the mission
 * 
 * @author Ceud
 */
public class Airfield extends AType
{
    protected int aID;
    protected int country;
    protected float[] position;
    protected String[] IDS;
    
    public Airfield(String logEntry)
    {
        super(logEntry);
        parseLine();
    }

    public String[] getIDS() { return IDS; }
    public int getaID() { return aID; }
    public int getCountry() { return country; }
    public float[] getPosition() { return position; }

    @Override
    public final void parseLine()
    {
        this.tick = Integer.parseInt(this.logEntry.substring(this.logEntry.indexOf("T:") + 2, 
                                     this.logEntry.indexOf("AType:") - 1).trim());
        this.aType = Integer.parseInt(this.logEntry.substring(this.logEntry.indexOf("AType:") + 6, 
                                      this.logEntry.indexOf("AID:") - 1).trim());
        this.aID = Integer.parseInt(this.logEntry.substring(this.logEntry.indexOf("AID:") + 4, 
                                    this.logEntry.indexOf("COUNTRY:") - 1).trim());
        this.country = Integer.parseInt(this.logEntry.substring(this.logEntry.indexOf("COUNTRY:") + 8, 
                                        this.logEntry.indexOf("POS(") - 1).trim());

        String[] tmpS = this.logEntry.substring(this.logEntry.indexOf("POS(") + 4, 
                                                this.logEntry.indexOf(") IDS(") - 1).replaceAll(" ", "").split(",");
        this.position = new float[3];
        this.position[0] = Float.parseFloat(tmpS[0]);
        this.position[1] = Float.parseFloat(tmpS[1]);
        this.position[2] = Float.parseFloat(tmpS[2]);
        
        if (this.logEntry.indexOf("IDS()") != -1) //(!tmpStr.isEmpty() && tmpStr.indexOf(",") != -1)
        {
            this.IDS = new String[0];
        }
        else
        {
            this.IDS = this.logEntry.substring(this.logEntry.indexOf("IDS(") + 4, 
            this.logEntry.lastIndexOf(")") - 0).replaceAll(" ", "").split(",");
        }
    }
    
    @Override
    public String toString()
    {
        String tmpStr = "";
        for (int i = 0; i < this.IDS.length; i++)
        {
            if (tmpStr.length() > 0)
            {
                tmpStr = tmpStr + ",";
            }
            tmpStr = tmpStr + this.IDS[i];
        }
        return this.tick + "; " + 
               this.aType + "; " + 
               this.aID + "; " + 
               this.country + "; (" + 
               this.position[0] + ", " + this.position[1] + ", " + this.position[2] + ")" + "; (" + 
               tmpStr + ")";
    }
    
}
