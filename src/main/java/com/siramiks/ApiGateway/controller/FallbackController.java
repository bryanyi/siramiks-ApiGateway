package com.siramiks.ApiGateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

  @GetMapping("productServiceFallback")
  public ResponseEntity<String> productServiceFallback() {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Issue detected in product-service");
  }

  @GetMapping("orderServiceFallback")
  public ResponseEntity<String> orderServiceFallback() {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Issue detected in order-service");
  }

  @GetMapping("paymentServiceFallback")
  public ResponseEntity<String> paymentServiceFallback() {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Issue detected in payment-service");
  }

}
