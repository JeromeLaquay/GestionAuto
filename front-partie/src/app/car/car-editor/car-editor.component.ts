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
  selector: 'app-car-editor',
  templateUrl: './car-editor.component.html',
  styleUrls: ['./car-editor.component.css']
})
export class CarEditorComponent implements OnInit {

  public car: Car;
  public formGroup: FormGroup;
  public years = [1990,1991,1992,1993];
  public colors = ['bleu','jaune','vert','noir'];
  public models: Model[] = [];
  public brands: Brand[] = [];
  private id: number;
  public brandSelected: Brand;
  constructor(private carService: CarService, private modelService: ModelService,private brandService: BrandService,private router: Router, private actRoute: ActivatedRoute, private fb: FormBuilder) { }

  ngOnInit(): void {
    this.id = this.actRoute.snapshot.params.id;
    this.formGroup = new FormGroup({
      color: new FormControl('', Validators.required),
      year: new FormControl('', Validators.required),
      mileage: new FormControl('', Validators.required),
      owner: new FormControl(''),
      brandId: new FormControl(''),
      modelId: new FormControl('', Validators.required)
    });
    this.getById(this.id);
    this.getAllBrands();
  }

  onSubmit(){
    console.log(this.formGroup.value);
    this.carService.modify(this.id,this.formGroup.value)
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

  getById(id: number){
    this.carService.getById(id).subscribe((data: Car) => {
      this.car = data;
      this.getModelById();
    })
  }

  getModelById(){
    this.modelService.getById(this.car.modelId).subscribe((data: Model) => {
      this.car.model = data;
      this.getBrandById();
    })
  }

  getBrandById(){
    this.brandService.getById(this.car.model.brandId).subscribe((data: Brand) => {
      this.car.model.brand = data;
      this.brandSelected = this.car.model.brand;
      this.selectBrandChange(this.car.model.brand.id);
      this.formGroup = new FormGroup({
        color: new FormControl(this.car.color, Validators.required),
        year: new FormControl(this.car.year, Validators.required),
        mileage: new FormControl(this.car.mileage, Validators.required),
        owner: new FormControl(this.car.owner),
        brandId: new FormControl(this.car.model.brandId),
        modelId: new FormControl(this.car.modelId, Validators.required)
      });
    })
  }
}