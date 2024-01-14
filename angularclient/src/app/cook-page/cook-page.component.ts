import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {HotelService} from "../service/hotel.service";
import {Menu} from "../model/menu/menu";
import {NgForm} from "@angular/forms";
import { HttpStatusCode } from '@angular/common/http';

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
    public employeeId: {id: number} = {id: 1};
    ngOnInit(): void {
        this.employeeId = history.state;
        console.log(this.employeeId.id);
    }
    // ✅✅✅✅☑☑☑
    // mozna zrobic tak ze bedzie pole w ktorym wpisujemy id zamoiwienia, a potem klikamy przycisk ktory wywoluje metode 🐱‍👤🐱‍👤🤗🙄😪😯🤐🥱😴😧😩😳
    onSubmitCompleteOrder(form: NgForm){
        this.hotelService.completeOrder(this.employeeId.id,this.orderId).subscribe(

            (response: HttpStatusCode.Ok) => {
                console.log("Order completed");
                confirm("Order completed");
            },
            error => {
                let errorMessageJSON: string = JSON.stringify(error);
                let key = "error";
                let index = errorMessageJSON.indexOf(key);
                let errorMessage = errorMessageJSON.substring(index+8, errorMessageJSON.length-2);
                confirm(errorMessage);
            }

        )
    }


//😋😋🍴🍴🍽🍽
    public showOrders(){
        this.hotelService.showOrders().subscribe(
            response => {
                this.orderList = response;
                this.displayOrders = true;
            },
            error => {
                let errorMessageJSON: string = JSON.stringify(error);
                let key = "error";
                let index = errorMessageJSON.indexOf(key);
                let errorMessage = errorMessageJSON.substring(index+8, errorMessageJSON.length-2);
                confirm(errorMessage);
            }
        )
    }
}
