package com.myapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.myapp.util.Constantes;

@Configuration
// http://docs.spring.io/spring-boot/docs/current/reference/html/howto-security.html
// Switch off the Spring Boot security configuration
//@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;
    
    /* Faz a injeção do UsuarioDAO
	@Autowired
	private UsuarioDAO usuarioDAO;
	*/

    // roles admin allow to access /admin/**
    // roles user allow to access /user/**
    // custom 403 access denied handler
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/home", "/about").permitAll()
                .antMatchers("/admin/**").hasAnyRole(Constantes.PERMISSAO_ADMINISTRADOR)
                .antMatchers("/listaconvidados").hasAnyRole(Constantes.PERMISSAO_ADMINISTRADOR)
                .antMatchers("/alteraconvidado").hasAnyRole(Constantes.PERMISSAO_ADMINISTRADOR)
                .antMatchers("/buscarUsuario").hasAnyRole(Constantes.PERMISSAO_ADMINISTRADOR)
                .antMatchers("/resultadoBusca").hasAnyRole(Constantes.PERMISSAO_ADMINISTRADOR)
                .antMatchers("/listarUsuarios").hasAnyRole(Constantes.PERMISSAO_ADMINISTRADOR)
                .antMatchers("/alterarUsuario").hasAnyRole(Constantes.PERMISSAO_ADMINISTRADOR)
                .antMatchers("/inserirUsuario").hasAnyRole(Constantes.PERMISSAO_ADMINISTRADOR)
                .antMatchers("/detalharUsuario").hasAnyRole(Constantes.PERMISSAO_ADMINISTRADOR)
                .antMatchers("/removerUsuario").hasAnyRole(Constantes.PERMISSAO_ADMINISTRADOR)
                .antMatchers("/meuperfil").hasAnyRole(Constantes.PERMISSAO_ADMINISTRADOR)
                .antMatchers("/meuperfil/*").hasAnyRole(Constantes.PERMISSAO_ADMINISTRADOR)
                .antMatchers("/perfilUsuario").hasAnyRole(Constantes.PERMISSAO_ADMINISTRADOR)
                .antMatchers("/salvar").hasAnyRole(Constantes.PERMISSAO_ADMINISTRADOR)
                .antMatchers("/alterar").hasAnyRole(Constantes.PERMISSAO_ADMINISTRADOR)
                .antMatchers("/alterar/").hasAnyRole(Constantes.PERMISSAO_ADMINISTRADOR)
                .antMatchers("/alterar/*").hasAnyRole(Constantes.PERMISSAO_ADMINISTRADOR)
                .antMatchers("/alterar/**").hasAnyRole(Constantes.PERMISSAO_ADMINISTRADOR)
                .antMatchers("/remover/**").hasAnyRole(Constantes.PERMISSAO_ADMINISTRADOR)
                .antMatchers("/user/**").hasAnyRole(Constantes.PERMISSAO_USUARIO)
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("user").password("password").roles(Constantes.PERMISSAO_USUARIO)
                .and()
                .withUser("admin").password("password").roles(Constantes.PERMISSAO_ADMINISTRADOR);
        
        /* Faz a busca dos dados do Usuário acessando o banco da aplicação
         * 
         auth.userDetailsService(usuarioDAO)
        .passwordEncoder(new BCryptPasswordEncoder());
         */
    }
    	
}