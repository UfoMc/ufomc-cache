package de.matga.cache.buildings;

import de.matga.cache.LoadingCache;
import de.matga.cache.etc.executor.Timer;
import de.matga.cache.mapping.Entry;
import lombok.Getter;

import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

@Getter
public class LoadingCacheBuilderImp<V> {

    private Entry<V>[] entries;
    private int maxEntries;
    private long expireTime;
    private LoadingCache<V> loadingCache;

    public LoadingCache<V> loadingCache(CacheImpMethods<V> methods, CacheBuilder<V> builder) {

        /*  define  */
        this.expireTime = builder.expireAfter;
        this.maxEntries = builder.maxEntries;
        this.entries = new Entry[maxEntries];
        /*    end    */

        loadingCache = new LoadingCache<>() {
            @Override
            public V get(String key) {

                Entry<V> entry = entries[key.hashCode() % maxEntries];
                V value;

                if (entry == null) {
                    value = methods.fetch(key);
                    put(key, value);
                } else {
                    value = entry.getValue();
                }

                return value;
            }

            @Override
            public void put(String key, V value) {

                int hash = key.hashCode() % maxEntries;
                Entry<V> entry = entries[hash];

                if (entry == null) {
                    entries[hash] = new Entry<>(key, value);
                } else {
                    entry.setValue(value);
                }

            }

            @Override
            public void remove(String key) {
                int hash = key.hashCode() % maxEntries;
                Entry<V> entry = entries[hash];

                if (entry == null) {
                    return;
                }

                methods.write(key, entries[hash].getValue());
                entries[hash] = null;

            }

            @Override
            public void forEach(BiConsumer<? super String, ? super V> action) {
                for (Entry<V> entry : entries) {

                    final String k;
                    final V v;
                    try {
                        k = entry.getKey();
                        v = entry.getValue();
                    } catch (IllegalStateException exception) {
                        throw new ConcurrentModificationException(exception);
                    }

                    action.accept(k, v);

                }
            }

            @Override
            public void save() {
                for (Entry<V> entry : entries){
                    methods.write(entry.getKey(), entry.getValue());
                }
            }
        };

        new Timer<V>().run(builder.updateDelay, this);

        return loadingCache;
    }

}
