import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CustomerService } from '../../services/customer.service';
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
        private customerService: CustomerService,
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
        this.customerService.getAllProducts().subscribe(res => {
            res.forEach(ele => {
                ele.processedImg = 'data:image/jpeg;base64,' + ele.byteImage;
                this.products.push(ele);
            });
        })
    }

    submitForm() {
        this.products = [];
        const title = this.searchProductForm.get('title')!.value;
        this.customerService.getAllProductsByName(title).subscribe(res => {
            res.forEach(ele => {
                ele.processedImg = 'data:image/jpeg;base64,' + ele.byteImage;
                this.products.push(ele);
            });
        })
    }

    addToCart(productId: any) {
        
    }
}
