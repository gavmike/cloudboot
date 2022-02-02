package ru.javaops.bootjava;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.javaops.bootjava.repository.UserRepository;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@SpringBootApplication
@RequiredArgsConstructor
public class RestaurantVotingApplication implements ApplicationRunner {
@Autowired(required = false)
TestService testService;

private final     Servicetest servicetest;

@Autowired
UserRepository userRepository;

    //for commit developer first
    //second commit from origin

    //3 commit
    //commit in feature task 1
    //commit idea
    //for сквош

    public static void main(String[] args)  {
        SpringApplication.run(RestaurantVotingApplication.class, args);
    }
/*
    @Override
    public void run(String... args) throws Exception {
        System.out.println("test");
        testService.test();
    }*/

    @Override
    public void run(ApplicationArguments args) throws InterruptedException, TimeoutException, ExecutionException {

       // System.out.println(userRepository.findAll());
        servicetest.serviceMethod();
        Thread.sleep(30000);
        //System.out.println(userRepository.findAll());
        servicetest.serviceMethod();
        /*Thread.sleep(90000);
        System.out.println(userRepository.findAll());
        servicetest.serviceMethod();
        Thread.sleep(90000);
        System.out.println(userRepository.findAll());
        servicetest.serviceMethod();
        Thread.sleep(90000);
        System.out.println(userRepository.findAll());
        servicetest.serviceMethod();
        Thread.sleep(90000);*/

      //  System.out.println(userRepository.findAll());
      //  servicetest.serviceMethod();
    }
}
