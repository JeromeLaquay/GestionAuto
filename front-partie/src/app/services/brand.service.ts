import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Brand } from '../interfaces/brand';

@Injectable({
  providedIn: 'root'
})
export class BrandService {

  urlBrand = '/brands/'

  constructor(private http: HttpClient) { }

  getAll(){
    return this.http.get(environment.urlApi + this.urlBrand);
  }

  create(brand: Brand){
    return this.http.post(environment.urlApi + this.urlBrand, brand);
  }

  modify(brand: Brand){
    return this.http.put(environment.urlApi + this.urlBrand, brand);
  }

  getById(id: number){
    return this.http.get(environment.urlApi + this.urlBrand +id);
  }

  delete(id: number){
    return this.http.delete(environment.urlApi + this.urlBrand +id);
  }
}
