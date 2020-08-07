import { Component, OnInit, ChangeDetectionStrategy, Inject, ElementRef, OnDestroy, AfterViewInit } from '@angular/core';
import { Observable, Subscription, combineLatest } from 'rxjs';
import { CurrentProductService, CmsComponentData } from '@spartacus/storefront';
import { TvpageProduct } from '../../model/TvpageProduct';
import { DOCUMENT } from '@angular/common';
import { TvpageVideoCmsComponent } from '../../model/TvpageVideoCmsComponent';

@Component({
  selector: 'cx-tvpage-video',
  templateUrl: './tvpage-video.component.html',
  styleUrls: ['./tvpage-video.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class TvpageVideoComponent implements OnInit, OnDestroy, AfterViewInit {
  static targetElementId: string = 'widget-carousel';

  product$: Observable<TvpageProduct>;
  subscription: Subscription;

  constructor(
    public component: CmsComponentData<TvpageVideoCmsComponent>,
    protected currentProductService: CurrentProductService,
    @Inject(DOCUMENT) private document,
    private elementRef: ElementRef
  ) { }

  ngOnInit() {
    this.product$ = this.currentProductService.getProduct('tvpage');
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  ngAfterViewInit() {
    this.subscription = combineLatest(this.component.data$, this.product$)
      .subscribe(([componentData, product]) => {
        this.elementRef.nativeElement.innerHTML = '';

        if (componentData && componentData.srcUrl) {
          const tvpageWidgetScriptContent =
            `
            (function (d, s) {
              __TVPage__ = window.__TVPage__ || {};
              __TVPage__.config = __TVPage__.config || {};
              __TVPage__.config["${TvpageVideoComponent.targetElementId}"] = {
                  targetEl: "${TvpageVideoComponent.targetElementId}",
                  type: "carousel",
                  data: ${product && product.tvpageVideoJson ? product.tvpageVideoJson : `[]`},
              };

              var js = d.createElement(s),
                  fjs = d.getElementsByTagName(s)[0];

              js.async = "async";
              js.src = "${componentData.srcUrl}";
              fjs.parentNode.insertBefore(js, fjs);
            }(document, 'script')); 
          `;

          let targetElement: HTMLDivElement = this.document.createElement('div');
          targetElement.id = TvpageVideoComponent.targetElementId;
          this.elementRef.nativeElement.appendChild(targetElement);

          let scriptElement: HTMLScriptElement = this.document.createElement('script');
          scriptElement.text = tvpageWidgetScriptContent;
          this.elementRef.nativeElement.appendChild(scriptElement);
        }
      });
  }
}
