import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { translations, translationChunksConfig } from '@spartacus/assets';
import { B2cStorefrontModule } from '@spartacus/storefront';
import { TvpageSpartacusModule } from '@tvpage/spartacus';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    TvpageSpartacusModule.withConfig({
      tvpage: {
        accountId: '12345abc'
      }
    }),
    B2cStorefrontModule.withConfig({
      backend: {
        occ: {
          baseUrl: 'https://localhost:9002',
          prefix: '/occ/v2/'
        }
      },
      context: {
        urlParameters: ['baseSite', 'language', 'currency'],
        baseSite: ['electronics-spa'],
        currency: ['USD'],
        language: ['en'],
      },
      i18n: {
        resources: translations,
        chunks: translationChunksConfig,
        fallbackLang: 'en'
      },
      features: {
        level: '2.0'
      }
    })
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
