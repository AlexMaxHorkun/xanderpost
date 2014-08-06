package xanderpost.security;

public class UserRole implements org.springframework.security.core.GrantedAuthority {
    private String role;

    private int id;

    private UserRole parent;

    private UserRole[] childRoles;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    protected void setId(int id) {
        this.id = id;
    }

    public UserRole getParent() {
        return parent;
    }

    public void setParent(UserRole parent) {
        this.parent = parent;
    }

    public UserRole[] getChildRoles() {
        return childRoles.clone();
    }

    public void setChildRoles(UserRole[] childRoles) {
        this.childRoles = childRoles.clone();
    }

    public String getAuthority() {
        return getRole();
    }
}
