/**
 * Created by andrapop on 2017-05-07.
 */
import {Injectable} from "@angular/core";
import {Headers, Http, Response} from "@angular/http";
import {Rental} from "./rentals.model";

import {Observable} from "rxjs";

@Injectable()
export class ClientMovieService {
    private clientMovieUrl = 'http://localhost:8080/api/rentals';
    private headers = new Headers({'Content-Type': 'application/json'});

    constructor(private http: Http) {
    }

    getClientMovies(clientID: number): Observable<Rental[]> {
        return this.http.get(`${this.clientMovieUrl}/${clientID}`, this.headers)
            .map(this.extractData)
            .catch(this.handleError);
    }

    private extractData(res: Response) {
        let body = res.json();
        return body.clientMovies || {};
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
    /*

    saveGrades(clientId: number, movieIdsGrades: Object): Observable<ClientMovie[]> {
        const url = `${this.clientMovieUrl}/${clientId}`;
        let clientMovies = this.createClientMovies(clientId, movieIdsGrades);
        console.log("grades: ",clientMovies);
        console.log("request: ",JSON.stringify({"clientMovies": clientMovies}));
        return this.http
            .put(url, JSON.stringify({"clientMovies": clientMovies}), {headers: this.headers})
            .map(this.extractData)
            .catch(this.handleError);
    }
    */

    private createClientMovies(clientId: number, movieIdsGrades: Object): Rental[] {
        const arr: Rental[] = [];
        Object.keys(movieIdsGrades).forEach(movieId => {
            const r = new Rental(
                clientId,
                parseInt(movieId),
                movieIdsGrades[movieId]);
            arr.push(r);
        });
        return arr;
    }
}