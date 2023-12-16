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
public class Admin extends Employee{

    @Column(name = "salary")
    private Float salary;

    public Admin(Long employeeId, String firstName, String lastName, String pesel, String phoneNumber, String emailAddress, Job job, Float salary){
        super(employeeId, firstName, lastName, pesel, phoneNumber, emailAddress, job);
        this.salary = salary;
    }

    public void fire(){

    }

    public void hire(){

    }

    public void preview(){

    }

    public void edit(){

    }
    public void raport(){

    }
    public void calculatePaycheck(){

    }
}
