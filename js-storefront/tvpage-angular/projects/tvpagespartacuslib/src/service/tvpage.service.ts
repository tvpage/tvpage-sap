import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable, Inject } from '@angular/core';
import { Observable } from 'rxjs';
import { OccEndpointsService } from '@spartacus/core';
import { map } from 'rxjs/operators';
import { TvpageMetaData, MetaTagData } from '../model/TvpageMetaData';
import { Meta, Title, MetaDefinition } from '@angular/platform-browser';
import { DOCUMENT } from '@angular/common';

@Injectable({
  providedIn: 'root',
})
export class TvpageService {
  protected headers = new HttpHeaders().set('Content-Type', 'application/json');

  constructor(
    private http: HttpClient,
    private occEndpoints: OccEndpointsService,
    private ngTitle: Title,
    private ngMeta: Meta,
    @Inject(DOCUMENT) private document
  ) { }

  getPageHtml(url: string): Observable<string> {
    return this.http.get<string>(
      this.occEndpoints.getUrl(
        'tvpage/storefront/html',
        {},
        {
          url: url,
        }
      ),
      {
        headers: this.headers,
      }
    );
  }

  getPageMeta(url: string): Observable<TvpageMetaData> {
    return this.http.get<TvpageMetaData>(
      this.occEndpoints.getUrl(
        'tvpage/storefront/meta-tags',
        {},
        {
          url: url,
        }
      ),
      {
        headers: this.headers,
      }
    );
  }

  populateMetaTags(url: string): void {
    this.getPageMeta(url)
      .pipe(
        map(metaData => metaData.html.head.tags)
      )
      .subscribe((tags: MetaTagData[]) => {

        for (let tag of tags) {
          if (tag.tag === 'title') {
            this.ngTitle.setTitle(tag.value || '');
          } else if (tag.tag === 'meta') {
            let metaDefinition: MetaDefinition = {};
            if (tag.attributes) {
              for (let tagAttribute of tag.attributes) {
                metaDefinition[tagAttribute.attribute] = tagAttribute.value;
              }
            }
            this.ngMeta.updateTag(metaDefinition);
          } else {
            // for other tags in <head> (e.g., <style>, <base>, <link>, <script>, <noscript>)
            let tagElement: HTMLElement = this.document.createElement(tag.tag);
            if (tag.attributes) {
              for (let tagAttribute of tag.attributes) {
                tagElement.setAttribute(tagAttribute.attribute, tagAttribute.value);
              }
            }
            if (tag.value) {
              tagElement.appendChild(this.document.createTextNode(tag.value));
            }

            this.document.head.appendChild(tagElement);
          }
        }
      });
  }
}
