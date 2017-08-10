package ua.shield.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by sa on 16.07.17.
 * Класс описывает подпись сотрудника для документа
 */
@Entity(name = "Sign")
@Table(name="sign")
public class Sign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Integer id;

    @Column(name="SIGN_DATE_TIME")
    LocalDateTime dateTimeSign;

    @Column(name="IS_SIGN")
    boolean isSign;


     @OneToOne
     @JoinColumn(name = "DOCUMENT_ID")
     Order order;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SIGNER_ID")
    User user;

    public Sign() {
    }

    public Sign(User user) {
        this.user = user;
    }

    public Sign(Order order, User user) {
        this.order = order;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getDateTimeSign() {
        return dateTimeSign;
    }

    public void setDateTimeSign(LocalDateTime dateTimeSign) {
        this.dateTimeSign = dateTimeSign;
    }

    public boolean isSign() {
        return isSign;
    }

    public void setSign(boolean sign) {
        isSign = sign;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }



    @Override
    public String toString() {
        return "Sign{" +
                "user=" + user +
                ", dateTimeSign=" + dateTimeSign +
                ", isSign=" + isSign +
                '}';
    }
}
