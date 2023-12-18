package com.hotelmangementprogram.hotelmanagement.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity(name = "ADMIN")
@Getter
@NoArgsConstructor
@DiscriminatorValue("ADMIN")
public class Admin extends Employee implements Serializable {

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
