package com.hotelmangementprogram.hotelmanagement.model;

import com.hotelmangementprogram.hotelmanagement.HotelManagementApplication;
import com.hotelmangementprogram.hotelmanagement.PaycheckStrategy.*;
import com.hotelmangementprogram.hotelmanagement.controller.HotelController;
import com.hotelmangementprogram.hotelmanagement.service.DataValidation;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.Serializable;
import java.util.ArrayList;
import java.time.DateTimeException;
import java.util.NoSuchElementException;


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


    public static ArrayList<Menu> showOrders(){
        return HotelManagementApplication.pendingOrders;
        }

    public Cook completeOrder(int orderId, Long employeeId){
        if (commission==null)
            commission=0f;

        commission += 0.05f*HotelManagementApplication.pendingOrders.get(orderId-1).getDishPrice();
        HotelManagementApplication.pendingOrders.remove(orderId-1);
        return this;
    }
}

