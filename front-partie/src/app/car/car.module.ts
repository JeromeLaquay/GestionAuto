import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CarRoutingModule } from './car-routing.module';
import { CarListeComponent } from './car-liste/car-liste.component';
import { CarCardComponent } from './car-card/car-card.component';
import { NewCarComponent } from './new-car/new-car.component';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
  // declaration des composants, directives, pipes... du module
  declarations: [
    CarListeComponent,
    CarCardComponent,
    NewCarComponent
  ],
  // import nécessaire au module pour fonctionner
  imports: [
    CommonModule,
    CarRoutingModule,
    ReactiveFormsModule
  ],
  // pas utilisé dans notre exercice mais permet par exemple de rendre accessible un service ou une directive par exemple
  // en dehors du module
  exports : []
})
export class CarModule { }
