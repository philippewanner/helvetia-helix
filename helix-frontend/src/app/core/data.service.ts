import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';
import { map, catchError } from 'rxjs/operators';

import { IClient, IInsurancePolicy } from '../../app/shared/interfaces';

@Injectable()
export class DataService {
  baseUrl: string = 'http://localhost:8081/api/v1';
  baseClientUrl: string = `${this.baseUrl}/client`;
  basePolicyUrl: string = `${this.baseUrl}/policy`;

  constructor(private http: HttpClient) {}

  /*
   * Client request
   */
  getClients(): Observable<IClient[]> {
    return this.http
      .get<IClient[]>(`${this.baseClientUrl}/`)
      .pipe(catchError(this.handleError));
  }

  getClient(id: string): Observable<IClient> {
    return this.http
      .get<IClient>(`${this.baseClientUrl}/${id}/`)
      .pipe(catchError(this.handleError));
  }

  addClient(firstname: string, lastname: string): Observable<void> {
    return this.http
      .post<any>(
        `${this.baseClientUrl}/?firstname=${firstname}&lastname=${lastname}`,
        null
      )
      .pipe(catchError(this.handleError));
  }

  removeClient(id: string): Observable<void> {
    return this.http
      .delete<any>(`${this.baseClientUrl}/${id}/`)
      .pipe(catchError(this.handleError));
  }

  addInsurancePolicyTo(clientId: string, policyId: string): Observable<void> {
    return this.http
      .post<any>(`${this.baseClientUrl}/${clientId}/policy/${policyId}`, null)
      .pipe(catchError(this.handleError));
  }

  removeInsurancePolicyTo(
    clientId: string,
    policyId: string
  ): Observable<void> {
    return this.http
      .delete<any>(`${this.baseClientUrl}/${clientId}/policy/${policyId}`)
      .pipe(catchError(this.handleError));
  }

  /*
   * Insurance Policy request
   */
  getAllInsurancePolicy(): Observable<IInsurancePolicy[]> {
    return this.http
      .get<IInsurancePolicy[]>(`${this.basePolicyUrl}/`)
      .pipe(catchError(this.handleError));
  }

  getInsurancePolicy(id: string) {
    return this.http
      .get<IInsurancePolicy>(`${this.basePolicyUrl}/${id}`)
      .pipe(catchError(this.handleError));
  }

  addInsurancePolicy(name: string, price: number): Observable<void> {
    return this.http
      .post<any>(`${this.basePolicyUrl}/?name=${name}&price=${price}`, null)
      .pipe(catchError(this.handleError));
  }

  removeInsurancePolicy(id: string) {
    return this.http
      .delete<IInsurancePolicy>(`${this.basePolicyUrl}/${id}`)
      .pipe(catchError(this.handleError));
  }

  /*
   * Erreur handler
   */

  private handleError(error: any) {
    console.error('server error:', error);
    if (error.error instanceof Error) {
      const errMessage = error.error.message;
      return Observable.throw(errMessage);
      // Use the following instead if using lite-server
      // return Observable.throw(err.text() || 'backend server error');
    }
    return Observable.throw(error || 'Node.js server error');
  }
}
