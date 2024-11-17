import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { Observable } from 'rxjs';
import { LoginRequest, LoginResponse } from '../../models/auth';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
    private readonly http = inject(HttpClient);
    private readonly url: string = environment.url + 'auth/login';

    public login(resource: LoginRequest): Observable<LoginResponse> {
        return this.http.post<LoginResponse>(`${this.url}`, resource);
    }
}
