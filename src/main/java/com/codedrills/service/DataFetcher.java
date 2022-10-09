package com.codedrills.service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Service
public class DataFetcher {
  private static Logger logger = Logger.getLogger(DataFetcher.class);
  private final RestTemplate restTemplate;
  private final static Map<CacheDuration, Cache<String, Object>> cacheMap = new HashMap<>();

  static {
    cacheMap.put(CacheDuration.SHORT,
      CacheBuilder.newBuilder()
        .maximumSize(25)
        .expireAfterWrite(5, TimeUnit.MINUTES)
        .build());
    cacheMap.put(CacheDuration.LONG,
      CacheBuilder.newBuilder()
        .maximumSize(25)
        .expireAfterWrite(6, TimeUnit.HOURS)
        .build());
  }

  @Autowired
  public DataFetcher(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public String fetchJson(String url, CacheDuration cacheDuration) {
    Optional<Cache<String, Object>> cacheO = Optional.ofNullable(cacheMap.get(cacheDuration));
    try {
      return (String) cacheO
        .map(c -> {
          try {
            return c.get(url, () -> fetchStringFor(url));
          } catch(ExecutionException ex) {
            throw new RuntimeException(ex.getCause().getMessage());
          }
        })
        .orElseGet(() -> fetchStringFor(url));
    } catch(Exception ex) {
      logger.error(String.format("Error while fetching %s", url), ex);
      throw new RuntimeException(ex.getMessage());
    }
  }

  public Document fetchDoc(String url, CacheDuration cacheDuration) {
    Optional<Cache<String, Object>> cacheO = Optional.ofNullable(cacheMap.get(cacheDuration));
    try {
      return (Document) cacheO
        .map(c -> {
          try {
            return c.get(url, () -> fetchDocFor(url));
          } catch(ExecutionException ex) {
            throw new RuntimeException(ex.getCause().getMessage());
          }
        })
        .orElseGet(() -> fetchDocFor(url));
    } catch(Exception ex) {
      logger.error(String.format("Error while fetching %s", url), ex);
      throw new RuntimeException(ex.getMessage());
    }
  }

  private String fetchStringFor(String url) {
    try {
      return restTemplate.getForObject(url, String.class);
    } catch(Exception ex) {
      throw new RuntimeException(ex);
    }
  }

  private Document fetchDocFor(String url) {
    try {
      return Jsoup.connect(url).get();
    } catch(IOException e) {
      throw new RuntimeException(e);
    }
  }

  public enum CacheDuration {
    SHORT,
    LONG,
    NONE
  }
}
