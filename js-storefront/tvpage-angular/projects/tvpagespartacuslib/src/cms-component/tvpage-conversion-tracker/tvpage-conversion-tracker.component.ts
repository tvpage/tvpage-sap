import { Component, OnInit, ChangeDetectionStrategy, OnDestroy, AfterViewInit, Inject, ElementRef } from '@angular/core';
import { Observable, Subscription } from 'rxjs';
import { Order, CheckoutService, Config } from '@spartacus/core';
import { DOCUMENT } from '@angular/common';

@Component({
  selector: 'cx-tvpage-conversion-tracker',
  templateUrl: './tvpage-conversion-tracker.component.html',
  styleUrls: ['./tvpage-conversion-tracker.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class TvpageConversionTrackerComponent implements OnInit, OnDestroy, AfterViewInit {
  order$: Observable<Order>;
  subscription: Subscription;

  constructor(
    protected checkoutService: CheckoutService,
    @Inject(DOCUMENT) private document,
    private elementRef: ElementRef,
    @Inject(Config) protected config: any
  ) { }

  ngOnInit() {
    this.order$ = this.checkoutService.getOrderDetails();
  }

  ngOnDestroy() {
    this.checkoutService.clearCheckoutData();
    this.subscription.unsubscribe();
  }

  ngAfterViewInit() {
    this.subscription = this.order$
      .subscribe((order: Order) => {
        this.elementRef.nativeElement.innerHTML = '';

        if (this.config.tvpage.accountId) {
          const conversionTrackerScriptContent =
            `
              (function() {
                var tvpa = document.createElement('script'); tvpa.type = 'text/javascript'; tvpa.async = true;
                tvpa.src = ('https:' == document.location.protocol ? 'https' : 'http') + '://a.tvpage.com/tvpa.min.js';
                var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(tvpa, s);
              })();
              
              var _tvpa = _tvpa || [];
              _tvpa.push(['config', {
                li: "${this.config.tvpage.accountId}" // Account ID
              }]);
            
              _tvpa.push(['track', 'products', {
                "tid": "${order.code}",
                "orders": [
                  ${order.entries.map((entry) =>
                    `{ "sku":"${entry.product.code}", "price":"${entry.basePrice.value}", "quantity": "${entry.quantity}"}`
                  ).join(',')}
                ]
              }]);
            `;

          let scriptElement: HTMLScriptElement = this.document.createElement('script');
          scriptElement.type = 'text/javascript';
          scriptElement.text = conversionTrackerScriptContent;
          this.elementRef.nativeElement.appendChild(scriptElement);
        }
      });
  }
}
