package de.matga.cache.mapping;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Entry<V> {

    private final String key;
    private V value;
    private int size;
    private long added;

    public Entry(String key, V value){
        this.added = System.currentTimeMillis();
        this.size = 0;
        this.key = key;
        this.value = value;
    }

}
