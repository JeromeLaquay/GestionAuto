import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModelListeComponent } from './model-liste.component';

describe('ModelListeComponent', () => {
  let component: ModelListeComponent;
  let fixture: ComponentFixture<ModelListeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModelListeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ModelListeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
