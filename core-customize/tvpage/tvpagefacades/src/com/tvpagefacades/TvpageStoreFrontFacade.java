package com.tvpagefacades;

import com.tvpagefacades.data.TvpageMetaData;


public interface TvpageStoreFrontFacade {

    String getTvpageStorefrontHtml(String uri);

    TvpageMetaData getTvpageStorefrontMetaTags(String uri);
}
