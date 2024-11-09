import { ApplicationConfig } from '@angular/core';
import { provideRouter } from '@angular/router';
import { provideAnimations } from "@angular/platform-browser/animations";
import { PrimeNGConfig } from 'primeng/api';
import { authInterceptor } from "./core/auth/auth.interceptor";
import { routes } from './app.routes';
import { provideHttpClient, withInterceptors } from '@angular/common/http';

export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes),
    provideAnimations(),
    provideHttpClient(
      withInterceptors([authInterceptor])
    ),
    {
      provide: PrimeNGConfig,
      useFactory: () => {
        const config = new PrimeNGConfig();
        config.ripple = true;
        return config;
      },
    }
  ]
};