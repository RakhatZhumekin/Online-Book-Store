package kz.kaspi.kaspiproject.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Sections {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id;

    @Column
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "section", fetch = FetchType.EAGER)
    private List<Books> books = new ArrayList<>();

    public Sections() {

    }

    public Sections(String name) {
        this.name = name;
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

    public List<Books> getBooks() {
        return books;
    }

    public void setBooks(List<Books> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Sections{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
