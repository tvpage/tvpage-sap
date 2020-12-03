import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TvpageConversionTrackerComponent } from './tvpage-conversion-tracker.component';
import { provideDefaultConfig, CmsConfig } from '@spartacus/core';

@NgModule({
  imports: [
    CommonModule
  ],
  providers: [
    provideDefaultConfig(<CmsConfig>{
      cmsComponents: {
        TvpageConversionTrackerComponent: {
          component: TvpageConversionTrackerComponent,
        }
      },
    }),
  ],
  declarations: [TvpageConversionTrackerComponent],
  exports: [TvpageConversionTrackerComponent],
  entryComponents: [TvpageConversionTrackerComponent],
})
export class TvpageConversionTrackerModule { }
