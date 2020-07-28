import { ChangeDetectionStrategy, Component, OnInit } from '@angular/core';
import { CmsComponentData } from '@spartacus/storefront';
import { TvpageStoreFrontCmsComponent } from '../../model/TvpageStorefrontCmsComponent';
import { RoutingService } from '@spartacus/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { TvpageService } from '../../service/tvpage.service';

@Component({
  selector: 'cx-tvpage-storefront',
  templateUrl: './tvpage-storefront.component.html',
  styleUrls: ['./tvpage-storefront.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class TvpageStorefrontComponent implements OnInit {

  tvpageHtml$: Observable<string>;

  constructor(
    public component: CmsComponentData<TvpageStoreFrontCmsComponent>,
    protected tvpageService: TvpageService,
    protected routingService: RoutingService,
    protected router: Router
  ) { }

  ngOnInit(): void {
    this.routingService
      .getRouterState()
      .subscribe((routingData) => {
        const basePageUrl = this.router.serializeUrl(this.router.createUrlTree([routingData.state.context.id]));
        const currUrl = routingData.state.url;
        let tvpageUrl = '';
        if (basePageUrl && currUrl && currUrl.startsWith(basePageUrl)) {
          tvpageUrl = currUrl.substr(basePageUrl.length);
        }
        this.tvpageHtml$ = this.tvpageService.getPageHtml(tvpageUrl);
        this.tvpageService.populateMetaTags(tvpageUrl);
      })
      .unsubscribe();
  }
}
