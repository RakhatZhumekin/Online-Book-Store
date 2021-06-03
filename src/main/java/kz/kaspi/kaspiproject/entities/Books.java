package kz.kaspi.kaspiproject.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Authors author;

    @ManyToOne
    @JoinColumn(name = "section_id")
    private Sections section;

    @Column
    @Enumerated(EnumType.STRING)
    private Language language;

    @Column
    private int price;

    @Column
    private int quantity;

    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "book", fetch = FetchType.EAGER)
    private List<BasketItem> basketItems = new ArrayList<>();

    public Books() {

    }

    public Books(String name, Authors author, Sections section, Language language, int price, int quantity, Status status) {
        this.name = name;
        this.author = author;
        this.section = section;
        this.language = language;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<BasketItem> getBasketItems() {
        return basketItems;
    }

    public void setBasketItems(List<BasketItem> basketItems) {
        this.basketItems = basketItems;
    }

    @Override
    public String toString() {
        return "Books{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author=" + author +
                ", section=" + section +
                ", language='" + language + '\'' +
                ", price=" + price +
                '}';
    }

    public enum Language {
        RUSSIAN("Russian"),
        KAZAKH("Kazakh"),
        ENGLISH("English");

        private final String name;

        private Language(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public enum Status {
        AVAILABLE("Available"),
        SOLD("Sold out");

        private final String name;

        private Status(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
