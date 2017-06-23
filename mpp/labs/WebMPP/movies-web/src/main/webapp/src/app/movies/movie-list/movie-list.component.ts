import {Component, OnInit} from '@angular/core';
import {Movie} from "../shared/movie.model";
import {MovieService} from "../shared/movie.service";
import {Router} from "@angular/router";


@Component({
    moduleId: module.id,
    selector: 'ubb-movie-list',
    templateUrl: './movie-list.component.html',
    styleUrls: ['./movie-list.component.css'],
})
export class MovieListComponent implements OnInit {
    errorMessage: string;
    movies: Movie[];
    selectedMovie: Movie;

    constructor(private movieService: MovieService,
                private router: Router) {
    }

    ngOnInit(): void {
        this.getMovies();
    }

    getMovies() {
        this.movieService.getMovies()
            .subscribe(
                movies => this.movies = movies,
                error => this.errorMessage = <any>error
            );
    }

    onSelect(movie: Movie): void {
        this.selectedMovie = movie;
    }

    gotoDetail(): void {
        this.router.navigate(['/movie/detail', this.selectedMovie.id]);
    }

    delete(movie: Movie): void {
        this.movieService.delete(movie.id)
            .subscribe(() => {
                this.movies = this.movies.filter(s => s !== movie);
                if (this.selectedMovie === movie) {
                    this.selectedMovie = null;
                }
            });
    }

}
