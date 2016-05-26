package me.south10.accounts;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by south10 on 2016-05-26.
 */
@Getter
@Setter
@Entity
public class Account {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    private String email;

    private String fullName;

    @Temporal(TemporalType.TIMESTAMP)
    private Date joined;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    private boolean admin;
}
