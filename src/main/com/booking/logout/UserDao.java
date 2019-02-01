package com.booking.logout;

import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private final List<User> all = new ArrayList<>();

    public User getById(int id){
        User result = new User();
        result.setId(1);
        for(User user:all){
            if(user.getId() == id){
                result = user;
            }
        }
        return result;
    }
    public User getUserByLoginPassword(final String loggin, final String password){
        User result = new User();
        result.setId(1);
        for(User user:all){
            if(user.getLogin().equals(loggin) && user.getPassword().equals(password)){
                result = user;
            }
        }
        return result;
    }
    public boolean add(final User user) {
        for(User u : all){
            if(u.getPassword().equals(user.getPassword()) && u.getLogin().equals(user.getLogin())){
                return false;
            }
        }
        return all.add(user);
    }
    public User.WHO getWhoByLogginPassword(final String loggin, final String password){
        User.WHO result = User.WHO.UNKNOWN;
        for(User user: all){
            if(user.getLogin().equals(loggin) && user.getPassword().equals(password)){
                result = user.getWho();
            }
        }
        return result;
    }
    public boolean userIsExist(final String loggin, final String password){
        boolean result = false;
        for(User user: all){
            if(user.getPassword().equals(password) && user.getLogin().equals(loggin)){
                result = true;
                break;
            }
        }
        return result;
    }
}
