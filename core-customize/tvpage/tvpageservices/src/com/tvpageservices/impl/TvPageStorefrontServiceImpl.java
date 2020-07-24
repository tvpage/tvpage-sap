package com.tvpageservices.impl;

import com.tvpageservices.TvpageStorefrontService;
import org.springframework.web.client.RestTemplate;

public class TvpageStorefrontServiceImpl implements TvpageStorefrontService {

    RestTemplate restTemplate;

    final String ROOT_URI = "https://localhost:8080/Tvpage/respose.json";

    @Override
    public String getTvpageStorefrontHtml(String uri) {
        //      String response = restTemplate.getForObject(ROOT_URI + uri, String.class);
        String response = "<div>hello world!Welcome to TvPage Storefront!</div>";
        return response;
    }

    @Override
    public String getTvpageStorefrontMetaTags(String uri) {
        //      String response = restTemplate.getForObject(ROOT_URI + uri, String.class);
        String response = "{ \"html\": { \"head\": { \"tags\": [ { \"tag\": \"meta\", \"attributes\": [ { \"attribute\": \"http-equiv\", \"value\": \"Content-Type\" }, { \"attribute\": \"content\", \"value\": \"text/html; charset=UTF-8\" } ] }, { \"tag\": \"meta\", \"attributes\": [ { \"attribute\": \"charset\", \"value\": \"utf-8\" } ] }, { \"tag\": \"meta\", \"attributes\": [ { \"attribute\": \"name\", \"value\": \"viewport\" }, { \"attribute\": \"content\", \"value\": \"width=device-width,minimum-scale=1,initial-scale=1\" } ] }, { \"tag\": \"link\", \"attributes\": [ { \"attribute\": \"rel\", \"value\": \"canonical\" }, { \"attribute\": \"href\", \"value\": \"https://www.google.com\" } ] }, { \"tag\": \"meta\", \"attributes\": [ { \"attribute\": \"name\", \"value\": \"description\" }, { \"attribute\": \"content\", \"value\": \"tvp supplied description\" } ] }, { \"tag\": \"meta\", \"attributes\": [ { \"attribute\": \"property\", \"value\": \"og:url\" }, { \"attribute\": \"content\", \"value\": \"tvp suppplied url\" } ] }, { \"tag\": \"meta\", \"attributes\": [ { \"attribute\": \"property\", \"value\": \"og:type\" }, { \"attribute\": \"content\", \"value\": \"website\" } ] }, { \"tag\": \"meta\", \"attributes\": [ { \"attribute\": \"property\", \"value\": \"og:title\" }, { \"attribute\": \"content\", \"value\": \"page title\" } ] }, { \"tag\": \"meta\", \"attributes\": [ { \"attribute\": \"property\", \"value\": \"og:image\" }, { \"attribute\": \"content\", \"value\": \"https://static.prod.tvpage.com/1759430/static/images/logo.png\" } ] }, { \"tag\": \"meta\", \"attributes\": [ { \"attribute\": \"property\", \"value\": \"og:image:secure_url\" }, { \"attribute\": \"content\", \"value\": \"https://static.prod.tvpage.com/1759430/static/images/logo.png\" } ] }, { \"tag\": \"meta\", \"attributes\": [ { \"attribute\": \"property\", \"value\": \"og:image:width\" }, { \"attribute\": \"content\", \"value\": \"854\" } ] }, { \"tag\": \"meta\", \"attributes\": [ { \"attribute\": \"property\", \"value\": \"og:image:height\" }, { \"attribute\": \"content\", \"value\": \"480\" } ] }, { \"tag\": \"meta\", \"attributes\": [ { \"attribute\": \"property\", \"value\": \"og:image:alt\" }, { \"attribute\": \"content\", \"value\": \"Image alt description\" } ] }, { \"tag\": \"meta\", \"attributes\": [ { \"attribute\": \"name\", \"value\": \"twitter:card\" }, { \"attribute\": \"content\", \"value\": \"summary_large_image\" } ] }, { \"tag\": \"meta\", \"attributes\": [ { \"attribute\": \"name\", \"value\": \"twitter:title\" }, { \"attribute\": \"content\", \"value\": \"page title\" } ] }, { \"tag\": \"meta\", \"attributes\": [ { \"attribute\": \"name\", \"value\": \"twitter:description\" }, { \"attribute\": \"content\", \"value\": \"page description\" } ] }, { \"tag\": \"meta\", \"attributes\": [ { \"attribute\": \"name\", \"value\": \"twitter:image\" }, { \"attribute\": \"content\", \"value\": \"https://static.prod.tvpage.com/1759430/static/images/logo.png\" } ] }, { \"tag\": \"link\", \"attributes\": [ { \"attribute\": \"rel\", \"value\": \"shortcut icon\" }, { \"attribute\": \"href\", \"value\": \"https://static.prod.tvpage.com/1759430/static/favicon/favicon.ico\" } ] }, { \"tag\": \"link\", \"attributes\": [ { \"attribute\": \"rel\", \"value\": \"apple-touch-icon-precomposed\" }, { \"attribute\": \"sizes\", \"value\": \"152x152\" }, { \"attribute\": \"href\", \"value\": \"https://static.prod.tvpage.com/1759430/static/favicon/favicon.ico\" } ] }, { \"tag\": \"meta\", \"attributes\": [ { \"attribute\": \"http-equiv\", \"value\": \"x-dns-prefetch-control\" }, { \"attribute\": \"content\", \"value\": \"on\" } ] },{ \"tag\": \"link\", \"attributes\": [ { \"attribute\": \"rel\", \"value\": \"dns-prefetch\" }, { \"attribute\": \"href\", \"value\": \"//cdnjs.tvpage.com\" } ] }, { \"tag\": \"link\", \"attributes\": [ { \"attribute\": \"rel\", \"value\": \"dns-prefetch\" }, { \"attribute\": \"href\", \"value\": \"//a.tvpage.com\" } ] }, { \"tag\": \"link\", \"attributes\": [ { \"attribute\": \"rel\", \"value\": \"dns-prefetch\" }, { \"attribute\": \"href\", \"value\": \"//v.tvpage.com\" } ] }, { \"tag\": \"link\", \"attributes\": [ { \"attribute\": \"rel\", \"value\": \"dns-prefetch\" }, { \"attribute\": \"href\", \"value\": \"//api.tvpage.com\" } ] }, { \"tag\": \"title\", \"value\": \"page title\", \"attributes\": [] } ] } } }";

        return response;
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
