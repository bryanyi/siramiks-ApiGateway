package com.siramiks.ApiGateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class ApiGatewayApplication {

  public static void main(String[] args) {
    SpringApplication.run(ApiGatewayApplication.class, args);
  }

  @Bean
  KeyResolver userKeySolver() {
    return exchange -> Mono.just("userKey");
  }
//	@Bean
//	KeyResolver authUserKeyResolver() {
//		return exchange -> ReactiveSecurityContextHolder.getContext()
//						.map(ctx -> ctx.getAuthentication()
//										.getCredentials().toString());
//	}

//	@Bean
//	public Customizer<Resilience4JCircuitBreakerFactory> defaultCustomizer() {
//		return factory -> factory.configureDefault(
//						id -> new Resilience4JConfigBuilder(id)
//										.circuitBreakerConfig(
//														CircuitBreakerConfig.ofDefaults()
//
//										)
//										.timeLimiterConfig(TimeLimiterConfig
//														.custom()
//														.timeoutDuration(Duration.ofSeconds(60)).build())
//										.build()
//		);
//	}

}
