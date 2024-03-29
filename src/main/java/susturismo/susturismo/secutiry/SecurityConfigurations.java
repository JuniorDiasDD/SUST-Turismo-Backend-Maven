package susturismo.susturismo.secutiry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {
    @Autowired
    SecurityFilter securityFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/api/v1/auth/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/category/all").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/event/*").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/event").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/equipa").permitAll()
                      //  .requestMatchers(HttpMethod.GET, "/api/v1/feed/*").permitAll()
                        //.requestMatchers(HttpMethod.GET, "/api/v1/feed").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/formation/*").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/formation").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/parceiro").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/parceiro/*").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/noticia/*").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/noticia").permitAll()
                        //.requestMatchers(HttpMethod.POST,"/events").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/formation/approve").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/event/approve").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/noticia/approve").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
