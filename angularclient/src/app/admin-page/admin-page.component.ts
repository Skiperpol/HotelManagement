import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {Employee} from "../model/employee/employee";
import {HotelService} from "../service/hotel.service";
import { HttpStatusCode } from '@angular/common/http';
import { EmployeeDto } from '../model/employeeDto/employeeDto';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-admin-page',
  templateUrl: './admin-page.component.html',
  styleUrls: ['./admin-page.component.css']
})
export class AdminPageComponent implements OnInit{
  constructor(private router: Router, private hotelService: HotelService){
    if(this.router.getCurrentNavigation()?.extras.state == undefined)
      router.navigateByUrl('login');
  }
  public employeeId: any;
  public employees: Employee[] = [];
  public jobDto: string = "";
  public employee: EmployeeDto = {
    empLogin: "",
    empPassword: "",
    firstName: "",
    lastName: "",
    pesel: "",
    phoneNumber: "",
    emailAddress: "",
    salaryIfApplicable:0
  }
  ngOnInit(): void {
    this.employeeId = history.state;
    console.log(this.employeeId.id);
    this.hotelService.getEmployees().subscribe(
      (response: Employee[]) => {
        this.employees = response;
        console.log(this.employees);
      }
    );
  }

  public onSubmit(createForm: NgForm):void{
    this.hotelService.saveEmployee(this.employee, this.jobDto).subscribe(
      (response: Employee) => {
        confirm("Added " +  this.jobDto + " to database. ID: " + response?.personId);
      },
      error=>{
        let errorMessageJSON: string = JSON.stringify(error);
        let key = "error";
        let index = errorMessageJSON.indexOf(key);
        let errorMessage = errorMessageJSON.substring(index+8, errorMessageJSON.length-2);
        confirm(errorMessage);
      }
    )
  }
  public fire(employeeId: string):void{
    this.hotelService.deleteEmployee(employeeId).subscribe(
      response => {
        confirm("pomyślnie usunięto pracownika z bazy danych")
      },
      error=>{
        let errorMessageJSON: string = JSON.stringify(error);
        let key = "error";
        let index = errorMessageJSON.indexOf(key);
        let errorMessage = errorMessageJSON.substring(index+8, errorMessageJSON.length-2);
        confirm(errorMessage);
      }
    )
  }

  public showPersonalData(): void {
    this.router.navigateByUrl('/personal-data', {state: {id: this.employeeId.id}});
  }
}
