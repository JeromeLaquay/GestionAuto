import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ModelService } from 'src/app/services/model.service';
import { BrandService } from 'src/app/services/brand.service';
import { Model } from '../../interfaces/model';
import { Brand } from '../../interfaces/brand';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-model-creator',
  templateUrl: './model-creator.component.html',
  styleUrls: ['./model-creator.component.css']
})
export class ModelCreatorComponent implements OnInit {

  public model: Model;
  public formGroup: FormGroup;
  public brands: Brand[] = [];
  constructor(private modelService: ModelService, private router: Router, private actRoute: ActivatedRoute, private fb: FormBuilder, private brandService: BrandService) { }

  ngOnInit(): void {
    this.formGroup = new FormGroup({
      name: new FormControl('', Validators.required),
      brandId: new FormControl('', Validators.required),
      imagePath: new FormControl('', Validators.required)
    });  
    this.getAllBrands();
  }

  onSubmit(){
    console.log(this.formGroup.value);
    this.modelService.create(this.formGroup.value)
    .subscribe(
      (data: Model) => {
        this.router.navigate(['models']);
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

}
