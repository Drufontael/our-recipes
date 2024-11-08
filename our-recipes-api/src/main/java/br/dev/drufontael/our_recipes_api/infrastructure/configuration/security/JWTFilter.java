package br.dev.drufontael.our_recipes_api.infrastructure.configuration.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

public class JWTFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token=request.getHeader(JWTCreator.HEADER_AUTHORIZATION);
        try {
            if(token!=null && !token.isEmpty()){
                JWTObject tokenObject=JWTCreator.create(token,SecurityConfig.PREFIX,SecurityConfig.KEY);

                List<SimpleGrantedAuthority> authorities=authorities(tokenObject.getRoles());

                UsernamePasswordAuthenticationToken userToken =
                        new UsernamePasswordAuthenticationToken(
                                tokenObject.getSubject(),
                                null,
                                authorities
                        );

                SecurityContextHolder.getContext().setAuthentication(userToken);
            } else{
                SecurityContextHolder.clearContext();
            }
            filterChain.doFilter(request,response);
        } catch (ExpiredJwtException | MalformedJwtException | UnsupportedJwtException e){
            e.printStackTrace();
            response.setStatus(HttpStatus.FORBIDDEN.value());
            return;
        }
    }

    private List<SimpleGrantedAuthority> authorities(List<String> roles) {
        return roles.stream().map(SimpleGrantedAuthority::new).toList();
    }
}
