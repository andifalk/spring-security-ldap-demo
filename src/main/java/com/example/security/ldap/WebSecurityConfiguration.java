package com.example.security.ldap;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.ldap.userdetails.PersonContextMapper;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;

@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests().anyRequest().fullyAuthenticated().and().httpBasic().and().formLogin();
  }

  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.ldapAuthentication()
        .userDetailsContextMapper(userDetailsContextMapper())
        .userDnPatterns("uid={0},ou=people", "uid={0},ou=otherpeople")
        .groupSearchBase("ou=groups")
        .contextSource()
        .url("ldap://localhost:8389/dc=novatec,dc=de")
        .and()
        .passwordCompare()
        .passwordEncoder(passwordEncoder())
        .passwordAttribute("userPassword");
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  @Bean
  public UserDetailsContextMapper userDetailsContextMapper() {
    return new PersonContextMapper();
  }
}
