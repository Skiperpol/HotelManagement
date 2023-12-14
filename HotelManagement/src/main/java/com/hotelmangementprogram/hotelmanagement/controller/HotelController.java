package com.hotelmangementprogram.hotelmanagement.controller;

import com.hotelmangementprogram.hotelmanagement.model.*;
import com.hotelmangementprogram.hotelmanagement.service.DataValidation;
import com.hotelmangementprogram.hotelmanagement.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
     * @RequestBody: EmployeeDto
     * @Results: (1) Valid data, new entry in the database created, Http status 201 (CREATED),
     * returns full new Employee body.
     * <p></p>(2) Invalid data or part of it, Http status 400 (BAD_REQUEST),
     * returns error message with its source specified.
     * @URL: POST http://localhost:8080/hotel/employee/add
     */
    @PostMapping("/employee/add")
    public ResponseEntity<Object> createDeveloper(@RequestBody EmployeeDto employeeDto) {
        //Checks the validity of data
        if(!dataValidation.checkEmployeeData())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("To be implemented");
        else {
            //Data is valid, creates new record in the database
            //To be changed later on, test method, works for cleaner only
            Employee newEmployee = hotelService.createEmployee(
                    new EmployeeLogin(
                            EMPTY_ID,
                            employeeDto.getEmpLogin(),
                            employeeDto.getEmpPassword()
                    ),
                    new Cleaner(
                            EMPTY_ID,
                            employeeDto.getFirstName(),
                            employeeDto.getLastName(),
                            employeeDto.getPesel(),
                            employeeDto.getPhoneNumber(),
                            employeeDto.getEmailAddress(),
                            Enum.valueOf(Job.class, employeeDto.getJob().toUpperCase()),
                            employeeDto.getCommission()
                    )
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(newEmployee);
        }
    }


}
