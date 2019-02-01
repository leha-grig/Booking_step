package com.booking.logout;

public class User {
    private int id;
    private String login;
    private String password;
    private WHO who;

    public User() {
    }

    public User(int id, String login, String password, WHO who) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.who = who;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public WHO getWho() {
        return who;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setWho(WHO who) {
        this.who = who;
    }

    public enum WHO{
        USER, ADMIN, UNKNOWN
    }
}
