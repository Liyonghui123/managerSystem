package com.douyu.pojo;

import com.douyu.service.UserService;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
@Data
public class User{
    private Long userId;

    private String userCode;

    private String userName;

    private String userPassword;

    private String userState;

}
