package com.hotelmangementprogram.hotelmanagement.model;

import com.hotelmangementprogram.hotelmanagement.PaycheckStrategy.*;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity(name = "COOK")
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("COOK")
public class Cook extends Employee implements Serializable {
    @Column(name = "salary")
    private Float salary;
    @Column(name = "commission")
    private Float commission;

    public Cook(Long employeeId, String firstName, String lastName, String pesel, String phoneNumber, String emailAddress, Job job, Float commission, Float salary){
        super(employeeId, firstName, lastName, pesel, phoneNumber, emailAddress, job);
        this.commission = commission;
        this.salary = salary;
        paycheck=new SalaryAndCommision(salary,commission);
    }

    public void completeOrder(){

    }

    public void showOrders(){

    }

}
