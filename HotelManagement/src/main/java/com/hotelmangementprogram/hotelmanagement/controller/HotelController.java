package com.hotelmangementprogram.hotelmanagement.controller;

import com.hotelmangementprogram.hotelmanagement.HotelManagementApplication;
import com.hotelmangementprogram.hotelmanagement.model.*;
import com.hotelmangementprogram.hotelmanagement.service.DataValidation;
import com.hotelmangementprogram.hotelmanagement.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")  //For frontend angular later on
@RequiredArgsConstructor
@RequestMapping("/hotel")
public class HotelController {
    private final HotelService hotelService;
    private final DataValidation dataValidation;
    private static final Long EMPTY_ID = null;
    private static final String EMPTY_IDS = "";

    //------------------------------------ POST REQUESTS ---------------------------------------------------

    /**
     * Creates new Employee and EmployeeLogin record in the database. Data given checked by dataValidation service.
     *
     * @param job Job of the employee
     * @RequestBody: EmployeeDto
     * @Results: (1) Valid data, new entry in the database created, Http status 201 (CREATED),
     * returns full new Employee body.
     * <p></p>(2) Invalid data or part of it, Http status 400 (BAD_REQUEST),
     * returns error message with its source specified.
     * @URL: POST http://localhost:8080/hotel/employee/{job}/add
     */
    @PostMapping("/employee/{job}/add")
    public ResponseEntity<Object> createEmployee(@PathVariable String job, @RequestBody EmployeeDto employeeDto) {
        //Checks the validity of data
        if (!dataValidation.checkEmployeeData())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Check input data");
        //Data is valid, creates new record in the database
        Employee newEmployee = null;
        EmployeeLogin newLogin = new EmployeeLogin(EMPTY_ID, employeeDto.getEmpLogin(), employeeDto.getEmpPassword());
        switch (job){
            case  "admin" ->
                newEmployee = hotelService.createEmployee(
                        newLogin,
                        new Admin(
                                EMPTY_ID,
                                employeeDto.getFirstName(),
                                employeeDto.getLastName(),
                                employeeDto.getPesel(),
                                employeeDto.getPhoneNumber(),
                                employeeDto.getEmailAddress(),
                                null,
                                employeeDto.getSalaryIfApplicable()
                        )
                );
            case  "cleaner" ->
                    newEmployee = hotelService.createEmployee(
                            newLogin,
                            new Cleaner(
                                    EMPTY_ID,
                                    employeeDto.getFirstName(),
                                    employeeDto.getLastName(),
                                    employeeDto.getPesel(),
                                    employeeDto.getPhoneNumber(),
                                    employeeDto.getEmailAddress(),
                                    null,
                                    (float) 0.0

                            )
                    );
            case  "cook" ->
                    newEmployee = hotelService.createEmployee(
                            newLogin,
                            new Cook(
                                    EMPTY_ID,
                                    employeeDto.getFirstName(),
                                    employeeDto.getLastName(),
                                    employeeDto.getPesel(),
                                    employeeDto.getPhoneNumber(),
                                    employeeDto.getEmailAddress(),
                                    null,
                                    null,
                                    employeeDto.getSalaryIfApplicable()
                            )
                    );
            case  "receptionist" ->
                    newEmployee = hotelService.createEmployee(
                            newLogin,
                            new Receptionist(
                                    EMPTY_ID,
                                    employeeDto.getFirstName(),
                                    employeeDto.getLastName(),
                                    employeeDto.getPesel(),
                                    employeeDto.getPhoneNumber(),
                                    employeeDto.getEmailAddress(),
                                    null,
                                    employeeDto.getSalaryIfApplicable()
                            )
                    );
            case  "waiter" ->
                    newEmployee = hotelService.createEmployee(
                            newLogin,
                            new Waiter(
                                    EMPTY_ID,
                                    employeeDto.getFirstName(),
                                    employeeDto.getLastName(),
                                    employeeDto.getPesel(),
                                    employeeDto.getPhoneNumber(),
                                    employeeDto.getEmailAddress(),
                                    null,
                                    null
                            )
                    );
        }
        newEmployee.setJob(Enum.valueOf(Job.class, job.toUpperCase())); //it is never null as a wrong job in request will not get past data validation
        return ResponseEntity.status(HttpStatus.CREATED).body(newEmployee);
    }

    /**
     * Creates new Room record in the database. Data given checked by dataValidation service.
     *
     * @RequestBody: RoomDto
     * @Results: (1) Valid data, new entry in the database created, Http status 201 (CREATED),
     * returns full new Room body.
     * <p></p>(2) Invalid data or part of it, Http status 400 (BAD_REQUEST),
     * returns error message with its source specified.
     * @URL: POST http://localhost:8080/hotel/room/add
     */
    @PostMapping("/room/add")
    public ResponseEntity<Object> createRoom(@RequestBody RoomDto roomDto){
        if (!dataValidation.checkRoomData())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Check input data");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(hotelService.createRoom(new Room(
                        EMPTY_ID,
                        roomDto.getRoomNumber(),
                        roomDto.getRoomPrice(),
                        true,
                        true,
                        Enum.valueOf(RoomType.class, roomDto.getRoomType().toUpperCase()),
                        EMPTY_IDS
                )));
    }

    /**
     * Creates new Guest record in the database. Data given checked by dataValidation service.
     *
     * @RequestBody: guestDto
     * @Results: (1) Valid data, new entry in the database created, Http status 201 (CREATED),
     * returns full new Guest body.
     * <p></p>(2) Invalid data or part of it, Http status 400 (BAD_REQUEST),
     * returns error message with its source specified.
     * @URL: POST http://localhost:8080/hotel/guest/add
     */
    @PostMapping("/guest/add")
    public ResponseEntity<Object> createGuest(@RequestBody GuestDto guestDto){
        if (!dataValidation.checkGuestData())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Check input data");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(hotelService.createGuest(new Guest(
                        EMPTY_ID,
                        guestDto.getFirstName(),
                        guestDto.getLastName(),
                        guestDto.getPesel(),
                        guestDto.getPhoneNumber(),
                        guestDto.getEmailAddress(),
                        null,
                        null,
                        null,
                        null
                )));
    }

    /**
     * Creates new Employee and EmployeeLogin record in the database. Data given checked by dataValidation service.
     *
     * @RequestBody: OrderDto data of the pending order
     * @Results: (1) Database and run-time pending order list updated, returns Http status 202 (ACCEPTED)
     * <p> (2) Invalid data, returns Http status 400 (BAD_REQUEST)
     * @URL: POST http://localhost:8080/hotel/waiter/accept
     */
    @PostMapping("/waiter/accept")
    public ResponseEntity<Object> acceptOrder(@RequestBody OrderDto orderDto){
        try{
            dataValidation.checkOrderData(orderDto);
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Check order data");
        }
        hotelService.acceptOrder(orderDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    /**
     * Method that transitions the HotelManagement system to the next day.
     */
    @PostMapping("/nextDay")
    public ResponseEntity<Object> nextDay(){
        hotelService.nextDay();
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    //------------------------------------ PUT REQUESTS ---------------------------------------------------
    /**
     * Creates new Employee and EmployeeLogin record in the database. Data given checked by dataValidation service.
     *
     * @RequestBody: guestAssignDto data of the assignment
     * @Results: (1) Room and Guest records in the database updated, returns Http status 200 (OK)
     * <p> (2) Invalid data, returns Http status 400 (BAD_REQUEST)
     * @URL: PUT http://localhost:8080/receptionist/assign
     */
    @PutMapping("/receptionist/assign")
    public ResponseEntity<Object> assignRoom(@RequestBody GuestAssignDto guestAssignDto){
        try {
            dataValidation.checkAssignRoomData(guestAssignDto);
        }catch (IllegalArgumentException | DateTimeException | NoSuchElementException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Check guest Ids, room number, state and dates of the stay");
        }
        hotelService.assignRoom(guestAssignDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/order/complete")
    public ResponseEntity<Object> completeOrder(@RequestBody OrderDto orderDto, int orderId, Long employeeId){
        try {
            dataValidation.checkOrderData(orderDto);
        }catch (IllegalArgumentException | DateTimeException | NoSuchElementException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Check order ID");
        }
        hotelService.completeOrder(orderId, employeeId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * Sets the parameter of the specified room to 'true' and adds a certain value
     * to the commission field of the specified cleaner (based on the roomType).
     *
     * @RequestBody roomId, cleanerId - kinda self-explanatory
     * @Results (1) Http status 400 (BAD REQUEST) when the roomId is incorrect
     * <p>(2) Http status 400 (BAD REQUEST) when the cleanerId is incorrect </p>
     * <p>(3) Http status 200 (OK) when everything went according to then plan d-_-b</p>
     * @URL http://localhost:8080/hotel/room/clean
     * @return
     */
    @PutMapping("room/clean")
    public ResponseEntity<Object> cleanRoom(@RequestBody Long roomId, Long cleanerId){
        try {
            dataValidation.checkRoomExists(roomId);
        }catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No such room exists");
        }

        try {
            dataValidation.checkEmployeeExists(cleanerId);
        }catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No such cleaner exists");
        }

        hotelService.cleanRoom(roomId, cleanerId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    //------------------------------------ GET REQUESTS ---------------------------------------------------

    //Standard Getters to the database by Id and by all
    @GetMapping("/employee/get/{employeeId}")
    public ResponseEntity<Object> getEmployee(@PathVariable Long employeeId){
        try{
            dataValidation.checkEmployeeExists(employeeId);
        }catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Employee doesn't exist");
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(hotelService.getEmployee(employeeId));
    }

    @GetMapping("employee/get/all")
    public ResponseEntity<List<Employee>> getEmployees(){
        return ResponseEntity.status((HttpStatus.OK))
                .body(hotelService.getEmployees());
    }

    @GetMapping("room/get/{roomId}")
    public ResponseEntity<Object> getRoom(@PathVariable Long roomId){
        if(!dataValidation.checkRoomExists(roomId)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Room doesn't exist");
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(hotelService.getRoom(roomId));
    }

    @GetMapping("room/get/all")
    public ResponseEntity<List<Room>> getRooms(){
        return ResponseEntity.status((HttpStatus.OK))
                .body(hotelService.getRooms());
    }

    @GetMapping("guest/get/{guestId}")
    public ResponseEntity<Object> getGuest(@PathVariable Long guestId){
        try{
            dataValidation.checkGuestExists(guestId);
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Guest doesn't exist");
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(hotelService.getGuest(guestId));
    }

    @GetMapping("guest/get/all")
    public ResponseEntity<List<Guest>> getGuests(){
        return ResponseEntity.status((HttpStatus.OK))
                .body(hotelService.getGuests());
    }

    @GetMapping("date/get")
    public ResponseEntity<LocalDate> getCurrentDate(){
        return ResponseEntity.status((HttpStatus.OK))
                .body(HotelManagementApplication.currentDate);
    }

    @GetMapping("balance/get")
    public ResponseEntity<Double> getBalance(){
        return ResponseEntity.status((HttpStatus.OK))
                .body(HotelManagementApplication.balance);
    }

    @GetMapping("order/get/all")
    public ResponseEntity<ArrayList<Menu>> showOrders(){
        return ResponseEntity.status((HttpStatus.OK))
                .body(hotelService.showOrders());
    }

    //----------------------------------- DELETE REQUESTS ---------------------------------------------------
    @DeleteMapping("/employee/{employeeId}/delete")
    public ResponseEntity<Object> deleteEmployee(@PathVariable Long employeeId){
        try{
            dataValidation.checkEmployeeExists(employeeId);
        }catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No such employee exists");
        }
        hotelService.deleteEmployee(employeeId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}