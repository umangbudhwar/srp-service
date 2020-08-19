package com.srp.configuration.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.srp.framework.filters.JwtRequestFilter;
import com.srp.service.MyUserDetailsService;

import lombok.extern.slf4j.Slf4j;

@EnableWebSecurity
@Slf4j
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    MyUserDetailsService userDetailsService;

    @Autowired
    JwtRequestFilter jwtRequestFilter;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // configure AuthenticationManager so that it knows from where to load // user
        // for matching credentials // Use BCryptPasswordEncoder
        auth.userDetailsService(userDetailsService)
            .passwordEncoder(getPasswordEncoder());
    }

    /* For Authentication */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // log.info("in authentication configure");
        auth.userDetailsService(userDetailsService)
            .passwordEncoder(getPasswordEncoder());
    }

    /* For Authorization */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // log.info("in authorization configure");
        http.cors()
            .and()
            .csrf()
            .disable()
            .authorizeRequests()
            .antMatchers("/**/home", "/**/getStudentForReportGeneration/**", "/**/updateStudents",
                "/**/fetchStudent/**","/updateBatchGroupCode","/**/loadCountOfStudentYearWise",
                "/**/fetchYearWiseStudentRecords/**")
            .hasAnyRole("FACULTY", "ADMIN")
            .antMatchers("/**/authenticate", "/**/registerFaculty", "/**/registerStudent", "/**/getSubjects", 
                "/**/getStreams","/**/findIfUserNameExist/**","/**/findIfFacultyUserNameExist/**",
                "/**fetchStudentForVerification/**","/**/addSubjects")
            .permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedHeaders(Arrays.asList("*"));
        corsConfiguration.setAllowedOrigins(Arrays.asList("*"));
        corsConfiguration.setExposedHeaders(Arrays.asList("x-auth-token"));
        corsConfiguration.setAllowedMethods(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return urlBasedCorsConfigurationSource;
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
