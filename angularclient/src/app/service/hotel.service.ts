import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Room } from '../model/room/room';
import { Guest } from '../model/guest/guest';
import { Employee } from '../model/employee/employee';
import { Menu } from '../model/menu/menu';
import { OrderDto } from '../model/orderDto/orderDto';
import { EmployeeLoginDto } from '../model/employeeLoginDto/employeeLoginDto';
import { GuestAssignDto } from '../model/guestAssignDto/guestAssignDto';
import { Observable, map} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HotelService {


  public mainUrl = 'http://localhost:8080/hotel'
  constructor(private http: HttpClient) {}

  public login(loginDto: EmployeeLoginDto): Observable<{job: string, empID: any}>{
     return this.http.post<{job: string, empID: any}>(this.mainUrl +'/login', loginDto);
  }

  public saveEmployee(employee: Employee): Observable<any>{
    return this.http.post<any>(this.mainUrl +'/employee/' + employee.job + '/add', employee).pipe(
      map(
        (response: Response) =>{
           console.log(response.json);
        }
      )
    );
  }

  public saveRoom(room: Room): Observable<any>{
    return this.http.post<any>(this.mainUrl +'/room/add', room).pipe(
      map(
        (response: Response) =>{
           console.log(response.json);
        }
      )
    );
  }

  public saveGuest(guest: Guest): Observable<any>{
    return this.http.post<any>(this.mainUrl +'/employee/add', guest).pipe(
      map(
        (response: Response) =>{
           console.log(response.json);
        }
      )
    );
  }

  public acceptOrder(order: OrderDto): Observable<any>{
    return this.http.post<any>(this.mainUrl +'/waiter/accept', order).pipe(
      map(
        (response: Response) =>{
           console.log(response.json);
        }
      )
    );
  }

  //
  public showOrders(): Observable<Menu[]>{
    return this.http.get<Menu[]>(this.mainUrl + '/order/get/all');
  }

  public nextDay(): void{
    this.http.post<void>(this.mainUrl +'/nextDay', null);
  }

  public getEmployees(): Observable<Employee[]>{
    return this.http.get<Employee[]>(this.mainUrl+'/employee/get/all');
  }
  public getEmployee(devId: string): Observable<Employee>{
    return this.http.get<Employee>(this.mainUrl+'/employee/get/' + devId);
  }

  public getGuests(): Observable<Guest[]>{
    return this.http.get<Guest[]>(this.mainUrl+'/guest/get/all');
  }

  public getGuest(guestId: string): Observable<Guest>{
    return this.http.get<Guest>(this.mainUrl+ '/guest/get/' +guestId);
  }

  public getRooms(): Observable<Room[]>{
    return this.http.get<Room[]>(this.mainUrl + '/room/get/all');
  }

  public getRoom(roomId: string): Observable<Room>{
    return this.http.get<Room>(this.mainUrl+ '/room/get/' +roomId);
  }

  public getVacantRooms(): Observable<Room[]>{
    return this.http.get<Room[]>(this.mainUrl + '/room/get/vacant');
  }

  public getUncleanedRooms(): Observable<Room[]>{
    return this.http.get<Room[]>(this.mainUrl + '/room/get/uncleaned');
  }

  public getCurrentDate(): Observable<string>{
    return this.http.get<string>(this.mainUrl+ '/data/get');
  }

  public getBalance(): Observable<number>{
    return this.http.get<number>(this.mainUrl+ '/balance/get');
  }

  public getOrders(): Observable<Menu[]>{
    return this.http.get<Menu[]>(this.mainUrl + '/order/get/all');
  }

  public assignRoom(guestAssignDto: GuestAssignDto): Observable<any>{
    return this.http.put<any>(this.mainUrl +'/receptionist/assign', guestAssignDto).pipe(
      map(
        (response: Response) =>{
           console.log(response.json);
        }
      )
    );
  }

  public shutdown(): void{
    this.http.put<void>(this.mainUrl +'/shutdown', null);
  }

  public completeOrder(employeeId: number, orderId: number): Observable<any>{
    return this.http.put<any>(this.mainUrl +'/order/' + orderId+ '/'+ employeeId + '/complete', null).pipe(
      map(
        (response: Response) =>{
           console.log(response.json);
        }
      )
    );
  }

  public cleanRoom(employeeId: number, roomId: number): Observable<any>{
    return this.http.put<any>(this.mainUrl +'/room/' + roomId + '/clean', employeeId).pipe(
      map(
        (response: Response) =>{
           console.log(response.json);
        }
      )
    );
  }

  public updateEmployee(employeeId: number, job: string, employee: Employee): Observable<any>{
    return this.http.put<any>(this.mainUrl +'/employee/' + job + '/' + employeeId + '/update', employee).pipe(
      map(
        (response: Response) =>{
           console.log(response.json);
        }
      )
    );
  }

  public deleteEmployee(employeeId : string){
    return this.http.delete(this.mainUrl + '/employee/'+ employeeId +'/delete')
  }
}
