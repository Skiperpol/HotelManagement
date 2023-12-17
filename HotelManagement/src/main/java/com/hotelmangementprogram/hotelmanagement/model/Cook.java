package com.hotelmangementprogram.hotelmanagement.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employee")
@Getter
@NoArgsConstructor
public class Cook extends Employee{
    @Column(name = "salary")
    private Float salary;
    @Column(name = "commission")
    private Float commission;

    public Cook(Long employeeId, String firstName, String lastName, String pesel, String phoneNumber, String emailAddress, Job job, Float commission, Float salary){
        super(employeeId, firstName, lastName, pesel, phoneNumber, emailAddress, job);
        this.commission = commission;
        this.salary = salary;
    }

    public void completeOrder(){

    }

    public void showOrders(){

    }

    public void calculatePaycheck(){

    }
}
