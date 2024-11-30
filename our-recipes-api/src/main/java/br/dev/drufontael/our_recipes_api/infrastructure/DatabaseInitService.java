package br.dev.drufontael.our_recipes_api.infrastructure;

import br.dev.drufontael.our_recipes_api.domain.exception.UserNotFoundException;
import br.dev.drufontael.our_recipes_api.domain.model.*;
import br.dev.drufontael.our_recipes_api.domain.ports.in.ManageIngredientPort;
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
@Profile("prod")
public class DatabaseInitService {

    private final JdbcTemplate jdbcTemplate;
    private final ManageUserPort manageUserPort;
    private final ManageIngredientPort manageIngredientPort;
    private final PasswordEncoder encoder;

    @Value("${script.sql.path}")
    private String scriptPath;
    @Value("${security.config.root}")
    private String securityConfigRoot;
    @Value("${security.config.root.password}")
    private String securityConfigRootPassword;

    public DatabaseInitService(JdbcTemplate jdbcTemplate, 
                               ManageUserPort manageUserPort, 
                               PasswordEncoder encoder,
                               ManageIngredientPort manageIngredientPort) {
        this.manageIngredientPort = manageIngredientPort;
        this.jdbcTemplate = jdbcTemplate;
        this.manageUserPort = manageUserPort;
        this.encoder = encoder;
    }

    @PostConstruct
    public void init() throws IOException {
        //runScript();
        createRootUser();
    }


    public void runScript() throws IOException {
        if(!manageIngredientPort.getAll().isEmpty()) return;
        String sql = new String(Files.readAllBytes(Paths.get(scriptPath)));
        jdbcTemplate.execute(sql);
    }


    public void createRootUser() {
        User root = new User();
        try {
            manageUserPort.findByUsername(securityConfigRoot);
        }catch (UserNotFoundException e){
            root.setUsername(securityConfigRoot);
            root.setPassword(securityConfigRootPassword);
            root=manageUserPort.register(root, encoder);
            manageUserPort.addRole(root, Role.ADMIN);
        }
    }


}

