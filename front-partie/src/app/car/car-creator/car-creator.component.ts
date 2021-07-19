import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CarService } from 'src/app/services/car.service';
import { BrandService } from 'src/app/services/brand.service';
import { ModelService } from 'src/app/services/model.service';
import { Car } from '../../interfaces/car';
import { Model } from '../../interfaces/model';
import { Brand } from '../../interfaces/brand';
import { FormGroup,  FormBuilder, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-car-creator',
  templateUrl: './car-creator.component.html',
  styleUrls: ['./car-creator.component.css']
})
export class CarCreatorComponent implements OnInit {

  public car: Car;
  public formGroup: FormGroup;
  public years = [1990,1991,1992,1993];
  public colors = ['bleu','jaune','vert','noir'];
  public models: Model[] = [];
  public brands: Brand[] = [];
  constructor(private carService: CarService, private modelService: ModelService,private brandService: BrandService,private router: Router, private actRoute: ActivatedRoute, private fb: FormBuilder) { }

  ngOnInit(): void {
    this.getAllBrands();
    this.formGroup = new FormGroup({
      color: new FormControl('', Validators.required),
      year: new FormControl('', Validators.required),
      mileage: new FormControl(0, Validators.required),
      owner: new FormControl(''),
      modelId: new FormControl('', Validators.required)
    });  
  }

  onSubmit(){
    console.log(this.formGroup.value);
    this.carService.create(this.formGroup.value)
    .subscribe(
      (data: Car) => {
        this.router.navigate(['cars']);
      },
      (error) => {
        console.log('erreur : ' + error)
      }
    )
  }

  getAllBrands(){
    this.brandService.getAll().subscribe((brandsData: Brand[]) => {
      this.brands = brandsData;
    })
  }

  getModelsByBrand(brandId: number){
    this.modelService.getByBrand(brandId).subscribe((data: Model[]) => {
     /** data.forEach( (model: Model) => {
       this.getBrandById(model);
     }) */
     this.models = data;
     })
   }

   selectBrandChange(brandId: number){
      this.getModelsByBrand(brandId);
  }
}
