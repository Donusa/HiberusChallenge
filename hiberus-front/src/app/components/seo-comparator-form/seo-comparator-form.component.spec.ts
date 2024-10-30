import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SeoComparatorFormComponent } from './seo-comparator-form.component';

describe('SeoComparatorFormComponent', () => {
  let component: SeoComparatorFormComponent;
  let fixture: ComponentFixture<SeoComparatorFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SeoComparatorFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SeoComparatorFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
