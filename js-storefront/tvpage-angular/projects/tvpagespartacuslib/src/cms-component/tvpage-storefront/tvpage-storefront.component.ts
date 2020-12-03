import { ChangeDetectionStrategy, Component, OnInit, Inject, ElementRef } from '@angular/core';
import { RoutingService, RoutingConfigService } from '@spartacus/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { TvpageService } from '../../service/tvpage.service';
import { DOCUMENT } from '@angular/common';

@Component({
  selector: 'cx-tvpage-storefront',
  templateUrl: './tvpage-storefront.component.html',
  styleUrls: ['./tvpage-storefront.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class TvpageStorefrontComponent implements OnInit {

  routerStateSubscription: Subscription;

  constructor(
    protected tvpageService: TvpageService,
    protected routingService: RoutingService,
    protected routingConfigService: RoutingConfigService,
    protected router: Router,
    @Inject(DOCUMENT) private document,
    private elementRef: ElementRef
  ) { }

  ngOnInit(): void { }

  ngOnDestroy() {
    if (this.routerStateSubscription) {
      this.routerStateSubscription.unsubscribe();
    }
  }

  ngAfterViewInit() {
    this.routerStateSubscription = this.routingService
      .getRouterState()
      .subscribe((routingData) => {
        if (routingData.nextState == null) {
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
          this.tvpageService.getPageHtml(tvpageUrl)
            .subscribe((html: string) => {
              this.elementRef.nativeElement.innerHTML = '';
              let fragment = this.document.createRange().createContextualFragment(html);
              this.elementRef.nativeElement.appendChild(fragment);
            });
          this.tvpageService.populateMetaTags(tvpageUrl);
        }
      });
  }
}
