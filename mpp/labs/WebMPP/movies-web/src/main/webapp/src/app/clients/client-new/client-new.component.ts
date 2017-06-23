import {Component, Input, ViewChild} from '@angular/core';
import {Client} from "../shared/client.model";
import {ClientService} from "../shared/client.service";
import {Location} from '@angular/common';

@Component({
  selector: 'ubb-client-new',
  templateUrl: './client-new.component.html',
  styleUrls: ['./client-new.component.css']
})
export class ClientNewComponent{

  
  @Input() client: Client;

  constructor(private clientService: ClientService,
              private location: Location) {
  }

  goBack(): void {
    this.location.back();
  }

  save(firstname, lastname, phoneNr, address) : void {
    console.log("jjjjjjjjjj ", firstname, lastname, phoneNr, address);
    if (!this.isValid(firstname, lastname, phoneNr, address)) {
      console.log("all fields are required ");
      alert("all fields are required");
      return;
    }
    this.clientService.create(firstname, lastname, phoneNr, address)
        .subscribe(response => {
          if (!response.hasOwnProperty("isError")) {
            this.goBack();
            return;
          }
          var errorString = "Server side errors:\n";
          if (response.firstName) {
            errorString += response.firstName + "\n";
          }
          if (response.lastName) {
            errorString += response.lastName + "\n";
          }
          if (response.phoneNr) {
            errorString += response.phoneNr + "\n";
          }
          if (response.address) {
            errorString += response.address;
          }

          alert(errorString);


        });
  }

  private isValid(firstname, lastname, phoneNr, address) {
    if (!firstname || !lastname || !phoneNr || !address) {
      console.log("all fields are required");
      return false;
    }

    //TODO other validations
    return true;
  }


}
