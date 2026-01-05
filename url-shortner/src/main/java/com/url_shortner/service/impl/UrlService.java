package com.url_shortner.service.impl;

import com.url_shortner.entity.UrlMapping;
import com.url_shortner.repository.UrlRepository;
import com.url_shortner.service.IUrlService;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class UrlService implements IUrlService {
    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    private static final String BASE62 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int CODE_LENGTH = 6;
    public String generateCode(){
        SecureRandom  random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < CODE_LENGTH; i++){
            sb.append(BASE62.charAt(random.nextInt(62)));
        }
        return sb.toString();
    }

    @Override
    public UrlMapping createShortUrl(String url) {
        UrlMapping urlMapping = new UrlMapping();
        urlMapping.setUrl(url);

        String code;
        do{
            code = generateCode();
        }while (urlRepository.findByShortCode(code)!= null);

        urlMapping.setShortCode(code);
        urlRepository.save(urlMapping);

        return urlMapping;
    }

    @Override
    public UrlMapping updateLongUrl(String shortCode, String url) {
        UrlMapping u = urlRepository.findByShortCode(shortCode);
        if(u == null){
            throw new RuntimeException("Url Not Found");
        }
        u.setUrl(url);
        return urlRepository.save(u);
    }

    @Override
    public String deleteShortUrl(String shortCode) {
        UrlMapping u = urlRepository.findByShortCode(shortCode);
        if(u == null){
            throw new RuntimeException("Url Not Found");
        }
        urlRepository.delete(u);
        return "Url Deleted Successfully";
    }

}
