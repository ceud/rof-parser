package RoFParser.AType;

/**
 * AType=13 signifies info of influence area
 * e.g. T:136544 AType:13 AID:110592 COUNTRY:102 ENABLED:1 BC(0,12,9,0,0,0,0,0)
 * 
 * @author Ceud
 */
public class InfluenceAreaHeader extends AType
{
    protected int areaID;
    protected int country;
    protected int enabled;
    protected int[] coalitionsPresent;
    
    public InfluenceAreaHeader(String logEntry)
    {
        super(logEntry);
        parseLine();
    }

    public int getAreaID() { return areaID; }
    public int[] getCoalitionsPresent() { return coalitionsPresent; }
    public int getCountry() { return country; }
    public int getEnabled() { return enabled; }

    @Override
    public final void parseLine()
    {
        this.tick = Integer.parseInt(
            this.logEntry.substring(this.logEntry.indexOf("T:") + 2, 
                                    this.logEntry.indexOf("AType:") - 1).trim());
        this.aType = Integer.parseInt(
            this.logEntry.substring(this.logEntry.indexOf("AType:") + 6, 
                                    this.logEntry.indexOf("AID:") - 1).trim());
        this.areaID = Integer.parseInt(
            this.logEntry.substring(this.logEntry.indexOf("AID:") + 4, 
                                    this.logEntry.indexOf("COUNTRY:") - 1).trim());
        this.country = Integer.parseInt(
            this.logEntry.substring(this.logEntry.indexOf("COUNTRY:") + 8, 
                                    this.logEntry.indexOf("ENABLED:") - 1).trim());
        this.enabled = Integer.parseInt(
            this.logEntry.substring(this.logEntry.indexOf("ENABLED:") + 8, 
                                    this.logEntry.indexOf("BC(") - 1).trim());
        
        String[] tmpBC = this.logEntry.substring(this.logEntry.indexOf("BC(") + 3, 
                                    this.logEntry.indexOf(")")).trim().split(",");
        this.coalitionsPresent = new int[8];
        for (int i = 0; i < 8; i++)
        {
            this.coalitionsPresent[i] = Integer.parseInt(tmpBC[i]);
        }
    }
    
    @Override
    public String toString()
    {
        String tmpStr = "";
        for (int i = 0; i < this.coalitionsPresent.length; i++)
        {
            if (tmpStr.length() > 0)
            {
                tmpStr = tmpStr + ",";
            }
            tmpStr = tmpStr + this.coalitionsPresent[i];
        }
        return this.tick + "; " + 
               this.aType + "; " + 
               this.areaID + "; " + 
               this.country + "; " + 
               this.enabled + "; (" + 
               tmpStr + ")";
    }
    
}
