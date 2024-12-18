import { Routes } from '@angular/router';
import { HomeComponent } from './pages/public/home/home.component';
import { LoginComponent } from './pages/public/login/login.component';
import { PainelComponent } from './pages/private/painel/painel.component';
import { StatusComponent } from './pages/public/status/status.component';
import { authGuard } from './core/guards/auth.guard';

export const routes: Routes = [
    {
        path: '',
        component: HomeComponent
    },
    {
        path: 'status',
        component: StatusComponent
    },
    {
        path: 'sistema',
        children: [
            { path: '', component: LoginComponent },
            { path: 'painel', canActivate: [authGuard], component: PainelComponent }
        ]
    },
    { path: '**', redirectTo: '', pathMatch: 'full' },
];
