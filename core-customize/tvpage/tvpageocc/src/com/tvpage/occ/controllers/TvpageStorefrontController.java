/*
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.tvpage.occ.controllers;

import com.tvpagefacades.TvPageStoreFrontFacade;
import com.tvpagefacades.data.TvPageContentData;
import de.hybris.platform.commerceservices.request.mapping.annotation.ApiVersion;
import de.hybris.platform.webservicescommons.cache.CacheControl;
import de.hybris.platform.webservicescommons.cache.CacheControlDirective;
import de.hybris.platform.webservicescommons.swagger.ApiBaseSiteIdParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/{baseSiteId}/tvpage")
@CacheControl(directive = CacheControlDirective.PRIVATE)
@ApiVersion("v2")
@Api(tags = "Storefront")
public class TvpageStorefrontController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TvpageStorefrontController.class);

    @Resource(name = "tvPageStorefrontFacade")
    private TvPageStoreFrontFacade tvpageStorefrontFacade;

    @GetMapping(value = "/storefront/html")
    @ResponseBody
    @ApiOperation(value = "Get TVPage Storefront HTML", notes = "Given a url, return TVPage content data containing HTML.", nickname = "getTvpageStorefrontHtml")
    @ApiBaseSiteIdParam
    public TvPageContentData getTvpageStorefrontHtml(@ApiParam(value = "TVPage URL") @RequestParam(required = false) final String url) {
        return getTvpageStorefrontFacade().getTvPageStorefrontHtml(url);
    }

    @GetMapping(value = "/storefront/meta-tags")
    @ResponseBody
    @ApiOperation(value = "Get TVPage Storefront Meta Tags", notes = "Given a url, return TVPage content data containing meta tags.", nickname = "getTvpageStorefrontMetaTags")
    @ApiBaseSiteIdParam
    public TvPageContentData getTvpageStorefrontMetaTags(@ApiParam(value = "TVPage URL") @RequestParam(required = false) final String url) {
        return getTvpageStorefrontFacade().getTvPageStorefrontMetaTags(url);
    }

    protected TvPageStoreFrontFacade getTvpageStorefrontFacade() {
        return tvpageStorefrontFacade;
    }
}
