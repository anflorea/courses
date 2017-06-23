"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var core_1 = require('@angular/core');
require('rxjs/add/operator/switchMap');
var MovieDetailComponent = (function () {
    function MovieDetailComponent(movieService, route, location) {
        this.movieService = movieService;
        this.route = route;
        this.location = location;
    }
    MovieDetailComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.route.params
            .switchMap(function (params) { return _this.movieService.getMovie(+params['id']); })
            .subscribe(function (movie) { return _this.movie = movie; });
    };
    MovieDetailComponent.prototype.goBack = function () {
        this.location.back();
    };
    __decorate([
        core_1.Input()
    ], MovieDetailComponent.prototype, "movie", void 0);
    MovieDetailComponent = __decorate([
        core_1.Component({
            selector: 'ubb-movie-detail',
            templateUrl: './movie-detail.component.html',
            styleUrls: ['./movie-detail.component.css'],
        })
    ], MovieDetailComponent);
    return MovieDetailComponent;
}());
exports.MovieDetailComponent = MovieDetailComponent;
