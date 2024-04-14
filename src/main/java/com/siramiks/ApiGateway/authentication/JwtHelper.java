package com.siramiks.ApiGateway.authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtHelper {

  @Value("${JWT_HELPER_SECRET}")
  public String SECRET;

  public void validateToken(final String token) {
    Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
  }

  private Key getSignKey() {
    byte[] keyBytes = Decoders.BASE64.decode(SECRET);
    return Keys.hmacShaKeyFor(keyBytes);
  }

  // methods to extract data from JWT token
  private Claims getClaimsFromToken(String token) {
    return Jwts.parser()
            .setSigningKey(getSignKey())
            .parseClaimsJws(token)
            .getBody();
  }

  public String getUsername(String token) {
    return getClaimsFromToken(token).getSubject();
  }

  public String getUserRole(String token) {
    return (String) getClaimsFromToken(token).get("userRole");
  }

  public String getUserId(String token) {
    return (String) getClaimsFromToken(token).get("userId");
  }

}
