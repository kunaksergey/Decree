package ua.shield.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by sa on 06.06.17.
 */
@Entity
@Table(name="users")
public class User {

    private int id;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private String email;
    private LocalDate birthDate;
    private byte[] photo;
    private boolean enable;
    List<Message> messageList=new ArrayList<>();
    Set<Role> roles=new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name="FIRST_NAME")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name="LAST_NAME")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name="LOGIN")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


    @Column(name="PASSWORD")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name="EMAIL")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name="BIRTH_DATE")
    public LocalDate getBirthDate() {
        return birthDate;
    }

    @Basic
    @Lob
    @Column(name="photo")
    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user",cascade=CascadeType.ALL,orphanRemoval = true)
    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

    @Column(name="ENABLE")
    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    @ManyToMany
    @JoinTable (name = "user_role_detail",
            joinColumns = @JoinColumn (name = "USER_ID"),
            inverseJoinColumns =@JoinColumn(name = "ROLE_ID"))
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", birthDate=" + birthDate +
                ", photo=" + Arrays.toString(photo) +
                ", isEnable=" + enable +
                ", messageList=" + messageList +
                ", roles=" + roles +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        return login.equals(user.login);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + login.hashCode();
        return result;
    }
}
