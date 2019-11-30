package ies.grupo33.CampusMonitoring.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "users", schema = "campus_monitoring")
public class User implements Serializable {

    @Id
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "admin", nullable = false)
    private boolean admin;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "users_local",
            schema = "campus_monitoring",
            joinColumns = @JoinColumn(name = "username", referencedColumnName = "username"),
            inverseJoinColumns = @JoinColumn(name = "local_name", referencedColumnName = "name"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"username", "local_name"})
    )
    private Collection<Local> locals;

    public User() {

    }

    public User(String username, String name, String email, boolean admin) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.admin = admin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public Collection<Local> getLocals() {
        return locals;
    }

    public void setLocals(Collection<Local> locals) {
        this.locals = locals;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", admin=" + admin +
                '}';
    }


}

