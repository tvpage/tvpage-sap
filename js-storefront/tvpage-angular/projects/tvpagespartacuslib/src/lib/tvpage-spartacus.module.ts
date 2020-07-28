import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { PageLayoutComponent, CmsPageGuard, LayoutConfig } from '@spartacus/storefront';
import { TvpageStorefrontModule } from '../cms-component';
import { provideDefaultConfig } from '@spartacus/core';

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    TvpageStorefrontModule,
    RouterModule.forChild([
      {
        path: 'storefront',
        children: [{
          path: '**',
          data: { pageLabel: '/storefront' },
          component: PageLayoutComponent,
          canActivate: [CmsPageGuard]
        }],
        data: { pageLabel: '/storefront' },
        component: PageLayoutComponent,
        canActivate: [CmsPageGuard]
      }
    ])
  ],
  providers: [
    provideDefaultConfig(<LayoutConfig>{
      layoutSlots: {
        TvpageStorefrontPageTemplate: {
          slots: ['Section1'],
        },
      }
    }),
  ],
})
export class TvpageSpartacusModule { }
