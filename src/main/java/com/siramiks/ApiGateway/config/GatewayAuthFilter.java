package com.siramiks.ApiGateway.config;

import com.siramiks.ApiGateway.authentication.JwtHelper;
import com.siramiks.ApiGateway.authentication.RouteValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import static org.springframework.http.HttpHeaders.*;

@Component
public class GatewayAuthFilter extends AbstractGatewayFilterFactory<GatewayAuthFilter.Config> {

  @Autowired
  private RouteValidator routeValidator;
  @Autowired
  private JwtHelper jwtHelper;

  public GatewayAuthFilter() {
    super(Config.class);
  }

  @Override
  public GatewayFilter apply(Config config) {
    return ((exchange, chain) -> {
      ServerHttpRequest req = null;

      if (routeValidator.isSecured.test(exchange.getRequest())) {
        // check if header contains token
        if (!exchange.getRequest().getHeaders().containsKey(AUTHORIZATION)) {
          throw new RuntimeException("Missing auth headers");
        }

        String authToken = exchange.getRequest().getHeaders().get(AUTHORIZATION).get(0);

        if (authToken != null && authToken.startsWith("Bearer ")) {
          // parsing out the actual token because it  you pass it with "Bearer ", we need to take out that bearer word.
          authToken = authToken.substring(7);
        }
        try {
          // instead of calling the api directly, utilize jwt util
          jwtHelper.validateToken(authToken);

          req = exchange
                  .getRequest()
                  .mutate()
                  .header("userId", jwtHelper.getUserId(authToken))
                  .header("userName", jwtHelper.getUsername(authToken))
                  .header("userRole", jwtHelper.getUserRole(authToken))
                  .build();

        } catch (Exception e) {
          System.out.println("INVALID TOKEN -  Gateway attempted to filter token but token invalid");
          throw new RuntimeException("invalid token");

        }
      }

      return chain.filter(exchange.mutate().request(req).build());
    });
  }

  public static class Config {

  }
}
