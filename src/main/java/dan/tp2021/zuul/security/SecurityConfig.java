package dan.tp2021.zuul.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.cors()
			.and()
				.authorizeRequests()
					.antMatchers(HttpMethod.GET, "/**")
						.hasAnyAuthority("administracion")
					.antMatchers(HttpMethod.POST, "/**")
						.hasAnyAuthority("administracion")
					.antMatchers(HttpMethod.PUT, "/**")
						.hasAnyAuthority("administracion")
					.antMatchers(HttpMethod.DELETE, "/**")
						.hasAnyAuthority("administracion")
					.antMatchers(HttpMethod.GET, "/ctacte/api/**")
						.hasAnyAuthority("lectura", "escritura")
					.antMatchers(HttpMethod.POST, "/ctacte/api/**")
						.hasAnyAuthority("escritura")
					.antMatchers(HttpMethod.PUT, "/ctacte/api/**")
						.hasAnyAuthority("escritura")
					.antMatchers(HttpMethod.DELETE, "/ctacte/api/**")
						.hasAnyAuthority("escritura")
					.antMatchers(HttpMethod.GET, "/usuarios/api/**")
						.hasAnyAuthority("lectura", "escritura")
					.antMatchers(HttpMethod.POST, "/usuarios/api/**")
						.hasAnyAuthority("escritura")
					.antMatchers(HttpMethod.PUT, "/usuarios/api/**")
						.hasAnyAuthority("escritura")
					.antMatchers(HttpMethod.DELETE, "/usuarios/api/**")
						.hasAnyAuthority("escritura")
//					.antMatchers(HttpMethod.GET, "/pedidos/api/empleado/**")
//						.not().hasAuthority("lectura") Algo asi, no se si el cliente puede tener permisos para ver informacion de los empleados
					.antMatchers(HttpMethod.GET, "/pedidos/api/**")
						.hasAnyAuthority("lectura", "escritura")
					.antMatchers(HttpMethod.POST, "/pedidos/api/**")
						.hasAnyAuthority("escritura")
					.antMatchers(HttpMethod.PUT, "/pedidos/api/**")
						.hasAnyAuthority("escritura")
					.antMatchers(HttpMethod.DELETE, "/pedidos/api/**")
						.hasAnyAuthority("escritura")
					.antMatchers(HttpMethod.GET, "/productos/api/**")
						.hasAnyAuthority("lectura", "escritura")
					.antMatchers(HttpMethod.POST, "/productos/api/**")
						.hasAnyAuthority("escritura")
					.antMatchers(HttpMethod.PUT, "/productos/api/**")
						.hasAnyAuthority("escritura")
					.antMatchers(HttpMethod.DELETE, "/productos/api/**")
						.hasAnyAuthority("escritura")
					.anyRequest().authenticated()
					.and() .oauth2ResourceServer()
					.jwt()
				.jwtAuthenticationConverter(new CustomJwtAuthenticationConverter());
	}
}
