package br.dev.drufontael.our_recipes_api.infrastructure.configuration.bean;

import br.dev.drufontael.our_recipes_api.domain.ports.out.PersistenceIngredientPort;
import br.dev.drufontael.our_recipes_api.domain.ports.out.PersistenceUserPort;
import br.dev.drufontael.our_recipes_api.domain.service.IngredientService;
import br.dev.drufontael.our_recipes_api.domain.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class ServiceConfig {

    private final PersistenceUserPort persistenceUserPort;
    private final PersistenceIngredientPort persistenceIngredientPort;

    @Bean
    public UserService userService() {
        return new UserService(persistenceUserPort);
    }

    @Bean
    public IngredientService ingredientService() {
        return new IngredientService(persistenceIngredientPort);
    }
}
