package xanderpost.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import xanderpost.entity.User;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "UserRole")
public class UserRole implements org.springframework.security.core.GrantedAuthority {
    private String role;

    private int id;

    private UserRole parent;

    private Set<UserRole> childRoles;

    private Set<User> users;

    public UserRole() {
    }

    public UserRole(String r) {
        setRole(r);
    }

    @Column(name = "role", length = 64, nullable = false, unique = true)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue
    public int getId() {
        return id;
    }

    protected void setId(int id) {
        this.id = id;
    }

    @ManyToOne(optional = true, targetEntity = UserRole.class, cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id", referencedColumnName = "id", nullable = true)
    public UserRole getParent() {
        return parent;
    }

    public void setParent(UserRole parent) {
        this.parent = parent;
    }

    @JsonIgnore
    @OneToMany(targetEntity = UserRole.class, mappedBy = "parent", orphanRemoval = false, fetch = FetchType.LAZY)
    public Set<UserRole> getChildRoles() {
        return childRoles;
    }

    @JsonIgnore
    public void setChildRoles(Set<UserRole> childRoles) {
        this.childRoles = childRoles;
    }

    @JsonIgnore
    @Transient
    public String getAuthority() {
        return getRole();
    }

    @JsonIgnore
    @ManyToMany(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    public Set<User> getUsers() {
        return users;
    }

    @JsonIgnore
    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
