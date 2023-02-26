package com.pokemon.pokemonbattle.cache;

import org.ehcache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * The type Local cache service.
 */
@Service
public class LocalCacheService extends AbstractLocalCacheService {

    private static Logger logger = LoggerFactory.getLogger(LocalCacheService.class);

    private static final String CACHE_NAME = "localCache";
    private static long CACHE_SIZE_IN_MB;
    private static long CACHE_EXPIRY_TIME_IN_SEC;
    private static long OBJECT_GRAPH_SIZE_IN_KB;

    /**
     * Sets cache expiry time in sec.
     *
     * @param cacheExpiryTimeInSec the cache expiry time in sec
     */
    @Value("${local-cache.expiry-time}")
    public void setCacheExpiryTimeInSec(long cacheExpiryTimeInSec) {
        CACHE_EXPIRY_TIME_IN_SEC = cacheExpiryTimeInSec;
    }

    /**
     * Sets cache size in mb.
     *
     * @param cacheSizeInMb the cache size in mb
     */
    @Value("${local-cache.size}")
    public void setCacheSizeInMb(long cacheSizeInMb) {
        CACHE_SIZE_IN_MB = cacheSizeInMb;
    }

    /**
     * Sets object graph size in kb.
     *
     * @param objectGraphSizeInKb the object graph size in kb
     */
    @Value("${local-cache.object-graph-size}")
    public void setObjectGraphSizeInKb(long objectGraphSizeInKb) {
        OBJECT_GRAPH_SIZE_IN_KB = objectGraphSizeInKb;
    }

    @Override
    public void setup() {
        Cache<String, String> lruCacheService = getCacheManager().createCache(CACHE_NAME,
                prepareConfiguration(CACHE_SIZE_IN_MB, CACHE_EXPIRY_TIME_IN_SEC, OBJECT_GRAPH_SIZE_IN_KB));
        setLruCacheService(lruCacheService);
    }

    @Override
    public Logger getLogger() {
        return logger;
    }
}