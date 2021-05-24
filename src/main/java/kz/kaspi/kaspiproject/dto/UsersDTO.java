package kz.kaspi.kaspiproject.dto;

import kz.kaspi.kaspiproject.entities.Roles;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UsersDTO {
    @NotNull(message = "Give id")
    private long id;

    @NotBlank(message = "Give name")
    private String name;

    @NotNull(message = "Give role")
    private Roles role;

    @NotNull(message = "Give password")
    private String password;

    public UsersDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
