package com.tvpageservices.impl;

import com.tvpageservices.TvPageStorefrontService;
import org.springframework.web.client.RestTemplate;

public class TvPageStorefrontServiceImpl implements TvPageStorefrontService {

    RestTemplate restTemplate;

    final String ROOT_URI = "https://localhost:8080/Tvpage/respose.json";

    @Override
    public String getTvPageStorefrontHtml(String uri) {
        //      String response = restTemplate.getForObject(ROOT_URI + uri, String.class);
        String  response="{ \"html\" : \"<div>hello world!Welcome to TvPage Storefront!</div>\"}";
        return response;
    }

    @Override
    public String getTvPageStorefrontMetaTags(String uri) {
        //      String response = restTemplate.getForObject(ROOT_URI + uri, String.class);
        String  response="{ \"metatags\" : [ {  \"key\": \"someKey\",\"value\" : \"someValue\"},{  \"key\": \"someKey1\",\"value\" : \"someValue1\"}]}";

        return response;
    }
    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
