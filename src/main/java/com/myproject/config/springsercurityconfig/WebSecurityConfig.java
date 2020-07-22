package com.myproject.config.springsercurityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // Sét đặt dịch vụ để tìm kiếm User trong Database.
        // Và sét đặt PasswordEncoder.
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    //    @Override
//    protected void configure(final HttpSecurity http) throws Exception {
//
//        http.csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/", "/home", "/login", "/about", "/api/**").permitAll()
//                .antMatchers("/user/**").hasAnyRole("ROLE_USER")
//                .antMatchers("/admin/**").hasAnyRole("ROLE_ADMIN")
//                .anyRequest().authenticated()
//
//                // Khi người dùng đã login, với vai trò XX.
//                // Nhưng truy cập vào trang yêu cầu vai trò YY,
//                // Ngoại lệ AccessDeniedException sẽ ném ra.
//                .and().exceptionHandling().accessDeniedHandler(accessDeniedHandler)
//
//                // Các trang không yêu cầu login
////        http.authorizeRequests().antMatchers("/","/home", "/login","/about","/api/**").permitAll();
//
//                // Trang /userInfo yêu cầu phải login với vai trò ROLE_USER hoặc ROLE_ADMIN.
//                // Nếu chưa login, nó sẽ redirect tới trang /login.
////        http.authorizeRequests().antMatchers("/user/**").access("hasAnyRole('ROLE_USER')");
////
////        // Trang chỉ dành cho ADMIN
////        http.authorizeRequests().antMatchers("/admin/**").hasAnyRole("ROLE_ADMIN") //.access("hasRole('ROLE_ADMIN')");
//
//
//                // Cấu hình cho Login Form.
//                .and().formLogin()
//                // Submit URL của trang login
//                .loginProcessingUrl("/j_spring_security_check") // Submit URL
//                .loginPage("/login")//
//                .defaultSuccessUrl("/home")
//                .defaultSuccessUrl("/about")//
//                .usernameParameter("username")//
//                .passwordParameter("password")
//                // Cấu hình cho Logout Page.
//                .and().logout().permitAll()
//
//                // Cấu hình Remember Me.
//                .and() //
//                .rememberMe().tokenRepository(this.persistentTokenRepository()) //
//                .tokenValiditySeconds(1 * 24 * 60 * 60); // 24h
//
//    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.
                authorizeRequests()
                .antMatchers("/", "/home", "/about","/api/**").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/user/**").hasAuthority("ROLE_USER")
                .antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable().formLogin()
                .loginPage("/login")
                //.defaultSuccessUrl("/home")
                .usernameParameter("username")
                .passwordParameter("password")

                .and().logout().permitAll()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login").and().exceptionHandling().accessDeniedHandler(accessDeniedHandler)
                .and() //
                .rememberMe().tokenRepository(this.persistentTokenRepository()) //
                .tokenValiditySeconds(1 * 24 * 60 * 60); // 24h
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }

    // save into database
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
        db.setDataSource(dataSource);
        return db;
    }
}
