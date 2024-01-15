import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {HotelService} from "../service/hotel.service";
import {GuestAssignDto} from "../model/guestAssignDto/guestAssignDto";

import {resolve} from "@angular/compiler-cli";
import {Guest} from "../model/guest/guest";
import {Room} from "../model/room/room";

@Component({
  selector: 'app-receptionist-page',
  templateUrl: './receptionist-page.component.html',
  styleUrls: ['./receptionist-page.component.css']
})
export class ReceptionistPageComponent implements OnInit{

  public vacantRooms: Room[] = [];
  public allRooms: Room[] = [];
  public displayVacantRooms: boolean = false;

  constructor(private router: Router, private hotelService: HotelService){
    if(this.router.getCurrentNavigation()?.extras.state == undefined)
      router.navigateByUrl('login');
  }
  public employeeId: any;
  ngOnInit(): void {
    this.employeeId = history.state;
    console.log(this.employeeId.id);
    this.hotelService.getRooms().subscribe(
      (response: Room[]) => {
        this.allRooms = response;
      }
    );
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

  public registerNewGuest(guest: Guest):void{
    this.hotelService.saveGuest(guest).subscribe(
      response => {
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

  public showPersonalData(): void {
    this.router.navigateByUrl('/personal-data', {state: {id: this.employeeId.id}});
  }

}
