package com.kpl.registration.service.Student;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class AsyncCallService {
    @Async
    public void asyncCall() throws InterruptedException {
        Thread.sleep(10000);
        log.info("Async Call Executed at {}", LocalDateTime.now());
    }
}
