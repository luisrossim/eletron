import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { CanActivateFn } from '@angular/router';
import { AuthService } from '../services/auth.service';

export const authGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);
  const authService = inject(AuthService);

  const user = authService.getUserFromCookie();

  if (!user) {
    router.navigate(['/login']);
    return false;
  }

  return authService.validateToken().toPromise().then(
    () => router.navigate(['/sistema/painel']),
    () => {
      authService.removeUserCookies();
      router.navigate(['/login']);
      return false;
    }
  );
};
