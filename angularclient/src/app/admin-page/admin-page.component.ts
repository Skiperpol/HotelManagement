import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {Employee} from "../model/employee/employee";
import {HotelService} from "../service/hotel.service";
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-admin-page',
  templateUrl: './admin-page.component.html',
  styleUrls: ['./admin-page.component.css']
})
export class AdminPageComponent implements OnInit{
  public employeeToFireId = "";
  public hotelBalance = 0;
  public currentDate = "";


  constructor(private router: Router, private hotelService: HotelService){
    if(this.router.getCurrentNavigation()?.extras.state == undefined)
      router.navigateByUrl('login');
  }
  public employeeId: any;
  ngOnInit(): void {
    this.employeeId = history.state;
    console.log(this.employeeId.id);
  }

  public hire(employee: Employee):void{
    this.hotelService.saveEmployee(employee).subscribe(
      response => {
        confirm("pomyślnie dodano pracownika do bazy danych")
      },
      error=>{
        let errorMessageJSON: string = JSON.stringify(error);
        let key = "error";
        let index = errorMessageJSON.indexOf(key);
        let errorMessage = errorMessageJSON.substring(index, errorMessageJSON.length);
        confirm(errorMessage);
      }
    )
  }


  onSubmitFireEmployee(form: NgForm){
    this.hotelService.deleteEmployee(this.employeeToFireId).subscribe(
      response => {
        confirm("pomyślnie usunięto pracownika z bazy danych")
      },
      error=>{
        let errorMessageJSON: string = JSON.stringify(error);
        let key = "error";
        let index = errorMessageJSON.indexOf(key);
        let errorMessage = errorMessageJSON.substring(index, errorMessageJSON.length);
        confirm(errorMessage);
      }
    )
  }

  public getHotelInfo(): void{
    this.hotelService.getCurrentDate().subscribe(
      (date: string) => {
        this.currentDate = date;
      },
      (error: any) => {
        let errorMessageJSON: string = JSON.stringify(error);
        let key = "error";
        let index = errorMessageJSON.indexOf(key);
        let errorMessage = errorMessageJSON.substring(index, errorMessageJSON.length);
        confirm(errorMessage);
      }
    )
    this.hotelService.getBalance().subscribe(
      (balance: number) => {
        this.hotelBalance = balance;
      }
      ,(error: any) => {
        let errorMessageJSON: string = JSON.stringify(error);
        let key = "error";
        let index = errorMessageJSON.indexOf(key);
        let errorMessage = errorMessageJSON.substring(index, errorMessageJSON.length);
        confirm(errorMessage);
      }
    )
    }

  }



