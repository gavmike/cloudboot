package ru.javaops.bootjava;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

@SpringBootApplication
@Slf4j
public class ScheduledExecutorRepeat {

    private static int count = 0;
    public static boolean completeTask;
    public static void delayMethod() throws InterruptedException {
        if(Math.random()>0.5){

            log.info("true");
            throw new RuntimeException("false");

        }

        else{

            log.info("false");
            /*try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                throw new InterruptedException();
            }*/
             throw new RuntimeException("false");
            // return false;
        }
    }
    public static void main(String[] args) throws InterruptedException {

        ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);

        Runnable task1 = () -> {
            count++;
            try {

                delayMethod();
                completeTask = true;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Running...task1 - count : " + count);
        };




    Callable taskCall = () -> {
            log.info("запущен clear");
            delayMethod();
            return true;
        };



        // init Delay = 5, repeat the task every 1 second
        ScheduledFuture<?> scheduledFuture = ses.scheduleAtFixedRate(task1, 0, 3, TimeUnit.SECONDS);

        while (true) {
            System.out.println(completeTask);
            Thread.sleep(1000);
            if (completeTask) {
                System.out.println(" cancel the scheduledFuture!");
                scheduledFuture.cancel(true);
                ses.shutdown();
                break;
            }
        }

    }
}