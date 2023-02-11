package com.edu.miu.backend.config;

import com.edu.miu.backend.filter.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configurable
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private UserDetailsService userService;
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    private void setUserService(UserDetailsService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setJwtRequestFilter(JwtRequestFilter jwtRequestFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    protected void configure(HttpSecurity http) throws Exception {
        String[] allowedNonAuthUrls = new String[]{
                "/",
                "/users",
                "/brands",
                "/cars",
                "/auth/**",
                "/users/**",
                "/swagger-ui/**",
                "/v3/api-docs/**",
                "/swagger-ui.html",
        };
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(allowedNonAuthUrls)
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS); // tell spring not to manage sessions

        // add the security filter from here to tell spring how to manage security and create
        // sessions for every request
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        http.headers().frameOptions().sameOrigin();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
