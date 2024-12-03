package mx.petcare.mascotas.petcareAPI;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/signin", "/signup").permitAll() // Permite acceso sin autenticación a las rutas de inicio y registro
                .anyRequest().authenticated() // Requiere autenticación para todas las demás rutas
            )
            .formLogin(withDefaults()) // Usa el formulario por defecto para inicio de sesión
            .rememberMe(withDefaults()) // Recuerda al usuario entre sesiones
            .logout(logout -> logout.logoutUrl("/signout").permitAll()); // Permite el logout sin autenticación
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        var userDetailsManager = new InMemoryUserDetailsManager();
        userDetailsManager.createUser(User.withUsername("userOne")
                .password(passwordEncoder().encode("userOne"))
                .roles("USER") // Asigna rol USER (aunque no lo uses en este caso)
                .build());
        userDetailsManager.createUser(User.withUsername("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN") // Asigna rol ADMIN (aunque no lo uses en este caso)
                .build());
        return userDetailsManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

/*
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                auth -> auth
                        .requestMatchers("/signin", "/signup").permitAll()
                        .requestMatchers("/learningResources/**", "/pets/**", "/api/reports/**", "/api/sharetips/**", "/tips/**", "/api/users/**").hasRole("USER")
                        .requestMatchers("/learningResources/**").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .formLogin(withDefaults())
                .rememberMe(withDefaults())
                .logout(logout -> logout.logoutUrl("/signout").permitAll());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        var userDetailsManager = new InMemoryUserDetailsManager();
        userDetailsManager.createUser(User.withUsername("userOne")
                .password(passwordEncoder().encode("userOne"))
                .roles("USER") 
                .build());
        // Crea el usuario con rol ADMIN
        userDetailsManager.createUser(User.withUsername("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN") 
                .build());
        return userDetailsManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}*/
