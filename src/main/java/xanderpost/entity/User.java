package xanderpost.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Email;
import org.springframework.security.core.userdetails.UserDetails;
import xanderpost.security.UserRole;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "User")
public class User implements UserDetails, Serializable {
    private long id;

    private String email;

    private String password;

    private Set<UserRole> roles;

    private boolean enabled = true;

    private Set<Post> posts;

    private Set<PostRating> ratings;

    private List<PostView> views;

    public User() {
    }

    public User(String email, String password, Set<UserRole> roles) {
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

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
    public Set<UserRole> getRoles() {
        return roles;
    }

    public boolean hasRole(String role) {
        boolean has = false;
        for (UserRole r : getAuthorities()) {
            if (r.getRole().equals(role)) {
                has = true;
                break;
            }
        }
        return has;
    }

    @Transient
    @JsonIgnore
    public List<UserRole> getAuthorities() {
        List<UserRole> authorities = new ArrayList<UserRole>();
        for (UserRole role : roles) {
            authorities.add(role);
            authorities.addAll(role.getChildRoles());
        }
        return authorities;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    @Transient
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Transient
    public String getUsername() {
        return getEmail();
    }

    @Transient
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Transient
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Column(name = "enabled")
    @JsonIgnore
    public boolean isEnabled() {
        return enabled;
    }

    @JsonIgnore
    public void setEnabled(boolean e) {
        enabled = e;
    }

    public void eraseCredentials() {
        password = null;
    }

    @OneToMany(targetEntity = Post.class, fetch = FetchType.LAZY, mappedBy = "author", orphanRemoval = true)
    @JsonIgnore
    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    @OneToMany(targetEntity = PostRating.class, fetch = FetchType.LAZY, mappedBy = "user", orphanRemoval = true)
    @JsonIgnore
    public Set<PostRating> getRatings() {
        return ratings;
    }

    public void setRatings(Set<PostRating> ratings) {
        this.ratings = ratings;
    }

    public boolean equals(Object obj) {
        if (obj instanceof User) {
            return ((User) obj).getId() == getId();
        }
        return false;
    }

    @OneToMany(targetEntity = PostView.class, fetch = FetchType.LAZY, mappedBy = "user", orphanRemoval = true)
    @OrderBy("time")
    public List<PostView> getViews() {
        return views;
    }

    public void setViews(List<PostView> views) {
        this.views = views;
    }
}
