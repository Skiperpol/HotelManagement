import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HotelService } from '../service/hotel.service';
import { OrderDto } from '../model/orderDto/orderDto';
import { NgForm } from '@angular/forms';
import { HttpClient, HttpErrorResponse, HttpStatusCode } from '@angular/common/http';
import { Menu } from '../model/menu/menu';

@Component({
  selector: 'app-waiter-page',
  templateUrl: './waiter-page.component.html',
  styleUrls: ['./waiter-page.component.css']
})
export class WaiterPageComponent implements OnInit{
  public errorMessage: string = "";
  public orderDto: OrderDto = {
    guestId: "",
    dishIds: ""
  }
  public orders: Menu[] = [];

  constructor(
    private router: Router,
    private hotelService: HotelService){
    if(this.router.getCurrentNavigation()?.extras.state == undefined)
      router.navigateByUrl('login');
  }

  public employeeId: any;
  ngOnInit(): void {
    this.employeeId = history.state;
    console.log(this.employeeId.id);
    this.showOrders();
  }

  public showPersonalData(): void {
    this.router.navigateByUrl("/personal-data", {state: {id: this.employeeId.id}});
  }

  onSubmit(form: NgForm){
    this.hotelService.acceptOrder(this.orderDto).subscribe(
      (response: HttpStatusCode.Accepted) => {
        confirm('Order accepted successfully');
      },
      (error) => {
        this.errorMessage = 'Failed to accept order: ' + this.orderDto.dishIds
        console.log(error);
        confirm(this.errorMessage)
      });
  }

  public showOrders(): void {
    this.hotelService.showOrders().subscribe(
      (orders: Menu[]) => {
        this.orders = orders;
      },
      (error) => {
        console.error('Error fetching orders:', error);
      }
    )
  }

  

}
