import { Component } from '@angular/core';
import { AdminService } from '../../service/admin.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
    selector: 'app-dashboard',
    templateUrl: './dashboard.component.html',
    styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent {

    products: any[] = [];
    searchProductForm: FormGroup;

    constructor(
        private adminService: AdminService,
        private fb: FormBuilder,
        private snackBar: MatSnackBar
    ) { }

    ngOnInit() {
        this.searchProductForm = this.fb.group({
            title: [null, [Validators.required]]
        })
        this.getAllProducts();
    }

    getAllProducts() {
        this.products = [];
        this.adminService.getAllProducts().subscribe(res => {
            res.forEach(ele => {
                ele.processedImg = 'data:image/jpeg;base64,' + ele.byteImage;
                this.products.push(ele);
            });
        })
    }

    submitForm() {
        this.products = [];
        const title = this.searchProductForm.get('title')!.value;
        this.adminService.getAllProductsByName(title).subscribe(res => {
            res.forEach(ele => {
                ele.processedImg = 'data:image/jpeg;base64,' + ele.byteImage;
                this.products.push(ele);
            });
        })
    }

    deleteProduct(productId: any) {
        this.adminService.deleteProduct(productId).subscribe({
            next: () => {
                this.snackBar.open('Product deleted successfully', 'Close', { duration: 5000 });
                this.getAllProducts();
            },
            error: (err) => {
                const errorMessage = err?.error?.message || 'Failed to delete product';
                this.snackBar.open(errorMessage, 'Close', { duration: 5000, panelClass: 'error-snackbar' });
            }
        });
    }

}
