package com.url_shortner.service;

import com.url_shortner.entity.UrlMapping;

public interface IUrlService {
    UrlMapping createShortUrl(String url);
    UrlMapping updateLongUrl(String shortCode , String url);
    String deleteShortUrl(String shortCode);
}
