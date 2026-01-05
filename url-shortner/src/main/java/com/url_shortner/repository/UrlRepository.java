package com.url_shortner.repository;

import com.url_shortner.entity.UrlMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<UrlMapping , Long> {
    public UrlMapping findByShortUrl(String shortUrl);
}
