import { ChangeDetectionStrategy, Component, OnInit } from '@angular/core';
import { CmsComponentData } from '@spartacus/storefront';
import { RoutingService, RoutingConfigService } from '@spartacus/core';
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
    protected tvpageService: TvpageService,
    protected routingService: RoutingService,
    protected routingConfigService: RoutingConfigService,
    protected router: Router
  ) { }

  ngOnInit(): void {
    this.routingService
      .getRouterState()
      .subscribe((routingData) => {
        let tvpageUrl = '';
        const baseUrl = this.router.serializeUrl(this.router.createUrlTree(['']));
        const currUrl = routingData.state.url;
        const routeConfig = this.routingConfigService.getRouteConfig('tvpageStorefront');

        if (currUrl && routeConfig && routeConfig.paths) {
          for (let path of routeConfig.paths) {
            let routeConfigPathurl = `${baseUrl}${path}`;
            if (currUrl === routeConfigPathurl || currUrl.startsWith(`${routeConfigPathurl}/`)) {
              tvpageUrl = currUrl.substr(routeConfigPathurl.length);
              break;
            }
          }
        }
        this.tvpageHtml$ = this.tvpageService.getPageHtml(tvpageUrl);
        this.tvpageService.populateMetaTags(tvpageUrl);
      })
      .unsubscribe();
  }
}
