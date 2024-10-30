import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, BehaviorSubject, tap } from 'rxjs';
import { MetricsDTO } from '../interfaces/metrics-dto.model';

@Injectable({
  providedIn: 'root'
})
export class SeoService {
  private apiUrl = "http://localhost:8080/api/seo";

  private seoHistorySubject: BehaviorSubject<MetricsDTO[]> = new BehaviorSubject<MetricsDTO[]>([]);
  seoHistory$: Observable<MetricsDTO[]> = this.seoHistorySubject.asObservable();

  constructor(private http: HttpClient) { }

  getHistory(): Observable<MetricsDTO[]> {
    return this.http.get<MetricsDTO[]>(`${this.apiUrl}/history`).pipe(
      tap((data: MetricsDTO[]) => this.seoHistorySubject.next(data))
    );
  }

  postSeoData(url: string, strategy: string): Observable<MetricsDTO> {
    const constructedUrl = `${this.apiUrl}/stats?url=${encodeURIComponent(url)}&strategy=${encodeURIComponent(strategy)}`;
    return this.http.post<MetricsDTO>(constructedUrl,{});
  }

}
