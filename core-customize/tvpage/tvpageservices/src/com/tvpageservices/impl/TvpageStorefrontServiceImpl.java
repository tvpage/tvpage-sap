package com.tvpageservices.impl;

import com.tvpageservices.TvpageStorefrontService;
import de.hybris.platform.acceleratorservices.config.SiteConfigService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.client.RestTemplate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TvpageStorefrontServiceImpl implements TvpageStorefrontService {

    private static final String TVPAGE_STOREFRONT_HTML_BASE_ENDPOINT = "tvpage.storefront.html.base.endpoint";
    private static final String TVPAGE_STOREFRONT_META_BASE_ENDPOINT = "tvpage.storefront.meta.base.endpoint";
    private static final String TVPAGE_STOREFRONT_META_HOME_PATH = "tvpage.storefront.meta.home.path";

    private RestTemplate restTemplate;
    private SiteConfigService siteConfigService;

    @Override
    public String getTvpageStorefrontHtml(String uri) {
        String response = restTemplate.getForObject(siteConfigService.getProperty(TVPAGE_STOREFRONT_HTML_BASE_ENDPOINT) + uri, String.class);
        Pattern pattern = Pattern.compile("<body.*?>(.*?)</body>", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(response);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return StringUtils.EMPTY;
    }

    @Override
    public String getTvpageStorefrontMetaTags(String uri) {
        if (StringUtils.isBlank(uri) || "/".equals(uri)) {
            uri = siteConfigService.getProperty(TVPAGE_STOREFRONT_META_HOME_PATH);
        }
        return restTemplate.getForObject(siteConfigService.getProperty(TVPAGE_STOREFRONT_META_BASE_ENDPOINT) + uri, String.class);
    }

    protected RestTemplate getRestTemplate() {
        return restTemplate;
    }


    @Required
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    protected SiteConfigService getSiteConfigService() {
        return siteConfigService;
    }

    @Required
    public void setSiteConfigService(SiteConfigService siteConfigService) {
        this.siteConfigService = siteConfigService;
    }
}
