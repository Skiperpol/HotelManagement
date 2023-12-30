package com.hotelmangementprogram.hotelmanagement.PaycheckStrategy;

public class Salary implements Paycheck{
    private Float salary;
    public Salary(Float salary)
    {
        this.salary = salary;
    }

    public Float calculatePaycheck()
    {
        return salary;
    }
}
