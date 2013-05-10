package ua.vynnyk.calibration.model.entity;

import java.math.BigDecimal;
import java.util.Date;

public final class Calibration {
     
     public static String[] title = new String[] {"№",
                                                  "Водомір",
                                                  "Початкова похибка",
                                                  "Похибка 1",
                                                  "Похибка 2",
                                                  "Похибка 3",
                                                  "Показник зняття",
                                                  "Показник встановлення",
                                                  "ДСТУ №",
                                                  "ДСТУ пломба"};
    
     private int id;
     private Meter meter;
     private Date dates;
     private BigDecimal error0;
     private BigDecimal error1;
     private BigDecimal error2;
     private BigDecimal error3;
     private BigDecimal meterageSt;
     private BigDecimal meterageEnd;
     private int dstuNumber;
     private String dstuSeal;

    public Calibration() {
        this.meter = new Meter();
    }
	
    public Calibration(int id, Meter meters, Date dates) {
        this.id = id;
        this.meter = meters;
        this.dates = dates;
    }
    
    public Calibration(int id, Meter meter, Date dates, 
                       BigDecimal error0, BigDecimal error1, BigDecimal error2, BigDecimal error3, 
                       BigDecimal meterageSt, BigDecimal meterageEnd, int dstuNumber, String dstuSeal) {
       this.id = id;
       this.meter = meter;
       this.dates = dates;
       this.error0 = error0;
       this.error1 = error1;
       this.error2 = error2;
       this.error3 = error3;
       this.meterageSt = meterageSt;
       this.meterageEnd = meterageEnd;
       this.dstuNumber = dstuNumber;
       this.dstuSeal = dstuSeal;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Meter getMeter() {
        return this.meter;
    }
    
    public void setMeter(Meter meter) {
        this.meter = meter;
    }
    public Date getDates() {
        return this.dates;
    }
    
    public void setDates(Date dates) {
        this.dates = dates;
    }
    public BigDecimal getError0() {
        return this.error0;
    }
    
    public void setError0(BigDecimal error0) {
        this.error0 = error0;
    }
    public BigDecimal getError1() {
        return this.error1;
    }
    
    public void setError1(BigDecimal error1) {
        this.error1 = error1;
    }
    public BigDecimal getError2() {
        return this.error2;
    }
    
    public void setError2(BigDecimal error2) {
        this.error2 = error2;
    }
    public BigDecimal getError3() {
        return this.error3;
    }
    
    public void setError3(BigDecimal error3) {
        this.error3 = error3;
    }
    public BigDecimal getMeterageSt() {
        return this.meterageSt;
    }
    
    public void setMeterageSt(BigDecimal meterageSt) {
        this.meterageSt = meterageSt;
    }
    public BigDecimal getMeterageEnd() {
        return this.meterageEnd;
    }
    
    public void setMeterageEnd(BigDecimal meterageEnd) {
        this.meterageEnd = meterageEnd;
    }
    public int getDstuNumber() {
        return this.dstuNumber;
    }
    
    public void setDstuNumber(int dstuNumber) {
        this.dstuNumber = dstuNumber;
    }
    public String getDstuSeal() {
        return this.dstuSeal;
    }
    
    public void setDstuSeal(String dstuSeal) {
        this.dstuSeal = dstuSeal;
    }
}


