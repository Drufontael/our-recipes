package br.dev.drufontael.our_recipes_api.infrastructure.configuration.security;

import br.dev.drufontael.our_recipes_api.infrastructure.exceptionHandler.ErrorResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.DecodingException;
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
import java.util.Date;
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
        } catch (ExpiredJwtException e){
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json");


            String jsonResponse = getErrorMessage(HttpStatus.UNAUTHORIZED.toString(), e.getMessage(), request);

            response.getWriter().write(jsonResponse);

        }catch (MalformedJwtException | DecodingException | UnsupportedJwtException e){
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setContentType("application/json");

            String jsonResponse = getErrorMessage(HttpStatus.BAD_REQUEST.toString(), e.getMessage(), request);

            response.getWriter().write(jsonResponse);
        }
    }

    private List<SimpleGrantedAuthority> authorities(List<String> roles) {
        return roles.stream().map(SimpleGrantedAuthority::new).toList();
    }

    private String getErrorMessage(String status, String message, HttpServletRequest request) throws JsonProcessingException {
        ErrorResponse errorDetails= new ErrorResponse(new Date(), status, message, "uri=" + request.getRequestURI());
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(errorDetails);
    }
}
