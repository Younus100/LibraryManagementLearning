package com.library.library_management.authservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeRequests(authorize -> authorize
                        .requestMatchers("/api/**").authenticated()
                        .requestMatchers("/auth/register/member", "/auth/register/admin", "/auth/signin ", "/error", "/**").permitAll() // Public access for signup and login
                        .requestMatchers("/admin").hasRole("LIBRARIAN") // Only LIBRARIAN can access /admin
                        .requestMatchers("/member").hasRole("MEMBER") // Only MEMBER can access /member
                        .requestMatchers("/user").permitAll() // All users can access /user
                        .anyRequest().authenticated())
                .addFilterBefore(new JWTValidator(), BasicAuthenticationFilter.class)
                .csrf().disable()
//                .cors().configurationSource(request -> {
//                    CorsConfiguration corsConfiguration = new CorsConfiguration();
//                    corsConfiguration.setAllowedOrigins(Arrays.asList("https://whatsapp-3lyseth5f-younus-projects-2596409c.vercel.app", "http://localhost:3000"));
////                    corsConfiguration.setAllowedOrigins(Arrays.asList("*"));
//                    corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
//                    corsConfiguration.setAllowCredentials(true);
//                    corsConfiguration.addAllowedHeader("*");
//                    return corsConfiguration;
//                })
//                .and().formLogin() // Disable form-based login
//                .and().httpBasic() // Disable HTTP basic authentication
        ;

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}