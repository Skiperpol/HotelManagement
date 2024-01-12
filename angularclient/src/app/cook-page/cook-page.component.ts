import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {HotelService} from "../service/hotel.service";
import {Menu} from "../model/menu/menu";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'app-cook-page',
  templateUrl: './cook-page.component.html',
  styleUrls: ['./cook-page.component.css']
})
export class CookPageComponent implements OnInit{

  public orderList: Menu[] = [];
  public displayOrders: boolean = false;
  public orderId: number = 0;

  constructor(private router: Router, private hotelService: HotelService){
    if(this.router.getCurrentNavigation()?.extras.state == undefined)
      router.navigateByUrl('login');
  }
  public employeeId: any;
  ngOnInit(): void {
    this.employeeId = history.state;
    console.log(this.employeeId.id);
  }
  // ✅✅✅✅☑☑☑
  // mozna zrobic tak ze bedzie pole w ktorym wpisujemy id zamoiwienia, a potem klikamy przycisk ktory wywoluje metode 🐱‍👤🐱‍👤🤗🙄😪😯🤐🥱😴😧😩😳
  onSubmitCompleteOrder(form: NgForm){
    this.hotelService.completeOrder(this.employeeId,this.orderId).subscribe(

      response => {
        console.log("pokazano")
        confirm('Zamówienie skompletowane');
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


//😋😋🍴🍴🍽🍽
  public showOrders(){
    this.hotelService.showOrders().subscribe(

      response => {
        console.log("pokazano")
        this.orderList = response;
        this.displayOrders = true;
      },
      error => {
        confirm("podano zły numer zamówienia")
      }
    )
  }
}
