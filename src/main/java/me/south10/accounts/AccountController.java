package me.south10.accounts;

import me.south10.commons.ErrorResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by south10 on 2016-05-26.
 */
@RestController
public class AccountController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AccountService service;

    @RequestMapping(value = "/accounts", method = RequestMethod.POST)
    public ResponseEntity createAccount(@RequestBody @Valid AccountDto.Create create, BindingResult result){
        if(result.hasErrors()){
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("잘못된 요청입니다.");
            errorResponse.setCode("bad.request");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
        Account newAccount = service.createAccount(create);
        return new ResponseEntity<>(modelMapper.map(newAccount, AccountDto.Response.class), HttpStatus.CREATED);
    }

    @ExceptionHandler(UserDuplicatedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handlerUserDuplicatedException(UserDuplicatedException e){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("[" + e.getUsername() + "] 중복된 username 입니다.");
        errorResponse.setCode("duplicated.username.exception");
        return errorResponse;
    }
}
