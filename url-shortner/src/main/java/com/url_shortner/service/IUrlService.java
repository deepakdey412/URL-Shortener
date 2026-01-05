package com.url_shortner.service;

import com.url_shortner.entity.UrlMapping;

public interface IUrlService {
    String createShortUrl(String url);
    UrlMapping updateLongUrl(String shortCode , String url);
    String deleteShortUrl(String shortCode);

    String getOriginal(String shortCode);
}
