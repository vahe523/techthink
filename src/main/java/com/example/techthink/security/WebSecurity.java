package com.example.techthink.security;

import com.example.techthink.service.UserService;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private final UserService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private static String[] WHITE_LIST = {"/registration", "/login", "/swagger*/**", "/v3/api-docs", "/all*/**"};
    private static String[] USER_LIST = {"user*/**"};
    private static String[] STUDENT_LIST = {"/student*/**"};
    private static String[] PROFESSOR_LIST = {"/professor*/**"};
    private static String[] ONLY_ADMIN_LIST = {"/admin*/**"};

    public WebSecurity(UserService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement(cust -> cust.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeRequests()
                .antMatchers(WHITE_LIST)
                .permitAll()
                .antMatchers(USER_LIST).hasAnyAuthority("STUDENT", "PROFESSOR", "ADMIN")
                .antMatchers(STUDENT_LIST).hasAnyAuthority("STUDENT", "ADMIN")
                .antMatchers(PROFESSOR_LIST).hasAnyAuthority("PROFESSOR", "ADMIN")
                .antMatchers(ONLY_ADMIN_LIST).hasAuthority("ADMIN")
                .and()
                .formLogin()
                .permitAll()
                .and()
                .httpBasic()
                .and()
                .csrf()
                .disable();
//        http
//                .csrf().disable().authorizeRequests()
//                .antMatchers(URLS)
//                .permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .defaultSuccessUrl("/home", true)
//                .passwordParameter("password")
//                .usernameParameter("username")
//                .and()
//                .rememberMe()
//                .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21))
//                .key("somethingverysecured")
//                .rememberMeParameter("remember-me")
//                .and()
//                .logout()
//                .logoutUrl("/logout")
//                .logoutRequestMatcher(
//                        new AntPathRequestMatcher("/logout", "GET"))
//                .clearAuthentication(true)
//                .invalidateHttpSession(true)
//                .deleteCookies("JSESSIONID", "remember-me")
//                .logoutSuccessUrl("/login");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }
}
