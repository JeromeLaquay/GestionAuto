import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CarService } from 'src/app/services/car.service';
import { Car } from '../../interfaces/car';

@Component({
  selector: 'app-car-liste',
  templateUrl: './car-liste.component.html',
  styleUrls: ['./car-liste.component.css']
})
export class CarListeComponent implements OnInit {

  public cars: Car[] = [];

  constructor(private carService: CarService, private router: Router) { }

  ngOnInit(): void {
    this.getAllCars();
  }

  getAllCars(){
    this.carService.getAll().subscribe((carsData: Car[]) => {
      this.cars = carsData;
    })
  }

  goToModifyCar(id: number){
    this.router.navigate(['/cars/'+id]);
  }

  goToNewCar(){
    this.router.navigate(['/cars/new']);
  }

}
