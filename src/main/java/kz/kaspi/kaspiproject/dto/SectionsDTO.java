package kz.kaspi.kaspiproject.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SectionsDTO {
    @NotNull(message = "Give ID")
    private int id;

    @NotBlank(message = "Give name")
    private String name;

    public SectionsDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
