import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Hero } from './hero';
import { MessageService } from './message.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';

@Injectable({
    providedIn: 'root',
  })
export class HeroService {

  private heroesUrl = '/heroes'
  private heroesUrlCrear ='/heroes/crear'
  private heroesUrlUno = '/heroes/detalle'
  private heroesActualizar = '/heroes/actualizar'
  private heroesEliminar = '/heroes/eliminar'



constructor(private messageService:MessageService,private http: HttpClient) { }

getHeroes(): Observable<Hero[]> {
  return this.http.get<Hero[]>(this.heroesUrl)
    .pipe(
      tap(_ => this.log('fetched heroes')),
      catchError(this.handleError<Hero[]>('getHeroes', []))
    );
}

getHero(id: string): Observable<Hero> {
  const url = `${this.heroesUrlUno}/${id}`;
  return this.http.get<Hero>(url).pipe(
    tap(_ => this.log(`fetched hero id=${id}`)),
    catchError(this.handleError<Hero>(`getHero id=${id}`))
  );
}


addHero(hero: Hero): Observable<Hero> {
  return this.http.post<Hero>(this.heroesUrlCrear, hero, this.httpOptions).pipe(
    tap((newHero: Hero) => this.log(`added hero w/ id=${newHero.id}`)),
    catchError(this.handleError<Hero>('addHero'))
  );
}

updateHero(hero: Hero): Observable<any> {
  return this.http.post(this.heroesActualizar, hero, this.httpOptions).pipe(
    tap(_ => this.log(`updated hero id=${hero.id}`)),
    catchError(this.handleError<any>('updateHero'))
  );
}

deleteHero(id: string): Observable<Hero> {
  const url = `${this.heroesEliminar}/${id}`;

  return this.http.delete<Hero>(url, this.httpOptions).pipe(
    tap(_ => this.log(`deleted hero id=${id}`)),
    catchError(this.handleError<Hero>('deleteHero'))
  );
}

httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

private handleError<T>(operation = 'operation', result?: T) {
  return (error: any): Observable<T> => {

    // TODO: send the error to remote logging infrastructure
    console.error(error); // log to console instead

    // TODO: better job of transforming error for user consumption
    this.log(`${operation} failed: ${error.message}`);

    // Let the app keep running by returning an empty result.
    return of(result as T);
  };

  
}

private log(message: string) {
  this.messageService.add(`HeroService: ${message}`);
}

}
