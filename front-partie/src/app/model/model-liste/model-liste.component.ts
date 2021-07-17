import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ModelService } from 'src/app/services/model.service';
import { Model } from '../../interfaces/model';

@Component({
  selector: 'app-model-liste',
  templateUrl: './model-liste.component.html',
  styleUrls: ['./model-liste.component.css']
})
export class ModelListeComponent implements OnInit {

  public models: Model[] = [];

  constructor(private modelService: ModelService, private router: Router) { }

  ngOnInit(): void {
    this.getAllCars();
  }

  getAllCars(){
    this.modelService.getAllModels().subscribe((modelsData: Model[]) => {
      this.models = modelsData;
    })
  }

  goToNewCar(){
    this.router.navigate(['/cars/new'])
  }
}
