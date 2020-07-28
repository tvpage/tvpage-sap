import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TvpageStorefrontComponent } from './tvpage-storefront.component';
import { CmsConfig, provideDefaultConfig } from '@spartacus/core';

@NgModule({
  imports: [
    CommonModule
  ],
  providers: [
    provideDefaultConfig(<CmsConfig>{
      cmsComponents: {
        TvpageStoreFrontCmsComponent: {
          component: TvpageStorefrontComponent,
        }
      },
    }),
  ],
  declarations: [TvpageStorefrontComponent],
  exports: [TvpageStorefrontComponent],
  entryComponents: [TvpageStorefrontComponent],
})
export class TvpageStorefrontModule { }
