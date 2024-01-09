import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminPageComponent } from './admin-page/admin-page.component';
import { CleanerPageComponent } from './cleaner-page/cleaner-page.component';
import { CookPageComponent } from './cook-page/cook-page.component';
import { HomePageComponent } from './home-page/home-page.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { WaiterPageComponent } from './waiter-page/waiter-page.component';
import { ReceptionistPageComponent } from './receptionist-page/receptionist-page.component';

const routes: Routes = [
  { path: 'admin', component: AdminPageComponent },
  { path: 'cleaner', component: CleanerPageComponent },
  { path: 'cook', component: CookPageComponent },
  { path: 'home', component: HomePageComponent },
  { path: 'login', component: LoginPageComponent },
  { path: '', component: LoginPageComponent},
  { path: 'receptionist', component: ReceptionistPageComponent },
  { path: 'waiter', component: WaiterPageComponent },
  { path: '**', redirectTo: ''}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
