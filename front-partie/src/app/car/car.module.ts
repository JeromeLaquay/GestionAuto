import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CarRoutingModule } from './car-routing.module';
import { CarListeComponent } from './car-liste/car-liste.component';
import { CarCardComponent } from './car-card/car-card.component';
import { NewCarComponent } from './new-car/new-car.component';
import { ReactiveFormsModule } from '@angular/forms';
import { CarCreatorComponent } from './car-creator/car-creator.component';
import { CarEditorComponent } from './car-editor/car-editor.component';

@NgModule({
  // declaration des composants, directives, pipes... du module
  declarations: [
    CarListeComponent,
    CarCardComponent,
    NewCarComponent,
    CarCreatorComponent,
    CarEditorComponent
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
