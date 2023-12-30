package com.hotelmangementprogram.hotelmanagement.PaycheckStrategy;

public class Hourly implements Paycheck{
    private Float hourlyWage;
    private Float hoursWorked;
    public Hourly(Float hourlyWage, Float hoursWorked) {
        this.hourlyWage = hourlyWage;
        this.hoursWorked = hoursWorked;
    }

    @Override
    public Float calculatePaycheck() {
        return hourlyWage*hoursWorked;
    }
}
