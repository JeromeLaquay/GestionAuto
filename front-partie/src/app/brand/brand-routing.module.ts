import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ModelModule } from '../model/model.module';
import { BrandCreatorComponent } from './brand-creator/brand-creator.component';
import { BrandEditorComponent } from './brand-editor/brand-editor.component';
import { BrandListeComponent } from './brand-liste/brand-liste.component';


const routes: Routes = [
  {
    path: '',
    component: BrandListeComponent
  },
  {
    path: 'new',
    component: BrandCreatorComponent
  },
  {
    path: ':id',
    component: BrandEditorComponent
  }
];

@NgModule({
  // et ici forChild pour les routings secondaire
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BrandRoutingModule { }