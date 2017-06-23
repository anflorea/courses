import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {MovieDetailComponent} from "./movies/movie-detail/movie-detail.component";
import {MoviesComponent} from "./movies/movies.component";
import {ClientsComponent} from "./clients/clients.component";
import {ClientDetailComponent} from "./clients/client-detail/client-detail.component";
import {MovieNewComponent} from "./movies/movie-new/movie-new.component";
import {ClientNewComponent} from "./clients/client-new/client-new.component";
import {RentComponent} from "./rent/rent.component";
import {RentalsComponent} from "./rentals/rentals.component";



const routes: Routes = [
    // { path: '', redirectTo: '/home', pathMatch: 'full' },
    { path: 'movies',     component: MoviesComponent },
    { path: 'movie/detail/:id', component: MovieDetailComponent},

    { path: 'clients',     component: ClientsComponent },
    { path: 'client/detail/:id', component: ClientDetailComponent},
    { path: 'movie/new', component: MovieNewComponent},
    { path: 'client/new', component: ClientNewComponent},
    { path: 'rent', component: RentComponent},
    { path: 'rentals', component: RentalsComponent}
];
@NgModule({
    imports: [ RouterModule.forRoot(routes) ],
    exports: [ RouterModule ]
})
export class AppRoutingModule {}
