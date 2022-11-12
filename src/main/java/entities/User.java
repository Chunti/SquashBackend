package entities;

import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User implements Serializable {
    @Id
    @Column(name = "user_id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "email", nullable = false, length = 45)
    private String email;

    @Column(name = "phone", nullable = false)
    private Integer phone;

    @Column(name = "role", nullable = false)
    private Integer role;

    @Column(name = "user_pass", nullable = false)
    private String userPass;

    /*@ManyToMany
    @JoinTable(name = "participation",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<Group> groups = new LinkedHashSet<>();*/

    public User(Integer id, String name, String email, Integer phone, Integer role, String userPass) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.role = role;

        this.userPass = BCrypt.hashpw(userPass, BCrypt.gensalt());
    }

    public User() {
    }

    public boolean verifyPassword(String pw){
        return BCrypt.checkpw(pw,userPass);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    /*public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }*/

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                ", role=" + role +
                '}';
    }
}