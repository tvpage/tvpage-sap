import { NgModule, ModuleWithProviders } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { PageLayoutComponent, CmsPageGuard, LayoutConfig } from '@spartacus/storefront';
import { TvpageStorefrontModule, TvpageVideoModule, TvpageConversionTrackerModule } from '../cms-component';
import { provideDefaultConfig, RoutingConfig, provideConfig, OccConfig } from '@spartacus/core';
import { TvpageConfig } from '../model/TvpageConfig';

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    TvpageStorefrontModule,
    TvpageVideoModule,
    TvpageConversionTrackerModule,
    RouterModule.forChild([
      {
        data: {
          cxRoute: 'tvpageStorefront',
          pageLabel: '/storefronts'
        },
        path: null,
        children: [{
          data: {
            pageLabel: '/storefronts'
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
        }
      }
    }),
    provideConfig(<LayoutConfig>{
      layoutSlots: {
        ProductDetailsPageTemplate: {
          lg: {
            pageFold: 'UpSelling',
          },
          pageFold: 'Summary',
          slots: [
            'Summary',
            'UpSelling',
            'CrossSelling',
            'Tabs',
            'Section4',
            'PlaceholderContentSlot',
          ],
        },
        OrderConfirmationPageTemplate: {
          slots: [
            'BodyContent',
            'SideContent',
            'BottomContent'
          ],
        },
      }
    }),
    provideDefaultConfig(<RoutingConfig>{
      routing: {
        routes: {
          tvpageStorefront: {
            paths: ['storefronts']
          }
        },
      },
    }),
    provideConfig(<OccConfig>{
      backend: {
        occ: {
          endpoints: {
            product: {
              tvpage: 'products/${productCode}?fields=tvpageVideoJson'
            },
          },
        }
      }
    })
  ],
})
export class TvpageSpartacusModule {
  static withConfig(config?: TvpageConfig): ModuleWithProviders<TvpageSpartacusModule> {
    return {
      ngModule: TvpageSpartacusModule,
      providers: [provideConfig(config)],
    };
  }
}
