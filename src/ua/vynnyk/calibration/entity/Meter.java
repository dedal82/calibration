package ua.vynnyk.calibration.entity;

public class Meter {

     private int id;
     private TypeMeters typesMeters;
     private String number;
     private int yearProduce;

    public Meter() {
        typesMeters = new TypeMeters();
    }
	
    public Meter(int id) {
        this();
        this.id = id;
    }
    public Meter(int id, TypeMeters typesMeters, String number, int yearProduce) {
       this.id = id;
       this.typesMeters = typesMeters;
       this.number = number;
       this.yearProduce = yearProduce;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public TypeMeters getTypesMeters() {
        return this.typesMeters;
    }
    
    public void setTypesMeters(TypeMeters typesMeters) {
        this.typesMeters = typesMeters;
    }
    public String getNumber() {
        return this.number;
    }
    
    public void setNumber(String number) {
        this.number = number;
    }
    public int getYearProduce() {
        return this.yearProduce;
    }
    
    public void setYearProduce(int yearProduce) {
        this.yearProduce = yearProduce;
    }
}


