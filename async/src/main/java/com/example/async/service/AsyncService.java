package com.example.async.service;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class AsyncService {

    @Async("async-thread")
    public CompletableFuture run(){
        return new AsyncResult(hello()).completable();
    }

    public String hello() {

        for(int i = 0; i<10; i++) {
            try {
                Thread.sleep(2000);
                log.info("thread sleep ...");

            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        return "async hello";
    }
}
