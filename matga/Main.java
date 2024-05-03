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

        //disable
        Runtime.getRuntime().addShutdownHook(new Thread(boot::disable));
    }

}
