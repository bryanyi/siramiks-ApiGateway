package com.siramiks.ApiGateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(
        value = "/fallback",
        produces = "application/json",
        method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}
)
public class FallbackController {

  @GetMapping("/productServiceFallback")
  @PostMapping("/productServiceFallback")
  public ResponseEntity<String> productServiceFallback() {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Issue detected in product-service");
  }

  @GetMapping("/orderServiceFallback")
  @PostMapping("/orderServiceFallback")
  public ResponseEntity<String> orderServiceFallback() {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Issue detected in order-service");
  }

  @GetMapping("/paymentServiceFallback")
  @PostMapping("/paymentServiceFallback")
  public ResponseEntity<String> paymentServiceFallback() {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Issue detected in payment-service");
  }

}
