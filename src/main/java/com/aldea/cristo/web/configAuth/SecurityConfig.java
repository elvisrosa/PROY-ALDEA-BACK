package com.aldea.cristo.web.configAuth;

import com.aldea.cristo.web.JWTUtil.jwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    private final jwtFilter fwtfilter;

    @Autowired
    public SecurityConfig(jwtFilter fwtFilter) {
        this.fwtfilter = fwtFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                //Permite no giardar sesiones y que en cada sesion se valide el jwt
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((authz)
                        -> authz
                        .requestMatchers(HttpMethod.POST, "/api/auth/**").permitAll()
                        .requestMatchers("/swagger-ui.html", "/swagger-ui/index.html").permitAll()
                        .requestMatchers("/api/niños/**").hasAnyRole("ADMIN", "TUTOR", "ADMINT")
                        .requestMatchers("/api/eliminar/**").hasAnyRole("ADMIN", "ADMINT")
                        //.requestMatchers("/api/user/**").hasRole("ADMINT")
                        .requestMatchers("/api/user/**").permitAll()
                        .requestMatchers("/api/casa/**").permitAll()
                        .requestMatchers("/api/agregarEstudio/**").permitAll()
                        .requestMatchers("/api/tutor/**").permitAll()
                        .requestMatchers("/cargar/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/user/actualizar/**").hasRole("ADMINT")
                        .requestMatchers("/api/api2/randon").hasAuthority("randon_order")
                        .anyRequest().authenticated()
                )
                .csrf(AbstractHttpConfigurer::disable)
                .cors(withDefaults())
                //.httpBasic(withDefaults());
                .addFilterBefore(fwtfilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /*@Bean
    public UserDetailsService memoryUsers() {
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();
        
        
         UserDetails customer = User.builder()
                .username("customer")
                .password(passwordEncoder().encode("customer"))
                .roles("CUSTOMER")
                .build();
        
        return new InMemoryUserDetailsManager(admin, customer);

    }
     */
    @Bean
    public AuthenticationManager authenticationManeger(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
