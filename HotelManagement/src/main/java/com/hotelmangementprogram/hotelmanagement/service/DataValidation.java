package com.hotelmangementprogram.hotelmanagement.service;

import com.hotelmangementprogram.hotelmanagement.HotelManagementApplication;
import com.hotelmangementprogram.hotelmanagement.model.*;
import com.hotelmangementprogram.hotelmanagement.repository.*;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.PropertySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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


    public void checkEmployeeData(EmployeeDto employeeDto) {
        //String values
        if (employeeDto.getEmpLogin().isBlank() || employeeDto.getEmpPassword().isBlank()
                || employeeDto.getFirstName().isBlank() || employeeDto.getLastName().isBlank())
            throw new IllegalArgumentException();
        List<EmployeeLogin> tmpList = employeeLoginRepository.findAll().stream()
                .filter(login -> {
                    if (login.getEmpLogin().equals(employeeDto.getEmpLogin()))
                        throw new IllegalArgumentException();
                    return true;
                }).toList();
        tmpList = null;
        //salary
        if (employeeDto.getSalaryIfApplicable() != null)
            if (employeeDto.getSalaryIfApplicable() < 0)
                throw new IllegalArgumentException();
    }

    public void checkPersonData(Object dto) throws IllegalArgumentException{
        GuestDto employeeDto;
        if(dto instanceof EmployeeDto){
           employeeDto = new GuestDto(
                   ((EmployeeDto) dto).getFirstName(),
                   ((EmployeeDto) dto).getLastName(),
                   ((EmployeeDto) dto).getPesel(),
                   ((EmployeeDto) dto).getPhoneNumber(),
                   ((EmployeeDto) dto).getEmailAddress()
           );
        }else
            employeeDto = (GuestDto) dto;
        //PESEL
        String pesel = employeeDto.getPesel();
        if (pesel.length() != 11 || pesel.split("[a-zA-z\\D]+").length > 1)
            throw new IllegalArgumentException();
        //RRMMDDPPPPK
        byte MM = Byte.parseByte(pesel.substring(2, 4));
        if (!((MM >= 1 && MM <= 12) || (MM >= 21 && MM <= 32)))
            throw new IllegalArgumentException();
        byte DD = Byte.parseByte(pesel.substring(4, 6));
        if (DD > 31 || DD == 0)
            throw new IllegalArgumentException();
        int[] peselArray = new int[10];
        for (int i = 0; i < 10; i++)
            peselArray[i] += pesel.charAt(i) - 48;
        int value = 1, sum = 0;
        for (int digit : peselArray) {
            sum += (digit * value) % 10;
            value += 2;
            if (value == 5)
                value = 7;
            if (value > 9)
                value = 1;
        }
        if ((10 - sum % 10) != (int) pesel.charAt(10) - 48)
            throw new IllegalArgumentException();
        //phonenumber
        if (employeeDto.getPhoneNumber().length() != 9
                || employeeDto.getPhoneNumber().split("[a-zA-z\\D]+")[0].length() != 9)
            throw new IllegalArgumentException();
        //email
        Matcher m = Pattern.compile("^(?!\\.)[a-zA-Z0-9.]+@[a-zA-Z0-9]+\\.[a-zA-Z]{2,}$")
                .matcher(employeeDto.getEmailAddress());
        String s = null;
        while (m.find()) {
            s = m.group(0);
            if (!s.equals(employeeDto.getEmailAddress()))
                throw new IllegalArgumentException();
        }
        if (s == null)
            throw new IllegalArgumentException();
    }

    public void checkEmployeeExists(Long employeeId) throws NoSuchElementException {
        if (employeeRepository.findById(employeeId).isEmpty())
            throw new NoSuchElementException();
    }

    public void checkRoomExists(Long roomId) throws NoSuchElementException {
        if (roomRepository.findById(roomId).isEmpty())
            throw new NoSuchElementException();
    }

    public void checkRoomData(RoomDto roomDto) throws IllegalArgumentException{
        if(roomDto.getRoomPrice() < 0 || roomDto.getRoomNumber() < 0)
            throw new IllegalArgumentException();
        Enum.valueOf(RoomType.class, roomDto.getRoomType().toUpperCase());
    }

    public void checkGuestExists(Long guestId) throws NoSuchElementException {
        if (!guestRepository.existsById(guestId)) throw new NoSuchElementException();
    }

    /**
     * Checks the validity of the given order
     *
     * @RequestBody: OrderDto data of the pending order
     * @Results: (1) Valid data, does not throw
     * <p> (2) Invalid data, throws exception
     */
    public void checkOrderData(OrderDto orderDto) throws IllegalArgumentException {
        checkGuestExists(Long.parseLong(orderDto.getGuestId()));
        if (Objects.equals(orderDto.getDishIds(), "") ||
                Arrays.stream(orderDto.getDishIds().split(","))
                        .mapToInt(currentDishId -> {
                            int id = Integer.parseInt(currentDishId);
                            if (id <= 0) throw new IllegalArgumentException();
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
    public void checkDate(String date) throws DateTimeException {
        //Checks if the String can be formatted into a date
        LocalDate newDate = LocalDate.parse(date, dateTimeFormatter);
        //Checks for the exception where the day number is bigger than the maximum day number for a month by
        //one, for example, 2023-11-31, where the maximum value is 30.
        String tmp = String.valueOf(date.charAt(8)) + date.charAt(9);
        if (tmp.charAt(0) == '0')
            tmp = String.valueOf(tmp.charAt(1));
        if (!String.valueOf(newDate.getDayOfMonth()).equals(tmp))
            throw new DateTimeException(null);
    }

    /**
     * This method checks the validity of assign room Dto
     *
     * @param guestAssignDto Dto with required data.
     * @Results: (1) Data valid, does not throw.
     * <p></p>(2) Data invalid, throws exception.
     */
    public void checkAssignRoomData(GuestAssignDto guestAssignDto) throws DateTimeException, IllegalArgumentException, NoSuchElementException {
        //Checks guest Ids
        for (String guestId : guestAssignDto.getGuestIds().split(","))
            checkGuestExists(Long.parseLong(guestId));
        //Checks Room number and if it's vacant
        Optional<Room> roomChecked = roomRepository.findAll().stream()
                .filter(room -> room.getRoomNumber().equals(guestAssignDto.getRoomNumber()))
                .findAny();
        if (roomChecked.isEmpty() || !roomChecked.get().isRoomIsEmpty())
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
