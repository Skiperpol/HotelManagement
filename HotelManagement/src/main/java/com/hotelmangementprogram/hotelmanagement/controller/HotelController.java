package com.hotelmangementprogram.hotelmanagement.controller;

import com.hotelmangementprogram.hotelmanagement.HotelManagementApplication;
import com.hotelmangementprogram.hotelmanagement.model.*;
import com.hotelmangementprogram.hotelmanagement.service.DataValidation;
import com.hotelmangementprogram.hotelmanagement.service.HotelService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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

    public Employee employeeForm(Long employeeId, String job, EmployeeDto employeeDto) {
        Employee employee = null;
        EmployeeLogin login = new EmployeeLogin(employeeId, employeeDto.getEmpLogin(), employeeDto.getEmpPassword());
        switch (job) {
            case "admin" -> employee = hotelService.createEmployee(
                    login,
                    new Admin(
                            employeeId,
                            employeeDto.getFirstName(),
                            employeeDto.getLastName(),
                            employeeDto.getPesel(),
                            employeeDto.getPhoneNumber(),
                            employeeDto.getEmailAddress(),
                            null,
                            employeeDto.getSalaryIfApplicable()
                    )
            );
            case "cleaner" -> employee = hotelService.createEmployee(
                    login,
                    new Cleaner(
                            employeeId,
                            employeeDto.getFirstName(),
                            employeeDto.getLastName(),
                            employeeDto.getPesel(),
                            employeeDto.getPhoneNumber(),
                            employeeDto.getEmailAddress(),
                            null,
                            null
                    )
            );
            case "cook" -> employee = hotelService.createEmployee(
                    login,
                    new Cook(
                            employeeId,
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
            case "receptionist" -> employee = hotelService.createEmployee(
                    login,
                    new Receptionist(
                            employeeId,
                            employeeDto.getFirstName(),
                            employeeDto.getLastName(),
                            employeeDto.getPesel(),
                            employeeDto.getPhoneNumber(),
                            employeeDto.getEmailAddress(),
                            null,
                            employeeDto.getSalaryIfApplicable()
                    )
            );
            case "waiter" -> employee = hotelService.createEmployee(
                    login,
                    new Waiter(
                            employeeId,
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
        return employee;
    }

    /**
     * checks sent employee login body (login & password).
     *
     * @RequestBody: EmployeeLoginDto
     * @Result: (1) logged succesfully, allowed access to page, sent body of encapsulated class object
     * <p></p>(2) unsuccesfull login attempt, access denied (to be implemented in front)
     * @return (1) error message with its source and if (1) body that consists of Job param and employee ID
     */
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody EmployeeLoginDto employeeLoginDto) {
        //login response body
        @Getter
        @AllArgsConstructor
        class LoginResponse{
            Job job;
            Long empID;
        }
        //actual method
        Optional<EmployeeLogin> employeeLogin = hotelService.login(
                employeeLoginDto.getEmpLogin(),
                employeeLoginDto.getEmpPassword()
        );
        if (employeeLogin.isEmpty()) { //empty Optional means invalid password or login
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Check login data");
        }
        //logged succesfully, returns ID and employee Job
        Long empID = employeeLogin.get().getEmployeeId();
        LoginResponse loginResponse = new LoginResponse(
                hotelService.getEmployee(empID).get().getJob(), //never null because at this point there's a much in database
                empID
        );
        return ResponseEntity.status(HttpStatus.OK).body(loginResponse);
    }

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
    public ResponseEntity<Object> createEmployee(@PathVariable String job, @RequestBody EmployeeDto employeeDto){
        //Checks the validity of data
        try{
            dataValidation.checkEmployeeData(employeeDto);
            dataValidation.checkPersonData(employeeDto);
        } catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Check input data");
        }
        //Data is valid, creates new record in the database
        Employee newEmployee = employeeForm(EMPTY_ID, job, employeeDto);
        newEmployee.setJob(Enum.valueOf(Job.class, job.toUpperCase())); //it is never null as a wrong job in request will not get past data validation
        return ResponseEntity.status(HttpStatus.CREATED).body(newEmployee);
    }


    @PostMapping("/employee/{job}/edit")
    public ResponseEntity<Object> editEmployee(Long employeeId, @PathVariable String job, @RequestBody EmployeeDto employeeDto) {
        //Checks the validity of data
        try{
            dataValidation.checkEmployeeData(employeeDto);
            dataValidation.checkPersonData(employeeDto);
        } catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Check input data");
        }
        //Data is valid, creates new record in the database
        Employee newEmployee = employeeForm(employeeId,job,employeeDto);
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
        try{
            dataValidation.checkRoomData(roomDto);
        }catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Check input data");
        }
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
        try{
            dataValidation.checkPersonData(guestDto);
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Check input data");
        }
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
    public ResponseEntity<HttpStatus> nextDay(){
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

    @PutMapping("/shutdown")
    public ResponseEntity<HttpStatus> shutdown() {
        hotelService.shutdown();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/order/complete")
    public ResponseEntity<Object> completeOrder(@RequestBody OrderDto orderDto, int orderId, Long employeeId){
        try {
            dataValidation.checkOrderData(orderDto);
        }catch (IllegalArgumentException | DateTimeException | NoSuchElementException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Check order ID");
        }
        hotelService.completeOrder(orderId,employeeId);
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
    @PutMapping("/room/clean")
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

    //Standard Getters to the database by ID and by all
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

    @GetMapping("/employee/get/all")
    public ResponseEntity<List<Employee>> getEmployees(){
        return ResponseEntity.status((HttpStatus.OK))
                .body(hotelService.getEmployees());
    }

    @GetMapping("/room/get/{roomId}")
    public ResponseEntity<Object> getRoom(@PathVariable Long roomId){
        try{
            dataValidation.checkRoomExists(roomId);
        }catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Room doesn't exist");
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(hotelService.getRoom(roomId));
    }

    @GetMapping("/room/get/all")
    public ResponseEntity<List<Room>> getRooms(){
        return ResponseEntity.status((HttpStatus.OK))
                .body(hotelService.getRooms());
    }

    @GetMapping("/room/get/vacant")
    public ResponseEntity<List<Room>> showVacantRooms(){
        return ResponseEntity.status((HttpStatus.OK))
                .body(hotelService.showVacantRooms());
    }

    @GetMapping("/room/get/uncleaned")
    public ResponseEntity<List<Room>> showUncleanedRooms(){
        return ResponseEntity.status((HttpStatus.OK))
                .body(hotelService.showUncleanedRooms());
    }

    @GetMapping("/guest/get/{guestId}")
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

    @GetMapping("/guest/get/all")
    public ResponseEntity<List<Guest>> getGuests(){
        return ResponseEntity.status((HttpStatus.OK))
                .body(hotelService.getGuests());
    }

    @GetMapping("/date/get")
    public ResponseEntity<LocalDate> getCurrentDate(){
        return ResponseEntity.status((HttpStatus.OK))
                .body(HotelManagementApplication.currentDate);
    }

    @GetMapping("/balance/get")
    public ResponseEntity<Double> getBalance(){
        return ResponseEntity.status((HttpStatus.OK))
                .body(HotelManagementApplication.balance);
    }

    @GetMapping("/order/get/all")
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