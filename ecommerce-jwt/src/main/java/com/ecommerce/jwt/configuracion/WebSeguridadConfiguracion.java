package com.ecommerce.jwt.configuracion;

import com.ecommerce.jwt.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSeguridadConfiguracion extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService jwtService;

    @Autowired
    private JwtAutenticacionEntryPoint jwtAutenticacionEntryPoint;

    @Autowired
    private JwtFiltroResquest jwtFiltroResquest;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors();
        httpSecurity.csrf().disable()//, "/listarLosProductos","/eliminarDetalleProducto/{productoId}","/listarProductoDetallePorId/{productoId}"
                .authorizeRequests().antMatchers("/autenticacion","/registrarUsuario","/listarLosProductos","/listarProductoDetallePorId/{productoId}").permitAll()
                .antMatchers(HttpHeaders.ALLOW).permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(jwtAutenticacionEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                ;

        httpSecurity.addFilterBefore(jwtFiltroResquest, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configuracionGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(jwtService).passwordEncoder(passwordEncoder());
    }
}
