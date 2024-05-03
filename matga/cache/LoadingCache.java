package de.matga.cache;

import java.util.function.BiConsumer;

public interface LoadingCache<V> {

    V get(String key);

    void put(String key, V value);

    void remove(String key);

    void forEach(BiConsumer<? super String, ? super V> action);
    void save();

}
