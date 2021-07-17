import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ModelModule } from '../model/model.module';
import { BrandListeComponent } from './brand-liste/brand-liste.component';


const routes: Routes = [
  {
    path: '',
    component: BrandListeComponent
  },
  {
    path: 'models/:id',
    component: ModelModule
  }
];

@NgModule({
  // et ici forChild pour les routings secondaire
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BrandRoutingModule { }