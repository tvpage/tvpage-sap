import { Component, OnInit, ChangeDetectionStrategy, Inject, ElementRef, OnDestroy, AfterViewInit } from '@angular/core';
import { Observable, Subscription } from 'rxjs';
import { CurrentProductService } from '@spartacus/storefront';
import { TvpageProduct } from '../../model/TvpageProduct';
import { DOCUMENT } from '@angular/common';

@Component({
  selector: 'cx-tvpage-video',
  templateUrl: './tvpage-video.component.html',
  styleUrls: ['./tvpage-video.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class TvpageVideoComponent implements OnInit, OnDestroy, AfterViewInit {
  static targetElementId: string = 'sample-carousel';

  product$: Observable<TvpageProduct>;
  subscription: Subscription;

  constructor(
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
    this.subscription = this.product$
      .subscribe((product: TvpageProduct) => {
        this.elementRef.nativeElement.innerHTML = '';

        const tvpageWidgetScriptContent =
          `
          (function (d, s) {
            __TVPage__ = window.__TVPage__ || {};
            __TVPage__.config = __TVPage__.config || {};
            __TVPage__.config["sample-carousel"] = {
                targetEl: "${TvpageVideoComponent.targetElementId}",
                type: "carousel",
                ${product.tvpageVideoJson ? 
                  `data: ${product.tvpageVideoJson},`:
                  `channel: {"id": "225234959", parameters:{"referenceId":""}},`
                }
            };

            var js = d.createElement(s),
                fjs = d.getElementsByTagName(s)[0];

            js.async = "async";
            js.src = "https://site.app.tvpage.com/1759430/tvpwidget/sample-carousel.js";
            fjs.parentNode.insertBefore(js, fjs);
          }(document, 'script')); 
        `;

        let targetElement: HTMLDivElement = this.document.createElement('div');
        targetElement.id = TvpageVideoComponent.targetElementId;
        this.elementRef.nativeElement.appendChild(targetElement);

        let scriptElement: HTMLScriptElement = this.document.createElement('script');
        scriptElement.text = tvpageWidgetScriptContent;
        this.elementRef.nativeElement.appendChild(scriptElement);
      });
  }
}
