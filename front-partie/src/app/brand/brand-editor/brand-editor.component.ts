import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BrandService } from 'src/app/services/brand.service';
import { Brand } from '../../interfaces/brand';
import { FormGroup,  FormBuilder, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-brand-editor',
  templateUrl: './brand-editor.component.html',
  styleUrls: ['./brand-editor.component.css']
})
export class BrandEditorComponent implements OnInit {

  public brand: Brand;
  public formGroup: FormGroup;
  public brandCreated: Brand;
  private id: number;
  constructor(private brandService: BrandService, private router: Router, private actRoute: ActivatedRoute, private fb: FormBuilder) { }

  ngOnInit(): void {
    this.id = this.actRoute.snapshot.params.id;
    this.getById(this.id);
    this.formGroup = new FormGroup({
      name: new FormControl(''),
      logo: new FormControl('')
    });  
  }

  getById(id: number){
    this.brandService.getById(id).subscribe((data: Brand) => {
      this.brand = data;
      this.formGroup = this.fb.group({
        name: new FormControl(this.brand.name),
        logo: new FormControl(this.brand.logo)
      })
    })
  }

  onSubmit(){
    console.log(this.formGroup.value);
    this.brand = this.formGroup.value;
    this.brandService.modify(this.id,this.brand)
    .subscribe(
      (brandData: Brand) => {
        this.router.navigate(['brands']);
      },
      (error) => {
        console.log('erreur : ' + error)
      }
    )
  }

  delete(){
    this.brandService.delete(this.id)
    .subscribe(
      (res) => {
        this.router.navigate(['brands']);
      },
      (error) => {
        console.log('erreur : ' + error)
      }
    );
  }
}
