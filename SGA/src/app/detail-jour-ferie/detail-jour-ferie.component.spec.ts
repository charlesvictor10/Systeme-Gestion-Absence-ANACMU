import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailJourFerieComponent } from './detail-jour-ferie.component';

describe('DetailJourFerieComponent', () => {
  let component: DetailJourFerieComponent;
  let fixture: ComponentFixture<DetailJourFerieComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DetailJourFerieComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailJourFerieComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
