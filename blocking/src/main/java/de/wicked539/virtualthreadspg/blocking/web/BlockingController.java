package de.wicked539.virtualthreadspg.blocking.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicReference;

@RestController
public class BlockingController {

    private final ExpensiveService expensiveService;

    public BlockingController(ExpensiveService expensiveService) {
        this.expensiveService = expensiveService;
    }

    @GetMapping("/expensive")
    public ResponseEntity<Long> getExpensiveResource() {
        return ResponseEntity.ok(expensiveService.calculateExpensiveNumber());
    }

    @GetMapping("/virtual")
    public ResponseEntity<Long> getExpensiveResourceWithVirtualThread() {
        AtomicReference<Long> expensiveNumber = new AtomicReference<>();

        try {
            Thread.startVirtualThread(() -> {
                expensiveNumber.set(expensiveService.calculateExpensiveNumber());
            }).join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok(expensiveNumber.get());
    }
}
