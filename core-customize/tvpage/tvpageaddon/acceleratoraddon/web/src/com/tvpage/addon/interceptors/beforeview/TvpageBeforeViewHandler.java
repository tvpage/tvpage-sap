/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.tvpage.addon.interceptors.beforeview;


import de.hybris.platform.acceleratorservices.config.SiteConfigService;
import de.hybris.platform.acceleratorstorefrontcommons.interceptors.BeforeViewHandler;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class TvpageBeforeViewHandler implements BeforeViewHandler {
    private static final String TVPAGE_ACCOUNT_ID = "tvpage.account.id";

    @Resource(name = "siteConfigService")
    private SiteConfigService siteConfigService;

    @Override
    public void beforeView(HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView) {
        final String tvpageAccountId = siteConfigService.getProperty(TVPAGE_ACCOUNT_ID);
        if (StringUtils.isNotEmpty(tvpageAccountId)) {
            modelAndView.addObject("tvpageAccountId", tvpageAccountId);
        }
    }
}
