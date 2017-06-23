"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var platform_browser_1 = require('@angular/platform-browser');
var core_1 = require('@angular/core');
var forms_1 = require('@angular/forms');
var http_1 = require('@angular/http');
var app_component_1 = require('./app.component');
var app_routing_module_1 = require("./app-routing.module");
var movie_detail_component_1 = require("./movies/movie-detail/movie-detail.component");
var movies_component_1 = require("./movies/movies.component");
var movie_list_component_1 = require("./movies/movie-list/movie-list.component");
var movie_service_1 = require("./movies/shared/movie.service");
var clients_component_1 = require('./clients/clients.component');
var client_list_component_1 = require('./clients/client-list/client-list.component');
var client_detail_component_1 = require('./clients/client-detail/client-detail.component');
var client_service_1 = require("./clients/shared/client.service");
var AppModule = (function () {
    function AppModule() {
    }
    AppModule = __decorate([
        core_1.NgModule({
            declarations: [
                app_component_1.AppComponent,
                movie_detail_component_1.MovieDetailComponent,
                movies_component_1.MoviesComponent,
                movie_list_component_1.MovieListComponent,
                clients_component_1.ClientsComponent,
                client_list_component_1.ClientListComponent,
                client_detail_component_1.ClientDetailComponent,
            ],
            imports: [
                platform_browser_1.BrowserModule,
                forms_1.FormsModule,
                http_1.HttpModule,
                app_routing_module_1.AppRoutingModule,
            ],
            providers: [movie_service_1.MovieService, client_service_1.ClientService],
            bootstrap: [app_component_1.AppComponent]
        })
    ], AppModule);
    return AppModule;
}());
exports.AppModule = AppModule;
