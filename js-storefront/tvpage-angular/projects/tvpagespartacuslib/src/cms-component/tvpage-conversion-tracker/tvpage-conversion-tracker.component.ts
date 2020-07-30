import { Component, OnInit, ChangeDetectionStrategy, OnDestroy } from '@angular/core';
import { Observable } from 'rxjs';
import { Order, CheckoutService } from '@spartacus/core';

@Component({
  selector: 'cx-tvpage-conversion-tracker',
  templateUrl: './tvpage-conversion-tracker.component.html',
  styleUrls: ['./tvpage-conversion-tracker.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class TvpageConversionTrackerComponent implements OnInit, OnDestroy {
    order$: Observable<Order>;
  
    constructor(
      protected checkoutService: CheckoutService
    ) {}
  
    ngOnInit() {
      this.order$ = this.checkoutService.getOrderDetails();
    }
  
    ngOnDestroy() {
      this.checkoutService.clearCheckoutData();
    }
  }
  