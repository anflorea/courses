/**
 * Created by flo on 17/06/2017.
 */
import {Injectable} from '@angular/core';
import {Http, Response, Headers, URLSearchParams} from "@angular/http";

import {Observable} from "rxjs";
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

import {Pizza} from "./pizza.model";


@Injectable()
export class PizzaService {
    private pizzasUrl = 'http://localhost:8080/api/pizzas';
    private updatePizzasUrl = 'http://localhost:8080/api/pizzas/update';
    private headers = new Headers({'Content-Type': 'application/json'});

    constructor(private http: Http) {
    }

    getPizzas(): Observable<Pizza[]> {
        return this.http.get(this.pizzasUrl)
            .map(this.extractData)
            .catch(this.handleError);
    }

    private extractData(res: Response) {
        let body = res.json();
        return body.pizzas || {};
    }

    getPizza(id: number): Observable<Pizza>{
        console.log("get pizza " + id);
        return this.http.get(this.pizzasUrl + "/" + id.toString()).map(this.extractPizzaData).catch(this.handleError);
    }

    private handleError(error: Response | any) {
        let errMsg: string;
        if (error instanceof Response) {
            const body = error.json() || '';
            const err = body.error || JSON.stringify(body);
            errMsg = `${error.status} - ${error.statusText || ''} ${err}`;
        } else {
            errMsg = error.message ? error.message : error.toString();
        }
        console.error(errMsg);
        return Observable.throw(errMsg);
    }

    private extractPizzaData(res: Response) {
        let body = res.json();

        return body.pizza || {};
    }

    create(name: string, description: string, price: number): Observable<Pizza> {
        let pizza = { name, description, price};
        console.log("aaaaaaaaaa");
        console.log( JSON.stringify({"pizza": pizza}));
        return this.http
            .post(this.pizzasUrl, JSON.stringify({"pizza": pizza}), {headers: this.headers})
            .map(this.extractPizzaData)
            .catch(this.handleError);
    }

    updatePizza(pizza: Pizza): Observable<Pizza> {
        return this.http.put(this.pizzasUrl + "/" + pizza.id.toString(), JSON.stringify({"pizza": pizza}), {headers: this.headers})
            .map(this.extractPizzaData)
            .catch(this.handleError);
    }

    updateAllPizzas(price: number): Observable<Pizza[]> {
        return this.http.put(this.updatePizzasUrl, JSON.stringify({"price": price}), {headers: this.headers})
            .map(this.extractData)
            .catch(this.handleError);
    }
}