import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditJourFerieComponent } from './edit-jour-ferie.component';

describe('EditJourFerieComponent', () => {
  let component: EditJourFerieComponent;
  let fixture: ComponentFixture<EditJourFerieComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditJourFerieComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditJourFerieComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
