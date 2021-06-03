package kz.kaspi.kaspiproject.entities;

import com.vladmihalcea.hibernate.type.array.ListArrayType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.List;

@Entity
@TypeDef(name = "list-array", typeClass = ListArrayType.class)
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @Type(type = "list-array")
    @Column(name = "basket_item_ids", columnDefinition = "bigint[]")
    private List<Long> basketItemIds;

    public Orders() {
    }

    public Orders(Users user, List<Long> basketItemIds) {
        this.user = user;
        this.basketItemIds = basketItemIds;
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

    public List<Long> getBasketItemIds() {
        return basketItemIds;
    }

    public void setBasketItemIds(List<Long> basketItemIds) {
        this.basketItemIds = basketItemIds;
    }
}
