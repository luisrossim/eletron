import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { PainelComponent } from './pages/painel/painel.component';
import { StatusComponent } from './pages/status/status.component';

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
            { path: 'painel', component: PainelComponent }
        ]
    },
    { path: '**', redirectTo: '', pathMatch: 'full' },
];
