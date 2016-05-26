package me.south10.accounts;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by south10 on 2016-05-26.
 */
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByUsername(String username);
}
