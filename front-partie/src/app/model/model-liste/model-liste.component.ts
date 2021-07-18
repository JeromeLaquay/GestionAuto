import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ModelService } from 'src/app/services/model.service';
import { Model } from '../../interfaces/model';
import { BrandService } from 'src/app/services/brand.service';
import { Brand } from '../../interfaces/brand';

@Component({
  selector: 'app-model-liste',
  templateUrl: './model-liste.component.html',
  styleUrls: ['./model-liste.component.css']
})
export class ModelListeComponent implements OnInit {

  public models: Model[] = [];
  public brands: Brand[] = [];
  constructor(private modelService: ModelService, private brandService: BrandService, private router: Router) { }

  ngOnInit(): void {
    this.getAllBrands();
    this.getAllModels();
  }

  getAllModels(){
    this.modelService.getAll().subscribe((modelsData: Model[]) => {
      this.models = modelsData;
    })
  }

  goToNewModel(){
    this.router.navigate(['models/new']);
  }

  goToModifyModel(id: number){
    this.router.navigate(['models/'+ id]);
  }

  getAllBrands(){
    this.brandService.getAll().subscribe((brandsData: Brand[]) => {
      this.brands = brandsData;
    })
  }

  getModelsByBrand(brandId: number){
    this.modelService.getByBrand(brandId).subscribe((data: Model[]) => {
      this.models = data;
    })
  }

  selectBrandChange(brandId: number){
    if(!brandId){
      this.getAllModels();
    }else{
      this.getModelsByBrand(brandId);
    }
  }
}
