package me.south10;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import me.south10.accounts.AccountDto;
import me.south10.accounts.AccountService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by south10 on 2016-05-26.
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@Transactional
public class AccountControllerTest {
    @Autowired
    WebApplicationContext wac;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    AccountService accountService;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        // TODO: 2016-05-26 추후 security 필터 추가 예정
        mockMvc = new MockMvcBuilders().webAppContextSetup(wac).build();
    }

    @Test
    public void createAccount() throws Exception {
        log.info("mockMvc : {}", mockMvc);
        AccountDto.Create createDto = accountCreateDto();
        ResultActions result = mockMvc.perform(post("/accounts")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(createDto)));

        result.andDo(print());
        result.andExpect(status().isCreated());
        result.andExpect(jsonPath("$.username", is("south10")));

        result = mockMvc.perform(post("/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createDto)));
        result.andDo(print());
        result.andExpect(status().isBadRequest());
        result.andExpect(jsonPath("$.code", is("duplicated.username.exception")));
    }

    private AccountDto.Create accountCreateDto() {
        AccountDto.Create createDto = new AccountDto.Create();
        createDto.setUsername("south10");
        createDto.setPassword("qwer1234");
        return createDto;
    }
}
