package ua.shield.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by sa on 06.06.17.
 */
@Entity
@Table(name = "message")
@NamedQueries({
        @NamedQuery(name="Message.findAllOrderByDate",
                    query ="SELECT m FROM Message m ORDER BY m.messageDateTime desc")
})
public class Message {
    private Integer id;
    private String message;
    private LocalDateTime messageDateTime;
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name="MESSAGE")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Column(name="MESSAGE_DATE_TIME")
    public LocalDateTime getMessageDateTime() {
        return messageDateTime;
    }


    public void setMessageDateTime(LocalDateTime messageDateTime) {
        this.messageDateTime = messageDateTime;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "USER_ID")
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Message{" +
                "message='" + message + '\'' +
                ", messageDateTime=" + messageDateTime +
                '}';
    }
}
