package de.matga.cache.buildings;

public interface CacheImpMethods<V> {

     V fetch(String key);

    void write(String key, V value);

}
