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
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/v1/api/users")
@AllArgsConstructor
@Slf4j
//@CrossOrigin("*")
public class UserControllerAdapter {

    private final ManageUserPort manageUserPort;
    private final PasswordEncoder encoder;

    @PostMapping("register")
    public ResponseEntity<UserResponse> register(@RequestBody UserRequest userRequest) {
        log.info("Recebendo registro: {}", userRequest);
        Long id = manageUserPort.register(userRequest.toDomain(), encoder).getId();
        UserResponse userResponse = new UserResponse(id, userRequest.username(), userRequest.email());
        log.info("Usuário criado com ID: {}", id);
        ResponseEntity<UserResponse> response=ResponseEntity.created(URI.create("/v1/api/users/"+id)).body(userResponse);
        log.info("Retornando response: {}", response);
        return response;
    }

    @PostMapping("login")
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


}
