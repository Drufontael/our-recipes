package br.dev.drufontael.our_recipes_api.infrastructure.configuration.security;

import lombok.Data;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Data
public class JWTObject {

    private String subject;
    private Date issueAt=new Date(System.currentTimeMillis());
    private Date expiration=new Date(System.currentTimeMillis()+SecurityConfig.EXPIRATION);
    private List<String> roles;

    public void setRoles(String... roles){
        this.roles= Arrays.asList(roles);
    }


    public void setRoles(List list) {
        this.roles=list;
    }
}
