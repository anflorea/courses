import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';

import {AppComponent} from './app.component';

import {AppRoutingModule} from "./app-routing.module";

import { PizzaComponent } from './pizza/pizza.component';
import { PizzaService } from "./pizza/shared/pizza.service";
import { PizzaDetailComponent } from './pizza/pizza-detail/pizza-detail.component';

@NgModule({
    declarations: [
        AppComponent,
        PizzaComponent,
        PizzaDetailComponent,


    ],
    imports: [
        BrowserModule,
        FormsModule,
        HttpModule,
        AppRoutingModule,
    ],
    providers: [PizzaService],
    bootstrap: [AppComponent]
})
export class AppModule {
}


