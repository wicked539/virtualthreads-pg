package de.wicked539.virtualthreadspg.reactive;

import de.wicked539.virtualthreadspg.core.ExpensiveService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class ReactiveController {

    private final ExpensiveService expensiveService;

    public ReactiveController(ExpensiveService expensiveService) {
        this.expensiveService = expensiveService;
    }

    @GetMapping("/reactive")
    public Mono<Long> getExpensiveResource() {
        return Mono.fromCallable(expensiveService::calculateExpensiveNumber);
    }
}
