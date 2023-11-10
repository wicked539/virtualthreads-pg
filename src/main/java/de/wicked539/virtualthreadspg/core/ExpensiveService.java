package de.wicked539.virtualthreadspg.core;

import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ExpensiveService {

    public Long calculateExpensiveNumber() {
        Long result = 0L;
        for (long i = 0; i < 100_000_000L; i++) {
            result++;
        }
        return result;
    }

}
