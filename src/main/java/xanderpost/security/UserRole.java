package xanderpost.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import xanderpost.entity.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "UserRole")
public class UserRole implements org.springframework.security.core.GrantedAuthority {
    private String role;

    private int id;

    private UserRole parent;

    private List<UserRole> childRoles = new ArrayList<UserRole>();

    private List<User> users = new ArrayList<User>();

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
    public List<UserRole> getChildRoles() {
        return childRoles;
    }

    @JsonIgnore
    public void setChildRoles(List<UserRole> childRoles) {
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
    public List<User> getUsers() {
        return users;
    }

    @JsonIgnore
    public void setUsers(List<User> users) {
        this.users = users;
    }
}
