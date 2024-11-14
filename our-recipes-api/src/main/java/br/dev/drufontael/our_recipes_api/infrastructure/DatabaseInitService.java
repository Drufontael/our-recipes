package br.dev.drufontael.our_recipes_api.infrastructure;

import br.dev.drufontael.our_recipes_api.domain.model.Role;
import br.dev.drufontael.our_recipes_api.domain.model.User;
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
@Profile("dev")
public class DatabaseInitService {

    private final JdbcTemplate jdbcTemplate;
    private final ManageUserPort manageUserPort;
    private final PasswordEncoder encoder;

    @Value("${script.sql.path}")
    private String scriptPath;
    @Value("${security.config.root}")
    private String securityConfigRoot;
    @Value("${security.config.root.password}")
    private String securityConfigRootPassword;

    public DatabaseInitService(JdbcTemplate jdbcTemplate, ManageUserPort manageUserPort, PasswordEncoder encoder) {
        this.jdbcTemplate = jdbcTemplate;
        this.manageUserPort = manageUserPort;
        this.encoder = encoder;
    }

    @PostConstruct
    public void runScript() throws IOException {
        String sql = new String(Files.readAllBytes(Paths.get(scriptPath)));
        jdbcTemplate.execute(sql);
    }

    @PostConstruct
    public void createRootUser() {
        User root = new User();
        root.setUsername(securityConfigRoot);
        root.setPassword(securityConfigRootPassword);
        root=manageUserPort.register(root, encoder);
        manageUserPort.addRole(root, Role.ADMIN);
    }
}

