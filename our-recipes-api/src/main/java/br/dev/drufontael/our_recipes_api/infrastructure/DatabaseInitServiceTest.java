package br.dev.drufontael.our_recipes_api.infrastructure;

import br.dev.drufontael.our_recipes_api.domain.model.*;
import br.dev.drufontael.our_recipes_api.domain.ports.in.ManageRecipePort;
import br.dev.drufontael.our_recipes_api.domain.ports.in.ManageUserPort;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
@Profile("test")
public class DatabaseInitServiceTest {

    private final JdbcTemplate jdbcTemplate;
    private final ManageUserPort manageUserPort;
    private final ManageRecipePort manageRecipePort;
    private final PasswordEncoder encoder;

    @Value("${script.sql.path}")
    private String scriptPath;
    @Value("${security.config.root}")
    private String securityConfigRoot;
    @Value("${security.config.root.password}")
    private String securityConfigRootPassword;

    public DatabaseInitServiceTest(JdbcTemplate jdbcTemplate, ManageUserPort manageUserPort, PasswordEncoder encoder,
                                   ManageRecipePort manageRecipePort) {
        this.manageRecipePort = manageRecipePort;
        this.jdbcTemplate = jdbcTemplate;
        this.manageUserPort = manageUserPort;
        this.encoder = encoder;
    }

    @PostConstruct
    public void init() throws IOException {
        runScript();
        createRootUser();
        createDefaultRecipes();
    }


    public void runScript() throws IOException {
        String sql = new String(Files.readAllBytes(Paths.get(scriptPath)));
        jdbcTemplate.execute(sql);
    }


    public void createRootUser() {
        User root = new User();
        root.setUsername(securityConfigRoot);
        root.setPassword(securityConfigRootPassword);
        root=manageUserPort.register(root, encoder);
        manageUserPort.addRole(root, Role.ADMIN);
    }


    public void createDefaultRecipes() {
        User testador=new User();
        testador.setUsername("testador");
        testador.setPassword("123456");
        testador=manageUserPort.register(testador, encoder);

        //Arroz branco
        manageRecipePort.createRecipe("Arroz branco","Arroz branco normal", 2,15, Difficulty.BEGINNER, testador);
        manageRecipePort.addTag(1L,new Tag("sem_gluten"),testador);
        manageRecipePort.addTag(1L,new Tag("sem_lactose"),testador);
        manageRecipePort.addTag(1L,new Tag("vegano"),testador);
        manageRecipePort.addTag(1L,new Tag("acompanhamento"),testador);
        manageRecipePort.addIngredient(1L,
                new RecipeIngredient(new Ingredient(98L,"arroz",null),
                        1,
                        new MeasurementUnit(6L,"xícara","xíc")),
                testador);
        manageRecipePort.addIngredient(1L,
                new RecipeIngredient(new Ingredient(38L,"água",null),
                        2,
                        new MeasurementUnit(6L,"xícara","xíc")),
                testador);
        manageRecipePort.addIngredient(1L,
                new RecipeIngredient(new Ingredient(98L,"arroz",null),
                        1,
                        new MeasurementUnit(6L,"xícara","xíc")),
                testador);
        manageRecipePort.addIngredient(1L,
                new RecipeIngredient(new Ingredient(11L,"cebola",null),
                        0.5,
                        new MeasurementUnit(5L,"xícara","xíc")),
                testador);
        manageRecipePort.addIngredient(1L,
                new RecipeIngredient(new Ingredient(3L,"óleo vegetal",null),
                        1,
                        new MeasurementUnit(8L,"xícara","xíc")),
                testador);
        manageRecipePort.addStep(1L,new Step(1,"Ferva a agua separadamente"),testador);
        manageRecipePort.addStep(1L,new Step(2,"Refoque a cebola e o alho picados"),testador);
        manageRecipePort.addStep(1L,new Step(3,"Junte o arroz aos ingredientes refogados e frite levemente"),testador);
        manageRecipePort.addStep(1L,new Step(4,"Junte a agua fervente, tempere com sal e deixe cozinhar"),testador);

        //Feijão
        manageRecipePort.createRecipe("Feijão","Feijão normal",4,50, Difficulty.BEGINNER, testador);
        manageRecipePort.addTag(2L,new Tag("sem_gluten"),testador);
        manageRecipePort.addTag(2L,new Tag("sem_lactose"),testador);
        manageRecipePort.addTag(2L,new Tag("vegano"),testador);
        manageRecipePort.addTag(2L,new Tag("acompanhamento"),testador);
        manageRecipePort.addIngredient(2L,
                new RecipeIngredient(new Ingredient(99L,"feijão",null),
                        1,
                        new MeasurementUnit(6L,"xícara","xíc")),
                testador);
        manageRecipePort.addIngredient(2L,
                new RecipeIngredient(new Ingredient(41L,"caldo de legumes",null),
                        1,
                        new MeasurementUnit(21L,"xícara","xíc")),
                testador);
        manageRecipePort.addIngredient(2L,
                new RecipeIngredient(new Ingredient(16L,"louro",null),
                        1,
                        new MeasurementUnit(5L,"xícara","xíc")),
                testador);
        manageRecipePort.addStep(2L,new Step(1,"Deixe o feijão de molho por 2 horas."),testador);
        manageRecipePort.addStep(2L,new Step(2,"Escorra e transfira para uma panela de pressão."),testador);
        manageRecipePort.addStep(2L,new Step(3,"Junte a água, o tempero pronto, o sal e o louro."),testador);
        manageRecipePort.addStep(2L,new Step(4,"Deixe cozinhar, em fogo baixo, por 20 minutos após o início da fervura."),testador);

        manageRecipePort.createRecipe("Sopa de Legumes","Sopa nutritiva de legumes.",4,30, Difficulty.BEGINNER, testador);

        manageRecipePort.createRecipe("Sanduiche de Mortadela","Simples, gostoso e popular.",1,5, Difficulty.BEGINNER, testador);

        manageRecipePort.createRecipe("Vitamina de Banana","Ideal para o dia a dia.",1,5, Difficulty.BEGINNER, testador);




    }
}

