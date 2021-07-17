import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CarService {

  urlCar = '/cars/'

  constructor(private http: HttpClient) { }

  getAllCars(){
    return this.http.get(environment.urlApi + this.urlCar);
  }

  getById(id: number){
    return this.http.get(environment.urlApi + this.urlCar + id);
  }
}
