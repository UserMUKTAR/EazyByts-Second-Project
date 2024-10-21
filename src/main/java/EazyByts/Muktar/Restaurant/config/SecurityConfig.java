package EazyByts.Muktar.Restaurant.config;

import EazyByts.Muktar.Restaurant.service.MyUserDetailsService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@CrossOrigin(origins = "http://localhost:63342")
@Configuration
@EnableWebSecurity
public class SecurityConfig {



    @Autowired
    private MyUserDetailsService userDetailsService;


    @Autowired
    private JWTFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(customizer -> customizer.disable())
                .authorizeHttpRequests(request -> request.
                        requestMatchers("register", "login").permitAll()
//                        .requestMatchers(HttpMethod.GET, "/menu").permitAll() // Public GET menu
//                        .requestMatchers(HttpMethod.POST, "/menu").hasRole("ADMIN") // Admin POST
//                        .requestMatchers(HttpMethod.PUT, "/menu/**").hasRole("ADMIN") // Admin PUT
//                        .requestMatchers(HttpMethod.DELETE, "/menu/**").hasRole("ADMIN")
                        .anyRequest().authenticated()).
                httpBasic(Customizer.withDefaults()).sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)

                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .build();

    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();

    }

    private CorsConfigurationSource corsConfigurationSource() {


        return new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {

                CorsConfiguration cfg = new CorsConfiguration();
                cfg.setAllowedOrigins(Arrays.asList(""));
                cfg.setAllowedMethods(Collections.singletonList(("*")));
                cfg.setAllowCredentials(true);
                cfg.setAllowedHeaders(Collections.singletonList(("*")));
                cfg.setExposedHeaders(Arrays.asList("Authorization"));
                return cfg;
            }
        };
    }







}
