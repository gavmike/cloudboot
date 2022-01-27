package ru.javaops.bootjava;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class Servicetest {
    Environment environment;
    @Value("${executers.time.time-out:30}")
    int timeOut;

    //private final ScheduledExecutorService executorService;
    public void serviceMethod() throws InterruptedException, ExecutionException {
        ScheduledExecutorService scheduledExecutorService = Executors
                .newScheduledThreadPool(1,new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        return new Thread(r,"clearCache");
                    }
                });


        ScheduledFuture<?> scheduledFuture =
                scheduledExecutorService.scheduleAtFixedRate(()->{

                        log.info("новая задача");

                    try {
                        ClearCache clearCache = new ClearCache();
                        boolean b = clearCache.clearCashe();
                        if(b==true){
                            System.out.println("Отбой");
                            scheduledExecutorService.shutdownNow();
                            System.out.println("isShutDown = "+scheduledExecutorService.isShutdown());
                        }
                    } catch (InterruptedException | ExecutionException | TimeoutException e) {
                        e.printStackTrace();
                    }


                }, 0L, 5L, TimeUnit.SECONDS);

        //scheduledExecutorService.shutdown();
        scheduledExecutorService.schedule((Callable) () -> {
            log.info("задача отменена по таймауту");
            boolean cancel = scheduledFuture.cancel(false);
            System.out.println("isShutDown = "+scheduledExecutorService.isShutdown());
            return cancel;
        },30,TimeUnit.SECONDS);
        System.out.println("isDone =" + scheduledFuture.isDone());
        System.out.println("value = "+timeOut);

        /*boolean timeOut = scheduledExecutorService.awaitTermination(5, TimeUnit.SECONDS);
        System.out.println("timeOut = "+timeOut);
        if(timeOut) {
            scheduledFuture.cancel(false);
            scheduledExecutorService.shutdown();
        }*/
       // Object o = scheduledFuture.get();
       /* Object result = null;
        try {
            result = scheduledFuture.get(3, TimeUnit.SECONDS);
            log.info("result ="+result.toString());
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            log.info("не удалось очистить кэш "+e);
        }

        if( result!=null && (boolean) result ==true ) {
            log.info("задача завершилась по успешному ответу");
            // Thread.sleep(60000);
           scheduledFuture.cancel(false);
           scheduledExecutorService.shutdown();
        }*/




    }
    public void release(String key) throws InterruptedException, ExecutionException, TimeoutException {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        ScheduledFuture<?> future = scheduledExecutorService.scheduleAtFixedRate(() -> {
            environment.getActiveProfiles();
            environment.getDefaultProfiles();
            environment.getProperty("sdfs");
        }, 1L, 5L, TimeUnit.SECONDS);



    }


}
