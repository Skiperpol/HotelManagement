package com.hotelmangementprogram.hotelmanagement.model;

import com.hotelmangementprogram.hotelmanagement.PaycheckStrategy.Hourly;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity(name = "WAITER")
@Getter
@NoArgsConstructor
@DiscriminatorValue("WAITER")
public class Waiter extends Employee implements Serializable {
    @Column(name = "hourlyWage")
    private Float hourlyWage;

    public Waiter(Long employeeId, String firstName, String lastName, String pesel, String phoneNumber, String emailAddress, Job job, Float hourlyWage) {
        super(employeeId, firstName, lastName, pesel, phoneNumber, emailAddress, job);
        this.hourlyWage = hourlyWage;
        paycheck=new Hourly(hourlyWage,8f);
    }
}
