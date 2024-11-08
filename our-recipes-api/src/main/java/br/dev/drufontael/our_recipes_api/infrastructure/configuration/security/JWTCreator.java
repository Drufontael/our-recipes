package br.dev.drufontael.our_recipes_api.infrastructure.configuration.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.List;

public class JWTCreator {
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String ROLES_AUTHORITIES = "authorities";

    public static String create(String prefix, String key, JWTObject jwtObject){
        Key signingKey = Keys.hmacShaKeyFor(key.getBytes());
        String token= Jwts.builder()
                .setSubject(jwtObject.getSubject())
                .setIssuedAt(jwtObject.getIssueAt())
                .setExpiration(jwtObject.getExpiration())
                .claim(ROLES_AUTHORITIES,checkRoles(jwtObject.getRoles()))
                .signWith(signingKey, SignatureAlgorithm.HS256)
                .compact();
        return prefix+" "+token;
    }

    private static List<String> checkRoles(List<String> roles) {
        return roles.stream().map(s->"ROLE_".concat(s.replace("ROLE_",""))).toList();
    }

    public static JWTObject create(String token,String prefix,String key){
        JWTObject object = new JWTObject();
        token=token.replace(prefix,"").trim();
        //Claims claims=Jwts.parser().setSigningKey(key).parseClaimsJwt(token).getBody();
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(key.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody();
        object.setSubject(claims.getSubject());
        object.setIssueAt(claims.getIssuedAt());
        object.setExpiration(claims.getExpiration());
        object.setRoles(((List)claims.get(ROLES_AUTHORITIES)));
        return object;
    }
}
