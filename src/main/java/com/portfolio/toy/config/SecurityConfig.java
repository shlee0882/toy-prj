package com.portfolio.toy.config;

import com.portfolio.toy.common.security.CustomAccessDeniedHandler;
import com.portfolio.toy.common.security.CustomLoginSuccessHandler;
import com.portfolio.toy.common.security.CustomNoOpPasswordEncoder;
import com.portfolio.toy.common.security.CustomUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.sql.DataSource;

@Slf4j
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 권한 설정
        http.authorizeRequests().antMatchers("/board/list").permitAll();
        http.authorizeRequests().antMatchers("/board/register").hasRole("MEMBER");
        http.authorizeRequests().antMatchers("/notice/list").permitAll();
        http.authorizeRequests().antMatchers("/notice/register").hasRole("ADMIN");

        http.formLogin()
                .loginPage("/login")
                .successHandler(authenticationSuccessHandler());

        http.logout()
                .logoutUrl("/logout").invalidateHttpSession(true);

        // 접근거부 단순 URI 지정
        // http.exceptionHandling().accessDeniedPage("/accessError");

        // 사용자 정의 접근거부 처리자 지정
        http.exceptionHandling().accessDeniedHandler(createAccessDeniedHandler());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 로그인 id/pw 설정 권한 부여
//        auth.inMemoryAuthentication()
//                .withUser("member")
//                .password("{noop}1234")
//                .roles("MEMBER");
//
//        auth.inMemoryAuthentication()
//                .withUser("admin")
//                .password("{noop}1234")
//                .roles("ADMIN");

//        // 인증할때 필요한 쿼리
//        String query1 = "SELECT user_id , user_pw , enabled FROM member WHERE user_id = ?";
//        // 권한 확인 할때 필요한 쿼리
//        String query2 ="SELECT b.user_id, a.auth FROM member_auth a, member b WHERE a.user_no = b.user_no AND b.user_id = ?";
//
//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .usersByUsernameQuery(query1)
//                .authoritiesByUsernameQuery(query2)
//                .passwordEncoder(createPasswordEncoder());

//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .passwordEncoder(passwordEncoder());

        auth.userDetailsService(createUserDetailsService())
                .passwordEncoder(createPasswordEncoder());
    }

    @Bean
    public UserDetailsService customUserDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public UserDetailsService createUserDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public AccessDeniedHandler createAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public AuthenticationSuccessHandler createAuthenticationSuccessHandler() {
        return new CustomLoginSuccessHandler();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new CustomLoginSuccessHandler();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new CustomNoOpPasswordEncoder();
    }

    @Bean
    public PasswordEncoder createPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
