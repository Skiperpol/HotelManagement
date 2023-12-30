package com.hotelmangementprogram.hotelmanagement.PaycheckStrategy;

public class Commision implements Paycheck{
    private Float commission;

    public Commision(Float commission) {
        this.commission = commission;
    }

    @Override
    public Float calculatePaycheck() {
        return commission;
    }
}
