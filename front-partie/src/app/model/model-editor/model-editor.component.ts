import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ModelService } from 'src/app/services/model.service';
import { BrandService } from 'src/app/services/brand.service';
import { Model } from '../../interfaces/model';
import { Brand } from '../../interfaces/brand';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-model-editor',
  templateUrl: './model-editor.component.html',
  styleUrls: ['./model-editor.component.css']
})
export class ModelEditorComponent implements OnInit {

  public model: Model;
  public formGroup: FormGroup;
  public brands: Brand[] = [];
  private id: number;
  constructor(private modelService: ModelService, private router: Router, private actRoute: ActivatedRoute, private fb: FormBuilder, private brandService: BrandService) { }

  ngOnInit(): void {
    this.id = this.actRoute.snapshot.params.id;
    this.getById(this.id);
    this.getAllBrands();
  }

  onSubmit(){
    this.modelService.modify(this.id, this.formGroup.value)
    .subscribe(
      (data: Model) => {
        this.router.navigate(['models']);
      },
      (error) => {
        console.log('erreur : ' + error)
      }
    )
  }

  getById(id: number){
    this.modelService.getById(id).subscribe((data: Model) => {
      this.model = data;
      this.formGroup = this.fb.group({
        name: new FormControl(this.model.name),
        brandId: new FormControl(this.model.brandId)
      })
    })
  }

  getAllBrands(){
    this.brandService.getAll().subscribe((brandsData: Brand[]) => {
      this.brands = brandsData;
    })
  }

  delete(){
    this.modelService.delete(this.id)
    .subscribe(
      (res) => {
        this.router.navigate(['models']);
      },
      (error) => {
        console.log('erreur : ' + error)
      }
    );
  }
}
