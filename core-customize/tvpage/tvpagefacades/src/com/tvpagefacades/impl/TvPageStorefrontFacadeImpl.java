package com.tvpagefacades.impl;

import com.google.gson.Gson;
import com.tvpagefacades.TvPageStoreFrontFacade;
import com.tvpagefacades.data.TvPageContentData;
import com.tvpageservices.TvPageStorefrontService;


public class TvPageStorefrontFacadeImpl  implements TvPageStoreFrontFacade {

   private TvPageStorefrontService tvpageStoreFrontService;

    @Override
    public TvPageContentData getTvPageStorefrontHtml(String uri)
    {
        String tvPageResponseJson=tvpageStoreFrontService.getTvPageStorefrontHtml(uri);
        // Gson gson = new Gson();
        Gson gson =new Gson();
        TvPageContentData pageData= gson.fromJson(tvPageResponseJson, TvPageContentData.class);
        System.out.println("Converted Response "+ pageData.getHtml());
        return pageData;
    }
    @Override
    public TvPageContentData getTvPageStorefrontMetaTags(String uri)
    {
        String tvPageResponseJson=tvpageStoreFrontService.getTvPageStorefrontMetaTags(uri);
        // Gson gson = new Gson();
        Gson gson =new Gson();
        TvPageContentData pageData= gson.fromJson(tvPageResponseJson, TvPageContentData.class);
        System.out.println("Converted Response "+ pageData.getMetatags().size());
        return pageData;
    }

    public TvPageStorefrontService getTvpageStoreFrontService() {
        return tvpageStoreFrontService;
    }

    public void setTvpageStoreFrontService(TvPageStorefrontService tvpageStoreFrontService) {
        this.tvpageStoreFrontService = tvpageStoreFrontService;
    }
}
