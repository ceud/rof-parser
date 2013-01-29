package RoFParser.AType;

import RoFParser.Utility.Config;

/**
 * AType=12 signifies that a game object spawned
 * 
 * @author Ceud
 */
public class GameObjectInvolved extends AType
{
    protected int id;
    protected String type;
    protected int country;
    protected String name;
    protected int parentID;
    
    public GameObjectInvolved(String logEntry)
    {
        super(logEntry);
        parseLine();
    }

    public int getCountry() { return country; }
    public int getID() { return id; }
    public String getName() { return name; }
    public int getParentID() { return parentID; }
    public String getType() { return type; }
    
    public boolean isCrew()
    {
        for (String s : Config.AI)
        {
            if (s.equals(type))
            {
                return true;
            }
        }
        return false;
    }
    
    public boolean isPlane()
    {
        for (String s : Config.Plane)
        {
            if (s.equals(type))
            {
                return true;
            }
        }
        return false;
    }
    
    public boolean isPlaneTurret()
    {
        for (String s : Config.Turret)
        {
            if (s.equals(type))
            {
                return true;
            }
        }
        return false;
    }
    
    public boolean isAirfield()
    {
        for (String s : Config.Airfield)
        {
            if (s.equals(type))
            {
                return true;
            }
        }
        return false;
    }
    
    public boolean isAmmo()
    {
        for (String s : Config.Ammo)
        {
            if (s.equals(type))
            {
                return true;
            }
        }
        return false;
    }
    
    public boolean isBalloon()
    {
        for (String s : Config.Balloon)
        {
            if (s.equals(type))
            {
                return true;
            }
        }
        return false;
    }
    
    public boolean isFlag()
    {
        for (String s : Config.Flag)
        {
            if (s.equals(type))
            {
                return true;
            }
        }
        return false;
    }
    
    public boolean isFortification()
    {
        for (String s : Config.Fortification)
        {
            if (s.equals(type))
            {
                return true;
            }
        }
        return false;
    }
    
    public boolean isShip()
    {
        for (String s : Config.Ship)
        {
            if (s.equals(type))
            {
                return true;
            }
        }
        return false;
    }
    
    public boolean isSpotlight()
    {
        for (String s : Config.Spotlight)
        {
            if (s.equals(type))
            {
                return true;
            }
        }
        return false;
    }
    
    public boolean isStaticBlock()
    {
        for (String s : Config.StaticBlock)
        {
            if (s.equals(type))
            {
                return true;
            }
        }
        return false;
    }
    
    public boolean isStaticVehicle()
    {
        for (String s : Config.StaticVehicle)
        {
            if (s.equals(type))
            {
                return true;
            }
        }
        return false;
    }
    
    public boolean isStaticVehicleAAA()
    {
        for (String s : Config.StaticVehicleAAA)
        {
            if (s.equals(type))
            {
                return true;
            }
        }
        return false;
    }
    
    public boolean isStaticVehicleAAAMGun()
    {
        for (String s : Config.StaticVehicleAAAMGun)
        {
            if (s.equals(type))
            {
                return true;
            }
        }
        return false;
    }
    
    public boolean isStaticVehicleMGun()
    {
        for (String s : Config.StaticVehicleMGun)
        {
            if (s.equals(type))
            {
                return true;
            }
        }
        return false;
    }
    
    public boolean isTank()
    {
        for (String s : Config.Tank)
        {
            if (s.equals(type))
            {
                return true;
            }
        }
        return false;
    }
    
    public boolean isTrainLocomotive()
    {
        for (String s : Config.TrainLocomotive)
        {
            if (s.equals(type))
            {
                return true;
            }
        }
        return false;
    }
    
    public boolean isTrainWagon()
    {
        for (String s : Config.TrainWagon)
        {
            if (s.equals(type))
            {
                return true;
            }
        }
        return false;
    }
    
    public boolean isTruck()
    {
        for (String s : Config.Truck)
        {
            if (s.equals(type))
            {
                return true;
            }
        }
        return false;
    }
    
    public boolean isTruckAAA()
    {
        for (String s : Config.TruckAAA)
        {
            if (s.equals(type))
            {
                return true;
            }
        }
        return false;
    }
    
    public boolean isTurretRiffle()
    {
        for (String s : Config.TurretRiffle)
        {
            if (s.equals(type))
            {
                return true;
            }
        }
        return false;
    }
    
    public boolean isVehicleExplosionTurret()
    {
        for (String s : Config.VehicleExplosionTurret)
        {
            if (s.equals(type))
            {
                return true;
            }
        }
        return false;
    }
    
    public boolean isVehicleTurretAI()
    {
        for (String s : Config.VehicleTurretAI)
        {
            if (s.equals(type))
            {
                return true;
            }
        }
        return false;
    }
    
    public boolean isWindsock()
    {
        for (String s : Config.Windsock)
        {
            if (s.equals(type))
            {
                return true;
            }
        }
        return false;
    }
    
    public String getDescriptiveType()
    {
        String type;
        
        if (isCrew()) { type = "crew"; }
        else if (isPlane()) { type = "plane"; }
        else if (isPlaneTurret()) { type = "plane_turret"; }
        else if (isAirfield()) { type = "airfield"; }
        else if (isAmmo()) { type = "ammo"; }
        else if (isBalloon()) { type = "balloon"; }
        else if (isFlag()) { type = "flag"; }
        else if (isFortification()) { type = "fortification"; }
        else if (isShip()) { type = "ship"; }
        else if (isSpotlight()) { type = "spotlight"; }
        else if (isStaticBlock()) { type = "building"; }
        else if (isStaticVehicle()) { type = "vehicle"; }
        else if (isStaticVehicleAAA()) { type = "vehicle_aaa"; }
        else if (isStaticVehicleAAAMGun()) { type = "vehicle_aaa_mgun"; }
        else if (isStaticVehicleMGun()) { type = "vehicle_mgun"; }
        else if (isTank()) { type = "tank"; }
        else if (isTrainLocomotive()) { type = "train_locomotive"; }
        else if (isTrainWagon()) { type = "train_wagon"; }
        else if (isTruck()) { type = "truck"; }
        else if (isTruckAAA()) { type = "truck_aaa"; }
        else if (isTurretRiffle()) { type = "turret_rifle"; }
        else if (isVehicleExplosionTurret()) { type = "vehicle_turret"; }
        else if (isVehicleTurretAI()) { type = "vehicle_turret"; }
        else if (isWindsock()) { type = "windsock"; }
        else { type = "unknown"; }
        
        return type;
    }
    
    @Override
    public final void parseLine()
    {
        this.tick = Integer.parseInt(
            this.logEntry.substring(this.logEntry.indexOf("T:") + 2, 
                                    this.logEntry.indexOf("AType:") - 1).trim());
        this.aType = Integer.parseInt(
            this.logEntry.substring(this.logEntry.indexOf("AType:") + 6, 
                                    this.logEntry.indexOf("ID:") - 1).trim());
        this.id = Integer.parseInt(this.logEntry.substring(this.logEntry.indexOf("ID:") + 3, 
                                   this.logEntry.indexOf("TYPE:") - 1).trim());
        this.type = this.logEntry.substring(this.logEntry.indexOf("TYPE:") + 5, 
                                            this.logEntry.indexOf("COUNTRY:") - 1).trim();
        this.country = Integer.parseInt(this.logEntry.substring(this.logEntry.indexOf("COUNTRY:") + 8, 
                                        this.logEntry.indexOf("NAME:") - 1).trim());
        this.name = this.logEntry.substring(this.logEntry.indexOf("NAME:") + 5, 
                                        this.logEntry.indexOf("PID:") - 1).trim();
        this.parentID = Integer.parseInt(this.logEntry.substring(this.logEntry.indexOf("PID:") + 4).trim());
    }
    
    @Override
    public String toString()
    {
        return this.tick + "; " + 
               this.aType + "; " + 
               this.id + "; " + 
               this.type + "; " + 
               this.country + "; " + 
               this.name + "; " + 
               this.parentID;
    }
    
}
