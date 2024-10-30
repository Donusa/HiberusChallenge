import { LiveAnnouncer } from '@angular/cdk/a11y';
import { AfterViewInit, Component, OnInit, ViewChild, inject } from '@angular/core';
import { MatSort, Sort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { MetricsDTO } from '../../interfaces/metrics-dto.model';
import { SeoService } from '../../services/seo.service';
import { MatPaginator } from '@angular/material/paginator';

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css'], 
})
export class HistoryComponent implements AfterViewInit, OnInit {
  
  @ViewChild(MatSort) sort: MatSort | undefined;
  @ViewChild(MatPaginator) paginator: MatPaginator | undefined;
  displayedColumns: string[] = ['url', 'strategy', 'speedIndex', 'timeToInteractive'];
  dataSource = new MatTableDataSource<MetricsDTO>();
  
  private _liveAnnouncer = inject(LiveAnnouncer);

  constructor(private seoService: SeoService) { }

  ngOnInit(): void {
    this.seoService.getHistory().subscribe();
    this.seoService.seoHistory$.subscribe(
      (data: MetricsDTO[]) => {
        this.dataSource.data = data.map(metric => ({
          ...metric,
          url: this.getDomainName(metric.url)
        }));
      },
      error => {
        console.error('Error fetching history', error);
      }
    );
    this.seoService.getHistory().subscribe();
  }

  ngAfterViewInit() {
    if (this.sort) this.dataSource.sort = this.sort;
    if (this.paginator) this.dataSource.paginator = this.paginator;
  }

  announceSortChange(sortState: Sort) {
    if (sortState.direction) {
      this._liveAnnouncer.announce(`Sorted ${sortState.direction}ending`);
    } else {
      this._liveAnnouncer.announce('Sorting cleared');
    }
  }

  getDomainName(url: string): string {
    const regex = /^(?:https?:\/\/)?(?:www\.)?([^\/.]+)\./;
    const match = url.match(regex);
    return match && match[1] ? match[1] : url;
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
}



}
