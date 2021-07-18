import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CarCreatorComponent } from './car-creator.component';

describe('CarCreatorComponent', () => {
  let component: CarCreatorComponent;
  let fixture: ComponentFixture<CarCreatorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CarCreatorComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CarCreatorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
