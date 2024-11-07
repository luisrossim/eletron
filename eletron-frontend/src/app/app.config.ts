import { ApplicationConfig } from '@angular/core';
import { provideRouter } from '@angular/router';
import { provideAnimations } from "@angular/platform-browser/animations";
import { PrimeNGConfig } from 'primeng/api';

import { routes } from './app.routes';

export const appConfig: ApplicationConfig = {
  providers: [
    provideAnimations(),
    provideRouter(routes),
    {
      provide: PrimeNGConfig,
      useFactory: () => {
        const config = new PrimeNGConfig();
        config.ripple = true;
        return config;
      },
    },
  ]
};