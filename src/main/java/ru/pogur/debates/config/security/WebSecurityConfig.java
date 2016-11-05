package ru.pogur.debates.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import ru.pogur.debates.service.security.UserDetailsServiceImpl;

/**
 * Created by dima-pc on 06.02.2016.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    public void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
//                .passwordEncoder(getShaPasswordEncoder());
        ;
    }
/*
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user")  // #1
                .password("password")
                .roles("USER")
                .and()
                .withUser("admin") // #2
                .password("password")
                .roles("ADMIN", "USER");
    }*/

    @Bean
    public SavedRequestAwareAuthenticationSuccessHandler successHandler() {
        SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
//        successHandler = new AuSu
//        successHandler.setTargetUrlParameter("/secure/");
        return successHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // �������� ������ �� CSRF ����
        http.csrf()
                .disable()
        ;

        http.formLogin()
                // ��������� �������� � ������ ������
                .loginPage("/login")
                        // ��������� action � ����� ������
                .loginProcessingUrl("/j_spring_security_check")
                        // ��������� URL ��� ��������� ������
                .failureUrl("/login?error")
                .defaultSuccessUrl("/index")
//                .successHandler(successHandler())
                        // ��������� ��������� ������ � ������ � ����� ������
                .usernameParameter("j_username")
                .passwordParameter("j_password")
                        // ���� ������ � ����� ������ ����
                .permitAll();

        http.logout()
                // ��������� ������ ������ ����
                .permitAll()
                        // ��������� URL �������
                .logoutUrl("/logout")
                        // ��������� URL ��� ������� �������
                .logoutSuccessUrl("/login?logout")
                        // ������ �� �������� ������� ������
                .invalidateHttpSession(true);

        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/static/**").permitAll()
                .antMatchers("/user/**").access("hasRole('ROLE_USER')")
                .antMatchers("/routes/**").access("hasRole('ROLE_USER')")
                .antMatchers("/index/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/index").hasAnyRole("USER", "ADMIN")
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')");
    }

    // ��������� Spring ����������, ��� ���� ���������������� <b></b>ShaPasswordEncoder
    // ��� ����� ������� � WebAppConfig, �� ��� ������������ ������� ���
//    @Bean
//    public ShaPasswordEncoder getShaPasswordEncoder() {
//        return new ShaPasswordEncoder();
//    }
}
