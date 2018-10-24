import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {ComputerListingComponent} from './computer-listing.component';

describe('ComputerAddComponent', () => {
  let component: ComputerListingComponent;
  let fixture: ComponentFixture<ComputerListingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ComputerListingComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ComputerListingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
