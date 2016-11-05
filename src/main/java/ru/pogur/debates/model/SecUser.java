package ru.pogur.debates.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by dima-pc on 06.02.2016.
 */
@Entity
@Table(name = "sec_user")
public class SecUser {
    private Long id;

    private String username;
    private String password;

    private List<SecRole> roles;

    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @ManyToMany(targetEntity = SecRole.class, fetch = FetchType.EAGER)
    public List<SecRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SecRole> roles) {
        this.roles = roles;
    }
}
