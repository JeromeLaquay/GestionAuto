import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class BrandService {

  urlModel = '/brands'

  constructor(private http: HttpClient) { }

  getAllBrands(){
    return this.http.get(environment.urlApi + this.urlModel);
  }

  get
}
