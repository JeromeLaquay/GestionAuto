import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CarService } from 'src/app/services/car.service';
import { BrandService } from 'src/app/services/brand.service';
import { ModelService } from 'src/app/services/model.service';
import { Car } from '../../interfaces/car';
import { Model } from '../../interfaces/model';
import { Brand } from '../../interfaces/brand';

@Component({
  selector: 'app-car-liste',
  templateUrl: './car-liste.component.html',
  styleUrls: ['./car-liste.component.css']
})
export class CarListeComponent implements OnInit {

  private cars: Car[] = [];
  public brands: Brand[] = [];
  private models: Model[] = [];
  public modelsFiltered: Model[] = [];
  public carsFiltered:  Car[] = [];
  constructor(private carService: CarService, private modelService: ModelService,private brandService: BrandService, private router: Router) { }

  ngOnInit(): void {
    this.getAllCars();
    this.getAllModels();
    this.getAllBrands();
  }

  getAllCars(){
    this.carService.getAll().subscribe((data: Car[]) => {
      data.forEach(car => {
        this.getModelById(car);
      })
      this.cars = data;
      this.carsFiltered = this.cars;
    })
  }

  goToModifyCar(id: number){
    this.router.navigate(['/cars/'+id]);
  }

  goToNewCar(){
    this.router.navigate(['/cars/new']);
  }

  getModelById(car: Car){
    this.modelService.getById(car.modelId).subscribe((data: Model) => {
      this.getBrandById(data);
      car.model = data;
    })
  }

  getBrandById(model: Model){
    this.brandService.getById(model.brandId).subscribe((data: Brand) => {
      model.brand = data;
    })
  }

   selectBrandChange(name: string){
      this.carsFiltered = this.cars.filter(car => car.model.brand.name.includes(name));
      this.modelsFiltered = this.models.filter(model => model.brand.name.includes(name));
  }

  selectModelChange(name: string){
    this.carsFiltered = this.cars.filter(car => car.model.name.includes(name));
  }

   getAllModels(){
    this.modelService.getAll().subscribe((data: Model[]) => {
      data.forEach(model => {
        this.getBrandById(model);
      })
      this.models = data;
      this.modelsFiltered = data;
    })
  }

  getAllBrands(){
    this.brandService.getAll().subscribe((brandsData: Brand[]) => {
      this.brands = brandsData;
    })
  }

}
