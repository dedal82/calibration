package ua.vynnyk.calibration.entity;

public class TypeMeters {

     private int id;
     private String name;
     private int diameter;
     private int cycle;
     private String precisions;     

    public TypeMeters() {
    }
	
    public TypeMeters(int id) {
        this.id = id;
    }
    public TypeMeters(int id, String name, int diameter, int cycle, String precisions) {
       this.id = id;
       this.name = name;
       this.diameter = diameter;
       this.cycle = cycle;
       this.precisions = precisions;  
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public int getDiameter() {
        return this.diameter;
    }
    
    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }
    public int getCycle() {
        return this.cycle;
    }
    
    public void setCycle(int cycle) {
        this.cycle = cycle;
    }
    public String getPrecisions() {
        return this.precisions;
    }
    
    public void setPrecisions(String precisions) {
        this.precisions = precisions;
    }
}


