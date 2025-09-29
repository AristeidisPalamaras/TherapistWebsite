package gr.kostasmavrakakis.website.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
public class SecurityConfig {

    @Value("${spring.security.admin.password}")
    private String adminPassword;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                // .requestMatchers("/", "/css/**", "/js/**", "/img/**").permitAll()
                .anyRequest().permitAll()
            )
            .headers(headers -> headers
                .httpStrictTransportSecurity(hsts -> hsts
                    .includeSubDomains(true)
                    .maxAgeInSeconds(31536000)
                    .preload(false) 
                )
                .xssProtection(xss -> xss.disable())
                .frameOptions(frame -> frame.deny())
                .referrerPolicy(policy -> policy.policy(
                    ReferrerPolicyHeaderWriter.ReferrerPolicy.STRICT_ORIGIN_WHEN_CROSS_ORIGIN
                ))
                .addHeaderWriter(
                    new StaticHeadersWriter("X-Content-Type-Options", "nosniff")
                )
                .addHeaderWriter(
                    new StaticHeadersWriter(
                        "Permissions-Policy",
                        "geolocation=(), microphone=(), camera=(), fullscreen=()"
                    )
                )
                .contentSecurityPolicy(csp -> csp
                    .policyDirectives(
                        "default-src 'self'; " +
                        "style-src 'self' https://fonts.googleapis.com https://cdn.jsdelivr.net https://unpkg.com; " +
                        "font-src 'self' https://fonts.gstatic.com; " +
                        "script-src 'self' https://unpkg.com; " +
                        "img-src 'self' data: https://*.tile.openstreetmap.fr https://cdn.jsdelivr.net; " +
                        "connect-src 'self' https://unpkg.com; " +
                        "object-src 'none';" +
                        "frame-ancestors 'none'; " +
                        "base-uri 'none'; " +
                        "form-action 'self'; " +
                        "upgrade-insecure-requests"
                    )
                )
            );

        return http.build();
    }

    @Bean
    public UserDetailsService users(PasswordEncoder encoder) {
        UserDetails admin = User.builder()
            .username("admin")
            .password(encoder.encode(adminPassword))
            .roles("ADMIN")
            .build();
        return new InMemoryUserDetailsManager(admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
