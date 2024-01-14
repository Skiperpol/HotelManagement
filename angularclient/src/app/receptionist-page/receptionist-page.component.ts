import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {HotelService} from "../service/hotel.service";
import {GuestAssignDto} from "../model/guestAssignDto/guestAssignDto";

import {resolve} from "@angular/compiler-cli";
import {Guest} from "../model/guest/guest";
import {Room} from "../model/room/room";
import { NgForm } from '@angular/forms';
import { GuestDto } from '../model/guestDto/guestDto';

@Component({
  selector: 'app-receptionist-page',
  templateUrl: './receptionist-page.component.html',
  styleUrls: ['./receptionist-page.component.css']
})
export class ReceptionistPageComponent implements OnInit{

  public vacantRooms: Room[] = [];
  public displayVacantRooms: boolean = false;
  public guestDto: GuestDto = {
    firstName: "",
    lastName: "",
    pesel: "",
    phoneNumber: "",
    emailAddress: "",
  }

  constructor(private router: Router, private hotelService: HotelService){
    if(this.router.getCurrentNavigation()?.extras.state == undefined)
      router.navigateByUrl('login');
  }
  public employeeId: any;
  ngOnInit(): void {
    this.employeeId = history.state;
    console.log(this.employeeId.id);
  }
  public assignRoom(guestAssignDto: GuestAssignDto):void{
    this.hotelService.assignRoom(guestAssignDto).subscribe(
      response => {
        confirm("Przypisano pokój");
      },
      error => {
        let errorMessageJSON: string = JSON.stringify(error);
        let key = "error";
        let index = errorMessageJSON.indexOf(key);
        let errorMessage = errorMessageJSON.substring(index, errorMessageJSON.length);
        confirm(errorMessage);
      }
    )
  }

  onSubmitAddGuest(form: NgForm){
    this.hotelService.saveGuest(this.guestDto).subscribe(
      (guest: Guest) => {
        confirm("Dodano gościa do bazy danych")
      },
      error => {
        let errorMessageJSON: string = JSON.stringify(error);
        let key = "error";
        let index = errorMessageJSON.indexOf(key);
        let errorMessage = errorMessageJSON.substring(index, errorMessageJSON.length);
        confirm(errorMessage);
      }
    )
  }
  public showVacantRooms():void{
    this.hotelService.getVacantRooms().subscribe(
      (rooms: Room[] ) => {
        console.log("pokazano")
        this.vacantRooms = rooms;
        this.displayVacantRooms = true;
      },
      (error) => {
        let errorMessageJSON: string = JSON.stringify(error);
        let key = "error";
        let index = errorMessageJSON.indexOf(key);
        let errorMessage = errorMessageJSON.substring(index, errorMessageJSON.length);
        confirm(errorMessage);
      }
    )
  }

}
