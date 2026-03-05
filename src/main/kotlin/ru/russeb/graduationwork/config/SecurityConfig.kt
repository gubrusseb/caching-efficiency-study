package ru.russeb.graduationwork.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import ru.russeb.graduationwork.service.SecurityUserDetailsService


@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val userDetailsService: SecurityUserDetailsService,
) {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeHttpRequests { authz ->
                authz
                    .requestMatchers("/", "/home", "/public/**", "/css/**", "/js/**","/images/**").permitAll()
                    .requestMatchers("/register", "/register/**","/about_us", "/profile","/cart","/test/**").permitAll()
                    .requestMatchers("/admin/**").hasRole("ADMIN")
                    .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                    .anyRequest().authenticated()
            }
            .formLogin { formLogin ->
                formLogin
                    .loginPage("/login")
                    .loginProcessingUrl("/login")
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/", true)
                    .failureUrl("/login?error=true")
                    .permitAll()
            }
            .logout { logout ->
                logout
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login?logout")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID", "remember-me")
                    .clearAuthentication(true)
                    .permitAll()
            }
            .rememberMe { rememberMe ->
                rememberMe
                    .key("uniqueAndSecret")
                    .tokenValiditySeconds(86400)
                    .userDetailsService(userDetailsService)
                    .rememberMeParameter("remember-me")
            }
            .sessionManagement { session ->
                session
                    .sessionFixation().migrateSession()
                    .maximumSessions(1)
                    .expiredUrl("/login?expired=true")
            }
            .authenticationProvider(authenticationProvider())

        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun authenticationProvider(): DaoAuthenticationProvider {
        val authProvider = DaoAuthenticationProvider(userDetailsService)
        authProvider.setPasswordEncoder(passwordEncoder())
        return authProvider
    }
}