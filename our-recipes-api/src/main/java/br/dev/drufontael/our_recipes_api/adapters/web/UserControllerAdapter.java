package br.dev.drufontael.our_recipes_api.adapters.web;

import br.dev.drufontael.our_recipes_api.adapters.web.dto.Login;
import br.dev.drufontael.our_recipes_api.adapters.web.dto.Session;
import br.dev.drufontael.our_recipes_api.adapters.web.dto.UserRequest;
import br.dev.drufontael.our_recipes_api.adapters.web.dto.UserResponse;
import br.dev.drufontael.our_recipes_api.domain.exception.UnauthorizedUserException;
import br.dev.drufontael.our_recipes_api.domain.model.User;
import br.dev.drufontael.our_recipes_api.domain.model.Role;
import br.dev.drufontael.our_recipes_api.domain.ports.in.ManageUserPort;
import br.dev.drufontael.our_recipes_api.infrastructure.configuration.security.JWTCreator;
import br.dev.drufontael.our_recipes_api.infrastructure.configuration.security.JWTObject;
import br.dev.drufontael.our_recipes_api.infrastructure.configuration.security.SecurityConfig;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/users")
@AllArgsConstructor
public class UserControllerAdapter {

    private final ManageUserPort manageUserPort;
    private final PasswordEncoder encoder;

    @PostMapping
    public ResponseEntity<UserResponse> register(@RequestBody UserRequest userRequest) {
        Long id = manageUserPort.register(userRequest.toDomain(),encoder).getId();
        return ResponseEntity.ok(new UserResponse(id,userRequest.username(),userRequest.email()));
    }

    @GetMapping
    public ResponseEntity<Session> login(@RequestBody Login login) {
        User user = manageUserPort.findByUsername(login.username());
        if (!encoder.matches(login.password(), user.getPassword()))
            throw new UnauthorizedUserException("Incorrect password for user"+login.username());

        JWTObject jwtObject = new JWTObject();
        jwtObject.setSubject(user.getUsername());
        jwtObject.setRoles(user.getRoles().stream().map(Role::getRole).toList());
        String token = JWTCreator.create(SecurityConfig.PREFIX, SecurityConfig.KEY,jwtObject);

        return ResponseEntity.ok(new Session(user.getUsername(), token));
    }

    @GetMapping("/yes")
    public ResponseEntity<String> access() { //TODO: Remover este endpoint
        // Recupera a autenticação do SecurityContext
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        // Verifica se a autenticação está presente e se é do tipo esperado
        if (authentication != null && authentication.isAuthenticated()) {
            // Obtém o nome do usuário a partir do token
            String username = authentication.getName();
            authentication.getAuthorities().forEach(System.out::println);
            return ResponseEntity.ok("Access granted to user: " + username);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Access denied");
    }

    @GetMapping("/no")
    public ResponseEntity<String> dontAccess() { //TODO: Remover este endpoint
        // Recupera a autenticação do SecurityContext
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        // Verifica se a autenticação está presente e se é do tipo esperado
        if (authentication != null && authentication.isAuthenticated()) {
            // Obtém o nome do usuário a partir do token
            String username = authentication.getName();
            authentication.getAuthorities().forEach(System.out::println);
            return ResponseEntity.ok("Access granted to user: " + username);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Access denied");
    }




}
