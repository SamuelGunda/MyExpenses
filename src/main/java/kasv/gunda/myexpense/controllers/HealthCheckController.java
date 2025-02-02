package kasv.gunda.myexpense.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/healthCheck")
public class HealthCheckController {

    @RequestMapping("")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("I am alive!");
    }
}

