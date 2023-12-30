package com.hotelmangementprogram.hotelmanagement.service;

import com.hotelmangementprogram.hotelmanagement.HotelManagementApplication;
import com.hotelmangementprogram.hotelmanagement.model.EmployeeDto;
import com.hotelmangementprogram.hotelmanagement.model.GuestAssignDto;
import com.hotelmangementprogram.hotelmanagement.model.OrderDto;
import com.hotelmangementprogram.hotelmanagement.model.Room;
import com.hotelmangementprogram.hotelmanagement.repository.*;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.PropertySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@RequiredArgsConstructor
public class DataValidation {
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
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    public void checkEmployeeData(EmployeeDto employeeDto) throws IllegalArgumentException{
        //To be implemented, test method
    }

    public void checkEmployeeExists(Long employeeId) throws NoSuchElementException {
        if(employeeRepository.findById(employeeId).isEmpty())
            throw new NoSuchElementException();
    }

    public boolean checkRoomExists(Long roomId) { return  true;
    }

    public boolean checkRoomData() {
        return true;
    }

    public boolean checkGuestData() {
        return true;
    }

    public void checkGuestExists(Long guestId) throws NoSuchElementException{
        if(!guestRepository.existsById(guestId)) throw new NoSuchElementException();
    }

    /**
     * Checks the validity of the given order
     *
     * @RequestBody: OrderDto data of the pending order
     * @Results: (1) Valid data, does not throw
     * <p> (2) Invalid data, throws exception
     */
    public void checkOrderData(OrderDto orderDto) throws IllegalArgumentException{
        checkGuestExists(Long.parseLong(orderDto.getGuestId()));
        if(Objects.equals(orderDto.getDishIds(), "") ||
                Arrays.stream(orderDto.getDishIds().split(","))
                .mapToInt(currentDishId -> {
                    int id = Integer.parseInt(currentDishId);
                    if(id <= 0) throw new IllegalArgumentException();
                    return id;
                }).max().getAsInt() > menuRepository.count())
            throw new IllegalArgumentException();

    }
    /**
     * This method checks the validity of the user-input date format.
     * The accepted format is YYYY-MM-DD.
     *
     * @param date String supposed to be a valid date when parsed.
     * @Results: (1) String is a valid date, does not throw.
     * <p></p>(2) String is not a valid date, throws exception.
     */
    public void checkDate(String date) throws DateTimeException{
        //Checks if the String can be formatted into a date
        LocalDate newDate = LocalDate.parse(date, dateTimeFormatter);
        //Checks for the exception where the day number is bigger than the maximum day number for a month by
        //one, for example, 2023-11-31, where the maximum value is 30.
        String tmp = String.valueOf(date.charAt(8)) + date.charAt(9);
        if(tmp.charAt(0) == '0')
            tmp = String.valueOf(tmp.charAt(1));
        if(!String.valueOf(newDate.getDayOfMonth()).equals(tmp))
            throw new DateTimeException(null);
    }
    /**
     * This method checks the validity of assign room Dto
     *
     * @param guestAssignDto Dto with required data.
     * @Results: (1) Data valid, does not throw.
     * <p></p>(2) Data invalid, throws exception.
     */
    public void checkAssignRoomData(GuestAssignDto guestAssignDto) throws DateTimeException,IllegalArgumentException, NoSuchElementException{
        //Checks guest Ids
        for(String guestId: guestAssignDto.getGuestIds().split(","))
            checkGuestExists(Long.parseLong(guestId));
        //Checks Room number and if it's vacant
        Optional<Room> roomChecked = roomRepository.findAll().stream()
                        .filter(room -> room.getRoomNumber().equals(guestAssignDto.getRoomNumber()))
                                .findAny();
        if(roomChecked.isEmpty() || !roomChecked.get().isRoomIsEmpty())
            throw new IllegalArgumentException();
        //Checks dates
        checkDate(guestAssignDto.getCheckInDate());
        checkDate(guestAssignDto.getCheckOutDate());
        LocalDate dateIn = LocalDate.parse(guestAssignDto.getCheckInDate());
        LocalDate dateOut = LocalDate.parse(guestAssignDto.getCheckOutDate());
        //Checks the date order
        if (!dateOut.isAfter(dateIn) ||
                !(dateIn.isAfter(HotelManagementApplication.currentDate)
                || dateIn.isEqual(HotelManagementApplication.currentDate)))
                    throw new DateTimeException(null);
    }
}
