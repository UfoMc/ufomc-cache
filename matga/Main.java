package de.matga;

import de.matga.bootstrap.Boot;

public class Main {

    public static void main(String[] args) {

        final Boot boot = new Boot().enable();

        Runtime.getRuntime().addShutdownHook(new Thread(boot::disable));

        //disable

    }

}