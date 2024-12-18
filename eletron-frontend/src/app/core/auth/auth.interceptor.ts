import { HttpErrorResponse, HttpHandlerFn, HttpInterceptorFn, HttpRequest } from '@angular/common/http';
import { inject } from '@angular/core';
import { UtilitiesService } from '../../utils/services/utilities.service';
import { tap } from 'rxjs';
import { ToastService } from '../../utils/services/toast.service';
import { AuthService } from '../services/auth.service';

export const authInterceptor: HttpInterceptorFn = (req: HttpRequest<unknown>, next: HttpHandlerFn) => {
  const utilities = inject(UtilitiesService);
  const toastService = inject(ToastService)
  const authService = inject(AuthService)

  const usuario = authService.getUserFromCookie();
  utilities.setLoading(true);

  if (usuario) {
    req = req.clone({
      setHeaders: { Authorization: `Bearer ${usuario.token}` }
    });
  }

  return next(req).pipe(
    tap({
      next: () => {
        utilities.setLoading(false);
      },
      error: (error: HttpErrorResponse) => {
        utilities.setLoading(false);
        if (error.status === 403) {
          toastService.send({
            severity: "error",
            summary: "Acesso negado",
            detail: "Você não tem permissão para acessar esse recurso."
          });
        }
      }
    })
  )
}