package com.travel.unified.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @jakarta.persistence.Column(name = "user_id")
    private Long userId;

    private String name;
    private String email;
    private String password;
    @jakarta.persistence.ManyToMany(fetch = jakarta.persistence.FetchType.EAGER)
    @org.hibernate.annotations.OnDelete(action = org.hibernate.annotations.OnDeleteAction.CASCADE)
    @jakarta.persistence.JoinTable(name = "user_roles", joinColumns = @jakarta.persistence.JoinColumn(name = "user_id"), inverseJoinColumns = @jakarta.persistence.JoinColumn(name = "role_id"))
    private java.util.Set<Role> roles = new java.util.HashSet<>();

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public java.util.Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(java.util.Set<Role> roles) {
        this.roles = roles;
    }
}
