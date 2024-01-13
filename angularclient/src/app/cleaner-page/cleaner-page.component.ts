import { Component, OnInit } from '@angular/core';
import { HotelService } from '../service/hotel.service';
import { Room } from '../model/room/room';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { HttpStatusCode } from '@angular/common/http';

@Component({
  selector: 'app-cleaner-page',
  templateUrl: './cleaner-page.component.html',
  styleUrls: ['./cleaner-page.component.css']
})
export class CleanerPageComponent implements OnInit{
  public errorMessage = "";
  public rooms: Room[] = [];
  public displayUncleanedRooms: boolean = false;
  public roomToCleanId: number = 0;
    
  constructor(private hotelService: HotelService, private router: Router){
    if(this.router.getCurrentNavigation()?.extras.state == undefined)
      router.navigateByUrl('login');
  }

  public employeeId = {id: ""};
  ngOnInit(): void {
    this.employeeId = history.state;
    console.log(this.employeeId.id);
  }

  // 👩‍🏭👩‍🏭
  public showPersonalData(): void {
    this.router.navigateByUrl("/personal-data", {state: {id: this.employeeId.id}});
  }


  // 🤢🤮
  public showUncleanedRooms(): void {
    this.hotelService.getUncleanedRooms().subscribe(
      (rooms: Room[] ) => {
        this.rooms = rooms;
        this.displayUncleanedRooms = true;
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

  // 🧹🧹
  onSubmit(form: NgForm){
    this.hotelService.cleanRoom(Number(this.employeeId.id), this.roomToCleanId).subscribe(
      (response: HttpStatusCode.Ok) => {
        confirm('Posprzatano pokoj!') // Nie dziala ten confirm
      }, 
      (error) => {
        let errorMessageJSON: string = JSON.stringify(error);
        let key = "error";
        let index = errorMessageJSON.indexOf(key);
        this.errorMessage = errorMessageJSON.substring(index, errorMessageJSON.length);
        confirm(this.errorMessage);
      }
      )
  }

}
