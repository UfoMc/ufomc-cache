package de.matga.cache.buildings;

import de.matga.cache.LoadingCache;

import java.util.concurrent.TimeUnit;

public class CacheBuilder<V> {

    int maxSize;
    long expireAfter;
    TimeUnit timeStamp;
    long updateDelay;

    public CacheBuilder() {
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

    public CacheBuilder<V> setUpdateDelay(long updateDelay) {
        this.updateDelay = updateDelay;
        return this;
    }

    public LoadingCache<V> build(CacheImpMethods<V> methods) {
        return new LoadingCacheBuilderImp<V>().loadingCache(methods, this);
    }

}
