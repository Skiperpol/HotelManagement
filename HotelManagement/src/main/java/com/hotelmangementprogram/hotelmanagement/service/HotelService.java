package com.hotelmangementprogram.hotelmanagement.service;

import com.hotelmangementprogram.hotelmanagement.model.*;
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
    /**
     * Method returns all Employee objects from the database as ArrayList
     *
     * @Results: (1) Returns the list of all Employee objects from the database, the list may be null.
     **/
    public List<Employee> getEmployees(){
        return employeeRepository.findAll();
    }
    /**
     * Method returns an Employee object from the database as Optional type
     *
     * @param employeeId Id of the employee that is to be fetched from the database
     * @Results: (1) Returns the employee object from the database as Optional, may be null.
     **/
    public Optional<Employee> getEmployee(Long employeeId){
        return employeeRepository.findById(employeeId);
    }
    /**
     * Method returns all Guest objects from the database as ArrayList
     *
     * @Results: (1) Returns the list of all Guest objects from the database, the list may be null.
     **/
    public List<Guest> getGuests(){
        return guestRepository.findAll();
    }
    /**
     * Method returns a Guest object from the database as Optional type
     *
     * @param guestId Id of the guest that is to be fetched from the database
     * @Results: (1) Returns the guest object from the database as Optional, may be null.
     **/
    public Optional<Guest> getGuest(Long guestId){
        return guestRepository.findById(guestId);
    }
    /**
     * Method returns all Room objects from the database as ArrayList
     *
     * @Results: (1) Returns the list of all Room objects from the database, the list may be null.
     **/
    public List<Room> getRooms(){
        return roomRepository.findAll();
    }
    /**
     * Method returns a Room object from the database as Optional type
     *
     * @param roomId Id of the room that is to be fetched from the database
     * @Results: (1) Returns the Room object from the database as Optional, may be null.
     **/
    public Optional<Room> getRoom(Long roomId){
        return roomRepository.findById(roomId);
    }
    /**
     * Method returns the Menu from the database as ArrayList of dishes
     *
     * @Results: (1) Returns the list of all Dish pseud-objects from the database, the list may, but should not, be null.
     **/
    public List<Menu> getMenu(){
        return menuRepository.findAll();
    }
    /**
     * Method returns all EmployeeLogin objects from the database as ArrayList
     *
     * @Results: (1) Returns the list of all logins from the database, the list may be null.
     **/
    public List<EmployeeLogin> getEmployeeLogins(){
        return employeeLoginRepository.findAll();
    }
    /**
     * Method returns a Guest object from the database as Optional type
     *
     * @param employeeId Id of the employee, whose login we want to fetch from the database
     * @Results: (1) Returns the login from the database as Optional, may be null.
     **/
    public Optional<EmployeeLogin> getEmployeeLogin(Long employeeId){
        return employeeLoginRepository.findById(employeeId);
    }
}
