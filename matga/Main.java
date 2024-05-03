package de.matga;

import de.matga.bootstrap.Boot;
import de.matga.cache.LoadingCache;
import de.matga.cache.buildings.CacheBuilder;
import de.matga.cache.buildings.CacheImpMethods;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {

        final Boot boot = new Boot().enable();

        Runtime.getRuntime().addShutdownHook(new Thread(boot::disable));

        LoadingCache<Integer> cache = new CacheBuilder<Integer>()
                .setExpireAfter(4)
                .setTimeStamp(TimeUnit.SECONDS)
                .setUpdateDelay(5)
                .setMaxSize(2)
                .build(new CacheImpMethods<>() {
            @Override
            public Integer fetch(String key) {
                return null;
            }

            @Override
            public void write(String key, Integer value) {

            }
        });



        while (true) {
            new Scanner(System.in).nextLine();
        }

        //disable


    }

}