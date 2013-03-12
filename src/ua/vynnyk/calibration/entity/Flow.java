package ua.vynnyk.calibration.entity;

import java.math.BigDecimal;

public class Flow {

     private int id;
     private int diameter;
     private BigDecimal capacity1;
     private BigDecimal flow1;
     private BigDecimal capacity2;
     private BigDecimal flow2;
     private BigDecimal capacity3;
     private BigDecimal flow3;

    public Flow() {
    }

	
    public Flow(int id, int diameter) {
        this.id = id;
        this.diameter = diameter;
    }
    public Flow(int id, int diameter, BigDecimal capacity1, BigDecimal flow1, BigDecimal capacity2, BigDecimal flow2, BigDecimal capacity3, BigDecimal flow3) {
       this.id = id;
       this.diameter = diameter;
       this.capacity1 = capacity1;
       this.flow1 = flow1;
       this.capacity2 = capacity2;
       this.flow2 = flow2;
       this.capacity3 = capacity3;
       this.flow3 = flow3;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public int getDiameter() {
        return this.diameter;
    }
    
    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }
    public BigDecimal getCapacity1() {
        return this.capacity1;
    }
    
    public void setCapacity1(BigDecimal capacity1) {
        this.capacity1 = capacity1;
    }
    public BigDecimal getFlow1() {
        return this.flow1;
    }
    
    public void setFlow1(BigDecimal flow1) {
        this.flow1 = flow1;
    }
    public BigDecimal getCapacity2() {
        return this.capacity2;
    }
    
    public void setCapacity2(BigDecimal capacity2) {
        this.capacity2 = capacity2;
    }
    public BigDecimal getFlow2() {
        return this.flow2;
    }
    
    public void setFlow2(BigDecimal flow2) {
        this.flow2 = flow2;
    }
    public BigDecimal getCapacity3() {
        return this.capacity3;
    }
    
    public void setCapacity3(BigDecimal capacity3) {
        this.capacity3 = capacity3;
    }
    public BigDecimal getFlow3() {
        return this.flow3;
    }
    
    public void setFlow3(BigDecimal flow3) {
        this.flow3 = flow3;
    }
}


