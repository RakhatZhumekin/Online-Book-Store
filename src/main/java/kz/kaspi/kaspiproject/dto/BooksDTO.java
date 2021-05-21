package kz.kaspi.kaspiproject.dto;

import kz.kaspi.kaspiproject.entities.Authors;
import kz.kaspi.kaspiproject.entities.Books.Language;
import kz.kaspi.kaspiproject.entities.Books.Status;
import kz.kaspi.kaspiproject.entities.Sections;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class BooksDTO {
    @NotNull(message = "Give ID")
    private int id;

    @NotBlank(message = "Give name")
    private String name;

    @NotNull(message = "Give author")
    private Authors author;

    @NotNull(message = "Give section")
    private Sections section;

    @NotNull(message = "Give language")
    private Language language;

    @Min(value = 1, message = "Price can't be less than 1")
    private int price;

    @Min(value = 0, message = "Quantity can't be less than 0")
    private int quantity;

    public BooksDTO() {
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

    public Authors getAuthor() {
        return author;
    }

    public void setAuthor(Authors author) {
        this.author = author;
    }

    public Sections getSection() {
        return section;
    }

    public void setSection(Sections section) {
        this.section = section;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
