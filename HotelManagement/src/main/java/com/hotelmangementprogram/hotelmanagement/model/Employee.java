package com.hotelmangementprogram.hotelmanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Getter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Employee extends People{
    @Column(name = "job")
    @Enumerated(EnumType.STRING)
    protected Job job;
    protected String login;
    protected String haslo;

    //paychecks
    public Employee(Long employeeId, String firstName, String lastName, String pesel, String phoneNumber, String emailAddress, Job job){
        super(employeeId, firstName, lastName, pesel, phoneNumber, emailAddress);
        this.job = job;
        this.login = login;
        this.haslo = haslo;
        //Lombok does not have an annotation for superclasses sadly
    }
    abstract void calculatePaycheck();
}
