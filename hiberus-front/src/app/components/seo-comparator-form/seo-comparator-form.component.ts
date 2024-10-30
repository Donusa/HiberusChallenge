import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { SeoService } from '../../services/seo.service';
import { MetricsDTO } from '../../interfaces/metrics-dto.model';

@Component({
  selector: 'app-seo-comparator-form',
  templateUrl: './seo-comparator-form.component.html',
  styleUrls: ['./seo-comparator-form.component.css']
})
export class SeoComparatorFormComponent {
  seoForm: FormGroup; 
  seoMetrics: MetricsDTO | undefined;
  currentMetric: MetricsDTO |undefined;
  isModalOpen: boolean = false; 
  isLoading: boolean = false;
  errorMessage: string | undefined; 


  constructor(
    private fb: FormBuilder,
    private seoService: SeoService,
  ) {
    this.seoForm = this.fb.group({
      site: ['', Validators.required],
      strategy: ['desktop']
    });
  }
  
  onSubmit() {
    if (this.seoForm.valid) {
      const { site, strategy } = this.seoForm.value;
      
      this.seoForm.reset({
        site: '',
        strategy: 'desktop'
      });
      this.isModalOpen = true;
      this.isLoading = true;
      this.errorMessage = undefined;

      this.seoService.postSeoData(site, strategy).subscribe(
        (metrics: MetricsDTO) => {
          this.seoMetrics = metrics;
          this.currentMetric = metrics;
          this.isLoading = false;
          this.seoService.getHistory().subscribe();
        },
        error => {
          this.isLoading = false;
          this.currentMetric = undefined;
          this.errorMessage = 'Ocurrió un error al obtener los datos de SEO.<br> Por favor, compruebe que los datos sean correctos e inténtelo de nuevo.';
        }
      );
    }
  }
  
  onStrategyChange(event: Event) {
    const currentStrategy = this.seoForm.get('strategy')?.value;
    this.seoForm.get('strategy')?.setValue(currentStrategy === 'mobile' ? 'desktop' : 'mobile');
  }
  
  closeModal() {
      this.isModalOpen = false;
      this.currentMetric = undefined;
      this.isLoading = false;
  }


}
