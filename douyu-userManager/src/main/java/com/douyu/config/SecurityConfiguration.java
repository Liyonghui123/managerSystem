package com.douyu.config;

import com.douyu.service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @description:
 * @author: Dangerous
 * @time: 2020/3/8 14:46
 */
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private static final String[] AUTH_WHITELIST = {

            // -- swagger ui
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/webjars/**"
    };
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private MyUserDetailService userDetailService;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                //产品相关
                .antMatchers("/product/**").access("hasAnyRole('ADMIN','USER')")
                //管理相关
                .antMatchers("/admin/**").hasRole("ADMIN")
                //分配角色相关
                .antMatchers("/assign/**").hasRole("ADMIN")
                //添加用户
                .antMatchers(HttpMethod.POST,"/user/**").access("hasAnyRole('ADMIN','USER')")
                //删除用户
                .antMatchers(HttpMethod.DELETE,"/user/**").hasRole("ADMIN")
                //修改用户
                .antMatchers(HttpMethod.PUT,"/user/**").access("hasAnyRole('ADMIN','USER')")
                //查找用户
                .antMatchers(HttpMethod.GET,"/user/**").access("hasAnyRole('ADMIN')")
                .antMatchers(AUTH_WHITELIST).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().and()
                .httpBasic()
                .and().csrf().disable();



    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailService);
//                .inMemoryAuthentication()
//                .withUser("admin1")
//                // 管理员，同事具有 ADMIN,USER权限，可以访问所有资源
//                .password("admin1")
//                .roles("ADMIN", "USER")
//                .and()
//                .withUser("user1").password("user1")
//                // 普通用户，只能访问 /product/**
//                .roles("USER");
    }

}
