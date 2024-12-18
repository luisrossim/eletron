import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { CanActivateFn } from '@angular/router';
import { AuthService } from '../services/auth.service';

export const authGuard: CanActivateFn = async (route, state) => {
  const router = inject(Router);
  const authService = inject(AuthService);

  const user = authService.getUserFromCookie();

  if (!user) {
    await router.navigate(['/login']);
    return false;
  }

  try {
    await authService.validateToken().toPromise();
    return true;
  } catch {
    authService.removeUserCookies();
    await router.navigate(['/login']);
    return false;
  }
};
