package de.matga.bootstrap;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Boot {

    @Getter
    public static Boot instance;

    public Boot enable() {
        instance = this;

        return this;
    }

    public void disable() {

    }

}
