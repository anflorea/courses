import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';

import {AppComponent} from './app.component';

import {AppRoutingModule} from "./app-routing.module";

import {MovieDetailComponent} from "./movies/movie-detail/movie-detail.component";
import {MoviesComponent} from "./movies/movies.component";
import {MovieListComponent} from "./movies/movie-list/movie-list.component";
import {MovieService} from "./movies/shared/movie.service";
import { ClientsComponent } from './clients/clients.component';
import { ClientListComponent } from './clients/client-list/client-list.component';
import { ClientDetailComponent } from './clients/client-detail/client-detail.component';
import {ClientService} from "./clients/shared/client.service";
import {MovieNewComponent} from "./movies/movie-new/movie-new.component";
import { ClientNewComponent } from './clients/client-new/client-new.component';
import { RentalsComponent } from './rentals/rentals.component';
import { RentComponent } from './rent/rent.component';
import {ClientMovieService} from "./rentals/shared/rentals.service";

@NgModule({
    declarations: [
        AppComponent,
        MovieDetailComponent,
        MoviesComponent,
        MovieListComponent,
        ClientsComponent,
        ClientListComponent,
        ClientDetailComponent,
        MovieNewComponent,
        ClientNewComponent,
        RentalsComponent,
        RentComponent,


    ],
    imports: [
        BrowserModule,
        FormsModule,
        HttpModule,
        AppRoutingModule,
    ],
    providers: [MovieService, ClientService, ClientMovieService],
    bootstrap: [AppComponent]
})
export class AppModule {
}


