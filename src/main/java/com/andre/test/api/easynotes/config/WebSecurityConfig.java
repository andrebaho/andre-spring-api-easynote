package com.andre.test.api.easynotes.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(1)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter  {

    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/oauth/authorize")
                .authenticated()
                .and().formLogin()
                .and().requestMatchers()
                .antMatchers("/login,/oauth/authorize");

    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Override public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/common/**", "/v3/api-docs/**", "/configuration/ui", "/swagger-resources",
         "/configuration/security", "/swagger-ui.html", "/webjars/**","/swagger-ui/**");
    }
}
