package br.dev.drufontael.our_recipes_api.adapters.persistence.repository.entities;

import br.dev.drufontael.our_recipes_api.domain.model.Role;
import br.dev.drufontael.our_recipes_api.domain.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tb_users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false, length = 50)
    private String username;
    private String password;
    @Column(length = 100)
    private String email;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "tb_roles",joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role_id")
    @Cascade(CascadeType.ALL)
    private List<String> roles = new ArrayList<>();



    public UserEntity(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.roles.addAll(user.getRoles().stream().map(Role::getRole).toList());
    }

    public User toDomain() {

        User user = new User(id, username, password, email);
        for (String role : roles) {
            user.addRole(Role.valueOf(role.replace("ROLE_", "")));
        }
        return user;

    }


}
