import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ModelService {

  urlModel = '/models'

  constructor(private http: HttpClient) { }

  getAllModels(){
    return this.http.get(environment.urlApi + this.urlModel);
  }
}
