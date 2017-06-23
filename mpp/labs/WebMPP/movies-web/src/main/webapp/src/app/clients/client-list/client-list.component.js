"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var core_1 = require('@angular/core');
var ClientListComponent = (function () {
    function ClientListComponent(clientService, router) {
        this.clientService = clientService;
        this.router = router;
    }
    ClientListComponent.prototype.ngOnInit = function () {
        this.getClients();
    };
    ClientListComponent.prototype.getClients = function () {
        var _this = this;
        this.clientService.getClients()
            .subscribe(function (clients) { return _this.clients = clients; }, function (error) { return _this.errorMessage = error; });
    };
    ClientListComponent.prototype.onSelect = function (client) {
        this.selectedClient = client;
    };
    ClientListComponent.prototype.gotoDetail = function () {
        this.router.navigate(['/client/detail', this.selectedClient.id]);
    };
    ClientListComponent = __decorate([
        core_1.Component({
            moduleId: module.id,
            selector: 'ubb-client-list',
            templateUrl: './client-list.component.html',
            styleUrls: ['./client-list.component.css'],
        })
    ], ClientListComponent);
    return ClientListComponent;
}());
exports.ClientListComponent = ClientListComponent;
