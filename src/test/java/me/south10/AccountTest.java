package me.south10;

import me.south10.accounts.Account;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by south10 on 2016-05-26.
 */
public class AccountTest {
    @Test
    public void getterSetter() throws Exception {
        Account account = new Account();
        account.setUsername("south10");
        account.setPassword("qwer1234");
        assertThat(account.getUsername(), is("south10"));
    }
}
