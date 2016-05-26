package me.south10.accounts;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by south10 on 2016-05-26.
 */
@Service
@Slf4j
@Transactional
public class AccountService {
    @Autowired
    private AccountRepository repository;

    @Autowired
    private ModelMapper modelMapper;


    public Account createAccount(AccountDto.Create dto) {
        Account account = modelMapper.map(dto, Account.class);

        String username = dto.getUsername();
        if(repository.findByUsername(username) != null){
            log.error("user duplicated exception. {}", username);
            throw new UserDuplicatedException(username);
        }

        account.setPassword(account.getPassword());
        Date now = new Date();
        account.setJoined(now);
        account.setUpdated(now);
        return repository.save(account);
    }
}
