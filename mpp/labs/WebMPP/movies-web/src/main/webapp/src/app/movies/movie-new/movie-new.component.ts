/**
 * Created by andrapop on 2017-04-24.
 */
import {Component, Input} from "@angular/core";
import {Location} from '@angular/common';

import {Movie} from "../shared/movie.model";
import {MovieService} from "../shared/movie.service";
import {error} from "util";


@Component({
    moduleId: module.id,
    selector: 'ubb-movie-new',
    templateUrl: './movie-new.component.html',
    styleUrls: ['./movie-new.component.css'],
})
export class MovieNewComponent {
    @Input() movie: Movie;

    constructor(private movieService: MovieService,
                private location: Location) {
    }

    goBack(): void {
        this.location.back();
    }


    save(name, genre, year): void {
        console.log("jjjjjjjjjj ", name, genre, year);
        if (!this.isValid(name, genre, year)) {
            console.log("all fields are required ");
            alert("all fields are required; year has to be an int");
            return;
        }
        this.movieService.create(name, genre, year)
            .subscribe(response => {
                if (!response.hasOwnProperty("isError")) {
                    this.goBack();
                    return;
                }
                var errorString = "Server side errors:\n";
                if (response.name) {
                    errorString += response.name + "\n";
                }
                if (response.genre) {
                    errorString += response.genre + "\n";
                }
                if (response.year) {
                    errorString += response.year + "\n";
                }

                alert(errorString);
            });
    }

    private isValid(name, genre, year) {
        if (!name || !genre || !year) {
            console.log("all fields are required");
            return false;
        }
        /*
        if (!year.isInteger(Number(year))) {
            console.log("year has to be an int");
            return false;
        }
        */
        //TODO other validations
        return true;
    }
}