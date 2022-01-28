package ru.javaops.bootjava;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class ClearCache {
//for test
    //for amend
    //for amend2
    //for amend3

    ExecutorService executor = Executors.newFixedThreadPool(1, new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r,"clearCacheClass");
        }
    });
    Future<?> future;
    public void clear(){
      /*   future = executor.submit(() -> {
             log.info("запущен clear");
               delayMethod();

                //this.latch.countDown();

        });*/

        future = executor.submit((Callable) () -> {
            log.info("запущен clear");
            delayMethod();
            return true;
        });

    }


    /*public boolean clearCashe() throws InterruptedException, ExecutionException, TimeoutException {
        log.info("запущен clearCashe");
        clear();
        Object o = future.get(2, TimeUnit.SECONDS);
        log.info("получили значение в clearCashe {} ",o);
        return (boolean) o;
    }*/
    public boolean clearCashe() throws InterruptedException, ExecutionException, TimeoutException {
        log.info("запущен clearCashe");
        clear();
        executor.shutdown();
        //boolean awaitTermination = executor.awaitTermination(5, TimeUnit.SECONDS);
        log.info("after awaitTerm");
        Object o = future.get(2, TimeUnit.SECONDS);
        log.info("получили значение в clearCashe {} ",o);
        return (boolean) o;
      //  return awaitTermination;

    }

    public void delayMethod() throws InterruptedException {
        if(Math.random()>0.5){

            log.info("true");
          //  throw new RuntimeException("false");

        }

        else{

            log.info("false");
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                throw new InterruptedException();
            }
            // throw new RuntimeException("false");
            // return false;
        }
    }


}
