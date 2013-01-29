package RoFParser.AType;

/**
 * AType=14 signifies zone boundary
 * e.g. T:1 AType:14 AID:108544 BP((133357.3,84.9,60437.9),(144639.3,84.9,50986.9),(159883.3,84.9,52150.9),(164799.3,84.9,53136.9),(187742.3,84.9,53102.9),(195996.3,84.9,60435.9),(196097.3,84.9,106921.9),(132545.3,84.9,109050.9))
 * 
 * @author Ceud
 */
public class InfluenceAreaBoundary extends AType
{
    protected int areaID;
    protected String[] boundaryPoints;
    
    public InfluenceAreaBoundary(String logEntry)
    {
        super(logEntry);
        parseLine();
    }

    public int getAreaID() { return areaID; }
    public String[] getBoundaryPoints() { return boundaryPoints; }

    @Override
    public final void parseLine()
    {
        this.tick = Integer.parseInt(
            this.logEntry.substring(this.logEntry.indexOf("T:") + 2, 
                                    this.logEntry.indexOf("AType:") - 1).trim());
        this.aType = Integer.parseInt(
            this.logEntry.substring(this.logEntry.indexOf("AType:") + 6, 
                                    this.logEntry.indexOf("AID:") - 1).trim());
        this.areaID = Integer.parseInt(this.logEntry.substring(this.logEntry.indexOf("AID:") + 4, 
                                    this.logEntry.indexOf("BP((") - 1).trim());

        String tmpBP = this.logEntry.substring(this.logEntry.indexOf("BP(") + 3, 
                                               this.logEntry.lastIndexOf(")") - 1).replaceAll(" ", "");
        
        if (tmpBP.length() > 0)
        {
            this.boundaryPoints = new String[tmpBP.substring(1, tmpBP.lastIndexOf(")") - 1).replaceAll(" ", "").split("\\),\\(").length];
            this.boundaryPoints = tmpBP.substring(1, tmpBP.lastIndexOf(")") - 0).replaceAll(" ", "").split("\\),\\(");
        }
        else
        {
            this.boundaryPoints = new String[0];
        }
    }
    
    @Override
    public String toString()
    {
        String tmpStr = "";
        for (int i = 0; i < this.boundaryPoints.length; i++)
        {
            if (tmpStr.length() > 0)
            {
                tmpStr = tmpStr + ",";
            }
            tmpStr = tmpStr + "(" + this.boundaryPoints[i] + ")";
        }
        return this.tick + "; " + 
               this.aType + "; " + 
               this.areaID + "; (" + 
               tmpStr + ")";
    }
}
