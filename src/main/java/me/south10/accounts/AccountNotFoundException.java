package me.south10.accounts;

/**
 * Created by south10 on 2016-05-26.
 */
public class AccountNotFoundException extends RuntimeException{
    Long id;

    public AccountNotFoundException(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
