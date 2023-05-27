package com.vladyslavnicko.gmail.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.vladyslavnicko.gmail.service.impl.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

//    private final AuthenticationSuccessHandler authenticationSuccessHandler;
//    private UserDetailsService userDetailsService;
//
//    public SecurityConfig(AuthenticationSuccessHandler authenticationSuccessHandler, UserDetailsService userDetailsService) {
//        this.authenticationSuccessHandler = authenticationSuccessHandler;
//        this.userDetailsService = userDetailsService;
//    }
//     
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//     
//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userDetailsService);
//        authProvider.setPasswordEncoder(passwordEncoder());
//         
//        return authProvider;
//    }
// 
////    @Override
////    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth.authenticationProvider(authenticationProvider());
////    }
//
//   @Bean
//   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeHttpRequests()
//                .requestMatchers("/login/log", "/logout")
//                //.hasAnyAuthority("USER", "EDITOR", "ADMIN")
//                //.permitAll()
//               // .anyRequest()
//                .authenticated()
//                .and()
//                .sessionManagement()
////                .and()
////                .formLogin() // Add form-based authentication
//                    //.loginPage("/login/log") // Specify the login page URL
//                   // .permitAll()
//                .and()
//                .oauth2Login()
//                .successHandler(authenticationSuccessHandler)
//                .and()
//                .logout()
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/")
//                .and()
//                .exceptionHandling().accessDeniedPage("/403");
//        return http.build();
//   }

}

