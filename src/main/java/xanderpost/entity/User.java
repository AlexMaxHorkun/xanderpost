package xanderpost.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Email;
import org.springframework.security.core.userdetails.UserDetails;
import xanderpost.security.UserRole;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Table(name = "User")
public class User implements UserDetails {
    private long id;

    private String email;

    private String password;

    private List<UserRole> roles = new ArrayList<UserRole>();

    @Id
    @Column(name = "id")
    @GeneratedValue
    public long getId() {
        return id;
    }

    protected void setId(long id) {
        this.id = id;
    }

    @Column(name = "email", unique = true, nullable = false)
    @NotNull
    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonIgnore
    @Column(name = "password", unique = false, nullable = false)
    @NotNull
    @Size(min = 3, max = 128)
    public String getPassword() {
        return password;
    }

    @JsonIgnore
    public void setPassword(String password) {
        this.password = password;
    }

    @ManyToMany(targetEntity = UserRole.class, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    public List<UserRole> getAuthorities() {
        return roles;
    }

    public void setAuthorities(List<UserRole> roles) {
        this.roles = roles;
    }

    @Transient
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Transient
    public String getUsername() {
        return getEmail();
    }

    @Transient
    public boolean isAccountNonExpired() {
        return true;
    }

    @Transient
    public boolean isAccountNonLocked() {
        return true;
    }

    @Transient
    public boolean isEnabled() {
        return true;
    }

    public void eraseCredentials() {
        password = null;
    }
}
