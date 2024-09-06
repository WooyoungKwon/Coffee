package com.date.coffee.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationFailureHandler customFailureHandler;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
//                         이 페이지는 로그인을 안해도 접근 허용
                        .requestMatchers("/", "/member/signUp", "/member/signIn", "/member/login", "/S3/**", "/static/**", "/css/**", "/js/**", "/images/**").permitAll()
                        .requestMatchers("/cafe/new").hasRole("ADMIN")
                        // 정적 리소스 경로에 대한 접근 허용
                        .anyRequest().authenticated()
                );
        http
                .formLogin(formLogin -> formLogin
                        .loginPage("/login").permitAll() // 사용자 정의 로그인 페이지 URL, 인증되지 않은 사용자가 접근하려 할 때 redirection
                        .loginProcessingUrl("/member/signIn").permitAll()
                        .failureHandler(customFailureHandler)
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                );
        http
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/"));

        http
                .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }
}
