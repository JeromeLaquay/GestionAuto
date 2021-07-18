import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BrandCreatorComponent } from './brand-creator.component';

describe('BrandCreatorComponent', () => {
  let component: BrandCreatorComponent;
  let fixture: ComponentFixture<BrandCreatorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BrandCreatorComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BrandCreatorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
