/**
 * Created by andrapop on 2017-05-07.
 */
export class Rental{
    clientId: number;
    movieId: number;
    movieName: string;

    constructor(clientId: number, movieId: number, movieName: string) {
        this.clientId = clientId;
        this.movieId = movieId;
        this.movieName = movieName;
    }

}