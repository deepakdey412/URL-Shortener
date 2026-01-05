package com.url_shortner.controller;

import com.url_shortner.entity.UrlMapping;
import com.url_shortner.service.IUrlService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/url-shortner")
public class UrlController {

    private final IUrlService  urlService;
    public UrlController(IUrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestParam String url) {
        String code  = urlService.createShortUrl(url);
        return  new ResponseEntity<>("http://localhost:8080/u/" + code , HttpStatus.CREATED);
    }

    @PutMapping("/update/{shortCode}")
    public ResponseEntity<UrlMapping> updateShortUrl(@PathVariable String shortCode , @RequestParam String url) {
        UrlMapping urlMapping = urlService.updateLongUrl(shortCode, url);
        return  new ResponseEntity<>(urlMapping, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{shortCode}")
    public ResponseEntity<String > deleteShortUrl(@PathVariable String shortCode) {
        String msg = urlService.deleteShortUrl(shortCode);
        return  new ResponseEntity<>(msg, HttpStatus.OK);
    }
}
