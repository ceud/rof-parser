package RoFParser.AType;

/**
 * AType=0 signifies the start of a mission
 * 
 * @author Ceud
 */
public class MissionStart extends AType
{
    protected String gDate;
    protected String gTime;
    protected String mFile;
    protected String mID;
    protected int gType;
    protected String CNTRS;
    protected String SETTS;
    protected int mods;

    public String getCNTRS() { return CNTRS; }
    public String getSETTS() { return SETTS; }
    public String getgDate() { return gDate; }
    public String getgTime() { return gTime; }
    public int getgType() { return gType; }
    public String getmFile() { return mFile; }
    public String getmID() { return mID; }
    public int getMods() { return mods; }
    
    public String getMissionName()
    {
        return mFile.substring(mFile.lastIndexOf("\\") + 1, mFile.lastIndexOf("."));
    }
    
    public MissionStart(String logEntry)
    {
        super(logEntry);
        parseLine();
    }

    @Override
    public final void parseLine()
    {
        this.tick = Integer.parseInt(
            this.logEntry.substring(this.logEntry.indexOf("T:") + 2, 
                                    this.logEntry.indexOf("AType:") - 1).trim());
        this.aType = Integer.parseInt(
            this.logEntry.substring(this.logEntry.indexOf("AType:") + 6, 
                                    this.logEntry.indexOf("GDate:") - 1).trim());
        this.gDate = this.logEntry.substring(this.logEntry.indexOf("GDate:") + 6, 
                                             this.logEntry.indexOf("GTime:") - 1).trim();
        this.gTime = this.logEntry.substring(this.logEntry.indexOf("GTime:") + 6, 
                                             this.logEntry.indexOf("MFile:") - 1).trim();
        this.mFile = this.logEntry.substring(this.logEntry.indexOf("MFile:") + 6, 
                                             this.logEntry.indexOf("MID:") - 1).trim();
        this.mID = this.logEntry.substring(this.logEntry.indexOf("MID:") + 4, 
                                           this.logEntry.indexOf("GType:") - 1).trim();
        this.gType = Integer.parseInt(this.logEntry.substring(this.logEntry.indexOf("GType:") + 6, 
                                      this.logEntry.indexOf("CNTRS:") - 1).trim());
        this.CNTRS = this.logEntry.substring(this.logEntry.indexOf("CNTRS:") + 6, 
                                             this.logEntry.indexOf("SETTS:") - 1).trim();
        this.SETTS = this.logEntry.substring(this.logEntry.indexOf("SETTS:") + 6, 
                                             this.logEntry.indexOf("MODS:") - 1).trim();
        this.mods = Integer.parseInt(this.logEntry.substring(this.logEntry.indexOf("MODS:") + 5).trim());
    }
    
    @Override
    public String toString()
    {
        return this.tick + "; " + 
               this.aType + "; " + 
               this.gDate + "; " + 
               this.gTime + "; " + 
               this.mFile + "; " + 
               this.mID + "; " + 
               this.gType + "; " + 
               this.CNTRS + "; " + 
               this.SETTS + "; " + 
               this.mods;
    }
    
}