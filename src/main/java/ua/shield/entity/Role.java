package ua.shield.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by sa on 08.06.17.
 */
@Entity
@Table(name="role")
public class Role {
    private String role;
    private Set<User> userSet=new HashSet<>();

    public Role() {
    }

    public Role(String role) {
        this.role = role;
    }


    @Id
    @Column(name="ROLE_ID")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @ManyToMany
    @JoinTable (name = "user_role_detail",
            joinColumns = @JoinColumn (name = "ROLE_ID"),
            inverseJoinColumns =@JoinColumn(name = "USER_ID"))
    public Set<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<User> userSet) {
        this.userSet = userSet;
    }

    @Override
    public String toString() {
        return "Role{" +
                "role='" + role + '\'' +
                '}';
    }


}
