import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CarListeComponent } from './car-liste.component';

describe('CarListeComponent', () => {
  let component: CarListeComponent;
  let fixture: ComponentFixture<CarListeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CarListeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CarListeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
