package com.tvpagefacades;

import com.tvpagefacades.data.TvPageContentData;


public interface TvPageStoreFrontFacade {

   public TvPageContentData getTvPageStorefrontHtml(String uri);

   public TvPageContentData getTvPageStorefrontMetaTags(String uri);
}
