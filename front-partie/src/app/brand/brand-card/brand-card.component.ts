import { Component, Input, OnInit } from '@angular/core';
import { Brand } from 'src/app/interfaces/brand';

@Component({
  selector: 'app-brand-card',
  templateUrl: './brand-card.component.html',
  styleUrls: ['./brand-card.component.css']
})
export class BrandCardComponent implements OnInit {

  @Input() brand: Brand;

  constructor() { }

  ngOnInit(): void {
  }

}
