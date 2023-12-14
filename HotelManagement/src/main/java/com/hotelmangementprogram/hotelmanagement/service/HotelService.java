package com.hotelmangementprogram.hotelmanagement.service;

import com.hotelmangementprogram.hotelmanagement.model.Employee;
import com.hotelmangementprogram.hotelmanagement.model.EmployeeLogin;
import com.hotelmangementprogram.hotelmanagement.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@RequiredArgsConstructor
public class HotelService {
    @Autowired
    private EmployeeLoginRepository employeeLoginRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private GuestRepository guestRepository;
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private RoomRepository roomRepository;

    /**
     * Method saves an Employee object and its credentials in the database and returns its body.
     *
     * @param employee Employee object to be saved
     * @Results: (1) Saves the Employee object and its credentials saved in the database and returns its body.
     **/
    public Employee createEmployee(EmployeeLogin employeeLogin, Employee employee){
        employeeLoginRepository.save(employeeLogin);
        return employeeRepository.save(employee);
    }

}
