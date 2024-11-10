import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { PainelComponent } from './components/painel/painel.component';

export const routes: Routes = [
    {
        path: '',
        component: HomeComponent
    },
    {
        path: 'sistema',
        component: LoginComponent,
        children: [
            { path: 'painel', component: PainelComponent }
        ]
    },
    { path: '**', redirectTo: '', pathMatch: 'full' },
];
