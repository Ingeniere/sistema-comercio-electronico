package com.ecommerce.jwt.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "aprende_programando_tu_mismo";

    private static final int TOKEN_VALIDITY = 3600 * 5;

    public String obtenerNombreDeUsuarioPorToken(String token) {
        return obtenerClaimsPorToken(token, Claims::getSubject);
    }

    public <T> T obtenerClaimsPorToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = obtenerTodosLosClaimsPorToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims obtenerTodosLosClaimsPorToken(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public boolean validarToken(String token, UserDetails userDetails) {
        final String username = obtenerNombreDeUsuarioPorToken(token);
        return (username.equals(userDetails.getUsername()) && !elTokenExpirado(token) );
    }

    private boolean elTokenExpirado(String token) {
        final Date expiration = obtenerLaFechaDeCaducidadDelToken(token);
        return expiration.before(new Date());
    }

    public Date obtenerLaFechaDeCaducidadDelToken(String token) {
        return obtenerClaimsPorToken(token, Claims::getExpiration);
    }

    public String generarToken(UserDetails userDetails) {

        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }
}
