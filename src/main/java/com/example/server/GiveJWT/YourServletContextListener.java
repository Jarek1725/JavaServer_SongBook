package com.example.server.GiveJWT;

import javax.servlet.ServletContextEvent;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class YourServletContextListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent event) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        Runnable task = Secret_Key_JWT::setSecretKey;
        executor.scheduleWithFixedDelay(task, 0, 60, TimeUnit.MINUTES);
    }

    public void contextDestroyed(ServletContextEvent event) {
        // Place here the code to run just before the application goes down
    }

}