import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CarListeComponent } from './car-liste/car-liste.component';
import { NewCarComponent } from './new-car/new-car.component';


const routes: Routes = [
  {
    path: '',
    component: CarListeComponent
  },
  {
    path: 'new',
    component: NewCarComponent
  }
];

@NgModule({
  // et ici forChild pour les routings secondaire
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CarRoutingModule { }