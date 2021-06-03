package kz.kaspi.kaspiproject.entities;

import javax.persistence.*;

@Entity
@Table(name = "basket_item")
public class BasketItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Books book;

    @Column
    private int quantity;

    @Column
    private boolean active = true;

    public BasketItem() {
    }

    public BasketItem(Users user, Books book, int quantity) {
        this.user = user;
        this.book = book;
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Books getBook() {
        return book;
    }

    public void setBook(Books book) {
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "BasketItem{" +
                "id=" + id +
                ", user=" + user +
                ", book=" + book +
                ", quantity=" + quantity +
                '}';
    }
}
