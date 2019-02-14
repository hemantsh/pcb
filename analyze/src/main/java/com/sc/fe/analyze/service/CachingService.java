package com.sc.fe.analyze.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 *
 * @author Hemant
 */
@Service
public class CachingService {

    @Autowired
    CacheManager cacheManager;

    /**
     *
     * @param cacheName - the name of cache
     * @param cacheKey - the key of cache
     */
    public void evictSingleCacheValue(String cacheName, Object cacheKey) {
        cacheManager.getCache(cacheName).evict(cacheKey);
    }

    /**
     *
     * @param cacheName to clear all cache of that cacheName
     */
    public void evictAllCacheValues(String cacheName) {
        cacheManager.getCache(cacheName).clear();
    }

    /**
     * To clear all the caches
     */
    public void evictAllCaches() {
        cacheManager.getCacheNames().stream()
                .forEach(cacheName -> cacheManager.getCache(cacheName).clear());
    }

//	@Scheduled(fixedRate = 60000)
//	public void evictAllcachesAtIntervals() {
//	    evictAllCaches();
//	}
    //Hourly cleanup
    /**
     *
     */
    @Scheduled(fixedRate = 360000)
    public void evictSelectedAtIntervals() {
        //evictAllCacheValues("");
    }

}
