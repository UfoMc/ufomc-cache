package de.matga.cache.buildings;

import de.matga.bootstrap.Boot;
import de.matga.cache.LoadingCache;

import java.util.concurrent.TimeUnit;

public class CacheBuilder<V> {

    int maxSize;
    long expireAfter;
    TimeUnit timeStamp;
    long updateDelay;
    int maxEntries;

    public CacheBuilder() {
        this.maxEntries = 1000;
        this.timeStamp = TimeUnit.MINUTES;
        this.expireAfter = 5;
        this.maxSize = 1000;
        this.updateDelay = 5;
    }

    public CacheBuilder<V> setExpireAfter(int expireAfter) {
        this.expireAfter = expireAfter;
        return this;
    }

    public CacheBuilder<V> setTimeStamp(TimeUnit timeStamp) {
        this.timeStamp = timeStamp;
        return this;
    }

    public CacheBuilder<V> setMaxSize(int size) {
        this.maxSize = size;
        return this;
    }

    public CacheBuilder<V> setMaxEntries(int maxEntries) {
        this.maxEntries = maxEntries;
        return this;
    }

    public CacheBuilder<V> setUpdateDelay(long updateDelay) {
        this.updateDelay = updateDelay;
        return this;
    }

    public LoadingCache<V> build(CacheImpMethods<V> methods) {
        LoadingCache<V> cache = new LoadingCacheBuilderImp<V>().loadingCache(methods, this);
        Boot.getInstance().getCaches().add(cache);
        return cache;
    }

}
