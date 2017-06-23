"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var core_1 = require('@angular/core');
var MovieListComponent = (function () {
    function MovieListComponent(movieService, router) {
        this.movieService = movieService;
        this.router = router;
    }
    MovieListComponent.prototype.ngOnInit = function () {
        this.getMovies();
    };
    MovieListComponent.prototype.getMovies = function () {
        var _this = this;
        this.movieService.getMovies()
            .subscribe(function (movies) { return _this.movies = movies; }, function (error) { return _this.errorMessage = error; });
    };
    MovieListComponent.prototype.onSelect = function (movie) {
        this.selectedMovie = movie;
    };
    MovieListComponent.prototype.gotoDetail = function () {
        this.router.navigate(['/movie/detail', this.selectedMovie.id]);
    };
    MovieListComponent = __decorate([
        core_1.Component({
            moduleId: module.id,
            selector: 'ubb-movie-list',
            templateUrl: './movie-list.component.html',
            styleUrls: ['./movie-list.component.css'],
        })
    ], MovieListComponent);
    return MovieListComponent;
}());
exports.MovieListComponent = MovieListComponent;
