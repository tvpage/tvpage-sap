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
            for (let tagAttribute of tag.attributes) {
              metaDefinition[tagAttribute.attribute] = tagAttribute.value;

              // const tagAttributeName = tagAttribute.attribute;
              // const tagAttributeValue = tagAttribute.value;
              // switch (tagAttributeName) {
              //   case 'charset':
              //     metaDefinition.charset = tagAttributeValue;
              //     break;
              //   case 'content':
              //     metaDefinition.content = tagAttributeValue;
              //     break;
              //   case 'http-equiv':
              //     metaDefinition.httpEquiv = tagAttributeValue;
              //     break;
              //   case 'id':
              //     metaDefinition.id = tagAttributeValue;
              //     break;
              //   case 'itemprop':
              //     metaDefinition.itemprop = tagAttributeValue;
              //     break;
              //   case 'name':
              //     metaDefinition.name = tagAttributeValue;
              //     break;
              //   case 'property':
              //     metaDefinition.property = tagAttributeValue;
              //     break;
              //   case 'scheme':
              //     metaDefinition.scheme = tagAttributeValue;
              //     break;
              //   case 'url':
              //     metaDefinition.url = tagAttributeValue;
              //     break;
              //   default:
              //     metaDefinition[tagAttributeName] = tagAttributeValue;
              //     break;
              // }
            }
            this.ngMeta.updateTag(metaDefinition);
          } else {
            let tagElement: HTMLElement = this.document.createElement(tag.tag);
            for (let tagAttribute of tag.attributes) {
              tagElement.setAttribute(tagAttribute.attribute, tagAttribute.value);
            }
            this.document.head.appendChild(tagElement);
          }
        }
      });
  }
}
