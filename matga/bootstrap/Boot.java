package de.matga.bootstrap;

import de.matga.cache.LoadingCache;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Boot {

    public List<LoadingCache<?>> caches = new ArrayList<>();

    @Getter
    public static Boot instance;

    public Boot enable() {
        instance = this;

        return this;
    }

    public void disable() {

       for (LoadingCache<?> cache : caches){

       }

    }

}
