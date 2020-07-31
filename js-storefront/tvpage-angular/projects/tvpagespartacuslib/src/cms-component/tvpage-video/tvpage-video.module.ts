import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TvpageVideoComponent } from './tvpage-video.component';
import { provideDefaultConfig, CmsConfig } from '@spartacus/core';

@NgModule({
  imports: [
    CommonModule
  ],
  providers: [
    provideDefaultConfig(<CmsConfig>{
      cmsComponents: {
        TvpageVideoCmsComponent: {
          component: TvpageVideoComponent,
        }
      },
    }),
  ],
  declarations: [TvpageVideoComponent],
  exports: [TvpageVideoComponent],
  entryComponents: [TvpageVideoComponent],
})
export class TvpageVideoModule { }
