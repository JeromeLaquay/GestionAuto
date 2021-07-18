import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Car } from '../interfaces/car';

@Injectable({
  providedIn: 'root'
})
export class CarService {

  urlCar = '/cars/'

  constructor(private http: HttpClient) { }

  getAll(){
    return this.http.get(environment.urlApi + this.urlCar);
  }

  create(car: Car){
    return this.http.post(environment.urlApi + this.urlCar, car);
  }

  modify(car: Car){
    return this.http.put(environment.urlApi + this.urlCar, car);
  }

  getById(id: number){
    return this.http.get(environment.urlApi + this.urlCar +id);
  }

  delete(id: number){
    this.http.delete(environment.urlApi + this.urlCar +id);
  }
}
