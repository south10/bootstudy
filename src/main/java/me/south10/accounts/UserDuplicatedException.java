package me.south10.accounts;

/**
 * Created by south10 on 2016-05-26.
 */
public class UserDuplicatedException extends RuntimeException {
    String username;

    public UserDuplicatedException(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
