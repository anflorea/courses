/**
 * Created by andrapop on 2017-04-22.
 */

import {Injectable} from '@angular/core';
import {Http, Response, Headers} from "@angular/http";

import {Observable} from "rxjs";
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

import {Client} from "./client.model";


@Injectable()
export class ClientService {
    private clientsUrl = 'http://localhost:8080/api/clients';
    private headers = new Headers({'Content-Type': 'application/json'});

    constructor(private http: Http) {
    }

    getClients(): Observable<Client[]> {
        return this.http.get(this.clientsUrl)
            .map(this.extractData)
            .catch(this.handleError);
    }

    private extractData(res: Response) {
        let body = res.json();
        return body.clients || {};
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

    getClient(id: number): Observable<Client> {
        return this.getClients()
            .map(clients => clients.find(client => client.id === id));
    }

    update(client): Observable<Client> {
        const url = `${this.clientsUrl}/${client.id}`;
        console.log("In update(client service)")
        return this.http
            .put(url, JSON.stringify({"client": client}), {headers: this.headers})
            .map(this.extractClientData)
            .catch(this.handleError);
    }

    private extractClientData(res: Response) {
        let body = res.json();
        if (body.error) {
            body.error.isError = true;
            return body.error;
        }
        return body.client || {};
    }

    create(firstName: string, lastName: string, phoneNr: number, address: string): Observable<Client> {
        let client = { firstName, lastName, phoneNr, address};
        console.log("aaaaaaaaaa");
        console.log( JSON.stringify({"client": client}));
        return this.http
            .post(this.clientsUrl, JSON.stringify({"client": client}), {headers: this.headers})
            .map(this.extractClientData)
            .catch(this.handleError);


    }

    delete(id: number): Observable<void> {
        const url = `${this.clientsUrl}/${id}`;
        return this.http
            .delete(url, {headers: this.headers})
            .map(() => null)
            .catch(this.handleError);
    }

}