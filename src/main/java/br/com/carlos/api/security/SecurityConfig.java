package br.com.carlos.api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET,"/users", "/painel", "/usuarios").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT,"/users", "/usuarios").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/usuarios/add").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/users/**", "/usuarios/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/product/**", "/category").hasAnyRole("COMMON", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/product", "/category").hasAnyRole("COMMON", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/product", "/category").hasAnyRole("COMMON", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/product/**", "/category/**").hasAnyRole("COMMON", "ADMIN")

                        .anyRequest().permitAll())

                .formLogin(form -> form.loginPage("/login")
                        .failureUrl("/login?erro=true")
                        .defaultSuccessUrl("/painel")
                        .permitAll())
                .logout(logout -> logout.permitAll())
                .httpBasic(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable());

        return http.build();
    }
}