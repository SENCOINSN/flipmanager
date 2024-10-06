package com.feature.flipmanagement.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

     @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("admin")
                .password(passwordEncoder().encode("pass123"))
                .roles("ADMIN")
                .build());
        return manager;
    }

    @Bean
       public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
           http.
           csrf(AbstractHttpConfigurer::disable)
          . authorizeHttpRequests(request -> request.requestMatchers("/", "/css/*", "/login","/feature/**").permitAll()
                         .anyRequest().authenticated())
                .formLogin(form -> form.loginPage("/login")
                         .defaultSuccessUrl("/feature/console", true)
                         .permitAll())
                 .logout(logout -> logout
                         .logoutUrl("/")
                         .logoutSuccessUrl("/login")
                         .invalidateHttpSession(true)
                         .deleteCookies("JSESSIONID")
                 .permitAll());
         return http.build();

      
    }

 
}
