package br.dev.drufontael.our_recipes_api.infrastructure.configuration.bean;

import br.dev.drufontael.our_recipes_api.domain.ports.out.*;
import br.dev.drufontael.our_recipes_api.domain.service.*;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class ServiceConfig {

    private final PersistenceUserPort persistenceUserPort;
    private final PersistenceIngredientPort persistenceIngredientPort;
    private final PersistenceMeasurementUnitPort persistenceMeasurementUnitPort;
    private final PersistenceRecipePort persistenceRecipePort;
    private final PersistenceTagPort persistenceTagPort;

    @Bean
    public UserService userService() {
        return new UserService(persistenceUserPort);
    }

    @Bean
    public IngredientService ingredientService() {
        return new IngredientService(persistenceIngredientPort);
    }

    @Bean
    public MeasurementUnitService measurementUnitService() {
        return new MeasurementUnitService(persistenceMeasurementUnitPort);
    }

    @Bean
    public RecipeService recipeService() {
        return new RecipeService(persistenceRecipePort,persistenceUserPort);
    }

    @Bean
    public TagService tagService() {
        return new TagService(persistenceTagPort);
    }
}
