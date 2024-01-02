package com.hotelmangementprogram.hotelmanagement.model;

import com.hotelmangementprogram.hotelmanagement.HotelManagementApplication;
import com.hotelmangementprogram.hotelmanagement.PaycheckStrategy.*;
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
        paycheck = new Salary(salary);
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

    public static Employee payoutPaycheck(Employee employee)
    {
        HotelManagementApplication.balance-=employee.calculatePaycheck();
        if(employee instanceof Cook)
        {
            ((Cook) employee).setCommission(0f);
        }
        else if(employee instanceof Cleaner)
        {
            ((Cleaner) employee).setCommission(0f);
        }

        return employee;
    }
}
