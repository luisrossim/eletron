import { HttpErrorResponse, HttpHandlerFn, HttpInterceptorFn, HttpRequest } from '@angular/common/http';
import { inject } from '@angular/core';
import { UtilitiesService } from '../../utils/services/utilities.service';
import { catchError, finalize, Observable, tap } from 'rxjs';
import { ToastService } from '../../utils/services/toast.service';

export const authInterceptor: HttpInterceptorFn = (req: HttpRequest<unknown>, next: HttpHandlerFn) => {
  const utilities = inject(UtilitiesService);
  const toastService = inject(ToastService)
  const token: string = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJlbGV0cm9uLWFwaSIsInN1YiI6Imx1aXNlbGV0cm8iLCJleHAiOjE3MzExMDk5MDV9.WH3hxmQyRWYRkhIulC-n51_4J5tab1bbSSg2aBckO3M';
  utilities.setLoading(true);

  if (token && token != '') {
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