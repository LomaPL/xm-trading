package pl.loma.xm.core;

import lombok.experimental.UtilityClass;

import java.time.Duration;

@UtilityClass
public final class Constants {

    public static final int FACTORY_TIMEOUT = 10;
    public static final Duration WAIT_TIMEOUT = Duration.ofSeconds(15);

}
