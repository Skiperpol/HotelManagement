package com.hotelmangementprogram.hotelmanagement.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employee")
@Getter
@NoArgsConstructor
public class Waiter extends Employee{
//    @Column(name = "hourlyWage")
    private Float hourlyWage;

    public Waiter(Long employeeId, String firstName, String lastName, String pesel, String phoneNumber, String emailAddress, Job job, Float commission){
        super(employeeId, firstName, lastName, pesel, phoneNumber, emailAddress, job);
        this.hourlyWage = hourlyWage;

        //Lombok does not have an annotation for superclasses sadly
    }
    @Override
    public void calculatePaycheck(){
    //strategy Hourly
    }

    public void acceptOrder(){

    }
}
