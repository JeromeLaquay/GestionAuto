import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'front-partie';

  constructor(private router: Router){

  }

  goToCars(){
    this.router.navigate(['/cars']);
  }

  goToModels(){
    this.router.navigate(['/models']);
  }

  goToBrands(){
    this.router.navigate(['/brands']);
  }
}
