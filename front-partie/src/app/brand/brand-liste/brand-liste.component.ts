import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BrandService } from 'src/app/services/brand.service';
import { Brand } from '../../interfaces/brand';

@Component({
  selector: 'app-brand-liste',
  templateUrl: './brand-liste.component.html',
  styleUrls: ['./brand-liste.component.css']
})
export class BrandListeComponent implements OnInit {

  public brands: Brand[] = [];

  constructor(private brandService: BrandService, private router: Router) { }

  ngOnInit(): void {
    this.getAllBrands();
  }

  getAllBrands(){
    this.brandService.getAllBrands().subscribe((brandsData: Brand[]) => {
      this.brands = brandsData;
    })
  }

  goToModel(idBrand: number){
    this.router.navigate(['models/'+idBrand]);
  }



}
