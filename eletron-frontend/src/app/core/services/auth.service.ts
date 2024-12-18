import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from '../../../environments/environment';
import { Observable } from 'rxjs';
import { CookieService } from 'ngx-cookie-service';
import { LoginRequest, LoginResponse } from '../../models/auth';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
    private readonly cookies = inject(CookieService);
    private readonly http = inject(HttpClient);
    private readonly router = inject(Router);
    private readonly url: string = environment.url + 'auth';

    public login(resource: LoginRequest): Observable<LoginResponse> {
        return this.http.post<LoginResponse>(`${this.url}/login`, resource);
    }

    public validateToken(): Observable<any> {
      return this.http.get<any>(`${this.url}/validate-token`);
    }

    public restoreSession() {
      const user = this.getUserFromCookie();
      if (user) {
        this.validateToken().subscribe({
          next: () => {
            this.router.navigate(['/sistema/painel']);
          },
          error: () => {
            this.removeUserCookies();
            this.router.navigate(['/sistema']);
          },
        });
      }
    }

    setUserCookie(user: LoginResponse): void {
      const userString = JSON.stringify(user);
      this.cookies.set('userData', userString, { expires: 1 });
    }
  
    getUserFromCookie(): LoginResponse | null {
      const userString = this.cookies.get('userData');
      return userString ? JSON.parse(userString) : null;
    }
  
    removeUserCookies(): void {
      this.cookies.delete('userData');
    }
}
