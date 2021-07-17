import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BrandListeComponent } from './brand-liste.component';

describe('BrandListeComponent', () => {
  let component: BrandListeComponent;
  let fixture: ComponentFixture<BrandListeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BrandListeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BrandListeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
