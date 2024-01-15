import { Component, OnInit } from '@angular/core';
import { HotelService } from '../service/hotel.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit{
  constructor(private hotelService: HotelService){}
  public currentDate: string = "";
  ngOnInit(): void {
    this.hotelService.getCurrentDate().subscribe(
      (response: string) => {
        this.currentDate = response;
      }
    )
  }
}
