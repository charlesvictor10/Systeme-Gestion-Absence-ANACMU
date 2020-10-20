import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddJourFerieComponent } from './add-jour-ferie.component';

describe('AddJourFerieComponent', () => {
  let component: AddJourFerieComponent;
  let fixture: ComponentFixture<AddJourFerieComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddJourFerieComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddJourFerieComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
