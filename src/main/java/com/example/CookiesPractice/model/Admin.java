package com.example.CookiesPractice.model;

import jakarta.persistence.*;

@Entity
@Table(name = "admins")
public class Admin {
    @Id
    @Column(name = "admin_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "admin_post")
    @Enumerated(EnumType.STRING)
    private adminPost adminPost;

    public Admin() {}

    public Admin(String login, String password, com.example.CookiesPractice.model.adminPost adminPost) {
        this.login = login;
        this.password = password;
        this.adminPost = adminPost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public com.example.CookiesPractice.model.adminPost getAdminPost() {
        return adminPost;
    }

    public void setAdminPost(com.example.CookiesPractice.model.adminPost adminPost) {
        this.adminPost = adminPost;
    }
}
