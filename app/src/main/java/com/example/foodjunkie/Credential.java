package com.example.foodjunkie;

public class Credential {
    private String Username;
    private String Password;



    Credential(String username, String password) {
        Username = username;
        Password = password;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
