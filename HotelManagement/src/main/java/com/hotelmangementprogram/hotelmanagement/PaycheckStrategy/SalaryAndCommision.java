package com.hotelmangementprogram.hotelmanagement.PaycheckStrategy;

public class SalaryAndCommision implements Paycheck{
    private Float salary;
    private Float commision;

    public SalaryAndCommision(Float salary, Float commision) {
        this.salary = salary;
        this.commision = commision;
    }

    @Override
    public Float calculatePaycheck() {
        return salary+commision;
    }
}
