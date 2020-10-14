# On Hybris Commerce side
 
* Setup hybris with spartacussampledataaddon:
  * `https://sap.github.io/spartacus-docs/installing-sap-commerce-cloud-2005/`
* Setup out custom extensions:
  * Create symbolic links for our custom extensions in `core-customize/tvpage` to `hybris/bin/custom`
* Add our custom extensions to localextensions.xml
* Add config properties in `hybris/config/local.properties`
  * Note that the properties below will be read by SiteConfigService. Thus, suffix of  `.{siteUid}` can be added if different values are needed for each sites.
  ```
  tvpage.account.id=1759430
  tvpage.storefront.html.base.endpoint=https://site.app.tvpage.com/1759430
  tvpage.storefront.meta.base.endpoint=https://site.app.tvpage.com/1759430/metadata_json
  tvpage.storefront.meta.home.path=/home
  ```
* Build and Initialize

# On Angular side
* Install the followings
  * Angular CLI: 9.1 or later.
  * node.js: 10.14.1 or later, < 13.0. The most recent 12.x version is recommended.
  * yarn: 1.15 or later
* From `js-storefront/tvpage-angular` directory, run the followings
  ```
  npm install
  ng build tvpagespartacuslib
  npm install dist/tvpagespartacuslib --save
  yarn start
  ```
* When making changes to the angular code in tvpagespartacuslib, we would need to rebuild the library. (If we make changes to the app component, change would reflect automatically).
  * Delete `dist/tvpagespartacuslib ` and ` node_modules/@tvpage`
  * Run
    ```
    ng build tvpagespartacuslib
    npm install dist/tvpagespartacuslib --save
    yarn start
    ```
