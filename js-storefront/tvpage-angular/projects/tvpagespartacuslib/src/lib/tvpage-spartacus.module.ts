import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { PageLayoutComponent, CmsPageGuard, LayoutConfig } from '@spartacus/storefront';
import { TvpageStorefrontModule } from '../cms-component';
import { provideDefaultConfig, RoutingConfig } from '@spartacus/core';

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    TvpageStorefrontModule,
    RouterModule.forChild([
      {
        data: {
          cxRoute: 'tvpageStorefront',
          pageLabel: '/storefront'
        },
        path: null,
        children: [{
          data: {
            pageLabel: '/storefront'
          },
          path: '**',
          component: PageLayoutComponent,
          canActivate: [CmsPageGuard]
        }],
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
    provideDefaultConfig(<RoutingConfig>{
      routing: {
        routes: {
          tvpageStorefront: {
            paths: ['storefront']
          }
        },
      },
    }),
  ],
})
export class TvpageSpartacusModule { }
