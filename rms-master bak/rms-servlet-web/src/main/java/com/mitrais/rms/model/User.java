package com.mitrais.rms.model;

public class User
{
    private Long id;
    private String userName;
    private String password;
    private String userType;
    
    public static enum USER_TYPE {
    	admin, employee;
    }

    public User(Long id, String userName, String password, String userType) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.userType = userType;
    }

    public Long getId() {
        return id;
    }
    
    public void setId(long id) {
    	this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getUserType() {
        return this.userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
