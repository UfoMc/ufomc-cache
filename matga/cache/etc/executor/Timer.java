package de.matga.cache.etc.executor;

import de.matga.cache.buildings.LoadingCacheBuilderImp;
import de.matga.cache.mapping.Entry;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Timer<V> {

    public void run(long updateDelay, LoadingCacheBuilderImp<V> methods) {

        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(()->{

            System.out.print("updated");
            update(methods);

        }, updateDelay, updateDelay, TimeUnit.SECONDS);

    }

    public void update(LoadingCacheBuilderImp<V> methods) {

        for (Entry<V> entry : methods.getEntries()){

            if (entry == null){
                continue;
            }

            if (entry.getAdded() <= System.currentTimeMillis() - methods.getExpireTime()) {
                methods.getLoadingCache().remove(entry.getKey());
            }

        }

    }

}
