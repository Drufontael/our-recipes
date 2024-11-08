package br.dev.drufontael.our_recipes_api.infrastructure.configuration.bean;

import br.dev.drufontael.our_recipes_api.domain.ports.out.PersistenceUserPort;
import br.dev.drufontael.our_recipes_api.domain.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class ServiceConfig {

    private final PersistenceUserPort persistence;

    @Bean
    public UserService userService() {
        return new UserService(persistence);
    }
}
