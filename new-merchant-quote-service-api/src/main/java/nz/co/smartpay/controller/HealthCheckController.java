package nz.co.smartpay.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/health")
public class HealthCheckController {

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity healthCheck() {
        // @todo - Check health of related services etc
        return ResponseEntity.ok().build();
    }

}
