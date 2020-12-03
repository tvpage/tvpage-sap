package com.tvpagefacades.impl;

import com.google.gson.Gson;
import com.tvpagefacades.TvpageStoreFrontFacade;
import com.tvpagefacades.data.TvpageMetaData;
import com.tvpageservices.TvpageStorefrontService;


public class TvpageStorefrontFacadeImpl implements TvpageStoreFrontFacade {

    private TvpageStorefrontService tvpageStoreFrontService;

    @Override
    public String getTvpageStorefrontHtml(String uri) {
        return tvpageStoreFrontService.getTvpageStorefrontHtml(uri);
    }

    @Override
    public TvpageMetaData getTvpageStorefrontMetaTags(String uri) {
        String tvPageResponseJson = tvpageStoreFrontService.getTvpageStorefrontMetaTags(uri);
        Gson gson = new Gson();
        TvpageMetaData tagData = gson.fromJson(tvPageResponseJson, TvpageMetaData.class);
        return tagData;
    }

    protected TvpageStorefrontService getTvpageStoreFrontService() {
        return tvpageStoreFrontService;
    }

    public void setTvpageStoreFrontService(TvpageStorefrontService tvpageStoreFrontService) {
        this.tvpageStoreFrontService = tvpageStoreFrontService;
    }
}
