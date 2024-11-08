import { HttpErrorResponse, HttpHandlerFn, HttpInterceptorFn, HttpRequest } from '@angular/common/http';
import { inject } from '@angular/core';
import { UtilitiesService } from '../services/utilities.service';
import { catchError, finalize, Observable, tap } from 'rxjs';
import { ToastService } from '../services/toast.service';

export const authInterceptor: HttpInterceptorFn = (req: HttpRequest<unknown>, next: HttpHandlerFn) => {
  const utilities = inject(UtilitiesService);
  const toastService = inject(ToastService)
  const token = '23094343';
  utilities.setLoading(true);

  if (token) {
    req = req.clone({
      setHeaders: { Authorization: `Bearer ${token}` }
    });
  }

  return next(req).pipe(
    tap({
      next: () => {
        utilities.setLoading(false);
      },
      error: (error: HttpErrorResponse) => {
        utilities.setLoading(false);
        toastService.send({
          severity: "error",
          summary: "Erro na Requisição",
          detail: `${ error.message }`
        });
      }
    })
  )
}