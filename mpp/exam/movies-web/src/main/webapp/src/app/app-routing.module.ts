import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {PizzaComponent} from "./pizza/pizza.component";
import { PizzaDetailComponent } from './pizza/pizza-detail/pizza-detail.component';



const routes: Routes = [
    // { path: '', redirectTo: '/home', pathMatch: 'full' },
    { path: 'pizzashop2010/pizzas', component: PizzaComponent},
    { path: 'pizzashop2010/pizza/:id', component: PizzaDetailComponent}
];
@NgModule({
    imports: [ RouterModule.forRoot(routes) ],
    exports: [ RouterModule ]
})
export class AppRoutingModule {}
