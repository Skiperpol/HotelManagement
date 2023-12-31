package com.hotelmangementprogram.hotelmanagement.service;

import com.hotelmangementprogram.hotelmanagement.HotelManagementApplication;
import com.hotelmangementprogram.hotelmanagement.model.*;
import com.hotelmangementprogram.hotelmanagement.repository.*;
import lombok.RequiredArgsConstructor;
import net.sf.jsqlparser.statement.DeclareType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jackson.JacksonProperties;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
     * @Results: (1) Saves the Employee object and its credentials in the database and returns its body.
     **/
    public Employee createEmployee(EmployeeLogin employeeLogin, Employee employee){
        employeeLoginRepository.save(employeeLogin);
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee( Employee employee){
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

    /**
     * Method saves a Room object in the database and returns its body.
     *
     * @param room Room object to be saved
     * @Results: (1) Saves the Room object in the database and returns its body.
     **/
    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }

    /**
     * Method saves a Guest object in the database and returns its body.
     *
     * @param guest Guest object to be saved
     * @Results: (1) Saves the Guest object in the database and returns its body.
     **/
    public Guest createGuest(Guest guest) {
        return guestRepository.save(guest);
    }
    /**
     * Method adds order to the pending orders list and updates the specified guest record
     * in the database.
     *
     * @param orderDto Dto carrying Id of the ordering guest and Ids of ordered dishes.
     * @Results: (1) Updates the run-time pending orders list and the guest record in the database.
     **/
    public void acceptOrder(OrderDto orderDto) {
        Optional<Guest> guestOptional = getGuest(Long.parseLong(orderDto.getGuestId()));
        assert guestOptional.isPresent();
        Guest guest = guestOptional.get();
        List<Menu> menu = getMenu();
        for(String id: orderDto.getDishIds().split(",")){
            Menu dish = menu.get(Integer.parseInt(id)-1);
            HotelManagementApplication.pendingOrders
                    .add(dish);
            guest.addAdditionalCharges(dish.getDishPrice());
        }
        createGuest(guest);
    }
    /**
     * Method updates room and guest records in the database, assigning guests to rooms
     *
     * @param guestAssignDto Dto carrying the assignment data
     * @Results: (1) Updates records in the database
     **/
    public void assignRoom(GuestAssignDto guestAssignDto) {
        Room newRoom = roomRepository.findAll().stream()
                .filter(room -> room.getRoomNumber().equals(guestAssignDto.getRoomNumber()))
                .findAny().get(); //Always is present
        newRoom.setRoomIsEmpty(false);
        newRoom.setGuestIds(guestAssignDto.getGuestIds());
        for(String guestId: guestAssignDto.getGuestIds().split(",")){
            Guest currentGuest = guestRepository.findById(Long.parseLong(guestId)).get(); //Always present
            currentGuest.setRoomNumber(guestAssignDto.getRoomNumber());
            currentGuest.setCheckInDate(LocalDate.parse(guestAssignDto.getCheckInDate()));
            currentGuest.setCheckOutDate(LocalDate.parse(guestAssignDto.getCheckOutDate()));
            createGuest(currentGuest);
        }
    }

    public void deleteEmployee(Long employeeId) {
        employeeRepository.deleteById(employeeId);
        employeeLoginRepository.deleteById(employeeId);
    }

    public void completeOrder(int orderId, Long employeeId){
        Cook cook = (Cook) getEmployee(employeeId).get(); //always present
        updateEmployee(cook.completeOrder(orderId,employeeId));
    }


    /**
     * Method that transitions the HotelManagement system to the next day.
     */
    public void nextDay(){
    }

    public ArrayList<Menu> showOrders(){
        return Cook.showOrders();
    }


    public void cleanRoom(Long roomId, Long cleanerId){
        Room room = getRoom(roomId).get();
        Cleaner cleaner = (Cleaner) (getEmployee(cleanerId).get());
        EmployeeLogin cleanerLogin = getEmployeeLogin(cleanerId).get();
        Cleaner.cleanRoom(room, cleaner);
        createRoom(room);
        createEmployee(cleanerLogin, cleaner);

    }
}
