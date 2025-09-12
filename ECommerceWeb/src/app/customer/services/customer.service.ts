import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserStorageService } from 'src/app/services/storage/user-storage.service';

const BASIC_URL = "http://localhost:8080";

@Injectable({
    providedIn: 'root'
})
export class CustomerService {

    constructor(
        private httpClient: HttpClient
    ) { }

    getAllProducts(): Observable<any> {
        return this.httpClient.get(`${BASIC_URL}/api/customer/products`, {
            headers: this.createAuthorizationHeader()
        });
    }

    getAllProductsByName(name: any): Observable<any> {
        return this.httpClient.get(`${BASIC_URL}/api/customer/search/${name}`, {
            headers: this.createAuthorizationHeader()
        });
    }

    private createAuthorizationHeader(): HttpHeaders {
        return new HttpHeaders().set(
            'Authorization', 'Bearer ' + UserStorageService.getToken()
        )
    }
}
