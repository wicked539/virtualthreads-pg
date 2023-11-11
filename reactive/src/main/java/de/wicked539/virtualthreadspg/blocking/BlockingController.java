package de.wicked539.virtualthreadspg.blocking;

import de.wicked539.virtualthreadspg.core.ExpensiveService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlockingController {

    private final ExpensiveService expensiveService;

    public BlockingController(ExpensiveService expensiveService) {
        this.expensiveService = expensiveService;
    }

    @GetMapping("/blocking")
    public ResponseEntity<Long> getExpensiveResource() {
        return ResponseEntity.ok(expensiveService.calculateExpensiveNumber());
    }
}
