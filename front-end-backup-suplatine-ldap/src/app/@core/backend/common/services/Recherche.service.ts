/*
 * Copyright (c) Akveo 2019. All Rights Reserved.
 * Licensed under the Single Application / Multi Application License.
 * See LICENSE_SINGLE_APP / LICENSE_MULTI_APP in the 'docs' folder for license information on type of purchased license.
 */

import {Injectable} from '@angular/core';
import {Observable, throwError} from 'rxjs';
import {HttpHeaders} from "@angular/common/http";
import {HttpService} from "../api/http.service";
import {catchError, retry} from "rxjs/operators";

@Injectable()
export class RechercheService {
  private baseurl = 'v1'; // request mapping


  // Http Headers
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    }),
  };

  constructor(private http: HttpService) {
  }

  // POST JSON.stringify(data)
  public create(data: any): Observable<any> {
    return this.http.post(this.baseurl + '/create', data, this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.errorHandl),
      );
  }

  // GET
  public getAll(): Observable<any> {
    return this.http.get(this.baseurl + '/get-all')
      .pipe(
        retry(1),
        catchError(this.errorHandl),
      );
  }

  // GET
  public getByName(name: string): Observable<any> {
    return this.http.put(this.baseurl + '/get-processName/' + name, this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.errorHandl),
      );
  }
  // GET
  public getByStatus(name: string): Observable<any> {
    return this.http.put(this.baseurl + '/get-status/' + name, this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.errorHandl),
      );
  }
  // GET
  public getByIdFun(name: string): Observable<any> {
    return this.http.put(this.baseurl + '/get-idFun/' + name, this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.errorHandl),
      );
  }
  // GET
  public getByIdTech(name: string): Observable<any> {
    return this.http.put(this.baseurl + '/get-idTech/' + name, this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.errorHandl),
      );
  }
  // GET
  public getByLog(name: string): Observable<any> {
    return this.http.put(this.baseurl + '/get-log/' + name, this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.errorHandl),
      );
  }
  // GET
  public getByDateStart(name: Date): Observable<any> {
    return this.http.put(this.baseurl + '/get-dateStart/' + name, this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.errorHandl),
      );
  }
  // GET
  public getByDateEnd(name: Date): Observable<any> {
    return this.http.put(this.baseurl + '/get-dateEnd/' + name, this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.errorHandl),
      );
  }

  // DELETE
  public delete(id: number) {
    return this.http.delete(this.baseurl + '/delete/' + id, this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.errorHandl),
      );
  }

  // Error handling
  public errorHandl(error: any) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      // Get client-side error
      errorMessage = error.error.message;
    } else {
      // Get server-side error
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    return throwError(errorMessage);
  }
}
