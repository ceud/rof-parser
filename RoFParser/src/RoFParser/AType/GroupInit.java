package RoFParser.AType;

/**
 * AType=11 signifies a planes group information
 * e.g. T:1 AType:11 GID:243712 IDS:66560,71680,76800,81920 LID:66560
 * 
 * @author Ceud
 */
public class GroupInit extends AType
{
    protected int gID;
    protected int[] IDS;
    protected int lID;
    
    public GroupInit(String logEntry)
    {
        super(logEntry);
        parseLine();
    }

    public int[] getIDS() { return IDS; }
    public int getgID() { return gID; }
    public int getlID() { return lID; }

    @Override
    public final void parseLine()
    {
        this.tick = Integer.parseInt(this.logEntry.substring(this.logEntry.indexOf("T:") + 2, 
                                                             this.logEntry.indexOf("AType:") - 1).trim());
        this.aType = Integer.parseInt(this.logEntry.substring(this.logEntry.indexOf("AType:") + 6, 
                                                              this.logEntry.indexOf("GID:") - 1).trim());
        this.gID = Integer.parseInt(this.logEntry.substring(this.logEntry.indexOf("GID:") + 4, 
                                                            this.logEntry.indexOf("IDS:") - 1).trim());

        String[] tmpIDS = this.logEntry.substring(this.logEntry.indexOf("IDS:") + 4, 
                          this.logEntry.indexOf("LID:") - 1).replaceAll(" ", "").split(",");
        this.IDS = new int[tmpIDS.length];
        for (int i = 0; i < tmpIDS.length; i++)
        {
            this.IDS[i] = Integer.parseInt(tmpIDS[i]);
        }
        
        this.lID = Integer.parseInt(this.logEntry.substring(this.logEntry.indexOf("LID:") + 4).trim());
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
               this.gID + "; " + 
               tmpStr + "; " + 
               this.lID;
    }
    
}
