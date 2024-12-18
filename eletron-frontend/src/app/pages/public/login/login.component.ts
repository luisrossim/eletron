import { Component, inject, OnInit } from '@angular/core';
import { ReactiveFormsModule, FormBuilder, Validators } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { CardModule } from 'primeng/card';
import { InputGroupModule } from 'primeng/inputgroup';
import { InputGroupAddonModule } from 'primeng/inputgroupaddon';
import { Router } from '@angular/router';
import { InputTextModule } from 'primeng/inputtext';
import { PasswordModule } from 'primeng/password';
import { AuthService } from '../../../core/services/auth.service';
import { LoginRequest, LoginResponse } from '../../../models/auth';
import { ToastService } from '../../../utils/services/toast.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule, ButtonModule, CardModule, InputGroupModule, InputGroupAddonModule, InputTextModule, PasswordModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit {
  toastService = inject(ToastService);

  loginForm = this.formBuilder.group({
    login: ['', [Validators.required]],
    password: ['', [Validators.required, Validators.minLength(6)]]
  });


  constructor(private formBuilder: FormBuilder, private router: Router, private authService: AuthService) {}


  ngOnInit(): void {
    this.authService.restoreSession();
  }

  onSubmit(){
    if(this.loginForm && this.loginForm.valid){
      const loginValues = this.loginForm.value;
      
      const loginRequest: LoginRequest = {
        login: loginValues.login!,
        password: loginValues.password!
      };

      this.authService.login(loginRequest).subscribe({
        next: (res) => {
          this.actionsForSuccess(res);
        },
        error: (error) => {
          this.toastService.send({ severity: "error", summary: "Error", detail: error.error });
        }
      })
    }
  }

  actionsForSuccess(res: any) {
    const userLogged: LoginResponse = {
      token: res.token,
      usuario: { 
        nome: res.usuario.nome,
        login: res.usuario.login,
        role: res.usuario.role
      }
    };

    this.authService.setUserCookie(userLogged);
    this.router.navigate(['sistema/painel']);
  }
}
