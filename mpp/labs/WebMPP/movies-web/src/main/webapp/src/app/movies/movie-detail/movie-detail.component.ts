import {Component, Input, OnInit} from '@angular/core';

import {ActivatedRoute, Params} from "@angular/router";
import {Location} from '@angular/common';


import 'rxjs/add/operator/switchMap';
import {MovieService} from "../shared/movie.service";
import {Movie} from "../shared/movie.model";


@Component({
    selector: 'ubb-movie-detail',
    templateUrl: './movie-detail.component.html',
    styleUrls: ['./movie-detail.component.css'],
})

export class MovieDetailComponent implements OnInit {

    @Input()
    movie: Movie;

    constructor(private movieService: MovieService,
                private route: ActivatedRoute,
                private location: Location) {
    }

    ngOnInit(): void {
        this.route.params
            .switchMap((params: Params) => this.movieService.getMovie(+params['id']))
            .subscribe(movie => this.movie = movie);
    }

    goBack(): void {
        this.location.back();
    }

    save(): void {
        this.movieService.update(this.movie)
            .subscribe(_ => this.goBack());
    }
}