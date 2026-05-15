package com.railway.ticket.management.system.domain;

import com.railway.ticket.management.system.domain.enums.Role;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public class User {
    private int id;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String email;
    @NotBlank
    private String fullName;
    private Role role;
    private LocalDateTime createdAt;

    public User(int id, String username, String password, String email, String fullName, Role role, LocalDateTime createdAt) {
        setId(id);
        setUsername(username);
        setPassword(password);
        setEmail(email);
        setFullName(fullName);
        setRole(role);
        setCreatedAt(createdAt);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
