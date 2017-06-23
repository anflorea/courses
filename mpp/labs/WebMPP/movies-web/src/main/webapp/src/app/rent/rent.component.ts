import { Component, OnInit } from '@angular/core';
import {Client} from "../clients/shared/client.model";
import {Movie} from "../movies/shared/movie.model";
import {MovieService} from "../movies/shared/movie.service";
import {ClientService} from "../clients/shared/client.service";
import {Location} from "@angular/common";


@Component({
  selector: 'ubb-rent',
  templateUrl: './rent.component.html',
  styleUrls: ['./rent.component.css']
})
export class RentComponent {
  errorMessage: string;
  selectedClient: Client;
  showMovies: boolean;
  selectedMovies: Movie[];
  availableMovies: Movie[];

  constructor(private clientService: ClientService,
              private movieService: MovieService,
              private location: Location) {
  }

  goBack(): void {
    this.location.back();
  }

  loadMovies(firstName: string, lastName: string) {
    this.showMovies = false;
    if (!firstName) {
      console.log("first name must not be empty");
      alert("first name must not be empty");
      return;
    }
    if (!lastName) {
      console.log("last name must not be empty");
      alert("last name must not be empty");
      return;
    }
    this.loadAvailableMovies();
    this.loadSelectedMovies(firstName, lastName);
  }

  private loadSelectedMovies(firstName: string, lastName: string) {
    this.clientService.getClients()
        .subscribe(
            clients => {
              const currentClient = clients.filter(s => s.firstName === firstName, s => s.lastName === lastName);
              //TODO there should be exactly one client in currentClient or err; handle errs
              if (currentClient.length === 1) {
                this.selectedClient = currentClient[0];
                this.showMovies = true;
                this.loadSelectedMoviesForClient(this.selectedClient);
              }
            },
            error => this.errorMessage = <any>error);
  }

  private loadSelectedMoviesForClient(client: Client) {
    this.selectedMovies = new Array();
    const movieIds = client.movies;
    if (movieIds) {
      movieIds.forEach(id => {
        this.movieService.getMovie(id)
            .subscribe(
                movie => {
                  this.selectedMovies = this.selectedMovies
                      .concat([movie]);
                },
                error => this.errorMessage = error);
      });
    }
  }

  private loadAvailableMovies() {
    this.movieService.getMovies()
        .subscribe(
            movies => this.availableMovies = movies,
            error => this.errorMessage = <any>error);
  }

  selectMovie(movie: Movie) {
    const disc = this.selectedMovies.filter(d => d === movie);
    if (disc.length > 0) {
      console.log("movie already selected");
      alert("movie already selected");
      return;
    }
    this.selectedMovies = this.selectedMovies.concat([movie]);
  }

  removeSelectedMovie(movie: Movie) {
    this.selectedMovies = this.selectedMovies.filter(d => d !== movie);
  }

  save() {

    this.selectedClient.movies = this.selectedMovies.map(m => m.id);
    console.log("aaaaaaaaaaaa ", this.selectedMovies, this.selectedMovies.map(d => d.id), this.selectedClient.movies);
    this.clientService.update(this.selectedClient)
        .subscribe(_ => this.goBack())
  }

}
