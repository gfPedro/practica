package com.practica.oee.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.practica.oee.jwt.JwtAuthTokenFilter;

@Configuration
@EnableWebSecurity
@Order(1000)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ConfiguracionSeguridad extends WebSecurityConfigurerAdapter {

	private final String PERFIL_LOGADO = "LOGADO";

	private static final String APIMATCHER_USUARIOS = "/servicios/usuarios";
	private static final String APIMATCHER_LOGIN = "/servicios/usuarios/login";

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().cors().and().csrf()
				.disable().authorizeRequests()
				// Acceso a la consola h2
				.antMatchers("/", "/h2-console/**", "/actuator/**", "/error").permitAll()
				// Usuarios sin necesidad de autorización
				.antMatchers(HttpMethod.POST, APIMATCHER_LOGIN).permitAll()
				.antMatchers(HttpMethod.POST, APIMATCHER_USUARIOS).permitAll()
				.antMatchers(HttpMethod.GET, APIMATCHER_USUARIOS).hasAuthority(this.PERFIL_LOGADO)
				// El resto necesitan tener autorización
				.anyRequest().authenticated();

		http.addFilterBefore(this.authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class)
				.authorizeRequests().filterSecurityInterceptorOncePerRequest(true);
	}

	@Bean
	public JwtAuthTokenFilter authenticationJwtTokenFilter() {
		return new JwtAuthTokenFilter();
	}

}
