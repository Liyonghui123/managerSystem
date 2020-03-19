package com.douyu.service;

import com.douyu.pojo.Role;
import com.douyu.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @description:
 * @author: Dangerous
 * @time: 2020/3/8 16:28
 */
@Configuration
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        System.out.println(userName);
        User user = this.userService.selectUserByUserName(userName);
        if(user==null){
            return null;
        }
        String password=user.getUserPassword();
        return new org.springframework.security.core.userdetails.User(userName,password, authorities(userName));
    }
    private Collection<GrantedAuthority> authorities(String userName){
        List<Role> roles = this.userService.selectRoleByUserName(userName);
        List<GrantedAuthority> authorities=new ArrayList<>();
        for (Role role : roles) {
            if(role!=null){
                authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
            }
        }
        return authorities;
    }
}
