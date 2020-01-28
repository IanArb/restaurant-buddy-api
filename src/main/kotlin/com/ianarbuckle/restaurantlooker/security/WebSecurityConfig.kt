package com.ianarbuckle.restaurantlooker.security

import com.ianarbuckle.restaurantlooker.authentication.service.CustomUserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.AuthenticationEntryPoint
import javax.servlet.http.HttpServletResponse

/**
 * @author ianarbuckle on 22/09/2019.
 */
@Configuration
@EnableWebSecurity
class WebSecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    lateinit var tokenTokenProvider: JwtTokenProvider

    override fun configure(auth: AuthenticationManagerBuilder) {
        val userDetailsService = mongoUserDetails()
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder())
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.httpBasic().disable().csrf().disable().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
                .antMatchers("/v2/api-docs",
                        "/configuration/ui",
                        "/swagger-resources",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "/webjars/**",
                        "/swagger-resources/configuration/ui",
                        "/swagger-ui.html").permitAll()
                .antMatchers("/authentication/login").permitAll()
                .antMatchers("/authentication/register").permitAll()
                .antMatchers("/authentication/retrieveUser").hasAnyAuthority()
                .antMatchers("/booking/**").hasAnyAuthority().anyRequest().authenticated()
                .antMatchers("/restaurants/**").hasAnyAuthority().anyRequest().authenticated()
                .antMatchers("/tables/**").hasAnyAuthority().anyRequest().authenticated()
                .and().csrf()
                .disable().exceptionHandling().authenticationEntryPoint(unauthorizedEntryPoint()).and()
                .apply(JwtConfigurer(tokenTokenProvider))
    }

    @Bean
    fun bCryptPasswordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    @Throws(Exception::class)
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    @Bean
    fun unauthorizedEntryPoint(): AuthenticationEntryPoint {
        return AuthenticationEntryPoint { request, response, authException ->
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED)
        }
    }

    @Bean
    fun mongoUserDetails(): UserDetailsService {
        return CustomUserDetailsService()
    }
}