package com.example.shop.config;

import com.example.shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserService userService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().sameOrigin()
                .and()

                .authorizeRequests()
                .antMatchers("/register/**", "/h2-console/**").anonymous()
                .antMatchers(HttpMethod.GET, "/location/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/location/**").hasAnyRole("ADMIN")
                .antMatchers("/office/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.POST, "/office/add/**").hasAnyRole("ADMIN")
                .antMatchers("/productCategory/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/productCategory/add/**").hasAnyRole("ADMIN")
                .antMatchers("/productCategory/product/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/productCategory/product/add/**").hasAnyRole("ADMIN")
                .antMatchers("/productCategory/product/byCategory/**").hasAnyRole("ADMIN", "USER")
                .anyRequest().authenticated()
                .and()

                .httpBasic()
                .and()

                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

}
