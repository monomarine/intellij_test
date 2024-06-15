package com.dop.resapi.autorization;

import com.dop.resapi.models.User;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtil {
    private final String key = "anythingstringforcecretkey";
    private final long tokenCheker = 60 * 60 * 1000;
    private final String TOKEN_HEADER = "Authorization";
    private final String TOKEN_PREFIX = "Bearer ";
    private final JwtParser parser;


    public JwtUtil() {
        parser = Jwts.parser().setSigningKey((key));
    }

    public String newToken(User user) {
        Claims cl = Jwts.claims().setSubject(user.getEmail());
         cl.put("name", user.getName());

        Date tokenCreationDate = new Date();
        Date tokenCheck = new Date((tokenCreationDate.getTime() + TimeUnit.MINUTES.toMillis(tokenCheker)));
        return Jwts.builder()
                .setClaims(cl)
                .setExpiration(tokenCheck)
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }

    public String acceptToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(TOKEN_HEADER);
        if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.substring((TOKEN_PREFIX.length()));
        }
        return null;
    }

    public Claims acceptClaims(HttpServletRequest request) {
        try {
            String token = acceptToken(request);
            if (token != null) {
                return readJwtClaims(token);
            }
            return null;
        }
        catch (ExpiredJwtException ex) {
            request.setAttribute("Доступ истек", ex.getMessage());
            throw ex;
        }
        catch (Exception ex){
            request.setAttribute("Нет доступа", ex.getMessage());
            throw ex;
        }
    }

    public Claims readJwtClaims(String token){
        return  parser.parseClaimsJwt(token).getBody();
    }

    public boolean validateClaims(Claims claims) throws AuthenticationException{
        try{
            return claims.getExpiration().after(new Date());
        }
        catch(Exception ex){
            throw ex;
        }
    }

    public String getEmail(Claims claims){
        return claims.getSubject();
    }

    public List<String> getRoles(Claims claims){
        return (List<String>) claims.get("roles");
    }
}

