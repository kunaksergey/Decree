package ua.shield.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by sa on 14.07.17.
 */
@Entity(name="Order")
@Table(name="document")
public class Order {
    /**
     * Статусы документа:
     * 0-подготовленный
     * 1-в работе
     * 2-завершенный
     */
    public static final int STATUS_PREPARED=0;
    public static final int STATUS_IN_WORK=1;
    public static final int STATUS_COMPLETED=2;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "OWNER_ID")
    private User owner;

    @Column(name="ORDER_DATE_TIME_CREATED")
    private LocalDateTime orderDateTimeCreated;

    @Column(name="STATUS")
    private int status;

    @Column(name="CAPTION")
    private String caption;

    @Column(name="DESCRIPTION")
    private String description;

    @Transient
    private byte[] file;

    @OneToMany(mappedBy = "order",
            cascade =  CascadeType.ALL
           )
    private Set<Sign> setSing=new LinkedHashSet<>();

    public Order() {
    }

    public Order(Integer id, int status, String description) {
        this.id = id;
        this.status = status;
        this.description = description;
    }

    public Order(int status, String describe) {
        this.status = status;
        this.description = describe;
    }

    public Order(User owner) {
        this.owner = owner;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public LocalDateTime getOrderDateTimeCreated() {
        return orderDateTimeCreated;
    }

    public void setOrderDateTimeCreated(LocalDateTime orderDateTimeCreated) {
        this.orderDateTimeCreated = orderDateTimeCreated;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDescribe(String describe) {
        this.description = description;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Set<Sign> getSetSing() {
        return setSing;
    }

    public void setSetSing(Set<Sign> setSing) {
        this.setSing = setSing;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", status=" + status +
                ", caption='" + caption + '\'' +
                ", description='" + description + '\'' +
                ", file=" + Arrays.toString(file) +
                ", mapSing=:" + setSing +
                '}';
    }
}
