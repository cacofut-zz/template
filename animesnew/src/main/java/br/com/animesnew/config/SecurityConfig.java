package br.com.animesnew.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private DataSource dataSource;
	
	
	@Autowired
	protected void configureGlobalSecurity( AuthenticationManagerBuilder auth ) throws Exception{
		
		auth.jdbcAuthentication()
			.dataSource ( dataSource )
			.passwordEncoder( passwordEncoder() )
			
			.usersByUsernameQuery( 
				"select "
				+ "usuario_user username,"
				+ "senha_user password, "
				+ "ativo_user enabled "
				+ "from user "
				+ "where usuario_user = ?" )
			
			.authoritiesByUsernameQuery(
			   "select usuario_user username, nome_autoridade authority "
			   + "from user, user_has_autoridade, autoridade "
			   + "where id_user       = user_id_user"
			   + "  and id_autoridade = autoridade_id_autoridade "
			   + "  and usuario_user  = ?");
		
	}
	
	@Override
	protected void configure( HttpSecurity http ) throws Exception{
		
		http
		.formLogin()
			.loginPage( "/login" )
			.defaultSuccessUrl( "/home" )
			.loginProcessingUrl( "/login" )
			.usernameParameter( "usuario" )
			.passwordParameter( "senha" )
		.and()
			.exceptionHandling().accessDeniedPage( "/403" )
			
			
		.and()
			.logout()
				.logoutUrl( "/logout" )
				.logoutSuccessUrl( "/login" )
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		
		.and()
			.rememberMe()
				.tokenRepository( new InMemoryTokenRepositoryImpl() )
				.tokenValiditySeconds( 2419200 )
				.key( "animesNew" )
		.and()
			.authorizeRequests()
				.antMatchers( "/admin/**" ).hasRole( "ADMIN" )
				.antMatchers( "/autenticado/**" ).authenticated()				
				.anyRequest().permitAll();
								
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder;
	}
}
