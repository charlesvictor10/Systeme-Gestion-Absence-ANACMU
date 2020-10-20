import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailCongeNonDeductibleComponent } from './detail-conge-non-deductible.component';

describe('DetailCongeNonDeductibleComponent', () => {
  let component: DetailCongeNonDeductibleComponent;
  let fixture: ComponentFixture<DetailCongeNonDeductibleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DetailCongeNonDeductibleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailCongeNonDeductibleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
