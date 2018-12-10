package com.neosuniversity.demoweb.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity(debug=true)
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/form/login").permitAll()
                .antMatchers("/login").permitAll()
                //.antMatchers("/").hasRole("USER")
                .antMatchers("/").permitAll()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/h2-console/**").hasAnyRole("USER","ADMIN")
                .and().exceptionHandling().accessDeniedPage("/errors/403")
                .and()
                .formLogin()
                .loginPage("/form/login")
                .loginProcessingUrl("/login")
                .failureUrl("/form/login?error")
               // .and()
                //.httpBasic()
                .and().csrf().disable();//la consola de h2 necesita deshabilitar csrf

        http.headers().frameOptions().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.inMemoryAuthentication()
               .withUser("user1").password("{noop}user1")
               .roles("USER")
               .and()
               .withUser("admin").password("{noop}admin")
               .roles("ADMIN","USER");
    }
}
