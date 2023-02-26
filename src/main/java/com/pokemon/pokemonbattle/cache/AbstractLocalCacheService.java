package com.pokemon.pokemonbattle.cache;

import com.pokemon.pokemonbattle.constants.CacheConstants;
import com.pokemon.pokemonbattle.utility.MapperUtil;
import jakarta.annotation.PostConstruct;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.Status;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.MemoryUnit;
import org.slf4j.Logger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;

public abstract class AbstractLocalCacheService implements DisposableBean {

    private static CacheManager cacheManager;
    private Cache<String, String> lruCacheService;

    @Autowired
    private MapperUtil mapperUtil;

    @PostConstruct
    private void init() {
        setup();
    }

    /**
     * Set.
     *
     * @param key       the key
     * @param value     the value
     * @param expiryInS the expiry in s
     */
    public void set(String key, Object value, int expiryInS) {
        try {
            getLogger().info("Setting value in cache for key : " + key);
            String serializedValue = mapperUtil.toJson(value);
            if (value != null) {
                saveToLru(key, serializedValue);
            }
        } catch (Exception exception) {
            getLogger().error("Cache set failed for keys: " + key + ", exception: " + exception.getMessage(), exception);
        }

    }

    /**
     * Get t.
     *
     * @param <T>        the type parameter
     * @param key        the key
     * @param typeToCast the type to cast
     * @return the t
     */
    public <T> T get(String key, Class<T> typeToCast) {
        T value = null;
        try{
        String serializedValue = null;
        serializedValue = lruCacheService.get(key);
        if(serializedValue != null) value = mapperUtil.fromJson(serializedValue, typeToCast);
        }
         catch (Exception exception) {
            getLogger().error("Cache get failed for keys: " + key + ", exception: " + exception.getMessage(), exception);
        }
        return value;
    }

    /**
     * Gets cache manager.
     *
     * @return the cache manager
     */
    protected static CacheManager getCacheManager() {
        if (cacheManager == null || !cacheManager.getStatus().equals(Status.AVAILABLE)) {
            cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build(true);
        }
        return cacheManager;
    }

    /**
     * Prepare configuration cache configuration.
     *
     * @param sizeInMb        the size in mb
     * @param expiryTimeInSec the expiry time in sec
     * @param objectGraphSize the object graph size
     * @return the cache configuration
     */
    protected CacheConfiguration<String, String> prepareConfiguration(long sizeInMb, long expiryTimeInSec, long objectGraphSize) {
        CacheConfiguration<String, String> cacheConfiguration = null;
        if (CacheConstants.MAX_SIZE_FOR_CACHING_IN_MB < sizeInMb) {
            sizeInMb = CacheConstants.MAX_SIZE_FOR_CACHING_IN_MB;
        }
        if (CacheConstants.MAX_EXPIRY_TIME_IN_SEC < expiryTimeInSec) {
            expiryTimeInSec = CacheConstants.MAX_EXPIRY_TIME_IN_SEC;
        }
        if (CacheConstants.MAX_OBJECT_GRAPH_SIZE_IN_KB < objectGraphSize) {
            objectGraphSize = CacheConstants.MAX_OBJECT_GRAPH_SIZE_IN_KB;
        }
        cacheConfiguration = CacheConfigurationBuilder
                .newCacheConfigurationBuilder(String.class, String.class,
                        ResourcePoolsBuilder.newResourcePoolsBuilder().heap(sizeInMb, MemoryUnit.MB))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(expiryTimeInSec)))
                .withSizeOfMaxObjectSize(objectGraphSize, MemoryUnit.KB)
                .build();
        return cacheConfiguration;
    }


    private void saveToLru(String key, String value) {
        lruCacheService.put(key, value);
    }

    /**
     * Sets local cache service.
     *
     * @param lruCacheService the local cache service
     */
    public void setLruCacheService(Cache<String, String> lruCacheService) {
        this.lruCacheService = lruCacheService;
    }

    @Override
    public void destroy() throws Exception {
        getCacheManager().close();
    }

    /**
     * Sets .
     */
    public abstract void setup();

    /**
     * Gets logger.
     *
     * @return the logger
     */
    public abstract Logger getLogger();

}