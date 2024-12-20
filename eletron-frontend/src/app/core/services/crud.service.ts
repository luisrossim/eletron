import { HttpClient, HttpParams } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';

export class CrudService<T> {
  private readonly http = inject(HttpClient);
  private readonly apiUrl: string;
  
  constructor(
    protected readonly entityName: string,
    protected readonly baseUrl: string = environment.url
  ) {
    this.apiUrl = `${this.baseUrl}${this.entityName}`;
  }

  public create(resource: Partial<T>): Observable<T> {
    return this.http.post<T>(`${this.apiUrl}`, resource);
  }

  public getAll(query?: { [key: string]: string }): Observable<T[]> {
    const params = new HttpParams({ fromObject: query });
    return this.http.get<T[]>(this.apiUrl, { params });
  }

  public getById(id: number | string): Observable<T> {
    return this.http.get<T>(`${this.apiUrl}/${id}`);
  }

  public update(resource: Partial<T>): Observable<T> {
    return this.http.put<T>(`${this.apiUrl}`, resource);
  }

  public delete(id: number | string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}