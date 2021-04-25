package com.example.onlinebookstore.configuration;

import com.example.onlinebookstore.service.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    //Password Encoder berfungsi agar ketika registrasi, passwordnya juga ter-encreate
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserSecurityService userService;
    @Autowired
    private AuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()

                //Berarti kalau ada patterin speerti ini, apa yang harus dilakukan
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/anonymous*").anonymous()
                .antMatchers("/login*").permitAll()
                .antMatchers("/sign-up*").permitAll()
                .antMatchers("/assets/**").permitAll()
                .antMatchers("/home*").permitAll()
                .antMatchers("/home-company*").permitAll()
                .antMatchers("/product*").permitAll()
                .antMatchers("/privacy-policy*").permitAll()

                //Selebihnya (selain pattern yang ada diatas), di-autentikasi
                //kalau gamau di-autentikasi, bisa diganti jadi .anyRequest().permitAll()
                //Kalau seperti itu, maka dari login kebawah tidak akan berlaku, karena request apapun diperbolehkan
                .anyRequest().authenticated()
                .and()
                .formLogin()

                //di-autentikasinya itu, dijalankan di line ini, yakni ke page login
                .loginPage("/login")

                //mau login kesini
                .loginProcessingUrl("/perform_login")
                .successHandler(myAuthenticationSuccessHandler)

                //kalau gagal login kesini
                .failureUrl("/login?error=true")
                .and()
                .logout()

                //kalau mau logout kesini
                .logoutUrl("/perform_logout")
                .logoutSuccessUrl("/login?logout=true")
                .deleteCookies("JSESSIONID");
    }
}
