package com.sc.fe.analyze.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;

public class CachingService {

	@Autowired
	CacheManager cacheManager;
	 
	public void evictSingleCacheValue(String cacheName, Object cacheKey) {
	    cacheManager.getCache(cacheName).evict(cacheKey);
	}
	 
	public void evictAllCacheValues(String cacheName) {
	    cacheManager.getCache(cacheName).clear();
	}
	
	public void evictAllCaches() {
	    cacheManager.getCacheNames().stream()
	      .forEach(cacheName -> cacheManager.getCache(cacheName).clear());
	}
	
//	@Scheduled(fixedRate = 60000)
//	public void evictAllcachesAtIntervals() {
//	    evictAllCaches();
//	}
	
	//Hourly cleanup
	@Scheduled(fixedRate = 360000)
	public void evictSelectedAtIntervals() {
		//evictAllCacheValues("");
	}
	
}
