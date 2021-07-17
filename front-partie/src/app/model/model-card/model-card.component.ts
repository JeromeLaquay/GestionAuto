import { Component, Input, OnInit } from '@angular/core';
import { Model } from 'src/app/interfaces/model';

@Component({
  selector: 'app-model-card',
  templateUrl: './model-card.component.html',
  styleUrls: ['./model-card.component.css']
})
export class ModelCardComponent implements OnInit {

  @Input() model: Model;
  constructor() { }

  ngOnInit(): void {
  }

}
