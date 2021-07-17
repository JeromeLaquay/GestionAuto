import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CarService } from 'src/app/services/car.service';
import { Car } from '../../interfaces/car';
import { FormGroup,  FormBuilder,  Validators } from '@angular/forms';

@Component({
  selector: 'app-new-car',
  templateUrl: './new-car.component.html',
  styleUrls: ['./new-car.component.css']
})
export class NewCarComponent implements OnInit {

  public car: Car;
  private id: number;
  public formGroup : FormGroup;

  constructor(private carService: CarService, private router: Router, private actRoute: ActivatedRoute, private fb: FormBuilder) { }

  ngOnInit(): void {
    this.id = this.actRoute.snapshot.params.id;
    this.getCarById(this.id);
  }

  getCarById(id: number){
    this.carService.getById(id).subscribe((carData: Car) => {
      this.car = carData;
      this.formGroup = this.fb.group({
        color: [this.car.color],
        year: [this.car.year],
        imagePath: [this.car.imagePath]
      })
    })
  }

  saveCar(){

  }

  deleteCar(){

  }

}
