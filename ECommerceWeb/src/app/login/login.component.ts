import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../services/auth/auth.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { UserStorageService } from '../services/storage/user-storage.service';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss']
})
export class LoginComponent {

    loginForm!: FormGroup;
    hidePassword = true;

    constructor(
        private fb: FormBuilder,
        private authService: AuthService,
        private snackBar: MatSnackBar,
        private router: Router
    ) { }

    ngOnInit(): void {
        this.loginForm = this.fb.group({
            email: [null, [Validators.required]],
            password: [null, [Validators.required]]
        })
    }

    togglePasswordVisibility() {
        this.hidePassword = !this.hidePassword;
    }

    onSubmit(): void {
        const userName = this.loginForm.get('email')!.value;
        const password = this.loginForm.get('password')!.value;

        this.authService.login(userName, password).subscribe(
            (res) => {
                if (UserStorageService.isAdminLoggedIn()) {
                    this.router.navigateByUrl('admin/dashboard');
                }
                else if (UserStorageService.isCustomerLoggedIn()) {
                    this.router.navigateByUrl('customer/dashboard');
                }
                this.snackBar.open('Login Successfull', 'OK', { duration: 5000 });
            },
            (err) => {
                this.snackBar.open('Bad Credentials', 'Close', { duration: 5000 });
            }
        )
    }
}