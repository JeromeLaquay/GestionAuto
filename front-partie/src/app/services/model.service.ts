import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Model } from '../interfaces/model';

@Injectable({
  providedIn: 'root'
})
export class ModelService {

  urlModel = '/models/'

  constructor(private http: HttpClient) { }

  getAll(){
    return this.http.get(environment.urlApi + this.urlModel);
  }

  create(model: Model){
    return this.http.post(environment.urlApi + this.urlModel, model);
  }

  modify(id: number,model: Model){
    return this.http.put(environment.urlApi + this.urlModel + id, model);
  }

  getById(id: number){
    return this.http.get(environment.urlApi + this.urlModel +id);
  }

  delete(id: number){
    return this.http.delete(environment.urlApi + this.urlModel +id);
  }

  getByBrand(brandId: number){
    return this.http.get(environment.urlApi + this.urlModel +'brands/'+brandId);
  }
}
