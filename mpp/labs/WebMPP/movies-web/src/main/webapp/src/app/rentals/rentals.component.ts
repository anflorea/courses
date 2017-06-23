import {Component} from '@angular/core';
import {ClientService} from "../clients/shared/client.service";
import {ClientMovieService} from "./shared/rentals.service";
import {Rental} from "./shared/rentals.model";
import {Location} from "@angular/common";
import {Client} from "../clients/shared/client.model";

@Component({
  moduleId: module.id,
  selector: 'ubb-rentals',
  templateUrl: './rentals.component.html',
  styleUrls: ['./rentals.component.css'],
})
export class RentalsComponent {
  errorMessage: string;
  showMovies: boolean;
  clientMovies: Rental[];
  selectedClient: Client;

  constructor(private clientService: ClientService,
              private clientMovieService: ClientMovieService,
              private location: Location) {

  }

  loadRentals(firstName: string, lastName: string) {
    this.showMovies = false;
    if (!firstName) {
      console.log("first name must not be empty");
      alert("last name must not be empty");
      return;
    }
    if (!lastName) {
      console.log("last name must not be empty");
      alert("last name must not be empty");
      return;
    }
    this.loadClientMoviesForClient(firstName, lastName);
  }

  private loadClientMoviesForClient(firstName: string, lastName: string) {
    this.clientService.getClients()
        .subscribe(
            clients => {
              const clientArr = clients.filter(c => c.firstName === firstName, c => c.lastName === lastName );
              //TODO handle errors (clientArr should contain one client)
              if (clientArr && clientArr.length === 1) {
                this.showMovies = true;
                const client = clientArr[0];
                this.selectedClient = client;
                this.clientMovieService.getClientMovies(client.id)
                    .subscribe(
                        clientMovies => this.clientMovies = clientMovies,
                        error => this.errorMessage = error)
              } else {
                console.log("clientArr ", clientArr);
              }
            },
            error => this.errorMessage = <any>error);
  }
  /*

  save(clientMovieForm) {
    let grades = clientMovieForm.form.value;
    console.log("grades: ", grades);
    this.clientMovieService.saveGrades(this.selectedClient.id, grades)
        .subscribe(_ => this.goBack());
  }
  */

  private goBack(): void {
    this.location.back();
  }
}