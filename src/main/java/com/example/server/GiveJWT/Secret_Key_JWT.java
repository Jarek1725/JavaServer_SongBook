package com.example.server.GiveJWT;

import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;

public class Secret_Key_JWT {
    private static String Secret_Key_current;

    public static String getSecret_Key_current() {
        return Secret_Key_current;
    }

    public static void setSecretKey(){
        String uuid = UUID.randomUUID().toString().replace("-", "");
        uuid+=UUID.randomUUID().toString().replace("-", "");
        uuid+=UUID.randomUUID().toString().replace("-", "");
        uuid+=UUID.randomUUID().toString().replace("-", "");
        Secret_Key_current = uuid;
    }
}
